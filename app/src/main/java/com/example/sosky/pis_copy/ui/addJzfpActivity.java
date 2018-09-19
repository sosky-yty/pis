package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpGrasslandBean;
import com.example.sosky.pis_copy.bean.UpPovertyBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 精准扶贫
 */
public class addJzfpActivity extends BaseActivity {
    private RxTitle rxTitle;
    private EditText jzOrdHz;
    private EditText jzOrdHzsfz;
    private EditText jzOrdBfzrr;
    private EditText jzOrdBfzrrszdw;
    private EditText jzOrdBfzrrlxfs;
    private EditText jzOrdZcgzdyxm;
    private EditText jzOrdDysj;
    private EditText jzOrdBz;
    private Button btnSave;
    private UpPovertyBean.InfoBean mInfoBean = new UpPovertyBean.InfoBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jzOrdHz.setText(AddFamilyMainActivity.mName);
        jzOrdHzsfz.setText(AddFamilyMainActivity.mID);
    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> povertyMap = SaveTool.getPoverty();
            String json = povertyMap.get(AddFamilyMainActivity.mID);
            UpPovertyBean upPovertyBean = new Gson().fromJson(json, UpPovertyBean.class);
            mInfoBean = upPovertyBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            jzOrdHz.setText(AddFamilyMainActivity.mName);
            jzOrdHzsfz.setText(AddFamilyMainActivity.mID);
            e.printStackTrace();
        }
    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        jzOrdHz = (EditText) findViewById(R.id.jz_ord_hz);
        jzOrdHzsfz = (EditText) findViewById(R.id.jz_ord_hzsfz);
        jzOrdBfzrr = (EditText) findViewById(R.id.jz_ord_bfzrr);
        jzOrdBfzrrszdw = (EditText) findViewById(R.id.jz_ord_bfzrrszdw);
        jzOrdBfzrrlxfs = (EditText) findViewById(R.id.jz_ord_bfzrrlxfs);
        jzOrdZcgzdyxm = (EditText) findViewById(R.id.jz_ord_zcgzdyxm);
        jzOrdDysj = (EditText) findViewById(R.id.jz_ord_dysj);
        jzOrdBz = (EditText) findViewById(R.id.jz_ord_bz);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    protected void bindListener() {
        btnSave.setOnClickListener(view -> {
            UpPovertyBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_hzsfz())) {
                SaveTool.saveOnePoverty(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误,无法保存");
            }
        });
    }

    @Override
    protected void inputDatas() {
        jzOrdHz.setText(mInfoBean.getOrd2_hz());
        jzOrdHzsfz.setText(mInfoBean.getOrd2_hzsfz());
        jzOrdBfzrr.setText(mInfoBean.getOrd2_bfzrr());
        jzOrdBfzrrszdw.setText(mInfoBean.getOrd2_bfzrrszdw());
        jzOrdBfzrrlxfs.setText(mInfoBean.getOrd2_bfzrrlxfs());
        jzOrdZcgzdyxm.setText(mInfoBean.getOrd2_zcgzdyxm());
        jzOrdDysj.setText(mInfoBean.getOrd2_dysj());
        jzOrdBz.setText(mInfoBean.getOrd2_bz());
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_jzfp;
    }

    private UpPovertyBean.InfoBean saveDatas() {
        UpPovertyBean.InfoBean infoBean = new UpPovertyBean.InfoBean();

        infoBean.setOrd2_hz(jzOrdHz.getText().toString());
        infoBean.setOrd2_hzsfz(jzOrdHzsfz.getText().toString());
        infoBean.setOrd2_bfzrr(jzOrdBfzrr.getText().toString());
        infoBean.setOrd2_bfzrrszdw(jzOrdBfzrrszdw.getText().toString());
        infoBean.setOrd2_bfzrrlxfs(jzOrdBfzrrlxfs.getText().toString());
        infoBean.setOrd2_zcgzdyxm(jzOrdZcgzdyxm.getText().toString());
        infoBean.setOrd2_dysj(jzOrdDysj.getText().toString());
        infoBean.setOrd2_bz(jzOrdBz.getText().toString());
        infoBean.setOrd2_flag("1");
        return infoBean;
    }


}
