package com.haier.cellarette.baselibrary.baseactivitys;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.KeyEvent;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.toasts.ToastUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class MyBaseActivity extends CheckPermissionsActivity {
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.CAMERA
    };

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected String[] YouNeedPermissions() {
        return needPermissions;
    }

}
