package com.haier.uplus.control.utils;

import android.content.Context;
import android.widget.Toast;

import com.haier.uhome.account.model.uacmodel.UacDevice;
import com.haier.uhome.account.model.uacmodel.UacDeviceTypeInfo;
import com.haier.uhome.usdk.api.interfaces.IuSDKCallback;
import com.haier.uhome.usdk.api.uSDKDevice;
import com.haier.uhome.usdk.api.uSDKDeviceAttribute;
import com.haier.uhome.usdk.api.uSDKDeviceInfo;
import com.haier.uhome.usdk.api.uSDKDeviceStatusConst;
import com.haier.uhome.usdk.api.uSDKErrorConst;
import com.haier.uplus.control.R;
//import com.loopj.android.http.AsyncHttpClient;
//
//import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {

	//asset has some files
	public static final String UPLUSID_ELECHEATER = "111c120024000810060500418001840000000000000000000000000000000000";
	public static final String UPLUSID_UDEVKIT = "110c10841050850814ee00000000000000000000000000000000000000000000";

	/**
	 * 获取设备状态提示
	 */
	public static String getuSDKDeviceStatus(Context ctx, uSDKDevice deviceItem) {
		uSDKDeviceStatusConst deviceItemStatus = deviceItem.getStatus();
		
        if(deviceItemStatus == uSDKDeviceStatusConst.STATUS_CONNECTED) {
        	return ctx.getString(R.string.devicestatus_connected);
        } else if(deviceItemStatus == uSDKDeviceStatusConst.STATUS_CONNECTING) {
        	return ctx.getString(R.string.devicestatus_connecting);
        } else if(deviceItemStatus == uSDKDeviceStatusConst.STATUS_READY) {
        	return ctx.getString(R.string.devicestatus_ready);
        } else if(deviceItemStatus == uSDKDeviceStatusConst.STATUS_OFFLINE) {
        	return ctx.getString(R.string.devicestatus_offline);
        } else {
        	return ctx.getString(R.string.devicestatus_unconnected);
        }
        
	}
	
	/**
	 * 
	 */
	public static String deviceDesc(uSDKDevice device) {
		String ip = device.getIp();
		String type = device.getType().getValue();
		String deviceId = device.getDeviceId();
		//String typeId = device.getUplusId();

		return "[" + deviceId + " " + ip + "]";
	}
	
	public static uSDKDeviceAttribute queryCode6ValueByKey(String code6Key, uSDKDevice device) {
		HashMap attrMap = device.getAttributeMap();
		if(attrMap != null) {
			Object result = attrMap.get(code6Key);
			if(result != null) {
				return (uSDKDeviceAttribute) result;
				
			} else {
				return null;
			
			}
			
		} else {
			return null;
			
		}
	}
	
	/**
	 * <pre>
	 * 當智能設備連接成功后，發送指令開始查詢設備屬性狀態，
	 * 指令執行成功后，設備會自動上報最新狀態
	 * 特別說明：根據設備ID開發文檔發送查詢指令，不能直接照搬DemoApp.
	 *
	 * Send this cmd to smart device to query properties,
	 * when connected. Smart device begin to dev doc. report properties when changes.
	 * queryCmd need to read smart device ID
	 * </pre>
	 */
	public static void querySmartDeviceProperties(uSDKDevice device) {

		String queryCmd = "getAllProperty";
		String smartDeviceUplusId = device.getUplusId();

		if(UPLUSID_UDEVKIT.equals(smartDeviceUplusId)
				|| UPLUSID_ELECHEATER.equals(smartDeviceUplusId)) {
			queryCmd = "2000ZZ";
			sendQueryCmd(device, queryCmd);

		} else {
			sendQueryCmd(device, queryCmd);

		}

	}

	private static void sendQueryCmd(uSDKDevice queryDevice, String queryCmd) {
		if("getAllProperty".equals(queryCmd)) {
			/*
		 	* 如果智能設備開發手冊中有操作：getAllProperty，
		 	* 您可以發送此指令給鐋智能設備，查詢它的屬性狀態
		 	*
		 	* if "getAllProperty" exists in your dev doc,
		 	* send it to get all properties of smart device.
		 	*/
			List cmdCollection = new ArrayList();
			queryDevice.execOperation(queryCmd, cmdCollection, 15, new IuSDKCallback() {
					@Override
					public void onCallback(uSDKErrorConst result) {
						if(result != uSDKErrorConst.RET_USDK_OK) {
							System.err.println(result);
						}
					}
			});

		} else {
			queryDevice.writeAttribute(queryCmd, "", new IuSDKCallback() {
				@Override
				public void onCallback(uSDKErrorConst result) {
					System.out.println(result);
				}

			});

		}

	}

