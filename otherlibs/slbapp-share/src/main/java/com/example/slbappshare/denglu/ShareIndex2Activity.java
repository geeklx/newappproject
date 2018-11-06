package com.example.slbappshare.denglu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slbappshare.R;

import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.weibo.SinaWeibo;

public class ShareIndex2Activity extends Activity implements View.OnClickListener, OnResultInfoLitener {

    private String TAG = this.getClass().getSimpleName();
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private JPushDengluUtils JPushDengluUtils;

    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareindex2);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        type = getIntent().getIntExtra("type", Platform.ACTION_SHARE);
        JPushDengluUtils = new JPushDengluUtils(this);
//        platformList = JShareInterface.getPlatformList();
        if (type == Platform.ACTION_USER_INFO) {
            if (JPushDengluUtils.en_shouquan(QQ.Name)) {
                btn1.setText(QQ.Name + "已授权");
            }
            if (JPushDengluUtils.en_shouquan(Wechat.Name)) {
                btn2.setText(Wechat.Name + "已授权");
            }
            if (JPushDengluUtils.en_shouquan(SinaWeibo.Name)) {
                btn3.setText(SinaWeibo.Name + "已授权");
            }
        }


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn1) {
            set_qq();
        } else if (i == R.id.btn2) {
            set_weixin();
        } else if (i == R.id.btn3) {
            set_xinlang();
        }
    }

    private void onclick() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    private void findview() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
    }

    @Override
    protected void onDestroy() {
        JPushDengluUtils.ondes();
        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * 设置QQ授权bufen
     */
    private void set_qq() {
        JPushDengluUtils.shezhi_shouquan_getinfo(QQ.Name);
    }

    /**
     * 设置微信授权bufen
     */
    private void set_weixin() {
        JPushDengluUtils.shezhi_shouquan_getinfo(Wechat.Name);
    }

    /**
     * 设置新浪授权bufen
     */
    private void set_xinlang() {
        JPushDengluUtils.shezhi_shouquan_getinfo(SinaWeibo.Name);
    }


    @Override
    public void onResults(String platform, String toastMsg, String data) {
        Toast.makeText(getApplicationContext(), platform + "---" + toastMsg + "---" + data, Toast.LENGTH_LONG).show();
    }
}