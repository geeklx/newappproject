package com.haier.cellarette.jiuzhidao_demo.musics.splash_login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.jiuzhidao_demo.R;
import com.haier.cellarette.libutils.utilslib.app.MobileUtils;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.jiuzhidao.biz_mz.presenter.MyPresenter_lunxun;
import com.haier.jiuzhidao.biz_mz.view.Lunxun;


public class SplshActivity extends BaseActivity implements Lunxun {

    private MyPresenter_lunxun myPresenter = new MyPresenter_lunxun();
    private int delayMillis = 1500;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), PollOrderActivity.class));
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
                myPresenter.onCreate(this);
                myPresenter.lunxun(key_token);
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
        return R.layout.activity_splash;
    }

    @Override
    public void isLogin(boolean is) {
        //登录
        if (is) {
            handler.postDelayed(runnable2, delayMillis);
        }
        //未登录
        else {
            handler.postDelayed(runnable, delayMillis);
        }
    }

    @Override
    public void webUrl(String web_url) {
    }

    @Override
    public void hasNewOrder(boolean is) {
    }

    @Override
    public void error(String string) {
        handler.postDelayed(runnable, delayMillis);
        Toasty.error(this, string).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.onDestory();
        handler.removeCallbacks(runnable);
        handler.removeCallbacks(runnable2);
    }
}