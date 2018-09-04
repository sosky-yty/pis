package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sosky.pis_copy.ui.fg.FamilyFragment;
import com.example.sosky.pis_copy.ui.fg.PersonFragment;
import com.example.sosky.pis_copy.ui.fg.MineFragment;

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
            case 0: return new PersonFragment();
            case 1: return new FamilyFragment();
            case 2: return new MineFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
