package com.riskitbiskit.fundamentalsbasketballdrills.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.riskitbiskit.fundamentalsbasketballdrills.LevelsAndType.LevelsActivity;
import com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.squareup.picasso.Picasso;

import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.*;


public class HomeFragmentTwo extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    //Request
    public static final String TABLE_REQUEST = "tableRequest";

    //Request Types
    public static final int IP_TABLE_REQUEST = 1;
    public static final int CP_TABLE_REQUEST = 2;

    //Class Global Varibales
    TextView userNameTV;
    TextView userHometownTV;
    SharedPreferences mSharedPreferences;

    public HomeFragmentTwo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment_two, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.background_image_holder_frag1);
        Picasso.with(getContext()).load(R.drawable.home).into(imageView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userNameTV = (TextView) getActivity().findViewById(R.id.home_frg1_user_name);
        userHometownTV = (TextView) getActivity().findViewById(R.id.home_frg1_user_hometown);
        TextView userPositionsTV = (TextView) getActivity().findViewById(R.id.home_frg1_user_pos);
        Button inProgressButton = (Button) getActivity().findViewById(R.id.in_progress_drills_button);
        Button completedButton = (Button) getActivity().findViewById(R.id.completed_drills_button);

//        mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        userNameTV.setText(mSharedPreferences.getString(USER_NAME, USER_DEFAULT));
        userHometownTV.setText(mSharedPreferences.getString(USER_HOMETOWN, USER_DEFAULT));
        userPositionsTV.setText(mSharedPreferences.getString(USER_POSITIONS, USER_DEFAULT));

        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);

        inProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LevelsActivity.class);
                intent.putExtra(TABLE_REQUEST, IP_TABLE_REQUEST);
                startActivity(intent);
            }
        });

        completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LevelsActivity.class);
                intent.putExtra(TABLE_REQUEST, CP_TABLE_REQUEST);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(UserPreferencesConstants.USER_NAME)) {
            userNameTV.setText(mSharedPreferences.getString(USER_NAME, USER_DEFAULT));
        }
        if (key.equals(UserPreferencesConstants.USER_HOMETOWN)) {
            userHometownTV.setText(mSharedPreferences.getString(USER_HOMETOWN, USER_DEFAULT));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
