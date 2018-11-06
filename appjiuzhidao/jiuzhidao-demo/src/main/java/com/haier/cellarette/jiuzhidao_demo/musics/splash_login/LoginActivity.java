package com.haier.cellarette.jiuzhidao_demo.musics.splash_login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.toasts.ToastUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.jiuzhidao_demo.R;
import com.haier.cellarette.jiuzhidao_demo.musics.splash_login.utils.VerificationUtils;
import com.haier.cellarette.libutils.utilslib.app.MobileUtils;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.jiuzhidao.biz_mz.presenter.MyPresenter;
import com.haier.jiuzhidao.biz_mz.view.GetCode;

public class LoginActivity extends MyBaseActivity implements GetCode {

    private TextInputLayout input_code;
    private TextInputLayout input_number;
    private Button btn_login;
    private TextView btn_code;
    private MyPresenter myPresenter = new MyPresenter();

    private int milles = 60;
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    milles--;
                    btn_code.setText("" + milles + "秒后重新发送");
                    if (milles > 0) {
                        sendCode();
                    } else {
                        initSendCode();
                    }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        myPresenter.onCreate(this);
        FindID();
        onClick();
    }

    private void onClick() {
        //登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = input_code.getEditText().getText().toString();
                String number = input_number.getEditText().getText().toString();

                input_code.setErrorEnabled(false);
                input_number.setErrorEnabled(false);

                //验证用户名和密码
                if (VerificationUtils.validateAccount(number, input_number) && VerificationUtils.validateCode(code, input_code)) {
                    ShowLoadingUtil.showLoading(LoginActivity.this, "登陆中...", null);
                    btn_login.setClickable(false);
                    myPresenter.login(number, code);
                } else {
                    btn_login.setClickable(true);
                }
            }
        });

        //验证码
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MobileUtils.isNetworkConnected(LoginActivity.this)) {
                    Toasty.error(LoginActivity.this, "网络连接失败!").show();
                    return;
                }
                String number = input_number.getEditText().getText().toString();
                boolean b = VerificationUtils.validateAccount(number, input_number);
                if (b) {
                    btn_code.setClickable(false);
                    sendCode();
                    myPresenter.getCode(number);
                }
            }
        });
    }


    private void FindID() {
        input_code = findViewById(R.id.input_code);
        input_number = findViewById(R.id.input_number);
        btn_login = findViewById(R.id.btn_login);
        btn_code = findViewById(R.id.btn_code);
    }

    private void initSendCode() {
        btn_code.setClickable(true);
        milles = 60;
        btn_code.setText("获取验证码");
    }

    private void sendCode() {
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }


    @Override
    public void onSuccess(String str) {//验证码
        ToastUtil.showToastShort(str);
    }

    @Override
    public void onError(String str) {//验证码失败
        Toasty.error(this, str).show();
        handler.removeMessages(1);
        initSendCode();
    }

    @Override
    public void onSuccessLogin(String str) {//登录
        SpUtils.getInstance(this).put("key_token", str);
        Toasty.success(this, "登录成功").show();
        startActivity(new Intent(LoginActivity.this, PollOrderActivity.class));
        finish();
    }

    @Override
    public void onSuccessErroe(String str) {//登录失败
        ShowLoadingUtil.dismiss();
        btn_login.setClickable(true);
        Toasty.error(this, str).show();
    }

    @Override
    protected void onDestroy() {
        ShowLoadingUtil.dismiss();
        ShowLoadingUtil.onDestory();
        super.onDestroy();
        myPresenter.onDestory();
    }
}