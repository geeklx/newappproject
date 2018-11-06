package com.example.slbappindex.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.CustomImageView;

public class FragmentContent4 extends BaseFragment implements View.OnClickListener {

    private String tablayoutId;
    private Context mContext;
    private CustomImageView ivCenter;
    private TextView tv1;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private Button btnSure;
    private Button btnCancel;



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
//        iv1 = rootView.findViewById(R.id.iv1);
        ivCenter = rootView.findViewById(R.id.iv_center);
        ivCenter.setOnClickListener(this);
        tv1 = rootView.findViewById(R.id.tv1);
        rl1 = rootView.findViewById(R.id.rl1);
        rl1.setOnClickListener(this);
        rl2 = rootView.findViewById(R.id.rl2);
        rl2.setOnClickListener(this);
        rl3 = rootView.findViewById(R.id.rl3);
        rl3.setOnClickListener(this);
        btnSure = rootView.findViewById(R.id.btn_sure);
        btnSure.setOnClickListener(this);
        btnCancel = rootView.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        initData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4;
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
        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();
    }

    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_center) {
            //上传头像bufen
            Toasty.normal(getActivity(),"上传头像").show();
        } else if (i == R.id.rl1) {
            // 设置
            startActivity(new Intent("hs.act.slbapp.Fragment4SettingActivity"));
        } else if (i == R.id.rl2) {
            // 反馈
            startActivity(new Intent("hs.act.slbapp.Fragment4FankuiActivity"));
        } else if (i == R.id.rl3) {
            // 关于
            startActivity(new Intent("hs.act.slbapp.Fragment4AboutActivity"));
        } else if (i == R.id.btn_sure) {
            // 退出登录
//            startActivity(new Intent(""));// 请求接口成功后跳转到登录页面

        } else if (i == R.id.btn_cancel) {
            // 关闭应用
            BaseAppManager.getInstance().closeApp();
        } else {
        }
    }
}
