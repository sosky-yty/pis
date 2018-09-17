package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpLowInsuranceBean;
import com.example.sosky.pis_copy.bean.UpVeryStrickenBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 民政特困
 */
public class addMztkActivity extends BaseActivity {
    private RxTitle rxTitle;
    private EditText mztkOrdXm;
    private EditText mztkOrdSfz;
    private Switch zktkOrdXf;
    private EditText mztkOrdNctkxsnx;
    private Switch mztkOrdXf;
    private EditText mztkOrdCstkxsnx;
    private EditText mztkOrdXsje;
    private EditText mztkOrdGyfs;
    private EditText mztkOrdBz;
    private Button btnSaveClxx;
    private String mID;
    private UpVeryStrickenBean.InfoBean mInfoBean = new UpVeryStrickenBean.InfoBean();
    private String[] gyfs = {"集中供养", "分散供养"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("local".equals(getIntent().getStringExtra("action"))) {
            mID = getIntent().getStringExtra("id");
            loadDatas();
        }else {
            mztkOrdXm.setText(AddPersonMainActivity.mName);
            mztkOrdSfz.setText(AddPersonMainActivity.mID);
        }
    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> veryStrickenMap = SaveTool.getVeryStricken();
            String json = veryStrickenMap.get(mID);
            UpVeryStrickenBean veryStrickenBean = new Gson().fromJson(json, UpVeryStrickenBean.class);
            mInfoBean = veryStrickenBean.getInfoBeans().get(0);
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
        mztkOrdXm = (EditText) findViewById(R.id.mztk_ord_xm);
        mztkOrdSfz = (EditText) findViewById(R.id.mztk_ord_sfz);
        zktkOrdXf = (Switch) findViewById(R.id.zktk_ord_xf);
        mztkOrdNctkxsnx = (EditText) findViewById(R.id.mztk_ord_nctkxsnx);
        mztkOrdXf = (Switch) findViewById(R.id.mztk_ord_xf);
        mztkOrdCstkxsnx = (EditText) findViewById(R.id.mztk_ord_cstkxsnx);
        mztkOrdXsje = (EditText) findViewById(R.id.mztk_ord_xsje);
        mztkOrdGyfs = (EditText) findViewById(R.id.mztk_ord_gyfs);
        mztkOrdBz = (EditText) findViewById(R.id.mztk_ord_bz);
        btnSaveClxx = (Button) findViewById(R.id.btn_save_clxx);

    }

    @Override
    protected void bindListener() {
        mztkOrdGyfs.setOnClickListener(v -> {
            MyTools.showSelectDialog(gyfs, mContext, mztkOrdGyfs);
        });
        btnSaveClxx.setOnClickListener(view -> {
            UpVeryStrickenBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_sfz())) {
                SaveTool.saveOneVeryStricken(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误,无法保存");
            }
        });
    }

    @Override
    protected void inputDatas() {
        mztkOrdXm.setText(mInfoBean.getOrd2_xm());
        mztkOrdSfz.setText(mInfoBean.getOrd2_sfz());
        mztkOrdNctkxsnx.setText(mInfoBean.getOrd2_nctkxsnx());
        mztkOrdCstkxsnx.setText(mInfoBean.getOrd2_cstkxsnx());
        mztkOrdXsje.setText(mInfoBean.getOrd2_xsje());
        mztkOrdGyfs.setText(mInfoBean.getOrd2_gyfs());
        mztkOrdBz.setText(mInfoBean.getOrd2_bz());

        zktkOrdXf.setChecked(mInfoBean.getOrd2_sfxsnctk().equals("是"));
        mztkOrdXf.setChecked(mInfoBean.getOrd2_sfxscstk().equals("是"));

    }

    private UpVeryStrickenBean.InfoBean saveDatas() {
        UpVeryStrickenBean.InfoBean infoBean = new UpVeryStrickenBean.InfoBean();
        infoBean.setOrd2_xm(mztkOrdXm.getText().toString());
        infoBean.setOrd2_sfz(mztkOrdSfz.getText().toString());
        infoBean.setOrd2_nctkxsnx(mztkOrdNctkxsnx.getText().toString());
        infoBean.setOrd2_cstkxsnx(mztkOrdCstkxsnx.getText().toString());
        infoBean.setOrd2_xsje(mztkOrdXsje.getText().toString());
        infoBean.setOrd2_gyfs(mztkOrdGyfs.getText().toString());
        infoBean.setOrd2_bz(mztkOrdBz.getText().toString());

        infoBean.setOrd2_sfxsnctk(zktkOrdXf.isChecked() ? "是" : "否");
        infoBean.setOrd2_sfxscstk(mztkOrdXf.isChecked() ? "是" : "否");
        return infoBean;
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_mztk;
    }
}
