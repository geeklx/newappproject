package com.haier.cellarette.jiuzhidao_demo.shouye;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.toasts.ToastUtil;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.jiuzhidao_demo.R;

public class FragmentContent3 extends BaseFragment {

    private String tablayoutId;
    private Context mContext;
    private TextView tv1;

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
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment3;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {
//        doNetWork_toubu();
//        which_page = 1;
//        //categoryId
//        doNetWorkContent2(which_page);
        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
    }


    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {
//        doNetWork_toubu();
//        GAdaptor = new RecycleViewAdapter1(mContext);
//        recyclerView.setAdapter(GAdaptor);
//        which_page = 1;
//        doNetWorkContent2(which_page);
//        isTou_onclick = false;
//        Toasty.normal(getActivity(), "内容" + cateId).show();
        if (!isrefresh){
            return;
        }
        Toasty.normal(getActivity(), "下拉刷新啦").show();
    }

}
