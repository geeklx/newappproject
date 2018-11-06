package com.haier.biz_demo2.api;

import com.haier.biz_demo2.bean.DemoNewModel;
import com.haier.cellarette.libvariants.NetConfig;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RankingApi {
    @FormUrlEncoded
    @POST(NetConfig.SERVER_ISERVICE2 + "http://v.juhe.cn/toutiao/index")
    Call<DemoNewModel> get_request_post(@Field("type") String type, @Field("key") String key);
}
