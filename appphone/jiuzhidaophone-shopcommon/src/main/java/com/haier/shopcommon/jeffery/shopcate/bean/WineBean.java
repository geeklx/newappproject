package com.haier.shopcommon.jeffery.shopcate.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JefferyLeng on 2018/6/29.
 */
public class WineBean implements Parcelable {
    private String wineName;

    public WineBean(String wineName) {
        this.wineName = wineName;
    }

    protected WineBean(Parcel in) {
        wineName = in.readString();
    }

    public static final Creator<WineBean> CREATOR = new Creator<WineBean>() {
        @Override
        public WineBean createFromParcel(Parcel in) {
            return new WineBean(in);
        }

        @Override
        public WineBean[] newArray(int size) {
            return new WineBean[size];
        }
    };

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(wineName);
    }
}

