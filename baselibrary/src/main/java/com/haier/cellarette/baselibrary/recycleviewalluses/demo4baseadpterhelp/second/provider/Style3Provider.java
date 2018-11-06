package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.adapter.BaseRecActDemo42Adapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.bean.BaseRecActDemo42Bean;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class Style3Provider extends BaseItemProvider<BaseRecActDemo42Bean, BaseViewHolder> {

    @Override
    public int viewType() {
        return BaseRecActDemo42Adapter.STYLE_THREE;
    }

    @Override
    public int layout() {
        return R.layout.activity_recycleviewallsuses_demo42_item3;
    }

    @Override
    public void convert(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
        if (position % 2 == 0) {
            helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img1);
        } else {
            helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img2);
        }
        helper.setText(R.id.tv_provider3, data.getmBean().getUserName());
        helper.addOnClickListener(R.id.iv_provider3);
        helper.addOnClickListener(R.id.tv_provider3);
        helper.addOnLongClickListener(R.id.iv_provider3);
        helper.addOnLongClickListener(R.id.tv_provider3);
    }

//    @Override
//    public void onClick(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
//        if (helper.getChildClickViewIds().contains(R.id.iv)) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserAvatar()).show();
//        } else if (helper.getChildClickViewIds().contains(R.id.tv)) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserName()).show();
//        } else {
//        }
//
//    }

//    @Override
//    public boolean onLongClick(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
//        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
//        return true;
//    }
}
