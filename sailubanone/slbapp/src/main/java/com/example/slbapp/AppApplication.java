package com.example.slbapp;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.bolex.autoEx.AutoEx;
import com.example.shining.libglin.glin.interceptor.IResultInterceptor;
import com.example.shining.libglin.juhenet.JuheNet;
import com.example.shining.libglin.net.Net;
import com.example.slbapp.interceptor.Appdemo1ResultInterceptor;
import com.haier.cellarette.baselibrary.changelanguage.LocalManageUtil;
import com.haier.cellarette.libretrofit.RetrofitNetNew;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jpush.android.api.JPushInterface;

public class AppApplication extends MultiDexApplication {
    public static final String DIR_PROJECT = "/jiuzhidaophone/app/";
    public static final String DIR_CACHE = DIR_PROJECT + "cache/"; // 网页缓存路径
    public static final String IMG_CACHE = DIR_PROJECT + "image/"; // image缓存路径
    public static final String VIDEO_CACHE = DIR_PROJECT + "video/"; // video缓存路径
    public static final String MUSIC_CACHE = DIR_PROJECT + "music/"; // music缓存路径

    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LocalManageUtil.setApplicationLanguage(this);
        MyLogUtil.on(true);
//        GlideOptionsFactory.init(this, R.drawable.ic_def_loading);
        configHios();
        configNet(true, new Appdemo1ResultInterceptor());
        configJuheNet(true, new Appdemo1ResultInterceptor());
        configRetrofitNet();
        Utils.init(this);// com.blankj:utilcode:1.17.3

        // AutoEx应用崩溃自动匹配Stack Overflow的解答
        /*┌—————————————————————AutoEx——————————————————————
        ├ 错误类型:android.content.res.Resources$NotFoundException: Resource ID #0x7f0b0056 type #0x12 is not valid。↑详细异常请往上滚动查看↑
        ├ 推荐参考Stack Overflow上4条同类问题。↓点击下方连接查看↓
        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
        ├ 标题:Android Resources$NotFoundException: Resource ID #0x7f030027
        ├ 链接:https://stackoverflow.com/questions/21269502/android-resourcesnotfoundexception-resource-id-0x7f030027
        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
        ├ 标题:android.content.res.Resources$NotFoundException: Resource ID #0x7f07007e
        ├ 链接:https://stackoverflow.com/questions/48161713/android-content-res-resourcesnotfoundexception-resource-id-0x7f07007e
        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
        ├ 标题:App crashes when adding an ImageView?
        ├ 链接:https://stackoverflow.com/questions/47600747/app-crashes-when-adding-an-imageview
        ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
        ├ 标题:XML Android app will not load on phone
        ├ 链接:https://stackoverflow.com/questions/48310838/xml-android-app-will-not-load-on-phone
        └—————————————————————AutoEx——————————————————————*/
        AutoEx.apply();

        //初始化极光分享
        configShare();
        //初始化极光统计
        configTongji();
        //初始化极光推送
        configTuisong();
    }

    private void configTuisong() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    private void configShare() {
        JShareInterface.setDebugMode(true);
        PlatformConfig platformConfig = new PlatformConfig()
                .setWechat("wxa3fa50c49fcd271c", "18ba7ba86f2c098bbe67da61f8d7b278")
                .setQQ("1106011004", "YIbPvONmBQBZUGaN")
                .setSinaWeibo("374535501", "baccd12c166f1df96736b51ffbf600a2", "https://www.jiguang.cn")
                .setFacebook("1847959632183996", "JShareDemo")
                .setTwitter("fCm4SUcgYI1wUACGxB2erX5pL", "NAhzwYCgm15FBILWqXYDKxpryiuDlEQWZ5YERnO1D89VBtZO6q")
                .setJchatPro("1847959632183996");
        JShareInterface.init(this, platformConfig);
    }

    private void configTongji() {
        // 设置开启日志,发布时请关闭日志
        JAnalyticsInterface.setDebugMode(true);
        JAnalyticsInterface.init(this);

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
