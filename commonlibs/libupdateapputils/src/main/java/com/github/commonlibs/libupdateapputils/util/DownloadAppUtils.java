package com.github.commonlibs.libupdateapputils.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;


/**
 * Created by Teprinciple on 2016/12/13.
 */
class DownloadAppUtils {
    private static final String TAG = DownloadAppUtils.class.getSimpleName();
    public static long downloadUpdateApkId = -1;//下载更新Apk 下载任务对应的Id
    public static String downloadUpdateApkFilePath;//下载更新Apk 文件路径


    /**
     * 通过浏览器下载APK包
     *
     * @param context
     * @param url
     */
    public static void downloadForWebView(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public static void download(final Context context, String url, String apkLocalPath) {

        final String packageName = context.getPackageName();
        downloadUpdateApkFilePath = apkLocalPath;

        if (!FileApkUtil.isFileExists(downloadUpdateApkFilePath)) {
            if (UpdateAppUtils.showProgress)
                UpdateAppUtils.mProgressDialog.show();
        }

        FileDownloader.setup(context);

        FileDownloader.getImpl().create(url)
                .setPath(apkLocalPath)
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        send(context, (int) (soFarBytes * 100.0 / totalBytes), packageName);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        send(context, 100, packageName);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Toast.makeText(context, "下载出错", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                }).start();
    }

    private static void send(Context context, int progress, String serverVersionName) {
        Intent intent = new Intent("teprinciple.updates");
//        ComponentName componentName = new ComponentName(context.getPackageName(), context.getPackageName() + ".UpdateAppReceiver");
        intent.putExtra("progress", progress);
        intent.putExtra("title", serverVersionName);
//        intent.setComponent(componentName);
        context.sendBroadcast(intent);

    }
}
