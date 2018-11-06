package com.haier.cellarette.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LxLinearLayout extends LinearLayout {

    private boolean itouch=true;

    public LxLinearLayout(Context context) {
        super(context);
    }

    public LxLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LxLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return itouch;
    }

    public void setTouch(boolean is){
        itouch = is;
    }
}
