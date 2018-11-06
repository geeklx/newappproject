package com.haier.index.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.libutils.utilslib.app.MobileUtils;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.index.R;


public class WelComeActivity extends BaseActivity {

    private int delayMillis = 1500;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent("hs.act.phone.HIOSAdActivity"));
            finish();
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent("hs.act.phone.index"));
            finish();
        }
    };
    private String key_token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        key_token = (String) SpUtils.getInstance(this).get("key_token", "");
        JumpWhere();
    }

    private void JumpWhere() {
        //联网
        if (MobileUtils.isNetworkConnected(this)) {
            if (key_token != null && !key_token.equals("")) {

            } else {
                handler.postDelayed(runnable, delayMillis);
            }
        }
        //未联网
        else {
            if (key_token != null && !key_token.equals("")) {
                handler.postDelayed(runnable2, delayMillis);
            } else {
                handler.postDelayed(runnable, delayMillis);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        handler.removeCallbacks(runnable2);
    }
}