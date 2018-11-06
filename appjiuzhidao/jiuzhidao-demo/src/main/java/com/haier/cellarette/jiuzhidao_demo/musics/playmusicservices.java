package com.haier.cellarette.jiuzhidao_demo.musics;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.haier.cellarette.baselibrary.common.BaseApp;

public class playmusicservices extends Service {

    private boolean stateChange = true;
    private Thread thread;
    private int currentValue = 0;
    private int currentMaxValue = 100;

    class ServiceBinder extends Binder {
        public playmusicservices getService() {
            return playmusicservices.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        start();
    }

    private void start() {
        //动态进度条bufen
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {


                    if (stateChange == false) {
                        if (currentValue >= currentMaxValue) {
                            stateChange = true;
                        } else {
                            currentValue++;
                        }
                    } else {
                        if (currentValue <= 0) {
                            stateChange = false;
                            OpenMusic openMusic = new OpenMusic(BaseApp.get());

                        } else {
//                            progressBar.setProgress(currentValue - 1);
                            currentValue--;
//                            thread=null;
                        }
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
