package com.haier.cellarette.jiuzhidao_demo.musics;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.jiuzhidao_demo.R;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;

import java.io.IOException;

public class OpenMusic implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private Context mContext;

    public OpenMusic(Context context) {
        mContext = context;
//      扫描威信二维码
        boolean order_raw_wx = (boolean) SpUtils.getInstance(context).get("order_raw_wx", false);

        playMusic();
        if (order_raw_wx) {
            SpUtils.getInstance(context).put("order_raw_wx", false);
            playMusic();
        }
    }

    private void playMusic() {
        Log.e("playMusic:", "playMusic");
        Uri uri = Uri.parse("android.resource://" + BaseApp.get().getPackageName() + "/" + R.raw.order);
//        if (null == mediaPlayer) {
//            mediaPlayer = MediaPlayer.create(mContext, R.raw.order);
//
//        }
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mContext, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e("playMusic:", "start");
//        mediaPlayer.start();
    }


    public void MusicDestory() {
        if (null != mediaPlayer ) {
            mediaPlayer.reset();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("playMusic:", "onCompletion");
        MusicDestory();
    }

//    public MediaPlayer getMediaPlayer(Context context) {
//
//        MediaPlayer mediaplayer = new MediaPlayer();
//
//        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
//            return mediaplayer;
//        }
//
//        try {
//            Class<?> cMediaTimeProvider = Class.forName("android.media.MediaTimeProvider");
//            Class<?> cSubtitleController = Class.forName("android.media.SubtitleController");
//            Class<?> iSubtitleControllerAnchor = Class.forName("android.media.SubtitleController$Anchor");
//            Class<?> iSubtitleControllerListener = Class.forName("android.media.SubtitleController$Listener");
//
//            Constructor constructor = cSubtitleController.getConstructor(new Class[]{Context.class, cMediaTimeProvider, iSubtitleControllerListener});
//
//            Object subtitleInstance = constructor.newInstance(context, null, null);
//
//            Field f = cSubtitleController.getDeclaredField("mHandler");
//
//            f.setAccessible(true);
//            try {
//                f.set(subtitleInstance, new Handler());
//            } catch (IllegalAccessException e) {
//                return mediaplayer;
//            } finally {
//                f.setAccessible(false);
//            }
//
//            Method setsubtitleanchor = mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor);
//
//            setsubtitleanchor.invoke(mediaplayer, subtitleInstance, null);
//            //Log.e("", "subtitle is setted :p");
//        } catch (Exception e) {
//        }
//
//        return mediaplayer;
//    }
}

