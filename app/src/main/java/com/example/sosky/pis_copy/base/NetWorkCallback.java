package com.example.sosky.pis_copy.base;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.view.RxToast;

public abstract class NetWorkCallback extends StringCallback{
    @Override
    public void onSuccess(Response<String> response) {
        try{
            String body = response.body();
            if (body == null || body == ""){
                RxToast.error("服务器出错,无数据");
                return;
            }
            RxLogTool.e("response",body);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void onRealSuccess(String response);

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        if (response.code() > 400){
            RxToast.error("服务器出错!");
            return;
        }
        if (response.code() == -1){
            RxToast.error("网络异常");
            return;
        }
        RxToast.error("未知异常");
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }
}
