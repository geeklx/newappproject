package com.example.slbapplogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slbapplogin.utils.YanzhengUtil;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

public class ZhuceActivity extends BaseActivity {

    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tv_error;
    private TextView tvZh;
    private EditText edt1;
    private TextView tvMm;
    private EditText edt2;
    private ImageView ivEyes1;
    private TextView tvQrmm;
    private EditText edt3;
    private ImageView ivEyes2;
    private EditText edt11;
    private Button btnHqyzm;
    private Button btnSure;
    private CheckBox cb1;
    private TextView tvWebview1;
    private TextView tv_tel1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuce;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText("用户注册");
        tvLeft.setVisibility(View.VISIBLE);
        edt11.setHint(getResources().getString(R.string.yhzc_tip4));
        btnHqyzm.setText(getResources().getString(R.string.yhzc_tip5));
        btnSure.setEnabled(false);
        tv_tel1.setText(getResources().getString(R.string.yhzc_tip9));

    }

    private void onclick() {
        ivEyes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YanzhengUtil.set_mima_vis(edt2, ivEyes1);
                YanzhengUtil.set_mima_vis(edt3, ivEyes2);
            }
        });
        ivEyes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YanzhengUtil.set_mima_vis(edt2, ivEyes1);
                YanzhengUtil.set_mima_vis(edt3, ivEyes2);
            }
        });
        btnHqyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hqyzm();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuce();
            }
        });
        tvWebview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zcfwxy();
            }

        });

    }

    private void findview() {
        tvLeft = findViewById(R.id.tv_left);
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvCenter = findViewById(R.id.tv_center);
        tv_error = findViewById(R.id.tv_error);
        tvZh = findViewById(R.id.tv_zh);
        edt1 = findViewById(R.id.edt1);
        tvMm = findViewById(R.id.tv_mm);
        edt2 = findViewById(R.id.edt2);
        ivEyes1 = findViewById(R.id.iv_eyes1);
        tvQrmm = findViewById(R.id.tv_qrmm);
        edt3 = findViewById(R.id.edt3);
        ivEyes2 = findViewById(R.id.iv_eyes2);
        edt11 = findViewById(R.id.edt11);
        btnHqyzm = findViewById(R.id.btn_hqyzm);
        btnSure = findViewById(R.id.btn_sure);
        cb1 = findViewById(R.id.cb1);
        tvWebview1 = findViewById(R.id.tv_webview1);
        tv_tel1 = findViewById(R.id.tv_tel1);

    }

    private String phone_num = "";

    private boolean panduan1() {
        String aaa = edt1.getText().toString().trim();
        String bbb = edt2.getText().toString().trim();
        String ccc = edt3.getText().toString().trim();
        String ddd = edt11.getText().toString().trim();
        if (TextUtils.isEmpty(aaa)) {
            tv_error.setText("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(bbb)) {
            tv_error.setText("密码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(ccc)) {
            tv_error.setText("确认密码不能为空");
            return false;
        }
        if (!bbb.equals(ccc)) {
            tv_error.setText("两次输入密码不一致");
            return false;
        }
        String vercode = edt11.getHint().toString().trim();
        if (TextUtils.equals(vercode, getResources().getString(R.string.yhzc_tip41))) {
            if (TextUtils.isEmpty(ddd)) {
                tv_error.setText("验证码不能为空");
                return false;
            }
        } else {
            if (!YanzhengUtil.isPhone(ddd, tv_error)) {
                return false;
            }
            phone_num = ddd;
        }

        return true;

    }

    @Override
    protected void onDestroy() {
        YanzhengUtil.timer_des();
        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


    /**
     * 获取验证码
     */
    private void hqyzm() {
        if (!panduan1()) {
            return;
        }
        //接口通了后执行下一步
        tv_error.setText("");
        edt11.setText("");
        edt11.setHint(getResources().getString(R.string.yhzc_tip41));
        tv_tel1.setText(getString(R.string.yhzc_tip10, phone_num));

        btnSure.setEnabled(true);
        btnSure.setBackgroundResource(R.drawable.btncomm_pressed);
        YanzhengUtil.startTime(60 * 1000, btnHqyzm);

    }

    /**
     * 注册
     */
    private void zhuce() {
        if (!panduan1()) {
            return;
        }
        if (!cb1.isChecked()) {
            Toasty.normal(this, "请先同意注册服务协议").show();
            return;
        }
        tv_error.setText("");
        //接口bufen
        Toasty.normal(this, "接口部分").show();
    }

    /**
     * 注册服务协议
     */
    private void zcfwxy() {
        HiosHelper.resolveAd(this, this, "http://pc.jiuzhidao.com/portal/page/index/id/9.html");
    }

}
