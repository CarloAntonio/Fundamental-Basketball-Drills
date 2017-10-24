package com.riskitbiskit.fundamentalsbasketballdrills.CompletedDrills;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class CompletedFragmentPagerAdapter extends FragmentPagerAdapter {
    public CompletedFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CompDribblingFragment();
        } else if (position == 1) {
            return new CompPassingFragment();
        } else {
            return new CompShootingFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Dribbling";
        } else if (position == 1) {
            return "Passing";
        } else {
            return "Shooting";
        }
    }
}
