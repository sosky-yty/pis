package com.example.sosky.pis_copy.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;

import java.util.Map;

public class addKeyPersonActivity extends BaseActivity {


    String[] minzu = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族",
            "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族",
            "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族",
            "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"};

    String[] sex = {"男", "女"};
    //证件类型
    String[] sfzlb = {"身份证", "非身份证"};
    //政治面貌
    String[] zzmm = {"群众", "党员", "团员", "预备党员"};
    //与户主关系
    String[] yhzgx = {"户主", "子女", "妻女", "子孙"};
    //婚姻状况
    String[] hyzk = {"未婚", "已婚", "离异"};
    //所在辖区
    String[] szxq = {"光明社区", "吉祥社区", "团结社区", "安康社区", "解放一村", "解放二村", "约若二村", "姑咱一村", "姑咱二村", "幸福一村", "幸福二村"};

    //文化程度
    String[] whcd = {"文盲", "小学", "中学", "高中（含中专）", "大学专科", "大学本科", "研究生", "博士"};
    //就业状态
    String[] jyzk = {"待业", "就业", "失业"};
    //兵役状态
    String[] byzt = {"已服", "未服"};

    //残疾等级
    String [] cjdj = {"1","2","3","4"};

    //参与集体经济项目
    String[] cyjtjjxm = {"乡村酒店", "大棚蔬菜", "瓦须黑帐篷体验园区", "生态畜牧专业合作"};

    String[] zdrylx = {"教职人员", "矫正人员", "帮扶人员", "刑满释放人员", "反分裂分子", "时轮金刚法会回流人员"};

    String[] yf = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private Context mContext;
    private RxTitle rxTitle;
    private Button button;

    private EditText ord_xm;
    private EditText ord_zm;
    private EditText ord_xb;
    private EditText ord_zjlb;
    private EditText ord_sfz;
    private EditText ord_csrq;
    private EditText ord_nl;
    private EditText ord_mz;
    private EditText ord_zzmm;
    private EditText ord_yhzgx;
    private EditText ord_hz;
    private EditText ord_hzsfz;
    private EditText ord_hyzk;
    private EditText ord_szxq;
    private EditText ord_xjzd;
    private EditText ord_lxdh;

    private Switch s_ord_sfcygyxgw;
    private EditText ord_gwmc;
    private LinearLayout linear_gwmc;

    private Switch s_ord_sfcyxnb;
    private Switch s_ord_sfcyxnh;
    private Switch s_ord_sfcydbylbx;
    private EditText ord_whcd;

    private Switch s_ord_sfzd;
    private EditText ord_szyx;
    private LinearLayout linear_szyx;

    private EditText ord_jyzk;
    private EditText ord_jydw;
    private LinearLayout linear_jydw;

    private EditText ord_byzt;
    private EditText ord_zp;

    private Switch s_ord_sfcyjtjj;
    private EditText ord_cyjtjjdxm;
    private LinearLayout linear_cyjtjjdxm;

    private Switch s_ord_sfwnctk;
    private Switch s_ord_sfwcstk;

    private Switch s_ord_sfwge;
    private EditText ord_gegyfs;
    private LinearLayout linear_gegyfs;

    private Switch s_ord_sfwlset;

    private Switch s_ord_sfwzdry;
    private EditText ord_zdrylx;
    private LinearLayout linear_zdrylx;

    //残联信息
    private Switch s_ord_sfwcjr;
    private EditText ord_cjzh;
    private EditText ord_cjdj;
    private LinearLayout linear_clxx;
    //司法信息
    private Switch s_ord_sfyfzqk;
    private EditText ord_fzlx;
    private EditText ord_jzqx;
    private EditText ord_bfqx;
    private LinearLayout linear_sfxx;
    //寺庙信息
    private Switch s_ord_sfsn;
    private EditText ord_szsm;
    private EditText ord_rssj;
    private EditText ord_jzryzshm;
    private EditText ord_lhlszrr;
    private EditText ord_lhlszrrdw;
    private EditText ord_lhlszrrlxdh;
    private LinearLayout linear_smxx;
    //计生信息
    private EditText ord_sskf;
    private EditText ord_jlfz;
    private EditText ord_tbfz;
    private EditText ord_xse;
    private EditText ord_yf;
    private EditText ord_cs;
    private EditText ord_apm;
    private EditText ord_qpm;
    private EditText ord_ahqhdw;
    private Switch s_ord_fhsz;
    private LinearLayout linear_jsxx;

    private UpPersonBean.InfoBean mInfoBean = new UpPersonBean.InfoBean();
    private String mID;

    @Override
    protected int getContentID() {
        return R.layout.activity_addkeyperson;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("local".equals(getIntent().getStringExtra("action"))) {
            mID= getIntent().getStringExtra("id");
            loadlocal();
        }
    }


    /**
     * 绑定view
     */
    @Override
    protected void bindView() {
        ord_xb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //选择性别
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.mydialog);
                builder.setTitle("请选择");
                builder.setItems(sex, (dialog, which) -> {
                    dialog.dismiss();
                    ord_xb.setText(sex[which]);

                    if (which == 1) {
                        MyTools.setLinearLayoutVisibility(linear_jsxx, true);
                    } else if (which == 0) {
                        MyTools.setLinearLayoutVisibility(linear_jsxx, false);
                    }
                });
                builder.setPositiveButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.create().show();
            }
        });

        ord_mz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(minzu, mContext, ord_xb);
            }
        });

        ord_hyzk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(hyzk, mContext, ord_hyzk);
            }
        });

        ord_zjlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(sfzlb, mContext, ord_zjlb);
            }
        });

        ord_zzmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(zzmm, mContext, ord_zzmm);
            }
        });

        ord_yhzgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(yhzgx, mContext, ord_yhzgx);
            }
        });

        ord_szxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(szxq, mContext, ord_szxq);
            }
        });
        ord_whcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(whcd, mContext, ord_whcd);
            }
        });
        ord_jyzk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(jyzk, mContext, ord_jyzk);
            }
        });
        ord_byzt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(byzt, mContext, ord_byzt);
            }
        });
        ord_cyjtjjdxm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(cyjtjjxm, mContext, ord_cyjtjjdxm);
            }
        });
        ord_zdrylx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(zdrylx, mContext, ord_zdrylx);
            }
        });

        ord_yf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTools.showSelectDialog(yf, mContext, ord_yf);
            }
        });

        MyTools.setSwitchLisenter(linear_gwmc, s_ord_sfcygyxgw);
        MyTools.setSwitchLisenter(linear_szyx, s_ord_sfzd);
        MyTools.setSwitchLisenter(linear_cyjtjjdxm, s_ord_sfcyjtjj);
        MyTools.setSwitchLisenter(linear_gegyfs, s_ord_sfwge);
        MyTools.setSwitchLisenter(linear_zdrylx, s_ord_sfwzdry);
        MyTools.setSwitchLisenter(linear_clxx, s_ord_sfwcjr);
        MyTools.setSwitchLisenter(linear_sfxx, s_ord_sfyfzqk);
        MyTools.setSwitchLisenter(linear_smxx, s_ord_sfsn);

        ord_jyzk.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (ord_jyzk.getText().toString().equals("待业") || ord_jyzk.getText().toString().equals("失业")) {
                    MyTools.setLinearLayoutVisibility(linear_jydw, true);
                } else {
                    MyTools.setLinearLayoutVisibility(linear_jydw, false);
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpPersonBean.InfoBean bean = saveDatas();
                if(MyTools.verificationID(bean.getOrd_sfz())){
                    SaveTool.saveOnePerson(bean);
                    RxToast.success("保存成功");
                }else{
                    RxToast.error("身份证错误");
                }

            }
        });
    }

    @Override
    protected void bindListener() {
        ord_csrq.setOnClickListener(v -> {
            MyTools.showDataPicker(mContext, ord_csrq);
        });
        ord_apm.setOnClickListener(v -> {
            MyTools.showDataPicker(mContext, ord_apm);
        });
        ord_qpm.setOnClickListener(v -> {
            MyTools.showDataPicker(mContext, ord_qpm);
        });

        ord_rssj.setOnClickListener(v -> {
            MyTools.showDataPicker(mContext, ord_rssj);
        });
        ord_cjdj.setOnClickListener(v->{
            MyTools.showSelectDialog(cjdj, mContext, ord_cjdj);
        });

    }

    //获取控件值,判断身份证是否合格
    private UpPersonBean.InfoBean saveDatas() {
        UpPersonBean.InfoBean infoBeans = new UpPersonBean.InfoBean();
        infoBeans.setOrd_xm(ord_xm.getText().toString());
        infoBeans.setOrd_zm(ord_zm.getText().toString());
        infoBeans.setOrd_xb(ord_xb.getText().toString());
        infoBeans.setOrd_zjlb(ord_zjlb.getText().toString());
        infoBeans.setOrd_sfz(ord_sfz.getText().toString());
        infoBeans.setOrd_csrq(ord_csrq.getText().toString());
        infoBeans.setOrd_nl(ord_nl.getText().toString());
        infoBeans.setOrd_mz(ord_mz.getText().toString());
        infoBeans.setOrd_zzmm(ord_zzmm.getText().toString());
        infoBeans.setOrd_yhzgx(ord_yhzgx.getText().toString());
        infoBeans.setOrd_hz(ord_hz.getText().toString());
        infoBeans.setOrd_hzsfz(ord_hzsfz.getText().toString());
        infoBeans.setOrd_hyzk(ord_hyzk.getText().toString());
        infoBeans.setOrd_szxq(ord_szxq.getText().toString());
        infoBeans.setOrd_xjzd(ord_xjzd.getText().toString());
        infoBeans.setOrd_lxdh(ord_lxdh.getText().toString());
        infoBeans.setOrd_sfcygyxgw(s_ord_sfcygyxgw.isChecked()?"是":"否");
        infoBeans.setOrd_gwmc(ord_gwmc.getText().toString());
        infoBeans.setOrd_sfcyxnb(s_ord_sfcyxnb.isChecked()?"是":"否");
        infoBeans.setOrd_sfcyxnh(s_ord_sfcyxnh.isChecked()?"是":"否");
        infoBeans.setOrd_sfcygyxgw(s_ord_sfcydbylbx.getText().toString());
        infoBeans.setOrd_whcd(ord_whcd.getText().toString());
        infoBeans.setOrd_sfzd(s_ord_sfzd.isChecked()?"是":"否");
        infoBeans.setOrd_szyx(ord_szyx.getText().toString());
        infoBeans.setOrd_jyzk(ord_jyzk.getText().toString());
        infoBeans.setOrd_jydw(ord_jydw.getText().toString());
        infoBeans.setOrd_byzt(ord_byzt.getText().toString());
        infoBeans.setOrd_zp(ord_zp.getText().toString());
        infoBeans.setOrd_sfcyjtjj(s_ord_sfcyjtjj.isChecked()?"是":"否");
        infoBeans.setOrd_cyjtjjdxm(ord_cyjtjjdxm.getText().toString());
        infoBeans.setOrd_sfwnctk(s_ord_sfwnctk.isChecked()?"是":"否");
        infoBeans.setOrd_sfwcstk(s_ord_sfwcstk.isChecked()?"是":"否");
        infoBeans.setOrd_sfwge(s_ord_sfwge.isChecked()?"是":"否");
        infoBeans.setOrd_gegyfs(ord_gegyfs.getText().toString());
        infoBeans.setOrd_sfwlset(s_ord_sfwlset.isChecked()?"是":"否");
        infoBeans.setOrd_sfwzdry(s_ord_sfwzdry.isChecked()?"是":"否");
        infoBeans.setOrd_zdrylx(ord_zdrylx.getText().toString());
        infoBeans.setOrd_sfwcjr(s_ord_sfwcjr.isChecked()?"是":"否");
        infoBeans.setOrd_cjzh(ord_cjzh.getText().toString());
        infoBeans.setOrd_cjdj(ord_cjdj.getText().toString());
        infoBeans.setOrd_sfyfzqk(s_ord_sfyfzqk.isChecked()?"是":"否");
        infoBeans.setOrd_fzlx(ord_fzlx.getText().toString());
        infoBeans.setOrd_jzqx(ord_jzqx.getText().toString());
        infoBeans.setOrd_bfqx(ord_bfqx.getText().toString());
        infoBeans.setOrd_sfsn(s_ord_sfsn.isChecked()?"是":"否");
        infoBeans.setOrd_szsm(ord_szsm.getText().toString());
        infoBeans.setOrd_rssj(ord_rssj.getText().toString());
        infoBeans.setOrd_jzryzshm(ord_jzryzshm.getText().toString());
        infoBeans.setOrd_lhlszrr(ord_lhlszrr.getText().toString());
        infoBeans.setOrd_lhlszrr(ord_lhlszrrdw.getText().toString());
        infoBeans.setOrd_lhlszrrlxdh(ord_lhlszrrlxdh.getText().toString());
        infoBeans.setOrd_sskf(ord_sskf.getText().toString());
        infoBeans.setOrd_jlfz(ord_jlfz.getText().toString());
        infoBeans.setOrd_tbfz(ord_tbfz.getText().toString());
        infoBeans.setOrd_xse(ord_xse.getText().toString());
        infoBeans.setOrd_yf(ord_yf.getText().toString());
        infoBeans.setOrd_cs(ord_cs.getText().toString());
        infoBeans.setOrd_apm(ord_apm.getText().toString());
        infoBeans.setOrd_qpm(ord_qpm.getText().toString());
        infoBeans.setOrd_ahqhdw(ord_ahqhdw.getText().toString());
        infoBeans.setOrd_fhsz(s_ord_fhsz.isChecked()?"是":"否");

        return infoBeans;
    }

    /**
     * 初始话view
     */
    @Override
    protected void initView() {
        mContext = this;
        rxTitle = findViewById(R.id.rx_title);

        rxTitle.setLeftFinish(this);

        button = findViewById(R.id.btn_save);
        ord_xm = findViewById(R.id.add_ord_xm);
        ord_zm = findViewById(R.id.add_ord_zm);
        ord_xb = findViewById(R.id.add_ord_xb);
        ord_zjlb = findViewById(R.id.add_ord_zjlb);
        ord_sfz = findViewById(R.id.add_ord_sfz);
        ord_csrq = findViewById(R.id.add_ord_csrq);
        ord_nl = findViewById(R.id.add_ord_nl);
        ord_mz = findViewById(R.id.add_ord_mz);
        ord_zzmm = findViewById(R.id.add_ord_zzmm);
        ord_yhzgx = findViewById(R.id.add_ord_yhzgx);
        ord_hz = findViewById(R.id.add_ord_hz);
        ord_hzsfz = findViewById(R.id.add_ord_hzsfz);
        ord_hyzk = findViewById(R.id.add_ord_hyzk);
        ord_szxq = findViewById(R.id.add_ord_szxq);
        ord_xjzd = findViewById(R.id.add_ord_xjzd);
        ord_lxdh = findViewById(R.id.add_ord_lxdh);

        s_ord_sfcygyxgw = findViewById(R.id.add_ord_sfcygyxgw);
        ord_gwmc = findViewById(R.id.add_ord_gwmc);
        linear_gwmc = findViewById(R.id.linear_gwmc);

        s_ord_sfcyxnb = findViewById(R.id.add_ord_sfcyxnb);
        s_ord_sfcyxnh = findViewById(R.id.add_ord_sfcyxnh);
        s_ord_sfcydbylbx = findViewById(R.id.add_ord_sfcydbylbx);
        ord_whcd = findViewById(R.id.add_ord_whcd);

        s_ord_sfzd = findViewById(R.id.add_ord_sfzd);
        ord_szyx = findViewById(R.id.add_ord_szyx);
        linear_szyx = findViewById(R.id.linear_szyx);

        ord_jyzk = findViewById(R.id.add_ord_jyzk);
        ord_jydw = findViewById(R.id.add_ord_jydw);
        linear_jydw = findViewById(R.id.linear_jydw);

        ord_byzt = findViewById(R.id.add_ord_byzt);

        ord_zp = findViewById(R.id.add_ord_zp);

        s_ord_sfcyjtjj = findViewById(R.id.add_ord_sfcyjtjj);
        ord_cyjtjjdxm = findViewById(R.id.add_ord_cyjtjjdxm);
        linear_cyjtjjdxm = findViewById(R.id.linear_cyjtjjdxm);

        s_ord_sfwnctk = findViewById(R.id.add_ord_sfwnctk);
        s_ord_sfwcstk = findViewById(R.id.add_ord_sfwcstk);


        s_ord_sfwge = findViewById(R.id.add_ord_sfwge);
        ord_gegyfs = findViewById(R.id.add_ord_gegyfs);
        linear_gegyfs = findViewById(R.id.linear_gegyfs);

        s_ord_sfwlset = findViewById(R.id.add_ord_sfwlsey);

        s_ord_sfwzdry = findViewById(R.id.add_ord_sfwzdry);
        ord_zdrylx = findViewById(R.id.add_ord_zdrylx);
        linear_zdrylx = findViewById(R.id.linear_zdrylx);


        s_ord_sfwcjr = findViewById(R.id.add_ord_sfwcjr);
        ord_cjzh = findViewById(R.id.add_ord_cjzh);
        ord_cjdj = findViewById(R.id.add_ord_cjdj);
        linear_clxx = findViewById(R.id.linear_clxx);


        s_ord_sfyfzqk = findViewById(R.id.add_ord_sfyfzqk);
        ord_fzlx = findViewById(R.id.add_ord_fzlx);
        ord_jzqx = findViewById(R.id.add_ord_jzqx);
        ord_bfqx = findViewById(R.id.add_ord_bfqx);
        linear_sfxx = findViewById(R.id.linear_sfxx);


        s_ord_sfsn = findViewById(R.id.add_ord_sfsn);
        ord_szsm = findViewById(R.id.add_ord_szsm);
        ord_rssj = findViewById(R.id.add_ord_rssj);
        ord_jzryzshm = findViewById(R.id.add_ord_jzryzshm);
        ord_lhlszrr = findViewById(R.id.add_ord_lhlszrr);
        ord_lhlszrrdw = findViewById(R.id.add_ord_lhlszrrdw);
        ord_lhlszrrlxdh = findViewById(R.id.add_ord_lhlszrrlxdh);
        linear_smxx = findViewById(R.id.linear_smxx);

        ord_sskf = findViewById(R.id.add_ord_sskf);
        ord_jlfz = findViewById(R.id.add_ord_jlfz);
        ord_tbfz = findViewById(R.id.add_ord_tbfz);
        ord_xse = findViewById(R.id.add_ord_xse);
        ord_yf = findViewById(R.id.add_ord_yf);
        ord_cs = findViewById(R.id.add_ord_cs);
        ord_apm = findViewById(R.id.add_ord_apm);
        ord_qpm = findViewById(R.id.add_ord_qpm);
        ord_ahqhdw = findViewById(R.id.add_ord_ahqhdw);
        s_ord_fhsz = findViewById(R.id.add_ord_fhsz);
        linear_jsxx = findViewById(R.id.linear_jsxx);
    }
    @Override
    protected void inputDatas() {
        ord_xm.setText(mInfoBean.getOrd_xm());
        ord_zm.setText(mInfoBean.getOrd_zm());
        ord_xb.setText(mInfoBean.getOrd_xb());
        ord_zjlb.setText(mInfoBean.getOrd_zjlb());
        ord_sfz.setText(mInfoBean.getOrd_sfz());
        ord_csrq.setText(mInfoBean.getOrd_csrq());
        ord_nl.setText(mInfoBean.getOrd_nl());
        ord_mz.setText(mInfoBean.getOrd_mz());
        ord_zzmm.setText(mInfoBean.getOrd_zzmm());
        ord_yhzgx.setText(mInfoBean.getOrd_yhzgx());
        ord_hz.setText(mInfoBean.getOrd_hz());
        ord_hzsfz.setText(mInfoBean.getOrd_hzsfz());
        ord_hyzk.setText(mInfoBean.getOrd_hyzk());
        ord_szxq.setText(mInfoBean.getOrd_szxq());
        ord_xjzd.setText(mInfoBean.getOrd_xjzd());
        ord_lxdh.setText(mInfoBean.getOrd_lxdh());

        ord_gwmc.setText(mInfoBean.getOrd_gwmc());

        ord_whcd.setText(mInfoBean.getOrd_whcd());

        ord_szyx.setText(mInfoBean.getOrd_szyx());
        ord_jyzk.setText(mInfoBean.getOrd_jyzk());
        ord_jydw.setText(mInfoBean.getOrd_jydw());
        ord_byzt.setText(mInfoBean.getOrd_byzt());
        ord_zp.setText(mInfoBean.getOrd_zp());

        ord_cyjtjjdxm.setText(mInfoBean.getOrd_cyjtjjdxm());

        ord_gegyfs.setText(mInfoBean.getOrd_gegyfs());

        ord_zdrylx.setText(mInfoBean.getOrd_zdrylx());

        ord_cjzh.setText(mInfoBean.getOrd_cjzh());
        ord_cjdj.setText(mInfoBean.getOrd_cjdj());
        ord_fzlx.setText(mInfoBean.getOrd_fzlx());
        ord_jzqx.setText(mInfoBean.getOrd_jzqx());
        ord_bfqx.setText(mInfoBean.getOrd_bfqx());

        ord_szsm.setText(mInfoBean.getOrd_szsm());
        ord_rssj.setText(mInfoBean.getOrd_rssj());
        ord_jzryzshm.setText(mInfoBean.getOrd_jzryzshm());
        ord_lhlszrr.setText(mInfoBean.getOrd_lhlszrr());
        ord_lhlszrrdw.setText(mInfoBean.getOrd_lhlszrrdw());
        ord_lhlszrrlxdh.setText(mInfoBean.getOrd_lhlszrrlxdh());
        ord_sskf.setText(mInfoBean.getOrd_sskf());
        ord_jlfz.setText(mInfoBean.getOrd_jlfz());
        ord_tbfz.setText(mInfoBean.getOrd_tbfz());
        ord_xse.setText(mInfoBean.getOrd_xse());
        ord_yf.setText(mInfoBean.getOrd_yf());
        ord_cs.setText(mInfoBean.getOrd_cs());
        ord_apm.setText(mInfoBean.getOrd_apm());
        ord_qpm.setText(mInfoBean.getOrd_qpm());
        ord_ahqhdw.setText(mInfoBean.getOrd_ahqhdw());
        RxLogTool.e(mInfoBean.getOrd_fhsz());
        s_ord_fhsz.setChecked(mInfoBean.getOrd_fhsz().equals("是"));
        s_ord_sfcygyxgw.setChecked(mInfoBean.getOrd_sfcygyxgw().equals("是"));
        s_ord_sfsn.setChecked(mInfoBean.getOrd_sfsn().equals("是"));
        s_ord_sfyfzqk.setChecked(mInfoBean.getOrd_sfyfzqk().equals("是"));
        s_ord_sfwcjr.setChecked(mInfoBean.getOrd_sfwcjr().equals("是"));
        s_ord_sfwlset.setChecked(mInfoBean.getOrd_sfwlset().equals("是"));
        s_ord_sfwzdry.setChecked(mInfoBean.getOrd_sfwzdry().equals("是"));
        s_ord_sfwnctk.setChecked(mInfoBean.getOrd_sfwnctk().equals("是"));
        s_ord_sfwcstk.setChecked(mInfoBean.getOrd_sfwcstk().equals("是"));
        s_ord_sfwge.setChecked(mInfoBean.getOrd_sfwge().equals("是"));
        s_ord_sfcyjtjj.setChecked(mInfoBean.getOrd_sfcyjtjj().equals("是"));
        s_ord_sfzd.setChecked(mInfoBean.getOrd_sfzd().equals("是"));
        s_ord_sfcyxnb.setChecked(mInfoBean.getOrd_sfcyxnb().equals("是"));
        s_ord_sfcyxnh.setChecked(mInfoBean.getOrd_sfcyxnh().equals("是"));
        s_ord_sfcydbylbx.setChecked(mInfoBean.getOrd_sfcydbylbx().equals("是"));
        if (ord_xb.getText().toString().equals("女")){
            MyTools.setLinearLayoutVisibility(linear_jsxx,true);
        }
    }

    private void loadlocal() {
        RxLogTool.e("开始加载本地");
        try {
            //个人
            Map<String, String> personMap = SaveTool.getPerson();
            String json = personMap.get(mID);
            UpPersonBean upPersonBean = new Gson().fromJson(json, UpPersonBean.class);
            mInfoBean = upPersonBean.getInfoBeans().get(0);
            if (mInfoBean != null) {
                inputDatas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
