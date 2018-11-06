package com.haier.cellarette.baselibrary.recycleviewalluses.demo7baseadpterhelp;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.databinding.ActivityRecycleviewallsusesDemo7ItemBinding;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo7baseadpterhelp.base.BaseDataBindingAdapter;

public class BaseRecActDemo7Adapter extends BaseDataBindingAdapter<BaseRecActDemo7BeanMsg, ActivityRecycleviewallsusesDemo7ItemBinding> {

    public BaseRecActDemo7Adapter() {
        super(R.layout.activity_recycleviewallsuses_demo7_item, null);
    }

    @Override
    protected void convert(ActivityRecycleviewallsusesDemo7ItemBinding binding, BaseRecActDemo7BeanMsg item) {
        binding.setBean7(item);
        binding.setOnclick7(BaseRecActDemo7BeanMsgPresenter.getInstance());
    }
}
