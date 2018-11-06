package com.haier.cellarette.baselibrary.expandableview.eximageview.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

import drawthink.expandablerecyclerview.holder.BaseViewHolder;

public class ImageViewHolder extends BaseViewHolder {

    public ImageView child_iv_image;
    public TextView group_tv_title;
    /**
     *  初始化你的View(这里包括GroupView,和childView)
     */
    public ImageViewHolder(Context ctx, View itemView, int viewType) {
        super(ctx, itemView, viewType);
        child_iv_image = itemView.findViewById(R.id.child_iv_image);
        group_tv_title = itemView.findViewById(R.id.group_tv_title);

    }
    /**
     * @return 返回你的ChildView 布局文件中根节点的ID
     */
    @Override
    public int getChildViewResId() {
        return R.id.child;
    }
    /**
     * @return 返回你的GroupView 布局文件中根节点的ID
     */
    @Override
    public int getGroupViewResId() {
        return R.id.group;
    }
}
