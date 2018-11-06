package com.haier.shopcommon.jeffery.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JefferyLeng on 2018/6/27.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private Context mContext;
    private SparseArray<View> mViewSparseArray;

    public CommonViewHolder(Context context, View itemView) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mViewSparseArray = new SparseArray<>();
    }

    public static CommonViewHolder getViewHolder(Context context, int layoutId, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, viewGroup);
        return new CommonViewHolder(context, itemView);
    }

    public <T extends View> T getView(int viewId) {
        View view = mViewSparseArray.get(viewId);
        if (view == null) {
            view = mView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setOnClickListener(int viewId,
                                               View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
