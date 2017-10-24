package com.riskitbiskit.fundamentalsbasketballdrills.InProgressDrills;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.riskitbiskit.fundamentalsbasketballdrills.Drills.Drill;
import com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract;
import com.riskitbiskit.fundamentalsbasketballdrills.LevelsAndType.LevelPlusTypeCursorAdapter;
import com.riskitbiskit.fundamentalsbasketballdrills.LevelsAndType.LevelsActivity;
import com.riskitbiskit.fundamentalsbasketballdrills.R;
import com.riskitbiskit.fundamentalsbasketballdrills.SpecificDrill;
import com.squareup.picasso.Picasso;


public class InProgDribblingFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int DRIBBLING_LOADER = 1;

    public InProgDribblingFragment() {};

    private LevelPlusTypeCursorAdapter testLevelPlusTypeCursorAdapter;
    private Uri selectorUri;
    private int requestedLevel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_ip_comp, container, false);

        Intent intent = getActivity().getIntent();
        requestedLevel = intent.getIntExtra(LevelsActivity.SKILL_LEVEL, Drill.SKILL_LEVEL_FROSH);

        GridView mGridView = (GridView) rootView.findViewById(R.id.frag_ip_comp_gridview);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.background_image_holder_frag_ip);

        Picasso.with(getContext()).load(R.drawable.puddlebounce_large).into(imageView);

        testLevelPlusTypeCursorAdapter = new LevelPlusTypeCursorAdapter(getContext(), null);

        mGridView.setAdapter(testLevelPlusTypeCursorAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (requestedLevel == Drill.SKILL_LEVEL_FROSH) {
                    Intent intentSpecificDrill = new Intent(getContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillContract.DrillListEntry.FROSH_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else if (requestedLevel == Drill.SKILL_LEVEL_SOPH) {
                    Intent intentSpecificDrill = new Intent(getContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillContract.DrillListEntry.SOPH_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else if (requestedLevel == Drill.SKILL_LEVEL_JUNIOR) {
                    Intent intentSpecificDrill = new Intent(getContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillContract.DrillListEntry.JUNIOR_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else if (requestedLevel == Drill.SKILL_LEVEL_SENIOR) {
                    Intent intentSpecificDrill = new Intent(getContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillContract.DrillListEntry.SENIOR_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                } else {
                    Intent intentSpecificDrill = new Intent(getContext(), SpecificDrill.class);
                    Uri currentDrillUri = ContentUris.withAppendedId(DrillContract.DrillListEntry.FROSH_CONTENT_URI, id);
                    intentSpecificDrill.setData(currentDrillUri);
                    startActivity(intentSpecificDrill);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (requestedLevel == Drill.SKILL_LEVEL_FROSH) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillContract.DrillListEntry.IP_FROSH_AND_DRIB);
            String testUri = builder.toString();
            selectorUri = Uri.parse(testUri);
        } else if (requestedLevel == Drill.SKILL_LEVEL_SOPH) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillContract.DrillListEntry.IP_SOPH_AND_DRIB);
            String testUri = builder.toString();
            selectorUri = Uri.parse(testUri);
        } else if (requestedLevel == Drill.SKILL_LEVEL_JUNIOR) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillContract.DrillListEntry.IP_JUNIOR_AND_DRIB);
            String testUri = builder.toString();
            selectorUri = Uri.parse(testUri);
        } else if (requestedLevel == Drill.SKILL_LEVEL_SENIOR) {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillContract.DrillListEntry.IP_SENIOR_AND_DRIB);
            String testUri = builder.toString();
            selectorUri = Uri.parse(testUri);
        } else {
            Uri.Builder builder = DrillContract.DrillListEntry.CONTENT_URI.buildUpon().appendPath(DrillContract.DrillListEntry.IP_FROSH_AND_DRIB);
            String testUri = builder.toString();
            selectorUri = Uri.parse(testUri);
        }

        getLoaderManager().initLoader(DRIBBLING_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //NOTE: projection, selection, selection args not needed. Raw query performed in provider.

        return new android.support.v4.content.CursorLoader(getContext(),
                selectorUri,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        testLevelPlusTypeCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
