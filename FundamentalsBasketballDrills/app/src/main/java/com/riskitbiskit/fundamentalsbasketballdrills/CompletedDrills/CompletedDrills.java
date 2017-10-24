package com.riskitbiskit.fundamentalsbasketballdrills.CompletedDrills;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.riskitbiskit.fundamentalsbasketballdrills.InProgressDrills.InProgressFragmentPagerAdapter;
import com.riskitbiskit.fundamentalsbasketballdrills.R;

public class CompletedDrills extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_drills);

        ViewPager completedViewPager = (ViewPager) findViewById(R.id.viewpager_completed);

        CompletedFragmentPagerAdapter completedFragmentPagerAdapter = new CompletedFragmentPagerAdapter(getSupportFragmentManager());

        completedViewPager.setAdapter(completedFragmentPagerAdapter);

        TabLayout completedTabLayout = (TabLayout) findViewById(R.id.sliding_tabs_completed);

        completedTabLayout.setupWithViewPager(completedViewPager);
    }
}
