package com.example.sosky.pis_copy.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpFamilyInfoBean;
import com.vondear.rxtools.view.RxTitle;

public class addKeyFamilyActivity extends BaseActivity {

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


    @Override
    protected int getContentID() {
        return R.layout.activity_addkeyfamily;
    }

    @Override
    protected void bindView() {

        rxTitle = findViewById(R.id.rx_title);
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

        return infoBean;
    }
    
    
}
