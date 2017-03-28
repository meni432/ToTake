package com.menisamet.totake.Services;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.menisamet.totake.Models.ItemData;
import com.menisamet.totake.Models.ListDataItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Database management class
 */
public abstract class Database {

    public static final String TAG = "TAG_" + Database.class.getCanonicalName();

    public static FirebaseUser static_FirebaseUser = null;
    public static List<ListDataItem> static_userListData = new ArrayList<>();

    public static OnLoadDataListener onLoadDataListener;


    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static void saveCasDataToDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ItemData");
        myRef.child(static_FirebaseUser.getUid()).setValue(static_userListData);
    }

    public static void loadDBToCash() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("ItemData");
        myRef.child(static_FirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ListDataItem> listDataItems = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Date startDate = postSnapshot.child("startDate").getValue(Date.class);
                    Date endDate = postSnapshot.child("endDate").getValue(Date.class);
                    String listName = postSnapshot.child("listName").getValue(String.class);
                    String googlePlaceId = postSnapshot.child("googlePlaceId").getValue(String.class);
                    String imagePath = postSnapshot.child("imagePath").getValue(String.class);
                    String imageName = postSnapshot.child("imageName").getValue(String.class);
                    List<ItemData> itemDataLists = new ArrayList<>();
                    for (DataSnapshot listSnapshot : postSnapshot.child("itemDataList").getChildren()) {
                        itemDataLists.add(listSnapshot.getValue(ItemData.class));
                    }
                    ListDataItem dataItem = new ListDataItem(listName, googlePlaceId, startDate, endDate, imagePath, imageName);
                    dataItem.setItemDataList(itemDataLists);
                    listDataItems.add(dataItem);
                }
                Log.d(TAG, "data load from db");
                static_userListData = listDataItems;
                if (onLoadDataListener != null) {
                    onLoadDataListener.dataLoaded();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public static void saveToCash(ListDataItem listDataItem) {
        static_userListData.add(listDataItem);
        saveCasDataToDB();
    }


    public static boolean isLogIn() {
        return (static_FirebaseUser != null);
    }


    public static void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        Database.onLoadDataListener = onLoadDataListener;
    }

    public interface OnLoadDataListener {
        public void dataLoaded();
    }
}
