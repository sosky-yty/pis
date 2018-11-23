package com.example.sosky.pis_copy.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sosky.pis_copy.MyTools;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.SaveTool;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.example.sosky.pis_copy.bean.UpPersonBean;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxFileTool;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.zyl.customkeyboardview.CustomKeyboardEditText;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

/**
 * 个人信息
 */
public class AddPersonMainActivity extends BaseActivity {

    private RxTitle rxTitle;
    private EditText editName;
    private CustomKeyboardEditText editSfz;
    private RelativeLayout rlOrdZp;
    private ImageView ivZp;
    private TextView tvZp;
    private RelativeLayout rlJichu;
    private TextView tvJichu;
    private RelativeLayout rlYibao;
    private TextView tvYibao;
    private RelativeLayout rlNongbao;
    private TextView tvNongbao;
    private RelativeLayout rlJisheng;
    private TextView tvJisheng;
    private RelativeLayout rlTekun;
    private TextView tvTekun;
    private RelativeLayout rlCanlian;
    private TextView tvCanlian;
    private Button btnSave;
    private EditText zjlb;


    public static String mName = "";
    public static String mID = "";
    public static String mMode = "";
    public static String mZjlb = "";
    private String[] sfzlb = {"身份证","非身份证"};

    /**
     * 图片选项
     */
    RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.ic_launcher)    //加载成功之前占位图
            .error(R.mipmap.ic_launcher)    //加载错误之后的错误图
            .skipMemoryCache(true)    //跳过内存缓存
            .override(Target.SIZE_ORIGINAL)
            .diskCacheStrategy(DiskCacheStrategy.NONE);  //跳过磁盘缓存
//                .override(400,400)	//指定图片的尺寸
    //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
//                .fitCenter()
    //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
//                .centerCrop()
//                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）

