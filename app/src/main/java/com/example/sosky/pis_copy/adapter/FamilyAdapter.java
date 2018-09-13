package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sosky.pis_copy.ui.fg.ServerfamilyFragment;
import com.example.sosky.pis_copy.ui.fg.localFamilyFragment;

public class FamilyAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] list_title = {"已上传","待上传"};


    public FamilyAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new ServerfamilyFragment();
            case 1: return new localFamilyFragment();
            default:
                return new ServerfamilyFragment();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * 显示ｔａｂｌａｙｏｕ名字
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title[position];
    }
}
