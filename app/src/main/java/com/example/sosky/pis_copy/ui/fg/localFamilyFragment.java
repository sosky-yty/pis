package com.example.sosky.pis_copy.ui.fg;

import android.app.Activity;
import android.content.Intent;
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
import com.example.sosky.pis_copy.adapter.LocalFamilyAdapter;
import com.example.sosky.pis_copy.adapter.LocalPersonAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.bean.UpFamilyInfoBean;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.example.sosky.pis_copy.ui.AddFamilyMainActivity;
import com.example.sosky.pis_copy.ui.AddPersonMainActivity;
import com.example.sosky.pis_copy.ui.addKeyFamilyActivity;
import com.google.gson.Gson;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class localFamilyFragment extends BaseFragment {
    private static RecyclerView recycler;
    List<UpFamilyInfoBean.InfoBean> datas = new ArrayList<>();
    private Button btnAdd;
    private Spinner typeFamily;
    private EditText editFamily;
    private Button searchFamily;
    private static List<UpFamilyInfoBean.InfoBean> infos;

    private static LocalFamilyAdapter mFamilyAdapter;

    @Override
    protected int getContentID() {
        return R.layout.fg_localfamily;
    }


    @Override
    public boolean onBackePressed() {
        return false;
    }

    @Override
    protected void onFragmentVisiableChange(boolean isVisiable) {
        super.onFragmentVisiableChange(isVisiable);
        if (isVisiable) {
            load();
            btnAdd.setOnClickListener(view1 -> {
                Intent i = new Intent(mContext, AddFamilyMainActivity.class);
                i.putExtra("action", "new");
                mContext.startActivity(i);
            });
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        Activity root = getActivity();
        recycler = root.findViewById(R.id.recycler_local_xumu);
        btnAdd = root.findViewById(R.id.btn_add_xumu);
        typeFamily = (Spinner) getActivity().findViewById(R.id.type_family);
        editFamily = (EditText) getActivity().findViewById(R.id.edit_family);
        searchFamily = (Button) getActivity().findViewById(R.id.search_family);

        mFamilyAdapter = new LocalFamilyAdapter(datas, getActivity());
        mFamilyAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mFamilyAdapter);
        load();

        btnAdd.setOnClickListener(view1 -> {
            Intent i = new Intent(mContext, addKeyFamilyActivity.class);
            i.putExtra("action", "new");
            mContext.startActivity(i);
        });

        searchFamily.setOnClickListener(view1 -> {
            String type = typeFamily.getSelectedItem().toString();
            String key = editFamily.getText().toString();
            if (type.equals("查找类型")) {
                RxToast.error("请选择查询类型");
                return;
            }
            //通过身份证查询
            if (type.equals("身份证") && key.length() >= 6) {
                UpFamilyInfoBean.InfoBean infoBean = new UpFamilyInfoBean.InfoBean();
                infoBean = searchById(key);
                if (!TextUtils.isEmpty(infoBean.getOrd_hzsfz())) {
                    Intent i = new Intent(mContext, AddPersonMainActivity.class);
                    i.putExtra("id", infoBean.getOrd_hzsfz());
                    i.putExtra("action", "local");
                    mContext.startActivity(i);
                } else {
                    RxToast.error("不存在此人，请确认身份证后重新查询");
                }
            }
            //通过姓名查询
            if (type.equals("姓名") && key.length() < 20) {
                List<UpFamilyInfoBean.InfoBean> infoBeans = new ArrayList<UpFamilyInfoBean.InfoBean>();
                infoBeans = searchByName(key);
                if (infoBeans.isEmpty()) {
                    RxToast.error("不存在此人");
                    return;
                }
                if (infoBeans.size() == 1) {
                    Intent i = new Intent(mContext, AddPersonMainActivity.class);
                    i.putExtra("id", infoBeans.get(0).getOrd_hzsfz());
                    i.putExtra("action", "local");
                    mContext.startActivity(i);
                } else if (infoBeans.size() > 1) {
                    refreshByBeans(infoBeans);
                }
            }
        });

    }

    /**
     * 给定内容刷新列表
     */
    public void refreshByBeans(List<UpFamilyInfoBean.InfoBean> infoBeans) {
        mFamilyAdapter = new LocalFamilyAdapter(infoBeans, getActivity());
        recycler.setAdapter(mFamilyAdapter);
    }

    private void load() {

        try {
            //个人
            Map<String, String> familysMap = SaveTool.getFamilys();

            Collection<String> values = familysMap.values();
            if (values.size() == 0) {
                return;
            }
            UpFamilyInfoBean beans = new UpFamilyInfoBean();
            beans.setInfoBeans(new ArrayList<>());
            for (String value : values) {
                UpFamilyInfoBean bean = new Gson().fromJson(value, UpFamilyInfoBean.class);

                //添加到LIST
                beans.getInfoBeans().add(bean.getInfoBeans().get(0));
            }

            infos = beans.getInfoBeans();
            mFamilyAdapter = new LocalFamilyAdapter(infos, getActivity());
            recycler.setAdapter(mFamilyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 通过身份证查询
     *
     * @param id
     * @return
     */
    public UpFamilyInfoBean.InfoBean searchById(String id) {
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        for (UpFamilyInfoBean.InfoBean infoBean : infos) {
            if (infoBean.getOrd_hzsfz().equals(id)) {
                return infoBean;
            }
        }
        return null;
    }

    /**
     * 通过姓名查询
     *
     * @param name
     * @return
     */
    public List<UpFamilyInfoBean.InfoBean> searchByName(String name) {
        List<UpFamilyInfoBean.InfoBean> infoBeans = new ArrayList<UpFamilyInfoBean.InfoBean>();
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        for (UpFamilyInfoBean.InfoBean infoBean : infos) {
            if (infoBean.getOrd_hz().equals(name)) {
                infoBeans.add(infoBean);
            }
        }
        return infoBeans;
    }
}
