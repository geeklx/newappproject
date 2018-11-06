package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseRecActDemo41Bean implements MultiItemEntity {
    public static final int style1 = 1;
    public static final int style2 = 2;
    public static final int style3 = 3;
    public static final int style4 = 4;
    public static final int style1_span = 1;
    public static final int style2_span = 3;
    public static final int style3_span = 4;
    public static final int style4_span = 4;
    private int itemType;
    private int spanSize;
    private BaseRecActDemo41ChildBean mBean;

    public BaseRecActDemo41Bean(int itemType, int spanSize, BaseRecActDemo41ChildBean mBean) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.mBean = mBean;
    }

    public BaseRecActDemo41ChildBean getmBean() {
        return mBean;
    }

    public void setmBean(BaseRecActDemo41ChildBean mBean) {
        this.mBean = mBean;
    }

    public BaseRecActDemo41Bean(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
