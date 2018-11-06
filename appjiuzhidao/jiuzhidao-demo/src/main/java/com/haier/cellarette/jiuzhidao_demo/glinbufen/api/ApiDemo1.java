package com.haier.cellarette.jiuzhidao_demo.glinbufen.api;


import com.example.shining.libglin.glin.annotation.Arg;
import com.example.shining.libglin.glin.annotation.JSON;
import com.example.shining.libglin.glin.annotation.POST;
import com.example.shining.libglin.glin.call.Call;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinjuhe.DemoJuheFileModel;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinjuhe.DemoJuheModel;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinnet.Demo1Model;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public interface ApiDemo1 {

    //新版我的钱包购物卡接口bufen
    @JSON("http://58.87.72.48:8888/v1/userAccount/user.account.mycard")
    Call<Demo1Model> mywallet_shoppingcard_list(String json);

    @POST("http://v.juhe.cn/toutiao/index")
    Call<DemoJuheModel> getList(@Arg("type") String type, @Arg("key") String key);

    @POST("http://japi.juhe.cn/voice_words/getWords")
    Call<DemoJuheFileModel> getfile(@Arg("file") File file, @Arg("rate") String rate, @Arg("pname") String pname, @Arg("device_id") String device_id, @Arg("key") String key);


    // 上传多个文件
    @Multipart
    @retrofit2.http.POST("http://japi.juhe.cn/voice_words/getWords")
    retrofit2.Call<DemoJuheFileModel> uploadFile(/*@Part("description") RequestBody description,*/
            @Part("rate") RequestBody rate,
            @Part("pname") RequestBody pname,
            @Part("device_id") RequestBody device_id,
            @Part("key") RequestBody key,
            @Part MultipartBody.Part file);
}
