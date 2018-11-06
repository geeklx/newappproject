package com.haier.index.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.index.R;

public class FragmentContent5 extends BaseFragment implements View.OnClickListener {

    private String tablayoutId;
    private Context mContext;
    private TextView tvFeedBack;
    private TextView tvMsgCenter;
    private TextView tvSettings;
    private TextView tv_share;
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
    protected int getLayoutId() {
        return R.layout.activity_fragment5;
    }


    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        findView(rootView);
        initData();
    }

    public void findView(View rootView) {
        tvFeedBack = f(rootView, R.id.tv_feedback);
        tvMsgCenter = f(rootView, R.id.tv_msg_center);
        tvSettings = f(rootView, R.id.tv_settings);
        tv_share = f(rootView, R.id.tv_share);
        tv_share.setOnClickListener(this);
        tvFeedBack.setOnClickListener(this);
        tvMsgCenter.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        swipeRefreshLayout = rootView.findViewById(R.id.swip_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_orange_light), getResources().getColor(android.R.color.holo_red_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //业务逻辑bufen

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_feedback) {
            Toasty.normal(mContext, "意见反馈").show();
        } else if (viewId == R.id.tv_msg_center) {
            Toasty.normal(mContext, "消息中心").show();
        } else if (viewId == R.id.tv_settings) {
            startActivity(new Intent("hs.act.phone.SettingsActivity"));
        } else if (viewId == R.id.tv_share) {
            startActivity(new Intent("hs.act.phone.ShareActivity"));
        }
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {

        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
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
//        Toasty.normal(getActivity(), "下拉刷新啦").show();
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //业务逻辑bufen

                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
