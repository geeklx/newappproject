package com.haier.cellarette.jiuzhidao_demo.glinbufen.view;


import com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinnet.Demo1Model;
import com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp.GlinIView;

public interface Demo1View extends GlinIView {
    void onGetNewMyWalletShoppingCardListSuccess(Demo1Model data);

    void onGetNewMyWalletShoppingCardListEmpty(Demo1Model data);

    void onGetNewMyWalletShoppingCardListFailed(String msg);
}
