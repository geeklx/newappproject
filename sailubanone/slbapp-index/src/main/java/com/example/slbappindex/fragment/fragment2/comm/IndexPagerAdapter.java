package com.example.slbappindex.fragment.fragment2.comm;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

import java.util.Objects;

/**
 * 首页viewpager adapter
 */
public class IndexPagerAdapter extends PagerAdapter {

    private Context context;

    public IndexPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return Fragment2Factory.PAGE_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MyLogUtil.d(Fragment2Factory.PAGE_LAYOUT_ID + position);
        int layoutId = context.getResources().getIdentifier(Fragment2Factory.PAGE_LAYOUT_ID + position, "layout", Objects.requireNonNull(context).getPackageName());
        if (layoutId == 0) {
            throw new UnsupportedOperationException("layout not found!");
        }
        View itemLayout = LayoutInflater.from(context).inflate(layoutId, container, false);
        container.addView(itemLayout);
        return itemLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}