//
//	public static String getuSerSession(Header[] headers) {
//		for(Header header : headers) {
//			String headeName = header.getName();
//			if("accessToken".equals(headeName)) {
//				return header.getValue();
//
//			}
//
//		}
//		return null;
//
//	}

	public static List fillDeviceInfoForRemoteControl(JSONArray devicesOfAccount) {
		List<uSDKDeviceInfo> remotedCtrledDeviceCollection =
				new ArrayList<uSDKDeviceInfo>();
		for (int i = 0;i < devicesOfAccount.length();i++) {
			try {
				JSONObject deviceItemJson = (JSONObject) devicesOfAccount.get(i);

				String deviceIdJsonValue = deviceItemJson.getString("id");

				JSONObject deviceTypeJsonValue = deviceItemJson.getJSONObject("typeInfo");
				String deviceUplusId = null;
				if (deviceTypeJsonValue != null) {
					deviceUplusId = deviceTypeJsonValue.getString("typeId");

				}

				uSDKDeviceInfo remoteCtrledDeviceInfo = new uSDKDeviceInfo(
						deviceIdJsonValue,
						deviceUplusId,
						true);
				remotedCtrledDeviceCollection.add(remoteCtrledDeviceInfo);

			} catch (JSONException e) {
				e.printStackTrace();

			}

		}

		return remotedCtrledDeviceCollection;

	}

	public static List fillDeviceInfoForRemoteControl(UacDevice[] devicesOfAccount) {
		List<uSDKDeviceInfo> remotedCtrledDeviceCollection =
				new ArrayList<uSDKDeviceInfo>();

		if(devicesOfAccount != null) {

			for (int i = 0; i < devicesOfAccount.length; i++) {
				UacDevice deviceItem = devicesOfAccount[i];

				String deviceIdValue = deviceItem.getId();

				UacDeviceTypeInfo deviceTypeInfo = deviceItem.getTypeInfo();
				//需要变更名字为getUplusId
				String deviceUplusId = deviceTypeInfo.getTypeId();

				uSDKDeviceInfo remoteCtrledDeviceInfo = new uSDKDeviceInfo(
						deviceIdValue,
						deviceUplusId,
						true);
				remotedCtrledDeviceCollection.add(remoteCtrledDeviceInfo);

			}

		}

		return remotedCtrledDeviceCollection;

	}

	/**
	 * 不知道哪儿些是必填的，需要对照openapi手册
	 * 或老代码
     */
	public static UacDevice[] makeDeviceArrayForBind(uSDKDevice myDevice) {
		UacDevice[] devicesForBind = new UacDevice[1];
		UacDevice deviceItem = new UacDevice();
		devicesForBind[0] = deviceItem;

		//第一步：设置设备ID
		deviceItem.setId(myDevice.getDeviceId());

		//第二步：设置设备名称
		deviceItem.setName("smart device");

		/*
		//第三步：设定模组
		DeviceModulesInfo[] myDeviceModulesInfoArray = new DeviceModulesInfo[1];
		//第三步：
			//3.1生成一个WIFI模组信息
		DeviceModulesInfo myDeviceModulesInfoWIFIItem = new DeviceModulesInfo();
				//3.1.1生成wifimodule
		myDeviceModulesInfoWIFIItem.setModuleType("wifimodule");
				//3.1.2设定设备Id
		myDeviceModulesInfoWIFIItem.setModuleId(myDevice.getDeviceId());

				//3.1.3设定WIFI模组
		ModulesInfoItem[] modulesInfo_WIFISubItems = new ModulesInfoItem[7];
					//3.1.3.1设置“IP”
		ModulesInfoItem modulesInfoItem_IP = new ModulesInfoItem();
		modulesInfoItem_IP.setKey("ip");
		modulesInfoItem_IP.setValue(myDevice.getIp());
		modulesInfo_WIFISubItems[0] = modulesInfoItem_IP;
					//3.1.3.2设置"hardwareVers"
		ModulesInfoItem modulesInfoItem_hardwareVers = new ModulesInfoItem();
		modulesInfoItem_hardwareVers.setKey("hardwareVers");
		modulesInfoItem_hardwareVers.setValue(myDevice.getSmartLinkHardwareVersion());
		modulesInfo_WIFISubItems[1] = modulesInfoItem_hardwareVers;
					//3.1.3.3设置"softwareVers"
		ModulesInfoItem modulesInfoItem_softwareVers = new ModulesInfoItem();
		modulesInfoItem_softwareVers.setKey("softwareVers");
		modulesInfoItem_softwareVers.setValue(myDevice.getSmartLinkSoftwareVersion());
		modulesInfo_WIFISubItems[2] = modulesInfoItem_softwareVers;
					//3.1.3.4设置"configVers"
		ModulesInfoItem modulesInfoItem_configVers = new ModulesInfoItem();
		modulesInfoItem_configVers.setKey("configVers");
		modulesInfoItem_configVers.setValue(myDevice.getSmartLinkDevfileVersion());
		modulesInfo_WIFISubItems[3] = modulesInfoItem_configVers;
					//3.1.3.5设置"protocolVers"
		ModulesInfoItem modulesInfoItem_protocolVers = new ModulesInfoItem();
		modulesInfoItem_protocolVers.setKey("protocolVers");
		modulesInfoItem_protocolVers.setValue(myDevice.getEProtocolVer());
		modulesInfo_WIFISubItems[4] = modulesInfoItem_protocolVers;
					//3.1.3.6设置"platform"
		ModulesInfoItem modulesInfoItem_platform = new ModulesInfoItem();
		modulesInfoItem_platform.setKey("platform");
		modulesInfoItem_platform.setValue(myDevice.getSmartLinkSoftwareVersion());
		modulesInfo_WIFISubItems[5] = modulesInfoItem_platform;
					//3.1.3.7设置"supportUpgrade"
		ModulesInfoItem modulesInfoItem_supportUpgrade = new ModulesInfoItem();
		modulesInfoItem_supportUpgrade.setKey("supportUpgrade");
		modulesInfoItem_supportUpgrade.setValue("0");
		modulesInfo_WIFISubItems[6] = modulesInfoItem_supportUpgrade;
		//
		myDeviceModulesInfoWIFIItem.setModuleInfos(modulesInfo_WIFISubItems);

		myDeviceModulesInfoArray[0] = myDeviceModulesInfoWIFIItem;
		deviceItem.setDeviceModules(myDeviceModulesInfoArray);
		*/

		//设定设备类型
		UacDeviceTypeInfo myDeviceTypeInfo = new UacDeviceTypeInfo();
		myDeviceTypeInfo.setTypeId(myDevice.getUplusId());
		//myDeviceTypeInfo.setMainType(myDeviceTypeInfo.getMainType());
		deviceItem.setTypeInfo(myDeviceTypeInfo);

		return devicesForBind;
	}

	public static List fillDeviceInfoForUserInfoView(UacDevice[] devicesOfAccount) {
		List devicesListForDisplay = new ArrayList();

		String deviceShortDesc = null;
		for(UacDevice device : devicesOfAccount) {
			deviceShortDesc = device.getId() + "-nickname:" + device.getName();
			devicesListForDisplay.add(deviceShortDesc);
		}

		return devicesListForDisplay;

	}

	/**
	 * 加載U+云證書進行SSL通信
	 */
