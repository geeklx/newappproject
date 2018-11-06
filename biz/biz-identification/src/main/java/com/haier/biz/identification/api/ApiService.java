package com.haier.biz.identification.api;

import com.haier.biz.identification.bean.WineDetial;
import com.haier.biz.identification.bean.WineItemDetail;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/4/13.
 * Description:
 */

public interface ApiService {
    //查询酒的集合
    String URL_WINE_LIST = "http://openapi.9kacha.com/recevice_json.php";
    //查询酒的详情
    String URL_WINE_DETAIL = "http://openapi.9kacha.com/search_wineinfo.php";

//    @POST("http://openapi.9kacha.com/recevice_json.php")
//    Call<WineDetial> uploadFileWithRequestBody1(@Part("json") RequestBody jsonBody, @Part MultipartBody.Part file);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("/recevice_json.php")
//    Call<WineDetial> uploadFileWithRequestBody2(@Body WineDetialRequestBean body);

//    @POST("/recevice_json.php")
//    Call<WineDetial> uploadFileWithRequestBody3(@Query("jparams") WineDetialRequestBean.RequestInfoBean p, @Query("img_binary") byte[] str);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URL_WINE_DETAIL)
    Call<WineItemDetail> winedetailRequestBody(@Body RequestBody body);

    // 上传多个文件
    @Multipart
    @POST(URL_WINE_LIST)
    Call<WineDetial> getwineList(/*@Part("description") RequestBody description,*/
            @Part("jparams") RequestBody jparams,
            @Part MultipartBody.Part file);

}
