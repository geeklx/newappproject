package com.haier.shopcommon.jeffery.shopcate.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.shopcommon.R;
import com.haier.shopcommon.jeffery.view.expandablerecylerview.viewholders.GroupViewHolder;

/**
 * Created by JefferyLeng on 2018/6/28.
 */
public class CateRWineViewHolder extends GroupViewHolder {

    private ImageView mIvCircleIcon;
    private TextView mTvCateName;
    private ImageView mIvArrow;

    public CateRWineViewHolder(View itemView) {
        super(itemView);
        mIvCircleIcon = itemView.findViewById(R.id.cate_content_iv_icon);
        mTvCateName = itemView.findViewById(R.id.cate_content_tv_name);
        mIvArrow = itemView.findViewById(R.id.cate_content_iv_arrow);
    }


    public void setContentName(String title) {
        mTvCateName.setText(title);
    }
}
