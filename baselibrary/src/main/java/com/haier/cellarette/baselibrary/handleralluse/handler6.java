package com.haier.cellarette.baselibrary.handleralluse;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class handler6 extends AppCompatActivity {

    private TextView tv1;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private String aaaa = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler6);
        tv1 = findViewById(R.id.tv1);
        //创建一个线程,线程名字：handler-thread
        mHandlerThread = new HandlerThread("myHandlerThread");
        //开启一个线程
        mHandlerThread.start();
        //在这个线程中创建一个handler对象
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作
                Log.d("handler ", "消息： " + msg.what + "  线程： " + Thread.currentThread().getName());
                aaaa += "消息： " + msg.what + "  线程： " + Thread.currentThread().getName() + "\n";
                tv1.setText(aaaa);
            }
        };
        //在主线程给handler发送消息
        mHandler.sendEmptyMessage(111);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程给handler发送数据
                mHandler.sendEmptyMessage(222);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mHandlerThread.quit();
    }
}
