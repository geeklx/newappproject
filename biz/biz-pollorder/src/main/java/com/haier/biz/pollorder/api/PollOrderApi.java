package com.haier.biz.pollorder.api;


import com.haier.biz.pollorder.bean.PollOrderBean;
import com.haier.cellarette.libvariants.NetConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/11.
 * Description:
 */
public interface PollOrderApi {

    //轮询
    @FormUrlEncoded
    @POST(NetConfig.SERVER_ISERVICE + "act=pei&op=state_info")
    Call<PollOrderBean> pollOrder(@Field("key") String key);
}
