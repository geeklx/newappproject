package com.haier.biz.identification.presenter;

import android.util.Log;

import com.haier.biz.identification.api.ApiService;
import com.haier.biz.identification.bean.MD5;
import com.haier.biz.identification.bean.WineDetial;
import com.haier.biz.identification.view.WineListInfoView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/4/16.
 * Description:
 */

public class WineListInfoPresenter extends Presenter<WineListInfoView> {


    private Call<WineDetial> wineDetialCall;

    public void getWineListInfo(File file) {
        JSONObject p = new JSONObject();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try {
            p.put("command", "10000");
            p.put("uid", "1a7aba90-2c89-11e6-88b4-5254000f5d7a");
            p.put("openid", "10001");
            p.put("rtoken", MD5.getMD5Code(time + "96f710480e84c858c422841525e8f49a").toLowerCase());
            p.put("time", time);
            p.put("stype", "wine");
            p.put("img_url", "0");
            p.put("response_type", "html");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img_binary", file.getName(), requestFile);
        // 添加描述
//        String descriptionString = "hello, 这是文件描述";
//        RequestBody rate1 = RequestBody.create(MediaType.parse("multipart/form-data"), p);

//        RequestBody pp = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), p.toString());
//        RequestBody pp = RequestBody.create(MediaType.parse("multipart/form-data"), p.toString());
//        RequestBody pp = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), p.toString());
        RequestBody pp = RequestBody.create(MediaType.parse("text/plain"), p.toString());
        wineDetialCall = RetrofitNetNew.build(ApiService.class, getIdentifier()).getwineList(pp, body);

        wineDetialCall.enqueue(new Callback<WineDetial>() {
            @Override
            public void onResponse(Call<WineDetial> call, Response<WineDetial> response) {
                if (getView() == null) {
                    return;
                }
                Log.i("onSuccess", response.toString());
                if (response != null && !"0".equals(response.body().getStatus())) {
                    getView().getListSuccess(response.body());
                } else {
                    getView().getListFailure(response.message());
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<WineDetial> call, Throwable t) {
                if (getView() == null) {
                    return;
                }
                getView().getListFailure(t.getMessage());
                call.cancel();
            }
        });
    }

    public void cancleWineListInfo() {
        if (wineDetialCall != null) {
            wineDetialCall.cancel();
        }
    }

}


