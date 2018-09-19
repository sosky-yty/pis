package com.example.sosky.pis_copy;

import android.util.ArrayMap;

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
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;

import java.util.ArrayList;
import java.util.Map;

/**
 * 保存数据 todo 接口开发方法
 */
public class SaveTool {
    static Gson gson = new Gson();

    /**
     * 根据id删除
     *
     * @param id 身份证
     */
    public static void clearPerson(String id) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "persons");
        mSPHelper.remove(id);
    }

    /**
     * 根据id删除家庭
     *
     * @param id 户主身份证
     */
    public static void clearFamily(String id) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "familys");
        mSPHelper.remove(id);
    }

    /**
     * 根据id删除残联
     *
     * @param id 身份证
     */
    public static void clearResidual(String id) {
        SPHelper mSPHlper = new SPHelper(MyApp.getContext(), "clxx");
        mSPHlper.remove(id);
    }

    /**
     * 根据id删除精准扶贫
     *
     * @param id 身份证
     */
    public static void clearPoverty(String id) {
        SPHelper mSPHlper = new SPHelper(MyApp.getContext(), "jzfp");
        mSPHlper.remove(id);
    }

    /**
     * 根据id删除临时救助
     *
     * @param id 户主身份证
     */
    public static void clearSeekHelp(String id) {
        SPHelper mSPHlper = new SPHelper(MyApp.getContext(), "lsjz");
        mSPHlper.remove(id);
    }

    /**
     * 根据id删除特困
     *
     * @param id 身份证
     */
    public static void clearVeryStricken(String id) {
        SPHelper mSPHlper = new SPHelper(MyApp.getContext(), "tkxx");
        mSPHlper.remove(id);
    }


    /**
     * 根据id删除新农保
     *
     * @param id 身份证
     */
    public static void clearNewAgricultural(String id) {
        SPHelper mSPHlper = new SPHelper(MyApp.getContext(), "xnb");
        mSPHlper.remove(id);
    }

    /**
     * 根据id删除医保
     *
     * @param id 身份证
     */
    public static void clearMedical(String id) {
        SPHelper mSPHlper = new SPHelper(MyApp.getContext(), "ybxx");
        mSPHlper.remove(id);
    }

    /**
     * 根据id删除低保
     *
     * @param id
     */
    public static void clearLowInsurance(String id) {
        SPHelper spHelper = new SPHelper(MyApp.getContext(), "dbxx");
        spHelper.remove(id);
    }

    /**
     * 根据id删除草原补助
     *
     * @param id
     */
    public static void clearGrassland(String id) {
        SPHelper spHelper = new SPHelper(MyApp.getContext(), "cybz");
        spHelper.remove(id);
    }


    /**
     * 保存一个 个人信息
     * todo bean抽象类 存取抽象方法
     * 通过身份证标识　进行存取
     *
     * @param infoBean
     */
    public static void saveOnePerson(UpPersonBean.InfoBean infoBean) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "persons");
        UpPersonBean mbean = new UpPersonBean();

        if (infoBean==null){
            return;
        }
        mbean.setInfoBeans(new ArrayList<UpPersonBean.InfoBean>());
        mbean.getInfoBeans().add(infoBean);
        String json = gson.toJson(mbean);
        mSPHelper.put(infoBean.getOrd_sfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过户主身份证　进行存取
     *
     * @param infoBean
     */
    public static void saveOneFamily(UpFamilyInfoBean.InfoBean infoBean) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "familys");
        UpFamilyInfoBean upXumuInfoBean = new UpFamilyInfoBean();
        if (infoBean==null){
            return;
        }
        upXumuInfoBean.setInfoBeans(new ArrayList<UpFamilyInfoBean.InfoBean>());
        upXumuInfoBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upXumuInfoBean);
        mSPHelper.put(infoBean.getOrd_hzsfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过身份证存取　残联信息
     *
     * @param infoBean
     */
    public static void saveOneResidual(UpResidualBean.InfoBean infoBean) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "clxx");
        UpResidualBean upResidualBean = new UpResidualBean();
        if (infoBean==null){
            return;
        }
        upResidualBean.setInfoBeans(new ArrayList<UpResidualBean.InfoBean>());
        upResidualBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upResidualBean);
        mSPHelper.put(infoBean.getOrd2_sfz(), json);
        RxLogTool.d(json);
    }


    /**
     * 通过户主身份证存取　精准扶贫
     *
     * @param infoBean
     */
    public static void saveOnePoverty(UpPovertyBean.InfoBean infoBean) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "jzfp");
        UpPovertyBean upPovertyBean = new UpPovertyBean();
        if (infoBean==null){
            return;
        }
        upPovertyBean.setInfoBeans(new ArrayList<UpPovertyBean.InfoBean>());
        upPovertyBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upPovertyBean);
        mSPHelper.put(infoBean.getOrd2_hzsfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过户主身份证存取 临时救助
     *
     * @param infoBean
     */
    public static void saveOneSeekHelp(UpSeekHelpBean.InfoBean infoBean) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "lsjz");
        UpSeekHelpBean upSeekHelpBean = new UpSeekHelpBean();
        if (infoBean==null){
            return;
        }
        upSeekHelpBean.setInfoBeans(new ArrayList<UpSeekHelpBean.InfoBean>());
        upSeekHelpBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upSeekHelpBean);
        mSPHelper.put(infoBean.getOrd2_hzsfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过身份证存取　特困信息
     *
     * @param infoBean
     */
    public static void saveOneVeryStricken(UpVeryStrickenBean.InfoBean infoBean) {
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(), "tkxx");
        UpVeryStrickenBean upVeryStrickenBean = new UpVeryStrickenBean();
        if (infoBean==null){
            return;
        }
        upVeryStrickenBean.setInfoBeans(new ArrayList<UpVeryStrickenBean.InfoBean>());
        upVeryStrickenBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upVeryStrickenBean);
        mSPHelper.put(infoBean.getOrd2_sfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过身份证存取 新农保
     *
     * @param infoBean
     */
    public static void saveOneNewAgricultuaral(UpNewAgriculturalBean.InfoBean infoBean) {
        SPHelper spHelper = new SPHelper(MyApp.getContext(), "xnb");
        UpNewAgriculturalBean upNewAgriculturalBean = new UpNewAgriculturalBean();
        if (infoBean==null){
            return;
        }
        upNewAgriculturalBean.setInfoBeans(new ArrayList<UpNewAgriculturalBean.InfoBean>());
        upNewAgriculturalBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upNewAgriculturalBean);
        spHelper.put(infoBean.getOrd2_sfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过身份证存取 医保信息
     *
     * @param infoBean
     */
    public static void saveOneMedical(UpMedicalBean.InfoBean infoBean) {
        SPHelper spHelper = new SPHelper(MyApp.getContext(), "ybxx");
        UpMedicalBean upMedicalBean = new UpMedicalBean();
        if (infoBean==null){
            return;
        }
        upMedicalBean.setInfoBeans(new ArrayList<UpMedicalBean.InfoBean>());
        upMedicalBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upMedicalBean);
        spHelper.put(infoBean.getOrd2_sfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 通过户主身份证存取　　低保信息
     */
    public static void saveOneLowInsurance(UpLowInsuranceBean.InfoBean infoBean) {
        SPHelper spHelper = new SPHelper(MyApp.getContext(), "dbxx");
        UpLowInsuranceBean upLowInsuranceBean = new UpLowInsuranceBean();
        if (infoBean==null){
            return;
        }
        upLowInsuranceBean.setInfoBeans(new ArrayList<UpLowInsuranceBean.InfoBean>());
        upLowInsuranceBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upLowInsuranceBean);
        spHelper.put(infoBean.getOrd2_hzsfz(), json);
        RxLogTool.d(json);
    }

    /**
     * 草原补助
     *
     * @param infoBean
     */
    public static void saveOneGrassland(UpGrasslandBean.InfoBean infoBean) {
        SPHelper spHelper = new SPHelper(MyApp.getContext(), "cybz");
        UpGrasslandBean upGrasslandBean = new UpGrasslandBean();
        if (infoBean==null){
            return;
        }
        upGrasslandBean.setInfoBeans(new ArrayList<UpGrasslandBean.InfoBean>());
        upGrasslandBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upGrasslandBean);
        spHelper.put(infoBean.getOrd2_hzsfz(), json);
        RxLogTool.d(json);
    }


    /**
     * 获得所有个人信息
     *
     * @return
     */
    public static Map<String, String> getPerson() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "persons");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 根据身份证查个人信息
     * todo 剩下的方法
     *
     * @return
     */
    public static UpPersonBean.InfoBean getOnePerson(String id) {
        SPHelper msp = new SPHelper(MyApp.getContext(), "persons");
        Map<String, String> map = getPerson();
        String json = map.get(id);
        UpPersonBean.InfoBean onePersonBean = new Gson().fromJson(json, UpPersonBean.class).getInfoBeans().get(0);
        return onePersonBean;
    }


    public static UpFamilyInfoBean.InfoBean getOneFamily(String id) {
        SPHelper msp = new SPHelper(MyApp.getContext(), "familys");
        Map<String, String> map = getFamilys();
        String json = map.get(id);
        UpFamilyInfoBean.InfoBean infoBean = new Gson().fromJson(json, UpFamilyInfoBean.class).getInfoBeans().get(0);
        return infoBean;
    }


    /**
     * 获得所有家庭信息
     *
     * @return
     */
    public static Map<String, String> getFamilys() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "familys");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有草原补助信息
     *
     * @return
     */
    public static Map<String, String> getGrassland() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "cybz");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有低保信息
     *
     * @return
     */
    public static Map<String, String> getLowInsurance() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "dbxx");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有医保信息
     *
     * @return
     */
    public static Map<String, String> getMedical() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "ybxx");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有新农保
     *
     * @return
     */
    public static Map<String, String> getNewAgricultural() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "xnb");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有特困信息
     *
     * @return
     */
    public static Map<String, String> getVeryStricken() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "tkxx");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有临时救助
     *
     * @return
     */
    public static Map<String, String> getSeekHelp() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "lsjz");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有残联信息
     *
     * @return
     */
    public static Map<String, String> getResidual() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "clxx");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }

    /**
     * 获得所有救助扶贫
     *
     * @return
     */
    public static Map<String, String> getPoverty() {
        SPHelper msp = new SPHelper(MyApp.getContext(), "jzfp");
        Map<String, String> map = (Map<String, String>) msp.getAlL();
        if (map == null) {
            map = new ArrayMap<>();
        }
        return map;
    }


}
