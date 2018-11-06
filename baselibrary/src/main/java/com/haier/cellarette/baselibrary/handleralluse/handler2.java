package com.haier.cellarette.baselibrary.handleralluse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class handler2 extends AppCompatActivity {

    private TextView tv1;

    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);
        tv1 = findViewById(R.id.tv1);
        getimg();
    }

    private void getimg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //更新UI
                        tv1.setText("我更新了~");
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
   
    }
}
