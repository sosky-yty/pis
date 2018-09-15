package com.example.sosky.pis_copy.ui;

import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.RxFileTool;
import com.vondear.rxtools.RxLogTool;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.RxTitle;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import java.io.File;
import java.io.IOException;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

/**
 * 家庭信息主界面
 */
public class AddFamilyMainActivity extends BaseActivity {

    private RxTitle rxTitle;
    private RelativeLayout rlOrdZp;
    private ImageView ivZp;
    private TextView tvZp;
    private RelativeLayout rlOrdJzfzp;
    private ImageView ivJzfzp;
    private TextView tvJzfzp;
    private RelativeLayout rlOrdXzfzp;
    private ImageView ivXzfzp;
    private TextView tvXzfzp;
    private RelativeLayout rlJiating;
    private TextView tvJiating;
    private RelativeLayout rlDibao;
    private TextView tvDibao;
    private RelativeLayout rlCaoyuan;
    private TextView tvCaoyuan;
    private RelativeLayout rlLingshi;
    private TextView tvLingshi;
    private RelativeLayout rlJingzhun;
    private TextView tvJingzhun;

    int whichzp = 1; // 1 全家福,2 旧房 ,3 新房

    @Override
    protected int getContentID() {
        return R.layout.activity_famely_main;
    }

    @Override
    protected void initView() {

        rxTitle = findViewById(R.id.rx_title);
        rxTitle.setLeftFinish(this);

        rlOrdZp = findViewById(R.id.rl_ord_zp);
        ivZp = findViewById(R.id.iv_zp);
        tvZp = findViewById(R.id.tv_zp);
        rlOrdJzfzp = findViewById(R.id.rl_ord_jzfzp);
        ivJzfzp = findViewById(R.id.iv_jzfzp);
        tvJzfzp = findViewById(R.id.tv_jzfzp);
        rlOrdXzfzp = findViewById(R.id.rl_ord_xzfzp);
        ivXzfzp = findViewById(R.id.iv_xzfzp);
        tvXzfzp = findViewById(R.id.tv_xzfzp);
        rlJiating = findViewById(R.id.rl_jiating);
        tvJiating = findViewById(R.id.tv_jiating);
        rlDibao = findViewById(R.id.rl_dibao);
        tvDibao = findViewById(R.id.tv_dibao);
        rlCaoyuan = findViewById(R.id.rl_caoyuan);
        tvCaoyuan = findViewById(R.id.tv_caoyuan);
        rlLingshi = findViewById(R.id.rl_lingshi);
        tvLingshi = findViewById(R.id.tv_lingshi);
        rlJingzhun = findViewById(R.id.rl_jingzhun);
        tvJingzhun = findViewById(R.id.tv_jingzhun);

    }

    @Override
    protected void bindListener() {

        rlOrdZp.setOnClickListener(v -> {
            whichzp = 1;
            showImgDialog();
        });
        rlOrdJzfzp.setOnClickListener(v -> {
            whichzp = 2;
            showImgDialog();
        });
        rlOrdXzfzp.setOnClickListener(v -> {
            whichzp = 3;
            showImgDialog();
        });
    }


    /**
     * 照相对话框
     */
    private void showImgDialog() {


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

        String idcard = getIntent().getStringExtra("idcard");
        File file = (new File(RxPhotoTool.getImageAbsolutePath(mContext, uri)));

        File tofile = new File(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "全家福照片/" + idcard + getexname(file));

        ImageView imageView = ivZp;
        TextView textView = tvZp;
        switch (whichzp) {
            case 1:
                tofile = new File(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "全家福照片/" + idcard + getexname(file));
                imageView = ivZp;
                textView = tvZp;
                break;
            case 2:
                tofile = new File(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "旧住房照片/" + idcard + getexname(file));
                imageView = ivJzfzp;
                textView = tvJzfzp;
                break;
            case 3:
                tofile = new File(RxFileTool.getSDCardPath() + getString(R.string.photo_path) + "新住房照片/" + idcard + getexname(file));
                imageView = ivXzfzp;
                textView = tvXzfzp;
                break;
            default:
                break;

        }


        RxFileTool.deleteFile(tofile);

        try {
            RxFileTool.copyFile(file, tofile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //加载到界面
        Glide.with(mContext).
                load(tofile).
                into(imageView);

        String tofileName = tofile.getName();
        textView.setText(tofileName);
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
