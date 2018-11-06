package com.example.biz3slbappdemo1.view;

import com.example.biz3slbappdemo1.bean.Fanslbdemo1Bean;
import com.haier.cellarette.libmvp.mvp.IView;

public interface Slbdemo1View extends IView {

    void OnSuccess(Object bean);
    void OnFail(String msg);

}
