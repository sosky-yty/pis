package com.example.sosky.pis_copy.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.vondear.rxtools.RxActivityTool;

public abstract class BaseActivity extends AppCompatActivity {

    protected android.support.v4.app.FragmentManager fragmentManager;
    protected Fragment showFragment;
    public Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横向转换,防止生命周期改变
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        mContext = this;
        this.setMyTheme();
        this.setContentView(this.getContentID());
        RxActivityTool.addActivity(this);
        fragmentManager = getSupportFragmentManager();

        this.initView();
        this.bindView();
        this.bindListener();
        this.loadDatas();
    }

    /**
     *  加载view数据
     */
    protected  void loadDatas(){};

    /**
     * 绑定view
     */
    protected  void bindView(){};
    /**
     * 绑定view
     */
    protected  void bindListener(){};
    

    /**
     * 初始话view
     */
    protected  void initView(){};

    protected abstract int getContentID();

    /**
     * 设置主题颜色
     */
    protected  void setMyTheme(){};

    /**
     * 显示fragment
     */
    public void ShowFragment(int fragmentID, Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (showFragment != null){
            fragmentTransaction.hide(showFragment);
        }
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        if(fragmentByTag != null){
            fragmentTransaction.show(showFragment);
        }else{
            fragmentByTag = fragment;
            fragmentTransaction.add(fragmentID,fragmentByTag,fragmentByTag.getClass().getName());
        }
        fragmentTransaction.commit();
        showFragment = fragmentByTag;
    }
}
