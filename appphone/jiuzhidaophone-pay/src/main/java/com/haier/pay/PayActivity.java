package com.haier.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.jiuzhidao.biz2_phone_pay_wx2.bean.WxBean;
import com.haier.jiuzhidao.biz2_phone_pay_wx2.view.GetWxView;
import com.haier.pay.wx.WxPay;
import com.haier.pay.yinlian.YlPay;
import com.haier.pay.zfb.AliPay;

public class PayActivity extends BaseActivity implements View.OnClickListener, GetWxView {

    private AliPay aliPay;
    private WxPay wxPay;
    private YlPay ylPay;

    @Override
    protected int getLayoutId() {
        return R.layout.pay;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initView();

        aliPay = new AliPay(this);
        wxPay = new WxPay(this);
        ylPay = new YlPay(this);
    }

    private void initView() {
        Button btWx = findViewById(R.id.btWx);
        Button btAlipay = findViewById(R.id.btAlipay);
        Button btYl = findViewById(R.id.btYl);
        btWx.setOnClickListener(this);
        btAlipay.setOnClickListener(this);
        btYl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btWx) {
            wxPay.pay();
//            startActivity(new Intent(PayActivity.this, demoact.class));
        } else if (i == R.id.btAlipay) {
            aliPay.pay();
        } else if (i == R.id.btYl) {
            ylPay.pay();
        }
    }

    @Override
    protected void onDestroy() {
        wxPay.onDes();
        super.onDestroy();
    }

    //    wx start-----------------------------------------------


    @Override
    public void onWxSuccess(WxBean wxBean) {
        wxPay.success(wxBean);
    }

    @Override
    public void onWxError(String s) {
        wxPay.error(s);
    }

    // wx end-----------------------------------------------


    //yl 回调
    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        ylPay.onActResult(requestCode, resultCode, data);
    }
}
