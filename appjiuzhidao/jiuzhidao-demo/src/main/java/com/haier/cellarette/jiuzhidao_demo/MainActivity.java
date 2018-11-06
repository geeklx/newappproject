//package com.haier.cellarette.jiuzhidao_demo;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//
//import com.haier.cellarette.baselibrary.smartbar.IBaseAction;
//import com.haier.cellarette.baselibrary.smartbar.SmartBar;
//import com.haier.cellarette.baselibrary.smartbar.SmartBarInject;
//import com.haier.cellarette.baselibrary.toasts.ToastUtil;
//import com.haier.cellarette.biz_demo1.bean.DemoNewModel;
//import com.haier.cellarette.biz_demo1.presenter.HaoyouPresenter2;
//import com.haier.cellarette.biz_demo1.view.IHaoyouView;
//import com.haier.cellarette.jiuzhidao_demo.musics.splash_login.SplshActivity;
//import com.haier.cellarette.libwebview.DemoWebviewMainActivity;
//
///**
// * Created by shining on 2017/12/1.
// */
//
//public class MainActivity extends AppCompatActivity implements IBaseAction, IHaoyouView {
//
//    private HaoyouPresenter2 presenter = new HaoyouPresenter2();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        SmartBarInject.inject(this).show(SmartBar.HOME | SmartBar.BACK);
//
//        presenter.onCreate(this);
//        presenter.ceshi_ref("", "");
//
//        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, DemoMainActivity.class));
//            }
//        });
//
//        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(MainActivity.this, DemoIndexMainActivity.class));
//                startActivity(new Intent(MainActivity.this, DemoWebviewMainActivity.class));
//            }
//        });
//
//        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, DemoRetrofitActivity.class));
//            }
//        });
//
//        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, SplshActivity.class));
//            }
//        });
//
////        startService(new Intent(this, playmusicservices.class));
//
//    }
//
//    @Override
//    public void onHomePressed() {
//        finish();
//    }
//
//    @Override
//    public Activity who() {
//        return this;
//    }
//
//    @Override
//    public void Success(DemoNewModel demoNewModel) {
//        String a = "";
//    }
//
//    @Override
//    public void Failed(String s) {
//    }
//
//    @Override
//    public void banben(String s) {
//        ToastUtil.showToastLong(s);
//
//    }
//
//    private long mCurrentMs = System.currentTimeMillis();
//
//    @Override
//    public String getIdentifier() {
//        return getClass().getName() + mCurrentMs;
//    }
//
//
//}
