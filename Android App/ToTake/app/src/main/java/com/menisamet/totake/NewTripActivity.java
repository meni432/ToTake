package com.menisamet.totake;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.menisamet.totake.Logic.GuiInterface;
import com.menisamet.totake.Logic.GuiService;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Server.Listeners.AddNewTripResponseListener;
import com.menisamet.totake.Services.PlaceImageLoader;

import java.util.Date;

public class NewTripActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = NewTripActivity.class.getCanonicalName();
    private GoogleApiClient mGoogleApiClient;
    private PlaceImageLoader mPlaceImageLoader;
    private Date mFromDate;
    private Date mToDate;
    private Button mToDataButton;
    private Button mFromDateButton;
    private Place mPlace;
    private Button mAddButton;
    private EditText mDestinationEditText;
    private ImageView mImageView;
    private Context mContext;
    private Intent mIntent;

    GuiInterface guiInterface = GuiService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        mContext = getApplicationContext();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mPlaceImageLoader = new PlaceImageLoader(mGoogleApiClient);


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("Destination");

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                mPlace = place;
                mPlaceImageLoader.placePhotosAsync(place.getId(), mImageView);
                updateView();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        mToDataButton = (Button) findViewById(R.id.to_data_button);
        mFromDateButton = (Button) findViewById(R.id.from_data_button);
        mAddButton = (Button) findViewById(R.id.add_button);
        mDestinationEditText = ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input));
        mImageView = (ImageView) findViewById(R.id.imageView);

        updateView();

    }

    void updateView() {
        if (mPlace != null && mFromDate != null && mToDate != null && mDestinationEditText.getText() != null) {
            Log.d(TAG, mDestinationEditText.getText().toString());
            mAddButton.setEnabled(true);
        } else {
            Log.d(TAG, mDestinationEditText.getText().toString());
            mAddButton.setEnabled(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void showFromDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDataChoseListener(new OnDataChoseListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                mFromDate = new Date(year, month, day);
                mFromDateButton.setText(day + "/" + month + "/" + year);
                updateView();
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showToDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDataChoseListener(new OnDataChoseListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                mToDate = new Date(year, month, day);
                mToDataButton.setText(day + "/" + month + "/" + year);
                updateView();
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void addTrip(View v) {
        guiInterface.addNewTrip(mPlace.getName().toString(), mFromDate, mToDate, mPlace.getId(), new AddNewTripResponseListener() {
            @Override
            public void onResponse(Trip trip) {
                try {
                    Intent intent = new Intent(mContext, TripActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(TripActivity.ARGS_TRIP_ID, trip.getTripID());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!Mode.TEST_MODE) {
                        throw e;
                    }
                }

            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        OnDataChoseListener onDataChoseListener;

        public void setOnDataChoseListener(OnDataChoseListener onDataChoseListener) {
            this.onDataChoseListener = onDataChoseListener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            if (onDataChoseListener != null) {
                onDataChoseListener.onDateSet(view, year, month, day);
            }
        }
    }


    interface OnDataChoseListener {
        public void onDateSet(DatePicker view, int year, int month, int day);
    }
}
