package com.example.sosky.pis_copy.ui.fg;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sosky.pis_copy.MyApp;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.ui.LoginActivity;
import com.vondear.rxtools.RxActivityTool;

public class MineFragment extends BaseFragment {
    private Button btnRelogin;
    private Button btnDownload;
    private Button btnSync;
    private TextView tvPan;

    @Override
    protected int getContentID() {
        return R.layout.fg_mine_main;
    }

    @Override
    public boolean onBackePressed() {
        return false;

    }

    /**
     * 绑定监听器
     */
    @Override
    protected void BindListener() {
        btnRelogin.setOnClickListener(view -> {
            MyApp.removeCookie();
            MyApp.removePassword();
            RxActivityTool.skipActivityAndFinish(getActivity(), LoginActivity.class);
        });
    }

    /**
     * 初始化控件
     * @param view 填充view
     */
    @Override
    protected void initView(View view) {
        super.initView(view);
        FragmentActivity activity =getActivity();
        btnRelogin = (Button) activity.findViewById(R.id.btn_relogin);
        btnDownload = (Button) activity.findViewById(R.id.btn_download);
        btnSync = (Button) activity.findViewById(R.id.btn_sync);
        tvPan = (TextView) activity.findViewById(R.id.tv_pan);
    }
}
