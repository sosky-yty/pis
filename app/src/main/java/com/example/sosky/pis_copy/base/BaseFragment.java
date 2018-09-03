package com.example.sosky.pis_copy.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected ProgressDialog progressDialog;

    private boolean hasCreateView;

    private boolean isFragmentVisiable;

    private View view;

    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(getContentID(),container,false);
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();

        if(viewGroup != null){
            viewGroup.removeView(view);
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if (view ==null){
            return;
        }
        hasCreateView = true;
        if(isVisibleToUser){
            onFragmentVisiableChange(true);
            isFragmentVisiable = true;
        }else{
            onFragmentVisiableChange(false);
            isFragmentVisiable = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        initVariable();
    }

    @Override
    public void onViewCreated(View view , @Nullable Bundle saveInstanceState){
        initView(view);
        BindListener();
        if (!hasCreateView && getUserVisibleHint()){
            onFragmentVisiableChange(true);
            isFragmentVisiable = true;
        }
    }

    /**
     * 显示进度框
     * @param message
     */
    public void showProgressDialog(int message){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this.getActivity());
        }
        progressDialog.setMessage((getString(message)));
        progressDialog.show();
    }

    /**
     * 关闭进度框
     */
    public void dismissProgressDialog(){
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisiable = false;
    }



    /**
     * 绑定监听器
     */
    protected void BindListener() {
    }

    /**
     * 初始化控件
     * @param view 填充view
     */
    protected void initView(View view) {
    }

    /**
     * 如果当前fragment是第一次加载,等待view创建完成后回调,可以在其中进行数据加载和对控件的操作
     * @param isVisiable 当前fragment可见状态
     */
    protected void onFragmentVisiableChange(boolean isVisiable) {
    }

    /**
     *
     * @return 返回布局文件资源id
     */
    protected abstract int getContentID();

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     *
     * @return 返回键
     */
    public boolean onBackePressed(){
        return false;
    }

}
