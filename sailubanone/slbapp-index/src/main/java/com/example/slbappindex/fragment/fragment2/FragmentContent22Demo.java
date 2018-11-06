package com.example.slbappindex.fragment.fragment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.ShopItemBean;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo3baseadpterhelp.BaseRecActDemo3Adapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo3baseadpterhelp.BaseRecActDemo3BeanHead;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;

import java.util.ArrayList;
import java.util.List;

public class FragmentContent22Demo extends BaseFragment  {

    private String tablayoutId;
    private Context mContext;
    private ImageView iv1;
    /**
     * 监听本地图片变化bufen
     *
     */
    private MessageReceiver22 mMessageReceiver;
    /**
     * 监听本地图片变化bufen
     */
    public class MessageReceiver22 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (Fragment2Factory.BC_fragment22.equals(intent.getAction())) {
                    // 刷新图片bufen
                    mList = ShopItemBean.getSampleData1();
                    mAdapter.setNewData(mList);
                    mAdapter.notifyDataSetChanged();
                }

            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onDestroyView() {
        LocalBroadcastManagers.getInstance(BaseApp.get()).unregisterReceiver(mMessageReceiver);
        super.onDestroyView();

    }
    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
        mMessageReceiver = new MessageReceiver22();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(Fragment2Factory.BC_fragment22);
        LocalBroadcastManagers.getInstance(BaseApp.get()).registerReceiver(mMessageReceiver, filter);
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
//        iv1 = rootView.findViewById(R.id.iv1);
        mRecyclerView = rootView.findViewById(R.id.recycler_view1);
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment22;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {

//        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        mList = new ArrayList<>();
        mList = ShopItemBean.getSampleData2();
        donetwork(mRecyclerView, getActivity());
    }


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {

        if (!isrefresh) {
            return;
        }
        Toasty.normal(getActivity(), "下拉刷新啦").show();
    }

    private RecyclerView mRecyclerView;
    private BaseRecActDemo3Adapter mAdapter;
    private List<BaseRecActDemo3BeanHead> mList;

    private void donetwork(RecyclerView mRecyclerView, Context context) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2, OrientationHelper.VERTICAL, false));

//        mAdapter.setNewData(mList);
        mAdapter = new BaseRecActDemo3Adapter(R.layout.baserecactdemo3_item, R.layout.baserecactdemo3_item_head, mList);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                BaseRecActDemo3BeanHead addressBean = mList.get(position);
                if (addressBean.isHeader) {
                    //头部点击bufen
                    Toasty.normal(BaseApp.get(), addressBean.header).show();
                } else {
                    //内容item点击bufen
                    Toasty.normal(BaseApp.get(), addressBean.t.getName() + " " + addressBean.t.getImg()).show();
                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo3BeanHead addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.demo3more) {
                    Toasty.normal(BaseApp.get(), "more>").show();
                } else if (i == R.id.tv) {
                    Toasty.normal(BaseApp.get(), addressBean.t.getName()).show();
                }
            }
        });
    }

}
