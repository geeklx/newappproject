package com.example.slbappshare.denglu;

/**
 * 授权登录的回调接口
 *
 * @author geek
 */
public interface OnResultInfoLitener {
    void onResults(String platform, String toastMsg, String data);
}