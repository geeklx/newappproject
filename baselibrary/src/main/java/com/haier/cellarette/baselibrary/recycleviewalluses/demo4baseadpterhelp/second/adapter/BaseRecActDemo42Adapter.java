package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.bean.BaseRecActDemo42Bean;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.provider.Style1Provider;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.provider.Style2Provider;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp.second.provider.Style3Provider;

import java.util.List;

public class BaseRecActDemo42Adapter extends MultipleItemRvAdapter<BaseRecActDemo42Bean, BaseViewHolder> {

    public static final int STYLE_ONE = 100;
    public static final int STYLE_TWO = 200;
    public static final int STYLE_THREE = 300;

    public BaseRecActDemo42Adapter(@Nullable List<BaseRecActDemo42Bean> data) {
        super(data);

        //构造函数若有传其他参数可以在调用finishInitialize()之前进行赋值，赋值给全局变量
        //这样getViewType()和registerItemProvider()方法中可以获取到传过来的值
        //getViewType()中可能因为某些业务逻辑，需要将某个值传递过来进行判断，返回对应的viewType
        //registerItemProvider()中可以将值传递给ItemProvider
        finishInitialize();

    }

    @Override
    protected int getViewType(BaseRecActDemo42Bean baseRecActDemo42Bean) {
        //根据实体类判断并返回对应的viewType，具体判断逻辑因业务不同，这里这是简单通过判断type属性
        if (baseRecActDemo42Bean.type == BaseRecActDemo42Bean.style1) {
            return STYLE_ONE;
        } else if (baseRecActDemo42Bean.type == BaseRecActDemo42Bean.style2) {
            return STYLE_TWO;
        } else if (baseRecActDemo42Bean.type == BaseRecActDemo42Bean.style3) {
            return STYLE_THREE;
        }
        return 0;
    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        mProviderDelegate.registerProvider(new Style1Provider());
        mProviderDelegate.registerProvider(new Style2Provider());
        mProviderDelegate.registerProvider(new Style3Provider());
    }
}
