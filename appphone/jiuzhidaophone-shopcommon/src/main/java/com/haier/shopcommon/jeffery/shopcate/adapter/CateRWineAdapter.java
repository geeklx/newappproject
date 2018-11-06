package com.haier.shopcommon.jeffery.shopcate.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haier.shopcommon.R;
import com.haier.shopcommon.jeffery.shopcate.bean.Genre;
import com.haier.shopcommon.jeffery.shopcate.bean.WineBean;
import com.haier.shopcommon.jeffery.view.expandablerecylerview.ExpandableRecyclerViewAdapter;
import com.haier.shopcommon.jeffery.view.expandablerecylerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by JefferyLeng on 2018/6/28.
 */
public class CateRWineAdapter extends ExpandableRecyclerViewAdapter<CateRWineViewHolder,CateRWineChildHolder> {

    public CateRWineAdapter(List<? extends ExpandableGroup> list) {
        super(list);
    }

    @Override
    public CateRWineViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_content_item_rwine,null);
//        initChildParentView(parent);
        return new CateRWineViewHolder(view);
    }

//    private void initChildParentView(ViewGroup parent) {
//        View childParentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_cate_content_rwinesub_item,null);
//        mRvCateSubContent = childParentView.findViewById(R.id.rv_cate_sub_content);
//        GridLayoutManager mLayoutManager = new GridLayoutManager(parent.getContext(),3,GridLayoutManager.VERTICAL,false);
//        //添加ItemDecoration，item之间的间隔
//        int leftRight = SizeUtils.dp2px(parent.getContext(), 10);
//        int topBottom = SizeUtils.dp2px(parent.getContext(), 10);
//        mRvCateSubContent.setLayoutManager(mLayoutManager);
//        SpacesItemDecoration mSpacesItemDecoration = new SpacesItemDecoration(leftRight, topBottom);
//        mRvCateSubContent.addItemDecoration(mSpacesItemDecoration);
//        mRvCateSubContent.setAdapter(mRWineContentItemAdapter);
//    }

    @Override
    public CateRWineChildHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_cate_content_ritem_rvitem,null);
        CateRWineChildHolder cateRWineChildHolder = new CateRWineChildHolder(parent.getContext(), view);
        return cateRWineChildHolder;
    }

    @Override
    public void onBindChildViewHolder(CateRWineChildHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        WineBean wineBean = ((Genre) group).getItems().get(childIndex);
        holder.setSubContentName(wineBean);
    }

    @Override
    public void onBindGroupViewHolder(CateRWineViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setContentName(group.getTitle());
    }

}
