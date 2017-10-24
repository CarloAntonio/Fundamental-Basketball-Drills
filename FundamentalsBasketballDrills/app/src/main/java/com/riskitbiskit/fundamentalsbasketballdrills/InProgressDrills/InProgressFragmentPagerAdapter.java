package com.riskitbiskit.fundamentalsbasketballdrills.InProgressDrills;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class InProgressFragmentPagerAdapter extends FragmentPagerAdapter {
    public InProgressFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new InProgDribblingFragment();
        } else if (position == 1){
            return new InProgPassingFragment();
        } else {
            return new InProgShootingFragment();
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
        } else if (position == 1){
            return "Passing";
        } else {
            return "Shooting";
        }
    }
}
