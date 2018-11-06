package com.haier.jiuzhidao.biz2_phone_pay_wx2.api;

import com.haier.cellarette.libvariants.NetConfig;
import com.haier.jiuzhidao.biz2_phone_pay_wx2.bean.WxBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetWx {

    // NetConfig.SERVER_ISERVICE +
    @GET("http://wxpay.wxutil.com/pub_v2/app/app_pay.php")
    Call<WxBean> getWx();

}
