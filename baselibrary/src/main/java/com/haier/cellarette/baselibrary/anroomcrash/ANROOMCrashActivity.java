package com.haier.cellarette.baselibrary.anroomcrash;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

import java.util.ArrayList;
import java.util.List;

public class ANROOMCrashActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anroomcrash);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //anr
                triggerANR();
//                add_neicun();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //oom
                add_neicun();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crash
                System.exit(0);
            }
        });
    }

    private void triggerANR() {
        try {
            System.out.println(System.currentTimeMillis());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Bitmap[]> list = new ArrayList<>();

    private void add_neicun() {
        for (int i = 0; i < 3000000; i++) {
            Bitmap[] bytes = new Bitmap[1024];
            list.add(bytes);
        }
    }
}
