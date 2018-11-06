//package com.example.slbappindex.fragment.fragment2.comm.demo;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//
//import com.example.slbappindex.fragment.fragment2.comm.demo.OnResultInfoReceiveLitener;
//
//public class MessageReceiver extends BroadcastReceiver {
//
//    private OnResultInfoReceiveLitener mListener;
//
//    public MessageReceiver(OnResultInfoReceiveLitener mListener) {
//        this.mListener = mListener;
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        try {
//            if (FragmentBroadcastUtil.BC_fragment21.equals(intent.getAction())) {
////                    String messge = intent.getStringExtra(KEY_MESSAGE);
////                    String extras = intent.getStringExtra(KEY_EXTRAS);
////                    StringBuilder showMsg = new StringBuilder();
////                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
////                    if (!YanzhengUtil.isEmpty(extras)) {
////                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
////                    }
////                    setCostomMsg(showMsg.toString());
//                // 刷新图片bufen
//                if (mListener != null) {
//                    mListener.onResults(FragmentBroadcastUtil.BC_fragment21);
//                }
//            }
//            if (FragmentBroadcastUtil.BC_fragment22.equals(intent.getAction())) {
////                   //刷新视频bufen
//                if (mListener != null) {
//                    mListener.onResults(FragmentBroadcastUtil.BC_fragment22);
//                }
//            }
//        } catch (Exception e) {
//        }
//    }
//}