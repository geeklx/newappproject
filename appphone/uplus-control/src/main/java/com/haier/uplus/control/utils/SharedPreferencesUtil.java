package com.haier.uplus.control.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/***
 * @author: 实现的主要功能:实现了轻量级属性配置的页面。 创建日期 修改者，修改日期，修改内容。
 */
public class SharedPreferencesUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String sharedPreferencesInfo = "haiericebox.shareInfo";

	private static Context myContext;

	private static SharedPreferences saveInfo;

	private static Editor saveEditor;

	private static SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();

	private static final String LOGIN_NAME_PASS = "login_name_pass";
	
	public static final String ISFRIST = "isFrist"; // false为没有查看
	
	public static void init(Context context) {
		myContext = context;
	}

	public static SharedPreferencesUtil getinstance(Context context) {
		myContext = context;
		if (saveInfo == null && myContext != null) {
			saveInfo = myContext.getSharedPreferences(sharedPreferencesInfo,
					Context.MODE_PRIVATE);
			saveEditor = saveInfo.edit();
		}
		return sharedPreferencesUtil;
	}

	public static SharedPreferencesUtil getInstance() {
		if (saveInfo == null && myContext != null) {
			saveInfo = myContext.getSharedPreferences(sharedPreferencesInfo,
					Context.MODE_PRIVATE);
			saveEditor = saveInfo.edit();
		}
		return sharedPreferencesUtil;
	}

	public boolean isContainKey(String key) {
		return saveInfo.contains(key);
	}

	public String getString(String key) {
		return saveInfo.getString(key, "");
	}

	public String getString(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}
	
	/**是否是第一次安裝  虚拟酒柜
	 * @return
	 */
	public boolean getIsFirstRun() {
		return saveInfo.getBoolean("isFirstRun", true);
	}
	public void setIsFirstRun(boolean boo) {
		saveEditor = saveInfo.edit();
		saveEditor.putBoolean("isFirstRun", boo);
		saveEditor.commit();
	}
	/**
	 * 微酒酷浮层
	 * @param key
	 * @param binded
	 * @return
	 */
	public boolean setShareNotFrist(String key, boolean binded) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, binded);
		return saveEditor.commit();
	}
	public boolean getShareNotFrist(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
	/**
	 * 博芬浮层
	 * @param key
	 * @param binded
	 * @return
	 */
	public boolean setShareNotFrist2(String key, boolean binded) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, binded);
		return saveEditor.commit();
	}
	public boolean getShareNotFrist2(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
//	public boolean getShareNotFrist() {
//		return saveInfo.getBoolean(ISFRIST, true);
//	}
//	public void setShareNotFrist(boolean flag) {
//		saveEditor = saveInfo.edit();
//
//		saveEditor.putBoolean(ISFRIST, flag);
//
//		saveEditor.commit();
//	}
	
	public long getlong(String key) {
		return saveInfo.getLong(key, 0);
	}

	public boolean setlong(String key, long time) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putLong(key, time);
		return saveEditor.commit();
	}

	public HashMap<String, String> getAll() {
		return (HashMap<String, String>) saveInfo.getAll();
	}

	public boolean setString(String key, String value) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, value);
		return saveEditor.commit();
	}

	public boolean clearItem(String key) {
		saveEditor.remove(key);
		return saveEditor.commit();
	}

	public boolean setUserid(String key, String sf_login) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, sf_login);
		return saveEditor.commit();
	}

	public String getuserid(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	/**
	 * 设置是否已有绑定设备
	 * 
	 * @param key
	 *            --isBinded
	 * @param binded
	 * @return
	 */
	public boolean setBinded(String key, boolean binded) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, binded);
		return saveEditor.commit();
	}

	/**
	 * 判断是否已有绑定设备
	 * 
	 * @param key
	 *            --isBinded
	 * @param defaultValue
	 * @return
	 */
	public boolean getBinded(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
	/**
	 * 设置是否第一次从控酒页面进入酒品详情
	 * 
	 * @param key
	 *            --isFristOpen
	 * @param isFristOpen
	 * @return
	 */
	public boolean setNotFristOpen(String key, boolean isFristOpen) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, isFristOpen);
		return saveEditor.commit();
	}

	/**
	 * 判断是否第一次从控酒页面进入酒品详情
	 * 
	 * @param key
	 *            --isFristOpen
	 * @param defaultValue
	 * @return
	 */
	public boolean getNotFristOpen(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
	
	
	/**
	 * 设置 已有绑定设备Mac
	 * 
	 * @param key
	 *            --deviceMac
	 * @param binded
	 * @return
	 */
	public boolean setDeviceMac(String key, String binded) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, binded);
		return saveEditor.commit();
	}

	/**
	 * 获取已有绑定设备
	 * 
	 * @param key
	 *            --deviceMac
	 * @param defaultValue
	 * @return
	 */
	public String getDeviceMac(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	public boolean setPassword(String key, String sf_login) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, sf_login);
		return saveEditor.commit();
	}

	public boolean setwhiteboard(String key, String sf_login) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, sf_login);
		return saveEditor.commit();
	}

	public String getwhiteboard(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	public String getPassword(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	public boolean setUserName(String key, String sf_login) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putString(key, sf_login);
		return saveEditor.commit();
	}

	public String getUserName(String key, String defaultValue) {
		return saveInfo.getString(key, defaultValue);
	}

	
	/**
	 * 设置是否是第一次扫描
	 * 
	 * @param key
	 *            --isFirstScan
	 * @param binded
	 * @return
	 */
	public boolean setFirstScan(String key, boolean scan) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, scan);
		return saveEditor.commit();
	}

	/**
	 * 判断是否是第一次扫描
	 * 
	 * @param key
	 *            --isFirstScan
	 * @param defaultValue
	 * @return
	 */
	public boolean getFirstScan(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
	
	
	
	public boolean setSound(String key, boolean binded) {
		if (saveInfo.contains(key)) {
			saveEditor.remove(key);
		}
		saveEditor.putBoolean(key, binded);
		return saveEditor.commit();
	}

	public boolean getSound(String key, boolean defaultValue) {
		return saveInfo.getBoolean(key, defaultValue);
	}
}
