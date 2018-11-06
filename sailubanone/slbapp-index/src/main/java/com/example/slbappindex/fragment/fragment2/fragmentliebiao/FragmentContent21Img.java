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
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.adapter.Fragment21ImgAdapter;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ImgBean;
import com.example.slbappindex.fragment.fragment2.utils.CopyAssetsToLujingUtils;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;

public class FragmentContent21Img extends BaseFragment {

    private String tablayoutId;
    private Context mContext;

    private RecyclerView mRecyclerView;
    private Fragment21ImgAdapter mAdapter;
    private List<Fragment21ImgBean> mList;
    private NiubiEmptyView niubiEmptyView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String aa = "";
    }

    private MessageReceiver21 mMessageReceiver;

    /**
     * 监听本地图片变化bufen
     */
    public class MessageReceiver21 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (Fragment2Factory.BC_fragment21.equals(intent.getAction())) {
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
        mMessageReceiver = new MessageReceiver21();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(Fragment2Factory.BC_fragment21);
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
        return R.layout.activity_fragment21;
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
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, OrientationHelper.VERTICAL, false));
        mAdapter = new Fragment21ImgAdapter(mList);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = mList.get(position).type;
                if (type == Fragment21ImgBean.style2) {
                    return 3;
                } else if (type == Fragment21ImgBean.style3) {
                    return 1;
                } else {
                    return 3;
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Fragment21ImgBean addressBean = mList.get(position);
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
                                                    Fragment21ImgBean addressBean_tou = mAdapter.getData().get(position - 1);
                                                    Fragment21ImgBean addressBean = mAdapter.getData().get(position);
                                                    if (addressBean_tou.type == Fragment21ImgBean.style2) {
                                                        if (mAdapter.getData().size() > position + 1) {
                                                            // 够用
                                                            Fragment21ImgBean addressBean_wei = mAdapter.getData().get(position + 1);
                                                            if (addressBean_wei.type == Fragment21ImgBean.style2) {
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
//        mList = Fragment21ImgBean.getMultipleItemData(len);
        mList = CopyAssetsToLujingUtils.getInstance(getActivity()).getMultipleItemDataUrlImg();
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
    private void del_loc_pic(Fragment21ImgBean addressBean) {
        if (FileUtils.deleteFile(new File(addressBean.getmBean().getUserAvatar()))) {
            Toasty.normal(getActivity(), "已删除" + addressBean.getmBean().getUserAvatar()).show();
            return;
        }
    }


    private List<ImageInfo> imageInfoList;

    /**
     * 网络大图预览bufen
     *
     * @param addressBean
     */
    private void jump_img_loc_yulan(Fragment21ImgBean addressBean) {
        // 本地图片：将原图和缩略图地址传一样的即可。
        imageInfoList = new ArrayList<>();
        imageInfoList.clear();
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setOriginUrl(addressBean.getmBean().getUserAvatar());
        imageInfo.setThumbnailUrl(addressBean.getmBean().getUserAvatar());
        imageInfoList.add(imageInfo);
        // 打开本地图片bufen
        ImagePreview
                .getInstance()
                .setContext(getActivity())
                .setIndex(0)
                .setImageInfoList(imageInfoList)
                .setShowDownButton(true)
                .setLoadStrategy(ImagePreview.LoadStrategy.Default)
                .setFolderName(ConstantsUtils.gen_img)
                .setScaleLevel(1, 3, 8)
                .setZoomTransitionDuration(500)
                .setShowCloseButton(true)
                .setEnableDragClose(true)// 是否启用上拉/下拉关闭，默认不启用
                .setEnableClickClose(true)// 是否启用点击图片关闭，默认启用
                .start();
        //打开缓存中的图片 bufen
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        FileProvider7.setIntentDataAndType(getActivity(), intent, "image/*", new File(addressBean.getmBean().getUserAvatar()), true);
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
