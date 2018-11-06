package com.haier.cellarette.baselibrary.expandableview.eximageview.bean;


import android.support.annotation.DrawableRes;

import java.io.Serializable;

public class ImageViewChildBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    @DrawableRes
    private int resId;

    public ImageViewChildBean() {
    }

    public ImageViewChildBean(String name, @DrawableRes int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}