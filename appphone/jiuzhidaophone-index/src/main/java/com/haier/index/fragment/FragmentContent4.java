package com.haier.index.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.CircularSeekBar;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.index.R;
import com.haier.uhome.usdk.api.interfaces.IuSDKCallback;
import com.haier.uhome.usdk.api.interfaces.IuSDKDeviceListener;
import com.haier.uhome.usdk.api.interfaces.IuSDKDeviceManagerListener;
import com.haier.uhome.usdk.api.uSDKCloudConnectionState;
import com.haier.uhome.usdk.api.uSDKDevice;
import com.haier.uhome.usdk.api.uSDKDeviceAlarm;
import com.haier.uhome.usdk.api.uSDKDeviceAttribute;
import com.haier.uhome.usdk.api.uSDKDeviceManager;
import com.haier.uhome.usdk.api.uSDKDeviceStatusConst;
import com.haier.uhome.usdk.api.uSDKErrorConst;
import com.haier.uhome.usdk.api.uSDKLogLevelConst;
import com.haier.uhome.usdk.api.uSDKManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FragmentContent4 extends BaseFragment implements View.OnClickListener{

    private String TAG = "U+";
    private String tablayoutId;
    private Context mContext;
    private TextView tvBoundCellarette;
    private TextView tvVirtualCellarette;
    private TextView tvWineManagement;
    private TextView tvLight;
    private RelativeLayout rl_unbinding;
    private RelativeLayout rl_binding_control;
    private CircularSeekBar circularSeekBar;
    private TextView tv_temp;
    private boolean isConnect = false;
    private boolean isOnOff = true;

    private List<uSDKDevice> allSightedDevices = null;

    private uSDKManager uSDKMgr = uSDKManager.getSingleInstance();
    private uSDKDeviceManager uSDKDeviceMgr = uSDKDeviceManager.getSingleInstance();
    private uSDKDevice selectedDevice;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        initUSDK();
//        initDevice();
        isConnect = (Boolean) SpUtils.getInstance(getActivity()).get("connect", false);
        if (isConnect){
            rl_unbinding.setVisibility(View.GONE);
            rl_binding_control.setVisibility(View.VISIBLE);
        }else {
            rl_unbinding.setVisibility(View.VISIBLE);
            rl_binding_control.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
//        initUSDK();
        findView(rootView);
        initData();
        initDevice();
    }

    /**
     * 初始化U+sdk
     */
//    private void initUSDK() {
//        uSDKManager sdkManager = uSDKManager.getSingleInstance();
//        sdkManager.init(getActivity());
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4;
    }


    public void findView(View rootView){
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "DINCond-Regular.ttf");

        rl_unbinding = f(rootView,R.id.rl_unbinding);
        rl_binding_control = f(rootView,R.id.rl_binding_control);

        tvBoundCellarette = f(rootView,R.id.tv_bound_cellarette);
        tvVirtualCellarette = f(rootView,R.id.tv_virtual_cellarette);
        tvBoundCellarette.setOnClickListener(this);
        tvVirtualCellarette.setOnClickListener(this);

        tvWineManagement = f(rootView,R.id.tv_wine_management);
        tvLight = f(rootView,R.id.tv_light);
        tvWineManagement.setOnClickListener(this);
        tvLight.setOnClickListener(this);

        circularSeekBar = f(rootView,R.id.csb_temp);
        tv_temp = f(rootView,R.id.tv_temp);
        tv_temp.setTypeface(typeFace);
