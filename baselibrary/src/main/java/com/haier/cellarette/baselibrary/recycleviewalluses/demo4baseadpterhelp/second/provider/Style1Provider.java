package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.adapter.BaseRecActDemo42Adapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.bean.BaseRecActDemo42Bean;

public class Style1Provider extends BaseItemProvider<BaseRecActDemo42Bean,BaseViewHolder> {

    @Override
    public int viewType() {
        return BaseRecActDemo42Adapter.STYLE_ONE;
    }

    @Override
    public int layout() {
        return R.layout.activity_recycleviewallsuses_demo42_item1;
    }

    @Override
    public void convert(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
        if (position % 2 == 0) {
            helper.setImageResource(R.id.iv, R.drawable.ic_zhaoliying);
        }else{
            helper.setImageResource(R.id.iv, R.drawable.img01);
        }
    }

    @Override
    public void onClick(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {

        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, BaseRecActDemo42Bean data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
