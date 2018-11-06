package com.haier.cellarette.jiuzhidao_demo.musics.splash_login;

import android.Manifest;
import android.view.KeyEvent;

import com.haier.cellarette.baselibrary.baseactivitys.CheckPermissionsActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class MyBaseActivity extends CheckPermissionsActivity {
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES
    };

    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected String[] YouNeedPermissions() {
        return needPermissions;
    }
//
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toasty.warning(getApplicationContext(), "退出后将无法接收新订单提醒！").show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
