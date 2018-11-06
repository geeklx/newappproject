package com.haier.cellarette.baselibrary.changelanguage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class ChangeLanActivity extends AppCompatActivity {

    private TextView mUserSelect;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelanguage);
        mUserSelect = findViewById(R.id.tv_user_select);
        mUserSelect.setText(getString(R.string.user_select_language, LocalManageUtil.getSelectLanguage(this)));
        //
        setClick();

    }


    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this, select);
        setupagain();
    }

    private void setClick() {
        //跟随系统
        findViewById(R.id.btn_auto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLanguage(0);
            }
        });
        //简体中文
        findViewById(R.id.btn_cn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLanguage(1);
            }
        });
        //繁体中文
        findViewById(R.id.btn_traditional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLanguage(2);
            }
        });
        //english
        findViewById(R.id.btn_en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLanguage(3);
            }
        });
    }

    public void setupagain() {
        Intent intent = new Intent("hs.act.welcome");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
