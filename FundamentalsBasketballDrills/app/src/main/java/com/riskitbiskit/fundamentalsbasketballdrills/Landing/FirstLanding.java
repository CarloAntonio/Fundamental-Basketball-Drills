package com.riskitbiskit.fundamentalsbasketballdrills.Landing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.riskitbiskit.fundamentalsbasketballdrills.Home.HomeActivity;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.squareup.picasso.Picasso;

import static com.riskitbiskit.fundamentalsbasketballdrills.Preferences.UserPreferencesConstants.*;

public class FirstLanding extends AppCompatActivity {

    EditText nameEditText;
    EditText hometownEditText;
    CheckBox PGCheckBox;
    CheckBox SGCheckBox;
    CheckBox SFCheckBox;
    CheckBox PFCheckBox;
    CheckBox CCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_landing);

        ImageView imageView = (ImageView) findViewById(R.id.background_image_first_landing);
        Button button = (Button) findViewById(R.id.start_training_button);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        hometownEditText = (EditText) findViewById(R.id.hometown_edit_text);
        PGCheckBox = (CheckBox) findViewById(R.id.point_guard_checkbox);
        SGCheckBox = (CheckBox) findViewById(R.id.shooting_guard_checkbox);
        SFCheckBox = (CheckBox) findViewById(R.id.small_forward_checkbox);
        PFCheckBox = (CheckBox) findViewById(R.id.power_forward_checkbox);
        CCheckBox = (CheckBox) findViewById(R.id.center_checkbox);


        Picasso.with(this).load(R.drawable.home).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString().trim();
                String userHometown = hometownEditText.getText().toString().trim();
                String userPositions = "None";

                if (PGCheckBox.isChecked()) {
                    userPositions = "PG";
                }

                if (SGCheckBox.isChecked()) {
                    if (userPositions.contains("None")) {
                        userPositions = "SG";
                    } else {
                        userPositions = userPositions + ", SG";
                    }
                }

                if (SFCheckBox.isChecked()) {
                    if (userPositions.contains("None")) {
                        userPositions = "SF";
                    } else {
                        userPositions = userPositions + ", SF";
                    }
                }

                if (PFCheckBox.isChecked()) {
                    if (userPositions.contains("None")) {
                        userPositions = "PF";
                    } else {
                        userPositions = userPositions + ", PF";
                    }
                }

                if (CCheckBox.isChecked()) {
                    if (userPositions.contains("None")) {
                        userPositions = "C";
                    } else {
                        userPositions = userPositions + ", C";
                    }
                }

//                SharedPreferences sharedPreferences = getSharedPreferences("FundamentalsData", MODE_PRIVATE);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_NAME, userName);
                editor.putString(USER_HOMETOWN, userHometown);
                editor.putString(USER_POSITIONS, userPositions);

                //Position Boolean Test
                editor.putBoolean(PG_BOOL, PGCheckBox.isChecked());
                editor.putBoolean(SG_BOOL, SGCheckBox.isChecked());
                editor.putBoolean(SF_BOOL, SFCheckBox.isChecked());
                editor.putBoolean(PF_BOOL, PFCheckBox.isChecked());
                editor.putBoolean(C_BOOL, CCheckBox.isChecked());

                editor.apply();

                Toast.makeText(getBaseContext(), "Welcome to the FBD Family!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
