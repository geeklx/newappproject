package com.example.slbappindex.fragment.fragment2.utils;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Description:
 * <br> 使用 IntentService 实现下载
 */

public class DownloadServiceMp4 extends IntentService {

    private static final String TAG = "DownloadServiceMp4";
    public static final String DOWNLOAD_URL = "down_load_url";
    public static final int WHAT_DOWNLOAD_FINISHED = 1;
    public static final int WHAT_DOWNLOAD_STARTED = 2;
    private boolean iscancel = false;
    private boolean isfinish = false;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;
    // 复制完成后传值的视频地址
    private String VideoPath;

    public DownloadServiceMp4() {
        super(TAG);

    }

    private static Handler mUIHandler;

    public static void setUIHandler(final Handler UIHandler) {
        mUIHandler = UIHandler;
    }

    /**
     * 这个方法运行在子线程
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(final Intent intent) {
        String assets = intent.getStringExtra(DOWNLOAD_URL);
        if (!TextUtils.isEmpty(assets)) {
            String name = assets.substring(assets.indexOf("/") + 1);
            downloadUrlToApk(assets, name, 0);// "mp4/a.mp4"   "a.mp4"
        }
    }

    /**
     * 发送消息到主线程
     *
     * @param id
     * @param o
     */
    private void sendMessageToMainThread(final int id, final Object o) {
        if (mUIHandler != null) {
            mUIHandler.sendMessage(mUIHandler.obtainMessage(id, o));
        }

    }

    /**
     * 复制加载文件
     */
    private void downloadUrlToApk(String assets, String name, int flag) {
        File filesDir = null;
        try {
            if (flag == 0) {//copy to sdcard
                filesDir = new File(ConstantsUtils.file_url_mp4 + name);
                File parentDir = filesDir.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
            } else {//copy to data
                filesDir = new File(BaseApp.get().getFilesDir(), name);
            }
            if (!filesDir.exists()) {
                filesDir.createNewFile();
                InputStream open = BaseApp.get().getAssets().open(assets);
                // 获取文件大小
//                String assets_url = "file:///android_asset/" + assets;
//                int length = Integer.parseInt(FileUtils.getFileSize(new File(assets_url)));
                FileOutputStream fileOutputStream = new FileOutputStream(filesDir);
                byte[] buffer = new byte[4 * 1024];
                int length = open.available();
                int count = 0;
                do {
                    int numread = open.read(buffer);
                    count += numread;
                    // 计算进度条位置
                    progress = (int) (((float) count / length) * 100);
                    sendMessageToMainThread(WHAT_DOWNLOAD_STARTED, progress);
                    if (numread <= 0) {
                        // 下载完成
                        sendMessageToMainThread(WHAT_DOWNLOAD_FINISHED, ConstantsUtils.file_url_mp4 + name);
                        break;
                    }
                    // 写入文件
                    fileOutputStream.write(buffer, 0, numread);
                } while (!cancelUpdate);// 点击取消就停止下载.

                open.close();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (flag == 0) {
                downloadUrlToApk(assets, name, 1);
            }
        }


    }


}