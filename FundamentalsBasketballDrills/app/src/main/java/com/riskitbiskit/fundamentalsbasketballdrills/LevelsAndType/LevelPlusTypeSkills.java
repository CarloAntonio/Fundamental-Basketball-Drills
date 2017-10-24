package com.riskitbiskit.fundamentalsbasketballdrills.LevelsAndType;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.riskitbiskit.fundamentalsbasketballdrills.Drills.Drill;
import com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.riskitbiskit.fundamentalsbasketballdrills.SpecificDrill;
import com.squareup.picasso.Picasso;

import static com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract.*;

public class LevelPlusTypeSkills extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int LEVEL_TYPE_LOADER = 0;

    private LevelPlusTypeCursorAdapter mLevelPlusTypeCursorAdapter;

    //Use this to know what to display, currently displaying all d
    int skillLevel;
    int skillType;
    Uri skillTypeAndLevelUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_plus_type_skills);

        final Intent intent = getIntent();
        skillLevel = intent.getIntExtra(LevelsActivity.SKILL_LEVEL, Drill.SKILL_LEVEL_FROSH);
        skillType = intent.getIntExtra(SkillTypeActivity.SKILL_TYPE, Drill.SKILL_TYPE_DRIBBLE);

        ImageView imageView = (ImageView) findViewById(R.id.background_image_holder_lpt);

        Picasso.with(this).load(R.drawable.hoop_large).into(imageView);

        getSkillLevelAndType();

        GridView gridView = (GridView) findViewById(R.id.level_plus_type_gridview);

        mLevelPlusTypeCursorAdapter = new LevelPlusTypeCursorAdapter(this, null);

        gridView.setAdapter(mLevelPlusTypeCursorAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (skillLevel == Drill.SKILL_LEVEL_FROSH) {
                    Intent intentSpecificDrill = new Intent(getBaseContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillListEntry.FROSH_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else if (skillLevel == Drill.SKILL_LEVEL_SOPH) {
                    Intent intentSpecificDrill = new Intent(getBaseContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillListEntry.SOPH_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else if (skillLevel == Drill.SKILL_LEVEL_JUNIOR) {
                    Intent intentSpecificDrill = new Intent(getBaseContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillListEntry.JUNIOR_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else if (skillLevel == Drill.SKILL_LEVEL_SENIOR) {
                    Intent intentSpecificDrill = new Intent(getBaseContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillListEntry.SENIOR_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else {
                    Intent intentSpecificDrill = new Intent(getBaseContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillListEntry.FROSH_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                }
            }
        });

        getLoaderManager().initLoader(LEVEL_TYPE_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                DrillListEntry._ID,
                DrillListEntry.SKILL_NAME,
                DrillListEntry.SKILL_COMPLETION
        };

        return new CursorLoader(this,
                skillTypeAndLevelUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mLevelPlusTypeCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mLevelPlusTypeCursorAdapter.swapCursor(null);
    }

    private void getSkillLevelAndType() {
        if (skillLevel == Drill.SKILL_LEVEL_FROSH && skillType == Drill.SKILL_TYPE_DRIBBLE) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.FROSH_AND_DRIB);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_SOPH && skillType == Drill.SKILL_TYPE_DRIBBLE) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.SOPH_AND_DRIB);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_JUNIOR && skillType == Drill.SKILL_TYPE_DRIBBLE) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.JUNIOR_AND_DRIB);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_SENIOR && skillType == Drill.SKILL_TYPE_DRIBBLE) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.SENIOR_AND_DRIB);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_FROSH && skillType == Drill.SKILL_TYPE_PASS) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.FROSH_AND_PASS);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_SOPH && skillType == Drill.SKILL_TYPE_PASS) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.SOPH_AND_PASS);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_JUNIOR && skillType == Drill.SKILL_TYPE_PASS) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.JUNIOR_AND_PASS);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_SENIOR && skillType == Drill.SKILL_TYPE_PASS) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.SENIOR_AND_PASS);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_FROSH && skillType == Drill.SKILL_TYPE_SHOOT) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.FROSH_AND_SHOOT);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_SOPH && skillType == Drill.SKILL_TYPE_SHOOT) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.SOPH_AND_SHOOT);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_JUNIOR && skillType == Drill.SKILL_TYPE_SHOOT) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.JUNIOR_AND_SHOOT);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        } else if (skillLevel == Drill.SKILL_LEVEL_SENIOR && skillType == Drill.SKILL_TYPE_SHOOT) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillListEntry.SENIOR_AND_SHOOT);
            String testUri = builder.toString();
            skillTypeAndLevelUri = Uri.parse(testUri);
        };
    }
}
