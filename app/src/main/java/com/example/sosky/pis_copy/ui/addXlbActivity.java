package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpNewAgriculturalBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 新农保
 */
public class addXlbActivity extends BaseActivity {
    private RxTitle rxTitle;
    private EditText xlbOrdXm;
    private EditText xlbOrdSfz;
    private EditText xlbOrdSccyxnbrq;
    private EditText xlbOrdCbqk;
    private EditText xlbOrdYlbxje;
    private EditText xlbOrdTxsj;
    private EditText xlbOrdLqdy;
    private EditText xlbOrdJfnx;
    private EditText xlbOrdZxrq;
    private EditText xlbOrdBz;
    private Button btnSaveClxx;
    private String mID;
    private UpNewAgriculturalBean.InfoBean mInfoBean = new UpNewAgriculturalBean.InfoBean();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("local".equals(getIntent().getStringExtra("action"))) {
            mID = getIntent().getStringExtra("id");
            loadDatas();
        } else {
            xlbOrdXm.setText(AddPersonMainActivity.mName);
            xlbOrdSfz.setText(AddPersonMainActivity.mID);
        }
    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> newAgriculturalMap = SaveTool.getNewAgricultural();
            String json = newAgriculturalMap.get(mID);
            UpNewAgriculturalBean newAgriculturalBean = new Gson().fromJson(json, UpNewAgriculturalBean.class);
            mInfoBean = newAgriculturalBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
                xlbOrdXm.setText(AddPersonMainActivity.mName);
            }
        } catch (Exception e) {
            xlbOrdXm.setText(AddPersonMainActivity.mName);
            xlbOrdSfz.setText(AddPersonMainActivity.mID);
            e.printStackTrace();
        }
    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        xlbOrdXm = (EditText) findViewById(R.id.xlb_ord_xm);
        xlbOrdSfz = (EditText) findViewById(R.id.xlb_ord_sfz);
        xlbOrdSccyxnbrq = (EditText) findViewById(R.id.xlb_ord_sccyxnbrq);
        xlbOrdCbqk = (EditText) findViewById(R.id.xlb_ord_cbqk);
        xlbOrdYlbxje = (EditText) findViewById(R.id.xlb_ord_ylbxje);
        xlbOrdTxsj = (EditText) findViewById(R.id.xlb_ord_txsj);
        xlbOrdLqdy = (EditText) findViewById(R.id.xlb_ord_lqdy);
        xlbOrdJfnx = (EditText) findViewById(R.id.xlb_ord_jfnx);
        xlbOrdZxrq = (EditText) findViewById(R.id.xlb_ord_zxrq);
        xlbOrdBz = (EditText) findViewById(R.id.xlb_ord_bz);
        btnSaveClxx = (Button) findViewById(R.id.btn_save_clxx);

    }

    @Override
    protected void bindListener() {
        btnSaveClxx.setOnClickListener(view -> {
            UpNewAgriculturalBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_sfz())) {
                SaveTool.saveOneNewAgricultuaral(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误,无法保存");
            }
        });

        xlbOrdSccyxnbrq.setOnClickListener(view -> {
            MyTools.showDataPicker(mContext, xlbOrdSccyxnbrq);
        });
    }

    @Override
    protected void inputDatas() {
        xlbOrdXm.setText(mInfoBean.getOrd2_xm());
        xlbOrdSfz.setText(mInfoBean.getOrd2_sfz());
        xlbOrdSccyxnbrq.setText(mInfoBean.getOrd2_sccyxnbrq());
        xlbOrdCbqk.setText(mInfoBean.getOrd2_cbqk());
        xlbOrdYlbxje.setText(mInfoBean.getOrd2_ylbxje());
        xlbOrdTxsj.setText(mInfoBean.getOrd2_txsj());
        xlbOrdLqdy.setText(mInfoBean.getOrd2_lqdy());
        xlbOrdJfnx.setText(mInfoBean.getOrd2_jfnx());
        xlbOrdZxrq.setText(mInfoBean.getOrd2_zxrq());
        xlbOrdBz.setText(mInfoBean.getOrd2_bz());
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_xlb;
    }


    private UpNewAgriculturalBean.InfoBean saveDatas() {
        UpNewAgriculturalBean.InfoBean infoBean = new UpNewAgriculturalBean.InfoBean();
        infoBean.setOrd2_xm(xlbOrdXm.getText().toString());
        infoBean.setOrd2_sfz(xlbOrdSfz.getText().toString());
        infoBean.setOrd2_sccyxnbrq(xlbOrdSccyxnbrq.getText().toString());
        infoBean.setOrd2_cbqk(xlbOrdCbqk.getText().toString());
        infoBean.setOrd2_ylbxje(xlbOrdYlbxje.getText().toString());
        infoBean.setOrd2_txsj(xlbOrdTxsj.getText().toString());
        infoBean.setOrd2_lqdy(xlbOrdLqdy.getText().toString());
        infoBean.setOrd2_jfnx(xlbOrdJfnx.getText().toString());
        infoBean.setOrd2_zxrq(xlbOrdZxrq.getText().toString());
        infoBean.setOrd2_bz(xlbOrdBz.getText().toString());
        infoBean.setOrd2_flag("1");
        return infoBean;
    }
}
