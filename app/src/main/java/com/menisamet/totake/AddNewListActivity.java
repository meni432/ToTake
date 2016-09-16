package com.menisamet.totake;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeUnit;
import android.os.Build;
import java.text.ParseException;
import java.util.Date;
import java.lang.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.ParseException;
import java.util.Date;

import static android.icu.util.Calendar.getInstance;

public class AddNewListActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = "TAG_" + AddNewListActivity.class.getCanonicalName();
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private GoogleApiClient mGoogleApiClient;
    private Date startDate;
    private Date endDate;
    private Place selectedPlace = null;
    private ImageView mImageView;
    private PlaceImageLoader placeImageLoader;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_list);


        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.drawable.to_take_logo);
        menu.setDisplayShowTitleEnabled(false);
        menu.setDisplayUseLogoEnabled(true);


        mImageView = (ImageView) findViewById(R.id.imageView);

        startGoogleApiClient();
        setAutoCompleate();

        placeImageLoader = new PlaceImageLoader(mGoogleApiClient);



    }

    private void setAutoCompleate() {
        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_NONE)
                .build();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager()
                        .findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("Your Destination");
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(13.0f);
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setPadding(0, 0, 0, 0);

        editText = (EditText)autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input);

        autocompleteFragment.setFilter(autocompleteFilter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                // Get itemToAddAdapter PlacePhotoMetadataResult containing metadata for the first 10 photos.
                placeImageLoader.placePhotosTask(place.getId(), mImageView);
                Log.d(TAG, "place id: " + place.getId());
                selectedPlace = place;
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    private synchronized void startGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }


    public void showDatePickerDialog(final View v) {

        DialogFragment newFragment = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = getInstance();
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                if (v.getId() == R.id.start_date_button) {
                    startDate = calendar.getTime();
                    Log.d(TAG, "start date set: " + startDate);
                    ((TextView) v).setText(sdf.format(startDate));
                } else if (v.getId() == R.id.end_date_button) {
                    endDate = calendar.getTime();
                    Log.d(TAG, "end date set: " + endDate);
                    ((TextView) v).setText(sdf.format(endDate));
                }
            }
        });
        newFragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void addButtonClicked(View view) {
//        ListDataItem itemToSave = new ListDataItem(selectedPlace.getId(), startDate, endDate, (String)selectedPlace.getName());
//        Database.instance().saveToDB(itemToSave, "ItemToTake");
        ListDataItem listDataItem=new ListDataItem(editText.getText().toString(), selectedPlace.getId(), startDate, endDate);
        int numOfDays=(int)((endDate.getTime()-startDate.getTime())/360);

        listDataItem.addItemDataList(new ItemData("shirts ",numOfDays));
        Database.instance().saveToCash(listDataItem);
        Intent intent = new Intent(AddNewListActivity.this, ListOfItemActivity.class);
        intent.putExtra(ListOfItemActivity.EXTRA_LIST_DATA_ITEM_POSITION, Database.static_userListData.size()-1);
        startActivity(intent);
        finish();
        Log.d(TAG, "send to db");
    }
}
