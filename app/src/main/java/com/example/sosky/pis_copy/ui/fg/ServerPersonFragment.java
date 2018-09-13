package com.example.sosky.pis_copy.ui.fg;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.adapter.ServerPersonAdapter;
import com.example.sosky.pis_copy.base.BaseFragment;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

public class ServerPersonFragment extends BaseFragment {
    //    private RxTitle rxTitle;
    private EditText editSearch;
    private RecyclerView recycler;
    List<UpPersonBean.InfoBean> datas = new ArrayList<>();
    public static Button btnSearch;


    private EditText editSearch1;
    private Button btnSearch3;
    private RecyclerView recyclerImportant;


    private ServerPersonAdapter mKeyPersonAdapter;


    /**
     * 关联布局文件
     *
     * @return 资源ID
     */
    @Override
    protected int getContentID() {
        return R.layout.fg_serverperson;
    }


    @Override
    protected void onFragmentVisiableChange(boolean isVisiable) {
        super.onFragmentVisiableChange(isVisiable);
        if (isVisiable) {
            //load();
        }
    }

    @Override
    public boolean onBackePressed() {
        return false;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        Activity root = getActivity();

        recycler = root.findViewById(R.id.recycler_important);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        //本地缓存加载
        String json = SaveTool.getServerPerson();
        if (!"".equals(json)) {
            try {
                UpPersonBean bean = new Gson().fromJson(json, UpPersonBean.class);
                List<UpPersonBean.InfoBean> data = bean.getInfoBeans();

                mKeyPersonAdapter = new ServerPersonAdapter(data, getActivity());
                recycler.setAdapter(mKeyPersonAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        mKeyPersonAdapter = new ServerPersonAdapter(datas, getActivity());
        mKeyPersonAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recycler.setAdapter(mKeyPersonAdapter);
        //load();
        //  btnSearch.setOnClickListener(v -> load());

    }

//    /**
//     * 请求服务器
//     */
//    public void load() {
//
//        ApiManager.getKeyPerson(new StringCallback() {
//
//            @Override
//            public void onSuccess(Response<String> response) {
//
//                try {
//                    RxLogTool.e("onSuccess");
//                    RxLogTool.e(response.message());
//                    RxLogTool.e(response.code());
//                    RxLogTool.e(response.body());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                String json = MyTools.xml2JSON(response.body());
//                String xml = MyTools.JSON2xml(json);
//                RxLogTool.e(json);
//                RxLogTool.e(xml);
//                KeyPersonBean bean = new Gson().fromJson(json, KeyPersonBean.class);
//
//                try {
//                    List<KeyPersonBean.DatasetBean.T1Bean> data = bean.getDataset().getT1();
//
//                    mKeyPersonAdapter = new KeyPersonAdapter(data, getActivity());
//                    recycler.setAdapter(mKeyPersonAdapter);
//                    SaveTool.saveServerperson(json);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                try {
//                    RxLogTool.e("失败,error");
//                    RxLogTool.e(response.message());
//                    RxLogTool.e(response.code());
//                    RxLogTool.e(response.body());
//
//                    RxToast.normal("无法连接到服务器");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//            }
//        });
//    }
}
