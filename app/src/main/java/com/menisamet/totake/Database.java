package com.menisamet.totake;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by meni on 04/09/16.
 */
public class Database {
//    public static boolean static_isLogIn = false;
    public static FirebaseUser static_FirebaseUser = null;


    public static boolean isLogIn(){
        static_FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return (static_FirebaseUser != null);
    }

}
