package com.riskitbiskit.fundamentalsbasketballdrills.LevelsAndType;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.riskitbiskit.fundamentalsbasketballdrills.CompletedDrills.CompletedDrills;
import com.riskitbiskit.fundamentalsbasketballdrills.Drills.Drill;
import com.riskitbiskit.fundamentalsbasketballdrills.Home.HomeFragmentOne;
import com.riskitbiskit.fundamentalsbasketballdrills.Home.HomeFragmentTwo;
import com.riskitbiskit.fundamentalsbasketballdrills.InProgressDrills.InProgressDrills;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class LevelsActivity extends AppCompatActivity {

    int requestedTable;
    public static final String SKILL_LEVEL = "skillLevel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Intent intent = getIntent();

        if (intent.hasExtra(HomeFragmentTwo.TABLE_REQUEST)) {
            requestedTable = intent.getIntExtra(HomeFragmentTwo.TABLE_REQUEST, HomeFragmentTwo.IP_TABLE_REQUEST);
        }

        ImageView froshImageView = (ImageView) findViewById(R.id.frosh_iv);
        ImageView sophImageView = (ImageView) findViewById(R.id.soph_iv);
        ImageView juniorImageView = (ImageView) findViewById(R.id.junior_iv);
        ImageView seniorImageView = (ImageView) findViewById(R.id.senior_iv);

        TextView froshTextView = (TextView) findViewById(R.id.frosh_tv);
        TextView sophTextView = (TextView) findViewById(R.id.soph_tv);
        TextView juniorTextView = (TextView) findViewById(R.id.junior_tv);
        TextView seniorTextView = (TextView) findViewById(R.id.senior_tv);

        Picasso.with(this).load(R.drawable.bballshadow).into(froshImageView);
        Picasso.with(this).load(R.drawable.hoop).into(sophImageView);
        Picasso.with(this).load(R.drawable.puddlebounce).into(juniorImageView);
        Picasso.with(this).load(R.drawable.jumpshot).into(seniorImageView);

        AssetManager assetManager = getAssets();

        Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "fonts/%s", "Roboto-Bold.ttf"));

        froshTextView.setTypeface(typeface);
        sophTextView.setTypeface(typeface);
        juniorTextView.setTypeface(typeface);
        seniorTextView.setTypeface(typeface);
    }

    public void froshClicked(View view) {
        if (requestedTable == HomeFragmentTwo.IP_TABLE_REQUEST) {
            Intent intent = new Intent(this, InProgressDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_FROSH);
            startActivity(intent);
        } else if (requestedTable == HomeFragmentTwo.CP_TABLE_REQUEST) {
            Intent intent = new Intent(this, CompletedDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_FROSH);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SkillTypeActivity.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_FROSH);
            startActivity(intent);
        }

    }

    public void sophClicked(View view) {
        if (requestedTable == HomeFragmentTwo.IP_TABLE_REQUEST) {
            Intent intent = new Intent(this, InProgressDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_SOPH);
            startActivity(intent);
        } else if (requestedTable == HomeFragmentTwo.CP_TABLE_REQUEST) {
            Intent intent = new Intent(this, CompletedDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_SOPH);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SkillTypeActivity.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_SOPH);
            startActivity(intent);
        }
    }

    public void juniorClicked(View view) {
        if (requestedTable == HomeFragmentTwo.IP_TABLE_REQUEST) {
            Intent intent = new Intent(this, InProgressDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_JUNIOR);
            startActivity(intent);
        } else if (requestedTable == HomeFragmentTwo.CP_TABLE_REQUEST) {
            Intent intent = new Intent(this, CompletedDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_JUNIOR);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SkillTypeActivity.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_JUNIOR);
            startActivity(intent);
        }
    }

    public void seniorClicked(View view) {
        if (requestedTable == HomeFragmentTwo.IP_TABLE_REQUEST) {
            Intent intent = new Intent(this, InProgressDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_SENIOR);
            startActivity(intent);
        } else if (requestedTable == HomeFragmentTwo.CP_TABLE_REQUEST) {
            Intent intent = new Intent(this, CompletedDrills.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_SENIOR);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SkillTypeActivity.class);
            intent.putExtra(SKILL_LEVEL, Drill.SKILL_LEVEL_SENIOR);
            startActivity(intent);
        }
    }
}
