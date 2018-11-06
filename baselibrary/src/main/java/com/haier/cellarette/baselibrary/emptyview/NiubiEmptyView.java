package com.haier.cellarette.baselibrary.emptyview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;

public class NiubiEmptyView {

    private Context context;
    private View loadingView;
    private View noDataView;
    private View errorView;
    private TextView errorTextView_content;
    private TextView errorTextView_click;
    private BaseQuickAdapter mAdapter;


    public NiubiEmptyView bind(Context context, RecyclerView mRecyclerView, BaseQuickAdapter mAdapter) {
        this.context = context;
        this.mAdapter = mAdapter;
        loadingView = ((Activity) context).getLayoutInflater().inflate(R.layout.activity_recycleviewallsuses_demo8_viewloading, (ViewGroup) mRecyclerView.getParent(), false);
        noDataView = ((Activity) context).getLayoutInflater().inflate(R.layout.activity_recycleviewallsuses_demo8_viewnodata, (ViewGroup) mRecyclerView.getParent(), false);
        errorView = ((Activity) context).getLayoutInflater().inflate(R.layout.activity_recycleviewallsuses_demo8_viewerror, (ViewGroup) mRecyclerView.getParent(), false);
        errorTextView_content = errorView.findViewById(R.id.empty_errnet_notice);
        errorTextView_click = errorView.findViewById(R.id.empty_errnet_btn);

        return this;
    }

    public void loading() {
//        mAdapter.setEmptyView(R.layout.activity_recycleviewallsuses_demo8_viewloading, (ViewGroup) mRecyclerView.getParent());
        mAdapter.setEmptyView(loadingView);
    }

    public void nodata() {
        mAdapter.setEmptyView(noDataView);
    }

    public void errornet(String error_text) {
        errorTextView_content.setText(TextUtils.isEmpty(error_text) ? "断网了" : error_text);
        mAdapter.setEmptyView(errorView);

    }

    public void setRetry(final RetryListener mListener) {
        errorTextView_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.retry();
                }
            }
        });
    }

    private RetryListener mListener;

    public interface RetryListener {
        void retry();
    }

    public void setRetryListener(RetryListener li) {
        this.mListener = li;
    }

}
