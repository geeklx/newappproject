package com.haier.jiuzhidao.jiuzhidaophone_splash;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdActivity extends BaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private LinearLayout webParent;
    private TextView tv_adJump;
    private ScheduledExecutorService mExecutorService;
    int time = 10;
    String url = "http://app.tanwan.com/htmlcode/17443.html";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adactivity;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        tv_adJump = findViewById(R.id.tv_adJump);
        tv_adJump.setOnClickListener(this);
        setting();
        setTime();
        mWebView.loadUrl(url);
    }

    private void setTime() {
        mExecutorService = Executors.newScheduledThreadPool(1);
        mExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (time == 0) {
                    jump();
                }

                tv_adJump.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_adJump.setText("跳过 ( " + time-- + " )");
                    }
                });

            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void jump() {
        mExecutorService.shutdown();
        startActivity(new Intent(getApplicationContext(), SplshActivity.class));
        finish();
    }

    private void setting() {
        mWebView = new WebView(this);
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webParent = findViewById(R.id.ad_web_container);
        webParent.addView(mWebView);
        WebSettings settings = mWebView.getSettings();//拿到webbiew的settings
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
        settings.setUseWideViewPort(true);//让图片更适合窗口
        settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //使WebView支持js
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        jump();
    }
}
