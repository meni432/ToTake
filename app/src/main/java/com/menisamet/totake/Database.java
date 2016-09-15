package com.menisamet.totake;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meni on 04/09/16.
 */
public class Database {

    public static final String TAG = "TAG_"+Database.class.getCanonicalName();
//    public static boolean static_isLogIn = false;
    public static FirebaseUser static_FirebaseUser = null;
    public static List<ListDataItem> static_userListData = null;

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

    //Load Database Data
    public static void addTestData()  {

    }

    /**
     *
     * @return
     */
    public static boolean isLogIn(){
//        static_FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return (static_FirebaseUser != null);
    }

    public static void addNewList(ListDataItem listData){
        static_userListData.add(listData);
    }

}
