package com.example.sosky.pis_copy.ui;

import android.widget.Button;
import android.widget.EditText;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpMedicalBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 医保信息
 */
public class addYbxxActivity extends BaseActivity {

    private RxTitle rxTitle;
    private EditText ybOrdXm;
    private EditText ybOrdSfz;
    private EditText ybOrdSccyybrq;
    private EditText ybOrdRyfl;
    private EditText ybOrdZjdc;
    private Button btnSaveClxx;
    private UpMedicalBean.InfoBean mInfoBean = new UpMedicalBean.InfoBean();
    private String mID;


    @Override
    protected void loadDatas() {
        if ("local".equals(getIntent().getStringExtra("action"))) {
            mID = getIntent().getStringExtra("id");
        }else {
            ybOrdXm.setText(AddPersonMainActivity.mName);
            ybOrdSfz.setText(AddPersonMainActivity.mID);
        }
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> medicalMap = SaveTool.getMedical();
            String json = medicalMap.get(mID);
            UpMedicalBean upMedicalBean = new Gson().fromJson(json, UpMedicalBean.class);
            mInfoBean = upMedicalBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            ybOrdXm.setText(AddPersonMainActivity.mName);
            ybOrdSfz.setText(AddPersonMainActivity.mID);
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        ybOrdXm = (EditText) findViewById(R.id.yb_ord_xm);
        ybOrdSfz = (EditText) findViewById(R.id.yb_ord_sfz);
        ybOrdSccyybrq = (EditText) findViewById(R.id.yb_ord_sccyybrq);
        ybOrdRyfl = (EditText) findViewById(R.id.yb_ord_ryfl);
        ybOrdZjdc = (EditText) findViewById(R.id.yb_ord_zjdc);
        btnSaveClxx = (Button) findViewById(R.id.btn_save_clxx);

    }


    @Override
    protected void bindListener() {

        ybOrdSccyybrq.setOnClickListener(v -> {
            MyTools.showDataPicker(mContext, ybOrdSccyybrq);
        });
        btnSaveClxx.setOnClickListener(view -> {
            UpMedicalBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_sfz())) {
                SaveTool.saveOneMedical(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误,无法保存");
            }
        });
    }

    @Override
    protected void inputDatas() {
        ybOrdXm.setText(mInfoBean.getOrd2_xm());
        ybOrdSfz.setText(mInfoBean.getOrd2_sfz());
        ybOrdSccyybrq.setText(mInfoBean.getOrd2_sccyybrq());
        ybOrdRyfl.setText(mInfoBean.getOrd2_ryfl());
        ybOrdZjdc.setText(mInfoBean.getOrd2_zjdc());

    }

    private UpMedicalBean.InfoBean saveDatas() {
        UpMedicalBean.InfoBean infoBean = new UpMedicalBean.InfoBean();
        infoBean.setOrd2_xm(ybOrdXm.getText().toString());
        infoBean.setOrd2_sfz(ybOrdSfz.getText().toString());
        infoBean.setOrd2_sccyybrq(ybOrdSccyybrq.getText().toString());
        infoBean.setOrd2_ryfl(ybOrdRyfl.getText().toString());
        infoBean.setOrd2_zjdc(ybOrdZjdc.getText().toString());
        return infoBean;
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_ybxx;
    }
}
