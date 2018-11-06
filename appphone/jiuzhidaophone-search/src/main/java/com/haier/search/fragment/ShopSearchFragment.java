package com.haier.search.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.flowlayout.FlowLayout;
import com.haier.cellarette.baselibrary.flowlayout.TagAdapter;
import com.haier.cellarette.baselibrary.flowlayout.TagFlowLayout;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libmvp.mvp.PresenterHelper;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.search.R;
import com.haier.search.bean.SearchTagBean;
import com.haier.search.contract.ShopSearchContract;
import com.haier.search.contract.ShopSearchPresenter;
import com.haier.search.view.BackCallBack;
import com.haier.search.view.ICallBack;
import com.haier.search.view.ShopSearchView;

import java.util.LinkedList;
import java.util.List;

/**
 * 商品搜索界面
 * Created by JefferyLeng on 2018/6/11.
 */
public class ShopSearchFragment extends BaseFragment implements ShopSearchContract.ShopSearchView {

    private final String TAG = this.getClass().getSimpleName();

    private TagFlowLayout mTflHotWords;
    private TagFlowLayout mTflHistoryWords;

    private ShopSearchView mShopSearchView;
    private RelativeLayout mRlClearHistory;

    private ShopSearchPresenter mShopSearchPresenter;

    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.shop_search_fragment;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        MyLogUtil.d(TAG, "ShopSearchFragment init, setup INVOKE....");
        mContext = getActivity().getApplicationContext();
        initView(rootView);
        initListener();
        mShopSearchPresenter = PresenterHelper.create(ShopSearchPresenter.class, this);
        mShopSearchPresenter.start(mContext);
    }

    private void initView(View rootView) {
        mShopSearchView = f(rootView, R.id.ssv_search);
        mRlClearHistory = f(rootView, R.id.rl_clear_searchhistory);
        mTflHotWords = f(rootView,R.id.tfl_hotword);
        mTflHistoryWords = f(rootView,R.id.tfl_history);
    }

    private void initListener() {
        mTflHotWords.setAdapter(mHotwordsAdapter);
        mTflHistoryWords.setAdapter(mLocalHistoryAdapter);

        mTflHistoryWords.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // TODO: 2018/6/12 跳到商品列表
                SearchTagBean item = (SearchTagBean) mLocalHistoryAdapter.getItem(position);
                Toasty.info(mContext,"点击item : " + item.getTagName()).show();
                return true;
            }
        });

        mTflHotWords.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // TODO: 2018/6/15 跳转到商品界面
                SearchTagBean item = (SearchTagBean) mHotwordsAdapter.getItem(position);
                Toasty.info(mContext,"点击item : " + item.getTagName()).show();
                return true;
            }
        });

        mShopSearchView.setOnClickBack(new BackCallBack() {
            @Override
            public void BackAciton() {
                // TODO: 2018/6/12 hide current fragment
                MyLogUtil.d(TAG, "hide ShopSearchFragment------->");

            }
        });

        mShopSearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                // TODO: 2018/6/12 1,更新本地历史数据 2，查询商品信息，跳转到商品列表界面
                MyLogUtil.d(TAG, "string 搜索的内容 : " + string);
                if (string.length() > 5) {
                    string = string.substring(0,4) + "...";
                }
                mShopSearchPresenter.handleLocalSearchHistory(string);

            }
        });
        mRlClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.info(mContext,mShopSearchPresenter.clearHistoryData()).show();
            }
        });
    }

    private TagAdapter mHotwordsAdapter = new TagAdapter() {
        @Override
        public View getView(FlowLayout parent, int position, Object o) {
            SearchTagBean item = (SearchTagBean) getItem(position);
            TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.shop_search_tag_item, null);
            tv.setText(item.getTagName());
            return tv;
        }
    };

    private TagAdapter mLocalHistoryAdapter = new TagAdapter() {
        @Override
        public View getView(FlowLayout parent, int position, Object o) {
            SearchTagBean item = (SearchTagBean) getItem(position);
            TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.shop_search_history_tag_item, null);
            tv.setText(item.getTagName());
            return tv;
        }
    };


    @Override
    public void updateHotWordsView(List<SearchTagBean> tagBeanList) {
        mHotwordsAdapter.addData(tagBeanList);
        mHotwordsAdapter.notifyDataChanged();
    }

    @Override
    public void updateLocalHistoryWordsView(LinkedList<SearchTagBean> tagBeanList) {
        if (tagBeanList == null) {
            mLocalHistoryAdapter.clearData();
        } else {
            mLocalHistoryAdapter.addData(tagBeanList);
        }
        mLocalHistoryAdapter.notifyDataChanged();

    }

    @Override
    public void onPause() {
        super.onPause();
        MyLogUtil.d(TAG, "onPause invoke........ sync sp");
        // TODO: 2018/6/12 减少IO存取本地搜索历史数据的次数，点击返回界面进行IO读取
        mShopSearchPresenter.toSyncHistorySearchWords();
    }
}
