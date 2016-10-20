package com.menisamet.totake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.menisamet.totake.Models.ListDataItem;
import com.menisamet.totake.Services.PlaceImageLoader;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class MyToTakeListActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String EXTRA_ITEM_DATA_LIST = "extra_time_data_list";
    public static final String EXTRA_LIST_DATA_ITEM_POSITION = "extra_list_data_item_position";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListView mListView;
    public static final String TAG = "TAG_" + MyToTakeListActivity.class.getCanonicalName();

    private GoogleApiClient mGoogleApiClient;

    private PlaceImageLoader mPlaceImageLoader;
    protected Context context;

    ExecutorService executor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_do_list);


        mListView = (ListView)findViewById(R.id.trip_list_view);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.ic_launcher);
        menu.setDisplayShowTitleEnabled(true);
        menu.setDisplayUseLogoEnabled(true);
        menu.setTitle(R.string.to_take);
        menu.setSubtitle(getString(R.string.my_trip_list));
//        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use itemToAddAdapter linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)


//        mAdapter = new MyAdapter(MyToTakeListActivity.this, Database.static_userListData);
//        mRecyclerView.setAdapter(mAdapter);

        Database.setOnLoadDataListener(new Database.OnLoadDataListener() {
            @Override
            public void dataLoaded() {
                Log.d(TAG, "data load from db");
                Log.d(TAG, Database.static_userListData.toString());
//                mAdapter.notifyDataSetChanged();
//                mAdapter = new MyAdapter(MyToTakeListActivity.this, Database.static_userListData);
//                mRecyclerView.setAdapter(mAdapter);

                loadListDataToView();


            }
        });
        Database.loadDBToCash();


        startGoogleApiClient();
        mPlaceImageLoader = new PlaceImageLoader(mGoogleApiClient);


        context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.checkAuthAndGoToActivity(context, AddNewListActivity.class);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trip_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.miSearch:
                Utility.showToast(this, "search clicked!");
                return true;
            case R.id.miDrawable:
                Utility.showToast(this, "drawable clicked!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void loadListDataToView(){
        TripListArrayAdapter tripListArrayAdapter = new TripListArrayAdapter(this, R.layout.todo_list_item, Database.static_userListData);
        mListView.setAdapter(tripListArrayAdapter);
    }

    private synchronized void startGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


    }

    public void newListOnClick(View view) {
        Utility.checkAuthAndGoToActivity(this, AddNewListActivity.class);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Database.setOnLoadDataListener(new Database.OnLoadDataListener() {
//            @Override
//            public void dataLoaded() {
//                mAdapter.notifyDataSetChanged();
//            }
//        });

    }

    class TripListArrayAdapter extends ArrayAdapter<ListDataItem> {

        public TripListArrayAdapter(Context context, int resource, List<ListDataItem> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                view = vi.inflate(R.layout.todo_list_item, null);
            }

            ListDataItem item = getItem(position);

            if (item != null) {
                TextView textView = (TextView) view.findViewById(R.id.info_text);
                TextView dateTextView = (TextView) view.findViewById(R.id.from_to_date_text_view);
                ImageView imageView = (ImageView) view.findViewById(R.id.place_image_view);

                if (textView != null) {
                    textView.setText(item.getListName());
                }

                if (dateTextView != null) {
                    dateTextView.setText(item.getRepresentativeFromToDate());
                }

                if (imageView != null) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    // load place image
                    if (imageView != null) {
                        mPlaceImageLoader.placePhotosAsync(item.getGooglePlaceId(), imageView);
                    }
                }


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                    Utility.checkAuthAndGoToActivity(context, ListDataItem.class);
                        Intent intent = new Intent(MyToTakeListActivity.this, ListOfItemActivity.class);
                        intent.putExtra(EXTRA_LIST_DATA_ITEM_POSITION, position);
                        startActivity(intent);
                    }
                });
            }

            return view;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        // Provide itemToAddAdapter reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for itemToAddAdapter data item in itemToAddAdapter view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just itemToAddAdapter string in this case
            protected CardView cardView;
            protected TextView textView;
            protected TextView dateTextView;
            protected ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                this.cardView = (CardView) view.findViewById(R.id.card_view);
                this.textView = (TextView) view.findViewById(R.id.info_text);
                this.imageView = (ImageView) view.findViewById(R.id.place_image_view);
                this.dateTextView = (TextView) view.findViewById(R.id.from_to_date_text_view);
            }
        }


        private Context context;
        private List<ListDataItem> listDatas;

        public MyAdapter(Context context, List<ListDataItem> listDatas) {
            this.context = context;
            this.listDatas = listDatas;
        }


        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, null);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        // Replace the contents of itemToAddAdapter view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.setText(listDatas.get(position).getListName());
            String fromToRepresentiveText = listDatas.get(position).getRepresentativeFromToDate();
            holder.dateTextView.setText(fromToRepresentiveText);

            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            // load place image
            if (holder.imageView != null) {
                mPlaceImageLoader.placePhotosAsync(listDatas.get(position).getGooglePlaceId(), holder.imageView);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Utility.checkAuthAndGoToActivity(context, ListDataItem.class);
                    Intent intent = new Intent(MyToTakeListActivity.this, ListOfItemActivity.class);
                    intent.putExtra(EXTRA_LIST_DATA_ITEM_POSITION, position);
                    startActivity(intent);
                }
            });

        }


        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return (null != listDatas ? listDatas.size() : 0);


        }


    }
}