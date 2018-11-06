package com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean;

import com.blankj.utilcode.util.FileUtils;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment22VideoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int style2 = 2;
    public static final int style3 = 3;

    public int type;
    private Fragment22ChildVideoBean mBean;

    public Fragment22VideoBean(int type) {
        this.type = type;
    }

    public Fragment22VideoBean(int type, Fragment22ChildVideoBean mBean) {
        this.type = type;
        this.mBean = mBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Fragment22ChildVideoBean getmBean() {
        return mBean;
    }

    public void setmBean(Fragment22ChildVideoBean mBean) {
        this.mBean = mBean;
    }


}
