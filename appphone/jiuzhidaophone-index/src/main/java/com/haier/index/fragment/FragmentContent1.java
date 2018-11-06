package com.haier.index.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.index.R;

public class FragmentContent1 extends BaseFragment implements View.OnClickListener {

    public static final String UA = "Mozilla/5.0 (Linux; Android 5.1; sudy6580_we_l Build/C320) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Safari/537.36";
    private String tablayoutId;
    private Context mContext;

    private View mBackImageView;
    private View mBackView;
    private View mCloseView;
    private TextView mTitleTextView;
    private ProgressBar mProgressBar;

    protected WebView mWebView;

    private String mUrl;
    private LinearLayout mTop;
    protected ViewGroup parent;
    private ProgressBar progress;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        findview(rootView);
        onclickListener();
        setupWebView();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
//        mUrl = getActivity().getIntent().getStringExtra("url");
        mUrl = tablayoutId;
        loadUrl(mUrl);
//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
    }


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clear_history();
                setupWebView();
                loadUrl(mUrl);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
//        Toasty.normal(getActivity(), "下拉刷新啦").show();
    }

    private void findview(View rootView) {
        mTop = rootView.findViewById(R.id.rlTop);
        progress = rootView.findViewById(R.id.progress);
        mBackImageView = rootView.findViewById(R.id.ic_back);
        mBackView = rootView.findViewById(R.id.back);
        mCloseView = rootView.findViewById(R.id.close);
        mTitleTextView = rootView.findViewById(R.id.title);
        mProgressBar = rootView.findViewById(R.id.progress);
        parent = rootView.findViewById(R.id.container_webview);
        swipeRefreshLayout = rootView.findViewById(R.id.swip_refresh);

    }


    private void onclickListener() {
        mBackImageView.setOnClickListener(this);
        mBackView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_orange_light), getResources().getColor(android.R.color.holo_red_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clear_history();
                        setupWebView();
                        loadUrl(mUrl);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @SuppressLint({"JavascriptInterface", "NewApi"})
    private void setupWebView() {

        mWebView = new WebView(BaseApp.get());
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parent.addView(mWebView);

        WebSettings settings = mWebView.getSettings();//拿到webbiew的settings
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
        settings.setUseWideViewPort(true);//让图片更适合窗口
        settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setUserAgentString(UA);
        //使WebView支持js
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);

        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.addJavascriptInterface(this, "jiugui");//js对应tag

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return HiosHelper.shouldOverrideUrl(getActivity(), url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 如果接受到ssl错误， 接受证书， 继续执行
                Log.d("geek", error.toString());
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = view.getTitle();
                if (title != null) {
                    mTitleTextView.setText(title);
                }
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitleTextView.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        });

        mWebView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });

    }

    private void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void onStart() {
        super.onStart();
        mWebView.loadUrl("javascript:onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.loadUrl("javascript:onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebView.loadUrl("javascript:onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        mWebView.loadUrl("javascript:onStop()");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_back || id == R.id.back) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return;
            }
            return;
        }

        if (id == R.id.close) {
//            finish();
        }
    }

    @Override
    public void onDestroy() {
//        FixInputMethodBug.fixFocusedViewLeak(getApplication());
        clear_history();
        super.onDestroy();
    }

    private void clear_history() {
        mWebView.loadUrl("javascript:onDestroy()");

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
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

}
