package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.view.RxTitle;

public class addClxxActivity extends BaseActivity {

    private RxTitle rxTitle;
    private EditText clOrdHz;
    private EditText clOrdSfz;
    private EditText clOrdCjzzh;
    private EditText clOrdCjdj;
    private EditText clOrdCjyy;
    private EditText clOrdCjlb;
    private Switch clOrdSfxszc;
    private EditText clOrdZclb;
    private EditText clOrdSfxsshbt;
    private EditText clOrdYhkh;
    private Button btnSave;

    @Override
    protected int getContentID() {
        return R.layout.activity_form_clxx;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        clOrdHz = (EditText) findViewById(R.id.cl_ord_hz);
        clOrdSfz = (EditText) findViewById(R.id.cl_ord_sfz);
        clOrdCjzzh = (EditText) findViewById(R.id.cl_ord_cjzzh);
        clOrdCjdj = (EditText) findViewById(R.id.cl_ord_cjdj);
        clOrdCjyy = (EditText) findViewById(R.id.cl_ord_cjyy);
        clOrdCjlb = (EditText) findViewById(R.id.cl_ord_cjlb);
        clOrdSfxszc = (Switch) findViewById(R.id.cl_ord_sfxszc);
        clOrdZclb = (EditText) findViewById(R.id.cl_ord_zclb);
        clOrdSfxsshbt = (EditText) findViewById(R.id.cl_ord_sfxsshbt);
        clOrdYhkh = (EditText) findViewById(R.id.cl_ord_yhkh);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    protected void bindListener() {

    }
}
