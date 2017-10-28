package com.menisamet.totake;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.menisamet.totake.Adapters.ExploreSelectedListAdapter;
import com.menisamet.totake.GuiElement.CardsRecyclerView;
import com.menisamet.totake.Logic.LogicInterface;
import com.menisamet.totake.Logic.LogicService;
import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Server.Listeners.AddNewItemResponseListener;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {
    private static String TAG = ExploreFragment.class.getCanonicalName();
    LogicInterface logicInterface = LogicService.getInstance();
    public static int msCurrentTripId = 0;
    private Trip mTrip;
    private List<Item> mItems;
    private List<Item> mSuggestionItems;
    private List<Item> mSuggestionSearchItems;

    private RecyclerView mRvSelectedItems;

    // Cards Adapter element
    private CardsRecyclerView mRecyclerView;
    private CardsRecyclerView.RecycleViewCardAdapter<Item> mRAdapter;
    private CardsRecyclerView.RecycleViewCardAdapter<Item> mSearchAdapter;

    // Selected Item Adapter elemnt
    private ExploreSelectedListAdapter mExploreSelectedListAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CURRENT_TRIP = "current_trip";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExploreFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CURRENT_TRIP, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_CURRENT_TRIP);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRvSelectedItems = (RecyclerView) getView().findViewById(R.id.rvExploreSelectedList);
        mRvSelectedItems.setItemAnimator(new SlideInLeftAnimator());

        logicInterface.setContext(getContext());

        if (isFragmentUIActive()) {
            initialSelectedItemView();
            mTrip = logicInterface.getTripById(msCurrentTripId);
            getUpdatedRecommendedItems();
        }

    }

    private void initialSelectedItemView() {
        Log.d(TAG, "current trip id : " + msCurrentTripId);
        mTrip = logicInterface.getTripById(msCurrentTripId);
        mItems = mTrip.getItems();
        mExploreSelectedListAdapter = new ExploreSelectedListAdapter(getContext(), mItems, msCurrentTripId);
        mRvSelectedItems.setAdapter(mExploreSelectedListAdapter);
        mRvSelectedItems.setLayoutManager(new LinearLayoutManager(getContext()));
        mExploreSelectedListAdapter.setOnItemDecButtonClickedListener(new ExploreSelectedListAdapter.OnItemDecButtonClickedListener() {
            @Override
            public void onDecClicked(View view, int position) {
                Item item = mItems.get(position);
                long originalAmount = item.getItemAmount();
                if (originalAmount > 0) {
                    item.setmItemAmount(originalAmount - 1);
                    logicInterface.notifyChangeAmount(mTrip, item);
                    mExploreSelectedListAdapter.notifyDataSetChanged();
                } else {
                    item.setmItemAmount(0);
                }
            }
        });
        mExploreSelectedListAdapter.setOnItemIncButtonClickedListener(new ExploreSelectedListAdapter.OnItemIncButtonClickedListener() {
            @Override
            public void onIncClicked(View view, int position) {
                Item item = mItems.get(position);
                item.setmItemAmount(item.getItemAmount() + 1);
                logicInterface.notifyChangeAmount(mTrip, item);
                mExploreSelectedListAdapter.notifyDataSetChanged();
            }
        });
        mExploreSelectedListAdapter.setOnItemDeleteButtonClickedListener(new ExploreSelectedListAdapter.OnItemDeleteButtonClickedListener() {
            @Override
            public void onDeleteClicked(View view, int position) {
                Item item = mItems.get(position);
                logicInterface.deleteItemFromTrip(mTrip, item);
//                mItems.remove(position);
//                exploreSelectedListAdapter.notifyItemRemoved(position);
                mExploreSelectedListAdapter.notifyItemRemoved(position);
                new CountDownTimer(300, 1) {
                    public void onFinish() {
                        mExploreSelectedListAdapter.notifyDataSetChanged();
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

            }
        });

    }

    private final int NUM_SUGGESTION_TO_DELETE = 2;

    public boolean isFragmentUIActive() {
        return isAdded() && !isDetached() && !isRemoving();
    }

    private boolean mWaitingForRecomandetion = false;


    private void initialSuggestionView(int numLines) {
//        mSuggestionItems = Item.createItemList(20); //TODO Meni - get from logic
        if (!isFragmentUIActive()) {
            return;
        }
        mRecyclerView = (CardsRecyclerView) getView().findViewById(R.id.suggestion_card_recycle_view);
        if (mRecyclerView == null) {
            return;
        }
        mRecyclerView.setDuration(1);
        mRecyclerView.setNumLinesAndOrientation(numLines, CardsRecyclerView.HORIZONTAL);
        mRAdapter = new CardsRecyclerView.RecycleViewCardAdapter<>(mSuggestionItems);
        mRAdapter.setCardGlobalColor(0xFFE91E63);
        mRAdapter.setImageGlobalRecurse(R.drawable.ic_add_circle_black_24dp);
        mRAdapter.setCardGlobalColor(CardsRecyclerView.RANDOM_COLOR);
        mRecyclerView.setAdapter(mRAdapter);
        mRecyclerView.setCardClickListener(new CardsRecyclerView.CardClickListener() {
            @Override
            public void onCardClick(View view, int position) {
                if (position > -1) {
                    assignItemToList(position);
//                    removeFromSuggestion(position, true);
                }
            }
        });
        final Trip trip = logicInterface.getTripById(msCurrentTripId);
        mRecyclerView.setCardLongClickListener(new CardsRecyclerView.CardLongClickListener() {
            @Override
            public void onCardLongCLick(View view, int position) {
                if (position > -1) {
                    final Item item = mSuggestionItems.get(position);
                    removeFromSuggestion(position, false);
                    Snackbar snackbar = generateDeleteSnackbar(item, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }, new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                        }

                        @Override
                        public void onShown(Snackbar snackbar) {
                        }
                    });
                    snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);
                            Log.d(TAG, " removeFromRecommendationList dismissed");
                            logicInterface.removeFromRecommendationList(trip, item);
                        }
                    });
                    snackbar.show();
                }
            }
        });

    }

    private void removeFromSuggestion(int position, boolean isChoosing) {
        mSuggestionItems.remove(position);
        Log.d(TAG, mSuggestionItems.toString());
        mRAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(position);
        checkForNeededUpdate();
    }

    private void moveToTailFromSuggestion(int position) {
        if (mSuggestionItems.size() > NUM_SUGGESTION_TO_DELETE) {
            Item item = mSuggestionItems.remove(position);
            Log.d(TAG, mSuggestionItems.toString());
            mSuggestionItems.add(mSuggestionItems.size() - 1, item);
            mRAdapter.notifyDataSetChanged();
        }
    }


    private void checkForNeededUpdate() {
        if (mSuggestionItems.size() < 5 || mOverrideSuggestion) {
            getUpdatedRecommendedItems();
        }
    }

    private void liveUpdateRecomendation() {
        new CountDownTimer(3000, 10) {
            public void onFinish() {
                checkForNeededUpdate();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }

    private boolean mOverrideSuggestion = false;

    private void getUpdatedRecommendedItems() {
        logicInterface.getRecommendationList(mTrip, new RecommendationListResponseListener() {
            @Override
            public void onResponse(List<Item> recommendedItems, boolean override) {
                if (override) {
                    mOverrideSuggestion = true;
                    List<Item> items = new ArrayList<Item>();
                    for (Item item : recommendedItems) {
                        int randPosition = Math.max((int) (Math.random() * (items.size() - 1)), 0);
                        if (!mItems.contains(item)) {
                            items.add(randPosition, item);
                        }
                    }

                    mSuggestionItems = items;
                    int currentPosition = 0;
                    if (mRecyclerView != null) {
                        currentPosition = mRecyclerView.getHorizontalFadingEdgeLength();
                    }
                    initialSuggestionView(4);
                    mRecyclerView.scrollToPosition(currentPosition);
                } else {
                    boolean hasChange = false;
                    if (mOverrideSuggestion) {
                        mSuggestionItems = new ArrayList<Item>();
                    }
                    mOverrideSuggestion = false;
                    if (mSuggestionItems == null) {
                        mSuggestionItems = new ArrayList<Item>();
                    }
                    for (Item item : recommendedItems) {
                        if (!mSuggestionItems.contains(item) && !mItems.contains(item)) {
                            mSuggestionItems.add(item);
                            hasChange = true;
                        }
                    }
                    if (hasChange) {
                        int currentPosition = 0;
                        if (mRecyclerView != null) {
                            currentPosition = mRecyclerView.getHorizontalFadingEdgeLength();
                        }
                        initialSuggestionView(1);
                        if (mRecyclerView != null) {
                            mRecyclerView.scrollToPosition(currentPosition);
                        }
                    }

                    liveUpdateRecomendation();
                }


            }
        });

        if (mRAdapter != null) {
            mRAdapter.notifyDataSetChanged();
        }
    }

    private void assignItemToList(final int position) {
        Item item = mSuggestionItems.get(position);
        logicInterface.assignItemToTrip(mTrip, item, item.getItemAmount(), new AddNewItemResponseListener() {
            @Override
            public void onResponse(Item item) {
                Log.d(TAG, "item was assign" + item);
            }
        });
        mExploreSelectedListAdapter.notifyDataSetChanged();
        mRvSelectedItems.scrollToPosition(0);
        removeFromSuggestion(position, true);
    }

    private Snackbar generateDeleteSnackbar(final Item item, final View.OnClickListener undoOnClick, final Snackbar.Callback onDismissedCallback) {

        return Snackbar
                .make(getView(), item.getItemName() + " " + getString(R.string.is_seleted), Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(getView(), item.getItemName() + " " + getString(R.string.add_to_your_list), Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        snackbar1.addCallback(onDismissedCallback);
                        undoOnClick.onClick(v);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isFragmentUIActive()) {
            initialSelectedItemView();
            mTrip = logicInterface.getTripById(msCurrentTripId);
            getUpdatedRecommendedItems();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}