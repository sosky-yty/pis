package com.example.sosky.pis_copy.ui.fg;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sosky.pis_copy.ApiManger;
import com.example.sosky.pis_copy.MyApp;
import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.bean.MsgBean;
import com.example.sosky.pis_copy.bean.UpFamilyInfoBean;
import com.example.sosky.pis_copy.bean.UpGrasslandBean;
import com.example.sosky.pis_copy.bean.UpLowInsuranceBean;
import com.example.sosky.pis_copy.bean.UpMedicalBean;
import com.example.sosky.pis_copy.bean.UpNewAgriculturalBean;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.example.sosky.pis_copy.bean.UpPovertyBean;
import com.example.sosky.pis_copy.bean.UpResidualBean;
import com.example.sosky.pis_copy.bean.UpSeekHelpBean;
import com.example.sosky.pis_copy.bean.UpVeryStrickenBean;
import com.example.sosky.pis_copy.ui.LoginActivity;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.dialog.RxDialogSure;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.List;
import java.util.Map;

public class MineFragment extends BaseFragment {
    private Button btnRelogin;
    private Button btnDownload;
    private Button btnSync;
    private TextView tvPan;

    @Override
    protected int getContentID() {
        return R.layout.fg_mine_main;
    }

    @Override
    public boolean onBackePressed() {
        return false;

    }

