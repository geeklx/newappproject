package com.haier.shopcommon.jeffery.shopcate.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.shopcommon.R;
import com.haier.shopcommon.jeffery.shopcate.bean.CategoryBean;

import java.util.List;

/**
 * 商品分类列表adapter
 * Created by JefferyLeng on 2018/6/22.
 */
public class ShopCateListAdapter extends BaseShopCateAdapter<CategoryBean.DataBean> {

    private int selectItem = 0;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    

    public ShopCateListAdapter(Context mContext, List<CategoryBean.DataBean> categoryBeanArrayList) {
        super(mContext, categoryBeanArrayList);
    }

    @Override
    protected View fillView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.shop_category_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvTitleName = view.findViewById(R.id.tv_cate_title);
            viewHolder.mIvTitlePic = view.findViewById(R.id.iv_cate_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CategoryBean.DataBean categoryTitleBean = mCategoryBeans.get(position);
        viewHolder.mTvTitleName.setText(categoryTitleBean.getModuleTitle());

        if (position == selectItem) {
//            viewHolder.mTvTitleName.setBackgroundColor(Color.WHITE);
            viewHolder.mIvTitlePic.setImageResource(R.drawable.cate_list_selected_star);
            viewHolder.mTvTitleName.setTextColor(mContext.getResources().getColor(R.color.shop_cate_list_green));
        } else {
//            viewHolder.mTvTitleName.setBackgroundColor(mContext.getResources().getColor(R.color.shop_cate_list_bg));
            viewHolder.mIvTitlePic.setImageResource(R.drawable.cate_list_normal_star);
            viewHolder.mTvTitleName.setTextColor(mContext.getResources().getColor(R.color.shop_cate_list_normal));
        }
        
        return view;
    }

    private static class ViewHolder {
        private TextView mTvTitleName;
        private ImageView mIvTitlePic;
    }
}
