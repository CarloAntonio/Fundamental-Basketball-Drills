package com.riskitbiskit.fundamentalsbasketballdrills.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragmentOne();
        } else {
            return new HomeFragmentTwo();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "I";
        } else {
            return "II";
        }
    }
}
