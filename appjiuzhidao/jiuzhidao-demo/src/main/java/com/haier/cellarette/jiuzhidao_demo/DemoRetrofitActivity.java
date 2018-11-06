package com.haier.cellarette.jiuzhidao_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts.ToastUtil;
import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.bean.DemoNewDataModel;
import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.bean.DemoNewModel;
import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.presenter.Demo1RetrofitNewPresenter;
import com.haier.cellarette.jiuzhidao_demo.retrofitbufen.view.DemoRetrofitNewView;

import java.util.List;


public class DemoRetrofitActivity extends BaseActivity implements DemoRetrofitNewView {

    private TextView tv1;
    private Demo1RetrofitNewPresenter demo1RetrofitNewPresenter = new Demo1RetrofitNewPresenter();

    @Override
    protected void onDestroy() {
        demo1RetrofitNewPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        tv1 = findViewById(R.id.tv1);
        demo1RetrofitNewPresenter.onCreate(this);
        demo1RetrofitNewPresenter.get_hr_demo1("shehui", "03972d8ebd2a40194a80fa019b314fa3");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_re;
    }

    @Override
    public void Success(DemoNewModel result) {
        ToastUtil.showToastCenter("成功返回！");
        String c = "";
        List<DemoNewDataModel> aaaaa = result.getResult().getData();
        for (DemoNewDataModel b : aaaaa) {
            c += b.getTitle() + ",";
        }
        tv1.setText(c);
    }

    @Override
    public void Failed(String msg) {

    }
}
