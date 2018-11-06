package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.bean.BaseRecActDemo41ChildBean;
import com.haier.cellarette.baselibrary.zothers.ClickableMovementMethod;
import com.haier.cellarette.baselibrary.zothers.SpannableStringUtils;

import java.util.List;

public class BaseRecActDemo41Style4Adapter extends BaseQuickAdapter<BaseRecActDemo41ChildBean, BaseViewHolder> {

    public BaseRecActDemo41Style4Adapter(int layoutResId, List<BaseRecActDemo41ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseRecActDemo41ChildBean item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.brademo1_img, R.drawable.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.brademo1_img, R.drawable.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.brademo1_img, R.drawable.animation_img3);
                break;
            default:
                break;
        }
        helper.setText(R.id.brademo1_tweetName, item.getUserName());
//        helper.setText(R.id.brademo1_tweetText,item.getText());
        helper.setText(R.id.brademo1_tweetDate, item.getCreatedAt());
        ((TextView) helper.getView(R.id.brademo1_tweetText)).setText(SpannableStringUtils.getBuilder(item.getText()).
                append("点击查看博客链接").setClickSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri url = Uri.parse("http://blog.51cto.com/liangxiao");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(url);
                mContext.startActivity(intent);
            }
        }).create());
        ((TextView) helper.getView(R.id.brademo1_tweetText)).setMovementMethod(ClickableMovementMethod.getInstance());
        helper.getView(R.id.brademo1_tweetText).setFocusable(false);
        helper.getView(R.id.brademo1_tweetText).setClickable(true);
        helper.getView(R.id.brademo1_tweetText).setLongClickable(false);

        helper.addOnClickListener(R.id.brademo1_img);
        helper.addOnClickListener(R.id.brademo1_tweetName);
        helper.addOnClickListener(R.id.brademo1_tweetText);
    }


}
