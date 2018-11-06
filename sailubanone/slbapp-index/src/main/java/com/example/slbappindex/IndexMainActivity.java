package com.example.slbappindex;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.slbappindex.adapter.RecycleAdapter;
import com.example.slbappindex.domain.Biaoge_listBean;
import com.example.slbappindex.fragment.FragmentContent1;
import com.example.slbappindex.fragment.FragmentContent2;
import com.example.slbappindex.fragment.FragmentContent3;
import com.example.slbappindex.fragment.FragmentContent4;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.example.slbappindex.fragment.fragment2.utils.DownloadServiceMp4;
import com.haier.cellarette.baselibrary.baseactivitys.MyBaseActivity;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.download.DownLoadDialog;
import com.haier.cellarette.baselibrary.loading.ShowLoadingUtil;
import com.haier.cellarette.baselibrary.statusbar.StatusBarUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.jzvd.Jzvd;

public class IndexMainActivity extends MyBaseActivity implements Handler.Callback {


    private List<Biaoge_listBean> mList;
    private static final String LIST_TAG0 = "list0";
    private static final String LIST_TAG1 = "list1";
    private static final String LIST_TAG2 = "list2";
    private static final String LIST_TAG3 = "list3";
    private static final String LIST_TAG4 = "list4";
    //    private String id1 = "http://jiuzhidao.com/shop/index.php?act=index&op=index&screen=1";
    public static final String id1 = "http://jiuzhidao.com/wap/";
    public static final String id2 = "2";
    public static final String id3 = "3";
    public static final String id4 = "4";

    private void addList() {
        mList.add(new Biaoge_listBean(id1, "设备", R.drawable.control_pobiji_guoshu_normal, R.drawable.control_pobiji_guoshu_select, true));
        mList.add(new Biaoge_listBean(id2, "手机影像", R.drawable.control_pobiji_jiangliao_normal, R.drawable.control_pobiji_jiangliao_select, false));
        mList.add(new Biaoge_listBean(id3, "演示点", R.drawable.control_pobiji_lengyin_normal, R.drawable.control_pobiji_lengyin_select, false));
        mList.add(new Biaoge_listBean(id4, "我的", R.drawable.control_pobiji_mofen_normal, R.drawable.control_pobiji_mofen_select, false));
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
        if (savedInstanceState != null) {
            mFragment1 = (FragmentContent1) mFragmentManager.findFragmentByTag(LIST_TAG1);
            mFragment2 = (FragmentContent2) mFragmentManager.findFragmentByTag(LIST_TAG2);
            mFragment3 = (FragmentContent3) mFragmentManager.findFragmentByTag(LIST_TAG3);
            mFragment4 = (FragmentContent4) mFragmentManager.findFragmentByTag(LIST_TAG4);
        }
        doNetWork();
        configTuisong();
        xiazai_jindutiao();
    }


    private void configTuisong() {
        JPushInterface.onResume(this);
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
        }

        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mFragment1 != null) {
            transaction.hide(mFragment1);
            mFragment1.give_id(tag_ids);
        }
        if (mFragment2 != null) {
            transaction.hide(mFragment2);
            mFragment2.give_id(tag_ids);
        }
        if (mFragment3 != null) {
            transaction.hide(mFragment3);
            mFragment3.give_id(tag_ids);
        }
        if (mFragment4 != null) {
            transaction.hide(mFragment4);
            mFragment4.give_id(tag_ids);
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
        if (Jzvd.backPress()) {
            return;
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        if (TextUtils.equals(tag_ids, id2)) {
            Fragment2Factory.getInstance(this).set_brc_fragment21and22_change(this);
        }
        Jzvd.releaseAllVideos();
    }

    /**
     * -----------------下载进度条bufen---------------------
     *
     * @param message
     * @return
     */
    private DownLoadDialog customDialog;

    private void xiazai_jindutiao() {
        customDialog = new DownLoadDialog(this);
        DownloadServiceMp4.setUIHandler(new Handler(this));
    }

    int i = 0;
    @Override
    public boolean handleMessage(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case DownloadServiceMp4.WHAT_DOWNLOAD_FINISHED:
                    if (TextUtils.equals(tag_ids, id2)) {
                        Fragment2Factory.getInstance(this).set_brc_fragment21and22_change(this);
                    }
                    customDialog.dismiss();
                    break;
                case DownloadServiceMp4.WHAT_DOWNLOAD_STARTED:
                    int progress = (int) msg.obj;
                    // 更新进度
                    MyLogUtil.e("ssssssssssss",customDialog.isShowing()+"");
                    if (customDialog!=null&&!customDialog.isShowing()){
                        customDialog.show();
                    }
                    customDialog.openDialog(progress);
                    if (progress >= 100) {
                        customDialog.closeDialog();
                    }
                    break;
            }
        }
        return true;
    }

}
