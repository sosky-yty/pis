package com.example.sosky.pis_copy.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.view.RxTitle;

public class addKeyPersonActivity extends BaseActivity{


    String[] minzu = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族",
            "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族",
            "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族",
            "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"};

    String[] sex = {"男", "女"};
    String[] hy = {"未婚", "已婚", "离异"};
    //证件类型
    String[] sfzlb ={"身份证","非身份证"};
    //政治面貌
    String[] zzmm={"群众","党员","团员","预备党员"};
    //与户主关系
    String[] yhzgx ={"户主","子女","妻女","子孙"};
    //婚姻状况
    String[] hyzk ={"未婚","已婚","离异"};
    //所在辖区
    String[] szxq ={"光明社区","吉祥社区","团结社区","安康社区","解放一村","解放二村","约若二村","姑咱一村","姑咱二村","幸福一村","幸福二村"};

    //文化程度
    String[] whcd={"文盲","小学","中学","高中（含中专）","大学专科","大学本科","研究生","博士"};
    //就业状态
    String[] jyzt={"待业","就业","失业"};
    //兵役状态
    String[] byzt={"已服","未服"};
    //参与集体经济项目
    String[] cyjtjjxm={"乡村酒店","大棚蔬菜","瓦须黑帐篷体验园区","生态畜牧专业合作"};

    String[] zdrylx={"教职人员","矫正人员","帮扶人员","刑满释放人员","反分裂分子","时轮金刚法会回流人员"};

    String[] yf = {"1","2","3","4","5","6","7","8","9","10"};

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




    @Override
    protected int getContentID() {
        return R.layout.activity_addkeyperson;
    }


    /**
     * 绑定view
     */
    @Override
    protected  void bindView(){
        mContext = this;
        rxTitle = findViewById(R.id.rx_title);

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

        s_ord_sfcygyxgw = findViewById(R.id.add_ord_sfcygyxgw) ;
        ord_gwmc = findViewById(R.id.add_ord_gwmc);
        linear_gwmc = findViewById(R.id.linear_gwmc);

        s_ord_sfcyxnb = findViewById(R.id.add_ord_sfcyxnb);
        s_ord_sfcyxnh = findViewById(R.id.add_ord_sfcyxnh);
        s_ord_sfcydbylbx = findViewById(R.id.add_ord_sfcydbylbx);
        ord_whcd = findViewById(R.id.add_ord_whcd);

        s_ord_sfzd = findViewById(R.id.add_ord_sfzd);
        ord_szyx = findViewById(R.id.add_ord_szyx);
        linear_szyx =findViewById(R.id.linear_szyx);

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
    };

    /**
     * 初始话view
     */
    @Override
    protected  void initView(){

    };

}
