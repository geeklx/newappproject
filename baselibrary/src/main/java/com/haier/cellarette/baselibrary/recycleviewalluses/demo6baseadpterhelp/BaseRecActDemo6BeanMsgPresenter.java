package com.haier.cellarette.baselibrary.recycleviewalluses.demo6baseadpterhelp;

import android.view.View;

import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class BaseRecActDemo6BeanMsgPresenter {

    private static volatile BaseRecActDemo6BeanMsgPresenter instance;

    public static BaseRecActDemo6BeanMsgPresenter getInstance() {
        if (instance == null) {
            synchronized (BaseRecActDemo6BeanMsgPresenter.class) {
                if (instance == null) {
                    instance = new BaseRecActDemo6BeanMsgPresenter();
                }
            }
        }
        return instance;
    }

    public BaseRecActDemo6BeanMsgPresenter() {

    }

    public void onClickCar(View view, BaseRecActDemo6BeanMsg movie) {
        Toasty.normal(view.getContext(), movie.content).show();
    }

    public void onClickName(View view, BaseRecActDemo6BeanMsg movie) {
        Toasty.normal(view.getContext(), movie.name).show();
    }
}
