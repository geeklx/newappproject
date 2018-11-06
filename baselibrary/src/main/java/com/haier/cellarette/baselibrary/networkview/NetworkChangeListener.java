package com.haier.cellarette.baselibrary.networkview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;

/**
 * 网络改变监控广播
 * <p>
 * 监听网络的改变状态,只有在用户操作网络连接开关(wifi,mobile)的时候接受广播,
 * 然后对相应的界面进行相应的操作，并将 状态 保存在我们的APP里面
 * <p>
 * <p>
 * Created by xujun
 */
public class NetworkChangeListener extends BroadcastReceiver {

    public static final String TAG1 = "2tag1";

    public NetworkChangeListener() {

    }

    public NetworkChangeListener(NetconListener mNet_con) {
        this.mNet_con = mNet_con;
    }

    public NetconListener mNet_con;

    @Override
    public void onReceive(Context context, Intent intent) {
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

            //业务逻辑
            if (mNet_con != null) {
                mNet_con.net_con_phone();
            }

        } else if (wifiState != null && State.CONNECTED == wifiState) {// 无线网络连接成功  
            Log.d(TAG1, "无线网络连接成功");

            NetModel.getInstance().setMobile(false);
            NetModel.getInstance().setWifi(true);
            NetModel.getInstance().setConnected(true);

            //业务逻辑
            if (mNet_con != null) {
                mNet_con.net_con_wifi();
            }


        } else if (wifiState != null && mobileState != null
                && State.CONNECTED != wifiState
                && State.CONNECTED != mobileState) {// 手机没有任何的网络  
            Log.d(TAG1, "手机没有任何的网络");

            NetModel.getInstance().setMobile(false);
            NetModel.getInstance().setWifi(false);
            NetModel.getInstance().setConnected(false);

            //业务逻辑
            if (mNet_con != null) {
                mNet_con.net_con_none();
            }


        }

    }

}  
