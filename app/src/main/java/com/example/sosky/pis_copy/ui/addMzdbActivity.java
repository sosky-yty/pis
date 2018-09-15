package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpPovertyBean;
import com.example.sosky.pis_copy.bean.UpVeryStrickenBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;

import java.util.Map;

public class addMzdbActivity extends BaseActivity {
    private RxTitle rxTitle;
    private EditText mzdbOrdHz;
    private EditText mzdbOrdHzsfz;
    private Switch mzdbOrdXf;
    private EditText mzdbOrdXsnf;
    private EditText mzdbOrdDblb;
    private EditText mzdbOrdXsje;
    private EditText mzdbOrdBz;
    private Button btnSaveClxx;
    private String mID;
    private UpVeryStrickenBean.InfoBean mInfoBean = new UpVeryStrickenBean.InfoBean();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("local".equals(getIntent().getStringExtra("action"))) {
            mID = getIntent().getStringExtra("id");
            loadDatas();
        }
    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> veryStrickenMap = SaveTool.getVeryStricken();
            String json = veryStrickenMap.get(mID);
            UpVeryStrickenBean upVeryStrickenBean = new Gson().fromJson(json, UpVeryStrickenBean.class);
            mInfoBean = upVeryStrickenBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        mzdbOrdHz = (EditText) findViewById(R.id.mzdb_ord_hz);
        mzdbOrdHzsfz = (EditText) findViewById(R.id.mzdb_ord_hzsfz);
        mzdbOrdXf = (Switch) findViewById(R.id.mzdb_ord_xf);
        mzdbOrdXsnf = (EditText) findViewById(R.id.mzdb_ord_xsnf);
        mzdbOrdDblb = (EditText) findViewById(R.id.mzdb_ord_dblb);
        mzdbOrdXsje = (EditText) findViewById(R.id.mzdb_ord_xsje);
        mzdbOrdBz = (EditText) findViewById(R.id.mzdb_ord_bz);
        btnSaveClxx = (Button) findViewById(R.id.btn_save_clxx);

    }

    @Override
    protected void bindListener() {







    }

    @Override
    protected void inputDatas() {
         mzdbOrdHz   .setText(mInfoBean.getOrd2_bz());
         mzdbOrdHzsfz.setText(mInfoBean.getOrd2_bz());
         mzdbOrdXf   .setText(mInfoBean.getOrd2_bz());
         mzdbOrdXsnf .setText(mInfoBean.getOrd2_bz());
         mzdbOrdDblb .setText(mInfoBean.getOrd2_bz());
         mzdbOrdXsje .setText(mInfoBean.getOrd2_bz());
         mzdbOrdBz   .setText(mInfoBean.getOrd2_bz());
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_mzdb;
    }
}
