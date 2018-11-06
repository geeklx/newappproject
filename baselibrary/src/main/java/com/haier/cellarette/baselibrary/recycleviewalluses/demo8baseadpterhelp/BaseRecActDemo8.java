package com.haier.cellarette.baselibrary.recycleviewalluses.demo8baseadpterhelp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo8 extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private BaseRecActDemo8Adapter mAdapter;
    private List<BaseRecActDemo8Bean> mList;

    public static List<BaseRecActDemo8Bean> getSampleData(int lenth) {
        List<BaseRecActDemo8Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo8Bean status = new BaseRecActDemo8Bean();
            status.setUserName("赵丽颖" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    private FloatingActionButton mBtn;
    private View noDataView;
    private TextView noDataTextView;
    private View errorView;
    private TextView errorTextView_content;
    private TextView errorTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo8);
        findview();
        onclick();
        donetwork();


    }

    private void donetwork() {
        mList = new ArrayList<>();
        mList = getSampleData(0);
        mAdapter = new BaseRecActDemo8Adapter(R.layout.activity_recycleviewallsuses_demo8_item, mList);
        mRecycleView.setAdapter(mAdapter);
        refresh();
    }

    private void onclick() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mError = true;
                mNoData = true;
                mAdapter.setNewData(null);
                refresh();
            }
        });
        noDataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无数据重试
                refresh();
            }
        });
        errorTextView_content.setText("网络连接失败，请检查网络后点击重试!");
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未联网重试
                refresh();
            }
        });

    }

    private void refresh() {
        mAdapter.setEmptyView(R.layout.activity_recycleviewallsuses_demo8_viewloading, (ViewGroup) mRecycleView.getParent());
        //测试用
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mError) {
                    mAdapter.setEmptyView(errorView);
                    mError = false;
                } else {
                    if (mNoData) {
                        mAdapter.setEmptyView(noDataView);
                        mNoData = false;
                    } else {
                        mList = getSampleData(10);
                        mAdapter.setNewData(mList);
                    }
                }
            }
        }, 3000);
    }

    private boolean mError = true;
    private boolean mNoData = true;

    private void findview() {
        mBtn = findViewById(R.id.btn_resets);
        mRecycleView = findViewById(R.id.rvlist);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        noDataView = getLayoutInflater().inflate(R.layout.activity_recycleviewallsuses_demo8_viewnodata, (ViewGroup) mRecycleView.getParent(), false);
        noDataTextView = noDataView.findViewById(R.id.empty_nodata_notice);
        errorView = getLayoutInflater().inflate(R.layout.activity_recycleviewallsuses_demo8_viewerror, (ViewGroup) mRecycleView.getParent(), false);
        errorTextView_content = errorView.findViewById(R.id.empty_errnet_notice);
        errorTextView = errorView.findViewById(R.id.empty_errnet_btn);


    }
}
