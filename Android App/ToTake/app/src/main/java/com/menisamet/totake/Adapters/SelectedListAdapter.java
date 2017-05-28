package com.menisamet.totake.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.R;

import java.util.List;

/**
 * Created by meni on 17/04/17.
 */

public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private int mTripId;

    public SelectedListAdapter(Context context, List<Item> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View selectedView = inflater.inflate(R.layout.list_selected_item_layout, parent, false);

        SelectedListAdapter.ViewHolder viewHolder = new SelectedListAdapter.ViewHolder(selectedView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Item item = mItems.get(position);

        LinearLayout selectedItemLinearLayout = viewHolder.selectedItemLinearLayout;
        TextView itemNameTextView = viewHolder.itemNameTextView;
        TextView itemAmountTextView = viewHolder.itemAmountTextView;
        final ImageButton doneImageButton = viewHolder.doneImageButton;


        itemNameTextView.setText(item.getItemName());
        itemAmountTextView.setText(String.valueOf(item.getItemAmount()));
        if (position % 2 == 0) {
            selectedItemLinearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorCardFooter));
        } else {
            selectedItemLinearLayout.setBackgroundColor(Color.TRANSPARENT);
        }

        if (item.isDone()){
            doneImageButton.setColorFilter(ContextCompat.getColor(mContext, R.color.item_done));
        } else {
            doneImageButton.setColorFilter(ContextCompat.getColor(mContext, R.color.divider));
        }


        doneImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDone = item.isDone();
                item.setIsDone(!isDone);
                if (!isDone) {
                    doneImageButton.setColorFilter(ContextCompat.getColor(mContext, R.color.item_done));
                } else {
                    doneImageButton.setColorFilter(ContextCompat.getColor(mContext, R.color.divider));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout selectedItemLinearLayout;
        public TextView itemNameTextView;
        public TextView itemAmountTextView;
        public ImageButton doneImageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            itemNameTextView = (TextView) itemView.findViewById(R.id.item_name_textView);
            itemAmountTextView = (TextView) itemView.findViewById(R.id.item_amount_textView);
            doneImageButton = (ImageButton) itemView.findViewById(R.id.item_done_image_button);
            selectedItemLinearLayout = (LinearLayout) itemView.findViewById(R.id.list_selected_item);
        }
    }

}
