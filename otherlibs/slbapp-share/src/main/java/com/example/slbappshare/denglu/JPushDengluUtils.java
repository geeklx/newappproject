package com.example.slbappshare.denglu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import cn.jiguang.share.android.api.AuthListener;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.model.AccessTokenInfo;
import cn.jiguang.share.android.model.BaseResponseInfo;
import cn.jiguang.share.android.model.UserInfo;
import cn.jiguang.share.android.utils.Logger;

public class JPushDengluUtils {

    private static final String TAG = "JPushDengluUtils";

    private OnResultInfoLitener onResultInfoLitener;

    public JPushDengluUtils(OnResultInfoLitener onResultInfoLitener) {
        this.onResultInfoLitener = onResultInfoLitener;

    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
//            String toastMsg = (String) msg.obj;
            Bundle b = msg.getData();
            String platform = b.getString("platform");
            String toastMsg = b.getString("toastMsg");
            String data = b.getString("data");
            if (onResultInfoLitener != null) {
                onResultInfoLitener.onResults(platform, toastMsg, data);
            }

            return false;
        }
    });

    /**
     * 设置授权+ 取消授权bufen
     *
     * @param name
     */
    public void shezhi_shouquan(String name) {
        if (!en_shouquan(name)) {
            shouquan(name);
        } else {
            shouquan_cancel(name);
        }
    }

    /**
     * 设置授权+ 获取个人信息bufen
     *
     * @param name
     */
    public void shezhi_shouquan_getinfo(String name) {
        JShareInterface.getUserInfo(name, mAuthListener);
    }

    /**
     * 销毁bufen
     */
    public void ondes() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    /**
     * 判断是否授权登录bufen
     *
     * @param name_platform
     */
    public boolean en_shouquan(String name_platform) {
        return JShareInterface.isAuthorize(name_platform);
    }

    /**
     * 授权登录bufen
     *
     * @param name_platform
     */
    public void shouquan(String name_platform) {
        if (!JShareInterface.isAuthorize(name_platform)) {
            JShareInterface.authorize(name_platform, mAuthListener);
        }
    }

    /**
     * 授权登录 取消bufen
     *
     * @param name_platform
     */
    public void shouquan_cancel(String name_platform) {
        if (JShareInterface.isAuthorize(name_platform)) {
            JShareInterface.removeAuthorize(name_platform, mAuthListener);
        }
    }


    /**
     * 授权、获取个人信息回调
     * action ：Platform.ACTION_AUTHORIZING 授权
     * Platform.ACTION_USER_INFO 获取个人信息
     */
    public AuthListener mAuthListener = new AuthListener() {
        @Override
        public void onComplete(Platform platform, int action, BaseResponseInfo data) {
            Logger.dd(TAG, "onComplete:" + platform + ",action:" + action + ",data:" + data);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    if (data instanceof AccessTokenInfo) {        //授权信息
                        String token = ((AccessTokenInfo) data).getToken();//token
                        long expiration = ((AccessTokenInfo) data).getExpiresIn();//token有效时间，时间戳
                        String refresh_token = ((AccessTokenInfo) data).getRefeshToken();//refresh_token
                        String openid = ((AccessTokenInfo) data).getOpenid();//openid
                        //授权原始数据，开发者可自行处理
                        String originData = data.getOriginData();
//                        toastMsg = "授权成功:" + data.toString();
                        toastMsg = "授权成功:";
                        Logger.dd(TAG, "openid:" + openid + ",token:" + token + ",expiration:" + expiration + ",refresh_token:" + refresh_token);
                        Logger.dd(TAG, "originData:" + originData);
                    }
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权成功";
                    break;
                case Platform.ACTION_USER_INFO:
                    if (data instanceof UserInfo) {      //第三方个人信息
                        String openid = ((UserInfo) data).getOpenid();  //openid
                        String name = ((UserInfo) data).getName();  //昵称
                        String imageUrl = ((UserInfo) data).getImageUrl();  //头像url
                        int gender = ((UserInfo) data).getGender();//性别, 1表示男性；2表示女性
                        //个人信息原始数据，开发者可自行处理
                        String originData = data.getOriginData();
                        toastMsg = "获取个人信息成功:";
//                        toastMsg = "获取个人信息成功:" + data.toString();
                        Logger.dd(TAG, "openid:" + openid + ",name:" + name + ",gender:" + gender + ",imageUrl:" + imageUrl);
                        Logger.dd(TAG, "originData:" + originData);
                    }
                    break;
            }
            she_handler_msg(1, platform.getName(), toastMsg, data.toString());
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            Logger.dd(TAG, "onError:" + platform + ",action:" + action + ",error:" + error);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "授权失败";
                    break;
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    toastMsg = "删除授权失败";
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "获取个人信息失败";
                    break;
            }
            she_handler_msg(1, platform.getName(), toastMsg + (error != null ? error.getMessage() : "") + "---" + errorCode, "");

        }

        @Override
        public void onCancel(Platform platform, int action) {
            Logger.dd(TAG, "onCancel:" + platform + ",action:" + action);
            String toastMsg = null;
            switch (action) {
                case Platform.ACTION_AUTHORIZING:
                    toastMsg = "取消授权";
                    break;
                // TODO: 2017/6/23 删除授权不存在取消
                case Platform.ACTION_REMOVE_AUTHORIZING:
                    break;
                case Platform.ACTION_USER_INFO:
                    toastMsg = "取消获取个人信息";
                    break;
            }
            she_handler_msg(1, platform.getName(), toastMsg, "");
//            if (handler != null) {
//                Message msg = handler.obtainMessage(3);
//                msg.obj = toastMsg;
//                msg.sendToTarget();
//            }
        }
    };

    private void she_handler_msg(int what, String platform, String toastMsg, String datas) {
        if (mHandler == null) {
            return;
        }
        Message msg = mHandler.obtainMessage(what);
//                msg.obj = data.toString();
        Bundle b = new Bundle();
        b.putString("platform", platform);
        b.putString("toastMsg", toastMsg);
        b.putString("data", datas);
        msg.setData(b);
        msg.sendToTarget();
    }

//    /**
//     * ItemClick的回调接口
//     *
//     * @author geek
//     */
//    public interface OnResultInfoLitener {
//        void onComplete(Platform platform, BaseResponseInfo data);
//        void onError(Platform platform, String toastMsg);
//        void onCancel(Platform platform, String toastMsg);
//    }
//
//    private OnItemClickLitener mOnItemClickLitener;
//
//    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }


}
