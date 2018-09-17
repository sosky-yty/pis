package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.adapter.LocalFamilyAdapter;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpLowInsuranceBean;
import com.example.sosky.pis_copy.bean.UpPovertyBean;
import com.example.sosky.pis_copy.bean.UpVeryStrickenBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 低保
 */
public class addMzdbActivity extends BaseActivity {
    private String[] dblb = {"低保兜底", "贫困低保", "一般低保"};


    private RxTitle rxTitle;
    private EditText mzdbOrdHz;
    private EditText mzdbOrdHzsfz;
    private Switch mzdbOrdSfxsdb;
    private EditText mzdbOrdXsnf;
    private EditText mzdbOrdDblb;
    private EditText mzdbOrdXsje;
    private EditText mzdbOrdBz;
    private Button btnSaveClxx;
    private String mID;
    private UpLowInsuranceBean.InfoBean mInfoBean = new UpLowInsuranceBean.InfoBean();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mzdbOrdHz.setText(AddFamilyMainActivity.mName);
        mzdbOrdHzsfz.setText(AddFamilyMainActivity.mID);

    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> lowInsuranceMap = SaveTool.getLowInsurance();
            String json = lowInsuranceMap.get(mID);
            UpLowInsuranceBean lowInsuranceBean = new Gson().fromJson(json, UpLowInsuranceBean.class);
            mInfoBean = lowInsuranceBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            mzdbOrdHz.setText(AddFamilyMainActivity.mName);
            mzdbOrdHzsfz.setText(AddFamilyMainActivity.mID);
            e.printStackTrace();
        }
    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        mzdbOrdHz = (EditText) findViewById(R.id.mzdb_ord_hz);
        mzdbOrdHzsfz = (EditText) findViewById(R.id.mzdb_ord_hzsfz);
        mzdbOrdSfxsdb = (Switch) findViewById(R.id.mzdb_ord_sfxsdb);
        mzdbOrdXsnf = (EditText) findViewById(R.id.mzdb_ord_xsnf);
        mzdbOrdDblb = (EditText) findViewById(R.id.mzdb_ord_dblb);
        mzdbOrdXsje = (EditText) findViewById(R.id.mzdb_ord_xsje);
        mzdbOrdBz = (EditText) findViewById(R.id.mzdb_ord_bz);
        btnSaveClxx = (Button) findViewById(R.id.btn_save_clxx);


    }

    @Override
    protected void bindListener() {
        mzdbOrdDblb.setOnClickListener(view -> {
            MyTools.showSelectDialog(dblb, mContext, mzdbOrdDblb);
        });

        btnSaveClxx.setOnClickListener(view -> {
            UpLowInsuranceBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_hzsfz())) {
                SaveTool.saveOneLowInsurance(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误,无法保存");
            }
        });

    }

    @Override
    protected void inputDatas() {
        mzdbOrdHz.setText(mInfoBean.getOrd2_bz());
        mzdbOrdHzsfz.setText(mInfoBean.getOrd2_bz());
        mzdbOrdXsnf.setText(mInfoBean.getOrd2_bz());
        mzdbOrdDblb.setText(mInfoBean.getOrd2_bz());
        mzdbOrdXsje.setText(mInfoBean.getOrd2_bz());
        mzdbOrdBz.setText(mInfoBean.getOrd2_bz());
        mzdbOrdSfxsdb.setChecked(mInfoBean.getOrd2_sfxsdb().equals("是"));
    }


    @Override
    protected int getContentID() {
        return R.layout.activity_form_mzdb;
    }


    private UpLowInsuranceBean.InfoBean saveDatas() {
        UpLowInsuranceBean.InfoBean infoBean = new UpLowInsuranceBean.InfoBean();
        infoBean.setOrd2_hz(mzdbOrdHz.getText().toString());
        infoBean.setOrd2_hzsfz(mzdbOrdHzsfz.getText().toString());
        infoBean.setOrd2_xsnf(mzdbOrdXsnf.getText().toString());
        infoBean.setOrd2_dblb(mzdbOrdDblb.getText().toString());
        infoBean.setOrd2_xsje(mzdbOrdXsje.getText().toString());
        infoBean.setOrd2_bz(mzdbOrdBz.getText().toString());
        infoBean.setOrd2_sfxsdb(mzdbOrdSfxsdb.isChecked() ? "是" : "否");
        return infoBean;
    }
}
