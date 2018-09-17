package com.example.sosky.pis_copy;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

public class ApiManger {
    /**
     * 调用api需先登录
     */
    public static String DOMAIN = (String) new SPHelper(MyApp.getContext(), "server").getSharedpreference("address", MyApp.getContext().getString(R.string.address));

    /**
     * 请求地址:http://nat.flobit.cn:94/simpleauth
     * 请求方法:POST/GET
     * 参数:username(用户名),password(密码)
     * 返回结果:JSON字符串
     * 测试账号:123
     * 密码:123
     * 测试账号:skzjfyc
     * 密码:skzjfyc
     */
    public static final String API_LOGIN = DOMAIN + "/simpleauth";

    /**
     * 获得个人信息
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlgrxx
     * 请求方法:POST/GET
     */
    public static final String API_GET_PERSON_INFO = DOMAIN + "/commonsapi/dlgrxx";

    /**
     * ## 上传个人档案
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getgrxx
     * 请求方法:POST
     * 参数:data 要上传的数据
     */
    public static final String API_UPLOAD_PERSON_INFO = DOMAIN + "/commonsapi/getgrxx";

    /**
     * ## 上传家庭信息
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getjtxx
     * 请求方法:POST
     * 参数:data
     * 请求数据示例
     */
    public static final String API_UPLOAD_FAMILY_INFO = DOMAIN + "/commonsapi/getjtxx";

    /**
     * ## 下载家庭信息(暂时返回所有后续调整)
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dljtxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_Family_INFO = DOMAIN + "/commonsapi/dljtxx";

    /**
     * ## 上传残联登记信息表
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getclxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_RESIDUAL_UNION_INFO = DOMAIN + "/commonsapi/getclxx";

    /**
     * ## 下载残联登记信息表
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlclxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_RESIDUAL_UNION_INFO = DOMAIN + "/commonsapi/dlclxx";

    /**
     * ## 上传新农保信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getxnbxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_LONGBAO_INFO = DOMAIN + "/commonsapi/getxnbxx";

    /**
     * ## 下载新农保信息
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlxnbxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_LONGBAO_INFO = DOMAIN + "/commonsapi/dlxnbxx";

    /**
     * ## 上传医保信息
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getybxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_YIBAO_INFO = DOMAIN + "/commonsapi/getybxx";

    /**
     * ## 下载医保登记表
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlybxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_YIBAO_INFO = DOMAIN + "/commonsapi/dlybxx";

    /**
     * ## 上传临时救助情况登记表
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getlsjzqkxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_TEMPORARY_ASSISTANCE_INFO = DOMAIN + "/commonsapi/getlsjzqkxx";

    /**
     * ## 下载临时救助情况登记表
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dllsjzqkxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_TEMPORARY_ASSISTANCE__INFO = DOMAIN + "/commonsapi/dllsjzqkxx";


    /**
     * ## 上传精准扶贫信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getjzfpxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_SUPPORTING_POOR_INFO = DOMAIN + "/commonsapi/getjzfpxx";

    /**
     * ## 下载精准扶贫信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dljzfpxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_SUPPORTING_POOR_INFO = DOMAIN + "/commonsapi/dljzfpxx";

    /**
     * ## 上传草原生态保护奖励补助资金登记
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getcystbh
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_STBHBZ_INFO = DOMAIN + "/commonsapi/getcystbh";

    /**
     * ## 下载草原生态保护奖励补助资金登记
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlcystb
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_STBHBZ_INFO = DOMAIN + "/commonsapi/dlcystb";


    /**
     * ## 上传民政特困信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getmztkxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_TKXX_INFO = DOMAIN + "/commonsapi/getmztkxx";

    /**
     * ## 下载民政特困信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlmztkxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_TKXX_INFO = DOMAIN + "/commonsapi/dlmztkxx";

    /**
     * ## 上传民政低保信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/getmzdbxx
     * 请求方法:POST
     * 参数:data
     */
    public static final String API_UPLOAD_MZDBXX_INFO = DOMAIN + "/commonsapi/getmzdbxx";

    /**
     * ## 下载民政低保信息录入
     * 请求地址:http://nat.flobit.cn:94/commonsapi/dlmzdbxx
     * 请求方法:POST/GET
     * 参数:无
     */
    public static final String API_GET_MZDBXX_INFO = DOMAIN + "/commonsapi/dlmzdbxx";

    /**
     * //登录成功示例
     * {"error":"","status":"ok"}
     * //登录失败示例
     * {"error":"账号或密码不正确","status":"failed"}
     *
     * @param stringCallback
     */
    public static void login(String username, String password, StringCallback stringCallback) {
        DOMAIN = (String) new SPHelper(MyApp.getContext(), "server").getSharedpreference("address", MyApp.getContext().getString(R.string.address));

        OkGo.<String>post(API_LOGIN)
                .params("username", username)
                .params("password", password)
                .execute(stringCallback);
    }

    /**
     * 自动登录
     *
     * @param stringCallback
     */
    public static void autologin(StringCallback stringCallback) {
        String username, password;
        username = MyApp.SPR.getString("username", "");
        password = MyApp.SPR.getString("password", "");
        OkGo.<String>post(API_LOGIN)
                .params("username", username)
                .params("password", password)
                .execute(stringCallback);
    }

    /**
     * 上传个人信息
     *
     * @param data
     * @param stringCallback
     */
    public static void upKeyPerson(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_PERSON_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 上传家庭信息
     *
     * @param data
     * @param stringCallback
     */
    public static void upFamily(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_FAMILY_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 上传残联登记信息表
     *
     * @param data
     * @param stringCallback
     */
    public static void upCanlian(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_RESIDUAL_UNION_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 上传新农保信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void upNongbao(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_LONGBAO_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 上传医保信息
     *
     * @param data
     * @param stringCallback
     */
    public static void upYibao(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_YIBAO_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 上传临时救助情况登记表
     *
     * @param data
     * @param stringCallback
     */
    public static void upLingshijiuzhu(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_TEMPORARY_ASSISTANCE_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 上传草原生态保护奖励补助资金登记
     *
     * @param data
     * @param stringCallback
     */
    public static void upCaoyuanshengtai(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_STBHBZ_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 上传民政特困信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void upTekun(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_TKXX_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 上传民政低保信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void upDibaoxx(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_MZDBXX_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 上传精准扶贫信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void upJingzhunfuping(String data, StringCallback stringCallback) {
        OkGo.<String>post(API_UPLOAD_SUPPORTING_POOR_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 下载个人信息
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadKeyPerson(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_PERSON_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 下载家庭信息(暂时返回所有后续调整)
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadFamily(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_Family_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 下载残联登记信息表(暂时返回所有后续调整)
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadCanlian(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_RESIDUAL_UNION_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 下载新农保信息
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadNongbao(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_LONGBAO_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 下载医保信息
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadYibao(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_YIBAO_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 下载临时救助情况登记表
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadLingshijiuzhu(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_TEMPORARY_ASSISTANCE__INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 下载精准扶贫信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadJingzhunfuping(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_SUPPORTING_POOR_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


    /**
     * 下载草原生态保护奖励补助资金登记
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadCaoyuanshengtai(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_STBHBZ_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 下载民政低保信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadDibaoxx(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_MZDBXX_INFO)
                .params("data", data)
                .execute(stringCallback);
    }

    /**
     * 下载民政特困信息录入
     *
     * @param data
     * @param stringCallback
     */
    public static void downLoadTekun(String data, StringCallback stringCallback) {
        OkGo.<String>get(API_GET_TKXX_INFO)
                .params("data", data)
                .execute(stringCallback);
    }


}
