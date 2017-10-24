package com.riskitbiskit.fundamentalsbasketballdrills.Landing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.riskitbiskit.fundamentalsbasketballdrills.Home.HomeActivity;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.squareup.picasso.Picasso;

import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.*;

public class MainLanding extends AppCompatActivity {

    ImageView mImageView;
    String storedName;
    String storedHometown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);

//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        storedName = sharedPreferences.getString(USER_NAME, USER_DEFAULT);
        storedHometown = sharedPreferences.getString(USER_HOMETOWN, USER_DEFAULT);

        mImageView = (ImageView) findViewById(R.id.background_image_main_landing);
        Button button = (Button) findViewById(R.id.start_training_button);

        Picasso.with(this).load(R.drawable.home).into(mImageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storedName.equals(USER_DEFAULT) || storedHometown.equals(USER_DEFAULT)) {
                    Intent intent = new Intent(getBaseContext(), FirstLanding.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.main_landing_rootlayout);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                frameLayout.setVisibility(View.VISIBLE);
            }
        }, 700);


    }
}
