package com.menisamet.totake;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.menisamet.totake.Helper.CardsRecyclerView;
import com.menisamet.totake.Helper.Utility;
import com.menisamet.totake.Models.ItemData;
import com.menisamet.totake.Services.Database;
import com.menisamet.totake.Services.SuggestionSystemCall;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListOfItemActivity extends AppCompatActivity {

    public static final String EXTRA_LIST_DATA_ITEM_POSITION = "extra_list_data_item_position";


    SuggestionSystemCall suggestionSystemCall;
    //String [] item={"sacks", "shirts", "pants"};
    List<ItemData> itemsToTake = null;
    List<ItemData> recommendations = null;
    SwipeMenuListView v;
    ArrayAdapter<ItemData> itemToAddAdapter;
    List<ItemData> itemsToAdd = null;
    //    ListView v_sugg;
    CardsRecyclerView mCardsRecyclerView;
    CardsRecyclerView.RecycleViewCardAdapter<ItemData> mSuggestionAdapter;

    ArrayAdapter<ItemData> suggestionAdapter;
    int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_item);
        itemsToTake = new ArrayList<>();
        itemsToAdd = new ArrayList<>();
        itemsToAdd.add(new ItemData("apple ", 1));
//        itemsToTake.add(new ItemData("test 1", 2));
//        itemsToTake.add(new ItemData("test 2", 4));
        itemsToAdd.add(new ItemData("honey", 1));
        itemsToAdd.add(new ItemData("ירוק וחום לשנה", 4));
        itemsToAdd.add(new ItemData("Add c",1));
        itemsToAdd.add(new ItemData("אנציקלופדית אבן שושן", 7));


        Bundle extras = getIntent().getExtras();
        listPosition = extras.getInt(MyToTakeListActivity.EXTRA_LIST_DATA_ITEM_POSITION);
        itemsToTake = Database.static_userListData.get(listPosition).getItemDataList();


        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
//        menu.setIcon(R.mipmap.ic_launcher);
        menu.setDisplayShowTitleEnabled(true);
        menu.setDisplayUseLogoEnabled(true);
        menu.setTitle(R.string.to_take);

        suggestionSystemCall = new SuggestionSystemCall(listPosition);

        v = (SwipeMenuListView) findViewById(R.id.listView); //find list from activity
//        v_sugg = (ListView) findViewById(R.id.listViewSuggestions); //find list from activity
        itemToAddAdapter = new item_adapter(this, itemsToTake);
//        suggestionAdapter = new suggestions_adapter(this,itemsToAdd);
        v.setAdapter(itemToAddAdapter);
//        v_sugg.setAdapter(suggestionAdapter);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData listData = itemsToTake.get(i);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });

//        v_sugg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ItemData listData = itemsToAdd.get(i);
//                String ob = listData.getName();
//                //get the item in the given place
//                Utility.showToast(getApplicationContext(), ob);
//            }
//        });

        mCardsRecyclerView = (CardsRecyclerView) findViewById(R.id.suggestion_card_recycler_view);
        setSuggestionRecycleAdapter();

        setSwipeList();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e = (EditText) findViewById(R.id.editText);
                String s = e.getText().toString();
                e.setText("");
                itemsToTake.add(new ItemData(s, 1));
                Database.saveCasDataToDB();
                itemToAddAdapter.notifyDataSetChanged();
                suggestionSystemCall.addLike(s);
                requestSuggestionAndPutOnList();
            }
        });

    }


    private void setSuggestionRecycleAdapter(){
        mCardsRecyclerView.setNumLinesAndOrientation(1, CardsRecyclerView.HORIZONTAL);
        mSuggestionAdapter = new CardsRecyclerView.RecycleViewCardAdapter<ItemData>(itemsToAdd);
        mSuggestionAdapter.setImageGlobalRecurse(R.drawable.plus_circle);
        mSuggestionAdapter.setCardGlobalColor(CardsRecyclerView.RANDOM_COLOR);
        mCardsRecyclerView.setAdapter(mSuggestionAdapter);
        mCardsRecyclerView.setCardClickListener(new CardsRecyclerView.CardClickListener() {
            @Override
            public void onCardClick(View view, int position) {
                ItemData listData = itemsToAdd.get(position);
                String ob = listData.getName();
                //get the item in the given place
                Utility.showToast(getApplicationContext(), ob);
            }
        });
    }
    private void setSwipeList() {
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
                // set itemToAddAdapter icon
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
                itemToAddAdapter.notifyDataSetChanged();
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
            itemNumber.setText(Integer.toString(itemData.getNumbers()));
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
                    suggestionSystemCall.addLike(itemName.getText().toString());
                    requestSuggestionAndPutOnList();
                    itemsToTake.add(new ItemData(itemName.getText().toString(), temp));
                    Database.saveCasDataToDB();
                    itemToAddAdapter.notifyDataSetChanged();
                    itemsToAdd.remove(position);
                    suggestionAdapter.notifyDataSetChanged();
                }
            });


            deletButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemsToAdd.remove(position);
                    suggestionAdapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

    private void requestSuggestionAndPutOnList() {
        suggestionSystemCall.setSuggestionListener(new SuggestionSystemCall.SuggestionListener() {
            @Override
            public void onReceiveSuggestionListener(List<ItemData> itemDatas) {
                itemsToAdd.addAll(itemDatas);
                suggestionAdapter.notifyDataSetChanged();
            }
        });
        suggestionSystemCall.getSuggestions(listPosition);

        Set<ItemData> hs = new HashSet<>();
        hs.addAll(itemsToAdd);
        itemsToAdd.clear();
        itemsToAdd.addAll(hs);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}