package com.menisamet.totake;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

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
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Calendar calendar = getInstance();
//                calendar.set(year, month, dayOfMonth);
                LocalDate localDate = new LocalDate(year, month, dayOfMonth);
                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
                if (v.getId() == R.id.start_date_button) {
                    startDate = localDate.toDate();
                    Log.d(TAG, "start date set: " + startDate);
                    DateTime dateTime = new DateTime(startDate);
                    String sf = dateTime.toString(dateTimeFormatter);
//                    ((TextView) v).setText(sdf.format(startDate));
                    ((TextView) v).setText(sf);
                } else if (v.getId() == R.id.end_date_button) {
                    endDate = localDate.toDate();
                    Log.d(TAG, "end date set: " + endDate);
                    DateTime dateTime = new DateTime(endDate);
                    String sf = dateTime.toString(dateTimeFormatter);
//                    ((TextView) v).setText(sdf.format(endDate));
                    ((TextView) v).setText(sf);
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
        int numOfDays=(int)((endDate.getTime()-startDate.getTime())/(1000*60*60*24));

        ListDataItem.currentId = Database.static_userListData.size()-1;
        listDataItem.addItemDataList(new ItemData("shirts ",numOfDays));
        listDataItem.addItemDataList(new ItemData("pants ",numOfDays));
        listDataItem.addItemDataList(new ItemData("socks ",numOfDays));
        listDataItem.addItemDataList(new ItemData("underwear ",numOfDays));
        listDataItem.addItemDataList(new ItemData("bathing cap ",1));
        listDataItem.addItemDataList(new ItemData("bathing suit ",2));
        listDataItem.addItemDataList(new ItemData("toothbrush ",1));
        listDataItem.addItemDataList(new ItemData("book ",1));
        listDataItem.addItemDataList(new ItemData("glasses ",1));
        listDataItem.addItemDataList(new ItemData("sunglasses ",1));
        listDataItem.addItemDataList(new ItemData("cell phone charger ",1));

        Database.instance().saveToCash(listDataItem);
        Intent intent = new Intent(AddNewListActivity.this, ListOfItemActivity.class);
        intent.putExtra(ListOfItemActivity.EXTRA_LIST_DATA_ITEM_POSITION, Database.static_userListData.size()-1);
        startActivity(intent);
        finish();
        Log.d(TAG, "send to db");
    }
}