//                .diskCacheStrategy(DiskCacheStrategy.ALL)    //缓存所有版本的图像
//                .diskCacheStrategy(DiskCacheStrategy.DATA)    //只缓存原来分辨率的图片
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);    //只缓存最终的图片

    @Override
    protected int getContentID() {
        return R.layout.activity_person_main;
    }

    @Override
    protected void initView() {

        rxTitle = findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);


        editName = findViewById(R.id.edit_name);
        editSfz = findViewById(R.id.edit_sfz);
        rlOrdZp = findViewById(R.id.rl_ord_zp);
        ivZp = findViewById(R.id.iv_zp);
        tvZp = findViewById(R.id.tv_zp);
        rlJichu = findViewById(R.id.rl_jichu);
        tvJichu = findViewById(R.id.tv_jichu);
        rlYibao = findViewById(R.id.rl_yibao);
        tvYibao = findViewById(R.id.tv_yibao);
        rlNongbao = findViewById(R.id.rl_nongbao);
        tvNongbao = findViewById(R.id.tv_nongbao);
        rlJisheng = findViewById(R.id.rl_jisheng);
        tvJisheng = findViewById(R.id.tv_jisheng);
        rlTekun = findViewById(R.id.rl_tekun);
        tvTekun = findViewById(R.id.tv_tekun);
        rlCanlian = findViewById(R.id.rl_canlian);
        tvCanlian = findViewById(R.id.tv_canlian);
        btnSave = findViewById(R.id.btn_save);
        zjlb = findViewById(R.id.main_zjlb);
    }

    @Override
    protected void loadDatas() {

        try {
            if ("local".equals(getIntent().getStringExtra("action"))) {
                mID = getIntent().getStringExtra("id");
                mMode = "local";
                UpPersonBean.InfoBean person = SaveTool.getOnePerson(mID);
                mName = person.getOrd_xm();
                mZjlb = person.getOrd_zjlb();
                editSfz.hideKeyboard();
                CustomKeyboardEditText.setFlag(false);
                //todo 本地查看 
                loadImg();
                editSfz.setText(mID);
                editName.setText(mName);
                zjlb.setText(mZjlb);
            } else {
                mID = getIntent().getStringExtra("id");
                CustomKeyboardEditText.setFlag(true);
                mMode = "new";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载照片
     */
    private void loadImg() {
        String[] sufix = {".jpg", ".jpeg", ".png"};
        for (String s : sufix) {
            File file = new File(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "个人照片/" + mID + s);
            if (RxFileTool.isFileExists(file)) {
                Glide.with(mContext).
                        load(file).
                        apply(options).
                        into(ivZp);
                break;
            }
        }
    }


    @Override
    protected void bindListener() {

        Bundle bundle = new Bundle();

        rlOrdZp.setOnClickListener(v -> showImgDialog());

        //基础
        rlJichu.setOnClickListener(v -> {
            getEx(bundle);
            if (!isSFZok()) {
                return;
            }
            RxActivityTool.skipActivity(mContext, AddPersonActivity.class, bundle);
        });
        //医保
        rlYibao.setOnClickListener(v -> {
            getEx(bundle);
            if (!isSFZok()) {
                return;
            }
            RxActivityTool.skipActivity(mContext, addYbxxActivity.class, bundle);

        });

        //农保        
        rlNongbao.setOnClickListener(v -> {
            getEx(bundle);
            if (!isSFZok()) {
                return;
            }
            RxActivityTool.skipActivity(mContext, addXlbActivity.class, bundle);

        });

        //特困
        rlTekun.setOnClickListener(v -> {
            getEx(bundle);
            if (!isSFZok()) {
                return;
            }
            RxActivityTool.skipActivity(mContext, addMztkActivity.class, bundle);
        });
        //残联
        rlCanlian.setOnClickListener(v -> {
            getEx(bundle);
            if (!isSFZok()) {
                return;
            }
            RxActivityTool.skipActivity(mContext, addClxxActivity.class, bundle);
        });

        zjlb.setOnClickListener(v -> {
            MyTools.showSelectDialog(sfzlb,mContext,zjlb);
        });
    }

    private void getEx(Bundle bundle) {
        bundle.putString("id", editSfz.getText().toString());
        bundle.putString("zjlb",zjlb.getText().toString());
        bundle.putString("action", mMode);

        String name = editName.getText().toString();
        String id = editSfz.getText().toString();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(id)) {
            RxToast.error("姓名必填,身份证必填");
            return;
        }
        mName = name;
        mID = id;
        mZjlb = zjlb.getText().toString();
        //点击后转为本地查看模式
        mMode = "local";
    }


    /**
     * 身份证是否填写
     *
     * @return
     */
    private boolean isSFZok() {
        String s_zjlb = zjlb.getText().toString();
        if (TextUtils.isEmpty(s_zjlb)){
            RxToast.error("请选择证件类别");
            return false;
        }
        if (!TextUtils.isEmpty(s_zjlb) && s_zjlb.equals("身份证")) {
            String sfz = editSfz.getText().toString();
            if (TextUtils.isEmpty(sfz)|| sfz.length()< 15){
                RxToast.error("请填写正确的身份证号");
                return false;
            }
            return  true;
        }

        if (!TextUtils.isEmpty(s_zjlb) && s_zjlb.equals("非身份证")){
            String fsfz = editSfz.getText().toString();
            if (TextUtils.isEmpty(fsfz)){
                RxToast.error("请填写证件号码");
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 照相对话框
     */
    private void showImgDialog() {

        if (!isSFZok()) {
            return;
        }

        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
        dialogChooseImage.getFromCameraView().setOnClickListener(v2 -> {
            RxPhotoTool.openCameraImage(this);
            dialogChooseImage.cancel();
        });
        dialogChooseImage.getFromFileView().setOnClickListener(v3 -> {
            RxPhotoTool.openLocalImage(this);
            dialogChooseImage.cancel();
        });
        dialogChooseImage.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE:
                //选择相册之后的处理
                if (resultCode == RESULT_OK) {

                    roadImageView(data.getData());

                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA:
                //选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    roadImageView(RxPhotoTool.imageUriFromCamera);

                }

                break;

            default:

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * //从Uri中加载图片 并将其转化成File文件
     *
     * @param uri
     * @return
     */
    private void roadImageView(Uri uri) {

        String idcard = editSfz.getText().toString();


        File file = (new File(RxPhotoTool.getImageAbsolutePath(mContext, uri)));

        File tofile = new File(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "个人照片/" + idcard + getexname(file));

        RxFileTool.deleteFile(tofile);

        try {
            RxFileTool.createOrExistsDir(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "个人照片/");
            RxFileTool.copyFile(file, tofile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Glide.with(mContext).
                load(tofile).
                apply(options).
                into(ivZp);

        String tofileName = tofile.getName();
        tvZp.setText(tofileName);
        RxLogTool.e("照片" + file.getAbsolutePath());
        RxLogTool.e("照片" + tofile.getAbsolutePath());

    }

    /**
     * 文件后缀
     *
     * @param file
     * @return
     */
    private String getexname(File file) {
        String fileName = file.getName();
        if (fileName.length() > 0 && fileName != null) {
            //--截取文件名
            int i = fileName.lastIndexOf(".");
            if (i > -1 && i < fileName.length()) {
                //--扩展名
                String extention = fileName.substring(i + 1);
                return "." + extention;
            }
        }
        return ".png";
    }

}
