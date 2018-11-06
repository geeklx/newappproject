package com.example.slbappshare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.example.slbappshare.fenxiang.ShareFileCopyAssets;

import cn.jiguang.share.android.api.Platform;

public class ShareIndexActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareindex);
//        JShareInterface.setDebugMode(true);
//        PlatformConfig platformConfig = new PlatformConfig()
//                .setWechat("wxc40e16f3ba6ebabc", "dcad950cd0633a27e353477c4ec12e7a")
//                .setQQ("1106011004", "YIbPvONmBQBZUGaN")
//                .setSinaWeibo("374535501", "baccd12c166f1df96736b51ffbf600a2", "https://www.jiguang.cn")
//                .setFacebook("1847959632183996", "JShareDemo")
//                .setTwitter("fCm4SUcgYI1wUACGxB2erX5pL", "NAhzwYCgm15FBILWqXYDKxpryiuDlEQWZ5YERnO1D89VBtZO6q")
//                .setJchatPro("1847959632183996");
//        JShareInterface.init(this,platformConfig);
//        JShareInterface.init(this);


        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }


    public void BTN1(View view) {
        //分享弹窗bufen
        Intent intent = new Intent("hs.act.slbapp.ShareBottomActivity");
        intent.putExtra("type", Platform.ACTION_SHARE);//授权
        startActivity(intent);
    }

    public void BTN2(View view) {
        Intent intent = new Intent("hs.act.slbapp.ShareIndex1Activity");
        intent.putExtra("type", Platform.ACTION_AUTHORIZING);//授权
        startActivity(intent);
    }

    public void BTN3(View view) {
        Intent intent = new Intent("hs.act.slbapp.ShareIndex2Activity");
        intent.putExtra("type", Platform.ACTION_USER_INFO);//授权
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


}