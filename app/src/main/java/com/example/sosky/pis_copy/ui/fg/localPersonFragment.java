package com.example.sosky.pis_copy.ui.fg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.adapter.LocalPersonAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.example.sosky.pis_copy.ui.AddPersonMainActivity;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;
import com.zyl.customkeyboardview.CustomKeyboardEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class localPersonFragment extends BaseFragment {
    private static RecyclerView recyclerView;
    List<UpPersonBean.InfoBean> datas = new ArrayList<>();
    private static List<UpPersonBean.InfoBean> infos;
    private Button btnAdd;
    private Spinner typePerson;
    private EditText editPerson;
    private Button searchPerson;

    private static Context mContext;
    private static LocalPersonAdapter mKeyPersonAdapter;

    @Override
    protected int getContentID() {
        return R.layout.fg_localperson;
    }

    @Override
    public boolean onBackePressed() {
        return false;
    }

    @Override
    protected void onFragmentVisiableChange(boolean isVisiable) {
        super.onFragmentVisiableChange(isVisiable);
        if (isVisiable) {
            loadDatas();
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Activity root = getActivity();
        recyclerView = root.findViewById(R.id.recycler_local);
        btnAdd = root.findViewById(R.id.btn_add_person);

        typePerson = (Spinner) getActivity().findViewById(R.id.type_person);
        editPerson = (EditText) getActivity().findViewById(R.id.edit_person);
        searchPerson = (Button) getActivity().findViewById(R.id.search_person);


        mContext = getActivity();
        mKeyPersonAdapter = new LocalPersonAdapter(datas, getActivity());
        mKeyPersonAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mKeyPersonAdapter);
        loadDatas();

        btnAdd.setOnClickListener(view1 -> {
            CustomKeyboardEditText.setFlag(true);
            Intent i = new Intent(mContext, AddPersonMainActivity.class);
            i.putExtra("action", "new");
            mContext.startActivity(i);
        });


        searchPerson.setOnClickListener(view1 -> {
            String type =  typePerson.getSelectedItem().toString();
            String key = editPerson.getText().toString();
            if (type.equals("查找类型")){
                RxToast.error("请选择查询类型");
                return ;
            }
            if (type.equals("身份证") && key.length() < 6 || type.equals("姓名") && key.length() > 20){
                RxToast.error("输入不符合规则");
                return;
            }
            //通过身份证查询
            if (type.equals("身份证")&&key.length()>=6){
                UpPersonBean.InfoBean infoBean = new UpPersonBean.InfoBean();
                infoBean = searchById(key);
                if (infoBean != null&&!TextUtils.isEmpty(infoBean.getOrd_sfz())){
                    Intent i = new Intent(mContext, AddPersonMainActivity.class);
                    i.putExtra("id", infoBean.getOrd_sfz());
                    i.putExtra("action", "local");
                    mContext.startActivity(i);
                }else {
                    RxToast.error("不存在此人，请确认身份证后重新查询");
                }
            }
            //通过姓名查询
            if (type.equals("姓名")&& key.length()<20){
                List<UpPersonBean.InfoBean> infoBeans = new ArrayList<UpPersonBean.InfoBean>();
                infoBeans = searchByName(key);
                if (infoBeans == null ||infoBeans.isEmpty()){
                    RxToast.error("不存在此人");
                    return;
                }
                if (infoBeans.size() == 1){
                    Intent i = new Intent(mContext, AddPersonMainActivity.class);
                    i.putExtra("id", infoBeans.get(0).getOrd_sfz());
                    i.putExtra("action", "local");
                    mContext.startActivity(i);
                }else if (infoBeans.size()>1){
                    refreshByBeans(infoBeans);
                }
            }
        });
    }

    /**
     * 刷新
     */
    public static void refresh() {
        loadDatas();
    }

    /**
     * 给定内容刷新列表
     */
    public void refreshByBeans(List<UpPersonBean.InfoBean> infoBeans){
        mKeyPersonAdapter = new LocalPersonAdapter(infoBeans, mContext);
        recyclerView.setAdapter(mKeyPersonAdapter);
    }

    //加载储存在本地的数据
    public static void loadDatas() {
        try {
            Map<String,String> personMap = SaveTool.getPerson();

            Collection<String> values = personMap.values();
            if (values.size() == 0) {
                RxLogTool.e("无数据");
                return;
            }
            UpPersonBean beans = new UpPersonBean();
            beans.setInfoBeans(new ArrayList<>());

            for (String value : values) {
                UpPersonBean bean = new Gson().fromJson(value, UpPersonBean.class);
                beans.getInfoBeans().add(bean.getInfoBeans().get(0));
            }
            infos = beans.getInfoBeans();
            mKeyPersonAdapter = new LocalPersonAdapter(infos, mContext);
            recyclerView.setAdapter(mKeyPersonAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过身份证查询
     * @param id
     * @return
     */
    public UpPersonBean.InfoBean searchById(String id){
        if (TextUtils.isEmpty(id)){
            return  null;
        }
        for (UpPersonBean.InfoBean infoBean :infos){
            if (infoBean.getOrd_sfz().equals(id)){
                return infoBean;
            }
        }
        return null;
    }

    /**
     * 通过姓名查询
     * @param name
     * @return
     */
    public List<UpPersonBean.InfoBean> searchByName(String name){
        List<UpPersonBean.InfoBean> infoBeans = new ArrayList<UpPersonBean.InfoBean>();
        if (TextUtils.isEmpty(name)){
            return null;
        }
        for (UpPersonBean.InfoBean infoBean: infos){
             if (infoBean.getOrd_xm().equals(name)){
                 infoBeans.add(infoBean);
             }
        }
        return infoBeans;
    }
}
