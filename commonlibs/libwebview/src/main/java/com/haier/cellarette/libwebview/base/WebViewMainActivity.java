package com.haier.cellarette.libwebview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.haier.cellarette.libwebview.R;


public class WebViewMainActivity extends WebViewActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String adId = getIntent().getStringExtra("aid");
        if (TextUtils.isEmpty(adId)) {
            finish();
            return;
        }
        if (adId.equals("1")) {
            url = "http://liangxiao.blog.51cto.com/";
        } else {
            url = "https://www.baidu.com/";
        }
//        setup();//必须删除bufen
//        mPresenter = PresenterHelper.create(AdPresenter.class, this);
//        mPresenter.getAdUrlById(adId);

        loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
