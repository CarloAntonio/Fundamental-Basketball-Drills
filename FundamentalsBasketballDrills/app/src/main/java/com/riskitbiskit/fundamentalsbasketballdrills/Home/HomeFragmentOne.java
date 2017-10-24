package com.riskitbiskit.fundamentalsbasketballdrills.Home;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.USER_DEFAULT;
import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.USER_HOMETOWN;
import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.USER_NAME;
import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.USER_POSITIONS;

public class HomeFragmentOne extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    //Class Global Variables
    TextView userNameTV;
    TextView userHometownTV;
    TextView userPositionsTV;
    ImageView profilePic;
    SharedPreferences mSharedPreferences;
    String dirPath;
    public static final int SELECT_PICTURE = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate fragment layout
        View rootView = inflater.inflate(R.layout.home_fragment_one, container, false);
        profilePic = (ImageView) rootView.findViewById(R.id.circle_profile_picture);
        //Grab views from inflate base view, in this case it's rootView
        ImageView imageView = (ImageView) rootView.findViewById(R.id.background_image_holder_frag2);

        //Check to see it there is a saved image, if not default image is used
        File file = new File("/data/user/0/com.riskitbiskit.fundamentalsbasketballdrills/app_testDir", "profile_pic.jpg");
        if (file.exists()) {
            Bitmap b = null;
            file = new File("/data/user/0/com.riskitbiskit.fundamentalsbasketballdrills/app_testDir", "profile_pic.jpg");
            try {
                b = BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            profilePic.setImageBitmap(b);
        } else {
            profilePic.setImageResource(R.drawable.test_pic);
        }

        Button button = (Button) rootView.findViewById(R.id.full_drills_list);

        Picasso.with(getContext()).load(R.drawable.home).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LevelsActivity.class);
                startActivity(intent);
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent that lets you choose a photo from the gallery
                Intent choosePictureIntent = new Intent();
                choosePictureIntent.setType("image/*");
                choosePictureIntent.setAction(Intent.ACTION_GET_CONTENT);

                //Intent that lets you take a picture
                Intent takePhctoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Intent Chooser
                String title = "Select/Take Picture";
                Intent chooserIntent = Intent.createChooser(choosePictureIntent, title);
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {takePhctoIntent});

                startActivityForResult(chooserIntent, SELECT_PICTURE);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = null;

            if (data.hasExtra("data")) {
                photo = (Bitmap) data.getExtras().get("data");
                dirPath = saveImage(photo);
                profilePic.setImageBitmap(photo);
            } else {
                Uri imageUri = data.getData();
                try {
                    photo = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dirPath = saveImage(photo);
                profilePic.setImageBitmap(photo);
            }
        }
    }

    public String saveImage(Bitmap bitmap) {
        ContextWrapper cw = new ContextWrapper(getContext().getApplicationContext());
        File directory = cw.getDir("testDir", Context.MODE_PRIVATE);

        File mypath = new File(directory, "profile_pic.jpg");

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return directory.getAbsolutePath();
    }

    public Bitmap loadImage() {

        File file = new File(dirPath, "profile_pic.jpg");
        try {
            return BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userNameTV = (TextView) getActivity().findViewById(R.id.home_frg2_user_name);
        userHometownTV = (TextView) getActivity().findViewById(R.id.home_frg2_user_hometown);
        userPositionsTV = (TextView) getActivity().findViewById(R.id.home_frg2_user_pos);

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        userNameTV.setText(mSharedPreferences.getString(USER_NAME, USER_DEFAULT));
        userHometownTV.setText(mSharedPreferences.getString(USER_HOMETOWN, USER_DEFAULT));
        userPositionsTV.setText(mSharedPreferences.getString(USER_POSITIONS, USER_DEFAULT));

        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(UserPreferencesConstants.USER_NAME)) {
            userNameTV.setText(mSharedPreferences.getString(USER_NAME, USER_DEFAULT));
        }
        if (key.equals(UserPreferencesConstants.USER_HOMETOWN)) {
            userHometownTV.setText(mSharedPreferences.getString(USER_HOMETOWN, USER_DEFAULT));
        }
        if (key.equals(UserPreferencesConstants.PG_BOOL) ||
                key.equals(UserPreferencesConstants.SG_BOOL) ||
                key.equals(UserPreferencesConstants.SF_BOOL) ||
                key.equals(UserPreferencesConstants.PF_BOOL) ||
                key.equals(UserPreferencesConstants.C_BOOL)) {
            boolean pg = mSharedPreferences.getBoolean(UserPreferencesConstants.PG_BOOL, false);
            boolean sg = mSharedPreferences.getBoolean(UserPreferencesConstants.SG_BOOL, false);
            boolean sf = mSharedPreferences.getBoolean(UserPreferencesConstants.SF_BOOL, false);
            boolean pf = mSharedPreferences.getBoolean(UserPreferencesConstants.PF_BOOL, false);
            boolean c = mSharedPreferences.getBoolean(UserPreferencesConstants.C_BOOL, false);

            String userPositions = getStringPositions(pg, sg, sf, pf, c);

            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(UserPreferencesConstants.USER_POSITIONS, userPositions);
            editor.apply();
        }
    }

    public String getStringPositions(boolean pg, boolean sg, boolean sf, boolean pf, boolean c) {

        String userPositions = "None";

        if (pg) {
            userPositions = "PG";
        }

        if (sg) {
            if (userPositions.contains("None")) {
                userPositions = "SG";
            } else {
                userPositions = userPositions + ", SG";
            }
        }

        if (sf) {
            if (userPositions.contains("None")) {
                userPositions = "SF";
            } else {
                userPositions = userPositions + ", SF";
            }
        }

        if (pf) {
            if (userPositions.contains("None")) {
                userPositions = "PF";
            } else {
                userPositions = userPositions + ", PF";
            }
        }

        if (c) {
            if (userPositions.contains("None")) {
                userPositions = "C";
            } else {
                userPositions = userPositions + ", C";
            }
        }

        return userPositions;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
