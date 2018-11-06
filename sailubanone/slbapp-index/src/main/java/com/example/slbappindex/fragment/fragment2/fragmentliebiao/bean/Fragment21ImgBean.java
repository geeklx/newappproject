package com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean;

import com.blankj.utilcode.util.FileUtils;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.libutils.utilslib.etc.DateUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cc.shinichi.library.bean.ImageInfo;

public class Fragment21ImgBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int style2 = 2;
    public static final int style3 = 3;

    public int type;
    private Fragment21ChildImgBean mBean;

    public Fragment21ImgBean(int type) {
        this.type = type;
    }

    public Fragment21ImgBean(int type, Fragment21ChildImgBean mBean) {
        this.type = type;
        this.mBean = mBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Fragment21ChildImgBean getmBean() {
        return mBean;
    }

    public void setmBean(Fragment21ChildImgBean mBean) {
        this.mBean = mBean;
    }



}
