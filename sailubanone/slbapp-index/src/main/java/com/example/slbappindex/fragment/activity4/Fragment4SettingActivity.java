package com.example.slbappindex.fragment.activity4;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.cacheutil.CacheUtil;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.widget.SwitchButton;

public class Fragment4SettingActivity extends BaseActivity implements View.OnClickListener, SwitchButton.OnCheckedChangeListener {

    private TextView tvRight;
    private TextView tvCenter;
    private SwitchButton slbSb1;
    private LinearLayout ll1;
    private TextView tv1;
    private SwitchButton slbSb2;
    private SwitchButton slbSb3;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_setting;
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
        tvCenter.setText("设置");
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);
        try {
            tv1.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
        } catch (Exception e) {
            tv1.setText("0.00B");
        }

    }

    private void onclick() {
        tvRight.setOnClickListener(this);
        slbSb1.setOnCheckedChangeListener(this);
        ll1.setOnClickListener(this);
        slbSb2.setOnCheckedChangeListener(this);
        slbSb3.setOnCheckedChangeListener(this);
    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        slbSb1 = findViewById(R.id.slb_sb1);
        ll1 = findViewById(R.id.ll1);
        tv1 = findViewById(R.id.tv1);
        slbSb2 = findViewById(R.id.slb_sb2);
        slbSb3 = findViewById(R.id.slb_sb3);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.ll1) {
            // 清除缓存bufen
            final AlertView dialog = new AlertView(this, "温馨提示", "清除缩略图及其他缓存文件",
                    "取消", "确定");
            dialog.show();
            dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                        @Override
                                        public void doLeft() {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void doRight() {
                                            //TODO 清除应用缓存
                                            CacheUtil.clearAllCache(BaseApp.get());
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        tv1.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
                                                    } catch (Exception e) {
                                                        tv1.setText("0.00B");
                                                    }
                                                }
                                            }, 500);
                                            dialog.dismiss();

                                        }
                                    }
            );
        } else {
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        int i = view.getId();
        if (i == R.id.slb_sb1) {
            // 开启硬解码bufen
            if (isChecked) {
                //设置开启
                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            } else {
                // 设置关闭
                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            }
        } else if (i == R.id.slb_sb2) {
            // 开启重力感应bufen
            if (isChecked) {
                //设置开启
                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            } else {
                // 设置关闭
                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            }
        } else if (i == R.id.slb_sb3) {
            // 开启应用自动弹出bufen
            if (isChecked) {
                //设置开启
                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            } else {
                // 设置关闭
                Toasty.normal(Fragment4SettingActivity.this, isChecked + "").show();
            }
        } else {
        }
    }
}
