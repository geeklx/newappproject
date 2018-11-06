package com.example.slbappshare;

import android.os.Bundle;

import cn.jiguang.share.android.utils.Logger;
import cn.jiguang.share.facebook.FacebookBroadcastReceiver;

/**
 * Created by Ivanwu on 2017/8/8.
 */

public class FaceBookUploadReceiver extends FacebookBroadcastReceiver {

    private static final String TAG = "FaceBookUploadReceiver";

    @Override
    protected void onSuccessfulAppCall(String appCallId, String action, Bundle extras) {
        Logger.dd(TAG, String.format("Photo uploaded by call " + appCallId + " succeeded."));
    }

    @Override
    protected void onFailedAppCall(String appCallId, String action, Bundle extras) {
        Logger.dd(TAG, String.format("Photo uploaded by call " + appCallId + " failed."));
    }
}
