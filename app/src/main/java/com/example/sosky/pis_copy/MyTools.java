package com.example.sosky.pis_copy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sosky.pis_copy.ui.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxPermissionsTool;
import com.vondear.rxtools.RxTool;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import fr.arnaudguyon.xmltojsonlib.JsonToXml;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MyTools {
    public static final int APPTYPE_P = 3;
    public static final int APPTYPE_PS = 2;
    public static final int APPTYPE_PSB = 1;
    /**
     * sp工具
     */
    public static SharedPreferences.Editor preferenceE;
    public static SharedPreferences preferenceR;

    /**
     * 取本地token
     *
     * @return
     */
    public static String getLocalToken() {
        return preferenceR.getString("token", "");
    }


    /**
     * 取本地id
     *
     * @return
     */
    public static String getLocalUserID() {
        return preferenceR.getString("userid", "");
    }

    /**
     * 本地token
     *
     * @return
     */
    public static void putLocalToken(String token) {
        preferenceE.putString("token", token).apply();
    }

    /**
     * put本地id
     *
     * @return
     */
    public static void putLocalUserID(String id) {
        preferenceE.putString("userid", id).apply();
    }

    /**
     * 取本地类型
     *
     * @return
     */
    public static int getAppType() {
        return preferenceR.getInt("app_type", 3);
    }

    /**
     * 设置本地类型
     *
     * @return
     */
    public static void setAppType(int type) {
        preferenceE.putInt("app_type", type).apply();
    }


    /**
     * 同步
     */
    public static void sync() {
        //todo 同步
    }

    /**
     * 取文本中间第一个指定文本
     *
     * @param src
     * @param left
     * @param right
     * @return
     */
    public static String getStringMiddle(String src, String left, String right) {
        if (src.indexOf(left) != -1 && src.indexOf(right) != -1) {
            return src.substring(src.indexOf(left) + left.length(), src.indexOf(right));
        } else {
            return "";
        }
    }

    /**
     * 取文本中间多个指定文本
     *
     * @param src
     * @param left
     * @param right
     * @return
     */
    public static ArrayList<String> getStringMiddles(String src, String left, String right) {
        ArrayList<String> list = new ArrayList<String>();
        while (src.indexOf(left) != -1 && src.indexOf(right) != -1) {
            list.add(src.substring(src.indexOf(left) + left.length(), src.indexOf(right)));
            src = src.substring(src.indexOf(right) + right.length());
        }
        return list;
    }

    private static final Pattern REG_UNICODE = Pattern.compile("[0-9A-Fa-f]{4}");

    /**
     * unicode转中文
     *
     * @param str
     * @return
     */
    public static String unicode2String(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c1 = str.charAt(i);
            if (c1 == '\\' && i < len - 1) {
                char c2 = str.charAt(++i);
                if (c2 == 'u' && i <= len - 5) {
                    String tmp = str.substring(i + 1, i + 5);
                    Matcher matcher = REG_UNICODE.matcher(tmp);
                    if (matcher.find()) {
                        sb.append((char) Integer.parseInt(tmp, 16));
                        i = i + 4;
                    } else {
                        sb.append(c1).append(c2);
                    }
                } else {
                    sb.append(c1).append(c2);
                }
            } else {
                sb.append(c1);
            }
        }
        return sb.toString();
    }

    /**
     * 清除缓存
     */
    public static void clearToken() {

        putLocalToken("");
        try {
            if (MyApp.getContext() != null) {
                RxActivityTool.skipActivityAndFinishAll(MyApp.getContext(), LoginActivity.class);
            } else {
                RxActivityTool.finishAllActivity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取本地缓存JSON
     *
     * @param key
     * @return
     */
    public static String getJSONCache(String key) {
        SharedPreferences sp = MyApp.getContext().getSharedPreferences("JSON_CACHE", 0);
        String jsoncache = sp.getString(key, "");
        return jsoncache;
    }

    /**
     * 本地缓存JSON
     *
     * @param key
     * @param content
     */
    public static void putJSONCache(String key, String content) {
        SharedPreferences sp = MyApp.getContext().getSharedPreferences("JSON_CACHE", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, content);
        editor.apply();
    }

    /**
     * @param json
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }


    /**
     * 替换
     *
     * @param viewId
     * @param fragment
     */
    public static void replaceFragment(AppCompatActivity context, int viewId, android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = context.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    /**
     * 移除
     *
     * @param fragment
     */
    public static void removeFragment(AppCompatActivity context, android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = context.getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(fragment).commit();
    }


    /**
     * 请求权限
     *
     * @param activity
     * @return
     */
    public static List<String> requestPermissions(Activity activity) {
        return RxPermissionsTool.
                with(activity).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.CALL_PHONE).
                addPermission(Manifest.permission.READ_PHONE_STATE).
                initPermission();
    }

    /**
     * 通过HashSet剔除重复元素
     * 删除ArrayList中重复元素,add进去顺序就变了不考虑顺序的话可以使用
     *
     * @param list
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }


    /**
     * 选择弹窗
     *
     * @param items
     * @param context
     * @param editText
     */
    public static void showSelectDialog(String[] items, Context context, TextView editText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.mydialog);
        builder.setTitle("请选择");
        builder.setItems(items, (dialog, which) -> {
            dialog.dismiss();
            editText.setText(items[which]);

        });
        builder.setPositiveButton("取消", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

    /**
     * 设置ｌｉｎｅａｒｌａｙｏｕｔ可见性
     * @param linearLayout
     * @param mode
     */
    public static void setLinearLayoutVisibility(LinearLayout linearLayout, Boolean mode){
        if (mode){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 设置ｓｗｉｔｃｈ监听器，于ｌｉｎｅａｒｌａｙｏｕｔ显示关联
     * @param linearLayout
     * @param sw
     */
    public static void setSwitchLisenter(LinearLayout linearLayout, Switch sw){
        sw.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b){
                setLinearLayoutVisibility(linearLayout,true);
            }else{
                setLinearLayoutVisibility(linearLayout,false);
            }
        }));

    }

    public static void showMultiChooseDialog(String[] items, Context context, TextView editText) {

        try {
            ArrayList<Integer> yourChoices = new ArrayList<>();

            // 设置默认选中的选项，全为false默认均未选中
            final boolean initChoiceSets[] = {false, false, false, false};
            yourChoices.clear();
            AlertDialog.Builder multiChoiceDialog =
                    new AlertDialog.Builder(context);
            multiChoiceDialog.setTitle("请选择");
            multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which,
                                            boolean isChecked) {
                            if (isChecked) {
                                yourChoices.add(which);
                            } else {
                                yourChoices.remove(which);
                            }
                        }
                    });
            multiChoiceDialog.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int size = yourChoices.size();
                            String str = "";
                            for (int i = 0; i < size; i++) {
                                if (i == size - 1) {
                                    str += items[yourChoices.get(i)];
                                } else {
                                    str += items[yourChoices.get(i)] + ",";
                                }
                            }
                            editText.setText(str);
                        }
                    });
            multiChoiceDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 日期时间选择器
     * @param context
     * @param editText
     */
    public static void showDataPicker(Context context, TextView editText) {
        final StringBuffer stringBuilder = new StringBuffer("");
        Calendar c = Calendar.getInstance();

        Dialog dateDialog = new DatePickerDialog(context, (arg0, arg1, arg2, arg3) -> {
            stringBuilder.append(arg1 + "-" + (arg2 + 1) + "-" + arg3);
            editText.setText(stringBuilder);

        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dateDialog.setTitle("请选择日期");
        dateDialog.show();

    }


    /**
     * 设置边界
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 反向取key
     *
     * @param map
     * @param value
     * @return
     */
    public static String FromValueGetKey(Map<String, String> map,
                                         String value) {
        Set set = map.entrySet();
        String arr = "";
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue().equals(value)) {
                arr = (String) entry.getKey();
            }
        }
        return arr;
    }

    /**
     * map取键值
     *
     * @param map
     * @return
     */
    public static String[] getAllKey(Map map) {
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        ArrayList<String> list = new ArrayList<>();
        while (iter.hasNext()) {
            String str = iter.next();
            list.add(str);
        }
        return list.toArray(new String[0]);
    }


    /**
     * xml2JSON
     *
     * @param xml
     * @return
     */
    public static String xml2JSON2(String xml) {
        try {
            XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();
            String string = xmlToJson.toJson().toString();

            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

 /*   public static String xml2JSON2(String xml) {
        try {

            //将xml转为json
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            //设置缩进
            String jsonPrettyPrintString = xmlJSONObj.toString(4);

            return jsonPrettyPrintString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }*/


    public static String JSON2xml2(String json) {
        try {
            JsonToXml jsonToXml = new JsonToXml.Builder(json).build();
            return jsonToXml.toFormattedString().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json string convert to xml string
     */
    public static String JSON2xml(String json){
        StringReader input = new StringReader(json);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch( Exception e){
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        if(output.toString().length()>=38){//remove <?xml version="1.0" encoding="UTF-8"?>
//            return output.toString().substring(39);
//        }
        return output.toString();
    }

    /**
     * xml string convert to json string
     */
    public static String xml2JSON(String xml){
        StringReader input = new StringReader(xml);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
        try {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch( Exception e){
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }






    /**
     * 对View进行量测，布局后截图
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 对单独某个View进行截图
     *
     * @param v
     * @return
     */
    private Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(screenshot);
        c.translate(-v.getScrollX(), -v.getScrollY());
        v.draw(c);
        return screenshot;
    }

    /**
     * 获取整个窗口的截图
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static Bitmap captureScreen(Activity context) {
        View cv = context.getWindow().getDecorView();

        cv.setDrawingCacheEnabled(true);
        cv.buildDrawingCache();
        Bitmap bmp = cv.getDrawingCache();
        if (bmp == null) {
            return null;
        }

        bmp.setHasAlpha(false);
        bmp.prepareToDraw();
        return bmp;
    }

    /**
     * 保存图片到文件File。
     *
     * @param src     源图片
     * @param file    要保存到的文件
     * @param recycle 是否回收
     * @return true 成功 false 失败
     */
    public static boolean save(Bitmap src, File file, boolean recycle) {


        OutputStream os;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = src.compress(Bitmap.CompressFormat.PNG, 50, os);
            if (recycle && !src.isRecycled())
                src.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * 改变TextView状态
     *
     * @param tvTitle
     */
    public static void changeTV2Over(TextView tvTitle) {
        tvTitle.setText("完成");
        tvTitle.setTextColor(RxTool.getContext().getResources().getColor(R.color.theme_color));
    }
    

    public static int getMax(int[] str) {

        int min, max;

        min = max = str[0];
        for (int i = 0; i < str.length; i++) {
            if (str[i] > max)   // 判断最大值
                max = str[i];
            if (str[i] < min)   // 判断最小值
                min = str[i];
        }
        return max;
    }

    /**
     * 验证id 15位或者１８位
     * @return
     */
    public static Boolean verificationID(String ID){
        if (ID.isEmpty()){
            return false;
        }else if (ID.length()!=15||ID.length()!= 18){
            return false;
        }
        return true;
    }
}
