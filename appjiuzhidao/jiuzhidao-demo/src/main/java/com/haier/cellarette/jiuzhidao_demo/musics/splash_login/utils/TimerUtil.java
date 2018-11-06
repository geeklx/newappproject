package com.haier.cellarette.jiuzhidao_demo.musics.splash_login.utils;

import android.app.Activity;
import android.content.Context;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/8.
 * Description:
 */
public class TimerUtil {

    private Context mContext;
    private int DELAY_TIME = 5;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public TimerUtil(Context mContext, int DELAY_TIME) {
        this.mContext = mContext;
        this.DELAY_TIME = DELAY_TIME;
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
    }

    /**
     * 开启定时任务
     *
     * @param onTimerSchedule
     */
    public void startTimer(final OnTimerSchedule onTimerSchedule) {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onTimerSchedule.onScheduleAtFixedRate();
                    }
                });
            }
        }, 0, DELAY_TIME, TimeUnit.SECONDS);
    }

    /**
     * 停止定时任务
     */
    public void stopTimer() {
        if (scheduledThreadPoolExecutor != null && !scheduledThreadPoolExecutor.isShutdown()) {
            scheduledThreadPoolExecutor.shutdown();
        }
    }

    public interface OnTimerSchedule {
        void onScheduleAtFixedRate();
    }
}
