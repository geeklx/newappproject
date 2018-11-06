package com.haier.cellarette.baselibrary.recycleviewalluses.demo2baseadpterhelp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRecActDemo2Adapter mAdapter;
    private List<BaseRecActDemo2Bean> mList;

    public static List<BaseRecActDemo2Bean> getSampleData(int lenth) {
        List<BaseRecActDemo2Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo2Bean status = new BaseRecActDemo2Bean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewalluses_demo2);
        findview();
        onclicklistener();
        donetwork();
    }

    private void donetwork() {
        mList = new ArrayList<>();
        mList = getSampleData(3);
        mAdapter.setNewData(mList);
        mAdapter.notifyDataSetChanged();

    }

    private void onclicklistener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                Toasty.normal(BaseRecActDemo2.this, position + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo2Bean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    Toasty.normal(BaseRecActDemo2.this, addressBean.getUserAvatar() + "    " + position).show();
                } else if (i == R.id.brademo1_tweetName) {
                    Toasty.normal(BaseRecActDemo2.this, addressBean.getUserName() + position).show();
                } else if (i == R.id.brademo1_tweetText) {
                    Toasty.normal(BaseRecActDemo2.this, addressBean.getText() + position).show();
                } else {
                }
            }
        });
    }

    private void findview() {
        mRecyclerView = findViewById(R.id.rvlist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseRecActDemo2Adapter(R.layout.baserecactdemo2_item);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount

        //head footer
        View headerView = getHeaderView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
            }
        });
        mAdapter.addHeaderView(headerView);

        View footerView = getFooterView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
            }
        });
        mAdapter.addFooterView(footerView, 0);

        mRecyclerView.setAdapter(mAdapter);
    }

    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeHeaderView(v);
            }
        };
    }

    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeFooterView(v);
            }
        };
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.activity_recycleviewalluses_demo2_head_view, (ViewGroup) mRecyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = view.findViewById(R.id.iv);
            imageView.setImageResource(R.drawable.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View getFooterView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.activity_recycleviewalluses_demo2_footer_view, (ViewGroup) mRecyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = view.findViewById(R.id.iv);
            imageView.setImageResource(R.drawable.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

}
