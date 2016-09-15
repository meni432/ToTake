package com.menisamet.totake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListOfItemActivity extends AppCompatActivity {

    //String [] item={"sacks", "shirts", "pants"};
    List<ItemData> listDatas = null;
    ListView v;
    ArrayAdapter<ItemData> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);
        v=(ListView) findViewById(R.id.listView);
        a=new ArrayAdapter<ItemData>(this,android.R.layout.simple_list_item_1, android.R.id.text1,listDatas);
        v.setAdapter(a);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData listData=listDatas.get(i);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });
    }


}