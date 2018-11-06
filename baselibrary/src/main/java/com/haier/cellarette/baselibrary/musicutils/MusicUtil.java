package com.haier.cellarette.baselibrary.musicutils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

public class MusicUtil implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static volatile MusicUtil instance;
    private MediaPlayer mediaPlayer;
    private Context mContext;

    public MusicUtil(Context mContext) {
        this.mContext = mContext;
    }

    public static MusicUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (MusicUtil.class) {
                if (instance == null) {
                    instance = new MusicUtil(context);
                }
            }
        }
        return instance;
    }

    public void playMusic(int uri) {
        Log.e("playMusic:", "playMusic");
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mContext, Uri.parse("android.resource://" + mContext.getPackageName() + "/" + uri));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("playMusic:error:", e.toString());
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
        if (null != mediaPlayer) {
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

}

