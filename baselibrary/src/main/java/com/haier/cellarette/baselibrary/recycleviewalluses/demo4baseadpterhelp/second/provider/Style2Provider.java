package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.adapter.BaseRecActDemo42Adapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.bean.BaseRecActDemo42Bean;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class Style2Provider extends BaseItemProvider<BaseRecActDemo42Bean, BaseViewHolder> {

    @Override
    public int viewType() {
        return BaseRecActDemo42Adapter.STYLE_TWO;
    }

    @Override
    public int layout() {
        return R.layout.activity_recycleviewallsuses_demo42_item2;
    }

    @Override
    public void convert(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
        helper.setText(R.id.tv_provider2, data.getmBean().getUserName());
        helper.setText(R.id.tv1_provider2, data.getmBean().getText());
        helper.addOnClickListener(R.id.tv_provider2);
        helper.addOnClickListener(R.id.tv1_provider2);
    }

//    @Override
//    public void onClick(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
//        if (helper.getItemId() == helper.getView(R.id.tv).getId()) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserName()).show();
//        } else if (helper.getItemId() == helper.getView(R.id.tv1).getId()) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getText()).show();
//        } else {
//        }
//
//    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
