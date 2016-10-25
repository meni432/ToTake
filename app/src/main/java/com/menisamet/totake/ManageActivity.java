package com.menisamet.totake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.menisamet.totake.Services.Database;

public class ManageActivity extends AppCompatActivity {

    public static final String TAG = "TAG_"+ManageActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        if (Database.isLogIn()){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Log.d(TAG, "user log in : "+user.getDisplayName());
        }else{
            Log.d(TAG, "user not log in");
        }
    }
}
