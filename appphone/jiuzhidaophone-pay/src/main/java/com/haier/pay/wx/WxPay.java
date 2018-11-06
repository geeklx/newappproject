package com.haier.pay.wx;

import android.content.Context;

import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.jiuzhidao.biz2_phone_pay_wx2.bean.WxBean;
import com.haier.jiuzhidao.biz2_phone_pay_wx2.presenter.MyWxPresenter;
import com.haier.pay.Pay;
import com.haier.pay.PayActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WxPay implements Pay<WxBean> {
    private IWXAPI api;
    private MyWxPresenter myWxPresenter = new MyWxPresenter();
    private Context mContext;

    public WxPay(Context context) {
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
        mContext = context;
    }

    @Override
    public void pay() {
        myWxPresenter.onCreate((PayActivity) mContext);
        myWxPresenter.getCode();
    }

    public void onDes() {
        myWxPresenter.onDestory();
    }

    @Override
    public void success(WxBean bean) {
        PayReq req = new PayReq();
        req.appId = bean.getAppid();
        req.partnerId = bean.getPartnerid();
        req.prepayId = bean.getPrepayid();
        req.nonceStr = bean.getNoncestr();
        req.timeStamp = String.valueOf(bean.getTimestamp());
        req.packageValue = "Sign=WXPay";
        req.sign = bean.getSign();
        req.extData = "app data"; // optional
        api.sendReq(req);
    }

    @Override
    public void error(String s) {
        Toasty.normal(BaseApp.get(), s).show();
    }
}
