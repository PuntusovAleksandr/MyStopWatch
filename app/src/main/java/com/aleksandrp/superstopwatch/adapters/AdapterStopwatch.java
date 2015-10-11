package com.aleksandrp.superstopwatch.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.aleksandrp.superstopwatch.fragments.HistoryFragment;
import com.aleksandrp.superstopwatch.fragments.StopwatchFragment;
import com.aleksandrp.superstopwatch.fragments.TimerFragment;

/**
 * Created by Aleksandr on 29.09.2015.
 */
public class AdapterStopwatch extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public AdapterStopwatch(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new StopwatchFragment();
            case 1:
                return new TimerFragment();
            case 2:
                return new HistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
