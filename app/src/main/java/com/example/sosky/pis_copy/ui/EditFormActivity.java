package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;

public class EditFormActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layouID = R.layout.activity_addkeyperson;

        //加载不同布局
        if ("xumu".equals(getIntent().getStringExtra("action"))) {
            layouID = R.layout.activity_addkeyfamily;
        }
        setContentView(R.layout.activity_editperson);

    }
}
