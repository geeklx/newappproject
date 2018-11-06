package com.haier.shopcommon.jeffery.shopcate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JefferyLeng on 2018/6/25.
 */
public abstract class BaseShopCateAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mCategoryBeans;

    public BaseShopCateAdapter(Context context, List<T> categoryBeanArrayList) {
        this.mContext = context;
        mCategoryBeans = categoryBeanArrayList;
    }


    @Override
    public int getCount() {
        return mCategoryBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mCategoryBeans.size() > 0 ? mCategoryBeans.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addData(ArrayList<T> categoryBeanArrayList) {
        mCategoryBeans = categoryBeanArrayList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return fillView(i,view,viewGroup);
    }

    protected abstract View fillView(int position,View view,ViewGroup viewGroup);
}
