package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.sosky.pis_copy.ApiManger;
import com.example.sosky.pis_copy.MyApp;
import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SPHelper;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxDeviceTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.List;

public class LoginActivity extends BaseActivity {

    private RxTitle rxTitle;
    private AutoCompleteTextView user;
    private EditText password;
    private AppCompatButton buttonLogin;
    private AppCompatButton buttonOffline;
    private AutoCompleteTextView address;
    private AppCompatButton buttonSave;
    private AppCompatButton buttonSet;
    private List<String> mPermission;

    @Override
    protected void bindView() {
        super.bindView();
    }

    @Override
    protected void initView() {
        super.initView();

        rxTitle = findViewById(R.id.rx_title);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);
        buttonOffline = findViewById(R.id.button_offline);
        address = findViewById(R.id.address);
        buttonSave = findViewById(R.id.button_save);
        buttonSet = findViewById(R.id.button_set);

        mPermission = MyTools.requestPermissions(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (MyApp.isLogin()) {
            toMain();
        }

        address.setText(ApiManger.DOMAIN);

        buttonSave.setOnClickListener(view -> {

            String string = address.getText().toString();
            new SPHelper(MyApp.getContext(), "server").put("address", string);
            RxToast.normal("成功");

        });
        buttonSet.setOnClickListener(view -> {
            address.setText("http://nat.flobit.cn:94");
            new SPHelper(MyApp.getContext(), "server").put("address", "http://nat.flobit.cn:94");
        });

        buttonOffline.setOnClickListener(view -> toMain());
        buttonLogin.setOnClickListener(view -> login(user.getText().toString(), password.getText().toString()));
    }

    private void login(String u, String p) {
        RxToast.normal("登录中 请稍候...");

        ApiManger.login(u, p, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.body() == null) {
                    RxToast.error("服务器错误");
                    return;
                }
                if (response.body().contains("ok")) {
                    RxToast.success("登录成功");
                    savepass(u, p);
                    toMain();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                RxToast.error("网络错误");
            }
        });
    }

    private void savepass(String u, String p) {
        MyApp.SPE.putString("username", u).apply();
        MyApp.SPE.putString("password", p).apply();
        MyApp.SPE.putBoolean("isLogin", true).apply();

    }

    /**
     * 跳转到主页
     */
    private void toMain() {
        RxActivityTool.skipActivityAndFinish(LoginActivity.this, MainActivity.class);

    }

    @Override
    protected int getContentID() {
        return R.layout.activity_login;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isGetAllPermission = true;
        for (String s : mPermission) {
            if (!RxDeviceTool.checkPermission(this, s)) {
                isGetAllPermission = false;
            }
        }
        if (!isGetAllPermission) {
            final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(LoginActivity.this);//提示弹窗
            rxDialogSureCancel.getTitleView().setText("重要提示");
            rxDialogSureCancel.setContent("部分权限未获得\n可能导致APP部分功能异常\n重新请求权限?");
            rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPermission = MyTools.requestPermissions(LoginActivity.this);
                    rxDialogSureCancel.cancel();
                }
            });
            rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rxDialogSureCancel.cancel();
                }
            });
            rxDialogSureCancel.show();
        }
    }

}
