package com.example.sosky.pis_copy.ui.fg;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.adapter.PersonPagerAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;

public class PersonFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int getContentID() {
        return R.layout.fg_person_main;
    }

    @Override
    protected void initView(View view) {
        FragmentActivity activity = getActivity();
        tabLayout = activity.findViewById(R.id.tablayout_person);
        viewPager = activity.findViewById(R.id.viewpager_person);

        viewPager.setAdapter(new PersonPagerAdapter(activity.getSupportFragmentManager(),activity));
        tabLayout.setupWithViewPager(viewPager);
    }
}
