package com.menisamet.totake;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menisamet.totake.Helper.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Random;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static com.menisamet.totake.Constants.NUMBER_OF_SUGGESTION_LINES;

public class ToolActivity extends AppCompatActivity {

    public static final String TAG = "TAG_"+ToolActivity.class.getCanonicalName();

    private RecyclerView mSuggestionRecyclerView;
    private RecyclerView.Adapter mSuggestionsAdapter;

    private RecyclerView mSelectedRecyclerView;
    private RecyclerView.Adapter mSelectedAdapter;

    private Random mRandom = new Random();

    private Context mContext;

    private ArrayList<String> mSelectedArrayList;
    ScaleInAnimationAdapter mAlphaAdapterSelections;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);

        mContext = this;

        mSuggestionRecyclerView = (RecyclerView) findViewById(R.id.suggestion_recycle_view);
        mSelectedRecyclerView = (RecyclerView) findViewById(R.id.selected_recycle_view);


        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(NUMBER_OF_SUGGESTION_LINES, StaggeredGridLayoutManager.HORIZONTAL);

        StaggeredGridLayoutManager gridLayoutManager1 =
                new StaggeredGridLayoutManager(NUMBER_OF_SUGGESTION_LINES, StaggeredGridLayoutManager.HORIZONTAL);


        mSuggestionRecyclerView.setLayoutManager(gridLayoutManager);
        mSelectedRecyclerView.setLayoutManager(gridLayoutManager1);


        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            StringBuilder stringBuilder = new StringBuilder();
            int rand = mRandom.nextInt(10);
            for (int j = 0; j < rand; j++){
                stringBuilder.append(' ');
            }
            stringBuilder.append("card "+i);
            for (int j = 0; j < rand; j++){
                stringBuilder.append(' ');
            }
            list.add(stringBuilder.toString());
        }


        mSuggestionsAdapter = new SuggestionCardAdapter(list);
        ((SuggestionCardAdapter) mSuggestionsAdapter).setIconRecurse(R.drawable.plus_circle);
        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(mSuggestionsAdapter);
        alphaAdapter.setDuration(400);
        alphaAdapter.setFirstOnly(false);
        mSuggestionRecyclerView.setAdapter(alphaAdapter);



        mSelectedArrayList = new ArrayList<>();
        mSelectedAdapter = new SuggestionCardAdapter(mSelectedArrayList);
        ((SuggestionCardAdapter) mSelectedAdapter).setIconRecurse(R.drawable.minus_circle);
         mAlphaAdapterSelections = new ScaleInAnimationAdapter(mSelectedAdapter);
        mAlphaAdapterSelections.setDuration(700);
        mAlphaAdapterSelections.setFirstOnly(false);
        mSelectedRecyclerView.setAdapter(new AlphaInAnimationAdapter(mAlphaAdapterSelections));


        mSuggestionRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String item = list.remove(position);
                mSuggestionsAdapter.notifyItemRemoved(position);
                addItemToSelectedList(item);
            }
        }));


        mSelectedRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                String item = mSelectedArrayList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage(getString(R.string.remove)+item).setTitle(R.string.are_you_sure);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectedArrayList.remove(position);
                        mSelectedAdapter.notifyItemRemoved(position);
                        //TODO implement the logic of remove from last items, currently only remove from the view
                    }
                });

                builder.setNegativeButton(R.string.cancel, null);

                Dialog dialog = builder.create();
                dialog.show();

            }
        }));


    }

    private void addItemToSelectedList(String item){
        Log.d(TAG, "add item to selected list: "+item);
        mSelectedArrayList.add(0, item);
        mAlphaAdapterSelections.notifyItemInserted(0);
        mSelectedRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mSelectedRecyclerView.smoothScrollToPosition(0);
            }
        });
    }
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "Belarus", "France", "Italy", "Germany", "Spain"
    };

    public class SuggestionCardAdapter extends RecyclerView.Adapter<SuggestionCardAdapter.SuggestionViewHolder> {

        private ArrayList<String> suggestionList;
        private int iconRecurse;

        public SuggestionCardAdapter(ArrayList<String> suggestionList) {
            this.suggestionList = suggestionList;
        }

        public void setIconRecurse(int iconRecurse) {
            this.iconRecurse = iconRecurse;
        }

        @Override
        public SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_card_item, parent, false);
            SuggestionViewHolder viewHolder = new SuggestionViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SuggestionViewHolder holder, final int position) {
            holder.mNameTextView.setText(suggestionList.get(position));
            holder.mCardView.setCardBackgroundColor(getRandomHSVColor());
            if (iconRecurse != 0){
                holder.mImageView.setImageResource(iconRecurse);
                holder.mImageView.setColorFilter(Color.WHITE);
            }
        }

        @Override
        public int getItemCount() {
            return suggestionList.size();
        }

        public class SuggestionViewHolder extends RecyclerView.ViewHolder {

            protected TextView mNameTextView;
            protected CardView mCardView;
            protected ImageView mImageView;

            public SuggestionViewHolder(View itemView) {
                super(itemView);
                mCardView = (CardView) itemView.findViewById(R.id.card_view);
                mNameTextView = (TextView) itemView.findViewById(R.id.item_text);
                mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            }
        }





    }


    public int getRandomHSVColor() {
        int[] colorSet = {0xFFF44336, 0xFFE91E63, 0xFF673AB7, 0xFF3F51B5, 0xFF2196F3, 0xFF03A9F4, 0xFF009688, 0xFFFF5722, 0xFF795548};
        int random = mRandom.nextInt(colorSet.length);
        return colorSet[random];

    }


}