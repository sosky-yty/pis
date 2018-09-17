package com.example.sosky.pis_copy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpLowInsuranceBean;
import com.example.sosky.pis_copy.bean.UpSeekHelpBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

/**
 * 临时救助
 */
public class addLsjzActivity extends BaseActivity {
    private RxTitle rxTitle;
    private EditText lsOrdHz;
    private EditText lsOrdHzsfz;
    private EditText lsOrdJzsj;
    private EditText lsOrdJzyy;
    private EditText lsOrdJzfs;
    private EditText lsOrdBz;
    private Button btnSave;
    private String mID;
    private UpSeekHelpBean.InfoBean mInfoBean = new UpSeekHelpBean.InfoBean();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lsOrdHz.setText(AddFamilyMainActivity.mName);
        lsOrdHzsfz.setText(AddFamilyMainActivity.mID);

    }

    @Override
    protected void loadDatas() {
        RxLogTool.e("开始加载本地");
        try {
            Map<String, String> seekHelpMap = SaveTool.getSeekHelp();
            String json = seekHelpMap.get(mID);
            UpSeekHelpBean seekHelpBean = new Gson().fromJson(json, UpSeekHelpBean.class);
            mInfoBean = seekHelpBean.getInfoBeans().get(0);
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
        lsOrdHz = (EditText) findViewById(R.id.ls_ord_hz);
        lsOrdHzsfz = (EditText) findViewById(R.id.ls_ord_hzsfz);
        lsOrdJzsj = (EditText) findViewById(R.id.ls_ord_jzsj);
        lsOrdJzyy = (EditText) findViewById(R.id.ls_ord_jzyy);
        lsOrdJzfs = (EditText) findViewById(R.id.ls_ord_jzfs);
        lsOrdBz = (EditText) findViewById(R.id.ls_ord_bz);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    protected void bindListener() {
        btnSave.setOnClickListener(view -> {
            UpSeekHelpBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd2_hzsfz())) {
                SaveTool.saveOneSeekHelp(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误,无法保存");
            }
        });
    }

    @Override
    protected void inputDatas() {
        lsOrdHz.setText(mInfoBean.getOrd2_hz());
        lsOrdHzsfz.setText(mInfoBean.getOrd2_hzsfz());
        lsOrdJzsj.setText(mInfoBean.getOrd2_jzsj());
        lsOrdJzyy.setText(mInfoBean.getOrd2_jzyy());
        lsOrdJzfs.setText(mInfoBean.getOrd2_jzfs());
        lsOrdBz.setText(mInfoBean.getOrd2_bz());
    }

    @Override
    protected int getContentID() {
        return R.layout.activity_form_lsjz;
    }


    private UpSeekHelpBean.InfoBean saveDatas() {
        UpSeekHelpBean.InfoBean infoBean = new UpSeekHelpBean.InfoBean();
        infoBean.setOrd2_hz(lsOrdHz.getText().toString());
        infoBean.setOrd2_hzsfz(lsOrdHzsfz.getText().toString());
        infoBean.setOrd2_jzsj(lsOrdJzsj.getText().toString());
        infoBean.setOrd2_jzyy(lsOrdJzyy.getText().toString());
        infoBean.setOrd2_jzfs(lsOrdJzfs.getText().toString());
        infoBean.setOrd2_bz(lsOrdBz.getText().toString());

        return infoBean;
    }
}
