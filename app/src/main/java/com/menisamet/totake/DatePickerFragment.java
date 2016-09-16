package com.menisamet.totake;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by meni on 11/09/16.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String TAG = "TAG_" + DatePickerFragment.class.getCanonicalName();

    DatePickerDialog.OnDateSetListener onDateSetListener;

    public DatePickerFragment(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        final Calendar c = Calendar.getInstance();
        int year = 0, day = 0 , month = 0;
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            // Create itemToAddAdapter new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
        }
        return new DatePickerDialog(getActivity(), onDateSetListener, 1,1,1);
    }

}
