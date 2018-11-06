package com.example.slbappindex.fragment.activity4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;
import com.github.commonlibs.libupdateapputils.util.UpdateAppReceiver;
import com.github.commonlibs.libupdateapputils.util.UpdateAppUtils;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

public class Fragment4AboutActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvRight;
    private TextView tvCenter;
    private GlideImageView iv_zhaoliying;
    private TextView tv1;
    private TextView tv11;
    private TextView img_url;
    private TextView video_url;
    private RelativeLayout rl1;
    private LinearLayout ll1;
    private LinearLayout ll2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_about;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText("关于");
        tv1.setText("版本:V1.4.2");
        img_url.setText(ConstantsUtils.file_url_img);
        video_url.setText(ConstantsUtils.file_url_mp4);
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);
//        GlideOptions glideOptions = new GlideOptions(R.drawable.slb_icon, R.drawable.slb_icon, 20);
//        GlideUtil.display(this, iv1, "https://s2.51cto.com//wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg", glideOptions);

    }

    private void onclick() {
        tvRight.setOnClickListener(this);
        tv11.setOnClickListener(this);
        iv_zhaoliying.setOnClickListener(this);
        rl1.setOnClickListener(this);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        iv_zhaoliying = findViewById(R.id.iv_zhaoliying);
        tv1 = findViewById(R.id.tv1);
        tv11 = findViewById(R.id.tv11);
        img_url = findViewById(R.id.img_url);
        video_url = findViewById(R.id.video_url);
        rl1 = findViewById(R.id.rl1);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.iv_zhaoliying) {
            // 临时跳转到绘本bufen
            startActivity(new Intent("hs.act.slbapp.ReadBookActivity"));
        } else if (i == R.id.tv11) {
            HiosHelper.resolveAd(this, this, "http://pc.jiuzhidao.com/portal/page/index/id/9.html");
        } else if (i == R.id.rl1) {
            //服务器apk path,这里放了百度云盘的apk 作为测试
            String apkPath = "http://issuecdn.baidupcs.com/issue/netdisk/apk/BaiduNetdisk_8.12.9.apk";
            UpdateAppUtils.from(this)
                    .serverVersionCode(AppUtils.getAppVersionCode() + 1)
                    .serverVersionName(AppUtils.getAppVersionName())
                    .downloadPath("apk/" + getPackageName() + ".apk")
                    .showProgress(true)
                    .apkPath(apkPath)
                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)    //default
                    .checkBy(UpdateAppUtils.CHECK_BY_VERSION_CODE) //default
                    .updateInfoTitle("新版本已准备好")
                    .updateInfo("版本：1.01" + "    " + "大小：2.41M\n" + "1.商户加入群聊，在线沟通更方便\n2.配送费专属优惠，下单更便宜\n3.新客加大福利，更多优惠等你来")
//                .showNotification(false)
//                .needFitAndroidN(false)
                    .update();
        } else if (i == R.id.ll1) {
            // 临时跳转到GLide47bufen
            startActivity(new Intent("hs.act.GlideMainActivity"));
        } else if (i == R.id.ll2) {

        } else {
        }
    }

    private UpdateAppReceiver updateAppReceiver = new UpdateAppReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        updateAppReceiver.setBr(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateAppReceiver.desBr(this);
    }
}
