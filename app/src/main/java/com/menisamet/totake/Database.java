package com.menisamet.totake;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 04/09/16.
 */
public class Database {
//

    public static final String TAG = "TAG_" + Database.class.getCanonicalName();
    //    public static boolean static_isLogIn = false;
    public static FirebaseUser static_FirebaseUser = null;
    //public static List<ListDataItem> static_userListData = new LinkedList<ListDataItem>();
    public static List<ListDataItem> static_userListData = new ArrayList<>();

    public static boolean needUpdate = true;

    private static Database database;

    public static OnLoadDataListener onLoadDataListener;


    static {

    }

    public static Database instance() {
        if (database == null)
            database = new Database();

        return database;

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
                if (needUpdate) {
                    List<ListDataItem> listDataItems = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                        ListDataItem item = postSnapshot.getValue(ListDataItem.class);
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
                    static_userListData = listDataItems;
                    if (onLoadDataListener != null) {
                        onLoadDataListener.dataLoaded();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public static void setNeedUpdate() {
        needUpdate = true;
    }

    public void saveToCash(ListDataItem listDataItem) {
        static_userListData.add(listDataItem);
        saveCasDataToDB();
    }


    public boolean isLogIn() {
        return (static_FirebaseUser != null);
    }


    public static void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        Database.onLoadDataListener = onLoadDataListener;
    }

    interface OnLoadDataListener {
        public void dataLoaded();
    }
}
