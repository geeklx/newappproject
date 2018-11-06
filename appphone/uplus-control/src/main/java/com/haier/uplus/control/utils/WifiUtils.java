package com.haier.uplus.control.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

public class WifiUtils {

	/**
	 * 获得当前无线ssid
	 * @param ctx
	 * @return
	 */
	public static String getWifiTitle(Context ctx) {
		WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String ssidStr = wifiInfo.getSSID();
		if(TextUtils.isEmpty(ssidStr))
			return null;
		char beginA = ssidStr.charAt(0);
		if (beginA == '"') {
			String[] ssidArr = ssidStr.split("\"");
			return ssidArr[1];
		} else {
			return ssidStr;
		}
	}

	public static void  getPhoneWifiList() {

	}

}
