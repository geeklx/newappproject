package com.haier.cellarette.jiuzhidao_demo.retrofitbufen.presenter;

import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.api.ApiDemo1RetrofitNew;
import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.bean.DemoNewModel;
import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.view.DemoRetrofitNewView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Demo1RetrofitNewPresenter extends Presenter<DemoRetrofitNewView> {

    public void get_hr_demo1(String type, String key) {
        RetrofitNetNew.build(ApiDemo1RetrofitNew.class, getIdentifier()).get_request_post(type, key).enqueue(new Callback<DemoNewModel>() {
            @Override
            public void onResponse(Call<DemoNewModel> call, Response<DemoNewModel> response) {
                if (getView() == null) {
                    return;
                }
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
                getView().Failed(t.getMessage());

                call.cancel();
            }
        });
    }
}
