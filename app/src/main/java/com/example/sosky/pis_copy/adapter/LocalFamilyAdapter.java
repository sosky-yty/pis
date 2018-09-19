package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.bean.UpFamilyInfoBean;
import com.example.sosky.pis_copy.ui.AddFamilyMainActivity;

import java.util.List;

public class LocalFamilyAdapter extends BaseQuickAdapter<UpFamilyInfoBean.InfoBean, BaseViewHolder> {
    private final List<UpFamilyInfoBean.InfoBean> mList;
    Context mContext;


    public LocalFamilyAdapter(List<UpFamilyInfoBean.InfoBean> datas, Context context) {
        super(R.layout.item_family, datas);

        mList = datas;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UpFamilyInfoBean.InfoBean item) {

        helper.setText(R.id.tv_name_it_family, item.getOrd_hz());
        String hzsfz = item.getOrd_hzsfz();
        helper.setText(R.id.tv_num_it, hzsfz);

        helper.getView(R.id.it_family).setOnClickListener(view -> {
            Intent i = new Intent(mContext, AddFamilyMainActivity.class);
            i.putExtra("id", hzsfz);
            i.putExtra("action", "local");
            mContext.startActivity(i);
        });
    }

}
