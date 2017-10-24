package com.riskitbiskit.fundamentalsbasketballdrills.LevelsAndType;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.riskitbiskit.fundamentalsbasketballdrills.Drills.Drill;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SkillTypeActivity extends AppCompatActivity {

    public static final String SKILL_TYPE = "skillType";

    int skillLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_type);

        Intent intent = getIntent();
        skillLevel = intent.getIntExtra(LevelsActivity.SKILL_LEVEL, Drill.SKILL_LEVEL_FROSH);

        ImageView dribblingImageView = (ImageView) findViewById(R.id.dribbling_iv);
        ImageView passingImageView = (ImageView) findViewById(R.id.passing_iv);
        ImageView shootingImageView = (ImageView) findViewById(R.id.shooting_iv);

        TextView dribblingTextView = (TextView) findViewById(R.id.dribbling_tv);
        TextView passingTextView = (TextView) findViewById(R.id.passing_tv);
        TextView shootingTextView = (TextView) findViewById(R.id.shooting_tv);

        Picasso.with(this).load(R.drawable.bballshadow).into(dribblingImageView);
        Picasso.with(this).load(R.drawable.hoop).into(passingImageView);
        Picasso.with(this).load(R.drawable.jumpshot).into(shootingImageView);

        AssetManager assetManager = getAssets();

        Typeface typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "fonts/%s", "Roboto-Bold.ttf"));

        dribblingTextView.setTypeface(typeface);
        passingTextView.setTypeface(typeface);
        shootingTextView.setTypeface(typeface);
    }

    public void dribblingClicked(View view) {
        Intent intent = new Intent(this, LevelPlusTypeSkills.class);
        intent.putExtra(LevelsActivity.SKILL_LEVEL, skillLevel);
        intent.putExtra(SKILL_TYPE, Drill.SKILL_TYPE_DRIBBLE);
        startActivity(intent);
    }

    public void passingClicked(View view) {
        Intent intent = new Intent(this, LevelPlusTypeSkills.class);
        intent.putExtra(LevelsActivity.SKILL_LEVEL, skillLevel);
        intent.putExtra(SKILL_TYPE, Drill.SKILL_TYPE_PASS);
        startActivity(intent);
    }

    public void shootingClicked(View view) {
        Intent intent = new Intent(this, LevelPlusTypeSkills.class);
        intent.putExtra(LevelsActivity.SKILL_LEVEL, skillLevel);
        intent.putExtra(SKILL_TYPE, Drill.SKILL_TYPE_SHOOT);
        startActivity(intent);
    }
}
