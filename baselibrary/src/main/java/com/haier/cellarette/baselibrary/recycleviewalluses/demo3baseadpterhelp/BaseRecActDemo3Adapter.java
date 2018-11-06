package com.haier.cellarette.baselibrary.recycleviewalluses.demo3baseadpterhelp;


import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.cellarette.baselibrary.R;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseRecActDemo3Adapter extends BaseSectionQuickAdapter<BaseRecActDemo3BeanHead, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public BaseRecActDemo3Adapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final BaseRecActDemo3BeanHead item) {
        helper.setText(R.id.demo3header, item.header);
        helper.setVisible(R.id.demo3more, item.isMore());
        helper.addOnClickListener(R.id.demo3more);

    }


    @Override
    protected void convert(BaseViewHolder helper, BaseRecActDemo3BeanHead item) {

        switch (helper.getLayoutPosition() % 2) {
            case 0:
                helper.setImageResource(R.id.iv, R.drawable.m_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.drawable.m_img2);
                break;
            default:
                break;
        }
        helper.setText(R.id.tv, item.t.getName());
        helper.addOnClickListener(R.id.tv);

    }
}
