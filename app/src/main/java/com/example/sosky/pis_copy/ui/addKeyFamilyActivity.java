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
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

public class addKeyFamilyActivity extends BaseActivity {

    private String[] jtzk = {"优", "良", "差"};
    private String[] xsdzc = {"新居", "易地", "地质", "牧民", "定居"};

    private RxTitle rxTitle;
    private EditText faOrdHz;
    private EditText faOrdHzsfz;
    private EditText faOrdJtrks;
    private EditText faOrdNzrs;
    private Switch addOrdSfwwch;
    private EditText faOrdLdls;
    private EditText faOrdJntc;
    private LinearLayout linearZcwchdyy;
    private EditText faOrdZcwchdyy;
    private EditText faOrdGlscclhj;
    private EditText faOrdMnhj;
    private EditText faOrdMnzchj;
    private EditText faOrdMnnfmchj;
    private EditText faOrdMyhj;
    private EditText faOrdSyhj;
    private EditText faOrdMhj;
    private EditText faOrdBz;
    private Switch addOrdSfjdlkpkh;
    private EditText addOrdTpnx;
    private EditText addOrdYdfpbqyy;
    private EditText addOrdPkhsx;
    private EditText addOrdJtzk;
    private Switch addOrdSfjnp;
    private Switch addOrdSfxsdb;
    private Switch addOrdSfxslsjz;
    private Switch addOrdSfjsgfw;
    private EditText addOrdJfhlx;
    private EditText addOrdJzfmj;
    private EditText addOrdJzftp;
    private EditText addOrdXzfmj;
    private EditText addOrdXzftp;
    private EditText addOrdCjfk;
    private Switch addOrdXf;
    private EditText addOrdXjjslx;
    private EditText addOrdDzzhbqyy;
    private EditText addOrdXsdzc;
    private EditText addOrdXsdbz;
    private EditText addOrdQjf;
    private Button btnSaveClxx;
    private UpFamilyInfoBean.InfoBean mInfoBean = new UpFamilyInfoBean.InfoBean();
    private String mID;


    @Override
    protected int getContentID() {
        return R.layout.activity_addkeyfamily;
    }

