package com.haier.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.usersdk.LoginUtil;


public class LoginMainActivity extends BaseActivity {

    private TextView tv1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_main;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        tv1= findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUtil.get().loginTowhere(LoginMainActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        //登录to
//                        ToastUtil.showToastCenter("可以跳转了~");
                        tv1.setText("更新UI~");
//                        startActivity(new Intent(LoginMainActivity.this, MainActivity2.class));
                    }
                });
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUtil.get().loginOutTowhere(LoginMainActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        //退出登录to
                        //登录to
//                        ToastUtil.showToastCenter("可以跳转了~");
                        tv1.setText("login");
//                        startActivity(new Intent(LoginMainActivity.this, MainActivity3.class));
                    }
                });
            }
        });
    }

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        
    }
}
