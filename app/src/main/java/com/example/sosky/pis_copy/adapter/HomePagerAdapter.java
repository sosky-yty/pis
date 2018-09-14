package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.sosky.pis_copy.ui.fg.MineFragment;
import com.example.sosky.pis_copy.ui.fg.localFamilyFragment;
import com.example.sosky.pis_copy.ui.fg.localPersonFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private Context context;

    public HomePagerAdapter(Context context ,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new localPersonFragment();
            case 1: return new localFamilyFragment();
            case 2: return new MineFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
