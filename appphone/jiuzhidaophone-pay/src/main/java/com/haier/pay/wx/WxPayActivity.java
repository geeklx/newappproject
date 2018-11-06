//package com.haier.pay.wx;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.Button;
//
//import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
//import com.haier.jiuzhidao.biz2_phone_pay_wx2.bean.WxBean;
//import com.haier.jiuzhidao.biz2_phone_pay_wx2.presenter.MyWxPresenter;
//import com.haier.jiuzhidao.biz2_phone_pay_wx2.view.GetWxView;
//import com.haier.pay.R;
//import com.tencent.mm.opensdk.modelpay.PayReq;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//public class WxPayActivity extends BaseActivity implements GetWxView {
//
//    private IWXAPI api;
//    private MyWxPresenter myWxPresenter = new MyWxPresenter();
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.pay;
//    }
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//        super.setup(savedInstanceState);
//        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
//        myWxPresenter.onCreate(this);
//        Button appayBtn = findViewById(R.id.btWx);
//        appayBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                myWxPresenter.getCode();
//            }
//        });
//    }
//
//
//    @Override
//    public void onWxSuccess(WxBean bean) {
//        PayReq req = new PayReq();
//        req.appId = bean.getAppid();
//        req.partnerId = bean.getPartnerid();
//        req.prepayId = bean.getPrepayid();
//        req.nonceStr = bean.getNoncestr();
//        req.timeStamp = String.valueOf(bean.getTimestamp());
//        req.packageValue = "Sign=WXPay";
//        req.sign = bean.getSign();
//        req.extData = "app data"; // optional
//        api.sendReq(req);
//    }
//
//
//    @Override
//    public void onWxError(String s) {
//
//    }
//}
