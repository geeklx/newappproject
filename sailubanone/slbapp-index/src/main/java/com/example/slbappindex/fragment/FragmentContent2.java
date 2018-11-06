package com.example.slbappindex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.slbappindex.IndexMainActivity;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.example.slbappindex.fragment.fragment2.comm.IndexPagerAdapter;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.ShopItemBean;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.tablayout.TabSelectAdapter;
import com.haier.cellarette.baselibrary.tablayout.TabUtils;
import com.haier.cellarette.baselibrary.tablayout.UnAnimTabLayout;
import com.haier.cellarette.baselibrary.tablayout.ViewPagerChangeAdapter;
import com.haier.cellarette.baselibrary.tablayout.ViewPagerSlide;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.app.FragmentHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FragmentContent2 extends BaseFragment {

    private TextView tvCenter;
    private TextView tvRight;
    private UnAnimTabLayout tablayoutMy;
    private String tab_id;
    private ViewPagerSlide mViewPager;
    private String tablayoutId;
    private boolean once;
    private String id1 = "11";//tablayout
    private String id2 = "22";
    private String current_id = id1;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }

    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tvCenter = rootView.findViewById(R.id.tv_center);
        tvRight = rootView.findViewById(R.id.tv_right);
        tablayoutMy = rootView.findViewById(R.id.tablayout_my);
        mViewPager = rootView.findViewById(R.id.viewpager_my);
        tvCenter.setText("手机影像");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.normal(BaseApp.get(), "编辑全选").show();
                if (current_id.equals(id1)) {
                    //跳转网络图片demobufen
                    Fragment2Factory.getInstance(getActivity()).jump_img_net_yulan(getActivity(), 1);

                } else if (current_id.equals(id2)) {
                    //跳转本地视频复制demobufen
                    Fragment2Factory.getInstance(getActivity()).jump_img_net_yulan(getActivity(), 2);
                }

            }
        });
        initData();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment2;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        init_viewpager();
        init_tablayout();
        setupFragments();

    }

    private void init_viewpager() {
        mViewPager.setScroll(true);
        mViewPager.setOffscreenPageLimit(Fragment2Factory.PAGE_COUNT);
        mViewPager.setAdapter(new IndexPagerAdapter(getActivity()));
        //给ViewPager添加事件监听
        mViewPager.addOnPageChangeListener(new ViewPagerChangeAdapter() {
            @Override
            public void onPageSelected(int position) {
                // 此处不能做统计数据bufen

            }
        });
        mViewPager.setCurrentItem(Fragment2Factory.DEFAULT_PAGE_INDEX);//设置当前显示标签页为第一页
    }

    /**
     * 初始化首页fragments
     */
    private void setupFragments() {
        // 使用HierarchyChangeListener的目的是防止在viewpager的itemview还没有准备好就去inflateFragment
        // 带来的问题
        mViewPager.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                if (((ViewGroup) parent).getChildCount() < Fragment2Factory.PAGE_COUNT) {
                    return;
                }

                FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                SparseArrayCompat<Class<? extends Fragment>> array = Fragment2Factory.get();//一个版本模式bufen
                int size = array.size();
                Fragment item;
                for (int i = 0; i < size; i++) {
                    item = FragmentHelper.newFragment(array.valueAt(i), null);
                    ft.replace(array.keyAt(i), item, item.getClass().getName());
                }
                ft.commitAllowingStateLoss();
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {

            }
        });


    }

    //手动设置ViewPager要显示的视图
    public void changeView(int desTab) {
        mViewPager.setCurrentItem(desTab, true);
    }

    private void init_tablayout() {
        List<ShopItemBean> mDataTablayout = new ArrayList<>();
        mDataTablayout.add(new ShopItemBean(id1, "图片"));
        mDataTablayout.add(new ShopItemBean(id2, "录像"));
        tablayoutMy.setupWithViewPager(mViewPager);
        tablayoutMy.removeAllTabs();
        for (ShopItemBean item : mDataTablayout) {
            tablayoutMy.addTab(tablayoutMy.newTab()
                    .setTag(item.getTab_id()).setText(item.getTab_name()));
        }
        tablayoutMy.addOnTabSelectedListener(new TabSelectAdapter() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabUtils.tabSelect(tablayoutMy, tab);
                String tag = (String) tab.getTag();
                current_id = tag;
                // 这段代码如果单独用tablayout的时候 就需要 因为tablayout会自动执行
//                if (!once) {
//                    once = true;
//                    return;
//                }
                if (TextUtils.isEmpty(tag)) {
                    return;
                }
                showFragment(current_id);
//                hookForNoAnimator();
            }
        });
        tablayoutMy.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tablayoutMy, 60, 60);
            }
        });
    }

    private void showFragment(String tag) {
        if (tag.equals(id1)) {
            changeView(0);
            chuli_img();
        } else if (tag.equals(id2)) {
            changeView(1);
            chuli_video();
        }
    }


    /**
     * 第二次进来的底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            //切换后不下拉刷新但是执行传递信息操作bufen
            Fragment2Factory.getInstance(getActivity()).set_brc_fragment21and22_change(getActivity());
            return;
        }
        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        //推送入口bufen
        Intent intent = new Intent("hs.act.slbapp.JpushActivity");
//        intent.putExtra("type", Platform.ACTION_SHARE);//授权
        startActivity(intent);

    }

    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
        if (!cateId.equals(IndexMainActivity.id2)) {
            //当切换到其他的时候注销广播
//            LocalBroadcastManagers.getInstance(BaseApp.get()).unregisterReceiver(mMessageReceiver);
        }
    }

//    private MessageReceiver2 mMessageReceiver;
//
//    public class MessageReceiver2 extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//                if (FragmentBroadcastUtil.BC_fragment21.equals(intent.getAction())) {
//                    // 刷新图片bufen
//
//                }
//                if (FragmentBroadcastUtil.BC_fragment22.equals(intent.getAction())) {
//                    // 刷新视频bufen
//
//                }
//            } catch (Exception e) {
//            }
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        LocalBroadcastManagers.getInstance(BaseApp.get()).unregisterReceiver(mMessageReceiver);
    }

    /**
     * --------------------------------业务逻辑bufen-------------------------------
     */

    /**
     * 处理图片刷新bufen
     */
    private void chuli_img() {
//        Toasty.normal(BaseApp.get(), "图片刷新了").show();

    }

    /**
     * 处理视频刷新bufen
     */
    private void chuli_video() {
//        Toasty.normal(BaseApp.get(), "视频刷新了").show();

    }

}
