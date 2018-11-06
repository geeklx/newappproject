package com.haier.jiuzhidao.biz_mz.presenter;

import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.jiuzhidao.biz_mz.api.GetCodeApi;
import com.haier.jiuzhidao.biz_mz.bean.GetCodeBean;
import com.haier.jiuzhidao.biz_mz.view.GetCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPresenter extends Presenter<GetCode> {
    public void getCode(String mob) {
        RetrofitNetNew.build(GetCodeApi.class, getIdentifier()).getCode(mob).enqueue(new Callback<GetCodeBean>() {
            @Override
            public void onResponse(Call<GetCodeBean> call, Response<GetCodeBean> response) {
                if(!hasView()){
                    return;
                }
                int code = response.body().getCode();
                if (code == 200) {
                    getView().onSuccess(response.body().getDatas().getMsg());
                } else if (code == 400) {
                    getView().onError(response.body().getDatas().getError());
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<GetCodeBean> call, Throwable t) {
                if(!hasView()){
                    return;
                }
                getView().onError("网络连接失败！");
                call.cancel();
            }
        });
    }

    public void login(String mob, String code) {
        RetrofitNetNew.build(GetCodeApi.class, getIdentifier()).Login(mob, code).enqueue(new Callback<GetCodeBean>() {
            @Override
            public void onResponse(Call<GetCodeBean> call, Response<GetCodeBean> response) {
                if(!hasView()){
                    return;
                }
                int code = response.body().getCode();
                if (code == 200) {
                    getView().onSuccessLogin(response.body().getDatas().getToken());
                } else if (code == 400) {
                    getView().onSuccessErroe(response.body().getDatas().getError());
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<GetCodeBean> call, Throwable t) {
                if(!hasView()){
                    return;
                }
                getView().onSuccessErroe("网络连接失败！");
                call.cancel();
            }
        });
    }
}
