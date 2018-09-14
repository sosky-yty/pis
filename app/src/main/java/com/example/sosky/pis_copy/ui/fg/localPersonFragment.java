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
import com.example.sosky.pis_copy.adapter.LocalPersonAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.example.sosky.pis_copy.ui.addKeyPersonActivity;
import com.google.gson.Gson;
import com.vondear.rxtools.RxLogTool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class localPersonFragment extends BaseFragment {
    private RecyclerView recyclerView;
    List<UpPersonBean.InfoBean> datas = new ArrayList<>();
    private Button btnAdd;

    private LocalPersonAdapter mKeyPersonAdapter;

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

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Activity root = getActivity();
        recyclerView = root.findViewById(R.id.recycler_local);
        btnAdd = root.findViewById(R.id.btn_add_person);

        mKeyPersonAdapter = new LocalPersonAdapter(datas, getActivity());
        mKeyPersonAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mKeyPersonAdapter);
        loadDatas();

        btnAdd.setOnClickListener(view1 -> {
            Intent i = new Intent(mContext, addKeyPersonActivity.class);
            i.putExtra("action", "person");
            mContext.startActivity(i);
        });

    }

    //加载储存在本地的数据
    private void loadDatas() {
        try{
        Map<String, String> personMap = SaveTool.getPerson();

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
            RxLogTool.d(value);
        }

        List<UpPersonBean.InfoBean> info = beans.getInfoBeans();
        mKeyPersonAdapter = new LocalPersonAdapter(info, getActivity());
        recyclerView.setAdapter(mKeyPersonAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
