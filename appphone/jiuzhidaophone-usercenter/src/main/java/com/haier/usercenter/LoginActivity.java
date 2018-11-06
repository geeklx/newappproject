package com.haier.usercenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.usersdk.LoginUtil;

public class LoginActivity extends AppCompatActivity {

    private Button btn_cancel;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findview();
        onclick();
        donetwork();

    }

    private void donetwork() {

    }

    private void onclick() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginCanceled();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donetlogin();
            }
        });
    }

    private void onLoginSuccess() {
        setResult(LoginUtil.LOGIN_RESULT_OK);
        finish();
    }

    private void onLoginCanceled() {
        setResult(LoginUtil.LOGIN_RESULT_CANCELED);
        finish();
    }

    /**
     * 登录操作
     */
    private void donetlogin() {
        //step 请求服务器成功后清除sp中的数据
//        SpUtils.get(this).get("", "");
        onLoginSuccess();
    }

    private void findview() {
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);
    }

    private boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            Toasty.normal(BaseApp.get(),"请输入您的手机号码！").show();
            return false;
        }

        if (!TextUtils.isDigitsOnly(phone)) {
            Toasty.normal(BaseApp.get(),"手机号格式错误，仅支持纯数字！").show();
            return false;
        }

        if (phone.length() != 11) {
            Toasty.normal(BaseApp.get(),"手机号格式错误，应为11位纯数字！").show();
            return false;
        }

        return true;
    }
}
