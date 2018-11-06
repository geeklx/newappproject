package com.haier.cellarette.baselibrary.recycleviewalluses.demo5baseadpterhelp;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.cellarette.baselibrary.R;

import java.util.List;

public class BaseRecActDemo5Adapter extends BaseItemDraggableAdapter<BaseRecActDemo5Bean, BaseViewHolder> {

    public BaseRecActDemo5Adapter(int layoutid, List<BaseRecActDemo5Bean> data) {
        super(layoutid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseRecActDemo5Bean item) {
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setImageResource(R.id.iv_head, R.drawable.ic_zhaoliying);
                break;
            case 1:
                helper.setImageResource(R.id.iv_head, R.drawable.img00);
                break;
            case 2:
                helper.setImageResource(R.id.iv_head, R.drawable.img01);
                break;
        }
        helper.setText(R.id.tv, item.getUserName());
        helper.setText(R.id.email, item.getText());
        helper.addOnClickListener(R.id.tv);
        helper.addOnClickListener(R.id.email);
    }
}
