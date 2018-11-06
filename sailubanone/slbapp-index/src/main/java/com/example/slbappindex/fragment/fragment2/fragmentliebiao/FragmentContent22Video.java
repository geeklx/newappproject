package com.example.slbappindex.fragment.fragment2.fragmentliebiao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.FileUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.adapter.Fragment22VideoAdapter;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22VideoBean;
import com.example.slbappindex.fragment.fragment2.utils.CopyAssetsToLujingUtils;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;
import cn.jzvd.JZDataSource;
import cn.jzvd.JzvdStd;

public class FragmentContent22Video extends BaseFragment {

    private String tablayoutId;
    private Context mContext;

    private RecyclerView mRecyclerView;
    private Fragment22VideoAdapter mAdapter;
    private List<Fragment22VideoBean> mList;
    private NiubiEmptyView niubiEmptyView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String aa = "";
    }

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
                    set_datas(3);
                }

            } catch (Exception e) {
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManagers.getInstance(BaseApp.get()).unregisterReceiver(mMessageReceiver);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

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

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(getLayoutId(),null);
//        mRecyclerView = rootView.findViewById(R.id.recycler_view1);
//        container.addView(rootView);
//        initData();
//        return rootView;
//
//    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
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
        donetwork(mRecyclerView, getActivity());
        niubiEmptyView = new NiubiEmptyView();
        niubiEmptyView.bind(getActivity(), mRecyclerView, mAdapter);
        niubiEmptyView.setRetry(new NiubiEmptyView.RetryListener() {
            @Override
            public void retry() {
                refresh();
            }
        });
        set_data();
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

    private void donetwork(RecyclerView mRecyclerView, Context context) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, OrientationHelper.VERTICAL, false));
        mAdapter = new Fragment22VideoAdapter(mList);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = mList.get(position).type;
                if (type == Fragment22VideoBean.style2) {
                    return 2;
                } else if (type == Fragment22VideoBean.style3) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Fragment22VideoBean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.tv1_provider2) {
                    Toasty.normal(BaseApp.get(), position + "item click=" + addressBean.getmBean().getText()).show();
                    //跳转网络图片demobufen
//                    jump_img_net_yulan(addressBean);
                } else if (i == R.id.iv_provider3) {
                    Toasty.normal(BaseApp.get(), position + "item click=" + addressBean.getmBean().getUserAvatar()).show();
                    //跳转本地图片demobufen
                    jump_img_loc_yulan(addressBean);
                } else if (i == R.id.tv_provider3) {
                    Toasty.normal(BaseApp.get(), position + "item click=" + addressBean.getmBean().getUserName()).show();
                } else {
                }
            }
        });
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {
                int i = view.getId();
                if (i == R.id.iv_provider3) {
                    final AlertView dialog = new AlertView(getActivity(), "温馨提示", "您确定要删除此条数据",
                            "删除", "确定");
                    dialog.show();
                    dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                                @Override
                                                public void doLeft() {
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void doRight() {
                                                    dialog.dismiss();
                                                    // 此处逻辑看readme
                                                    Fragment22VideoBean addressBean_tou = mAdapter.getData().get(position - 1);
                                                    Fragment22VideoBean addressBean = mAdapter.getData().get(position);
                                                    if (addressBean_tou.type == Fragment22VideoBean.style2) {
                                                        if (mAdapter.getData().size() > position + 1) {
                                                            // 够用
                                                            Fragment22VideoBean addressBean_wei = mAdapter.getData().get(position + 1);
                                                            if (addressBean_wei.type == Fragment22VideoBean.style2) {
                                                                mAdapter.getData().remove(addressBean_tou);
                                                                mAdapter.notifyItemRemoved(position - 1);
                                                            }
                                                        } else {
                                                            // 不够用
                                                            mAdapter.getData().remove(addressBean_tou);
                                                            mAdapter.notifyItemRemoved(position - 1);
                                                        }
                                                    }
                                                    mAdapter.getData().remove(addressBean);
                                                    mAdapter.notifyItemRemoved(position);
                                                    //删除本地路径下的图片bufen
                                                    del_loc_pic(addressBean);
                                                    if (mAdapter.getData().size() == 2) {
                                                        //改变视图bufen
                                                        set_emptyview();
                                                    }
                                                }
                                            }
                    );
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
//                            .setTitle("提示!")
//                            .setMessage("您确定要删除此条数据")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            })
//                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    builder.show();

                }
                return true;
            }
        });


    }


    /**
     * 设置为空布局bufen
     */
    private void set_emptyview() {
        niubiEmptyView.nodata();
//        niubiEmptyView.errornet("网络连接失败，请检查网络后点击重试!");
    }

    private void set_datas(int len) {
//        mList = Fragment22VideoBean.getMultipleItemData(len);
        mList = CopyAssetsToLujingUtils.getInstance(getActivity()).getMultipleItemDataUrlVideo();
        mAdapter.setNewData(mList);
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
//            mAdapter.setEmptyView(getView());
            niubiEmptyView.nodata();
        }
    }


    /**
     * ---------------------业务逻辑bufen----------------------
     */

    /**
     * 点击图片 删除本地路径下的图片
     *
     * @param addressBean
     */
    private void del_loc_pic(Fragment22VideoBean addressBean) {
        if (FileUtils.deleteFile(new File(addressBean.getmBean().getUserAvatar()))) {
            Toasty.normal(getActivity(), "已删除" + addressBean.getmBean().getUserAvatar()).show();
            return;
        }
    }


    /**
     * 网络大图预览bufen
     *
     * @param addressBean
     */
    private void jump_img_loc_yulan(Fragment22VideoBean addressBean) {
        // 打开assets下复制到本地视频
        JzvdStd.startFullscreen(getActivity(),JzvdStd.class,addressBean.getmBean().getUserAvatar(),"本地视频");

        //打开本地中的视频 bufen
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("oneshot", 0);
//        intent.putExtra("configchange", 0);
//        FileProvider7.setIntentDataAndType(getActivity(), intent, "video/*", new File(addressBean.getmBean().getUserAvatar()), true);
//        startActivity(intent);
    }

    /**
     * 给列表赋值bufen
     */
    private void set_data() {
        niubiEmptyView.loading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                set_datas(1);
            }
        }, 2000);

    }

    /**
     * 暂无数据或者断网重试
     */
    private void refresh() {
        niubiEmptyView.loading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                set_datas(5);
            }
        }, 2000);
    }

}
