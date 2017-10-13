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
import com.menisamet.totake.Server.Listeners.UserLoadListener;

import java.util.Date;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public interface ServerInterface {
    public void setFireBaseUserId(String fireBaseUserId, String displayName, UserLoadListener userLoadListener);
    public void setUserId(long userId, UserLoadListener userLoadListener);
    public void getAllItems(AllItemsResponseListener allItemsResponseListener);
    public void setContext(Context context);
    public void addNewItem(Trip trip, String itemName, long amount, AddNewItemResponseListener addNewItemResponseListener);
    public void assignItemToTrip(Trip trip, Item item, long amount, AddNewItemResponseListener addNewItemResponseListener);
    public void notifyChangeAmount(Trip trip, Item item);
    public void deleteTrip(Trip trip);
    public void deleteItemFromTrip(Trip trip, Item item);
    public void addNewTrip(String destinationName, Date startDate, Date endDate, String googlePlaceId, final AddNewTripResponseListener addNewTripResponseListener);
    public void  addNewUser(String userName, String userEmail, final UserLoadListener userLoadListener);
    public void getRecommendationList(Trip trip, final RecommendationListResponseListener recommendationListResponseListener);

}

