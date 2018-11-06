package com.haier.cellarette.jiuzhidao_demo.glinbufen.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.net.Net;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.api.ApiDemo1;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinnet.Demo1Model;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp.GlinPresenter;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp.ParamsUtils;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.view.Demo1View;


public class Demo1GlinPresenter extends GlinPresenter<Demo1View> {

    /**
     * 获取我的钱包购物卡列表数据bufen
     */
    public void getDemo1Presenter(String user_id) {
        JSONObject p = new JSONObject();
        p.put("user_id", user_id);//DataProvider.getUser_id()
        Net.build(ApiDemo1.class, getIdentifier()).mywallet_shoppingcard_list(ParamsUtils.just(p)).enqueue(new Callback<Demo1Model>() {
            @Override
            public void onResponse(Result<Demo1Model> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetNewMyWalletShoppingCardListFailed(result.getMessage());
                    return;
                }
                Demo1Model data = result.getResult();
                if (data == null || data.getUserShopCardList() == null || data.getUserShopCardList().size() == 0) {
                    //暂无数据bufen
                    getView().onGetNewMyWalletShoppingCardListEmpty(data);
                    return;
                }
                //请求成功bufen
                getView().onGetNewMyWalletShoppingCardListSuccess(data);

            }
        });
    }
}
