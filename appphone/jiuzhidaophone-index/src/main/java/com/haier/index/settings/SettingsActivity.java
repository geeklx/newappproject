package com.haier.index.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.commonlibs.libupdateapputils.util.UpdateAppUtils;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.cacheutil.CacheUtil;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.widget.SmoothCheckBox;
import com.haier.cellarette.baselibrary.widget.SwitchButton;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.index.R;

import static com.haier.cellarette.libutils.utilslib.device.DeviceUtil.getVersionCode;
import static com.haier.cellarette.libutils.utilslib.device.DeviceUtil.getVersionName;


/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/17.
 * Description:设置
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener, SmoothCheckBox.OnCheckedChangeListener, SwitchButton.OnCheckedChangeListener {
    private static final int MSG_UPDATE_CACHEMEM_UI = 0;

    private Context mContext;
    private ImageView ivBack;
    private TextView tvCenDegree;
    private TextView tvFahDegree;
    private TextView tvTitle;
    private TextView tvClearMemory;
    private TextView tvCheckUpdates;
    private TextView tvlogout;
    private boolean isUpdate = true;
    private TextView tvAddress;
    private TextView tvPay;
    private SwitchButton sbMsgNotice;
    private SwitchButton sbOpenNotice;
    private SwitchButton sbTroubleNotice;
    private SmoothCheckBox scbCenDegree;
    private SmoothCheckBox scbFahDegree;
    private TextView tvAbout;
    private TextView tvCacheMem;
    private TextView tvVolume;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE_CACHEMEM_UI:
                    try {
                        tvCacheMem.setText(CacheUtil.getTotalCacheSize(BaseApp.get()));
                    } catch (Exception e) {
                        tvCacheMem.setText("0.0KB");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mContext = SettingsActivity.this;
        initView();
    }

    private void initView() {
        ivBack = f(R.id.iv_back);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle = f(R.id.tv_title);
        tvTitle.setText("设置");
        tvClearMemory = f(R.id.tv_clear_memory);
        tvCheckUpdates = f(R.id.tv_check_updates);
        tvAddress = f(R.id.tv_settings_address);
        tvPay = f(R.id.tv_settings_pay);
        tvAbout = f(R.id.tv_about);
        tvCacheMem = f(R.id.tv_cache_memory);
        sbMsgNotice = f(R.id.sb_msg_notice);
        sbOpenNotice = f(R.id.sb_open_notice);
        sbTroubleNotice = f(R.id.sb_trouble_notice);
        scbCenDegree = f(R.id.scb_cen_degree);
        scbFahDegree = f(R.id.scb_fah_degree);
        tvCenDegree = f(R.id.tv_cen_degree);
        tvFahDegree = f(R.id.tv_fah_degree);
        tvVolume = f(R.id.tv_volume);

        tvlogout = f(R.id.tv_logout);
        ivBack.setOnClickListener(this);
        tvClearMemory.setOnClickListener(this);
        tvCheckUpdates.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvPay.setOnClickListener(this);
        tvlogout.setOnClickListener(this);
        tvAbout.setOnClickListener(this);
        tvCenDegree.setOnClickListener(this);
        tvFahDegree.setOnClickListener(this);
        sbMsgNotice.setOnCheckedChangeListener(this);
        sbOpenNotice.setOnCheckedChangeListener(this);
        sbTroubleNotice.setOnCheckedChangeListener(this);
        scbCenDegree.setOnCheckedChangeListener(this);
        scbFahDegree.setOnCheckedChangeListener(this);
        tvVolume.setOnClickListener(this);

        Message msg = new Message();
        msg.what = MSG_UPDATE_CACHEMEM_UI;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_cen_degree) {
            if (scbCenDegree.isChecked())
                scbCenDegree.setChecked(false, true);
            else
                scbCenDegree.setChecked(true, true);
        } else if (id == R.id.tv_fah_degree) {
            if (scbFahDegree.isChecked())
                scbFahDegree.setChecked(false, true);
            else
                scbFahDegree.setChecked(true, true);
        } else if (id == R.id.tv_clear_memory) {
            showAlert("确定要清除缓存吗？", 1);
        } else if (id == R.id.tv_check_updates) {
//            if (isUpdate) {//TODO 判断当前版本是否需要更新
//                showAlert("发现最新版本", 2);
//            } else {
//                Toasty.normal(mContext, "已是最新版本").show();
//            }
            startActivity(new Intent("hs.ac.github.DemoUpdateAppMainActivity"));
//            //服务器apk path,这里放了百度云盘的apk 作为测试
//            String apkPath = "http://issuecdn.baidupcs.com/issue/netdisk/apk/BaiduNetdisk_7.15.1.apk";
//            UpdateAppUtils.from(this)
//                    .serverVersionCode(getVersionCode(this) + 1)
//                    .serverVersionName(getVersionName(this))
//                    .downloadPath("updateapp/" + getPackageName() + ".apk")
//                    .apkPath(apkPath)
//                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)    //default
//                    .checkBy(UpdateAppUtils.CHECK_BY_VERSION_CODE) //default
//                    .updateInfoTitle("新版本已准备好")
//                    .updateInfo("版本：1.01" + "    " + "大小：2.41M\n" + "1.商户加入群聊，在线沟通更方便\n2.配送费专属优惠，下单更便宜\n3.新客加大福利，更多优惠等你来")
////                .showNotification(false)
////                .needFitAndroidN(false)
//                    .update();
        } else if (id == R.id.tv_logout) {
//            showAlert("你确定退出此账号", 3);
            startActivity(new Intent("hs.act.phone.uploadpic"));
        } else if (id == R.id.tv_settings_address) {
            startActivity(new Intent("hs.act.phone.AddressActivity"));
        } else if (id == R.id.tv_settings_pay) {
            startActivity(new Intent("hs.act.phone.PayActivity"));
        } else if (id == R.id.tv_about) {
            Toasty.normal(this, "tv_about").show();
            HiosHelper.resolveAd(SettingsActivity.this, SettingsActivity.this, "http://pc.jiuzhidao.com/portal/page/index/id/9.html");
        } else if (id == R.id.tv_volume) {
            startActivity(new Intent("hs.act.phone.RingActivity"));
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        int id = view.getId();
        if (id == R.id.sb_msg_notice) {
            if (isChecked) {
                Toasty.normal(this, "sb_msg_notice: checked").show();
            } else {
                Toasty.normal(this, "sb_msg_notice: unchecked").show();
            }
        } else if (id == R.id.sb_open_notice) {
            if (isChecked) {
                Toasty.normal(this, "sb_open_notice: checked").show();
            } else {
                Toasty.normal(this, "sb_open_notice: unchecked").show();
            }
        } else if (id == R.id.sb_trouble_notice) {
            if (isChecked) {
                Toasty.normal(this, "sb_trouble_notice: checked").show();
            } else {
                Toasty.normal(this, "sb_trouble_notice: unchecked").show();
            }
        }
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
        int id = checkBox.getId();
        if (id == R.id.scb_cen_degree) {
            if (isChecked)
                Toasty.normal(this, "scb_cen_degree: checked").show();
            else
                Toasty.normal(this, "scb_cen_degree: unchecked").show();
        } else if (id == R.id.scb_fah_degree) {
            if (isChecked)
                Toasty.normal(this, "scb_fah_degree: checked").show();
            else
                Toasty.normal(this, "scb_fah_degree: unchecked").show();
        }
    }


    private void showAlert(String str, final int flag) {
        final AlertView dialog = new AlertView(mContext, "温馨提示", str,
                "取消", "确定");
        dialog.show();
        dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                    @Override
                                    public void doLeft() {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void doRight() {
                                        dialog.dismiss();
                                        if (flag == 1) {
                                            //TODO 清除应用缓存
                                            CacheUtil.clearAllCache(BaseApp.get());
                                            Toasty.normal(mContext, "清除完毕").show();

                                            Message msg = new Message();
                                            msg.what = MSG_UPDATE_CACHEMEM_UI;
                                            mHandler.sendMessageDelayed(msg, 500);
                                        } else if (flag == 2) {
                                            //TODO 更新应用版本
                                            Toasty.normal(mContext, "开始更新版本").show();
                                        } else if (flag == 3) {
                                            //TODO 退出应用
                                        }
                                    }
                                }
        );
    }
}
