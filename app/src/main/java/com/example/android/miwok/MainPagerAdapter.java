package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    final int TAB_COUNT = 4; // number of tabs on our main page
    private String tabTitles[] = new String[] {"Numbers", "Family", "Colours", "Phrases"};

    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int position){
        if (position == 0){
            return new NumbersFragment();
        }
        else if (position == 1){
            return new FamilyFragment();
        }
        else if (position == 2){
            return new ColoursFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount(){
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position){
        // Generate title based on item position
        return tabTitles[position];
    }
}