    /**
     * 绑定监听器
     */
    @Override
    protected void BindListener() {
        btnRelogin.setOnClickListener(view -> {
            MyApp.removeCookie();
            MyApp.removePassword();
            RxActivityTool.skipActivityAndFinish(getActivity(), LoginActivity.class);
        });

        btnDownload.setOnClickListener(v -> {
            final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(getContext());
            rxDialogSureCancel.getTitleView().setText("重要提示");
            rxDialogSureCancel.setContent("下载会清空手机当前所有已填写的数据\n可能导致数据丢失\n确定下载?");
            rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    download();
                    rxDialogSureCancel.cancel();
                }
            });
            rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rxDialogSureCancel.cancel();
                }
            });
            rxDialogSureCancel.show();
        });

        btnSync.setOnClickListener(view -> {
            RxDialogSure dialogSure = new RxDialogSure(mContext);
            dialogSure.setTitle("提示信息");
            dialogSure.setContent("请点击确定开始上传,请不要多次上传");
            dialogSure.setSureListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String xml = "";
                    Map<String, String> PersonsMap = SaveTool.getPerson();
                    for (Map.Entry<String, String> entry : PersonsMap.entrySet()) {
                        String xml1 = MyTools.JSON2xml(entry.getValue());
                        String substring = xml1.substring(xml1.indexOf("\n"));
                        xml = xml + substring;
                    }
                    ApiManger.upKeyPerson(xml, new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            String json1 = MyTools.xml2JSON(response.body());
                            MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                            tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                            if (msgBean.getDataset().getT1().getCode() == 1) {
                                //todo 成功之后
                                tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));

                                upMore();
                            } else {
                                //todo 失败逻辑
                                tvPan.setTextColor(Color.RED);
                            }
                            RxLogTool.e(response.body());
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);

                        }
                    });
                    dialogSure.dismiss();
                }
            });
            dialogSure.show();
        });
    }

    /**
     * 从服务器下载所有数据
     */
    private void download() {
        dlperson();
        dlfamily();
        dlgrassland();
        dllowinsurance();
        dlmedical();
        dlnewAgricultural();
        dlpoverty();
        dlresidual();
        dlseekhelp();
        dlverystriken();

    }

    private void dlverystriken() {
        ApiManger.downLoadTekun(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("特困信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpVeryStrickenBean veryStrickenBean = gson.fromJson(json, UpVeryStrickenBean.class);
                    List<UpVeryStrickenBean.InfoBean> infoBeans = veryStrickenBean.getInfoBeans();
                    for (UpVeryStrickenBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneVeryStricken(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlresidual() {
        ApiManger.downLoadCanlian(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("残联信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpResidualBean residualBean = gson.fromJson(json, UpResidualBean.class);
                    List<UpResidualBean.InfoBean> infoBeans = residualBean.getInfoBeans();
                    for (UpResidualBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneResidual(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlseekhelp() {
        ApiManger.downLoadLingshijiuzhu(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("临时救助信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpSeekHelpBean seekHelpBean = gson.fromJson(json, UpSeekHelpBean.class);
                    List<UpSeekHelpBean.InfoBean> infoBeans = seekHelpBean.getInfoBeans();
                    for (UpSeekHelpBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneSeekHelp(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlpoverty() {
        ApiManger.downLoadJingzhunfuping(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("精准信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpPovertyBean povertyBean = gson.fromJson(json, UpPovertyBean.class);
                    List<UpPovertyBean.InfoBean> infoBeans = povertyBean.getInfoBeans();
                    for (UpPovertyBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOnePoverty(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlnewAgricultural() {
        ApiManger.downLoadNongbao(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("新农保信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpNewAgriculturalBean newAgriculturalBean = gson.fromJson(json, UpNewAgriculturalBean.class);
                    List<UpNewAgriculturalBean.InfoBean> infoBeans = newAgriculturalBean.getInfoBeans();
                    for (UpNewAgriculturalBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneNewAgricultuaral(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlmedical() {
        ApiManger.downLoadYibao(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("医保信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpMedicalBean medicalBean = gson.fromJson(json, UpMedicalBean.class);
                    List<UpMedicalBean.InfoBean> infoBeans = medicalBean.getInfoBeans();
                    for (UpMedicalBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneMedical(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dllowinsurance() {
        ApiManger.downLoadDibaoxx(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("低保信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpLowInsuranceBean lowInsuranceBean = gson.fromJson(json, UpLowInsuranceBean.class);
                    List<UpLowInsuranceBean.InfoBean> infoBeans = lowInsuranceBean.getInfoBeans();
                    for (UpLowInsuranceBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneLowInsurance(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlgrassland() {
        ApiManger.downLoadCaoyuanshengtai(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("草原生态保护奖励补助资金登记信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpGrasslandBean grasslandBean = gson.fromJson(json, UpGrasslandBean.class);
                    List<UpGrasslandBean.InfoBean> infoBeans = grasslandBean.getInfoBeans();
                    for (UpGrasslandBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneGrassland(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlfamily() {
        ApiManger.downLoadFamily(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("家庭信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpFamilyInfoBean familyInfoBean = gson.fromJson(json, UpFamilyInfoBean.class);
                    List<UpFamilyInfoBean.InfoBean> infoBeans = familyInfoBean.getInfoBeans();
                    for (UpFamilyInfoBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOneFamily(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void dlperson() {
        ApiManger.downLoadKeyPerson(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                tvPan.append("个人信息下载完成,正在解析\n");
                String json = transString(body);
                if (null == json || TextUtils.isEmpty(json)) {
                    return;
                }

                try {
                    Gson gson = new Gson();
                    UpPersonBean personBean = gson.fromJson(json, UpPersonBean.class);
                    List<UpPersonBean.InfoBean> infoBeans = personBean.getInfoBeans();
                    for (UpPersonBean.InfoBean bean : infoBeans) {
                        SaveTool.saveOnePerson(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Nullable
    private String transString(String body) {
        if (null == body) {
            return "";
        }
        //验证
        if (body.contains("下载失败")) {
            tvPan.append("下载失败，请联系数据管理员开通下载通道或者审核数据\n");
            return "";
        }
        body = body.replace("<t1>", "<info>");
        body = body.replace("</t1>", "</info>");

        RxLogTool.e("TAG", body);
        String json = MyTools.xml2JSON(body);
        json = json.replace("\"dataset\" :", "");
        json = json.substring(json.indexOf("{") + 1, json.lastIndexOf("}"));
        RxLogTool.e("TAG", json);
        return json;
    }

    /**
     * 上传更多接口数据 todo
     */
    private void upMore() {

        tvPan.append("正在上传家庭信息\n");
        upFamily();
        tvPan.append("正在上传草原生态信息\n");
        upGrassland();
        tvPan.append("正在上传低保信息\n");
        uplowinsuranceBean();
        tvPan.append("正在上传医保信息\n");
        upMedical();
        tvPan.append("正在上传新农保信息\n");
        upNewAgricultural();
        tvPan.append("正在上传精准扶贫信息\n");
        upPoverty();
        tvPan.append("正在上传残联信息\n");
        upResidual();
        tvPan.append("正在上传临时补助信息\n");
        upSeekHelp();
        tvPan.append("正在上特困信息\n");
        upVeryStriken();
    }

    /**
     * 初始化控件
     *
     * @param view 填充view
     */
    @Override
    protected void initView(View view) {
        super.initView(view);
        FragmentActivity activity = getActivity();
        btnRelogin = activity.findViewById(R.id.btn_relogin);
        btnDownload = activity.findViewById(R.id.btn_download);
        btnSync = activity.findViewById(R.id.btn_sync);
        tvPan = activity.findViewById(R.id.tv_pan);
    }

    private void upFamily() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> familys = SaveTool.getFamilys();
        for (Map.Entry<String, String> entry : familys.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upFamily(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void upGrassland() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> grassland = SaveTool.getGrassland();
        for (Map.Entry<String, String> entry : grassland.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upCaoyuanshengtai(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void uplowinsuranceBean() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> lowInsurances = SaveTool.getLowInsurance();
        for (Map.Entry<String, String> entry : lowInsurances.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upDibaoxx(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }


    private void upMedical() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> medical = SaveTool.getMedical();
        for (Map.Entry<String, String> entry : medical.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upYibao(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void upNewAgricultural() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> newAgricultural = SaveTool.getNewAgricultural();
        for (Map.Entry<String, String> entry : newAgricultural.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upNongbao(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void upPoverty() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> poverty = SaveTool.getPoverty();
        for (Map.Entry<String, String> entry : poverty.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upJingzhunfuping(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void upResidual() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> residual = SaveTool.getResidual();
        for (Map.Entry<String, String> entry : residual.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upCanlian(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void upSeekHelp() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> seekHelp = SaveTool.getSeekHelp();
        for (Map.Entry<String, String> entry : seekHelp.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upLingshijiuzhu(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }

    private void upVeryStriken() {
        StringBuffer json = new StringBuffer();
        String xml = "";
        Map<String, String> veryStricken = SaveTool.getVeryStricken();
        for (Map.Entry<String, String> entry : veryStricken.entrySet()) {
            String xml1 = MyTools.JSON2xml(entry.getValue());
            String substring = xml1.substring(xml1.indexOf("\n"));
            xml = xml + substring;
        }
        ApiManger.upTekun(xml, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json1 = MyTools.xml2JSON(response.body());
                MsgBean msgBean = new Gson().fromJson(json1, MsgBean.class);
                tvPan.append(msgBean.getDataset().getT1().getMessage() + "\n");
                if (msgBean.getDataset().getT1().getCode() == 1) {
                    //todo 成功之后
                    tvPan.setTextColor(getContext().getResources().getColor(R.color.md_green_700));
                } else {
                    //todo 失败逻辑
                    tvPan.setTextColor(Color.RED);
                }
                RxLogTool.e(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });
    }


}
