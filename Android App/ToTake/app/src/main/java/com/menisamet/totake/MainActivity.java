package com.menisamet.totake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.menisamet.totake.Logic.LogicInterface;
import com.menisamet.totake.Logic.LogicService;
import com.menisamet.totake.Server.ServerService;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, TripFragment.OnFragmentInteractionListener {
    private static String TAG = ServerService.class.getCanonicalName();

    LogicInterface logicInterface = LogicService.getInstance();
    HomeFragment mHomeFragment;
    TripFragment mTripFragment;

    //Navigation Listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(mHomeFragment);
                    return true;
                case R.id.navigation_trips:
                    showFragment(mTripFragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        //ActionBar
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.ic_launcher);
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayUseLogoEnabled(true);
        menu.setTitle(R.string.application_name);
        menu.setSubtitle(getString(R.string.application_slogen));

        // Initialize fragments
        mHomeFragment = new HomeFragment();
        mTripFragment = new TripFragment();

        // display home fragment
        showFragment(mHomeFragment);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        logicInterface.setContext(getApplicationContext());


//        logicInterface.setUserId(1, new UserLoadListener() {
//            @Override
//            public void onUserLoad(User user) {
//                System.out.println(user);
//            }
//        });

//        ServerInterface logicInterface = ServerService.getInstance();
//        logicInterface.getAllItems(new AllItemsResponseListener() {
//            @Override
//            public void onResponse(List<Item> items) {
//                if (items != null) {
//                    Log.d(TAG, "from items array: " + items);
//                }
//                else {
//                    Log.d(TAG, "from items array: null object");
//                }
//            }
//        }, getBaseContext());

//        ServerInterface logicInterface = ServerService.getInstance();
//        logicInterface.setContext(getBaseContext());
//        logicInterface.getAllItems(new AllItemsResponseListener() {
//            @Override
//            public void onResponse(List<Item> items) {
//                if (items != null) {
//                    Log.d(TAG, "from items array: " + items);
//                } else {
//                    Log.d(TAG, "from items array: null object");
//                }
//            }
//        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miSetting:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This function replace the fragment that show on main activity- home fragment
     *
     * @param fragment
     */
    private void showFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void addNewListOnClick(View view) {
        Intent intent = new Intent(this, NewTripActivity.class);
        startActivity(intent);
    }
}
