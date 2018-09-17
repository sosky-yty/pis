package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpFamilyInfoBean;
import com.example.sosky.pis_copy.bean.UpResidualBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 残联信息
 */
public class addClxxActivity extends BaseActivity {

    private String[] cjyy = {"疾病", "遗传", "意外", "地震", "其他"};
    private String[] cjlb = {"视力", "听力", "言语", "肢体", "精神", "多重", "其他"};
    private String[] zclb = {"城市居民灵活就业", "扶持农村贫困残疾人发展生产", "贫困残疾人适配所需的基本辅助器具", "重度残疾人生活补贴"};

    private RxTitle rxTitle;
    private LinearLayout linear_zclb;
    private EditText clOrdXm;
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
    private UpResidualBean.InfoBean mInfoBean = new UpResidualBean.InfoBean();
    private String mID;

    @Override
    protected int getContentID() {
        return R.layout.activity_form_clxx;
    }

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
            Map<String, String> residualMap = SaveTool.getResidual();
            String json = residualMap.get(mID);
            UpResidualBean upResidualBean = new Gson().fromJson(json, UpResidualBean.class);
            mInfoBean = upResidualBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void inputDatas() {

        clOrdXm.setText(mInfoBean.getOrd2_xm());
        clOrdSfz.setText(mInfoBean.getOrd2_sfz());
        clOrdCjzzh.setText(mInfoBean.getOrd2_cjzzh());
        clOrdCjdj.setText(mInfoBean.getOrd2_cjdj());
        clOrdCjyy.setText(mInfoBean.getOrd2_cjyy());
        clOrdCjlb.setText(mInfoBean.getOrd2_cjlb());
        clOrdZclb.setText(mInfoBean.getOrd2_zclb());
        clOrdSfxsshbt.setText(mInfoBean.getOrd2_sfxsshbt());
        clOrdYhkh.setText(mInfoBean.getOrd2_yhkh());
        clOrdSfxszc.setChecked(mInfoBean.getOrd2_sfxszc().equals("是"));


    }

    @Override
    protected void bindView() {
        rxTitle = (RxTitle) findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        clOrdXm = (EditText) findViewById(R.id.cl_ord_xm);
        clOrdSfz = (EditText) findViewById(R.id.cl_ord_sfz);
        clOrdCjzzh = (EditText) findViewById(R.id.cl_ord_cjzzh);
        clOrdCjdj = (EditText) findViewById(R.id.cl_ord_cjdj);
        clOrdCjyy = (EditText) findViewById(R.id.cl_ord_cjyy);
        clOrdCjlb = (EditText) findViewById(R.id.cl_ord_cjlb);
        clOrdSfxszc = (Switch) findViewById(R.id.cl_ord_sfxszc);
        clOrdZclb = (EditText) findViewById(R.id.cl_ord_zclb);
        clOrdSfxsshbt = (EditText) findViewById(R.id.cl_ord_sfxsshbt);
        clOrdYhkh = (EditText) findViewById(R.id.cl_ord_yhkh);
        linear_zclb = findViewById(R.id.linear_zclb);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    protected void bindListener() {
        clOrdZclb.setOnClickListener(view -> {
            MyTools.showSelectDialog(zclb, mContext, clOrdZclb);
        });

        clOrdCjlb.setOnClickListener(view -> {
            MyTools.showSelectDialog(cjlb, mContext, clOrdCjlb);
        });

        clOrdCjyy.setOnClickListener(view -> {
            MyTools.showSelectDialog(cjyy, mContext, clOrdCjyy);
        });

        MyTools.setSwitchLisenter(linear_zclb, clOrdSfxszc);

        btnSave.setOnClickListener(view -> {
            UpResidualBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_sfz())) {
                SaveTool.saveOneResidual(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误");
            }
        });
    }

    /**
     * 保存数据
     *
     * @return
     */
    private UpResidualBean.InfoBean saveDatas() {
        UpResidualBean.InfoBean infoBean = new UpResidualBean.InfoBean();
        infoBean.setOrd2_xm(clOrdXm.getText().toString());
        infoBean.setOrd2_sfz(clOrdSfz.getText().toString());
        infoBean.setOrd2_cjzzh(clOrdCjzzh.getText().toString());
        infoBean.setOrd2_cjdj(clOrdCjdj.getText().toString());
        infoBean.setOrd2_cjyy(clOrdCjyy.getText().toString());
        infoBean.setOrd2_cjlb(clOrdCjlb.getText().toString());

        infoBean.setOrd2_zclb(clOrdZclb.getText().toString());
        infoBean.setOrd2_sfxsshbt(clOrdSfxsshbt.getText().toString());
        infoBean.setOrd2_yhkh(clOrdYhkh.getText().toString());

        infoBean.setOrd2_sfxszc(clOrdSfxszc.isChecked() ? "是" : "否");

        return infoBean;
    }

}
