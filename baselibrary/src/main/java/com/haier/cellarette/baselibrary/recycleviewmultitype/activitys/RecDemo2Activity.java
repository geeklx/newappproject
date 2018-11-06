package com.haier.cellarette.baselibrary.recycleviewmultitype.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;


import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo2.ItemDemo2;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo2.ItemDemo21;
import com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo2.ItemDemo2ImageViewBinder;
import com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo2.ItemDemo2TextViewBinder;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypeAsserts;

public class RecDemo2Activity extends AppCompatActivity {

    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Object> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo2);
        mRecyclerView = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1, OrientationHelper.VERTICAL,false));
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo2.class,new ItemDemo2TextViewBinder());
        mAdapter.register(ItemDemo21.class,new ItemDemo2ImageViewBinder());
        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView,mAdapter);

        items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(new ItemDemo2(i+"",i));
            items.add(new ItemDemo21(i+"",R.drawable.ic_fab_done));
        }
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter,items);
    }
}
