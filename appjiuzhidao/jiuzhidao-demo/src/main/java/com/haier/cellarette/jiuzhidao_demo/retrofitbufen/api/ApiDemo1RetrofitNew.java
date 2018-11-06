package com.haier.cellarette.jiuzhidao_demo.retrofitbufen.api;

import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.bean.DemoNewModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface ApiDemo1RetrofitNew {

    @GET("http://v.juhe.cn/toutiao/index")
    Call<DemoNewModel> get_request_get(@Field("type") String type, @Field("key") String key);

    @FormUrlEncoded
    @POST("http://v.juhe.cn/toutiao/index")
    Call<DemoNewModel> get_request_post(@Field("type") String type, @Field("key") String key);

}
