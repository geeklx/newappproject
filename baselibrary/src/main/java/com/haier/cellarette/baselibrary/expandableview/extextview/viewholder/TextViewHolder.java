package com.haier.cellarette.baselibrary.expandableview.extextview.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

import drawthink.expandablerecyclerview.holder.BaseViewHolder;

public class TextViewHolder extends BaseViewHolder {

    public TextView child_tv_name;
    public TextView group_tv_title;

    public TextViewHolder(Context ctx, View itemView, int viewType) {
        super(ctx, itemView, viewType);
        child_tv_name = itemView.findViewById(R.id.child_tv_name);
        group_tv_title = itemView.findViewById(R.id.group_tv_title);

    }

    @Override
    public int getChildViewResId() {
        return R.id.child;
    }

    @Override
    public int getGroupViewResId() {
        return R.id.group;
    }
}
