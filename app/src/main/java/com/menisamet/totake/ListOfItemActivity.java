package com.menisamet.totake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListOfItemActivity extends AppCompatActivity {

    String [] item={"sacks", "shirts", "pants"};
    ListView v;
    ArrayAdapter<String> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);
        v=(ListView) findViewById(R.id.listView);
        a=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, item);
        v.setAdapter(a);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ob=item[i]; //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });
    }


}