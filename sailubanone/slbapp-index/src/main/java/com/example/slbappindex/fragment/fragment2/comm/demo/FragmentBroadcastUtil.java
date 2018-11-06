//package com.example.slbappindex.fragment.fragment2.comm.demo;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//
//import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ImgBean;
//import com.haier.cellarette.baselibrary.common.BaseApp;
//import com.haier.cellarette.baselibrary.widget.AlertView;
//import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
//import com.haier.cellarette.baselibrary.common.ConstantsUtils;
//
//import cc.shinichi.library.ImagePreview;
//import cc.shinichi.library.glide.ImageLoader;
//import cc.shinichi.sherlockutillibrary.utility.ui.ToastUtil;
//
//public class FragmentBroadcastUtil {
////    public static final String BC_fragment21 = "com.bc.fragment21";
////    public static final String BC_fragment22 = "com.bc.fragment22";
//    private MessageReceiver mMessageReceiver;
//
//    /**
//     * 发送广播bufen
//     *
//     * @param MESSAGE_RECEIVED_ACTION
//     */
//    public static void shezhi_Receiver(String MESSAGE_RECEIVED_ACTION) {
//        Intent msgIntent = new Intent(MESSAGE_RECEIVED_ACTION);
////        msgIntent.putExtra(JpushActivity.KEY_MESSAGE, message);
////        msgIntent.putExtra(JpushActivity.KEY_EXTRAS, (Bundle) extras);
//        LocalBroadcastManagers.getInstance(BaseApp.get()).sendBroadcast(msgIntent);
//    }
//
//    /**
//     * 注册广播bufen
//     *
//     * @param MESSAGE_RECEIVED_ACTION
//     */
//    public static void registerMessageReceiver(String MESSAGE_RECEIVED_ACTION, BroadcastReceiver mMessageReceiver) {
////        mMessageReceiver = new MessageReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter.addAction(MESSAGE_RECEIVED_ACTION);
//        LocalBroadcastManagers.getInstance(BaseApp.get()).registerReceiver(mMessageReceiver, filter);
//    }
//
//    /**
//     * 销毁广播bufen
//     */
//    public static void des_Receiver() {
////        LocalBroadcastManagers.getInstance(BaseApp.get()).unregisterReceiver(mMessageReceiver);
//    }
//
//
//}
