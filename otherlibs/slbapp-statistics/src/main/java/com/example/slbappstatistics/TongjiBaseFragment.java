package com.example.slbappstatistics;


import android.support.v4.app.Fragment;
import android.util.Log;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;


public class TongjiBaseFragment extends Fragment {
    private static final String TAG="BaseFragment";
    protected int apiCallType = 2;
    /**
    * 由于fragment使用场景比较多样，单单依靠OnResume/OnPause两个回调表示fragment Show/Hide是不准确的,比如：
    *场景一：
    *　　首页一个Activity承载多个Fragment Tab的情况，此时tab间切换并不会触发Fragment的OnResume/OnPause．触发的回调函数是onHiddenChanged(boolean hidden)
    *场景二：
    *　　一个ViewPager承载多个页面的Fragment时
    * 　　　a.当第一个Fragment1显示时，虽然第二个Fragment2此时尚未显示，但是Fragment2的OnResume却以及执行，处于resumed的状态．
    *　　　 b.ViewPager页面切换OnResume/OnPause/onHiddenChanged均未触发，触发的回调是setUserVisibleHint
    *　　此时判断Fragment　Show/Hide应该用setUserVisibleHint，而非OnResume/OnPause
    */


    public void setApiCallType(int callType){
        apiCallType = callType;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isResumed()) {
            Log.e(TAG,this.getClass().getCanonicalName()+" isVisibleToUser:"+isVisibleToUser);
            if(isVisibleToUser){
                JAnalyticsInterface.onPageStart(getActivity(),this.getClass().getCanonicalName());
            }else {
                JAnalyticsInterface.onPageEnd(getActivity(),this.getClass().getCanonicalName());
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG,this.getClass().getCanonicalName()+" onHiddenChanged:"+hidden);
        if(hidden){
            JAnalyticsInterface.onPageEnd(getActivity(),this.getClass().getCanonicalName());
        }else {
            JAnalyticsInterface.onPageStart(getActivity(),this.getClass().getCanonicalName());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden() && getUserVisibleHint()) {
            Log.e(TAG,this.getClass().getCanonicalName()+" onResume");
            JAnalyticsInterface.onPageStart(getActivity(),this.getClass().getCanonicalName());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isHidden() && getUserVisibleHint()) {
            Log.e(TAG,this.getClass().getCanonicalName()+" onPause");
            JAnalyticsInterface.onPageEnd(getActivity(),this.getClass().getCanonicalName());
        }
    }
}
