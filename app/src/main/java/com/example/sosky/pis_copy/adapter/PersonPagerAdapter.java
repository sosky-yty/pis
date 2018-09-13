package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sosky.pis_copy.ui.fg.ServerPersonFragment;
import com.example.sosky.pis_copy.ui.fg.localPersonFragment;

public class PersonPagerAdapter extends FragmentPagerAdapter {
    private String[] list_title ={"已上传","待上传"};
    private Context context;

    public PersonPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ServerPersonFragment();
            case 1:
                return new localPersonFragment();
            default:
                return new ServerPersonFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title[position];
    }
}
