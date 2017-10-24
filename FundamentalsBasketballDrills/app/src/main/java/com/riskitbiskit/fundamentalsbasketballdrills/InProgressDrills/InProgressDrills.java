package com.riskitbiskit.fundamentalsbasketballdrills.InProgressDrills;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.riskitbiskit.fundamentalsbasketballdrills.R;

public class InProgressDrills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_progress_drills);

        ViewPager inProgressViewPager = (ViewPager) findViewById(R.id.viewpager_in_progress);

        InProgressFragmentPagerAdapter inProgressFragmentPagerAdapter = new InProgressFragmentPagerAdapter(getSupportFragmentManager());

        inProgressViewPager.setAdapter(inProgressFragmentPagerAdapter);

        TabLayout inProgressTabLayout = (TabLayout) findViewById(R.id.sliding_tabs_in_progress);

        inProgressTabLayout.setupWithViewPager(inProgressViewPager);
    }
}
