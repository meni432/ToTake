package com.menisamet.totake;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 04/09/16.
 */
public class Database {
    //

    public static final String TAG = "TAG_"+Database.class.getCanonicalName();
//    public static boolean static_isLogIn = false;
    public static FirebaseUser static_FirebaseUser = null;
    public static List<ListData> static_userListData = null;

    // only for test
    static  {
        static_userListData = new ArrayList<>();
        static_userListData.add(new ListData(1,"first list"));
        static_userListData.add(new ListData(2,"second list"));
        static_userListData.add(new ListData(3,"th list"));
        ListData sliListData = new ListData(4,"this list");
        sliListData.addItemDataList(new ItemData("item test 1", 3, false));
        static_userListData.add(sliListData);
    }
    // end tests

    //
    //Load Database Data
    public static void addTestData()  {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("users").child(static_FirebaseUser.getUid());
//
//
//        myRef.setValue(static_userListData);
    }

    /**
     *
     * @return
     */
    public static boolean isLogIn(){
        static_FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return (static_FirebaseUser != null);
    }

    public static void addNewList(ListData listData){
        static_userListData.add(listData);
    }
}
