package com.haier.index;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.haier.cellarette.baselibrary.baseactivitys.MyBaseActivity;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.index.adapter.RecycleAdapter;
import com.haier.index.domain.Biaoge_listBean;
import com.haier.index.fragment.FragmentContent1;
import com.haier.index.fragment.FragmentContent2;
import com.haier.index.fragment.FragmentContent4;
import com.haier.index.fragment.FragmentContent5;
import com.haier.index.fragment.FragmentContent6;
import com.haier.shopcommon.jeffery.shopcate.fragment.ShopCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class IndexMainActivity extends MyBaseActivity {

    private List<Biaoge_listBean> mList;
    private static final String LIST_TAG0 = "list0";
    private static final String LIST_TAG1 = "list1";
    private static final String LIST_TAG2 = "list2";
    private static final String LIST_TAG3 = "list3";
    private static final String LIST_TAG4 = "list4";
    private static final String LIST_TAG5 = "list5";
    private static final String LIST_TAG6 = "list6";
    //    private String id1 = "http://jiuzhidao.com/shop/index.php?act=index&op=index&screen=1";
    private String id1 = "http://jiuzhidao.com/wap/";
    private String id2 = "2";
    private String id3 = "3";
    private String id4 = "4";
    private String id5 = "5";
    private String id6 = "6";

    private void addList() {
        mList.add(new Biaoge_listBean(id1, "商城", R.drawable.control_pobiji_guoshu_normal, R.drawable.control_pobiji_guoshu_select, true));
        mList.add(new Biaoge_listBean(id2, "识酒", R.drawable.control_pobiji_jiangliao_normal, R.drawable.control_pobiji_jiangliao_select, false));
        mList.add(new Biaoge_listBean(id3, "分类", R.drawable.control_pobiji_lengyin_normal, R.drawable.control_pobiji_lengyin_select, false));
        mList.add(new Biaoge_listBean(id4, "绑酒", R.drawable.control_pobiji_mofen_normal, R.drawable.control_pobiji_mofen_select, false));
        mList.add(new Biaoge_listBean(id5, "我的", R.drawable.control_pobiji_reyin_normal, R.drawable.control_pobiji_reyin_select, false));
        mList.add(new Biaoge_listBean(id6, "框架用法", R.drawable.control_pobiji_lengyin_normal, R.drawable.control_pobiji_lengyin_select, false));
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
    private ShopCategoryFragment mFragment3; //
    private FragmentContent4 mFragment4; //
    private FragmentContent5 mFragment5; //
    private FragmentContent6 mFragment6; //


    private FragmentManager mFragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demomain;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState!=null){
            mFragment1 = (FragmentContent1) mFragmentManager.findFragmentByTag(LIST_TAG1);
            mFragment2 = (FragmentContent2) mFragmentManager.findFragmentByTag(LIST_TAG2);
            mFragment3 = (ShopCategoryFragment) mFragmentManager.findFragmentByTag(LIST_TAG3);
            mFragment4 = (FragmentContent4) mFragmentManager.findFragmentByTag(LIST_TAG4);
            mFragment5 = (FragmentContent5) mFragmentManager.findFragmentByTag(LIST_TAG5);
            mFragment6 = (FragmentContent6) mFragmentManager.findFragmentByTag(LIST_TAG6);
        }
        doNetWork();

    }

    /**
     * 设置皮肤bufen
     *
     * @param aa
     */
    private void pifu(String aa) {
//        int aa = new Random().nextInt(3);
        int color = R.color.oneKeyRepair;
        if (aa.equals(id1)) {
            color = R.color.oneKeyRepair;
        } else if (aa.equals(id2)) {
            color = R.color.search_blue;
        } else if (aa.equals(id3)) {
            color = R.color.e04832;
        } else if (aa.equals(id4)) {
            color = R.color.orange_red;
        } else if (aa.equals(id5)) {
            color = R.color.colorPrimary;
        } else if (aa.equals(id6)) {
            color = R.color.oneKeyRepair;
        }
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, color));

    }


    private void doNetWork() {
        //请求数据bufen
        mList = new ArrayList<Biaoge_listBean>();
        addList();

//        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
//        mLinearLayoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
//        recyclerView.setLayoutManager(mLinearLayoutManager1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, mList.size(), OrientationHelper.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        ShowLoadingUtil.showLoading(this, "", null);
        mAdapter.setContacts(mList);
        mAdapter.notifyDataSetChanged();
        //设置Fragmentbufen
        showFragment(id1, false);
    }

    private int current_pos = 0;

    private void onclick() {
        mAdapter.setOnItemClickLitener(new RecycleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击item
                current_pos = position;
                footer_onclick();

            }
        });
    }

    //点击item
    private void footer_onclick() {
        Biaoge_listBean model = (Biaoge_listBean) mAdapter.getItem(current_pos);
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

    private void findview() {
        recyclerView = findViewById(R.id.recycler_view1);

        mAdapter = new RecycleAdapter(this);
////        LinearLayoutManager mLinearLayoutManager1 = new LinearLayoutManager(this);
////        mLinearLayoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
////        recyclerView.setLayoutManager(mLinearLayoutManager1);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 5, OrientationHelper.VERTICAL, false));
//        recyclerView.setAdapter(mAdapter);
    }

    private String tag_ids;

    private void showFragment(String tag, boolean isrefresh) {
        tag_ids = tag;
        //pifubufen
//        pifu(tag);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);

        if (tag.equalsIgnoreCase("-1")) { //
//            if (mFragment1 == null) {
//                mFragment1 = new FragmentContent1();
//                transaction.add(R.id.container, mFragment1, LIST_TAG0);
//            } else {
//                transaction.show(mFragment1);
//                mFragment1.initData();
//            }
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
                mFragment3 = new ShopCategoryFragment();
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
        } else if (tag.equalsIgnoreCase(id6)) {
            if (mFragment6 == null) {
                mFragment6 = new FragmentContent6();
                Bundle args = new Bundle();
                args.putString("tablayoutId", tag);
                mFragment6.setArguments(args);
                transaction.add(R.id.container, mFragment6, LIST_TAG6);
            } else {
                transaction.show(mFragment6);
                mFragment6.getCate(tag, isrefresh);
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
        if (mFragment6 != null) {
            transaction.hide(mFragment6);
        }
    }

    private long exitTime;

//    @Override
//    public void onBackPressed() {
//        if (mDrawerLayout.isDrawerOpen(mLayout)) {
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//
//        } else {
////            mDrawerLayout.openDrawer(Gravity.START);
//            if (System.currentTimeMillis() - firstTime < 3000) {
//                super.onBackPressed();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MobileLifeApplication.closeApp();
//                    }
//                });
//            } else {
//                firstTime = System.currentTimeMillis();
//                ToastUtil.showToastLong(getString(R.string.main_close));
//            }
//        }
//        if ((System.currentTimeMillis() - exitTime) < 3000) {
//            super.onBackPressed();
//            new Handler().post(new Runnable() {
//                @Override
//                public void run() {
//                    BaseAppManager.getInstance().closeApp();
//                }
//            });
//        } else {
//            Toasty.warning(getApplicationContext(), "再次点击退出程序哟 ~").show();
//            exitTime = System.currentTimeMillis();
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        ShowLoadingUtil.onDestory();
        if (!tag_ids.equals(id1)) {
            //设置为选中
            clearList();
            Biaoge_listBean model = (Biaoge_listBean) mAdapter.getItem(0);
            model.setEnselect(true);
            mAdapter.setContacts(mList);
            mAdapter.notifyDataSetChanged();
            //切换Fragment
            showFragment(id1, false);
        } else {
            if ((System.currentTimeMillis() - exitTime) < 1500) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        BaseAppManager.getInstance().closeApp();

                    }
                });
            } else {
                Toasty.warning(getApplicationContext(), "再次点击退出程序哟 ~").show();
                exitTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void onDestroy() {
        ShowLoadingUtil.onDestory();
        super.onDestroy();
    }
}
