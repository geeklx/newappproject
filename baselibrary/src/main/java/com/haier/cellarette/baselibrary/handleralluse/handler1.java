package com.haier.cellarette.baselibrary.handleralluse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class handler1 extends AppCompatActivity {

    private TextView tv1;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tv1.setText(msg.obj + "");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler1);
        tv1 = findViewById(R.id.tv1);
        getimg();
    }

    private void getimg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //不带参数
//                mHandler.sendEmptyMessage(1);
                //带参数
                Message msg = Message.obtain();
                msg.obj = "支持Object类型";
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
