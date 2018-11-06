package com.example.slbappsplash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.libwebview.base.WebViewActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HIOSAdActivity extends WebViewActivity implements View.OnClickListener {

    private TextView tv_adJump;
    private ScheduledExecutorService mExecutorService;
    int time = 5;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hios_adactivity);
        findview();
        onclickListener();
        setupWebView();
        tv_adJump = findViewById(R.id.tv_adJumps);
        tv_adJump.setOnClickListener(this);
        setTime();
        url = "http://app.tanwan.com/htmlcode/17443.html";
        loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        set_destory();
        super.onDestroy();
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

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_adJumps) {
            jump();
        } else {

        }
    }
}
