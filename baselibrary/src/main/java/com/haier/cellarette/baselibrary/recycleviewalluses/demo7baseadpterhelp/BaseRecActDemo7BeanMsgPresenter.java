package com.haier.cellarette.baselibrary.recycleviewalluses.demo7baseadpterhelp;

import android.view.View;

import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class BaseRecActDemo7BeanMsgPresenter {

    private static volatile BaseRecActDemo7BeanMsgPresenter instance;

    public static BaseRecActDemo7BeanMsgPresenter getInstance() {
        if (instance == null) {
            synchronized (BaseRecActDemo7BeanMsgPresenter.class) {
                if (instance == null) {
                    instance = new BaseRecActDemo7BeanMsgPresenter();
                }
            }
        }
        return instance;
    }

    public BaseRecActDemo7BeanMsgPresenter() {

    }

    public void onClickCar(View view, BaseRecActDemo7BeanMsg movie) {
        Toasty.normal(view.getContext(), movie.content).show();
    }

    public void onClickName(View view, BaseRecActDemo7BeanMsg movie) {
        Toasty.normal(view.getContext(), movie.name).show();
    }
}
