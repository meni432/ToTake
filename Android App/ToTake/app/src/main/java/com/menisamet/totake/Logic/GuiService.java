package com.menisamet.totake.Logic;

import android.content.Context;

import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;
import com.menisamet.totake.Server.Listeners.UserLoadListener;
import com.menisamet.totake.Server.LogicInterface;
import com.menisamet.totake.Server.LogicService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

// TODO need to deal with similar item name (this is Logic problem, not gui problem)- OK!!!!

public class GuiService implements GuiInterface {

    private static final GuiService ourInstance = new GuiService();
    private User currentUser;
    private List<Item> mAllItems;
    private List<Item> recommendationList;
    private static int RECOMMENDATION_LIST_SIZE_MAX = 30;
    private static int RECOMMENDATION_LIST_SIZE_MIN = 20;
    private LogicInterface server = LogicService.getInstance();

    public static GuiService getInstance() {
        return ourInstance;
    }

    private GuiService() {

    }


    @Override
    public void setUserId(long userId, final UserLoadListener userLoadListener) {
        server.setUserId(userId, new UserLoadListener() {
            @Override
            public void onUserLoad(User user) {
                currentUser = user;
                userLoadListener.onUserLoad(user);
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
            Collections.reverse(trips);
            return trips;
        }
        return null;
    }

    @Override
    public Trip getTripById(long tripId) {
        return currentUser.getTrip(tripId);
    }

    @Override
    public void addNewTrip(final String destinationName, Date startDate, Date endDate, String googlePlaceId, final AddNewTripResponseListener addNewTripResponseListener) {
        server.addNewTrip(destinationName, startDate, endDate, googlePlaceId,  new AddNewTripResponseListener() {
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
        server.assignItemToTrip(trip, item, amount, new AddNewItemResponseListener() {
            @Override
            public void onResponse(Item item) {
                trip.addItemToList(item);
                addNewItemResponseListener.onResponse(item);
            }
        });
    }

    @Override
    public void deleteItemFromTrip(Trip trip, Item item) {
//        server.deleteItemFromTrip(trip, item);
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
        //TODO need to change for realy recomendation protocol
        getAllItems(new AllItemsResponseListener() {
            @Override
            public void onResponse(List<Item> items) {
                List<Item> itemList = new ArrayList<Item>();
                for (Item item : items) {
                    if (!trip.getItems().contains(item)) {
                        itemList.add(item);
                    }
                }
                Collections.sort(itemList, new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (Math.random() > 0.5) {
                            return 1;
                        }
                        return -1;
                    }
                });
                recommendationListResponseListener.onResponse(itemList);
            }
        });
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
}
