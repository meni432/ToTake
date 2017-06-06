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
        server.getAllItems(new AllItemsResponseListener() {
            @Override
            public void onResponse(List<Item> items) {
                mAllItems = items;
            }
        });
    }


    @Override
    public void setUserId(long userId, UserLoadListener userLoadListener) {
        server.setUserId(userId, new UserLoadListener() {
            @Override
            public void onUserLoad(User user) {
                currentUser = user;
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
            return currentUser.getTrips();
        }
        return null;
    }

    @Override
    public Trip getTripById(long tripId) {
        return currentUser.getTrip(tripId);
    }

    @Override
    public void addNewTrip(String destinationName, Date startDate, Date endDate, final AddNewTripResponseListener addNewTripResponseListener) {
        server.addNewTrip(destinationName, startDate, endDate, new AddNewTripResponseListener() {
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
    public void deleteItemFromTrip(Trip trip, Item item) {
        server.deleteItemFromTrip(trip, item);
    }

    @Override
    public void getAllItems(AllItemsResponseListener allItemsResponseListener) {
        allItemsResponseListener.onResponse(mAllItems);
    }

    @Override
    public void getRecommendationList(Trip trip, RecommendationListResponseListener recommendationListResponseListener) {

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
