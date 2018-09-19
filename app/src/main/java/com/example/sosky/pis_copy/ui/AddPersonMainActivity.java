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


    public static String mName = "";
    public static String mID = "";
    public static String mMode = "";


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

    }

    @Override
    protected void loadDatas() {

        try {
            if ("local".equals(getIntent().getStringExtra("action"))) {
                mID = getIntent().getStringExtra("id");
                mMode = "local";
                UpPersonBean.InfoBean person = SaveTool.getOnePerson(mID);
                mName = person.getOrd_xm();
                editSfz.hideKeyboard();
                CustomKeyboardEditText.setFlag(false);
                //todo 本地查看 
                loadImg();
                editSfz.setText(mID);
                editName.setText(mName);
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

    }

    private void getEx(Bundle bundle) {
        bundle.putString("id", editSfz.getText().toString());
        bundle.putString("action", mMode);

        String name = editName.getText().toString();
        String id = editSfz.getText().toString();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(id)) {
            RxToast.error("姓名必填,身份证必填");
            return;
        }
        mName = name;
        mID = id;
        //点击后转为本地查看模式
        mMode = "local";
    }


    /**
     * 身份证是否填写
     *
     * @return
     */
    private boolean isSFZok() {
        String string = editSfz.getText().toString();
        if (TextUtils.isEmpty(string) || string.length() < 8) {
            RxToast.error("请先正确填写身份证号");
            return false;
        }
        return true;
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
