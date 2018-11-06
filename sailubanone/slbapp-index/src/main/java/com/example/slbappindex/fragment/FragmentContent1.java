package com.example.slbappindex.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.biz3slbappdemo1.bean.Fanslbdemo1Bean;
import com.example.biz3slbappdemo1.bean.Slbdemo1Bean;
import com.example.biz3slbappdemo1.presenter.Slbdemo1Presenter;
import com.example.biz3slbappdemo1.view.Slbdemo1View;
import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libutils.utilslib.jiami.Md5Utils;

public class FragmentContent1 extends BaseFragment implements Slbdemo1View {

    private String tablayoutId;
    private Context mContext;
    private TextView tv1;

    private Slbdemo1Presenter presenter = new Slbdemo1Presenter();


    @Override
    public void onDestroyView() {
        presenter.onDestory();
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
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        tv1 = rootView.findViewById(R.id.tv1);
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment1;
    }

    /**
     * 第一次进来加载bufen
     */
    public void initData() {

        Toasty.normal(getActivity(), "初始化内容" + tablayoutId).show();
        presenter.onCreate(this);

    }
    /**
     * 当切换底部的时候通知每个fragment切换的id是哪个bufen
     *
     * @param cateId
     */
    public void give_id(String cateId) {
//        Toasty.normal(getActivity(), "cateId=" + cateId + "下拉刷新啦").show();

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
        Slbdemo1Bean slbdemo1Bean = new Slbdemo1Bean();
        slbdemo1Bean.setSid("1111111");
        slbdemo1Bean.setUsername("mqh");
        slbdemo1Bean.setPassword(Md5Utils.get32Md5LowerCase("123456"));
        slbdemo1Bean.setMethod("save");
        slbdemo1Bean.setAction("req");
        slbdemo1Bean.setCmd("user");
        slbdemo1Bean.setMail("1196185392@qq.com");
        slbdemo1Bean.setTel("15137615080");
        slbdemo1Bean.setWeixin("mqh19911118");
        slbdemo1Bean.setRole("A");

        presenter.getSlbdemo1Data("sairobo", slbdemo1Bean);
        tv1.setText("");
    }

    @Override
    public void OnSuccess(Object fanslbdemo1Bean) {
        tv1.setText(fanslbdemo1Bean.toString());
    }

    @Override
    public void OnFail(String s) {
        tv1.setText(s);
    }
}