//	public static AsyncHttpClient setCertificates(Context context, AsyncHttpClient client) {
//		InputStream cerInputstream = null;
//		try {
//			cerInputstream = context.getAssets().open("ucloudCer.cer");
//			CertificateFactory cerFactory = CertificateFactory
//							.getInstance("X.509");
//			Certificate cer = cerFactory.generateCertificate(cerInputstream);
//			//Android平臺只用BKS實現
//			KeyStore keyStore = KeyStore.getInstance("BKS");
//			keyStore.load(null, null);
//			//把U+云自生成證書加入信任
//			keyStore.setCertificateEntry("trust", cer);
//
//			//讓client使用此sslsocketfactory通信
//			SSLSocketFactory cerSSLSocketFacoty = new SSLSocketFactory(keyStore);
//			client.setSSLSocketFactory(cerSSLSocketFacoty);
//
//			cerInputstream.close();
//
//			return client;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			if(cerInputstream != null) {
//				try {
//					cerInputstream.close();
//					cerInputstream = null;
//
//				} catch (IOException e) {}
//
//			}
//
//		}
//
//		return null;
//
//	}

	public static void displayShortMsg(Context ctx, String shortMsg) {
		Toast.makeText(ctx, shortMsg,
				Toast.LENGTH_LONG).show();
	}
}
