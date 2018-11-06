package com.example.slbappindex.domain;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Biaoge_listBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String text_id;
    private String text_content;
    @DrawableRes
    private int text_icon;
    @DrawableRes
    private int text_icon2;
    private boolean enselect = false;

    public Biaoge_listBean() {
    }

    public Biaoge_listBean(String text_id, String text_content, int text_icon, int text_icon2, boolean enselect) {
        this.text_id = text_id;
        this.text_content = text_content;
        this.text_icon = text_icon;
        this.text_icon2 = text_icon2;
        this.enselect = enselect;
    }

    public String getText_id() {
        return text_id;
    }

    public void setText_id(String text_id) {
        this.text_id = text_id;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public @DrawableRes int getText_icon() {
        return text_icon;
    }

    public void setText_icon(@DrawableRes int text_icon) {
        this.text_icon = text_icon;
    }

    public int getText_icon2() {
        return text_icon2;
    }

    public void setText_icon2(int text_icon2) {
        this.text_icon2 = text_icon2;
    }

    public boolean isEnselect() {
        return enselect;
    }

    public void setEnselect(boolean enselect) {
        this.enselect = enselect;
    }
}
