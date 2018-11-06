package com.haier.cellarette.baselibrary.recycleviewalluses.demo3baseadpterhelp;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseRecActDemo3BeanHead extends SectionEntity<BaseRecActDemo3Bean> {
    private boolean isMore;
    public BaseRecActDemo3BeanHead(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public BaseRecActDemo3BeanHead(BaseRecActDemo3Bean t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
