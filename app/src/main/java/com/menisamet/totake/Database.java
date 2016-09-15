package com.menisamet.totake;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 04/09/16.
 */
public class Database
{
//
    //
    public static final String TAG = "TAG_"+Database.class.getCanonicalName();
//    public static boolean static_isLogIn = false;
    public static FirebaseUser static_FirebaseUser = null;
    public static List<ListDataItem> static_userListData = null;
    private FirebaseDatabase database;

    // only for test
    static  {
        static_userListData = new ArrayList<>();
        static_userListData.add(new ListDataItem(1,"first list"));
        static_userListData.add(new ListDataItem(2,"second list"));
        static_userListData.add(new ListDataItem(3,"th list"));
        ListDataItem sliListData = new ListDataItem(4,"this list");
        sliListData.addItemDataList(new ItemData("item test 1", 3));
        static_userListData.add(sliListData);
    }
    // end tests

    public void Database()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ItemToTake\\7WPohkoU8PfRrJaj5uwlArduZ9s2\\-KRieE0kTZZl8RTtTgKz");

//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                ListDataItem value = dataSnapshot.getValue(database
//                Log.d(TAG, "Value is: " + value.getListName()+"********************");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error)
//            {
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

    public static void destGetFromDB(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ItemToTake");
        myRef.child(static_FirebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "there is "+dataSnapshot.getChildrenCount()+" trip in db");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ListDataItem listDataItem = snapshot.getValue(ListDataItem.class);
                    Log.d(TAG, "listDataItem: "+listDataItem);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    public static void saveToDB(Object objectToSave, String pathToDB)
    {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("ItemToTake");
//        String key = myRef.child(static_FirebaseUser.getUid()).push().getKey();
//        myRef.child(static_FirebaseUser.getUid()).child(key).setValue(objectToSave);
        static_userListData.add((ListDataItem)objectToSave);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ItemToTake");
        myRef.child(static_FirebaseUser.getUid()).setValue(static_userListData);



    }

    /*public Object RetrievedDataFromDB(String pathToDB)
    {

    }*/

    public static boolean isLogIn(){
//        static_FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return (static_FirebaseUser != null);
    }


}
