package com.example.slbappindex.fragment.fragment2.fragmentliebiao.provider;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.adapter.Fragment21ImgAdapter;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22VideoBean;

import java.io.File;

public class Fragment22VideoStyle3Provider extends BaseItemProvider<Fragment22VideoBean, BaseViewHolder> {

    @Override
    public int viewType() {
        return Fragment21ImgAdapter.STYLE_THREE;
    }

    @Override
    public int layout() {
        return R.layout.activity_rec_item_frag22_video;
    }

    @Override
    public void convert(BaseViewHolder helper, Fragment22VideoBean data, int position) {
        //加载图片
        File file = new File(data.getmBean().getUserAvatar());
        ImageView iv = helper.itemView.findViewById(R.id.iv_provider3);
        Glide.with(mContext).load(file).into(iv);
//        helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img1);
//        if (position % 2 == 0) {
//            helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img1);
//        } else {
//            helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img2);
//        }
        //显示视频播放按钮
        helper.setVisible(R.id.id_iv_video, data.getmBean().isRetweet());
        helper.setText(R.id.tv_provider3, data.getmBean().getUserName());
        helper.addOnClickListener(R.id.iv_provider3);
        helper.addOnClickListener(R.id.tv_provider3);
        helper.addOnLongClickListener(R.id.iv_provider3);
        helper.addOnLongClickListener(R.id.tv_provider3);
    }

//    @Override
//    public void onClick(BaseViewHolder helper, Fragment22VideoBean data, int position) {
//        if (helper.getChildClickViewIds().contains(R.id.iv)) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserAvatar()).show();
//        } else if (helper.getChildClickViewIds().contains(R.id.tv)) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserName()).show();
//        } else {
//        }
//
//    }

//    @Override
//    public boolean onLongClick(BaseViewHolder helper, Fragment22VideoBean data, int position) {
//        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
//        return true;
//    }
}