//        circularSeekBar.setTemperatureValue(18, 18, 5);
//        tv_temp.setText("10");


        //温度调节
        circularSeekBar.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onSetValues(int progress) {

            }

            @Override
            public void onRefreshTemperatureOfRoom() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChange(CircularSeekBar view, final int newProgress) {
//                refreshSightedDeviceList(null);
                selectedDevice.writeAttribute("CSetTemper", (newProgress+5)+"", new IuSDKCallback() {
                    @Override
                    public void onCallback(uSDKErrorConst result) {
                        if(result == uSDKErrorConst.RET_USDK_OK) {
                            Toasty.success(getActivity(), "酒柜温度设置成功").show();
                        }
                    }
                });
            }
        });
        circularSeekBar.edit();
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
//        Toasty.success(getActivity(), "初始化内容" + tablayoutId).show();
    }


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            return;
        }
        Toasty.normal(getActivity(), "下拉刷新啦").show();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId==R.id.tv_bound_cellarette){
            startActivity(new Intent("hs.act.phone.GuideActivity"));
        }else if (viewId==R.id.tv_virtual_cellarette){
            startActivity(new Intent("hs.act.phone.VirtualCellaretteActivity"));
        }else if (viewId==R.id.tv_wine_management){
            startActivity(new Intent("hs.act.phone.WineManageActivity"));
        }else if (viewId==R.id.tv_light){//酒柜灯开发控制
            if (isOnOff=true){
                onORoff(isOnOff);
                isOnOff=false;
            }else if (isOnOff=false){
                onORoff(isOnOff);
                isOnOff=true;
            }
        }
    }

    /**
     * 设置酒柜灯开关状态
     * @param isOnOff
     */
    public void onORoff(boolean isOnOff){
        selectedDevice.writeAttribute("lightStatus", isOnOff+"", new IuSDKCallback() {
            @Override
            public void onCallback(uSDKErrorConst result) {
                if(result == uSDKErrorConst.RET_USDK_OK) {
                    Toasty.success(getActivity(), "操作成功").show();
                }
            }
        });
    }

    private void initDevice() {
        registerDevicesManage();
        startuSDK();
        refreshCloudIP();
    }


    /**
     * 注册设备管理
     */
    private void registerDevicesManage() {
        uSDKDeviceMgr.setDeviceManagerListener(new IuSDKDeviceManagerListener() {

            /**
             * 设备管理-uSDK剔除无法交互的设备
             */
            @Override
            public void onDevicesRemove(List<uSDKDevice> devicesChanged) {
                refreshSightedDeviceList(devicesChanged);
                Log.e(TAG, "smart device has been invalid....");
            }

            /**
             * 设备管理-uSDK發現一臺可用設備
             */
            @Override
            public void onDevicesAdd(List<uSDKDevice> devicesChanged) {
                refreshSightedDeviceList(devicesChanged);
                Log.e(TAG,"smart device added....");
            }

            /**
             * 远程服务器推送设备解绑定消息定消息
             */
            @Override
            public void onDeviceUnBind(String devicesChanged) {
                Log.e(TAG,"Remote Server push msg:" + devicesChanged + " has been removed from accout");
            }

            /**
             * 远程服务器推送设备綁定消息
             */
            @Override
            public void onDeviceBind(String devicesChanged) {
                Log.e(TAG,"Remote Server push msg:"+ devicesChanged + " has been added to accout");
            }

            /**
             * App在此可以得到遠程服務器連接狀態
             */
            @Override
            public void onCloudConnectionStateChange(uSDKCloudConnectionState status) {
                //遠程服務器已連接
                if (status ==uSDKCloudConnectionState.CLOUD_CONNECTION_STATE_CONNECTED) {
                    Log.e(TAG, " connect to Remote Server : OK");
                }

            }
        });

    }

    /**
     * 启动uSDK
     */
    private void startuSDK() {
        uSDKMgr.initLog(uSDKLogLevelConst.USDK_LOG_DEBUG, true, new IuSDKCallback() {
            @Override
            public void onCallback(uSDKErrorConst uSDKErrorConst) {

            }
        });

        uSDKMgr.startSDK(new IuSDKCallback() {

            @Override
            public void onCallback(uSDKErrorConst uSDKStartResult) {
                //使用此API查询uSDK启动状态
                //Is uSDK started?
                //boolean appQueryuSDKisStatus = uSDKMgr.isSDKStart();
                if (uSDKStartResult == uSDKErrorConst.RET_USDK_OK) {
                    refreshSightedDeviceList(null);
                } else {
//                   Log.e(TAG, " connect to Remote Server : OK");
                }
            }
        });

    }

    /**
     * 刷新设备列表
     * @param devicesChanged
     */
    private void refreshSightedDeviceList(List<uSDKDevice> devicesChanged) {
        //直接刷新uSDK可发现的所有设备
        allSightedDevices = uSDKDeviceMgr.getDeviceList();
        if (allSightedDevices.size()>0){
            uSDKDevice childDevice = allSightedDevices.get(0);
            String deviceID = childDevice.getDeviceId();
            getSelectDevice(deviceID);
            receiveSmartDevciesProperties();
        }else {
            return;
        }

    }

    /**
     * 刷新IP地址
     */
    private void refreshCloudIP() {
        CloudEnvironmentIPTask cloudEnvironmentIPTask = new CloudEnvironmentIPTask();
        cloudEnvironmentIPTask.execute();
    }

    private class CloudEnvironmentIPTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            String environmentDesc = null;
            try {
                InetAddress environmentInetAddress = InetAddress.getByName("uhome.haier.net");
                environmentDesc = environmentInetAddress.getHostAddress();
                Log.e(TAG, "Current Cloud Environment:" + environmentDesc);

            } catch (UnknownHostException e) {
                e.printStackTrace();
                return "Cloud IP Get Failed!";

            }

            return environmentDesc;
        }
        protected void onPostExecute(String result) {
//            cloudIPTextView.setText("uhome.haier.net:" + result);
        }
    }

    private void getSelectDevice(String deviceID){
        this.selectedDevice = uSDKDeviceMgr.getDevice(deviceID);
    }


    /**
     * 接收智能设备属性、报警、连接状态
     */
    private void receiveSmartDevciesProperties() {
        this.selectedDevice.setDeviceListener(new IuSDKDeviceListener() {

            /**
             * callback when connect status change
             * 智能設備的連接狀態發生改變時
             */
            @Override
            public void onDeviceOnlineStatusChange(uSDKDevice device,uSDKDeviceStatusConst status, int statusCode) {

                Log.e(TAG, "Current device ip and statusCode:" + device.getIp()+""+statusCode);
            }

            /**
             * callback when property change
             * 智能設備的屬性狀態發生改變時
             */
            @Override
            public void onDeviceAttributeChange(uSDKDevice device, List<uSDKDeviceAttribute> propertiesChanged) {
                List<String> deviceProperties = getSmartDevicePropertiesValues(device);
                String[] properties = deviceProperties.get(0).split(":");
                int currentTemp = Integer.parseInt(properties[1].trim());
                circularSeekBar.setTemperatureValue(currentTemp, 20, 5);
                tv_temp.setText(properties[1]);
            }

            /**
             * callback when alarm
             * 智能設備發生報警時
             */
            @Override
            public void onDeviceAlarm(uSDKDevice device, List<uSDKDeviceAlarm> alarms) {
                for (uSDKDeviceAlarm alarmItem : alarms) {
                }
            }

            /**
             * callback when ip or nettype change
             * 智能設備IP及網絡類型發生改變時
             */
            @Override
            public void onDeviceBaseInfoChange(uSDKDevice uSDKDevice) {

            }

            @Override
            public void onSubDeviceListChange(uSDKDevice uSDKDevice, ArrayList<uSDKDevice> arrayList) {

            }
        });

    }

    /**
     * 获得当前设备的属性值集合
     */
    private ArrayList<String> getSmartDevicePropertiesValues(uSDKDevice device) {
        ArrayList<String> list = new ArrayList<String>();

        HashMap<String, uSDKDeviceAttribute> propertiesMap = device.getAttributeMap();
        Set<Map.Entry<String, uSDKDeviceAttribute>> set = propertiesMap.entrySet();
        Iterator<Map.Entry<String, uSDKDeviceAttribute>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, uSDKDeviceAttribute> entry = it.next();
            uSDKDeviceAttribute attr = entry.getValue();
            list.add(attr.getAttrName() + " : " + attr.getAttrValue());
        }

        return list;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
