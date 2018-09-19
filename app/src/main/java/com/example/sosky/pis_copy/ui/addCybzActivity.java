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
import com.example.sosky.pis_copy.bean.UpResidualBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 草原补助
 */
public class addCybzActivity extends BaseActivity {
    private RxTitle rxTitle;
    private EditText cyOrdHz;
    private EditText cyOrdHzsfz;
    private EditText cyOrdNf;
    private EditText cyOrdJe;
    private EditText cyOrdBz;
    private Button btnSave;

    private UpGrasslandBean.InfoBean mInfoBean = new UpGrasslandBean.InfoBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cyOrdHz.setText(AddFamilyMainActivity.mName);
        cyOrdHzsfz.setText(AddFamilyMainActivity.mID);
    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> grasslandMap = SaveTool.getGrassland();
            String json = grasslandMap.get(AddFamilyMainActivity.mID);
            UpGrasslandBean upGrasslandBean = new Gson().fromJson(json, UpGrasslandBean.class);
            mInfoBean = upGrasslandBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            cyOrdHz.setText(AddFamilyMainActivity.mName);
            cyOrdHzsfz.setText(AddFamilyMainActivity.mID);
            e.printStackTrace();
        }
    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);

        cyOrdHz = (EditText) findViewById(R.id.cy_ord_hz);
        cyOrdHzsfz = (EditText) findViewById(R.id.cy_ord_hzsfz);
        cyOrdNf = (EditText) findViewById(R.id.cy_ord_nf);
        cyOrdJe = (EditText) findViewById(R.id.cy_ord_je);
        cyOrdBz = (EditText) findViewById(R.id.cy_ord_bz);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    protected void bindListener() {
        cyOrdNf.setOnClickListener(v -> {
            MyTools.showDataPicker(mContext, cyOrdNf);
        });

        btnSave.setOnClickListener(view -> {
            UpGrasslandBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_hzsfz())) {
                SaveTool.saveOneGrassland(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误");
            }
        });
    }

    @Override
    protected void inputDatas() {
        cyOrdHz.setText(mInfoBean.getOrd2_hz());
        cyOrdHzsfz.setText(mInfoBean.getOrd2_hzsfz());
        cyOrdNf.setText(mInfoBean.getOrd2_nf());
        cyOrdJe.setText(mInfoBean.getOrd2_je());
        cyOrdBz.setText(mInfoBean.getOrd2_bz());
    }

    private UpGrasslandBean.InfoBean saveDatas() {
        UpGrasslandBean.InfoBean infoBean = new UpGrasslandBean.InfoBean();
        infoBean.setOrd2_hz(cyOrdHz.getText().toString());
        infoBean.setOrd2_hzsfz(cyOrdHzsfz.getText().toString());
        infoBean.setOrd2_nf(cyOrdNf.getText().toString());
        infoBean.setOrd2_je(cyOrdJe.getText().toString());
        infoBean.setOrd2_bz(cyOrdBz.getText().toString());
        infoBean.setOrd2_flag("1");
        return infoBean;
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_cybz;
    }
}
