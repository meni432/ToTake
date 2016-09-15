package com.menisamet.totake.Suggestion;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.menisamet.totake.Constants;
import com.menisamet.totake.ItemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 16/09/2016.
 */
public class SuggestionSystemCall {
    public static final String TAG = "TAG_"+SuggestionSystemCall.class.getSimpleName();

    List<ItemData> suggestionData;
    AsyncHttpClient client;
    SuggestionListener suggestionListener;

    public SuggestionSystemCall(int listId, SuggestionListener suggestionListener){
        client = new AsyncHttpClient();
        suggestionData = new ArrayList<>();
        this.suggestionListener = suggestionListener;
    }

    public void getMySuggestion (){
        client.get(Constants.SUGGESTION_SERVER_GET_SUGGESTIONS, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    Log.d(TAG, response.toString());
                    JSONObject firstEvent = (JSONObject) response.get(String.valueOf(0));
                    Log.d(TAG, firstEvent.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void getSuggestions(){

        client.get(Constants.SUGGESTION_SERVER_GET_SUGGESTIONS, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                final List<ItemData> itemDatas = new ArrayList<>();
                try {
                    Log.d(TAG, response.toString());
                    JSONArray jsonArray = response.getJSONArray("");
                    Log.d(TAG, jsonArray.toString());

                    if (suggestionListener != null){
                        for (int i = 0; i < jsonArray.length(); i++){
                            String name = jsonArray.getString(i);
                            itemDatas.add(new ItemData(name, 999));
                        }
                        suggestionListener.onReceiveSuggestionListener(itemDatas);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    interface SuggestionListener {
        public void onReceiveSuggestionListener(List<ItemData> itemDatas);
    }

}
