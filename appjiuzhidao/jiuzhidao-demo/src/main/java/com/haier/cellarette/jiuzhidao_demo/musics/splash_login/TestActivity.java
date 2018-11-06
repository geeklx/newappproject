package com.haier.cellarette.jiuzhidao_demo.musics.splash_login;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.jiuzhidao_demo.R;
import com.haier.cellarette.jiuzhidao_demo.musics.OpenMusic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestActivity extends MyBaseActivity {
    private ScheduledExecutorService scheduledExecutorService;
    private int i;
//    private OpenMusic openMusic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
//        supportActionBar.setCustomView();
        supportActionBar.setTitle("TestActivity");
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (i == 3) {
                    scheduledExecutorService.shutdownNow();
                }
                i++;
//                showNotification(getApplicationContext(), 1, "您有新的酒知道订单。", "点击进入");
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    private void showNotification(Context context, int id, String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "您有新的酒知道订单。");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
        Intent resultIntent = new Intent(context, TestActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
        new OpenMusic(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        openMusic.MusicDestory();
    }
}
