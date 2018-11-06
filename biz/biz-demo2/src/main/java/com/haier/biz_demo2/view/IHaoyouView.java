package com.haier.biz_demo2.view;


import com.haier.biz_demo2.bean.DemoNewModel;
import com.haier.cellarette.libmvp.mvp.IView;

public interface IHaoyouView extends IView {
    void Success(DemoNewModel tokens);

    void Failed(String msg);

    void banben(String msg);


}
