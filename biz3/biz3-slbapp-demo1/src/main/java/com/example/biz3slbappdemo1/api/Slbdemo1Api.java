package com.example.biz3slbappdemo1.api;

import com.example.biz3slbappdemo1.bean.Fanslbdemo1Bean;
import com.example.biz3slbappdemo1.presenter.Slbdemo1Presenter;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Slbdemo1Api {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("http://47.98.245.152:9355/" + Slbdemo1Presenter.url_me)
    Call<Object> getdemo1(@Body RequestBody body);

}
