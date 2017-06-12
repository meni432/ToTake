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

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public interface GuiInterface {
    public void setUserId(long userId, UserLoadListener userLoadListener);
    public User getUser();
    public List<Trip> getAllTrips();
    public Trip getTripById(long id);
    public void addNewTrip(String destinationName, Date startDate, Date endDate, String googlePlaceId,  final AddNewTripResponseListener addNewTripResponseListener);
    public void deleteTrip(Trip trip);
    public void addNewItem(Trip trip, String itemName, long amount, AddNewItemResponseListener addNewItemResponseListener);
    public void assignItemToTrip(Trip trip, Item item, long amount, AddNewItemResponseListener addNewItemResponseListener);
    public void deleteItemFromTrip(Trip trip, Item item);
    public void getAllItems(final AllItemsResponseListener allItemsResponseListener);
    public void getRecommendationList(Trip trip, RecommendationListResponseListener recommendationListResponseListener);
    public void notifyChangeAmount(Trip trip, Item item);
    public List<Item> getSuggestionForSearch(String query);
    public void setContext(Context context);
}
