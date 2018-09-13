package com.example.sosky.pis_copy.adapter;

import android.content.Context;
import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.example.sosky.pis_copy.ui.EditPersonActivity;

import java.util.List;

public class ServerPersonAdapter extends BaseQuickAdapter<UpPersonBean.InfoBean, BaseViewHolder> {
    List<UpPersonBean.InfoBean> mList;
    Context mContext;


    public ServerPersonAdapter(List<UpPersonBean.InfoBean> datas, Context context) {
        super(R.layout.item_people, datas);
        mList = datas;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UpPersonBean.InfoBean item) {

        helper.setText(R.id.tv_name_it, item.getOrd_xm());
        helper.setText(R.id.tv_sex_it, item.getOrd_xb());
        helper.setText(R.id.tv_area_it, item.getOrd_szxq());

        helper.getView(R.id.it_people).setOnClickListener(view -> {

            Intent i = new Intent(mContext, EditPersonActivity.class);
            i.putExtra("id", item.getOrd_sfz());
            i.putExtra("action", "person");
            mContext.startActivity(i);

        });
    }
}