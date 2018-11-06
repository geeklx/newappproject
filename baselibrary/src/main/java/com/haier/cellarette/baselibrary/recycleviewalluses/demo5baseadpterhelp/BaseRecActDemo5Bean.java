package com.haier.cellarette.baselibrary.recycleviewalluses.demo5baseadpterhelp;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseRecActDemo5Bean implements Serializable{
    private static final long serialVersionUID = 1L;
    private @DrawableRes int img;
    private String userName;
    private String text;

    public BaseRecActDemo5Bean() {
    }

    public BaseRecActDemo5Bean(int img, String userName, String text) {
        this.img = img;
        this.userName = userName;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
