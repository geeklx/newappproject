package com.haier.uplus.control.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haier.biz.wine_management.bean.WineManageBean;
import com.haier.biz.wine_management.presenter.WineManagementPresenter;
import com.haier.biz.wine_management.view.WineManagementView;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.uplus.control.R;
import com.haier.uplus.control.adapter.WineManageAdapter;

import java.util.List;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/21.
 * Description:酒柜酒品管理
 */
public class WineManageActivity extends BaseActivity implements WineManagementView,View.OnClickListener{

    private Context mContext;
    private RelativeLayout rl_root;
    private ImageView iv_back;
    private TextView tv_title;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRvWine;
    private EmptyView mEmptyView;
    private WineManageAdapter mAdapter;
    private WineManagementPresenter wineManagementPresenter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wine_management;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        mContext = this;
        wineManagementPresenter = new WineManagementPresenter();
        wineManagementPresenter.onCreate(WineManageActivity.this);

        initUI();
    }

    private void initUI() {
        rl_root = f(R.id.rl_root);
        iv_back = f(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);
        tv_title = f(R.id.tv_title);
        tv_title.setText("酒品管理");
        mSwipeRefreshLayout = f(R.id.swiprefresh);
        mRvWine = f(R.id.rv_wine);
        mEmptyView = f(R.id.ev_view);
        mEmptyView.bind(rl_root).setRetryListener(retryListener);
        retryListener.retry();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRvWine.setLayoutManager(gridLayoutManager);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_orange_light), getResources().getColor(android.R.color.holo_red_light));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        wineManagementPresenter.getWineList("D0BAE45D37EF");
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onSuccess(List<WineManageBean.DataBean> list) {
        mEmptyView.success();
        if (list.size()>0){
            mRvWine.setAdapter(new WineManageAdapter(mContext,list));
        }else {
            mEmptyView.nodata();
        }

    }

    @Override
    public void onError(String s) {
        Toasty.error(mContext,s).show();
        mEmptyView.nodata();
    }

    public EmptyView.RetryListener retryListener = new EmptyView.RetryListener(){

        @Override
        public void retry() {
            mEmptyView.loading();
            wineManagementPresenter.getWineList("D0BAE45D37EF");
        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.iv_back){
            finish();
        }
    }
}
