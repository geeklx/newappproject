package com.haier.cellarette.baselibrary.expandableview.eximageview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.expandableview.eximageview.bean.ImageViewChildBean;
import com.haier.cellarette.baselibrary.expandableview.eximageview.bean.ImageViewGroupBean;
import com.haier.cellarette.baselibrary.expandableview.eximageview.viewholder.ImageViewHolder;

import java.util.List;

import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;

public class ImageViewAdapter extends BaseRecyclerViewAdapter<ImageViewGroupBean, ImageViewChildBean, ImageViewHolder> {

    private Context ctx;
    private LayoutInflater mInflater;
    private List datas;

    public ImageViewAdapter(Context ctx, List<RecyclerViewData> datas) {
        super(ctx, datas);
        mInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        this.datas = datas;
    }

    /**
     * true 全部可展开
     * fasle  同一时间只能展开一个
     */
    @Override
    public boolean canExpandAll() {
        return true;
    }

    @Override
    public View getGroupView(ViewGroup parent) {
        return mInflater.inflate(R.layout.activity_expandableact_imageview_group, parent, false);
    }

    @Override
    public View getChildView(ViewGroup parent) {
        return mInflater.inflate(R.layout.activity_expandableact_imageview_child, parent, false);
    }

    @Override
    public ImageViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new ImageViewHolder(ctx, view, viewType);
    }

    /**
     * group View数据设置
     *
     * @param holder
     * @param groupPos
     * @param position
     * @param groupData
     */
    @Override
    public void onBindGroupHolder(ImageViewHolder holder, int groupPos, int position, ImageViewGroupBean groupData) {
        holder.group_tv_title.setText(groupData.getName());
    }

    /**
     * child View数据设置
     *
     * @param holder
     * @param groupPos
     * @param childPos
     * @param position
     * @param childData
     */
    @Override
    public void onBindChildpHolder(ImageViewHolder holder, int groupPos, int childPos, int position, ImageViewChildBean childData) {
        holder.child_iv_image.setImageResource(childData.getResId());
    }
}
