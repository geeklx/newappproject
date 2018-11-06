package com.haier.cellarette.baselibrary.smartbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haier.cellarette.baselibrary.R;

public class SmartBar extends LinearLayout {

    private ImageView mHomeView;
    private ImageView mBackView;
    private int mIconPadding;

    /**
     * show home button
     */
    public static final int HOME = 1;
    /**
     * show back button
     */
    public static final int BACK = 2;
    /**
     * hide all button
     */
    public static final int NONE = 0;

    private int mShowFlags;

    public SmartBar(Context context) {
        this(context, null, 0);
    }

    public SmartBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SmartBar, defStyleAttr, 0);
        int homeRes = ta.getResourceId(R.styleable.SmartBar_home_icon, R.drawable.smartbar_home_icon);
        int backRes = ta.getResourceId(R.styleable.SmartBar_back_icon, R.drawable.smartbar_back_icon);
        mIconPadding = ta.getDimensionPixelSize(R.styleable.SmartBar_icon_padding, 0);
        ta.recycle();

        init(homeRes, backRes);
        // default show home and back
        show(HOME|BACK);
    }

    private void init(int homeRes, int backRes) {
        int size = (int) getResources().getDimension(R.dimen.x81);
        LayoutParams homeParams = new LayoutParams(size, size);
        mHomeView = new ImageView(getContext());
        mHomeView.setScaleType(ImageView.ScaleType.FIT_XY);
        mHomeView.setAdjustViewBounds(true);
        mHomeView.setImageResource(homeRes);
        addView(mHomeView, homeParams);

        LayoutParams backParams = new LayoutParams(size, size);
        mBackView = new ImageView(getContext());
        mBackView.setScaleType(ImageView.ScaleType.FIT_XY);
        mBackView.setAdjustViewBounds(true);
        mBackView.setImageResource(backRes);
        addView(mBackView, backParams);

        mHomeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) { mListener.onHomePressed();}
            }
        });

        mBackView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) { mListener.onBackPressed();}
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHomeView.setVisibility(isHomeShown() ? View.VISIBLE : View.GONE);

        if (isBackShown()) {
            mBackView.setVisibility(View.VISIBLE);
            getParams(mBackView).leftMargin = isHomeShown() ? mIconPadding : 0;
        }else {
            mBackView.setVisibility(View.GONE);
            getParams(mBackView).leftMargin = 0;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private LayoutParams getParams(View childView) {
        return (LayoutParams) childView.getLayoutParams();
    }

    public boolean isHomeShown() {
        return (mShowFlags & HOME) == HOME;
    }

    public boolean isBackShown() {
        return (mShowFlags & BACK) == BACK;
    }

    public void show(int flag) {
        mShowFlags = flag;
        requestLayout();
    }

    private OnKeyListener mListener;

    public void setOnKeyListener(OnKeyListener li) {
        mListener = li;
    }

    public interface OnKeyListener {
        void onBackPressed();
        void onHomePressed();
    }
}
