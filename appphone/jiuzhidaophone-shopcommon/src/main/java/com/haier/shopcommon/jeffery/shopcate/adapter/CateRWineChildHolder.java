package com.haier.shopcommon.jeffery.shopcate.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.haier.shopcommon.R;
import com.haier.shopcommon.jeffery.shopcate.bean.WineBean;
import com.haier.shopcommon.jeffery.view.expandablerecylerview.viewholders.ChildViewHolder;

/**
 * Created by JefferyLeng on 2018/6/28.
 */
public class CateRWineChildHolder extends ChildViewHolder {

    Context mContext;
    TextView mTvSubContentName;

    public CateRWineChildHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mTvSubContentName = itemView.findViewById(R.id.tv_cate_sub_content_name);
    }

    public void setSubContentName(WineBean wineBean) {
        mTvSubContentName.setText(wineBean.getWineName());
    }
}
