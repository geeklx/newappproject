package com.haier.cellarette.baselibrary.scrollview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class ScrollViewAct extends AppCompatActivity {

    private LinearLayout ll1;
    private TextView tv1;
    private TextView tv2;
    private ScrollBottomScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollviews);
        ll1 = findViewById(R.id.ll11);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.GONE);
        mScrollView = findViewById(R.id.scrollView);
        mScrollView.registerOnScrollViewScrollToBottom(new ScrollBottomScrollView.OnScrollBottomListener() {
            @Override
            public void srollToBottom() {
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
            }

            @Override
            public void srollToTop() {
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
            }

            @Override
            public void srollToOther() {
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
            }
        });
    }

}
