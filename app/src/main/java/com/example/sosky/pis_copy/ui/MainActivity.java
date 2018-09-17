package com.example.sosky.pis_copy.ui;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.adapter.HomePagerAdapter;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private BottomNavigationViewEx navigation;
    private ViewPager homeViewPager;
    FragmentPagerAdapter adapter;

    @Override
    protected int getContentID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        navigation = findViewById(R.id.navigation);
        homeViewPager = findViewById(R.id.home_viewpager);

        adapter = new HomePagerAdapter(this, fragmentManager);

        homeViewPager.setAdapter(adapter);
        homeViewPager.setOffscreenPageLimit(2);

        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setCurrentItem(0);
        navigation.setupWithViewPager(homeViewPager);
    }

    ;
}
