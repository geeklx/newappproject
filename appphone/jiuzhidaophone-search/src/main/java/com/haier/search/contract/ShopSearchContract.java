package com.haier.search.contract;

import com.haier.cellarette.libmvp.mvp.IView;
import com.haier.search.bean.SearchTagBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JefferyLeng on 2018/6/11.
 */
public class ShopSearchContract {

    public interface ShopSearchView extends IView {

        void updateHotWordsView(List<SearchTagBean> tagBeanList);

        void updateLocalHistoryWordsView(LinkedList<SearchTagBean> tagBeanList);

    }
}
