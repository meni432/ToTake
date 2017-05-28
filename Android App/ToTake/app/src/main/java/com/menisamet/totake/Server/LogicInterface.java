package com.menisamet.totake.Server;

import android.content.Context;
import android.os.IInterface;

import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;

import java.util.Date;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public interface LogicInterface {
    public void getAllItems(AllItemsResponseListener allItemsResponseListener, Context context);//this is the right one
//    public List<Item> getAllItems();
    public List<Trip> getUserTripList(int userId);
    public String getUserName(int userId);
    public User getUser(int userId);
    public void addNewItem(String itemName, long amount, int tripId, AddNewItemResponseListener addNewItemResponseListener);
    public void notifyChangeAmount(int tripId, int itemId, Long newAmount);
    public void deleteTrip(int userId, int tripID); // do not delete the trip from data base only delete it from this user trips
    public void deleteItemFromTrip(int userId, int tripId, int itemId);
    /**
     * need to add this trip to the user and to the DB of all the lists
     */
    public void addNewTrip(String destinationName, Date Start, Date end,int userId, AddNewTripResponseListener addNewTripResponseListener);

    /**
     * @param userId
     * @return true if user exist in the DB
     */
    public boolean ifUserExist(int userId);

    /**
     * get user information
     * @return new user (with empty list of trips)
     */
    public User addNewUser(int userId, String userName, String userMail);
    /**
     * get trip and return recommendation list
     */
    public void getRecommendationList(int tripId, RecommendationListResponseListener recommandationListResponseListener);

}

