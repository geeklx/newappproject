package com.haier.shopcommon.jeffery.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by JefferyLeng on 2018/6/27.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context,int layoutId,List<T> datas) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(mContext,mLayoutId,null);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        bind(holder, mDatas.get(position));
    }

    protected abstract void bind(CommonViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
