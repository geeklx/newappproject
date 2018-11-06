package com.haier.cellarette.baselibrary.handleralluse;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class handler3 extends AppCompatActivity {

    private TextView tv1;

    private final Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //更新UI
            tv1.setText("这是3秒后我完成更新了的操作~");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler3);
        tv1 = findViewById(R.id.tv1);
        getimg();
    }

    private void getimg() {
        mHandler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }
}
