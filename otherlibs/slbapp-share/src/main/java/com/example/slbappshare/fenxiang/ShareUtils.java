package com.example.slbappshare.fenxiang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;

public class ShareUtils {

    private static final String TAG = ShareUtils.class.getName();
    public static String share_url = "http://blog.51cto.com/liangxiao";
    public static String share_text = "一个行走的geek，一个行走的CD";
    public static String share_title = " 梁肖技术中心-51CTO博客";
    public static String share_image_assets_url = "file:///android_asset/" + "img/geek_icon.png";
    public static String share_imageurl = "https://s2.51cto.com//wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg";
    public static String share_imageurl_1 = "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1308/02/c0/24056523_1375430477597.jpg";
    public static String share_videourl = "http://v.youku.com/v_show/id_XOTQwMDE1ODAw.html?from=s1.8-1-1.2&spm=a2h0k.8191407.0.0";
    public static String share_musicurl = "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3";
    public static String music_shareUrl = "https://y.qq.com/n/yqq/song/109325260_num.html?ADTAG=h5_playsong&no_redirect=1";


    /**
     * 从资源中获取的Drawable --> Bitmap
     *
     * @param context
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Context context, Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bm = bd.getBitmap();
        return bm;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */

    public static Bitmap bitmap;
    public static Bitmap returnBitMap(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }


    public void showShare(String name) {
        //这里以分享链接为例
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setTitle(share_title);
        shareParams.setText(share_text);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(share_url);
//        shareParams.setImagePath(ShareFileCopyAssets.ImagePath);
        shareParams.setImagePath(share_image_assets_url);
        JShareInterface.share(name, shareParams, mShareListener);
        setmHandlerThreads();

    }

    public void showShare1() {
        //这里以分享链接为例
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setTitle(share_title);
        shareParams.setText(share_text);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(share_url);
        shareParams.setImagePath(share_image_assets_url);

    }

    private PlatActionListener mShareListener = new PlatActionListener() {
        @Override
        public void onComplete(Platform platform, int action, HashMap<String, Object> data) {
            if (mHandler != null) {
                Message message = mHandler.obtainMessage();
                message.obj = "分享成功";
                mHandler.sendMessage(message);
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            Log.e(TAG, "error:" + errorCode + ",msg:" + error);
            if (mHandler != null) {
                Message message = mHandler.obtainMessage();
                message.obj = "分享失败:" + error.getMessage() + "---" + errorCode;
                mHandler.sendMessage(message);
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            if (mHandler != null) {
                Message message = mHandler.obtainMessage();
                message.obj = "分享取消";
                mHandler.sendMessage(message);
            }
        }
    };


    private HandlerThread mHandlerThread;
    private H mHandler;

    private class H extends Handler {

        public H(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作
            Log.d("handler ", "消息： " + msg.what + "  线程： " + Thread.currentThread().getName());
            String toastMsg = (String) msg.obj;
//            Toasty.info(get, toastMsg).show();
            //释放资源
            mHandlerThread.quit();
        }
    }

    public void setmHandlerThreads() {
        mHandlerThread = new HandlerThread("分享第三方图文Handler");
        mHandlerThread.start();
        mHandler = new H(mHandlerThread.getLooper());
    }

    public void desShare() {
        //释放资源
        mHandlerThread.quit();
    }
}
