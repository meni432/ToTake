package com.menisamet.totake;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    List<ItemData> itemsToAdd = null;
    ListView v_sugg;
    ArrayAdapter<ItemData> a_sugg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);
        itemsToTake = new ArrayList<>();
        itemsToAdd=new ArrayList<>();
        itemsToAdd.add(new ItemData("Add a",1));
//        itemsToTake.add(new ItemData("test 1", 2));
//        itemsToTake.add(new ItemData("test 2", 4));
        itemsToAdd.add(new ItemData("Add b",1));
//        itemsToTake.add(new ItemData("test 3", 13));
//        itemsToTake.add(new ItemData("test 4", 4));
//        itemsToAdd.add(new ItemData("Add c",1));
//        itemsToTake.add(new ItemData("test 5", 7));
//        itemsToTake.add(new ItemData("test 6", 1));
        Bundle extras = getIntent().getExtras();
        int listPosition = extras.getInt(EXTRA_LIST_DATA_ITEM_POSITION);
        itemsToTake = Database.static_userListData.get(listPosition).getItemDataList();

        v = (SwipeMenuListView) findViewById(R.id.listView); //find list from activity
        v_sugg = (ListView) findViewById(R.id.listViewSuggestions); //find list from activity
        a = new item_adapter(this, itemsToTake);
        a_sugg= new suggestions_adapter(this,itemsToAdd);
        v.setAdapter(a);
        v_sugg.setAdapter(a_sugg);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData listData = itemsToTake.get(i);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });

        v_sugg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData listData = itemsToAdd.get(i);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });
        setSwipeList();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "New item in your List!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                EditText e=(EditText)findViewById(R.id.editText);
                                String s=e.getText().toString();
                                itemsToTake.add(new ItemData(s,1));
                            }
                        }).show();
            }
        });
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
        v.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                itemsToTake.remove(position);
                a.notifyDataSetChanged();
                return false;
            }
        });
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


    class suggestions_adapter extends ArrayAdapter<ItemData> {

        public suggestions_adapter(Context context, List<ItemData> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ItemData itemData = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.suggestions_layout, parent, false);
            }

            final TextView itemName = (TextView) convertView.findViewById(R.id.item_test);
            final TextView itemNumber = (TextView) convertView.findViewById(R.id.num_text);
            ImageButton addButton = (ImageButton) convertView.findViewById(R.id.imageButtonPlus);
            ImageButton deletButton = (ImageButton) convertView.findViewById(R.id.imageButtondelet);
            itemName.setText(itemData.getName());
            itemNumber.setText(itemData.getNumbers() + "");
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = (Integer.parseInt(itemNumber.getText().toString()));
                    itemsToTake.add(new ItemData(itemName.getText().toString(),temp));
                    a.notifyDataSetChanged();
                    itemsToAdd.remove(position);
                    a_sugg.notifyDataSetChanged();}
            });


            deletButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemsToAdd.remove(position);
                    a_sugg.notifyDataSetChanged();
                   }
            });

            return convertView;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}