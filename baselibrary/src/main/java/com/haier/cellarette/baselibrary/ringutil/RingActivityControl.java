package com.haier.cellarette.baselibrary.ringutil;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class RingActivityControl {
    private static final String TAG = RingActivity.class.getSimpleName();
    /**
     * 公用函数集成 各音量调节seekbars 与 静音时switchbutton 的组合
     */
    private List<SeekBar> mSoundArray = new ArrayList<>();
    private RadioGroup mRgRingEffect;
    private SwitchButton mMute;
    private Context mContext;
    private SettingsContentObserver settingsContentObserver;//监听设置变化
    private AudioManager audioManager;
    private int mIdRings[];
    public static final String SOUND_ALARM = "SOUND_ALARM";
    public static final String SOUND_MUSIC = "SOUND_MUSIC";
    public static final String SOUND_DTMF = "SOUND_DTMF";
    public static final String SOUND_NOTIFICATION = "SOUND_NOTIFICATION";
    public static final String SOUND_RING = "SOUND_RING";
    public static final String SOUND_VOICE_CALL = "SOUND_VOICE_CALL";
    public static final String SOUND_SYSTEM = "SOUND_SYSTEM";


    public RingActivityControl(Context context, Handler handler) {
        mContext = context;
        settingsContentObserver = new SettingsContentObserver(mContext, handler);
//        audioManager = (AudioManager) BaseApp.get().getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 注册声音调节的Seekbar（声音类型记录到Seekbar的Tag）
     *
     * @param type
     * @param seekbar
     */
    public void ringRegisterVolSeekbar(String type, SeekBar seekbar) {
        seekbar.setTag(type);
        mSoundArray.add(seekbar);
    }

    /**
     * 注册静音的SwitchButton
     *
     * @param switchbutton
     */
    public void ringRegisterMuteSwitchbutton(SwitchButton switchbutton) {
        mMute = switchbutton;
    }

    /**
     * 注册音效选择的RadioGroup（声音类型记录到Seekbar的Tag）
     *
     * @param radiogroup
     */
    public void ringRegisterRingEffect(RadioGroup radiogroup, int... idRings) {
        mRgRingEffect = radiogroup;
        mIdRings = idRings;
    }

    /**
     * 设置静音与音量调节的监听
     */
    public void ringSetListener() {
        //静音与音量调节状态的初始化
        ring_set_ui_init_state();

        //设置静音开关的监听
        mMute.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //设置关闭静音
                    ring_close_mute(mSoundArray);
                } else {
                    //设置打开静音
                    ring_open_mute(mSoundArray);
                }
            }
        });

        //设置音效选择的监听
        mRgRingEffect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int idRadiobutton) {
                for (int i = 0; i < mIdRings.length; i++) {
                    if (mIdRings[i] == idRadiobutton)
                        ring_put_sp_sound_effect(i + 1);
                }
                ring_play_sound_effect(SOUND_MUSIC);
            }
        });

        //设置Seekbar的监听
        ring_set_seekbar_listener(mSoundArray);
    }

    /**
     * 静音与音量调节状态的更新
     * 基于系统音量变化，实时更新音量界面
     */
    public void ringUpdateState() {
        ring_put_sp_all_current_vol();
        int tag = ring_get_sp_mute_state();

        if (tag == 0) { //不静音
            for (SeekBar seekbar : mSoundArray) {
                String type = (String) seekbar.getTag();
                seekbar.setMax(ring_get_max_volume(audioManager, type));
                seekbar.setProgress(ring_get_sp_current_vol(type));
            }
        } else if (tag == 1) {  //静音
            if (ring_has_sound())
                mMute.setChecked(false);
        }
        RingUtil.getSoundVolume(audioManager);
    }

    /**
     * 开始监听系统音量，进行相应的变化
     */
    public void ringStartListenSystemVol() {
        mContext.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, settingsContentObserver);
    }

    /**
     * 结束对系统音量的监听
     */
    public void ringFinishListenSystemVol() {
        mContext.getContentResolver().unregisterContentObserver(settingsContentObserver);
    }

    /**
     * 静音与音量调节状态的初始化
     * 在设置静音与音量的监听之前，基于当前系统音量状态，设置音量界面和系统保持一致
     */
    public void ring_set_ui_init_state() {
        //初始化音效
        int ringN = ring_get_sp_sound_effect();
        mRgRingEffect.check(mIdRings[ringN - 1]);


        //初始化静音和音量调节
        ring_put_sp_all_current_vol();
        int tag = ring_get_sp_mute_state();

        if (tag == 0) { //不静音
            mMute.setChecked(false);
            for (SeekBar seekbar : mSoundArray) {
                String type = (String) seekbar.getTag();
                seekbar.setMax(ring_get_max_volume(audioManager, type));
                seekbar.setProgress(ring_get_sp_current_vol(type));
                ring_set_ui_seekbar(seekbar, false);
            }
        } else if (tag == 1) {  //静音
            if (ring_has_sound()) {
                ring_put_sp_mute_state(0);
                mMute.setChecked(false);
                for (SeekBar seekbar : mSoundArray) {
                    String type = (String) seekbar.getTag();
                    seekbar.setMax(ring_get_max_volume(audioManager, type));
                    seekbar.setProgress(ring_get_sp_current_vol(type));
                    ring_set_ui_seekbar(seekbar, false);
                }
            } else {
                mMute.setChecked(true);
                for (SeekBar seekbar : mSoundArray) {
                    String type = (String) seekbar.getTag();
                    seekbar.setMax(ring_get_max_volume(audioManager, type));
                    seekbar.setProgress(ring_get_sp_mute_vol(type));
                    ring_set_ui_seekbar(seekbar, true);
                }
            }
        } else if (tag == -1) {  //之前没有设置过静音标志位
            if (ring_has_sound()) {
                ring_put_sp_mute_state(0);
                mMute.setChecked(false);
                for (SeekBar seekbar : mSoundArray) {
                    String type = (String) seekbar.getTag();
                    seekbar.setMax(ring_get_max_volume(audioManager, type));
                    seekbar.setProgress(ring_get_sp_current_vol(type));
                    ring_set_ui_seekbar(seekbar, false);
                }
            } else {
                ring_put_sp_mute_state(1);
                mMute.setChecked(true);
                for (SeekBar seekbar : mSoundArray) {
                    String type = (String) seekbar.getTag();
                    ring_put_sp_mute_vol(type, 0);
                    seekbar.setMax(ring_get_max_volume(audioManager, type));
                    seekbar.setProgress(ring_get_sp_mute_vol(type));
                    ring_set_ui_seekbar(seekbar, true);
                }
            }
        }
        RingUtil.getSoundVolume(audioManager);
    }

    /**
     * 判断当前实际是否有声音
     * （注：当前的规则认为所有seekbar注册的音量类型的当前音量之和大于0，否则认为是实际是静音情况）
     *
     * @return true：有声音  false：没声音
     */
    private boolean ring_has_sound() {
        int sum = 0;
        for (SeekBar seekbar : mSoundArray) {
            String type = (String) seekbar.getTag();
            sum += ring_get_sp_current_vol(type);
        }
        return sum > 0;
    }

    /**
     * 设置音量调节Seekbar的监听
     *
     * @param seekbars
     */
    private void ring_set_seekbar_listener(List<SeekBar> seekbars) {
        for (SeekBar seekbar : seekbars) {
            //设置媒体seekbar监听
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean isUser) {
                    String type = (String) seekBar.getTag();  //获取当前Seekbar的类型
                    int tag = ring_get_sp_mute_state();       //1：静音  0：非静音
                    if (tag == 0 && isUser) {                 //如果是用户操作，设置音量并更新当前音量的SP值
                        ring_set_volume(audioManager, type, i);
                        ring_put_sp_current_vol(type);
                    } else if (tag == 0 && !isUser) {         //如果不是用户操作，不做任何操作（仅更新UI）
                    } else if (tag == 1 && isUser)            //如果是用户操作，设置静音时的SP值
                        ring_put_sp_mute_vol(type, i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {
                    String type = (String) seekBar.getTag();
                    ring_play_sound_effect(type);
                }
            });
        }
    }

    /**
     * 设置声音调节Seekbar的UI样式
     *
     * @param seekbar
     * @param isMute
     */
    private void ring_set_ui_seekbar(SeekBar seekbar, boolean isMute) {
        if (isMute) {
            seekbar.setProgressDrawable(mContext.getDrawable(R.drawable.volume_bg_progress_grey));
            seekbar.setThumb(mContext.getDrawable(R.drawable.volume_thumb_grey));
        } else {
            seekbar.setProgressDrawable(mContext.getDrawable(R.drawable.volume_bg_progress));
            seekbar.setThumb(mContext.getDrawable(R.drawable.volume_thumb));
        }
    }

    /**
     * 关闭静音事件的操作
     *
     * @param seekbars
     */
    private void ring_close_mute(List<SeekBar> seekbars) {
        ring_put_sp_mute_state(0);      //设置静音标志位：非静音
        for (SeekBar seekbar : seekbars) {
            String type = (String) seekbar.getTag();
            int maxVol = ring_get_max_volume(audioManager, type);
            seekbar.setMax(maxVol);
            if (ring_has_sound()) {  //如果实际有声音，seekbar位置根据实际声音SP恢复
                int currentVol = ring_get_sp_current_vol(type);
                seekbar.setProgress(currentVol);
            } else {                 //如果实际没有声音，seekbar位置基于静音备份SP恢复，将该静音备份设置给当前声音
                int currentVol = ring_get_sp_mute_vol(type);
                seekbar.setProgress(currentVol);
                ring_set_volume(audioManager, type, currentVol);
            }
            ring_set_ui_seekbar(seekbar, false);
        }
    }

    /**
     * 打开静音事件的操作
     *
     * @param seekbars
     */
    private void ring_open_mute(List<SeekBar> seekbars) {
        ring_put_sp_mute_state(1);      //设置静音标志位：静音
        for (SeekBar seekbar : seekbars) {
            String type = (String) seekbar.getTag();
            int currentVol = seekbar.getProgress();
            ring_put_sp_mute_vol(type, currentVol);
            ring_set_volume(audioManager, type, 0);
            ring_put_sp_current_vol(type);
            ring_set_ui_seekbar(seekbar, true);
        }
    }

    /**
     * 设置指定类型声音的音量
     *
     * @param audioManager
     * @param type
     * @param i
     */
    private void ring_set_volume(AudioManager audioManager, String type, int i) {
        Log.e(TAG, "ring_set_volume  " + type + ": " + i);
        if (type.equals(SOUND_ALARM)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_ALARM, i);
        } else if (type.equals(SOUND_MUSIC)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_MUSIC, i);
        } else if (type.equals(SOUND_DTMF)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_DTMF, i);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_NOTIFICATION, i);
        } else if (type.equals(SOUND_RING)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_RING, i);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_VOICE_CALL, i);
        } else if (type.equals(SOUND_SYSTEM)) {
            RingUtil.setVolume(audioManager, RingUtil.SOUND_SYSTEM, i);
        }
    }

    /**
     * 获取指定类型声音的最大音量
     *
     * @param audioManager
     * @param type
     * @return
     */
    private int ring_get_max_volume(AudioManager audioManager, String type) {
        if (type.equals(SOUND_ALARM)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_ALARM);
        } else if (type.equals(SOUND_MUSIC)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_MUSIC);
        } else if (type.equals(SOUND_DTMF)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_DTMF);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_NOTIFICATION);
        } else if (type.equals(SOUND_RING)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_RING);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_VOICE_CALL);
        } else if (type.equals(SOUND_SYSTEM)) {
            return RingUtil.getMaxVol(audioManager, RingUtil.SOUND_SYSTEM);
        }
        return 0;
    }

    /**
     * 获取指定类型声音的当前音量
     *
     * @param audioManager
     * @param type
     * @return
     */
    private int ring_get_current_volume(AudioManager audioManager, String type) {
        if (type.equals(SOUND_ALARM)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_ALARM);
        } else if (type.equals(SOUND_MUSIC)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_MUSIC);
        } else if (type.equals(SOUND_DTMF)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_DTMF);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_NOTIFICATION);
        } else if (type.equals(SOUND_RING)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_RING);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_VOICE_CALL);
        } else if (type.equals(SOUND_SYSTEM)) {
            return RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_SYSTEM);
        }
        return 0;
    }

    /**
     * 获取静音状态从SP文件中
     *
     * @return 1：静音  0：非静音  -1：第一次检测静音状态
     */
    private int ring_get_sp_mute_state() {
        return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDTAG, -1);
    }

    /**
     * 记录静音状态到SP文件
     *
     * @param i 1：静音  0：非静音
     */
    private void ring_put_sp_mute_state(int i) {
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDTAG, i);
    }

    /**
     * 记录指定类型音量到静音声音SP
     *
     * @param type
     * @param i
     */
    private void ring_put_sp_mute_vol(String type, int i) {
        if (type.equals(SOUND_ALARM)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDALARM, i);
        } else if (type.equals(SOUND_MUSIC)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDMUSIC, i);
        } else if (type.equals(SOUND_DTMF)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDDTMF, i);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDNOTICE, i);
        } else if (type.equals(SOUND_RING)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDRING, i);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDVOICECALL, i);
        } else if (type.equals(SOUND_SYSTEM)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.QUIETSOUNDSYSTEM, i);
        }
    }

    /**
     * 获取指定类型音量从静音声音SP
     *
     * @param type
     * @return
     */
    private int ring_get_sp_mute_vol(String type) {
        if (type.equals(SOUND_ALARM)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDALARM, -1);
        } else if (type.equals(SOUND_MUSIC)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDMUSIC, -1);
        } else if (type.equals(SOUND_DTMF)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDDTMF, -1);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDNOTICE, -1);
        } else if (type.equals(SOUND_RING)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDRING, -1);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDVOICECALL, -1);
        } else if (type.equals(SOUND_SYSTEM)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDSYSTEM, -1);
        }
        return 0;
    }

    /**
     * 记录指定类型当前音量到当前声音SP
     *
     * @param type
     */
    private void ring_put_sp_current_vol(String type) {
        if (type.equals(SOUND_ALARM)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDALARM, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_ALARM));
        } else if (type.equals(SOUND_MUSIC)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDMUSIC, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_MUSIC));
        } else if (type.equals(SOUND_DTMF)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDDTMF, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_DTMF));
        } else if (type.equals(SOUND_NOTIFICATION)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDNOTICE, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_NOTIFICATION));
        } else if (type.equals(SOUND_RING)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDRING, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_RING));
        } else if (type.equals(SOUND_VOICE_CALL)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDVOICECALL, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_VOICE_CALL));
        } else if (type.equals(SOUND_SYSTEM)) {
            RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDSYSTEM, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_SYSTEM));
        }
    }

    /**
     * 记录所有类型当前音量到当前声音SP
     */
    private void ring_put_sp_all_current_vol() {
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDALARM, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_ALARM));
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDMUSIC, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_MUSIC));
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDDTMF, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_DTMF));
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDNOTICE, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_NOTIFICATION));
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDRING, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_RING));
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDVOICECALL, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_VOICE_CALL));
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDSYSTEM, RingUtil.getCurrentVol(audioManager, RingUtil.SOUND_SYSTEM));
    }

    /**
     * 获取指定类型音量从当前声音SP
     *
     * @param type
     * @return
     */
    private int ring_get_sp_current_vol(String type) {
        if (type.equals(SOUND_ALARM)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDALARM, -1);
        } else if (type.equals(SOUND_MUSIC)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDMUSIC, -1);
        } else if (type.equals(SOUND_DTMF)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDDTMF, -1);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDNOTICE, -1);
        } else if (type.equals(SOUND_RING)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDRING, -1);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDVOICECALL, -1);
        } else if (type.equals(SOUND_SYSTEM)) {
            return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDSYSTEM, -1);
        }
        return 0;
    }

    /**
     * 打印SP文件中的所有变量
     */
    private void ring_print_sp_all_vars() {
        Log.e(TAG, "------------------ ring_print_sp_all_vars ------------------ ");
        Log.e(TAG, RingConstants.CURRENTSOUNDALARM + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDALARM, -1));
        Log.e(TAG, RingConstants.CURRENTSOUNDMUSIC + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDMUSIC, -1));
        Log.e(TAG, RingConstants.CURRENTSOUNDDTMF + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDDTMF, -1));
        Log.e(TAG, RingConstants.CURRENTSOUNDNOTICE + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDNOTICE, -1));
        Log.e(TAG, RingConstants.CURRENTSOUNDRING + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDRING, -1));
        Log.e(TAG, RingConstants.CURRENTSOUNDVOICECALL + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDVOICECALL, -1));
        Log.e(TAG, RingConstants.CURRENTSOUNDSYSTEM + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDSYSTEM, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDTAG + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDTAG, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDALARM + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDALARM, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDMUSIC + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDMUSIC, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDDTMF + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDDTMF, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDNOTICE + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDNOTICE, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDRING + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDRING, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDVOICECALL + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDVOICECALL, -1));
        Log.e(TAG, RingConstants.QUIETSOUNDSYSTEM + ": " + RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.QUIETSOUNDSYSTEM, -1));
    }

    /**
     * 设置播放音效
     *
     * @param i
     */
    private void ring_put_sp_sound_effect(int i) {
        RingSpUtils.getInstance(BaseApp.get()).put(RingConstants.CURRENTSOUNDEFFECT, i);
    }

    /**
     * 获取播放音效
     */
    private int ring_get_sp_sound_effect() {
        return (int) RingSpUtils.getInstance(BaseApp.get()).get(RingConstants.CURRENTSOUNDEFFECT, 1);
    }

    /**
     * 使用指定声音类型播放音效（当用户调节声音时，进行音效播放）
     *
     * @param type
     */
    private void ring_play_sound_effect(String type) {
        if (type.equals(SOUND_ALARM)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_ALARM);
        } else if (type.equals(SOUND_MUSIC)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_MUSIC);
        } else if (type.equals(SOUND_DTMF)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_DTMF);
        } else if (type.equals(SOUND_NOTIFICATION)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_NOTIFICATION);
        } else if (type.equals(SOUND_RING)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_RING);
        } else if (type.equals(SOUND_VOICE_CALL)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_VOICE_CALL);
        } else if (type.equals(SOUND_SYSTEM)) {
            RingUtil.ring_uri_msg_msg(mContext, false, RingUtil.SOUND_SYSTEM);
        }
    }

    /**
     * 当前Activity处于Running状态时，获取音量状态变化
     */
    private class SettingsContentObserver extends ContentObserver {
        public SettingsContentObserver(Context context, Handler handler) {
            super(handler);
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return false;
        }

        @Override
        public void onChange(boolean selfChange) {
            ringUpdateState();
        }
    }
}
