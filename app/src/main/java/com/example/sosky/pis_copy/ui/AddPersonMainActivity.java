package com.example.sosky.pis_copy.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxFileTool;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import java.io.File;
import java.io.IOException;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class AddPersonMainActivity extends BaseActivity {

    private RxTitle rxTitle;
    private EditText editName;
    private EditText editSfz;
    private RelativeLayout rlOrdZp;
    private ImageView ivZp;
    private TextView tvZp;
    private RelativeLayout rlJichu;
    private TextView tvJichu;
    private RelativeLayout rlDibao;
    private TextView tvDibao;
    private RelativeLayout rlTekun;
    private TextView tvTekun;
    private RelativeLayout rlCaoyuan;
    private TextView tvCaoyuan;
    private RelativeLayout rlLingshi;
    private TextView tvLingshi;
    private RelativeLayout rlJingzhun;
    private TextView tvJingzhun;
    private String mId;


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
        rlDibao = findViewById(R.id.rl_dibao);
        tvDibao = findViewById(R.id.tv_dibao);
        rlTekun = findViewById(R.id.rl_tekun);
        tvTekun = findViewById(R.id.tv_tekun);
        rlCaoyuan = findViewById(R.id.rl_caoyuan);
        tvCaoyuan = findViewById(R.id.tv_caoyuan);
        rlLingshi = findViewById(R.id.rl_lingshi);
        tvLingshi = findViewById(R.id.tv_lingshi);
        rlJingzhun = findViewById(R.id.rl_jingzhun);
        tvJingzhun = findViewById(R.id.tv_jingzhun);


        mId = getIntent().getStringExtra("id");
    }

    
    @Override
    protected void bindListener() {

        //fixme  xxxx
        Bundle bundle = new Bundle();
        bundle.putString("id",editSfz.getText().toString());
        bundle.putString("action","local");
        rlOrdZp.setOnClickListener(v -> showImgDialog());

        rlDibao.setOnClickListener(v -> {
            RxActivityTool.skipActivity(mContext, addMzdbActivity.class);
        });
        rlCaoyuan.setOnClickListener(v -> {
            RxActivityTool.skipActivity(mContext, addCybzActivity.class);
        });
        rlJichu.setOnClickListener(v -> {
            RxActivityTool.skipActivity(mContext, addKeyPersonActivity.class);
        });
        rlJingzhun.setOnClickListener(v -> {
            RxActivityTool.skipActivity(mContext, addJzfpActivity.class);
        });
        rlLingshi.setOnClickListener(v -> {
            RxActivityTool.skipActivity(mContext, addLsjzActivity.class);
        });
        rlTekun.setOnClickListener(v -> {
         
            
            RxActivityTool.skipActivity(mContext, addMztkActivity.class,bundle);
        });

    }


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
