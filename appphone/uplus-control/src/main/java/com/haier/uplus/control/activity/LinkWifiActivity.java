package com.haier.uplus.control.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.uhome.usdk.api.interfaces.IuSDKCallback;
import com.haier.uhome.usdk.api.interfaces.IuSDKSmartLinkCallback;
import com.haier.uhome.usdk.api.uSDKDevice;
import com.haier.uhome.usdk.api.uSDKDeviceManager;
import com.haier.uhome.usdk.api.uSDKErrorConst;
import com.haier.uplus.control.R;
import com.haier.uplus.control.dialog.SmartConfigDialog;
import com.haier.uplus.control.utils.WifiUtils;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:连接WiFi
 */
public class LinkWifiActivity extends BaseActivity implements View.OnClickListener{

    private String TAG = "LinkWifiActivity";
    private Context mContext;
    private TextView tv_wifi_ssid;
    private EditText et_pass;
    private ImageView iv_eyes;
    private ImageView iv_back;
    private TextView tv_title;
    private Button btn_next;
    private SmartConfigDialog smartConfigDialog;

    private String wifiName;
    private String wifiPass;

    private String waiting2joinwifidevicemac;

    private uSDKDeviceManager uSDKDeviceMgr = uSDKDeviceManager.getSingleInstance();

    private uSDKDevice ready2AccountDevice;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_link_wifi;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        mContext = this;
        initUI();
        initUIEvent();
    }


    /**
     *
     */
    private void isWaitingJoinWifiDeviceDeal() {
        Intent intentFromNotification = getIntent();
        String tailOfMac = intentFromNotification.getStringExtra("mac");
        if (intentFromNotification != null && tailOfMac != null) {
            waiting2joinwifidevicemac = tailOfMac;
        }
    }

    private void initUI() {
        iv_back = f(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = f(R.id.tv_title);
        tv_title.setText("配置入网");
        tv_wifi_ssid = f(R.id.tv_now_wifi);
        et_pass =  f(R.id.et_set_pass);
        iv_eyes = f(R.id.iv_set_pass_eye);
        btn_next = f(R.id.btn_next);

        iv_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        String wifiName = WifiUtils.getWifiTitle(this);
        if (TextUtils.isEmpty(wifiName)) {
            tv_wifi_ssid.setHint(this.getString(R.string.tip_wifioff));
        } else {
            tv_wifi_ssid.setText(wifiName);
        }
        iv_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_eyes.isSelected()) {
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_eyes.setSelected(false);
                    String password = et_pass.getText().toString();
                    et_pass.setSelection(password.length());
                } else {
                    et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_eyes.setSelected(true);
                    String password = et_pass.getText().toString();
                    et_pass.setSelection(password.length());
                }

            }
        });

        this.smartConfigDialog = new SmartConfigDialog(this);
    }

    private void initUIEvent() {
        /**
         * 取消智能设备入网配置动作
         * cancel smartlink progress
         */
        smartConfigDialog.setOnCancelListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uSDKDeviceMgr.stopSmartLinkConfig(new IuSDKCallback() {
                    @Override
                    public void onCallback(uSDKErrorConst result) {
                        System.out.println("config:" + result);
                        if(smartConfigDialog != null) {
                            smartConfigDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        if (id==R.id.iv_back){
            finish();
        }else if (id==R.id.btn_next){
            wifiName = tv_wifi_ssid.getText().toString();
            wifiPass = et_pass.getText().toString();
            if (checkApName(wifiName,wifiPass)) {
                smartLinkConfig();
//                startActivity(new Intent("hs.act.phone.LinkSuccessActivity"));
//                finish();
            }
        }
    }


    /**
     * 配置入网
     */
    private void smartLinkConfig() {
        wifiName = tv_wifi_ssid.getText().toString();
        wifiPass = et_pass.getText().toString();
        smartConfigDialog.show();

        if (waiting2joinwifidevicemac == null) {
            /**
             * 执行智能设备入网配置
             */
            uSDKDeviceMgr.configDeviceBySmartLink(wifiName, wifiPass, null, 60, false, new IuSDKSmartLinkCallback() {

                @Override
                public void onSmartLinkCallback(uSDKDevice deviceJustJoined, uSDKErrorConst reason) {
                    if (reason == uSDKErrorConst.RET_USDK_OK) {
                        Toasty.success(mContext,"入网配置成功").show();
                        ready2AccountDevice = deviceJustJoined;
                        SpUtils.getInstance(LinkWifiActivity.this).put("connect", true);
                        startActivity(new Intent("hs.act.phone.LinkSuccessActivity"));
                        finish();
                    } else {
                        Toasty.error(mContext,"入网配置失败").show();
                    }
                    smartConfigDialog.dismiss();
                }
            });
        } else {
            /**
             *执行智能设备入网配置
             */
            uSDKDeviceMgr.configDeviceBySmartLink(
                    wifiName, wifiPass, waiting2joinwifidevicemac, 60, false, new IuSDKSmartLinkCallback() {

                        @Override
                        public void onSmartLinkCallback(
                                uSDKDevice deviceJustJoined, uSDKErrorConst reason) {
                            if (reason == uSDKErrorConst.RET_USDK_OK) {
                                Toasty.success(mContext,"入网配置成功").show();
                                ready2AccountDevice = deviceJustJoined;
                                SpUtils.getInstance(LinkWifiActivity.this).put("connect", true);
                                startActivity(new Intent("hs.act.phone.LinkSuccessActivity"));
                                finish();
                            } else {
                                Toasty.error(mContext,"入网配置失败").show();
                            }
                            smartConfigDialog.dismiss();

                        }
                    });
        }
    }

    private boolean checkApName(String apName,String apPass) {
        if (!apName.isEmpty()&&!apPass.isEmpty()) {
            return true;
        } else {
            Toasty.normal(mContext,getResources().getString(R.string.code_check_wifipass)).show();
            return false;
        }
    }
}
