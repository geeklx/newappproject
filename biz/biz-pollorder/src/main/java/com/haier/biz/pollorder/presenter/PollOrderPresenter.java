package com.haier.biz.pollorder.presenter;

import com.haier.biz.pollorder.api.PollOrderApi;
import com.haier.biz.pollorder.bean.PollOrderBean;
import com.haier.biz.pollorder.view.PollOrderView;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libretrofit.RetrofitNetNew;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/11.
 * Description:
 */
public class PollOrderPresenter extends Presenter<PollOrderView> {
    private PollOrderBean pollOrderBean=null;
    public void pollOrder(String key) {
        RetrofitNetNew.build(PollOrderApi.class, getIdentifier()).pollOrder(key).enqueue(new Callback<PollOrderBean>() {
            @Override
            public void onResponse(Call<PollOrderBean> call, Response<PollOrderBean> response) {
                if(!hasView()){
                    return;
                }
                int code = response.body().getCode();
                if (code == 200) {
                    pollOrderBean = response.body();
                    getView().onSuccess(pollOrderBean.getDatas());
                } else if (code == 400) {
                    getView().onError("请求失败！");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<PollOrderBean> call, Throwable t) {
                if(!hasView()){
                    return;
                }
                getView().onError("请求失败！");
                call.cancel();
            }
        });
    }

}
