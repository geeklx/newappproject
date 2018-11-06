package com.haier.cellarette.jiuzhidao_demo.musics.splash_login;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.haier.biz.pollorder.bean.PollOrderBean;
import com.haier.biz.pollorder.presenter.PollOrderPresenter;
import com.haier.biz.pollorder.view.PollOrderView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.updateapk.UpdateManager;
import com.haier.cellarette.jiuzhidao_demo.R;
import com.haier.cellarette.jiuzhidao_demo.musics.OpenMusic;
import com.haier.cellarette.jiuzhidao_demo.musics.splash_login.utils.TimerUtil;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/8.
 * Description:
 */
public class PollOrderActivity extends MyBaseActivity implements PollOrderView {

    private Context mContext;
    private ViewGroup parent;
    private WebView mWebView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private TimerUtil mTimerUtil;
    private String mToken = "";
    private String mWebUrl = "";
    private Boolean isHasLogin = false;
    private Boolean isHasNewOrder = false;
    private Boolean isFirstLoad = true;
    private PollOrderPresenter pollOrderPresenter = new PollOrderPresenter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_poll_order;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        versionupdate();
        mContext = PollOrderActivity.this;
        mToken = (String) SpUtils.getInstance(this).get("key_token", "");
        swipeRefreshLayout = findViewById(R.id.swip_refresh);

        pollOrderPresenter.onCreate(PollOrderActivity.this);

        setting();

        mTimerUtil = new TimerUtil(this, 5);
        mTimerUtil.startTimer(new TimerUtil.OnTimerSchedule() {
            @Override
            public void onScheduleAtFixedRate() {
                Log.e("mzzzzzzzzzzzzz", mToken);
                if (mToken != null && !mToken.equals("")) {
                    pollOrderPresenter.pollOrder(mToken);
                } else {
                    startActivity(new Intent(PollOrderActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_orange_light), getResources().getColor(android.R.color.holo_red_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mWebUrl != null && !mWebUrl.equals(""))
                            clear_history();
                        setting();
                        mWebView.loadUrl(mWebUrl);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        //设置子视图是否允许滚动到顶部
        swipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(@NonNull SwipeRefreshLayout parent, @Nullable View child) {
                return mWebView.getScrollY() > 0;
            }
        });

    }

    private void setting() {
        mWebView = new WebView(mContext);
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parent = findViewById(com.haier.cellarette.libwebview.R.id.container);
        parent.addView(mWebView);

        WebSettings settings = mWebView.getSettings();//拿到webbiew的settings
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
        settings.setUseWideViewPort(true);//让图片更适合窗口
        settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //使WebView支持js
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (url.startsWith("tel:")) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

//            /**
//             * 监听当WebView插入历史记录时，清楚之前的所有浏览记录
//             * @param view
//             * @param url
//             * @param isReload
//             */
//            @Override
//            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
//                super.doUpdateVisitedHistory(view, url, isReload);
//                deleteDatabase("webview.db");
//                deleteDatabase("webviewCache.db");
//                mWebView.clearCache(true);
//                mWebView.clearFormData();
//                mWebView.clearHistory();
//                getCacheDir().delete();
//            }
        });
    }

    private void clear_history() {
//        mWebView.loadUrl("javascript:onDestroy()");

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
//            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onSuccess(PollOrderBean.DatasBean result) {
        if (result != null) {
            Toasty.info(PollOrderActivity.this, "AJFKASJFD");
            isHasLogin = result.isHas_login();
            isHasNewOrder = result.isHas_neworder();
            mWebUrl = result.getWeb_url();

            if (isHasLogin) {
                if (isFirstLoad && mWebUrl.equals(result.getWeb_url())) {
                    mWebView.loadUrl(mWebUrl);
                    isFirstLoad = false;
                }
                if (isHasNewOrder)
                    showNotification(getApplicationContext(), 1, "您有新的酒知道订单", "查看订单详情");
            } else {
                startActivity(new Intent(PollOrderActivity.this, LoginActivity.class));
                this.finish();
            }
        }
    }

    @Override
    public void onError(String string) {
        Toasty.error(this, string).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showNotification(Context context, int id, String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "您有新的酒知道订单。");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
        Intent resultIntent = new Intent(context, PollOrderActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
        new OpenMusic(this);
    }


    private void versionupdate() {
        UpdateManager manager = new UpdateManager(this,
                PollOrderActivity.this);
        manager.checkUpdate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimerUtil.stopTimer();
    }

}
