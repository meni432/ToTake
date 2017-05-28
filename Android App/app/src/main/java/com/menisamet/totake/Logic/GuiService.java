package com.menisamet.totake.Logic;

import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;
import com.menisamet.totake.Server.LogicInterface;
import com.menisamet.totake.Server.LogicService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

// TODO need to deal with similar item name (this is Logic problem, not gui problem)- OK!!!!

public class GuiService implements GuiInterface {

    private static final GuiService ourInstance = new GuiService();
    private User thisAppUser;
    private List<Item> allItems;
    private List<Item> recommendationList;
    private static int RECOMMENDATION_LIST_SIZE_MAX = 30;
    private static int RECOMMENDATION_LIST_SIZE_MIN = 20;
    private LogicInterface server = LogicService.getInstance();

    public static GuiService getInstance() {
        return ourInstance;
    }

    private GuiService() {

    }


    /**
     * finished
     * @return this upp user
     */
    @Override
    public User getUserDetails() {
        return this.thisAppUser;
    }

    /**
     * finished
     * @param destinationName
     * @param Start
     * @param end
     * send to listener a new trip from the server with the first defult items (for the geven data on the trip)
     */
    @Override
    public void addNewTrip(String destinationName, Date Start, Date end, final AddNewTripResponseListener addNewTripResponseListener) {
        server.addNewTrip(destinationName, Start, end, thisAppUser.getIdUser(), new AddNewTripResponseListener() {
            @Override
            public void onResponse(Trip trip) {
                thisAppUser.addNewTrip(trip);
                addNewTripResponseListener.onResponse(trip);
            }
        });

        // TODO debug only
        addNewTripResponseListener.onResponse(null);
    }


    /**
     * finished
     * send to given listener a new item and add this item to the trip with the given trip id
     * @param itemName
     * @param amount
     * @param tripId
     * @param addNewItemResponseListener
     */
    @Override
    public void addNewItem(String itemName, long amount, final int tripId, final AddNewItemResponseListener addNewItemResponseListener) {
        server.addNewItem(itemName, amount, tripId, new AddNewItemResponseListener() {
            @Override
            public void onResponse(Item item) {
                thisAppUser.addItemToTrip(tripId, item);
                addNewItemResponseListener.onResponse(item);
            }
        });
    }

    /**
     * finished
     * remove this item from this trip
     * @param tripId
     * @param itemId
     */
    @Override
    public void deleteItemFromTrip(int tripId, int itemId) {
        thisAppUser.getTrip(tripId).removeItemFromList(itemId);
        server.deleteItemFromTrip(thisAppUser.getIdUser(),tripId, itemId);
    }

    /**
     * finished
     * delete this trip from users trip list
     * @param tripId
     */
    @Override
    public void deleteTrip(int tripId) {
        thisAppUser.deleteTrip(tripId);
        server.deleteTrip(thisAppUser.getIdUser(), tripId);
    }

    /**
     * finished
     * @return all the trip of this user
     */
    @Override
    public List<Trip> getAllTrips() {
        return thisAppUser.getUserTripList();
    }

    /**
     * finished
     * @param allItemsResponseListener
     */
    @Override
    public void getAllItems(final AllItemsResponseListener allItemsResponseListener) {
        server.getAllItems(new AllItemsResponseListener() {
            @Override
            public void onResponse(List<Item> items) {
                allItems = new ArrayList<Item>(items);
                allItemsResponseListener.onResponse(items);
            }
        }, null);  // should get context instead null!!!!!!
    }


    /**
     * finished
     * @param tripId
     * @return a recommendation list for this trip
     */
    @Override
    public void getRecommendationList(int tripId, final RecommendationListResponseListener recommendationListResponseListener) {
        server.getRecommendationList(tripId, new RecommendationListResponseListener() {
            @Override
            public void onResponse(List<Item> recommendedItems) {
                recommendationList = new ArrayList<Item>(recommendedItems);
                recommendationListResponseListener.onResponse(recommendedItems);
            }
        });
    }

    @Override
    public void deleteRecommendation(int itemId, int tripId, final RecommendationListResponseListener recommendationListResponseListener) {
        for (int i = 0; i < recommendationList.size(); i++) {
            if(recommendationList.get(i).getItemID() == itemId){
                recommendationList.remove(i);
            }
        }
        if(recommendationList.size() <= RECOMMENDATION_LIST_SIZE_MIN ){
            getRecommendationList(tripId, recommendationListResponseListener);
        }
    }

    /**
     * finished
     * update item amount ana given:
     * @param tripId
     * @param itemId
     * @param newAmount
     */
    @Override
    public void notifyChangeAmount(int tripId, int itemId, Long newAmount) {
        Trip trip = this.thisAppUser.getTrip(tripId);
        trip.setItemAmount(itemId, newAmount);
        server.notifyChangeAmount(itemId, itemId, newAmount);
    }

    /**
     * Completion of words to avoid mistakes (like shoe and shoes - same)
     * @param query
     * @return
     */
    @Override
    public List<Item> getSuggestionForSearch(String query) {
        return null;
    }

    /**
     * connect with server and get user details
     * should be called every time open the app
     * @param userId
     */
    @Override
    public void connect(int userId) {
        if(server.ifUserExist(userId)){ //if this user already signed in
            thisAppUser = server.getUser(userId); //synchronize.
        }
        else{ //else need to get information from fireBase
            String userName=""; //need to somehow get user name from fireBase
            String userEmail= "";
            thisAppUser = server.addNewUser(userId, userName, userEmail);
        }
        //need to update the server so he update the DB on this user
    }

}
