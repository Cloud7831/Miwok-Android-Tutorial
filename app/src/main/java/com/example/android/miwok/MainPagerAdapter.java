package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    final int TAB_COUNT = 4; // number of tabs on our main page
    private Context context;

    public MainPagerAdapter(FragmentManager fm, Context c){
        super(fm);
        context = c;
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
        if (position == 0){
            return context.getString(R.string.category_numbers);
        }
        else if (position == 1){
            return context.getString(R.string.category_family);
        }
        else if (position == 2){
            return context.getString(R.string.category_colours);
        }
        else {
            return context.getString(R.string.category_phrases);
        }
    }
}
