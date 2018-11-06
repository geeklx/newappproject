package com.haier.cellarette.baselibrary.baseactivitys;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.haier.cellarette.baselibrary.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CheckPermissionsActivity extends BaseActivity {

    private List<String> needRequestPermissonList;
    private boolean isInstalls = true;
    private Dialog dialog;

    protected abstract String[] YouNeedPermissions();

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES

    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onResume() {
        super.onResume();

        if (null != YouNeedPermissions() || YouNeedPermissions().length != 0) {
            needPermissions = YouNeedPermissions();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            isInstalls = getPackageManager().canRequestPackageInstalls();
            if (isInstalls) {
                deletedPermission();
            }
        } else {
            deletedPermission();
        }
        if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }

    }

    private void deletedPermission() {
        for (int x = 0; x < needPermissions.length; x++) {
            if (needPermissions[x].equals(Manifest.permission.REQUEST_INSTALL_PACKAGES)) {
                needPermissions[x] = needPermissions[needPermissions.length - 1];
                needPermissions = Arrays.copyOf(needPermissions, needPermissions.length - 1);
            }
        }
    }

    /**
     * @param permissions
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", String[].class,
                            int.class);

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer) checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean) shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(26)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        if (requestCode == 10086) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                isInstalls = getPackageManager().canRequestPackageInstalls();
                if (!isInstalls) {
                    showMissingPermissionDialog();
                } else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        if (ScreenUtils.isTablet()){
            return;
        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示");
//        if (!isInstalls) {
//            builder.setMessage("安装应用需要打开未知来源权限。"/* + "\n\n" + "请点击|" + "设置|" + "更多设置|" + "系统安全|" + "安装未知应用|" + "-允许酒知道安装。"*/);
//        } else {
//            builder.setMessage("当前应用缺少必要权限。" + "\n\n" + "请点击|" + "设置|" + "权限" + "-打开所需权限。");
//
//        }
//
//        // 拒绝, 退出应用
//        builder.setNegativeButton("取消",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//
//        builder.setPositiveButton("设置",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startAppSettings();
//                    }
//                });
//
//
//        builder.setCancelable(false);
//
//        builder.show();

        dialog = new Dialog(this, R.style.notice_dialog);
        if (dialog.isShowing()) {
            return;
        }
        dialog.setContentView(R.layout.notice_dialog);
        dialog.setCancelable(false);
        dialog.show();
        TextView tv_notice = dialog.findViewById(R.id.tv_notice);
        Button btn_concle = dialog.findViewById(R.id.btn_concle);
        Button btn_settings = dialog.findViewById(R.id.btn_settings);
        if (!isInstalls) {
            tv_notice.setText("安装应用需要打开未知来源权限" + "\n\n" + "请点击|" + "设置|" + "更多设置|" + "系统安全|" + "安装未知应用|" + "-允许酒知道安装");
        } else {
            tv_notice.setText("当前应用缺少必要权限" + "\n\n" + "请点击|" + "设置|" + "权限" + "-打开所需权限");
        }
        btn_concle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppSettings();
            }
        });


    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 跳到列表
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
//            startActivityForResult(intent, 10086);
            // 跳到单一bufen
            Uri packageURI = Uri.parse("package:" + Utils.getApp().getPackageName());
            //注意这个是8.0新API
            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
            startActivityForResult(intent, 10086);
        } else {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            this.finish();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
