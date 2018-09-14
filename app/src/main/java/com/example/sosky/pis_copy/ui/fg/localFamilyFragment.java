package com.example.sosky.pis_copy.ui.fg;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.adapter.LocalFamilyAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.bean.UpXumuInfoBean;
import com.example.sosky.pis_copy.ui.addKeyFamilyActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class localFamilyFragment extends BaseFragment {
    private RecyclerView recycler;
    List<UpXumuInfoBean.InfoBean> datas = new ArrayList<>();
    private Button btnAdd;

    private LocalFamilyAdapter mFamilyAdapter;

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
                Intent i = new Intent(mContext, addKeyFamilyActivity.class);
                i.putExtra("action", "xumu");
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

        mFamilyAdapter = new LocalFamilyAdapter(datas, getActivity());
        mFamilyAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mFamilyAdapter);
        load();

        btnAdd.setOnClickListener(view1 -> {
            Intent i = new Intent(mContext, addKeyFamilyActivity.class);
            i.putExtra("action", "xumu");
            mContext.startActivity(i);
        });
    }


    private void load() {

        try {
            //个人
            Map<String, String> personMap = SaveTool.getXumu();

            Collection<String> values = personMap.values();
            if (values.size() == 0) {
                return;
            }
            UpXumuInfoBean beans = new UpXumuInfoBean();
            beans.setInfoBeans(new ArrayList<>());
            for (String value : values) {
                UpXumuInfoBean bean = new Gson().fromJson(value, UpXumuInfoBean.class);

                //添加到LIST
                beans.getInfoBeans().add(bean.getInfoBeans().get(0));
                //  RxLogTool.e(value);
                //  RxLogTool.e(bean.toString());
            }

            List<UpXumuInfoBean.InfoBean> info = beans.getInfoBeans();
            mFamilyAdapter = new LocalFamilyAdapter(info, getActivity());
            recycler.setAdapter(mFamilyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
