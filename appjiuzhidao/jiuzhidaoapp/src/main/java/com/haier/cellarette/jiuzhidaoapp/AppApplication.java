package com.haier.cellarette.jiuzhidaoapp;

import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.shining.libglin.glin.interceptor.IResultInterceptor;
import com.example.shining.libglin.juhenet.JuheNet;
import com.example.shining.libglin.net.Net;
import com.haier.cellarette.jiuzhidaoapp.glinsetting.Appdemo1ResultInterceptor;
import com.haier.cellarette.libglide37.glide.GlideOptionsFactory;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

public class AppApplication extends MultiDexApplication {
    public static final String DIR_PROJECT = "/jiuzhidao/jiuzhidaoapp/";
    public static final String DIR_CACHE = DIR_PROJECT + "cache/"; // 网页缓存路径

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyLogUtil.on(true);
        GlideOptionsFactory.init(this, R.drawable.ic_def_loading);
        configHios();
        configNet(true, new Appdemo1ResultInterceptor());
        configJuheNet(true, new Appdemo1ResultInterceptor());
        configRetrofitNet();
        //初始化统计

    }

    private void configHios() {
//        HiosRegister.load();
        HiosHelper.config("ad.web.page", "web.page");
        // 接收部分
        // private int mAction; // default 0
        // private String mSkuId; // maybe null
        // private String mCategoryId;
        // mAction = getIntent().getIntExtra("act", 0);
        // mSkuId = getIntent().getStringExtra("sku_id");
        // mCategoryId = getIntent().getStringExtra("category_id");
    }

    protected void configNet(boolean debug, IResultInterceptor interceptor) {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;

        new Net.Builder()
                .baseUrl("")
                .debug(debug)
                .timeout(30 * 1000)
                .cacheDir(cacheDir)
                .cacheSize(1024 * 1024)
                .resultInterceptor(interceptor)
                .build();
    }

    protected void configJuheNet(boolean debug, IResultInterceptor interceptor) {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;

        new JuheNet.Builder()
                .baseUrl("")
                .debug(debug)
                .timeout(30 * 1000)
                .cacheDir(cacheDir)
                .cacheSize(1024 * 1024)
                .resultInterceptor(interceptor)
                .build();
    }

    protected void configRetrofitNet() {
        String cacheDir = Environment.getExternalStorageDirectory() + DIR_CACHE;
        // https://api-cn.faceplusplus.com/
//        RetrofitNet.config();
        RetrofitNetNew.config();
    }
}
