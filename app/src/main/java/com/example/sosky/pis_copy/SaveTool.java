package com.example.sosky.pis_copy;

import android.util.ArrayMap;

import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.example.sosky.pis_copy.bean.UpXumuInfoBean;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;

import java.util.ArrayList;
import java.util.Map;

/**
 * 保存数据
 */
public class SaveTool {
    static Gson gson = new Gson();

    /**
     *
     * @param id 身份证
     */
    public static void clearPerson(String id){
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(),"persons");
        mSPHelper.remove(id);
    }

    /**
     *
     * @param id 户主身份证
     */
    public static void clearXumu(String id){
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(),"xumus");
        mSPHelper.remove(id);
    }

    /**
     * 通过身份证标识　进行存取
     * @param infoBean
     */
    public static void saveOnePerson(UpPersonBean.InfoBean infoBean){
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(),"persons");
        UpPersonBean mbean = new UpPersonBean();

        mbean.setInfoBeans(new ArrayList<UpPersonBean.InfoBean>());
        mbean.getInfoBeans().add(infoBean);
        String json = gson.toJson(mbean);
        mSPHelper.put(infoBean.getOrd_sfz(),json);
        RxLogTool.d(json);
    }

    /**
     * 通过户主身份证　进行存取
     * @param infoBean
     */
    public static void saveOneXumu(UpXumuInfoBean.InfoBean infoBean){
        SPHelper mSPHelper = new SPHelper(MyApp.getContext(),"xumus");
        UpXumuInfoBean upXumuInfoBean = new UpXumuInfoBean();

        upXumuInfoBean.setInfoBeans(new ArrayList<UpXumuInfoBean.InfoBean>());
        upXumuInfoBean.getInfoBeans().add(infoBean);
        String json = gson.toJson(upXumuInfoBean);
        mSPHelper.put(infoBean.getOrd_hzsfz(),json);
        RxLogTool.d(json);
    }


    public static Map<String,String> getPerson(){
        SPHelper msp = new SPHelper(MyApp.getContext(),"persons");
        Map<String,String> map = (Map<String,String>) msp.getAlL();
        if (map == null){
            map = new ArrayMap<>();
        }
        return map;
    }

    public static Map<String,String> getXumu(){
        SPHelper msp = new SPHelper(MyApp.getContext(),"xumus");
        Map<String,String> map = (Map<String,String>) msp.getAlL();
        if (map == null){
            map = new ArrayMap<>();
        }
        return map;
    }

}
