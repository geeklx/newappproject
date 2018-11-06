package com.haier.cellarette.baselibrary.recycleviewalluses.demo6baseadpterhelp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haier.cellarette.baselibrary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseRecActDemo6 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRecActDemo6Adapter mAdapter;
    private List<BaseRecActDemo6BeanMsg> mList;

    private List<BaseRecActDemo6BeanMsg> genData() {
        ArrayList<BaseRecActDemo6BeanMsg> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "赵丽颖";
            int price = random.nextInt(10) + 10;
            int len = random.nextInt(80) + 60;
//            Movie movie = new Movie(name, len, price, "He was one of Australia's most distinguished artistes");
            BaseRecActDemo6BeanMsg movie = new BaseRecActDemo6BeanMsg(name + i, i + i, i, "He was one of Australia's most distinguished artistes" + i);
            list.add(movie);
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo6);
        mRecyclerView = findViewById(R.id.rvlist);
        mAdapter = new BaseRecActDemo6Adapter(R.layout.activity_recycleviewallsuses_demo6_item, genData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }
}
