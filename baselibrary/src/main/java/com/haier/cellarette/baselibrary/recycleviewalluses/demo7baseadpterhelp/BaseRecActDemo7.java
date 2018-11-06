package com.haier.cellarette.baselibrary.recycleviewalluses.demo7baseadpterhelp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseRecActDemo7 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRecActDemo7Adapter mAdapter;
    private List<BaseRecActDemo7BeanMsg> mList;

    private List<BaseRecActDemo7BeanMsg> genData() {
        ArrayList<BaseRecActDemo7BeanMsg> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "赵丽颖";
            int price = random.nextInt(10) + 10;
            int len = random.nextInt(80) + 60;
//            Movie movie = new Movie(name, len, price, "He was one of Australia's most distinguished artistes");
            BaseRecActDemo7BeanMsg movie = new BaseRecActDemo7BeanMsg(name + i, i + i, i, "He was one of Australia's most distinguished artistes" + i);
            list.add(movie);
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo7);
        mRecyclerView = findViewById(R.id.rvlist);
        mAdapter = new BaseRecActDemo7Adapter();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mList = new ArrayList<>();
        mList = genData();
        mAdapter.setNewData(mList);

        mAdapter.setUpFetchEnable(true);
        mAdapter.setStartUpFetchPosition(2);
        mAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                startUpFetch();
            }
        });
    }

    private int count;

    private void startUpFetch() {
        count++;
        // set fetching on when start network request.
        mAdapter.setUpFetching(true);
        // get data from internet.
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData(0, genData());
                // set fetching off when network request ends.
                mAdapter.setUpFetching(false);
                // set fetch enable false when you don't need anymore.
                if (count > 5) {
                    mAdapter.setUpFetchEnable(false);
                }
            }
        }, 300);
    }
}
