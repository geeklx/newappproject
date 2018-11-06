package com.haier.index.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libglide37.glidenetpicpressnormal.SelectorUtil;
import com.haier.index.R;

public class FragmentContent6 extends BaseFragment {

    private String tablayoutId;
    private Context mContext;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;
    private TextView tv16;
    private TextView tv17;
    private TextView tv18;
    private TextView tv19;
    private TextView tv1_demo1;
    private ImageView iv1;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        mContext = getActivity();
//        Bundle arg = getArguments();
        if (getArguments() != null) {
            tablayoutId = getArguments().getString("tablayoutId");
        }
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tv1 = rootView.findViewById(R.id.tv_recycleviewgallery);
        tv2 = rootView.findViewById(R.id.tv_likebutton);
        tv3 = rootView.findViewById(R.id.tv_networkview);
        tv4 = rootView.findViewById(R.id.tv_handler);
        tv5 = rootView.findViewById(R.id.tv_recycleview);
        tv6 = rootView.findViewById(R.id.tv_greendao);
        tv7 = rootView.findViewById(R.id.tv_zuniscrollview);
        tv8 = rootView.findViewById(R.id.tv_usersdk);
        tv9 = rootView.findViewById(R.id.tv_hios);
        tv10 = rootView.findViewById(R.id.tv_caranim);
        tv11 = rootView.findViewById(R.id.tv_recycleviewalluses);
        tv12 = rootView.findViewById(R.id.tv_changelanguage);
        tv13 = rootView.findViewById(R.id.tv_scrollviewact);
        tv14 = rootView.findViewById(R.id.tv_anroomcrash);
        tv15 = rootView.findViewById(R.id.tv_MusicActivity);
        tv16 = rootView.findViewById(R.id.tv_uploadpic);
        tv17 = rootView.findViewById(R.id.tv_expandableview);
        tv18 = rootView.findViewById(R.id.tv_assetsMainActivity);
        tv19 = rootView.findViewById(R.id.tv_shoppingcar);
        tv1_demo1 = rootView.findViewById(R.id.tv1_demo1);
        iv1 = rootView.findViewById(R.id.iv_glidenetpicpressnormal);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        SelectorUtil.addSeletorFromNet(FragmentContent2.class, "http://pic2.ooopic.com/12/42/25/02bOOOPIC95_1024.jpg",
                "http://pic34.photophoto.cn/20150127/0006019093196381_b.jpg", iv1, 500, 400, getActivity());
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.RecycleViewGalleryActivity"));
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.likebutton"));
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.networkview"));
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.handler"));
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.RecycleViewMainActivity.act"));
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.GreenDaoAct.act"));
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.ZuniScrollViewAct.act"));
            }
        });
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.LoginMainActivity"));
            }
        });
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.ac.webview.DemoWebviewMainActivity"));
            }
        });
        tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.caranimation.act"));
            }
        });
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.recycleviewalluses.act"));
            }
        });
        tv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.changelanguage"));
            }
        });
        tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.ScrollViewAct"));
            }
        });
        tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.anroomcrash"));
            }
        });
        tv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.MusicActivity"));
            }
        });
        tv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.uploadpic"));
            }
        });
        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.phone.expandableview"));
            }
        });
        tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.AssetsMainActivity"));
            }
        });
        tv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.ShoppingCarActivity"));
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1_demo1.setVisibility(View.VISIBLE);
            }
        }, 10000);

        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment6;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {

        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
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

}
