package com.haier.shopcommon.jeffery.shopcate.bean;

import com.haier.shopcommon.jeffery.view.expandablerecylerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by JefferyLeng on 2018/6/29.
 */
public class Genre extends ExpandableGroup<WineBean> {

    public Genre(String title, List<WineBean> items) {
        super(title, items);
    }


}
