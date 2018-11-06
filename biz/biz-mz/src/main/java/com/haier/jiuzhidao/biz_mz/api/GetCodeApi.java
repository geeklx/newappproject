package com.haier.jiuzhidao.biz_mz.api;

import com.haier.cellarette.libvariants.NetConfig;
import com.haier.jiuzhidao.biz_mz.bean.GetCodeBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetCodeApi {

    //获取验证码
    @FormUrlEncoded
    @POST(NetConfig.SERVER_ISERVICE + "act=login&op=get_pei_code")
    Call<GetCodeBean> getCode(@Field("mobile") String mobile);

    //登录
    @FormUrlEncoded
    @POST(NetConfig.SERVER_ISERVICE + "act=login&op=pei_login")
    Call<GetCodeBean> Login(@Field("mobile") String mobile, @Field("code") String code);


    //轮询
    @FormUrlEncoded
    @POST(NetConfig.SERVER_ISERVICE + "act=pei&op=state_info")
    Call<GetCodeBean> lunxun(@Field("key") String key);

}
