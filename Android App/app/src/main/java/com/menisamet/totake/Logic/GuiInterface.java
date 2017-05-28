package com.menisamet.totake.Logic;

import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public interface GuiInterface {
    public User getUserDetails();
    public void addNewTrip(String destinationName, Date Start, Date end, AddNewTripResponseListener addNewTripResponseListener);
    // TODO need to deal with similar item name (this is Logic problem, not gui problem)- OK!!!!
    public void addNewItem(String itemName, long amount, int tripId, AddNewItemResponseListener addNewItemResponseListener); //need trip id to know to which list to add
    public void deleteItemFromTrip(int tripId, int itemId);
    public void deleteTrip(int tripId);
    public List<Trip> getAllTrips(); // all trip for current user
    public void getAllItems(final AllItemsResponseListener allItemsResponseListener); // all items in world for autocomplete
    /**
     * get trip and return recommendation list
     */
    public void getRecommendationList(int tripId, RecommendationListResponseListener recommendationListResponseListener);
    public void deleteRecommendation(int itemId, int TripId, final RecommendationListResponseListener recommendationListResponseListener);
    public void notifyChangeAmount(int tripId, int itemId,Long newAmount);
    public List<Item> getSuggestionForSearch(String query);

    /**
     * i want that every time you open the application you need to identify with your user id
     * this way the list be on your app in the moment you in
     * @param userId
     */
    public void connect(int userId);


}
