package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.adapter.BaseRecActDemo41Adapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.bean.BaseRecActDemo41Bean;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.hunhe.bean.BaseRecActDemo41ChildBean;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo41 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRecActDemo41Adapter mAdapter;
    private List<BaseRecActDemo41Bean> mList;

    public static List<BaseRecActDemo41Bean> getMultipleItemData(int lenth) {
        List<BaseRecActDemo41Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo41ChildBean status = new BaseRecActDemo41ChildBean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");

            list.add(new BaseRecActDemo41Bean(BaseRecActDemo41Bean.style1, BaseRecActDemo41Bean.style1_span, status));
            list.add(new BaseRecActDemo41Bean(BaseRecActDemo41Bean.style2, BaseRecActDemo41Bean.style2_span, status));
            list.add(new BaseRecActDemo41Bean(BaseRecActDemo41Bean.style3, BaseRecActDemo41Bean.style3_span, status));
            list.add(new BaseRecActDemo41Bean(BaseRecActDemo41Bean.style4, BaseRecActDemo41Bean.style4_span));
        }

        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo41);
        findview();
        onclicklistener();
        donetwork();
    }

    private void donetwork() {
        mList = new ArrayList<>();
        mList = getMultipleItemData(20);

        mAdapter = new BaseRecActDemo41Adapter(mList);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
//        mAdapter.isFirstOnly(true);//init firstOnly state
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mList.get(position).getSpanSize();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    private void onclicklistener() {

    }

    private void findview() {
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false));

    }

}
