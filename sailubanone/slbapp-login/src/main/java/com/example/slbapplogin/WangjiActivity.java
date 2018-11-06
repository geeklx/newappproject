package com.example.slbapplogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.slbapplogin.utils.YanzhengUtil;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class WangjiActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvLeft;
    private TextView tvCenter;
    private EditText edt1;
    private EditText edt2;
    private Button btnHqyzm;
    private Button btnSure;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wangji;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText("忘记密码");
        tvLeft.setVisibility(View.VISIBLE);
        YanzhengUtil.showSoftInputFromWindow(this, edt1);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            onBackPressed();
        } else if (i == R.id.btn_hqyzm) {
            hqyzm();
        } else if (i == R.id.btn_sure) {
            yanzheng();

        }
    }

    private void onclick() {
        tvLeft.setOnClickListener(this);
        btnHqyzm.setOnClickListener(this);
        btnSure.setOnClickListener(this);
    }

    private void findview() {
        tvLeft = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnHqyzm = findViewById(R.id.btn_hqyzm);
        btnSure = findViewById(R.id.btn_sure);
    }

    @Override
    protected void onDestroy() {
        YanzhengUtil.timer_des();
        super.onDestroy();

    }


    private boolean panduan1(int ccc) {
        String aaa = edt1.getText().toString().trim();
        String bbb = edt2.getText().toString().trim();
        if (TextUtils.isEmpty(aaa)) {
            Toasty.normal(this, getResources().getString(R.string.yhzc_tip4)).show();
            return false;
        }
        if (ccc == -1) {
            return true;
        }
        if (TextUtils.isEmpty(bbb)) {
            Toasty.normal(this, getResources().getString(R.string.yhzc_tip41)).show();
            return false;
        }
        return true;
    }

    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * 获取验证码
     */
    private void hqyzm() {
        if (!panduan1(-1)) {
            return;
        }
        //接口调用成功后继续下一步
        YanzhengUtil.startTime(60 * 1000, btnHqyzm);//倒计时bufen


    }

    /**
     * 验证
     */
    private void yanzheng() {
        if (!panduan1(0)) {
            return;
        }
        //接口bufen
        Toasty.normal(this, "接口部分").show();
        startActivity(new Intent("hs.act.slbapp.ChongzhiActivity"));
        finish();
    }
}
