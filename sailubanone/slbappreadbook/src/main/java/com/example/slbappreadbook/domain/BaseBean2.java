package com.example.slbappreadbook.domain;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

public class BaseBean2 {
    private int id;
    private @DrawableRes
    int drawable;
    private String url;
    private Bitmap bitmap;

    public BaseBean2() {
    }

    public BaseBean2(int id, int drawable, String url) {
        this.id = id;
        this.drawable = drawable;
        this.url = url;
    }

    public BaseBean2(int id, int drawable, String url, Bitmap bitmap) {
        this.id = id;
        this.drawable = drawable;
        this.url = url;
        this.bitmap = bitmap;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
