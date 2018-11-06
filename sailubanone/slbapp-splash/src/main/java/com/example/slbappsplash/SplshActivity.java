package com.example.slbappsplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.haier.cellarette.baselibrary.baseactivitys.MyBaseActivity;
import com.haier.cellarette.baselibrary.splash.AlphaView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class SplshActivity extends MyBaseActivity implements View.OnClickListener {
    /**
     * 加载图片ViewPager
     */
//    布局设置
    private Integer[] Layouts = {R.layout.splash_activity_lay1, R.layout.splash_activity_lay2, R.layout.splash_activity_lay3, R.layout.splash_activity_lay4};
    private Integer[] strings = {R.drawable.guid1, R.drawable.guid2, R.drawable.guid3, R.drawable.guid4};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_index;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash_index);
        AlphaView alphaview = findViewById(R.id.alphaview);
        alphaview.setPointGravity(Gravity.RIGHT);
        alphaview.setData(strings, Layouts);
        alphaview.setPoint(R.drawable.new_press_dian, R.drawable.new_normal_dian, 50, 50, 30, 40, 30, 1);
        alphaview.setSplashItemOnClick(this, R.layout.splash_activity_lay4, R.id.login, R.id.regist);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login) {
//            startActivity(new Intent("hs.act.slbapp.index"));
            Toasty.info(this, "注册").show();

            finish();
        } else if (i == R.id.regist) {
            startActivity(new Intent("hs.act.slbapp.LoginActivity"));

        }
    }
}