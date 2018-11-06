package com.haier.cellarette.baselibrary.ringutil;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;


import java.io.IOException;

public class RingUtil {
    private static final String TAG = RingUtil.class.getSimpleName();
    public static final int SOUND_ALARM = 0;
    public static final int SOUND_MUSIC = 1;
    public static final int SOUND_DTMF = 2;
    public static final int SOUND_NOTIFICATION = 3;
    public static final int SOUND_RING = 4;
    public static final int SOUND_VOICE_CALL = 5;
    public static final int SOUND_SYSTEM = 6;

    /**
     * 播放指定音效
     * @param mContext
     * @param isDefault
     * @param i
     */
    public static void ring_uri_msg_msg(final Context mContext, final boolean isDefault, final int i) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                int whichRadio = (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDEFFECT, 3);
                Uri uri = null;
                switch (whichRadio) {
                    case 1:
                        uri = Uri.parse("android.resource://" + BaseApp.get().getPackageName() + "/" + R.raw.ring1);
                        break;
                    case 2:
                        uri = Uri.parse("android.resource://" + BaseApp.get().getPackageName() + "/" + R.raw.ring2);
                        break;
                    case 3:
                        uri = Uri.parse("android.resource://" + BaseApp.get().getPackageName() + "/" + R.raw.ring3);
                        break;
                }
                Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                final MediaPlayer player = new MediaPlayer();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        player.release();
                    }
                });
                try {
                    if (isDefault) {
                        player.setDataSource(mContext, alert);
                    } else {
                        player.setDataSource(mContext, uri);
                    }
                    final AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                    switch (i) {
                        case SOUND_ALARM:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_ALARM);
                                //player.setLooping(true);
                                player.prepare();
                                player.start();
                            }
                            break;
                        case SOUND_MUSIC:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                player.prepare();
                                player.start();
                            }
                            break;
                        case SOUND_DTMF:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_DTMF) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_DTMF);
                                player.prepare();
                                player.start();
                            }
                            break;
                        case SOUND_NOTIFICATION:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                                player.prepare();
                                player.start();
                            }
                            break;
                        case SOUND_RING:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_RING);
                                player.prepare();
                                player.start();
                            }
                            break;
                        case SOUND_VOICE_CALL:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
                                player.prepare();
                                player.start();
                            }
                            break;
                        case SOUND_SYSTEM:
                            if (audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM) != 0) {
                                player.setAudioStreamType(AudioManager.STREAM_SYSTEM);
                                player.prepare();
                                player.start();
                            }
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
       /* int whichCode = FridgeApplication.getInstance().getSpUtil().getCurrentNotify();
        playMusic(whichCode, false);*/
    }

    /**
     * 设置指定声音的音量
     *
     * @param audioManager
     * @param type         音量类型
     * @param progress     音量大小
     */
    public static void setVolume(AudioManager audioManager, int type, int progress) {
        switch (type) {
            case SOUND_ALARM:
                //设置闹钟音量
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, 0);
                break;
            case SOUND_MUSIC:
                //设置媒体（音乐）音量
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                break;
            case SOUND_NOTIFICATION:
                //设置通知音量
                audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, progress, 0);
                break;
            case SOUND_SYSTEM:
                //设置系统音量
                audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
                break;
            case SOUND_RING:
                //设置手机铃声音量
                audioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0);
                break;
            case SOUND_VOICE_CALL:
                //设置通话音量
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, 0);
                break;
            case SOUND_DTMF:
                //设置拨号键音量
                audioManager.setStreamVolume(AudioManager.STREAM_DTMF, progress, 0);
                break;
            default:
                break;
        }
    }

    /**
     * 获取当前指定声音的音量
     */
    public static int getCurrentVol(AudioManager audioManager, int type) {
        switch (type) {
            case SOUND_ALARM:
                //获取当前闹钟声音
                return audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
            case SOUND_MUSIC:
                //获取当前媒体（音乐）声音
                return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            case SOUND_NOTIFICATION:
                //获取当前通知声音
                return audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
            case SOUND_SYSTEM:
                //获取当前系统声音
                return audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
            case SOUND_RING:
                //获取当前手机铃声声音
                return audioManager.getStreamVolume(AudioManager.STREAM_RING);
            case SOUND_VOICE_CALL:
                //获取当前通话声音
                return audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
            case SOUND_DTMF:
                //获取当前拨号键声音
                return audioManager.getStreamVolume(AudioManager.STREAM_DTMF);
            default:
                return 0;
        }
    }

    /**
     * 获取当前指定声音的最大声音
     */
    public static int getMaxVol(AudioManager audioManager, int type) {
        switch (type) {
            case SOUND_ALARM:
                //获取当前闹钟最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
            case SOUND_MUSIC:
                //获取媒体（音乐）最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            case SOUND_NOTIFICATION:
                //获取当前通知最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
            case SOUND_SYSTEM:
                //获取当前系统最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
            case SOUND_RING:
                //获取当前手机铃声最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
            case SOUND_VOICE_CALL:
                //获取当前通话最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
            case SOUND_DTMF:
                //获取当前拨号键最大声音
                return audioManager.getStreamMaxVolume(AudioManager.STREAM_DTMF);
            default:
                return 0;
        }
    }

    /**
     * 测试音量
     */
    public static void getSoundVolume(AudioManager audioManager) {
        Log.e("geekSTREAM_ALARM", audioManager.getStreamVolume(AudioManager.STREAM_ALARM) + "");
        Log.e("geekSTREAM_MUSIC", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + "");
        Log.e("geekSTREAM_DTMF", audioManager.getStreamVolume(AudioManager.STREAM_DTMF) + "");
        Log.e("geekSTREAM_NOTIFICATION", audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) + "");
        Log.e("geekSTREAM_RING", audioManager.getStreamVolume(AudioManager.STREAM_RING) + "");
        Log.e("geekSTREAM_VOICE_CALL", audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL) + "");
        Log.e("geekSTREAM_SYSTEM", audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM) + "");
    }

}
