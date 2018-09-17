package com.example.sosky.pis_copy;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.vondear.rxtools.RxCrashTool;
import com.vondear.rxtools.RxTool;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class MyApp extends Application{

    private static Context context;

    //本地设置保存对象
    public static SharedPreferences NSP;
    public static SharedPreferences.Editor SPE;
    public static SharedPreferences SPR;
    public static String action = "local";

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        SPE = getSharedPreferences("pis_copy",MODE_PRIVATE).edit();
        SPR = getSharedPreferences("pis_copy",MODE_PRIVATE);

        NSP = PreferenceManager.getDefaultSharedPreferences(this);

        initOKGO();

        RxTool.init(this);
        RxCrashTool.init(getFilesDir()+"/crashs");
    }

    private void initOKGO(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //设置全局响应时间,读取,写入,连接
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        builder.readTimeout(60000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60000,TimeUnit.MILLISECONDS);
        builder.connectTimeout(60000,TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("okgo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor);

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-type", "application/x-www-form-urlencoded");
        headers.put("Content-type", "application/xml");
        headers.put("Accept", "*/*");

        //初始化okgg 重试3次
        OkGo.getInstance()
                .init(this)
                .setOkHttpClient(builder.build())
                .addCommonHeaders(headers)
                .setRetryCount(3);
    }

    public static void removeCookie(){
        CookieStore cookieStore = OkGo.getInstance().getCookieJar().getCookieStore();

        cookieStore.removeAllCookie();
    }

    public static Context getContext(){
        return context;
    }

    public static void savePassword(String um ,String pw){
        SPE.putString("username",um).apply();
        SPE.putString("password",pw).apply();
        SPE.putBoolean("isLogin",true).apply();
    }

    public static boolean isLogin(){
        return SPR.getBoolean("isLogin",false);
    }

    public static void removePassword(){
        SPE.putString("username","").apply();
        SPE.putString("password","").apply();
        SPE.putBoolean("isLogin",false).apply();
    }
}