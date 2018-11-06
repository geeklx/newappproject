package com.haier.cellarette.baselibrary.recycleviewalluses.demo9baseadpterhelp;


import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.haier.cellarette.baselibrary.R;

/**
 * Created by BlingBling on 2016/10/15.
 */

public final class CustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycleviewallsuses_demo9_emptyview;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view1;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view1;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view1;
    }
}
