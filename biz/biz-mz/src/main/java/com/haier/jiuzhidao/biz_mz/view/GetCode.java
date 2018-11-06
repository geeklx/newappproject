package com.haier.jiuzhidao.biz_mz.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface GetCode extends IView {
    void onSuccess(String str);
    void onError(String str);

    void onSuccessLogin(String str);
    void onSuccessErroe(String str);
}
