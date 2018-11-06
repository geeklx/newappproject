package com.haier.biz.identification.presenter;


import com.haier.biz.identification.api.ApiService;
import com.haier.biz.identification.bean.MD5;
import com.haier.biz.identification.bean.WineDetial;
import com.haier.biz.identification.bean.WineItemDetail;
import com.haier.biz.identification.view.WineDetialInfoView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/10.
 * Description:
 */

public class WineDetialInfoPresenter extends Presenter<WineDetialInfoView> {

    public void getWineDetialInfo(WineDetial.WineListBean wineListBean) {
        JSONObject p = new JSONObject();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try {
            p.put("command", "10001");
            p.put("uid", "1a7aba90-2c89-11e6-88b4-5254000f5d7a");
            p.put("openid", "10001");
            p.put("rtoken", MD5.getMD5Code(time + "96f710480e84c858c422841525e8f49a").toLowerCase());
            p.put("time", time);
            p.put("stype", "wine");
            p.put("wine_id", wineListBean.getWine_id());
            p.put("sign", wineListBean.getSign());
            p.put("language", "ch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), p.toString());
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), p.toString());
        RetrofitNetNew.build(ApiService.class, getIdentifier()).winedetailRequestBody(body).enqueue(new Callback<WineItemDetail>() {
            @Override
            public void onResponse(Call<WineItemDetail> call, Response<WineItemDetail> response) {
                if (getView() == null) {
                    return;
                }
                if (response.body() != null) {
                    getView().getDetialSuccess(response.body());
                } else {
                    getView().getDetialFailure(response.message());
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<WineItemDetail> call, Throwable t) {
                if (getView() == null) {
                    return;
                }
                getView().getDetialFailure(t.getMessage());
                call.cancel();
            }
        });
    }

}
