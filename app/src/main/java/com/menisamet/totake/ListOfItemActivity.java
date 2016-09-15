package com.menisamet.totake;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class ListOfItemActivity extends AppCompatActivity
{
    //String [] item={"sacks", "shirts", "pants"};
    List<ItemData> itemsToTake = null;
    List<ItemData> recommendations = null;
    SwipeMenuListView v;
    ArrayAdapter<ItemData> a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);
        itemsToTake = new ArrayList<>();
        itemsToTake.add(new ItemData("test 1", 3));
        itemsToTake.add(new ItemData("test 2", 4));
        v = (SwipeMenuListView) findViewById(R.id.listView); //find list from activity
        a = new item_adapter(this, itemsToTake);
        v.setAdapter(a);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData listData = itemsToTake.get(i);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });

        setSwipeList();

    }

    private void setSwipeList(){
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_white_48dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        v.setMenuCreator(creator);
        v.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
    }


    class item_adapter extends ArrayAdapter<ItemData> {

        public item_adapter(Context context, List<ItemData> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ItemData itemData = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_data_layout, parent, false);
            }

            TextView itemName = (TextView) convertView.findViewById(R.id.item_text);
            final TextView itemNumber = (TextView) convertView.findViewById(R.id.number_text);
            ImageButton upButton = (ImageButton) convertView.findViewById(R.id.upButton);
            ImageButton downButton = (ImageButton) convertView.findViewById(R.id.downButton);
            upButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = (Integer.parseInt(itemNumber.getText() + ""));
                    temp++;
                    itemData.setNumbers(temp);
                    itemNumber.setText(temp + "");
                }
            });
            downButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = (Integer.parseInt(itemNumber.getText() + ""));
                    if (temp > 0) {
                        temp--;
                        itemData.setNumbers(temp);
                        itemNumber.setText(temp + "");
                    }
                }
            });
            itemName.setText(itemData.getName());
            itemNumber.setText(itemData.getNumbers() + "");
            return convertView;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}