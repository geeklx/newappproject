package com.haier.cellarette.jiuzhidao_demo.retrofitbufen.view;

import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.bean.DemoNewModel;
import com.haier.cellarette.libmvp.mvp.IView;

public interface DemoRetrofitNewView extends IView {
    void Success(DemoNewModel tokens);

    void Failed(String msg);
}
