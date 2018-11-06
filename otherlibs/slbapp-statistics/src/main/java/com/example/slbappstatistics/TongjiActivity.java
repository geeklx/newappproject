package com.example.slbappstatistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

public class TongjiActivity extends Activity implements View.OnClickListener {

    private EditText edt1;
    private EditText edt2;
    private Button btnHqyzm;
    private Button btnSure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongji);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        JAnalyticsInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JAnalyticsInterface.init(this);

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
//        if (i == R.id.tv1) {
//
//        } else {
//
//        }
    }

    private void onclick() {
//        tvLeft.setOnClickListener(this);
    }

    private void findview() {

//        edt1 = (EditText) findViewById(R.id.edt1);

    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


}