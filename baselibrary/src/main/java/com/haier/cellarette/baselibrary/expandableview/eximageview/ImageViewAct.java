package com.haier.cellarette.baselibrary.expandableview.eximageview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.expandableview.SimplePaddingDecoration;
import com.haier.cellarette.baselibrary.expandableview.eximageview.adapter.ImageViewAdapter;
import com.haier.cellarette.baselibrary.expandableview.eximageview.bean.ImageViewChildBean;
import com.haier.cellarette.baselibrary.expandableview.eximageview.bean.ImageViewGroupBean;
import com.haier.cellarette.baselibrary.expandableview.extextview.adapter.TextViewAdapter;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;
import java.util.List;

import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

public class ImageViewAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<RecyclerViewData> mDatas;
    private ImageViewAdapter mAdapter;


    private void initDatas() {
        List<ImageViewChildBean> bean1 = new ArrayList<>();
        List<ImageViewChildBean> bean2 = new ArrayList<>();
        List<ImageViewChildBean> bean3 = new ArrayList<>();
        // 每个子列表长度可以不相同
        bean1.add(new ImageViewChildBean("Dog", R.mipmap.dog));
        bean1.add(new ImageViewChildBean("Dog", R.mipmap.dog));
        bean2.add(new ImageViewChildBean("Cat", R.mipmap.cat));
        bean3.add(new ImageViewChildBean("Bird", R.mipmap.bird));

        ImageViewGroupBean bean11 = new ImageViewGroupBean("Dog", R.mipmap.dog);
        ImageViewGroupBean bean22 = new ImageViewGroupBean("Cat", R.mipmap.cat);
        ImageViewGroupBean bean33 = new ImageViewGroupBean("Bird", R.mipmap.bird);
        mDatas = new ArrayList<>();
        mDatas.add(new RecyclerViewData(bean11, bean1, true));
        mDatas.add(new RecyclerViewData(bean22, bean2, true));
        mDatas.add(new RecyclerViewData(bean33, bean3, true));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandableact_imageview);
        findview();
        donetwork();
        onclicklistener();
    }

    private void donetwork() {
        initDatas();
        mAdapter = new ImageViewAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void onclicklistener() {
        mAdapter.setOnItemClickListener(new OnRecyclerViewListener.OnItemClickListener() {
            @Override
            public void onGroupItemClick(int position, int groupPosition, View view) {
                //点击group bufen
                ImageViewGroupBean group = (ImageViewGroupBean) mDatas.get(groupPosition).getGroupData();
                Toasty.normal(ImageViewAct.this, "groupPos:" + groupPosition + " group:" + group.getName()).show();
            }

            @Override
            public void onChildItemClick(int position, int groupPosition, int childPosition, View view) {
                //点击child bufen
                ImageViewChildBean bean = (ImageViewChildBean) mDatas.get(groupPosition).getChild(childPosition);
                Toasty.normal(ImageViewAct.this, "groupPos:" + groupPosition + " childPos:" + childPosition + " child:" + bean.getName()).show();
            }
        });

    }

    private void findview() {
        mRecyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SimplePaddingDecoration(this));

    }

}