package com.haier.jiuzhidao.biz_mz.presenter;

import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.jiuzhidao.biz_mz.api.GetCodeApi;
import com.haier.jiuzhidao.biz_mz.bean.GetCodeBean;
import com.haier.jiuzhidao.biz_mz.view.Lunxun;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPresenter_lunxun extends Presenter<Lunxun> {
    public void lunxun(String key) {
        RetrofitNetNew.build(GetCodeApi.class, getIdentifier()).lunxun(key).enqueue(new Callback<GetCodeBean>() {
            @Override
            public void onResponse(Call<GetCodeBean> call, Response<GetCodeBean> response) {
                if(!hasView()){
                    return;
                }
                int code = response.body().getCode();
                if (code == 200) {
                    getView().isLogin(response.body().getDatas().isHas_login());
                    getView().hasNewOrder(response.body().getDatas().isHas_neworder());
                    getView().webUrl(response.body().getDatas().getWeb_url());
                } else if (code == 400) {
                    getView().error("请求网络失败，请稍后重试！");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<GetCodeBean> call, Throwable t) {
                if(!hasView()){
                    return;
                }
                getView().error("请求网络失败，请稍后重试！");
                call.cancel();
            }
        });
    }

}
