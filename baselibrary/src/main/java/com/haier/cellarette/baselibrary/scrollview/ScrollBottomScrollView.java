package com.haier.cellarette.baselibrary.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class ScrollBottomScrollView extends ScrollView {

    private OnScrollBottomListener _listener;
    private int _calCount;

    public interface OnScrollBottomListener {
        void srollToBottom();
        void srollToTop();
        void srollToOther();
    }

    public void registerOnScrollViewScrollToBottom(OnScrollBottomListener l) {
        _listener = l;
    }

    public void unRegisterOnScrollViewScrollToBottom() {
        _listener = null;
    }

    public ScrollBottomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = this.getChildAt(0);
//        if (this.getHeight() + this.getScrollY() == view.getHeight()) {
//            _calCount++;
//            if (_calCount == 1) {
//                if (_listener != null) {
//                    _listener.srollToBottom();
//                }
//            }
//        } else {
//            _calCount = 0;
//        }
//        System.out.println("滑动到了 _calCount=" + _calCount);

        int scrollY = this.getScrollY();
        int height = this.getHeight();
        int scrollViewMeasuredHeight = view.getMeasuredHeight();
        System.out.println("滑动到了 scrollY=" + scrollY);
        System.out.println("滑动到了 height=" + height);
        System.out.println("滑动到了 scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);
        if (scrollY == 0) {
            System.out.println("滑动到了顶端 view.getScrollY()=" + scrollY);
            if (_listener != null) {
                _listener.srollToTop();
            }
        } else if ((scrollY + height) >= scrollViewMeasuredHeight - 20) {// 20是为了更平滑
            System.out.println("滑动到了底部 scrollY=" + scrollY);
            System.out.println("滑动到了底部 height=" + height);
            System.out.println("滑动到了底部 scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);
            if (_listener != null) {
                _listener.srollToBottom();
            }
        } else {
            //
            if (_listener != null) {
                _listener.srollToOther();
            }
        }
    }
}