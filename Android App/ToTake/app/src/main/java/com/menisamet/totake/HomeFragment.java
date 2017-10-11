package com.menisamet.totake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.menisamet.totake.Logic.LogicInterface;
import com.menisamet.totake.Logic.LogicService;
import com.menisamet.totake.Modals.User;
import com.menisamet.totake.Server.Listeners.UserLoadListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getCanonicalName();
    LogicInterface logicInterface = LogicService.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //loading component
    private Button mReloadButton;
    private Button mLoginTestButton;
    private Button mLoginTahelButton;
    private TextView mUserNameTextView;
    private ProgressBar mLoadingProgressBar;


    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Inflate the layout for this fragment
        mReloadButton = (Button) getView().findViewById(R.id.reload_button);
        mLoginTestButton = (Button) getView().findViewById(R.id.login_button);
        mLoginTahelButton = (Button) getView().findViewById(R.id.login_tahel_button);
        mUserNameTextView = (TextView) getView().findViewById(R.id.user_name_textView);
        mLoadingProgressBar = (ProgressBar) getView().findViewById(R.id.loading_progressBar);


        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        FirebaseUser firebaseUser;
        if ((firebaseUser = mAuth.getCurrentUser()) != null) {
            logicInterface.setFireBaseUser(firebaseUser.getUid(), firebaseUser.getDisplayName(), new UserLoadListener() {
                @Override
                public void onUserLoad(User user) {
                    loadUserFromServer();
                }
            });
        }

        loadUserFromServer();



        mReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingProgressBar.setVisibility(View.VISIBLE);
                loadUserFromServer();
            }
        });

        mLoginTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FacebookLoginActivity.class);
                startActivity(intent);
            }
        });

        mLoginTahelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserWithServer();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        loadUserFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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

    private void loginUserWithServer() {
        logicInterface.setUserId(15, new UserLoadListener() {
            @Override
            public void onUserLoad(User user) {
                if (user != null) {
                    mLoadingProgressBar.setVisibility(View.GONE);
                    mUserNameTextView.setText(user.getNameUser());
                }
                Log.d(TAG, "user :" + user);
            }
        });
    }

    private void loadUserFromServer() {
        User user = logicInterface.getUser();
        if (user != null) {
            mLoadingProgressBar.setVisibility(View.GONE);
            mUserNameTextView.setText(user.getNameUser());
        }
        Log.d(TAG, "user :" + user);
    }
}
