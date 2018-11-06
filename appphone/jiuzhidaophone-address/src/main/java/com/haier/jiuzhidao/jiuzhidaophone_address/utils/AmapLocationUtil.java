package com.haier.jiuzhidao.jiuzhidaophone_address.utils;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/3/27.
 * Description:
 */

public class AmapLocationUtil {
    //声明AMapLocationClient类对象
    public AMapLocationClient locationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption locationOption = null;
    public LocationListener locationListener;
    private Context context;


    public AmapLocationUtil(Context context) {
        this.context = context;
        initLocation();
    }

    /**
     * 初始化位置信息
     */
    private void initLocation() {
        //初始化定位
        locationClient = new AMapLocationClient(this.context);
        //设置定位回调监听
        locationClient.setLocationListener(aMapLocationListener);

        //初始化定位参数
        locationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        locationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        locationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        locationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        locationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
//        locationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        locationClient.setLocationOption(locationOption);

        //启动定位
        locationClient.startLocation();
    }

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    //声明定位回调监听器
    public AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (locationListener != null) {
                    locationListener.onLocationChanged(amapLocation);
                }
            }
        }
    };

    /**
     * 销毁AMapLocationClient
     */
    public void onDestroy() {
        locationClient.onDestroy();
        locationClient = null;
        locationOption = null;
        locationListener = null;
    }

    public interface LocationListener {
        void onLocationChanged(AMapLocation amapLocation);
    }
}
