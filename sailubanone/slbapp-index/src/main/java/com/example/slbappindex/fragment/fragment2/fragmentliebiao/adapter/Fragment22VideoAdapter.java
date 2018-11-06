package com.example.slbappindex.fragment.fragment2.fragmentliebiao.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22VideoBean;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.provider.Fragment22VideoStyle2Provider;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.provider.Fragment22VideoStyle3Provider;

import java.util.List;

public class Fragment22VideoAdapter extends MultipleItemRvAdapter<Fragment22VideoBean, BaseViewHolder> {

    public static final int STYLE_TWO = 200;
    public static final int STYLE_THREE = 300;

    public Fragment22VideoAdapter(@Nullable List<Fragment22VideoBean> data) {
        super(data);

        //构造函数若有传其他参数可以在调用finishInitialize()之前进行赋值，赋值给全局变量
        //这样getViewType()和registerItemProvider()方法中可以获取到传过来的值
        //getViewType()中可能因为某些业务逻辑，需要将某个值传递过来进行判断，返回对应的viewType
        //registerItemProvider()中可以将值传递给ItemProvider
        finishInitialize();

    }

    @Override
    protected int getViewType(Fragment22VideoBean Fragment22VideoBean) {
        //根据实体类判断并返回对应的viewType，具体判断逻辑因业务不同，这里这是简单通过判断type属性
        if (Fragment22VideoBean.type == com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22VideoBean.style2) {
            return STYLE_TWO;
        } else if (Fragment22VideoBean.type == com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22VideoBean.style3) {
            return STYLE_THREE;
        }
        return STYLE_TWO;
    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        mProviderDelegate.registerProvider(new Fragment22VideoStyle2Provider());
        mProviderDelegate.registerProvider(new Fragment22VideoStyle3Provider());
    }
}
