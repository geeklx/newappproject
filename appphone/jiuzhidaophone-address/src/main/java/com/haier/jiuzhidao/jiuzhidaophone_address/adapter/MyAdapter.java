package com.haier.jiuzhidao.jiuzhidaophone_address.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.jiuzhidao.jiuzhidaophone_address.R;
import com.haier.jiuzhidao.jiuzhidaophone_address.bean.AddressBean;

import java.util.List;


public class MyAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {

    public MyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        String sex = item.getSex() == 1 ? "男" : "女";

        if (item.getFlag()!=null&&!item.getFlag().equals("")&&!item.getFlag().equals(" ")) {
            helper.setText(R.id.tv_location, item.getFlag());
            helper.setVisible(R.id.tv_location,true);
        }else{
            helper.setVisible(R.id.tv_location,false);
        }
        helper.setText(R.id.tv_address, item.getAddress()).
                setText(R.id.tv_address, item.getAddress()).
                setText(R.id.tv_address2, item.getDoornum()).
                setText(R.id.tv_person, item.getName() + "(" + sex + ")" + "  " + item.getNum());

        helper.addOnClickListener(R.id.tv_address2);
        helper.addOnClickListener(R.id.tv_person);
    }
}