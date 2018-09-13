package com.example.sosky.pis_copy.ui.fg;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.TabLayout;


import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.adapter.FamilyAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;

public class FamilyFragment extends BaseFragment {
    private TabLayout tableLayout;
    private ViewPager viewPager;

    @Override
    protected int getContentID() {
        return R.layout.fg_family_main;
    }

    @Override
    protected void initView(View view) {
        FragmentActivity activity = getActivity();
        tableLayout = activity.findViewById(R.id.tablayout_family);
        viewPager = activity.findViewById(R.id.viewpager_family);

        viewPager.setAdapter(new FamilyAdapter(activity.getSupportFragmentManager(),activity));
        tableLayout.setupWithViewPager(viewPager);
    }
}
