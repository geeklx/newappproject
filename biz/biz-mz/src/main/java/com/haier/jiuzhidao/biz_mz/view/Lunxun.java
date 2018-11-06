package com.haier.jiuzhidao.biz_mz.view;

import com.haier.cellarette.libmvp.mvp.IView;

public interface Lunxun extends IView {
    void isLogin(boolean is);
    void webUrl(String web_url);
    void hasNewOrder(boolean is);
    void error(String string);
}


