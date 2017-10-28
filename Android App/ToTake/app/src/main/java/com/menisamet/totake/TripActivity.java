package com.menisamet.totake;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.menisamet.totake.Logic.LogicInterface;
import com.menisamet.totake.Logic.LogicService;
import com.menisamet.totake.Modals.Trip;

public class TripActivity extends AppCompatActivity implements ItemListFragment.OnFragmentInteractionListener, ExploreFragment.OnFragmentInteractionListener {
    public static final String TAG = TripActivity.class.getCanonicalName();
    public static final String ARGS_TRIP_ID = "trip_id";
    private int mCurrentTripId;
    ExploreFragment mExploreFragment;
    ItemListFragment mItemListFragment;

    LogicInterface logicInterface = LogicService.getInstance();

    ToggleButton mTbItemListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        mCurrentTripId = getIntent().getIntExtra(ARGS_TRIP_ID, -1);
        mExploreFragment.msCurrentTripId = mCurrentTripId;

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        Trip trip = logicInterface.getTripById(mCurrentTripId);
        if (trip != null) {
            toolbar.setTitle(trip.getDestinationName());
        }
        setSupportActionBar(toolbar);
        mTbItemListButton = (ToggleButton) findViewById(R.id.tb_item_list_button);
        mTbItemListButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ItemListFragment.msCurrentTripId = mCurrentTripId;
                    showFragment(mItemListFragment);
                    mTbItemListButton.setText(R.string.view_build_list);
                } else {
                    ExploreFragment.msCurrentTripId = mCurrentTripId;
                    showFragment(mExploreFragment);
                    mTbItemListButton.setText(R.string.view_item_list);
                }
            }
        });

        // Initialize fragments
        mExploreFragment = new ExploreFragment();
        mItemListFragment = new ItemListFragment();


        // display default fragment
        showFragment(mExploreFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trip_action_menu, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This function replace the fragment that show on main activity
     *
     * @param fragment
     */
    private void showFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}