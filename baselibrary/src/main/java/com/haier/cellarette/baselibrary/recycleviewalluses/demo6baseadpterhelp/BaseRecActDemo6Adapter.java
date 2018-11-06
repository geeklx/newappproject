package com.haier.cellarette.baselibrary.recycleviewalluses.demo6baseadpterhelp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.cellarette.baselibrary.BR;
import com.haier.cellarette.baselibrary.R;

import java.util.List;


public class BaseRecActDemo6Adapter extends BaseQuickAdapter<BaseRecActDemo6BeanMsg, BaseRecActDemo6Adapter.MyViewHolder> {

    public BaseRecActDemo6Adapter(int layoutResId, @Nullable List<BaseRecActDemo6BeanMsg> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MyViewHolder helper, BaseRecActDemo6BeanMsg item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.bean6, item);
        binding.setVariable(BR.onclick6, BaseRecActDemo6BeanMsgPresenter.getInstance());
        binding.executePendingBindings();
        switch (helper.getLayoutPosition() %
                2) {
            case 0:
                helper.setImageResource(R.id.iv, R.drawable.ic_zhaoliying);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.drawable.img01);
                break;
        }
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public static class MyViewHolder extends BaseViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}
