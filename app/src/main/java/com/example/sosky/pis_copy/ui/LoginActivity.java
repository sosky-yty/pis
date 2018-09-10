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

import com.example.sosky.pis_copy.MyApp;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.view.RxToast;

public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView user;
    private EditText password;
    private AppCompatButton buttonLogin;
    private AppCompatButton buttonOffline;

    private AutoCompleteTextView address;
    private AppCompatButton buttonSave;
    private AppCompatButton buttonSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (MyApp.isLogin()) {
            toMain();
        }
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.button_login);
        buttonOffline = findViewById(R.id.button_offline);

        address = findViewById(R.id.address);
        buttonSave = findViewById(R.id.button_save);
        buttonSet = findViewById(R.id.button_set);

//        address.setText(ApiManager.DOMAIN);
//
//        buttonSave.setOnClickListener(view -> {
//
//            String string = address.getText().toString();
//            new SPHelper(App.getContext(), "server").put("address", string);
//            RxToast.normal("成功");
//
//        });
//        buttonSet.setOnClickListener(view -> {
//            address.setText("http://ebs.flobit.cn");
//            new SPHelper(App.getContext(), "server").put("address", "http://ebs.flobit.cn");
//        });
//
//        buttonOffline.setOnClickListener(view -> toMain());
//        buttonLogin.setOnClickListener(view -> login(user.getText().toString(), password.getText().toString()));
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