    @Override
    protected void bindListener() {
        addOrdJtzk.setOnClickListener(v -> {
            MyTools.showSelectDialog(jtzk, mContext, addOrdJtzk);
        });

        addOrdXsdzc.setOnClickListener(v -> {
            MyTools.showSelectDialog(xsdzc, mContext, addOrdXsdzc);
        });

        MyTools.setSwitchLisenter(linearZcwchdyy, addOrdSfwwch);

        btnSaveClxx.setOnClickListener(v -> {
            UpFamilyInfoBean.InfoBean bean = saveDatas();
            if (MyTools.verificationID(bean.getOrd_hzsfz())) {
                SaveTool.saveOneFamily(bean);
                RxToast.success("保存成功");
            } else {
                RxToast.error("身份证错误");
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("local".equals(getIntent().getStringExtra("action"))) {
            mID = getIntent().getStringExtra("id");
            loadlocal();
        } else {
            faOrdHz.setText(AddFamilyMainActivity.mName);
            faOrdHzsfz.setText(AddFamilyMainActivity.mID);
        }
    }

    private void loadlocal() {
        RxLogTool.e("开始加载本地");
        try {
            //个人
            Map<String, String> xumuMap = SaveTool.getFamilys();
            String json = xumuMap.get(mID);
            UpFamilyInfoBean upPersonBean = new Gson().fromJson(json, UpFamilyInfoBean.class);
            mInfoBean = upPersonBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            faOrdHz.setText(AddFamilyMainActivity.mName);
            faOrdHzsfz.setText(AddFamilyMainActivity.mID);
            e.printStackTrace();
        }
    }

    @Override
    protected void inputDatas() {
        faOrdHz.setText(mInfoBean.getOrd_hz());
        faOrdHzsfz.setText(mInfoBean.getOrd_hzsfz());
        faOrdJtrks.setText(mInfoBean.getOrd_jtrks());
        faOrdNzrs.setText(mInfoBean.getOrd_nzrs());
        faOrdLdls.setText(mInfoBean.getOrd_ldls());
        faOrdJntc.setText(mInfoBean.getOrd_jntc());
        faOrdZcwchdyy.setText(mInfoBean.getOrd_zcwchdyy());
        faOrdGlscclhj.setText(mInfoBean.getOrd_glscclhj());
        faOrdMnhj.setText(mInfoBean.getOrd_mnhj());
        faOrdMnzchj.setText(mInfoBean.getOrd_mnzchj());
        faOrdMnnfmchj.setText(mInfoBean.getOrd_mnnfmchj());
        faOrdMyhj.setText(mInfoBean.getOrd_myhj());
        faOrdSyhj.setText(mInfoBean.getOrd_syhj());
        faOrdMhj.setText(mInfoBean.getOrd_mhj());
        faOrdBz.setText(mInfoBean.getOrd_bz());
        addOrdTpnx.setText(mInfoBean.getOrd_tpnx());
        addOrdYdfpbqyy.setText(mInfoBean.getOrd_ydfpbqyy());
        addOrdPkhsx.setText(mInfoBean.getOrd_pkhsx());
        addOrdJtzk.setText(mInfoBean.getOrd_jtzk());
        addOrdJfhlx.setText(mInfoBean.getOrd_jfhlx());
        addOrdJzfmj.setText(mInfoBean.getOrd_jzfmj());
        addOrdJzftp.setText(mInfoBean.getOrd_jzftp());
        addOrdXzfmj.setText(mInfoBean.getOrd_xzfmj());
        addOrdXzftp.setText(mInfoBean.getOrd_xzftp());
        addOrdCjfk.setText(mInfoBean.getOrd_cjfk());
        addOrdXjjslx.setText(mInfoBean.getOrd_xjjslx());
        addOrdDzzhbqyy.setText(mInfoBean.getOrd_dzzhbqyy());
        addOrdXsdzc.setText(mInfoBean.getOrd_xsdzc());
        addOrdXsdbz.setText(mInfoBean.getOrd_xsdbz());
        addOrdQjf.setText(mInfoBean.getOrd_qjf());
        addOrdSfjdlkpkh.setChecked(mInfoBean.getOrd_sfjdlkpkh().equals("是"));
        addOrdSfjnp.setChecked(mInfoBean.getOrd_sfjnp().equals("是"));
        addOrdSfxsdb.setChecked(mInfoBean.getOrd_sfxsdb().equals("是"));
        addOrdSfxslsjz.setChecked(mInfoBean.getOrd_sfxslsjz().equals("是"));
        addOrdSfjsgfw.setChecked(mInfoBean.getOrd_sfjsgfw().equals("是"));
        addOrdXf.setChecked(mInfoBean.getOrd_xf().equals("是"));
        addOrdSfwwch.setChecked(mInfoBean.getOrd_sfwwch().equals("是"));
    }

    @Override
    protected void bindView() {

        rxTitle = findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);
        faOrdHz = findViewById(R.id.fa_ord_hz);
        faOrdHzsfz = findViewById(R.id.fa_ord_hzsfz);
        faOrdJtrks = findViewById(R.id.fa_ord_jtrks);
        faOrdNzrs = findViewById(R.id.fa_ord_nzrs);
        addOrdSfwwch = findViewById(R.id.add_ord_sfwwch);
        faOrdLdls = findViewById(R.id.fa_ord_ldls);
        faOrdJntc = findViewById(R.id.fa_ord_jntc);
        linearZcwchdyy = findViewById(R.id.linear_zcwchdyy);
        faOrdZcwchdyy = findViewById(R.id.fa_ord_zcwchdyy);
        faOrdGlscclhj = findViewById(R.id.fa_ord_glscclhj);
        faOrdMnhj = findViewById(R.id.fa_ord_mnhj);
        faOrdMnzchj = findViewById(R.id.fa_ord_mnzchj);
        faOrdMnnfmchj = findViewById(R.id.fa_ord_mnnfmchj);
        faOrdMyhj = findViewById(R.id.fa_ord_myhj);
        faOrdSyhj = findViewById(R.id.fa_ord_syhj);
        faOrdMhj = findViewById(R.id.fa_ord_mhj);
        faOrdBz = findViewById(R.id.fa_ord_bz);
        addOrdSfjdlkpkh = findViewById(R.id.add_ord_sfjdlkpkh);
        addOrdTpnx = findViewById(R.id.add_ord_tpnx);
        addOrdYdfpbqyy = findViewById(R.id.add_ord_ydfpbqyy);
        addOrdPkhsx = findViewById(R.id.add_ord_pkhsx);
        addOrdJtzk = findViewById(R.id.add_ord_jtzk);
        addOrdSfjnp = findViewById(R.id.add_ord_sfjnp);
        addOrdSfxsdb = findViewById(R.id.add_ord_sfxsdb);
        addOrdSfxslsjz = findViewById(R.id.add_ord_sfxslsjz);
        addOrdSfjsgfw = findViewById(R.id.add_ord_sfjsgfw);
        addOrdJfhlx = findViewById(R.id.add_ord_jfhlx);
        addOrdJzfmj = findViewById(R.id.add_ord_jzfmj);
        addOrdJzftp = findViewById(R.id.add_ord_jzftp);
        addOrdXzfmj = findViewById(R.id.add_ord_xzfmj);
        addOrdXzftp = findViewById(R.id.add_ord_xzftp);
        addOrdCjfk = findViewById(R.id.add_ord_cjfk);
        addOrdXf = findViewById(R.id.add_ord_xf);
        addOrdXjjslx = findViewById(R.id.add_ord_xjjslx);
        addOrdDzzhbqyy = findViewById(R.id.add_ord_dzzhbqyy);
        addOrdXsdzc = findViewById(R.id.add_ord_xsdzc);
        addOrdXsdbz = findViewById(R.id.add_ord_xsdbz);
        addOrdQjf = findViewById(R.id.add_ord_qjf);
        btnSaveClxx = findViewById(R.id.btn_save_clxx);


    }

    /**
     * 获取所有值
     *
     * @return
     */
    public UpFamilyInfoBean.InfoBean saveDatas() {

        UpFamilyInfoBean.InfoBean infoBean = new UpFamilyInfoBean.InfoBean();
        infoBean.setOrd_hz(faOrdHz.getText().toString());
        infoBean.setOrd_hzsfz(faOrdHzsfz.getText().toString());
        infoBean.setOrd_jtrks(faOrdJtrks.getText().toString());
        infoBean.setOrd_nzrs(faOrdNzrs.getText().toString());
        infoBean.setOrd_ldls(faOrdLdls.getText().toString());
        infoBean.setOrd_jntc(faOrdJntc.getText().toString());
        infoBean.setOrd_zcwchdyy(faOrdZcwchdyy.getText().toString());
        infoBean.setOrd_glscclhj(faOrdGlscclhj.getText().toString());
        infoBean.setOrd_mnhj(faOrdMnhj.getText().toString());
        infoBean.setOrd_mnzchj(faOrdMnzchj.getText().toString());
        infoBean.setOrd_mnnfmchj(faOrdMnnfmchj.getText().toString());
        infoBean.setOrd_myhj(faOrdMyhj.getText().toString());
        infoBean.setOrd_syhj(faOrdSyhj.getText().toString());
        infoBean.setOrd_mhj(faOrdMhj.getText().toString());
        infoBean.setOrd_bz(faOrdBz.getText().toString());
        infoBean.setOrd_tpnx(addOrdTpnx.getText().toString());
        infoBean.setOrd_ydfpbqyy(addOrdYdfpbqyy.getText().toString());
        infoBean.setOrd_pkhsx(addOrdPkhsx.getText().toString());
        infoBean.setOrd_jtzk(addOrdJtzk.getText().toString());
        infoBean.setOrd_jfhlx(addOrdJfhlx.getText().toString());
        infoBean.setOrd_jzfmj(addOrdJzfmj.getText().toString());
        infoBean.setOrd_jzftp(addOrdJzftp.getText().toString());
        infoBean.setOrd_xzfmj(addOrdXzfmj.getText().toString());
        infoBean.setOrd_xzftp(addOrdXzftp.getText().toString());
        infoBean.setOrd_cjfk(addOrdCjfk.getText().toString());
        infoBean.setOrd_xjjslx(addOrdXjjslx.getText().toString());
        infoBean.setOrd_dzzhbqyy(addOrdDzzhbqyy.getText().toString());
        infoBean.setOrd_xsdzc(addOrdXsdzc.getText().toString());
        infoBean.setOrd_xsdbz(addOrdXsdbz.getText().toString());
        infoBean.setOrd_qjf(addOrdQjf.getText().toString());

        //todo 是否 
        infoBean.setOrd_sfjdlkpkh(addOrdSfjdlkpkh.isChecked() ? "是" : "否");
        infoBean.setOrd_sfjnp(addOrdSfjnp.isChecked() ? "是" : "否");
        infoBean.setOrd_sfxsdb(addOrdSfxsdb.isChecked() ? "是" : "否");
        infoBean.setOrd_sfxslsjz(addOrdSfxslsjz.isChecked() ? "是" : "否");
        infoBean.setOrd_sfjsgfw(addOrdSfjsgfw.isChecked() ? "是" : "否");
        infoBean.setOrd_sfjsgfw(addOrdSfjsgfw.isChecked() ? "是" : "否");
        infoBean.setOrd_xf(addOrdXf.isChecked() ? "是" : "否");
        infoBean.setOrd_sfwwch(addOrdSfwwch.isChecked() ? "是" : "否");
        return infoBean;
    }


}
