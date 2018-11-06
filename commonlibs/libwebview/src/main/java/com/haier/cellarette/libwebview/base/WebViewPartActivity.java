package com.haier.cellarette.libwebview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.haier.cellarette.libwebview.R;


public class WebViewPartActivity extends WebViewActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_webview_part_layout);
        findview();
        onclickListener();
        setupWebView();

        url = "http://liangxiao.blog.51cto.com/";
        loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        set_destory();
    }
}
