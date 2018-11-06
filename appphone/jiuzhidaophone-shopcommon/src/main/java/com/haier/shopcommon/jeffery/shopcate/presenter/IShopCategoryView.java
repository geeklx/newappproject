package com.haier.shopcommon.jeffery.shopcate.presenter;

import com.haier.cellarette.libmvp.mvp.IView;
import com.haier.shopcommon.jeffery.shopcate.bean.CategoryBean;

import java.util.ArrayList;

/**
 * Created by JefferyLeng on 2018/6/20.
 */
public interface IShopCategoryView extends IView {

    void fillCateList(ArrayList<CategoryBean.DataBean> categoryBeans);


}
