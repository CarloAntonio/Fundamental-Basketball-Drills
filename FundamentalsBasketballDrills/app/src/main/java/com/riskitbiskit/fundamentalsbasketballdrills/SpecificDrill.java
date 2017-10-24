package com.riskitbiskit.fundamentalsbasketballdrills;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.riskitbiskit.fundamentalsbasketballdrills.Drills.DatabaseAccess;
import com.riskitbiskit.fundamentalsbasketballdrills.Drills.Drill;
import com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract.*;

public class SpecificDrill extends YouTubeBaseActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String API_KEY = "AIzaSyAZVQjXua-ev6um4mhcyDdZ9l8x2lFrmII";

    public static final int SPECIFIC_DRILL_LOADER = 0;

    Button playButton;
    private Uri currentDrillUri;
    private YouTubePlayerView mYouTubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_drill);

        Intent intent = getIntent();
        currentDrillUri = intent.getData();

        ImageView imageView = (ImageView) findViewById(R.id.background_image_specific_drill);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_view);

        Picasso.with(this).load(R.drawable.home).into(imageView);

        getLoaderManager().initLoader(SPECIFIC_DRILL_LOADER, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                DrillListEntry._ID,
                DrillListEntry.SKILL_NAME,
                DrillListEntry.SKILL_VIDEO_ID,
                DrillListEntry.SKILL_CHALLENGE,
                DrillListEntry.SKILL_COMPLETION
        };
        return new CursorLoader(this,
                currentDrillUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data.moveToFirst()) {

            TextView textView = (TextView) findViewById(R.id.exercise_name);
            textView.setText(data.getString(data.getColumnIndex(DrillListEntry.SKILL_NAME)));

            final String specificDrillVideoId = data.getString(data.getColumnIndex(DrillListEntry.SKILL_VIDEO_ID));

            mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo(specificDrillVideoId);
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };

            playButton = (Button) findViewById(R.id.play_button);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mYouTubePlayerView.initialize(API_KEY, mOnInitializedListener);
                }
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
