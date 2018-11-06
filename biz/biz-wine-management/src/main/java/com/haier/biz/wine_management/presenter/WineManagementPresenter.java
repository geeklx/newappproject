package com.haier.biz.wine_management.presenter;

import com.haier.biz.wine_management.api.WineManagementApi;
import com.haier.biz.wine_management.bean.WineManageBean;
import com.haier.biz.wine_management.view.WineManagementView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:
 */
public class WineManagementPresenter extends Presenter<WineManagementView> {
    private List<WineManageBean.DataBean> wineData=null;
    public void getWineList(String mac) {
        JSONObject jsonObject = new JSONObject();
        try {//{ "deviceId":"D0BAE45D37EF"}
            jsonObject.put("deviceId", mac);//mac地址
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitNetNew.build(WineManagementApi.class, getIdentifier()).pollOrder(body).enqueue(new Callback<WineManageBean>() {
            @Override
            public void onResponse(Call<WineManageBean> call, Response<WineManageBean> response) {
                if(!hasView()){
                    return;
                }
                if ("00000" .equals(response.body().getCode())) {
                    if (response.body().getData()!=null)
                    wineData = response.body().getData();
                    getView().onSuccess(wineData);
                } else{
                    getView().onError(response.body().getMsg());
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<WineManageBean> call, Throwable t) {
                if(!hasView()){
                    return;
                }
                getView().onError("请求失败！");
                call.cancel();
            }
        });
    }

}
