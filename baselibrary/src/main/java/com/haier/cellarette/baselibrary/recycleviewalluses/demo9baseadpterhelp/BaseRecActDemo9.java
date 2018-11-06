package com.haier.cellarette.baselibrary.recycleviewalluses.demo9baseadpterhelp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo9 extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private BaseRecActDemo9Adapter mAdapter;
    private View headView;
    private List<BaseRecActDemo9Bean> mList;
    private static final int PAGE_SIZE = 6;
    private int mNextRequestPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo9);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        mList = new ArrayList<>();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void onclick() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //业务逻辑bufen
                        refresh();
                    }
                }, 3000);
            }
        });
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setNewData(null);
                mAdapter.setLoadMoreView(new CustomLoadMoreView());
                mRecyclerView.setAdapter(mAdapter);
                Toasty.normal(BaseRecActDemo9.this, "change complete!").show();

                mSwipeRefreshLayout.setRefreshing(true);
                refresh();
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toasty.normal(BaseRecActDemo9.this, Integer.toString(position)).show();
            }
        });
    }

    private void findview() {
        mSwipeRefreshLayout = findViewById(R.id.swipeLayout);
        mRecyclerView = findViewById(R.id.rv_list);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_orange_light), getResources().getColor(android.R.color.holo_red_light));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new BaseRecActDemo9Adapter(R.layout.activity_recycleviewallsuses_demo9_item, null);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        headView = getLayoutInflater().inflate(R.layout.activity_recycleviewalluses_demo9_head_view, (ViewGroup) mRecyclerView.getParent(), false);
        ((TextView) headView.findViewById(R.id.tv)).setText("change load view");
        mAdapter.addHeaderView(headView);
//        mAdapter.setLoadMoreView(new CustomLoadMoreView());

        mRecyclerView.setAdapter(mAdapter);
    }

    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<BaseRecActDemo9Bean> data) {
                setData(true, data);
                mAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {
                Toasty.normal(BaseRecActDemo9.this, "网络连接失败!").show();
                mAdapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }).start();
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<BaseRecActDemo9Bean> data) {
                setData(false, data);
            }

            @Override
            public void fail(Exception e) {
                mAdapter.loadMoreFail();
                Toasty.normal(BaseRecActDemo9.this, "网络连接失败!").show();
            }
        }).start();
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toasty.normal(BaseRecActDemo9.this, "no more data").show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

}


// 伪造假请求

interface RequestCallBack {
    void success(List<BaseRecActDemo9Bean> data);

    void fail(Exception e);
}

class Request extends Thread {

    public static List<BaseRecActDemo9Bean> getSampleData(int lenth) {
        List<BaseRecActDemo9Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo9Bean status = new BaseRecActDemo9Bean();
            status.setUserName("赵丽颖" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    private static final int PAGE_SIZE = 6;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (mPage == 2 && mFirstError) {
            mFirstError = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.fail(new RuntimeException("fail"));
                }
            });
        } else {
            int size = PAGE_SIZE;
            if (mPage == 1) {
                if (mFirstPageNoMore) {
                    size = 1;
                }
                mFirstPageNoMore = !mFirstPageNoMore;
                if (!mFirstError) {
                    mFirstError = true;
                }
            } else if (mPage == 4) {
                size = 1;
            }

            final int dataSize = size;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.success(getSampleData(dataSize));
                }
            });
        }
    }
}




