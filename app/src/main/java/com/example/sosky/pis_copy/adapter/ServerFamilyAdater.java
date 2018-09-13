package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.bean.UpXumuInfoBean;
import com.example.sosky.pis_copy.ui.EditPersonActivity;

import java.util.List;

public class ServerFamilyAdater extends BaseQuickAdapter<UpXumuInfoBean.InfoBean, BaseViewHolder> {
    private List<UpXumuInfoBean.InfoBean> mList;
    Context mContext;
    public ServerFamilyAdater(List<UpXumuInfoBean.InfoBean> data,Context context) {
        super(R.layout.item_family, data);

        mList = data;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UpXumuInfoBean.InfoBean item) {

        helper.setText(R.id.tv_name_it_family, item.getOrd_hz());
        helper.setText(R.id.tv_num_it, item.getOrd_jtrks());
        helper.setText(R.id.tv_jtzk_it, item.getOrd_jtzk());

        helper.getView(R.id.it_family).setOnClickListener(view -> {

            Intent i = new Intent(mContext, EditPersonActivity.class);
            i.putExtra("id", item.getOrd_hzsfz());
            i.putExtra("action", "xumu");
            mContext.startActivity(i);

        });
    }
}
