package com.github.commonlibs.libupdateapputils.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.github.commonlibs.libupdateapputils.customview.ConfirmDialogNew;
import com.github.commonlibs.libupdateapputils.feature.Callback;
import com.github.commonlibs.libupdateapputils.view.AppUpdateProgressDialog;

import java.io.File;


/**
 * Created by Teprinciple on 2016/11/15.
 */
public class UpdateAppUtils {
    private final String TAG = "UpdateAppUtils";
    public static final int CHECK_BY_VERSION_NAME = 1001;
    public static final int CHECK_BY_VERSION_CODE = 1002;
    public static final int DOWNLOAD_BY_APP = 1003;
    public static final int DOWNLOAD_BY_BROWSER = 1004;

    private Activity activity;
    private int checkBy = CHECK_BY_VERSION_CODE;
    private int downloadBy = DOWNLOAD_BY_APP;
    private int serverVersionCode = 0;
    private String apkPath="";
    private String downloadPath="";
    private String apkLocalPath = "";
    private String serverVersionName="";
    private boolean isForce = false; //是否强制更新
    private int localVersionCode = 0;
    private String localVersionName="";
    public static boolean needFitAndroidN = true; //提供给 整个工程不需要适配到7.0的项目 置为false
    public static boolean showNotification = true;
    public static boolean showProgress = false;
    private String updateInfo = "";
    private String updateInfoTitle = "";

    public static AppUpdateProgressDialog mProgressDialog = null;

    public UpdateAppUtils needFitAndroidN(boolean needFitAndroidN) {
        UpdateAppUtils.needFitAndroidN = needFitAndroidN;
        return this;
    }

    private UpdateAppUtils(Activity activity) {
        this.activity = activity;
        getAPPLocalVersion(activity);
        downloadPath = activity.getPackageName() + ".apk";
        apkLocalPath = get_file_url(activity) + File.separator + downloadPath;
    }

    public static UpdateAppUtils from(Activity activity){
        return new UpdateAppUtils(activity);
    }

    public UpdateAppUtils checkBy(int checkBy){
        this.checkBy = checkBy;
        return this;
    }

    public UpdateAppUtils apkPath(String apkPath){
        this.apkPath = apkPath;
        return this;
    }

    public UpdateAppUtils downloadBy(int downloadBy){
        this.downloadBy = downloadBy;
        return this;
    }

    /**
     * 指定APK下载路径，base目录为/sdcard/Android/data/<Package Name>/cache/
     * @param downloadPath
     * @return
     */
    public UpdateAppUtils downloadPath(String downloadPath){
        this.downloadPath = downloadPath;
        this.apkLocalPath = get_file_url(activity) + File.separator + downloadPath;
        return this;
    }

    public UpdateAppUtils showNotification(boolean showNotification){
        UpdateAppUtils.showNotification = showNotification;
        return this;
    }

    public UpdateAppUtils showProgress(boolean showProgress){
        UpdateAppUtils.showProgress = showProgress;
        if (showNotification)
            mProgressDialog = new AppUpdateProgressDialog(activity);
        return this;
    }

    public UpdateAppUtils updateInfo(String updateInfo){
        this.updateInfo = updateInfo;
        return this;
    }

    public UpdateAppUtils updateInfoTitle(String updateInfoTitle){
        this.updateInfoTitle = updateInfoTitle;
        return this;
    }

    public UpdateAppUtils serverVersionCode(int serverVersionCode){
        this.serverVersionCode = serverVersionCode;
        return this;
    }

    public UpdateAppUtils serverVersionName(String serverVersionName){
        this.serverVersionName = serverVersionName;
        return this;
    }

    public UpdateAppUtils isForce(boolean  isForce){
        this.isForce = isForce;
        return this;
    }

    //获取apk的版本号 currentVersionCode
    private  void getAPPLocalVersion(Context ctx) {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            localVersionName = info.versionName; // 版本名
            localVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        switch (checkBy){
            case CHECK_BY_VERSION_CODE:
                if (serverVersionCode >localVersionCode){
                    toUpdate();
                }else {
                    Toast.makeText(activity, "当前版本是最新版本", Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;

            case CHECK_BY_VERSION_NAME:
                if (!serverVersionName.equals(localVersionName)){
                    toUpdate();
                }else {
                    Toast.makeText(activity, "当前版本是最新版本", Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;
        }

    }

    private void toUpdate() {
        removeLocalApk();
        realUpdate();
    }

    private void realUpdate() {
        ConfirmDialogNew dialog = new ConfirmDialogNew(activity, new Callback() {
            @Override
            public void callback(int position) {
                switch (position){
                    case 0:  //cancle
                        if (isForce) System.exit(0);
                        break;

                    case 1:  //sure
                        if (downloadBy == DOWNLOAD_BY_APP) {
                            if (isWifiConnected(activity)){
                                DownloadAppUtils.download(activity, apkPath, apkLocalPath);
                            }else {
                                new ConfirmDialogNew(activity, new Callback() {
                                    @Override
                                    public void callback(int position) {
                                        if (position==1){
                                            DownloadAppUtils.download(activity, apkPath, apkLocalPath);
                                        }else {
                                            if (isForce)activity.finish();
                                        }
                                    }
                                }).setContent("目前手机不是WiFi状态\n确认是否继续下载更新？").setTitle("友情提示").show();
                            }
                        }else if (downloadBy == DOWNLOAD_BY_BROWSER){
                            DownloadAppUtils.downloadForWebView(activity,apkPath);
                        }
                        break;
                }
            }
        });

        String content = "发现新版本:"+serverVersionName+"\n是否下载更新?";
        if (!TextUtils.isEmpty(updateInfo)){
//            content = "发现新版本:"+serverVersionName+"是否下载更新?\n\n"+updateInfo;
            content = updateInfo;
        }
        String title = "新版本已准备好";
        if (!TextUtils.isEmpty(updateInfo)){
            title = updateInfoTitle;
        }
        dialog.setTitle(title);
        dialog.setContent(content);
        dialog.setCancelable(false);
        dialog.show();
    }


    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    private void removeLocalApk(){
        int apkLocalVersionCode;

        if(FileApkUtil.isFileExists(apkLocalPath)) {  //如果本地已经下载了安装包APP，并且该安装包APP的VersionCode小于服务器，则删除该APP
            apkLocalVersionCode = FileApkUtil.getApkVersionCode(activity, apkLocalPath);
            if(apkLocalVersionCode < serverVersionCode) {
                FileApkUtil.deleteFile(apkLocalPath);
            }
        }
    }

    /**
     * 获取assets路径bufen
     *
     * @return
     */
    public String get_file_url(Context context) {
        String file_apk_url;
        File file_apks = context.getExternalCacheDir();
        if (file_apks != null) {
            file_apk_url = file_apks.getAbsolutePath();
        } else {
            file_apk_url = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return file_apk_url;
    }

}
