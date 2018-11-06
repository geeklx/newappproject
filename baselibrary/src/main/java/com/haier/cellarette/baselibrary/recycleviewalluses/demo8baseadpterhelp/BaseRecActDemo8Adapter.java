package com.haier.cellarette.baselibrary.recycleviewalluses.demo8baseadpterhelp;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.cellarette.baselibrary.R;

import java.util.List;

public class BaseRecActDemo8Adapter extends BaseQuickAdapter<BaseRecActDemo8Bean, BaseViewHolder> {

    public BaseRecActDemo8Adapter(int layoutResId, @Nullable List<BaseRecActDemo8Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseRecActDemo8Bean item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.brademo1_img, R.drawable.img00);
                break;
            case 1:
                helper.setImageResource(R.id.brademo1_img, R.drawable.img01);
                break;
            case 2:
                helper.setImageResource(R.id.brademo1_img, R.drawable.img02);
                break;
            default:

                break;
        }
        helper.setText(R.id.brademo1_tweetName,item.getUserName());
        helper.setText(R.id.brademo1_tweetText,item.getText());
        helper.setText(R.id.brademo1_tweetDate,item.getCreatedAt());
    }

}
