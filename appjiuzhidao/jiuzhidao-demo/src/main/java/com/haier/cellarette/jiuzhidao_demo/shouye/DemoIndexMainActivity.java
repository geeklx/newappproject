package com.haier.cellarette.jiuzhidao_demo.shouye;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.updateapk.UpdateManager;
import com.haier.cellarette.jiuzhidao_demo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoIndexMainActivity extends BaseActivity {

    private List<Biaoge_listBean> mList;
    private static final String LIST_TAG1 = "list1";
    private static final String LIST_TAG2 = "list2";
    private static final String LIST_TAG3 = "list3";
    private static final String LIST_TAG4 = "list4";
    private static final String LIST_TAG5 = "list5";
    private String id1 = "1";
    private String id2 = "2";
    private String id3 = "3";
    private String id4 = "4";
    private String id5 = "5";

    private void addList() {
        mList.add(new Biaoge_listBean(id1, "直播", R.drawable.control_pobiji_guoshu_normal, R.drawable.control_pobiji_guoshu_select, true));
        mList.add(new Biaoge_listBean(id2, "视频", R.drawable.control_pobiji_jiangliao_normal, R.drawable.control_pobiji_jiangliao_select, false));
        mList.add(new Biaoge_listBean(id3, "关注", R.drawable.control_pobiji_lengyin_normal, R.drawable.control_pobiji_lengyin_select, false));
        mList.add(new Biaoge_listBean(id4, "鱼吧", R.drawable.control_pobiji_mofen_normal, R.drawable.control_pobiji_mofen_select, false));
        mList.add(new Biaoge_listBean(id5, "发现", R.drawable.control_pobiji_reyin_normal, R.drawable.control_pobiji_reyin_select, false));
    }

    private void clearList() {
        for (int i = 0; i < mList.size(); i++) {
            Biaoge_listBean item = mList.get(i);
            if (item.isEnselect()) {
                item.setEnselect(false);
            }
        }
    }

    //数据解析bufen
    private RecyclerView recyclerView;
    private RecycleAdapter mAdapter;
    private List<Biaoge_listBean> mratings;

    private FragmentContent1 mFragment1; //
    private FragmentContent2 mFragment2; //
    private FragmentContent3 mFragment3; //
    private FragmentContent4 mFragment4; //
    private FragmentContent5 mFragment5; //


    private FragmentManager mFragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demomain;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        mFragmentManager = getSupportFragmentManager();
        doNetWork();
        versionupdate();
    }

    private void versionupdate() {
        UpdateManager manager = new UpdateManager(this,
                DemoIndexMainActivity.this);
        manager.checkUpdate();
    }

    private void doNetWork() {
        //请求数据bufen
        mList = new ArrayList<Biaoge_listBean>();
        addList();
        mAdapter.setContacts(mList);
        mAdapter.notifyDataSetChanged();
        //设置Fragmentbufen
        showFragment(id1, false);
    }

    private void onclick() {
        mAdapter.setOnItemClickLitener(new RecycleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击item
                Biaoge_listBean model = (Biaoge_listBean) mAdapter.getItem(position);
                if (model.isEnselect()) {
                    //切换Fragment 并且刷新
                    showFragment(model.getText_id(), true);
                } else {
                    //设置为选中
                    clearList();
                    model.setEnselect(true);
                }
                mAdapter.setContacts(mList);
                mAdapter.notifyDataSetChanged();
                //切换Fragment
                showFragment(model.getText_id(), false);
            }
        });
    }

    private void findview() {
        recyclerView = findViewById(R.id.recycler_view1);

        mAdapter = new RecycleAdapter(this);
//        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
//        mLinearLayoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
//        recyclerView.setLayoutManager(mLinearLayoutManager1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5, OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    private void showFragment(String tag, boolean isrefresh) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);

        if (tag.equalsIgnoreCase("-1")) { //
            if (mFragment1 == null) {
                mFragment1 = new FragmentContent1();
                transaction.add(R.id.container, mFragment1, LIST_TAG1);
            } else {
                transaction.show(mFragment1);
                mFragment1.initData();
            }
        } else if (tag.equalsIgnoreCase(id1)) {
            if (mFragment1 == null) {
                mFragment1 = new FragmentContent1();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment1.setArguments(args);
                transaction.add(R.id.container, mFragment1, LIST_TAG1);
            } else {
                transaction.show(mFragment1);
                mFragment1.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id2)) {
            if (mFragment2 == null) {
                mFragment2 = new FragmentContent2();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment2.setArguments(args);
                transaction.add(R.id.container, mFragment2, LIST_TAG2);
            } else {
                transaction.show(mFragment2);
                mFragment2.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id3)) {
            if (mFragment3 == null) {
                mFragment3 = new FragmentContent3();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment3.setArguments(args);
                transaction.add(R.id.container, mFragment3, LIST_TAG3);
            } else {
                transaction.show(mFragment3);
                mFragment3.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id4)) {
            if (mFragment4 == null) {
                mFragment4 = new FragmentContent4();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment4.setArguments(args);
                transaction.add(R.id.container, mFragment4, LIST_TAG4);
            } else {
                transaction.show(mFragment4);
                mFragment4.getCate(tag, isrefresh);
            }
        } else if (tag.equalsIgnoreCase(id5)) {
            if (mFragment5 == null) {
                mFragment5 = new FragmentContent5();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment5.setArguments(args);
                transaction.add(R.id.container, mFragment5, LIST_TAG5);
            } else {
                transaction.show(mFragment5);
                mFragment5.getCate(tag, isrefresh);
            }
        }

        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mFragment1 != null) {
            transaction.hide(mFragment1);
        }
        if (mFragment2 != null) {
            transaction.hide(mFragment2);
        }
        if (mFragment3 != null) {
            transaction.hide(mFragment3);
        }
        if (mFragment4 != null) {
            transaction.hide(mFragment4);
        }
        if (mFragment5 != null) {
            transaction.hide(mFragment5);
        }
    }


}
