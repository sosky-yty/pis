package com.example.sosky.pis_copy.ui.fg;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sosky.pis_copy.ApiManger;
import com.example.sosky.pis_copy.MyApp;
import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.ui.LoginActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxLogTool;

import java.util.Map;

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

        btnSync.setOnClickListener(view -> {
            StringBuffer json = new StringBuffer();
            Map<String,String> PersonsMap  =SaveTool.getPerson();
            for (Map.Entry<String, String> entry : PersonsMap.entrySet()) {
                    json.append(entry.getValue());
            }
            String xml = MyTools.JSON2xml(json.toString());
            //RxLogTool.e(xml);
            ApiManger.upKeyPerson(xml, new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    RxLogTool.e(response.code());
                    RxLogTool.e(response.body());
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);

                }
            });
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
        btnRelogin = activity.findViewById(R.id.btn_relogin);
        btnDownload = activity.findViewById(R.id.btn_download);
        btnSync = activity.findViewById(R.id.btn_sync);
        tvPan = activity.findViewById(R.id.tv_pan);
    }

}
