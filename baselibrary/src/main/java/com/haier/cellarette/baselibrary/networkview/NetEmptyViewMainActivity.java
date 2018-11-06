package com.haier.cellarette.baselibrary.networkview;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;


public class NetEmptyViewMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EmptyView mEmptyView;
    private LinearLayout containlayout;
    private TextView tv1, tv2, tv3, tv4, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_main);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        mEmptyView = findViewById(R.id.empty_view);
//        mEmptyView.setIs_an(1);
        containlayout = findViewById(R.id.containlayout);
//        mEmptyView.notices("暂无酒品", "获取数据失败\n请检查网络是否通畅", "loading...", "");//提供自定义文字 默认可不传
        mEmptyView.bind(containlayout).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                //重试
                mEmptyView.loading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        mEmptyView.success();
                    }
                }, 3000);
            }
        });

        mEmptyView.getmNodataLayout().findViewById(R.id.empty_nodata_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重试
                mEmptyView.loading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        mEmptyView.success();
                    }
                }, 3000);
            }
        });
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);

        //根据网络显示布局
//        NetModel.getInstance().initReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(mlistener, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mlistener);
    }

    private NetworkChangeListener mlistener = new NetworkChangeListener(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            State wifiState = null;
            State mobileState = null;

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

            Log.d(TAG1,
                    "wifi状态:" + wifiState + "\n mobile状态:" + mobileState);

            if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED == mobileState) {// 手机网络连接成功
                Log.d(TAG1, "手机2g/3g/4g网络连接成功");

                NetModel.getInstance().setMobile(true);
                NetModel.getInstance().setWifi(false);
                NetModel.getInstance().setConnected(true);

                mEmptyView.success();

            } else if (wifiState != null && State.CONNECTED == wifiState) {// 无线网络连接成功
                Log.d(TAG1, "无线网络连接成功");

                NetModel.getInstance().setMobile(false);
                NetModel.getInstance().setWifi(true);
                NetModel.getInstance().setConnected(true);

                mEmptyView.success();

            } else if (wifiState != null && mobileState != null
                    && State.CONNECTED != wifiState
                    && State.CONNECTED != mobileState) {// 手机没有任何的网络
                Log.d(TAG1, "手机没有任何的网络");

                NetModel.getInstance().setMobile(false);
                NetModel.getInstance().setWifi(false);
                NetModel.getInstance().setConnected(false);

                mEmptyView.errorNet();

            }
        }
    };


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv1) {
            mEmptyView.loading();

        } else if (i == R.id.tv2) {
            mEmptyView.errorNet();

        } else if (i == R.id.tv3) {
            mEmptyView.nodata();

        } else if (i == R.id.tv4) {
            mEmptyView.success();

        } else if (i == R.id.tv5) {
//            startActivity(new Intent(EmptyViewMainActivity.this, EmptyViewMainActivity.class));

        } else {
        }
    }
}
