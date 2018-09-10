package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.sosky.pis_copy.ApiManger;
import com.example.sosky.pis_copy.MyApp;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SPHelper;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

public class LoginActivity extends BaseActivity {

    private RxTitle rxTitle;
    private AutoCompleteTextView user;
    private EditText password;
    private AppCompatButton buttonLogin;
    private AppCompatButton buttonOffline;
    private AutoCompleteTextView address;
    private AppCompatButton buttonSave;
    private AppCompatButton buttonSet;

    @Override
    protected void bindView() {
        super.bindView();
    }

    @Override
    protected void initView() {
        super.initView();

        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        user = (AutoCompleteTextView) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        buttonLogin = (AppCompatButton) findViewById(R.id.button_login);
        buttonOffline = (AppCompatButton) findViewById(R.id.button_offline);
        address = (AutoCompleteTextView) findViewById(R.id.address);
        buttonSave = (AppCompatButton) findViewById(R.id.button_save);
        buttonSet = (AppCompatButton) findViewById(R.id.button_set);

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
            address.setText("http://ebs.flobit.cn");
            new SPHelper(MyApp.getContext(), "server").put("address", "http://ebs.flobit.cn");
        });

        buttonOffline.setOnClickListener(view -> toMain());
        buttonLogin.setOnClickListener(view -> login(user.getText().toString(), password.getText().toString()));
    }

    private void login(String s, String s1) {
        RxToast.normal("绑定成功");
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
}
