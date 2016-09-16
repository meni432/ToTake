package com.menisamet.totake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyToTakeListActivity extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener {

    private static final String EXTRA_ITEM_DATA_LIST = "extra_time_data_list";
    private static final String EXTRA_LIST_DATA_ITEM_POSITION = "extra_list_data_item_position";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String TAG = "TAG_" + MyToTakeListActivity.class.getCanonicalName();

    private GoogleApiClient mGoogleApiClient;

    private PlaceImageLoader mPlaceImageLoader;
    protected Context context;

    protected void onStart()
    {
        super.onStart();
        Log.d(TAG,"****");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ItemToTake");
        myRef.child(Database.static_FirebaseUser.getUid()).addValueEventListener(valueEventListener);
        //myRef.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener()
    {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ListDataItem post = dataSnapshot.getValue(ListDataItem.class);
           // Log.d(TAG, post.getListName());
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "&onCancelled*");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_do_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(MyToTakeListActivity.this, Database.static_userListData);
        mRecyclerView.setAdapter(mAdapter);


        startGoogleApiClient();
        mPlaceImageLoader = new PlaceImageLoader(mGoogleApiClient);


        context = this;
//        Database.addTestData();

    }

    private synchronized void startGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    public void newListOnClick(View view){
        Utility.checkAuthAndGoToActivity(this, AddNewListActivity.class);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            protected CardView cardView;
            protected TextView textView;
            protected TextView dateTextView;
            protected ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                this.cardView = (CardView) view.findViewById(R.id.card_view);
                this.textView = (TextView) view.findViewById(R.id.info_text);
                this.imageView = (ImageView) view.findViewById(R.id.place_image_view);
                this.dateTextView = (TextView)view.findViewById(R.id.from_to_date_text_view);
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

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.setText(listDatas.get(position).getListName());
            String fromToRepresentiveText = listDatas.get(position).getRepresentativeFromToDate();
            holder.dateTextView.setText(fromToRepresentiveText);
//            mPlaceImageLoader.placePhotosTask(listDatas.get(position).getGooglePlaceId(), holder.imageView);

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
//

//
