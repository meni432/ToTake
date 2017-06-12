package com.menisamet.totake.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.menisamet.totake.Logic.GuiInterface;
import com.menisamet.totake.Logic.GuiService;
import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.R;

import java.util.List;

/**
 * Created by meni on 17/04/17.
 */

public class ExploreSelectedListAdapter extends RecyclerView.Adapter<ExploreSelectedListAdapter.ViewHolder> {

    //TODO to be continue https://github.com/chthai64/SwipeRevealLayout
    private List<Item> mItems;
    private Context mContext;


    private OnItemIncButtonClickedListener onItemIncButtonClickedListener;
    private OnItemDecButtonClickedListener onItemDecButtonClickedListener;
    private OnItemDeleteButtonClickedListener onItemDeleteButtonClickedListener;


    public ExploreSelectedListAdapter(Context context, List<Item> items, int tripId) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View exploreView = inflater.inflate(R.layout.explore_selected_item_layout, parent, false);

        ExploreSelectedListAdapter.ViewHolder viewHolder = new ExploreSelectedListAdapter.ViewHolder(exploreView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Item item = mItems.get(position);

        if (item != null) {
            TextView itemNameTextView = viewHolder.itemNameTextView;
            TextView itemAmountTextView = viewHolder.itemAmountTextView;
            ImageButton incButton = viewHolder.incButton;
            ImageButton decButton = viewHolder.decButton;
            LinearLayout exploreSelectedItemLinearLayout = viewHolder.exploreSelectedItemLinearLayout;
            ImageButton deleteButton = viewHolder.deleteButton;


            itemNameTextView.setText(item.getItemName());
            itemAmountTextView.setText(String.valueOf(item.getItemAmount()));
            if (position % 2 == 0) {
                exploreSelectedItemLinearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorCardFooter));
            } else {
                exploreSelectedItemLinearLayout.setBackgroundColor(Color.TRANSPARENT);
            }

            incButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemIncButtonClickedListener != null) {
                        onItemIncButtonClickedListener.onIncClicked(v, position);
                    }
                }
            });

            decButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemDecButtonClickedListener != null) {
                        onItemDecButtonClickedListener.onDecClicked(v, position);
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemDeleteButtonClickedListener != null) {
                        onItemDeleteButtonClickedListener.onDeleteClicked(v, position);
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if (mItems == null) return 0;
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SwipeRevealLayout swipeRevealLayout;
        public LinearLayout exploreSelectedItemLinearLayout;
        public TextView itemNameTextView;
        public TextView itemAmountTextView;
        public ImageButton incButton;
        public ImageButton decButton;
        public ImageButton deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            itemNameTextView = (TextView) itemView.findViewById(R.id.item_name_textView);
            itemAmountTextView = (TextView) itemView.findViewById(R.id.item_amount_textView);
            incButton = (ImageButton) itemView.findViewById(R.id.item_inc_button);
            decButton = (ImageButton) itemView.findViewById(R.id.item_dec_button);
            exploreSelectedItemLinearLayout = (LinearLayout) itemView.findViewById(R.id.explore_selected_item);
            deleteButton = (ImageButton) itemView.findViewById(R.id.item_delete_button);
        }
    }

    public void setOnItemIncButtonClickedListener(OnItemIncButtonClickedListener onItemIncButtonClickedListener) {
        this.onItemIncButtonClickedListener = onItemIncButtonClickedListener;
    }

    public void setOnItemDecButtonClickedListener(OnItemDecButtonClickedListener onItemDecButtonClickedListener) {
        this.onItemDecButtonClickedListener = onItemDecButtonClickedListener;
    }

    public void setOnItemDeleteButtonClickedListener(OnItemDeleteButtonClickedListener onItemDeleteButtonClickedListener) {
        this.onItemDeleteButtonClickedListener = onItemDeleteButtonClickedListener;
    }

    public interface OnItemIncButtonClickedListener {
        public void onIncClicked(View view, int position);
    }

    public interface OnItemDecButtonClickedListener {
        public void onDecClicked(View view, int position);
    }

    public interface OnItemDeleteButtonClickedListener {
        public void onDeleteClicked(View view, int position);
    }

}