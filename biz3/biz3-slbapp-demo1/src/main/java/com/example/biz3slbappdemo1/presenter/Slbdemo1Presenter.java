package com.example.biz3slbappdemo1.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.biz3slbappdemo1.api.Slbdemo1Api;
import com.example.biz3slbappdemo1.bean.Fanslbdemo1Bean;
import com.example.biz3slbappdemo1.bean.Slbdemo1Bean;
import com.example.biz3slbappdemo1.view.Slbdemo1View;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libutils.utilslib.jiami.CommonUtil;

import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Slbdemo1Presenter extends Presenter<Slbdemo1View> {

    public static final String url_me = "hircloud/service/user/set";

    private int id = (new Random()).nextInt();
    private String jsonrpc = "2.0";
    private String date = System.currentTimeMillis() + "";
    private String user1 = "sairobo";


    public void getSlbdemo1Data(String user,Slbdemo1Bean params) {
        JSONObject requestData = new JSONObject();
        requestData.put("user", user);

        requestData.put("id", id);
        requestData.put("jsonrpc", jsonrpc);
        requestData.put("date", date);
        requestData.put("method", url_me);
        requestData.put("token", CommonUtil.getEncryptToken(id, url_me, date, ((JSONObject) JSON.toJSON(params)).toJSONString()));
        requestData.put("params", JSON.toJSON(params));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), requestData.toString());

        RetrofitNetNew.build(Slbdemo1Api.class, getIdentifier()).getdemo1(requestBody).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (!hasView()) {
                    return;
                }
                getView().OnSuccess(response.body());
                call.cancel();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                String string = t.toString();
                getView().OnFail(string);
                call.cancel();
            }
        });

    }
}
