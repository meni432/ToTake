package com.menisamet.totake.Server;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Server.Listeners.AllItemsResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;
import com.menisamet.totake.Server.Listeners.UserLoadListener;
import com.menisamet.totake.Server.Services.GsonRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 18/04/17.
 */

public class ServerService implements ServerInterface {
    public static final String TAG = ServerService.class.getCanonicalName();

    private static final ServerService ourInstance = new ServerService();
    private static final int MY_SOCKET_TIMEOUT_MS = 90000;
    private static String mServerUrl = "http://env-1520118.njs.jelastic.vps-host.net";

    public static ServerService getInstance() {
        return ourInstance;
    }

    private RequestQueue mRequestQueue;
    private Context mCurrentContext = null;

    private long mCurrentUserId;

    private ServerService() {
    }


    @Override
    public void getAllItems(final AllItemsResponseListener allItemsResponseListener) {


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
    public void setContext(Context context) {
        if (mCurrentContext != context) {
            mRequestQueue = Volley.newRequestQueue(context);
            mRequestQueue.start();
            mCurrentContext = context;
        }
    }

    @Override
    public void setFireBaseUserId(String fireBaseUserId, String displayName, final UserLoadListener userLoadListener) {
        Log.d(TAG, "Set Firebase user " + fireBaseUserId + "display: " + displayName);
        String url = mServerUrl + "/getFireBaseUser?userId=" + fireBaseUserId + "&displayName=" + displayName;

        try {
            displayName = URLEncoder.encode(displayName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        GsonRequest<User> gsonRequest = new GsonRequest<>(mServerUrl + "/getFireBaseUser?userId=" + fireBaseUserId + "&displayName=" + displayName, User.class, null, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                if (response != null) {
                    Log.d(TAG,"get firebase user");
                    userLoadListener.onUserLoad(response);
                    mCurrentUserId = response.getIdUser();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                userLoadListener.onUserLoad(null);
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void setUserId(final long userId, final UserLoadListener userLoadListener) {
        GsonRequest<User> gsonRequest = new GsonRequest<>(mServerUrl + "/getUser?userId=" + userId, User.class, null, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                if (response != null) {
                    userLoadListener.onUserLoad(response);
                    mCurrentUserId = userId;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                userLoadListener.onUserLoad(null);
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void addNewItem(Trip trip, String itemName, long amount, final AddNewItemResponseListener addNewItemResponseListener) {
        String path = "/addNewItem?userId=" + mCurrentUserId + "&tripId=" + trip.getTripID() + "&itemName=" + itemName + "&amount=" + amount;
        GsonRequest<Item> gsonRequest = new GsonRequest<>(mServerUrl + path, Item.class, null, new Response.Listener<Item>() {
            @Override
            public void onResponse(Item response) {
                addNewItemResponseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                addNewItemResponseListener.onResponse(null);
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void assignItemToTrip(Trip trip, Item item, long amount, final AddNewItemResponseListener addNewItemResponseListener) {
        String path = "/assignItemToUser?userId=" + mCurrentUserId + "&tripId=" + trip.getTripID() + "&itemId=" + item.getItemID() + "&amount=" + amount;
        GsonRequest<Item> gsonRequest = new GsonRequest<>(mServerUrl + path, Item.class, null, new Response.Listener<Item>() {
            @Override
            public void onResponse(Item response) {
                addNewItemResponseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                addNewItemResponseListener.onResponse(null);
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void notifyChangeAmount(Trip trip, Item item) {
        String path = "/notifyChangeAmount?userId=" + mCurrentUserId + "&tripId=" + trip.getTripID() + "&itemId=" + item.getItemID() + "&amount=" + item.getItemAmount();
        GsonRequest<Item> gsonRequest = new GsonRequest<>(mServerUrl + path, Item.class, null, new Response.Listener<Item>() {
            @Override
            public void onResponse(Item response) {
                Log.d(TAG, "update item amount");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "cannot update item ammount");
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void deleteTrip(Trip trip) {
        String path = "/deleteTrip?userId=" + mCurrentUserId + "&tripId=" + trip.getTripID();
        GsonRequest<Trip> gsonRequest = new GsonRequest<>(mServerUrl + path, Trip.class, null, new Response.Listener<Trip>() {
            @Override
            public void onResponse(Trip response) {
                Log.d(TAG, "trip deleted");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "cannotd delete trip");
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void deleteItemFromTrip(Trip trip, Item item) {
        String path = "/deleteItemFromTrip?userId=" + mCurrentUserId + "&tripId=" + trip.getTripID() + "&itemId=" + item.getItemID();
        GsonRequest<Item> gsonRequest = new GsonRequest<>(mServerUrl + path, Item.class, null, new Response.Listener<Item>() {
            @Override
            public void onResponse(Item response) {
                Log.d(TAG, "delete item");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "cannot delete item");
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void addNewTrip(String destinationName, Date startDate, Date endDate, String googlePlaceId, final AddNewTripResponseListener addNewTripResponseListener) {
        String path = "/addNewTrip?userId=" + mCurrentUserId + "&destinationName=" + destinationName + "&startDate=" + startDate.getTime() + "&endDate=" + endDate.getTime() + "&googlePlaceId=" + googlePlaceId;
        Log.d(TAG, path);
        String encodedUrl = (mServerUrl + path).replaceAll(" ", "%20");
        GsonRequest<Trip> gsonRequest = new GsonRequest<>(encodedUrl, Trip.class, null, new Response.Listener<Trip>() {
            @Override
            public void onResponse(Trip response) {
                addNewTripResponseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "add new trim error " + error.networkResponse);
                Log.d(TAG, "volley error " + error);
//                addNewTripResponseListener.onResponse(null);
            }
        });

        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void addNewUser(String userName, String userEmail, final UserLoadListener userLoadListener) {
        String path = "/addUser?userName=" + userName + "&userEmail=" + userEmail;
        GsonRequest<User> gsonRequest = new GsonRequest<>(mServerUrl + path, User.class, null, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                userLoadListener.onUserLoad(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userLoadListener.onUserLoad(null);
            }
        });

        mRequestQueue.add(gsonRequest);
    }


    @Override
    public void getRecommendationList(Trip trip, final RecommendationListResponseListener recommendationListResponseListener) {
        String path = mServerUrl + "/getSuggestionItemForTrip?userId="+mCurrentUserId+"&tripId="+trip.getTripID();
        Log.d(TAG, "request path = " + path);
        GsonRequest<Item[]> gsonRequest = new GsonRequest<>(path, Item[].class, null, new Response.Listener<Item[]>() {
            @Override
            public void onResponse(Item[] response) {
                Log.d(TAG, "item recomended: " + Arrays.toString(response));
                List<Item> responseItemList = null;
                if (response != null && response.length > 0) {
                    responseItemList = Arrays.asList(response);
                }
                recommendationListResponseListener.onResponse(responseItemList, true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
                recommendationListResponseListener.onResponse(null, true);
            }
        });

        mRequestQueue.add(gsonRequest);
    }

    @Override
    public void removeFromRecommendationList(Trip trip, final Item item) {
        Log.d(TAG, "on removeSuggestionItemForTrip");
        String path = mServerUrl + "/removeSuggestionItemForTrip?userId="+mCurrentUserId+"&tripId="+trip.getTripID()+"&itemId="+item.getItemID();
        Log.d(TAG, "request path = " + path);
        GsonRequest<String> gsonRequest = new GsonRequest<>(path, String.class, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "add to black list callback froms server:  "+item.getItemID());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
            }
        });

        mRequestQueue.add(gsonRequest);
    }
}