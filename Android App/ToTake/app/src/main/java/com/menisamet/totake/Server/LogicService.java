package com.menisamet.totake.Server;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;
import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.R;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;
import com.menisamet.totake.Server.Services.GsonRequest;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public class LogicService implements LogicInterface {
    public static final String TAG = LogicService.class.getCanonicalName();
    private static final LogicService ourInstance = new LogicService();
    private static String mServerUrl = "http://totake.website:8080";

    public static LogicService getInstance() {
        return ourInstance;
    }

    private LogicService() {
        Log.d(TAG, "server base url: " + mServerUrl);
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do whatever you want to do with error.getMessage();
            }
        };
    }


    @Override
    public void getAllItems(final AllItemsResponseListener allItemsResponseListener, Context context) {

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.start();
        GsonRequest<Item[]> gsonRequest = new GsonRequest<>(mServerUrl + "/getAllItems", Item[].class, null, new Response.Listener<Item[]>() {
            @Override
            public void onResponse(Item[] response) {
                allItemsResponseListener.onResponse(Arrays.asList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
                allItemsResponseListener.onResponse(null);
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public List<Trip> getUserTripList(int userId) {
        return Trip.createTripList(10);
    }

    @Override
    public String getUserName(int userId) {
        return null;
    }

    @Override
    public User getUser(int userId) {
        return new User("totake user",1);
    }

    @Override
    public void addNewItem(String itemName, long amount, int tripId, AddNewItemResponseListener addNewItemResponseListener) {
        Item item = new Item("Item sample", 10, 11);
        addNewItemResponseListener.onResponse(item);
    }

    @Override
    public void notifyChangeAmount(int tripId, int itemId, Long newAmount) {

    }

    @Override
    public void deleteTrip(int userId, int tripID) {

    }

    @Override
    public void deleteItemFromTrip(int userId, int tripId, int itemId) {

    }

    @Override
    public void addNewTrip(String destinationName, Date Start, Date end, int userId, AddNewTripResponseListener addNewTripResponseListener) {

    }

    @Override
    public boolean ifUserExist(int userId) {
        return false;
    }

    @Override
    public User addNewUser(int userId, String userName, String userMail) {
        return new User("created user", 1, Trip.createTripList(10));
    }

    @Override
    public void getRecommendationList(int tripId, RecommendationListResponseListener recommendationListResponseListener) {
        recommendationListResponseListener.onResponse(Item.createItemList(10));
    }
}