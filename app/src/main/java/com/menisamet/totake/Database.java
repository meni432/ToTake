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

    public static final String TAG = "TAG_"+Database.class.getCanonicalName();
//    public static boolean static_isLogIn = false;
    public static FirebaseUser static_FirebaseUser = null;
    //public static List<ListDataItem> static_userListData = new LinkedList<ListDataItem>();
    public static List<ListDataItem> static_userListData = new ArrayList<>();

    private static Database database;

    static {

    }

    private void Database()
    {
        destGetFromDB();


    }

    public void destGetFromDB()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ItemToTake");
        myRef.child(static_FirebaseUser.getUid()).addValueEventListener(valueEventListener);
        myRef.addValueEventListener(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(TAG, "&onDataChange*");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "&onCancelled*");
        }
    };

    public static Database instance()
    {
        if(database == null)
            database= new Database();


        return database;

    }

    public void saveToDB(Object objectToSave, String path)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.child(static_FirebaseUser.getUid()).push().setValue(objectToSave);
    }

    public void saveToCash(ListDataItem listDataItem){
        static_userListData.add(listDataItem);
    }


    public boolean isLogIn(){
        return (static_FirebaseUser != null);
    }
}
