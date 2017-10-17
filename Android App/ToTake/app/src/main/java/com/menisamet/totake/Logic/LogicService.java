package com.menisamet.totake.Logic;

import android.content.Context;
import android.util.Log;

import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;
import com.menisamet.totake.Server.Listeners.UserLoadListener;
import com.menisamet.totake.Server.ServerInterface;
import com.menisamet.totake.Server.ServerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

// TODO need to deal with similar item name (this is Logic problem, not gui problem)- OK!!!!

public class LogicService implements LogicInterface {

    private static final String TAG = LogicService.class.getCanonicalName();
    private static final LogicService ourInstance = new LogicService();
    private User currentUser;
    private List<Item> mAllItems;
    private List<Item> recommendationList;
    private static int RECOMMENDATION_LIST_SIZE_MAX = 30;
    private static int RECOMMENDATION_LIST_SIZE_MIN = 20;
    private ServerInterface server = ServerService.getInstance();
    private OnUserChangeListener onUserChangeListener = null;

    public static LogicService getInstance() {
        return ourInstance;
    }

    private LogicService() {

    }


    @Override
    public void setFireBaseUser(String firebaseUser, String userName, final UserLoadListener userLoadListener) {
        server.setFireBaseUserId(firebaseUser, userName, new UserLoadListener() {
            @Override
            public void onUserLoad(User user) {
                currentUser = user;
                noticeListenerOnChangeUser(user);
                userLoadListener.onUserLoad(user);
            }
        });

        userLoadListener.onUserLoad(null);
    }

    @Override
    public void setUserId(long userId, final UserLoadListener userLoadListener) {
        server.setUserId(userId, new UserLoadListener() {
            @Override
            public void onUserLoad(User user) {
                currentUser = user;
                userLoadListener.onUserLoad(user);
                noticeListenerOnChangeUser(user);
            }
        });
    }

    @Override
    public User getUser() {
        return currentUser;
    }

    @Override
    public List<Trip> getAllTrips() {
        if (currentUser != null) {
            final List<Trip> trips = currentUser.getTrips();
            if (trips != null && trips.size() > 0) {
                Collections.reverse(trips);
                return trips;
            }
        }
        return null;
    }

    @Override
    public Trip getTripById(long tripId) {
        return currentUser.getTrip(tripId);
    }

    @Override
    public void addNewTrip(final String destinationName, Date startDate, Date endDate, String googlePlaceId, final AddNewTripResponseListener addNewTripResponseListener) {
        server.addNewTrip(destinationName, startDate, endDate, googlePlaceId, new AddNewTripResponseListener() {
            @Override
            public void onResponse(Trip trip) {
                currentUser.addNewTrip(trip);
                addNewTripResponseListener.onResponse(trip);
            }
        });
    }

    @Override
    public void deleteTrip(Trip trip) {
        server.deleteTrip(trip);
    }

    @Override
    public void addNewItem(final Trip trip, String itemName, long amount, AddNewItemResponseListener addNewItemResponseListener) {
        server.addNewItem(trip, itemName, amount, new AddNewItemResponseListener() {
            @Override
            public void onResponse(Item item) {
                trip.addItemToList(item);
            }
        });
    }

    @Override
    public void assignItemToTrip(final Trip trip, Item item, long amount, final AddNewItemResponseListener addNewItemResponseListener) {
        trip.addItemToList(item);
        server.assignItemToTrip(trip, item, amount, new AddNewItemResponseListener() {
            @Override
            public void onResponse(Item item) {
                addNewItemResponseListener.onResponse(item);
            }
        });
    }

    @Override
    public void deleteItemFromTrip(Trip trip, Item item) {
        server.deleteItemFromTrip(trip, item);
        // TODO need to implement
        trip.getItems().remove(item);
    }

    @Override
    public void getAllItems(final AllItemsResponseListener allItemsResponseListener) {
        if (mAllItems == null) {
            server.getAllItems(new AllItemsResponseListener() {
                @Override
                public void onResponse(List<Item> items) {
                    mAllItems = items;
                    allItemsResponseListener.onResponse(mAllItems);
                }
            });
        } else {
            allItemsResponseListener.onResponse(mAllItems);
        }
    }

    @Override
    public void getRecommendationList(final Trip trip, final RecommendationListResponseListener recommendationListResponseListener) {
        server.getRecommendationList(trip, new RecommendationListResponseListener() {
            @Override
            public void onResponse(List<Item> recommendedItems, boolean override) {
                List<Item> itemList = new ArrayList<Item>();
                if (recommendedItems != null && recommendedItems.size() > 0) {
                    for (Item item : recommendedItems) {
                        if (!trip.getItems().contains(item)) {
                            itemList.add(0, item);
                        } else {
                            assignItemToTrip(trip, item, 1, new AddNewItemResponseListener() {
                                @Override
                                public void onResponse(Item item) {
                                    Log.d(TAG, "Re assign item to trip after get a prediction" + item);
                                }
                            });
                        }
                    }
                    recommendationListResponseListener.onResponse(itemList, false);
                } else {
                    getAllItems(new AllItemsResponseListener() {
                        @Override
                        public void onResponse(List<Item> items) {
                            List<Item> allItemList = new ArrayList<Item>();
                            for (Item item : items) {
                                if (!trip.getItems().contains(item)) {
                                    allItemList.add(item);
                                }
                            }
                            recommendationListResponseListener.onResponse(allItemList, true);
                        }
                    });
                }

            }
        });
    }

    @Override
    public void removeFromRecommendationList(Trip trip, Item item) {
        server.removeFromRecommendationList(trip, item);
    }

    @Override
    public void notifyChangeAmount(Trip trip, Item item) {
        server.notifyChangeAmount(trip, item);
    }

    @Override
    public List<Item> getSuggestionForSearch(String query) {
        return null;
    }

    @Override
    public void setContext(Context context) {
        server.setContext(context);
    }


    private void noticeListenerOnChangeUser(User user) {
        if (this.onUserChangeListener != null) {
            this.onUserChangeListener.getCurrentUser(user);
        }
    }

    public void setOnUserChangeListener(OnUserChangeListener onUserChangeListener) {
        this.onUserChangeListener = onUserChangeListener;
    }
}
