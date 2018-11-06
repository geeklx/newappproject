package com.haier.biz_demo2.presenter;


import com.haier.biz_demo2.api.RankingApi;
import com.haier.biz_demo2.bean.DemoNewModel;
import com.haier.biz_demo2.view.IHaoyouView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libvariants.NetConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HaoyouPresenter2 extends Presenter<IHaoyouView> {


    public void ceshi_ref(String type, String key) {
        RetrofitNetNew.build(RankingApi.class, getIdentifier()).get_request_post(type, key).enqueue(new Callback<DemoNewModel>() {
            @Override
            public void onResponse(Call<DemoNewModel> call, Response<DemoNewModel> response) {
                if (getView() == null) {
                    return;
                }
                getView().banben(NetConfig.SERVER_ISERVICE);
                DemoNewModel detectResult = response.body();
                if (detectResult != null) {
                    getView().Success(detectResult);
                } else {
                    getView().Failed(response.message());
                }
                call.cancel();

            }

            @Override
            public void onFailure(Call<DemoNewModel> call, Throwable t) {
                if (getView() == null) {
                    return;
                }
                getView().banben(NetConfig.SERVER_ISERVICE);
                getView().Failed(t.getMessage());

                call.cancel();
            }
        });
    }
}
