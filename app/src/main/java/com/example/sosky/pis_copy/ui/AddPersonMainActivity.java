package com.example.sosky.pis_copy.ui;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sosky.pis_copy.R;
import com.example.sosky.pis_copy.base.BaseActivity;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

public class AddPersonMainActivity extends BaseActivity {


    private RelativeLayout rlOrdZp;
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

    @Override
    protected int getContentID() {
        return R.layout.activity_person_main;
    }

    @Override
    protected void initView() {

        rlOrdZp = findViewById(R.id.rl_ord_zp);
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


    }

    @Override
    protected void bindListener() {
        rlOrdZp.setOnClickListener(v -> {
            {
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
        });

    }
}
