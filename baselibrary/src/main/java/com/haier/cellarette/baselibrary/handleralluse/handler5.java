package com.haier.cellarette.baselibrary.handleralluse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

/**
 * 很明显的一点就是，我们要在子线程中调用Looper.prepare() 为一个线程开启一个消息循环，默认情况下Android中新诞生的线程是没有开启消息循环的。
 * （主线程除外，主线程系统会自动为其创建Looper对象，开启消息循环。） Looper对象通过MessageQueue来存放消息和事件。一个线程只能有一个Looper，
 * 对应一个MessageQueue。 然后通过Looper.loop() 让Looper开始工作，从消息队列里取消息，处理消息。
 * 注意：写在Looper.loop()之后的代码不会被执行，这个函数内部应该是一个循环，当调用mHandler.getLooper().quit()后，loop才会中止，其后的代码才能得以运行。
 * 然而这一切都可以用HandlerThread类来帮我们做这些逻辑操作。
 */
public class handler5 extends AppCompatActivity {

    private TextView tv1;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler5);
        tv1 = findViewById(R.id.tv1);

        new MyThread().start();
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.e("handler5--", "Threadname--" + Thread.currentThread().getName() + "messagewhat--" + msg.what);
//                    tv1.setText("Threadname--" + Thread.currentThread().getName() + "messagewhat--" + msg.what + "\n");
                }
            };

            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.sendEmptyMessage(2);

            Looper.loop();
        }
    }

}
