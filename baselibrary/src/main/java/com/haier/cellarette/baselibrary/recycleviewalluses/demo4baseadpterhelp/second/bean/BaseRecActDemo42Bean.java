package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.bean;

import java.io.Serializable;

public class BaseRecActDemo42Bean implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int style1 = 1;
    public static final int style2 = 2;
    public static final int style3 = 3;

    public int type;
    private BaseRecActDemo42ChildBean mBean;

    public BaseRecActDemo42Bean(int type) {
        this.type = type;
    }

    public BaseRecActDemo42Bean(int type, BaseRecActDemo42ChildBean mBean) {
        this.type = type;
        this.mBean = mBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BaseRecActDemo42ChildBean getmBean() {
        return mBean;
    }

    public void setmBean(BaseRecActDemo42ChildBean mBean) {
        this.mBean = mBean;
    }
}
