package com.haier.cellarette.baselibrary.musicutils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haier.cellarette.baselibrary.R;

public class MusicActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicact);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MusicUtil.getInstance(MusicActivity.this).playMusic(R.raw.ring1);
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicUtil.getInstance(MusicActivity.this).playMusic(R.raw.ring2);
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicUtil.getInstance(MusicActivity.this).playMusic(R.raw.ring3);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicUtil.getInstance(MusicActivity.this).MusicDestory();
    }
}
