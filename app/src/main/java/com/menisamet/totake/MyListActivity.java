package com.menisamet.totake;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.menisamet.totake.Models.ListDataItem;

public class MyListActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<ListDataItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        setupWindowAnimations();
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        spinner = (Spinner) findViewById(R.id.spinner);


        setSelectionSpinnerListener();

    }


    private void setSelectionSpinnerListener() {
        // Create an ArrayAdapter using the string array and itemToAddAdapter default spinner layout
      /*  adapter = new ArrayAdapter<ListDataItem>(this,
                R.layout.spinner_item, Database.static_userListData.values());*/
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spinner.setSelection(position);
//                Utility.showToast(view.getContext(), "click "+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void settingOnClick(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_button_on_click));
    }

    public void newListOnClick(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_button_on_click));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }
}

