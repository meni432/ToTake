package com.menisamet.totake;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.menisamet.totake.Adapters.ExploreSelectedListAdapter;
import com.menisamet.totake.GuiElement.CardsRecyclerView;
import com.menisamet.totake.Logic.GuiInterface;
import com.menisamet.totake.Logic.GuiService;
import com.menisamet.totake.Modals.Item;
import com.menisamet.totake.Modals.Trip;
import com.menisamet.totake.Server.Listeners.RecommendationListResponseListener;

import java.util.List;


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
    GuiInterface guiInterface = GuiService.getInstance();
    public static int msCurrentTripId = 0;
    private List<Item> mItems;
    private List<Item> mSuggestionItems;
    private List<Item> mSuggestionSearchItems;

    private RecyclerView mRvSelectedItems;

    // Cards Adapter element
    private CardsRecyclerView mRecyclerView;
    private CardsRecyclerView.RecycleViewCardAdapter<Item> mRAdapter;
    private CardsRecyclerView.RecycleViewCardAdapter<Item> mSearchAdapter;

    private EditText mSearchEditText;

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
        mSearchEditText = (EditText) getView().findViewById(R.id.search_edit_text);

        guiInterface.setContext(getContext());

        initialSelectedItemView();
        initialSuggestionView();
    }

    private void initialSelectedItemView() {
        final Trip trip = guiInterface.getTripById(msCurrentTripId);
        mItems = trip.getItems();
        final ExploreSelectedListAdapter exploreSelectedListAdapter = new ExploreSelectedListAdapter(getContext(), mItems, msCurrentTripId);
        mRvSelectedItems.setAdapter(exploreSelectedListAdapter);
        mRvSelectedItems.setLayoutManager(new LinearLayoutManager(getContext()));
        exploreSelectedListAdapter.setOnItemDecButtonClickedListener(new ExploreSelectedListAdapter.OnItemDecButtonClickedListener() {
            @Override
            public void onDecClicked(View view, int position) {
                Item item = mItems.get(position);
                long originalAmount = item.getItemAmount();
                if (originalAmount > 0) {
                    item.setmItemAmount(originalAmount - 1);
                    guiInterface.notifyChangeAmount(trip, item);
                    exploreSelectedListAdapter.notifyDataSetChanged();
                } else {
                    item.setmItemAmount(0);
                }
            }
        });
        exploreSelectedListAdapter.setOnItemIncButtonClickedListener(new ExploreSelectedListAdapter.OnItemIncButtonClickedListener() {
            @Override
            public void onIncClicked(View view, int position) {
                Item item = mItems.get(position);
                item.setmItemAmount(item.getItemAmount() + 1);
                guiInterface.notifyChangeAmount(trip, item);
                exploreSelectedListAdapter.notifyDataSetChanged();
            }
        });
        exploreSelectedListAdapter.setOnItemDeleteButtonClickedListener(new ExploreSelectedListAdapter.OnItemDeleteButtonClickedListener() {
            @Override
            public void onDeleteClicked(View view, int position) {
                Item item = mItems.get(position);
                guiInterface.deleteItemFromTrip(trip, item);
//                mItems.remove(position);
//                exploreSelectedListAdapter.notifyItemRemoved(position);
                exploreSelectedListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initialSuggestionView() {
//        mSuggestionItems = Item.createItemList(20); //TODO Meni - get from logic
        mSuggestionItems = Item.createItemList(10);
        mRecyclerView = (CardsRecyclerView) getView().findViewById(R.id.suggestion_card_recycle_view);
        mRecyclerView.setNumLinesAndOrientation(1, CardsRecyclerView.HORIZONTAL);
        mRAdapter = new CardsRecyclerView.RecycleViewCardAdapter<>(mSuggestionItems);
        mRAdapter.setImageGlobalRecurse(R.drawable.ic_add_circle_black_24dp);
        mRAdapter.setCardGlobalColor(CardsRecyclerView.RANDOM_COLOR);
        mRecyclerView.setAdapter(mRAdapter);
        mRecyclerView.setCardClickListener(new CardsRecyclerView.CardClickListener() {
            @Override
            public void onCardClick(View view, int position) {
                if (position > -1) {
                    removeFromSuggestion(position, true);
                }
            }
        });
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
                            // TODO - Meni add a complete delete
                        }
                        @Override public void onShown(Snackbar snackbar) {
                            // current not in used
                            // for
                        }
                    });
                    snackbar.show();
                }
            }
        });

        Trip trip = guiInterface.getTripById(msCurrentTripId);
        guiInterface.getRecommendationList(trip, new RecommendationListResponseListener() {
            @Override
            public void onResponse(List<Item> recommendedItems) {
                mSuggestionItems = recommendedItems;
                mRAdapter.notifyDataSetChanged();
            }
        });
    }

    private void removeFromSuggestion(int position, boolean isChoosing) {
        // TODO Meni - notify logic
        // TODO Meni - chnage to hide
        mSuggestionItems.remove(position);
        mRAdapter.notifyItemRemoved(position);
    }

    private Snackbar generateDeleteSnackbar(final Item item, final View.OnClickListener undoOnClick, final Snackbar.Callback onDismissedCallback) {
        Snackbar snackbar = Snackbar
                .make(getView(), item.getItemName() + " " +getString(R.string.is_seleted), Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(getView(), item.getItemName() + " " +getString(R.string.add_to_your_list), Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        snackbar1.addCallback(onDismissedCallback);
                        undoOnClick.onClick(v);
                    }
                });

        return snackbar;
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