package com.haier.uplus.control.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.biz.wine_management.bean.WineManageBean;
import com.haier.cellarette.libglide37.glide.GlideUtil;
import com.haier.uplus.control.R;

import java.util.List;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/22.
 * Description:
 */
public class WineManageAdapter extends RecyclerView.Adapter<WineManageAdapter.ViewHolder>{

    private Context mContext;
    private List<WineManageBean.DataBean> mDates;

    public WineManageAdapter(Context mContext, List<WineManageBean.DataBean> mDates) {
        this.mContext = mContext;
        this.mDates = mDates;
    }

    public void setDate(List<WineManageBean.DataBean> data){
        mDates.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_wine_manage,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WineManageBean.DataBean wineManageBean = mDates.get(position);
        GlideUtil.display(mContext,holder.iv_wine,wineManageBean.getGoodsImage());
        holder.tv_wine_name.setText(wineManageBean.getGoodsName());
    }


    @Override
    public int getItemCount() {
        return mDates.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_wine;
        public TextView tv_wine_name;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_wine = itemView.findViewById(R.id.iv_wine);
            tv_wine_name = itemView.findViewById(R.id.tv_wine_name);
        }
    }
}
