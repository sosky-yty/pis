package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpNewAgriculturalBean;
import com.vondear.rxtools.view.RxTitle;

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
    private UpNewAgriculturalBean.InfoBean  mInfoBean = new UpNewAgriculturalBean.InfoBean();


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

    }

    @Override
    protected void bindView() {

        rxTitle = (RxTitle) findViewById(R.id.rx_title);
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

    }

    @Override
    protected void inputDatas() {

    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_xlb;
    }
}
