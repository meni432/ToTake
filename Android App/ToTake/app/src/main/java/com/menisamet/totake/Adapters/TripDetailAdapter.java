package com.menisamet.totake.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.R;
import com.menisamet.totake.TripActivity;

import java.util.List;

/**
 * Created by meni on 16/04/17.
 */

public class TripDetailAdapter extends RecyclerView.Adapter<TripDetailAdapter.ViewHolder>{

    private List<Trip> mTrips;
    private Context mContext;

    public TripDetailAdapter(List<Trip> trips, Context context) {
        this.mTrips = trips;
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    // involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tripView = inflater.inflate(R.layout.trip_detail_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(tripView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Trip trip = mTrips.get(position);

        // Set item views based on your views and data model
        TextView destinationTextView = viewHolder.destinationTextView;
        ImageView placeImageView = viewHolder.placeImageView;
        TextView datesTextView = viewHolder.datesTextView;
        CardView tripCardView = viewHolder.tripCardView;

        destinationTextView.setText(trip.getDestinationName());
        datesTextView.setText(trip.sFromToDates());
        tripCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TripActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(TripActivity.ARGS_TRIP_ID, trip.getTripID());
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView placeImageView;
        public TextView destinationTextView;
        public TextView datesTextView;
        public CardView tripCardView;


        public ViewHolder(View itemView) {
            super(itemView);

            placeImageView = (ImageView) itemView.findViewById(R.id.place_image_view);
            destinationTextView = (TextView) itemView.findViewById(R.id.destination_text_view);
            datesTextView = (TextView) itemView.findViewById(R.id.dates_text_view);
            tripCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}