package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.bean.BaseRecActDemo41Bean;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.bean.BaseRecActDemo41ChildBean;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo41Adapter extends BaseMultiItemQuickAdapter<BaseRecActDemo41Bean, BaseViewHolder> {

    private BaseRecActDemo41Style4Adapter mAdapter;
    private LinearSnapHelper mLinearSnapHelper;
    private List<BaseRecActDemo41ChildBean> mList = new ArrayList<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseRecActDemo41Adapter(List<BaseRecActDemo41Bean> data) {
        super(data);
        addItemType(BaseRecActDemo41Bean.style1, R.layout.activity_recycleviewallsuses_demo41_item1);
        addItemType(BaseRecActDemo41Bean.style2, R.layout.activity_recycleviewallsuses_demo41_item2);
        addItemType(BaseRecActDemo41Bean.style3, R.layout.activity_recycleviewallsuses_demo41_item3);
        addItemType(BaseRecActDemo41Bean.style4, R.layout.activity_recycleviewallsuses_demo41_item4);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseRecActDemo41Bean item) {
        switch (helper.getItemViewType()) {
            case BaseRecActDemo41Bean.style1:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.drawable.ic_zhaoliying);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.drawable.img01);
                        break;

                }
                break;
            case BaseRecActDemo41Bean.style2:
                helper.setText(R.id.tv, item.getmBean().getUserName());
                helper.setText(R.id.tv1, item.getmBean().getText());
                break;
            case BaseRecActDemo41Bean.style3:
                helper.setText(R.id.tv, item.getmBean().getUserName());
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.drawable.ic_zhaoliying);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.drawable.img01);
                        break;

                }
                break;
            case BaseRecActDemo41Bean.style4:
                final RecyclerView mRecyclerView = helper.getView(R.id.rvlist);
                mRecyclerView.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(), 1, OrientationHelper.HORIZONTAL, false));
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setOnFlingListener(null);
                mLinearSnapHelper = new LinearSnapHelper();
                mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
                mList = getSampleData(6);
                mAdapter = new BaseRecActDemo41Style4Adapter(R.layout.activity_recycleviewallsuses_demo41_item4_style4item, mList);

                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                onclicklistener();
                break;
        }
    }

    private void onclicklistener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                Toasty.normal(BaseApp.get(), position + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo41ChildBean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    Toasty.normal(BaseApp.get(), addressBean.getUserAvatar() + "    " + position).show();
                } else if (i == R.id.brademo1_tweetName) {
                    Toasty.normal(BaseApp.get(), addressBean.getUserName() + position).show();
                } else if (i == R.id.brademo1_tweetText) {
                    Toasty.normal(BaseApp.get(), addressBean.getText() + position).show();
                } else {
                }
            }
        });
    }

    public static List<BaseRecActDemo41ChildBean> getSampleData(int lenth) {
        List<BaseRecActDemo41ChildBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo41ChildBean status = new BaseRecActDemo41ChildBean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }
}
