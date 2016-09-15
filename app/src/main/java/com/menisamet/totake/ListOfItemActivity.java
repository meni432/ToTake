package com.menisamet.totake;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListOfItemActivity extends AppCompatActivity {

    //String [] item={"sacks", "shirts", "pants"};
    List<ItemData> itemsToTake = null;
    List<ItemData> recommendations = null;
    ListView v;
    ArrayAdapter<ItemData> a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);
        itemsToTake = new ArrayList<>();
        itemsToTake.add(new ItemData("test 1", 3));
        itemsToTake.add(new ItemData("test 2", 4));
        v=(ListView) findViewById(R.id.listView); //find list from activity
        a=new item_adapter(this, itemsToTake);
        v.setAdapter(a);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData listData=itemsToTake.get(i);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });
    }

    class item_adapter extends ArrayAdapter<ItemData>{

        public item_adapter(Context context, List<ItemData> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemData itemData=getItem(position);
            if(convertView==null){
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_data_layout,parent,false);
            }

            TextView itemName= (TextView)convertView.findViewById(R.id.item_text);
            TextView itemNumber= (TextView)convertView.findViewById(R.id.number_text);

            itemName.setText(itemData.getName());
            itemNumber.setText(itemData.getNumbers() + "");
            return convertView;
        }
    }
}