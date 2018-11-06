package com.haier.shopcommon.jeffery.view.searchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haier.cellarette.baselibrary.size.SizeUtils;
import com.haier.shopcommon.R;

/**
 * 搜索框view
 *
 * @author Jeffery Leng
 * @date 2018/6/11
 * @email JefferyLeng@guider.cc
 */
public class CommonSearchView extends LinearLayout {

    private Context context;
    private EditText et_search;
    private LinearLayout search_block;
    private ImageView searchBack;
    private ImageView mIvRightPic;

    private ICallBack mCallBack;// 搜索按键回调接口
    private BackCallBack mBackCallBack; // 返回按键回调接口
    private EditCallBack mEditCallBack;
    private SearchRightIconCallBack mSearchRightIconCallBack;

    // 自定义属性设置
    // 1. 搜索字体属性设置：大小、颜色 & 默认提示
    private Float textSizeSearch;
    private int textColorSearch;
    private String textHintSearch;

    // 2. 搜索框设置：高度 & 颜色
    private int searchBlockHeight;
    private int searchBlockColor;
    private int mLeftPic;
    private int mRightPic;

    public CommonSearchView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CommonSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(context, attrs);
        init();
    }

    public CommonSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Search_View_common);

        // 搜索框字体大小（dp）
        textSizeSearch = typedArray.getDimension(R.styleable.Search_View_common_textSizeSearch, SizeUtils.dp2px(context, 20));

        // 搜索框字体颜色（使用十六进制代码，如#333、#8e8e8e）
        int defaultColor = context.getResources().getColor(R.color.colorText); // 默认颜色 = 灰色
        textColorSearch = typedArray.getColor(R.styleable.Search_View_common_textColorSearch, defaultColor);

        // 搜索框提示内容（String）
        textHintSearch = typedArray.getString(R.styleable.Search_View_common_textHintSearch);

        // 搜索框高度
        searchBlockHeight = typedArray.getInteger(R.styleable.Search_View_common_searchBlockHeight, SizeUtils.dp2px(context, 150));

        // 搜索框颜色
        int defaultColor2 = context.getResources().getColor(R.color.colorDefault); // 默认颜色 = 白色
        searchBlockColor = typedArray.getColor(R.styleable.Search_View_common_searchBlockColor, defaultColor2);

        mLeftPic = typedArray.getResourceId(R.styleable.Search_View_common_searchLeftPic, R.drawable.shop_search_back_icon);
        mRightPic = typedArray.getResourceId(R.styleable.Search_View_common_searchRightPic, R.drawable.shop_search_back_icon);


        // 释放资源
        typedArray.recycle();
    }

    private void init() {
        initView();

        mIvRightPic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSearchRightIconCallBack != null) {
                    mSearchRightIconCallBack.onIconClick();
                }
            }
        });



        /**
         * 监听输入键盘更换后的搜索按键
         * 调用时刻：点击键盘上的搜索键时
         */
        et_search.setFocusable(false);
        et_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditCallBack != null) {
                    mEditCallBack.jumpToSearch();
                }
            }
        });


        /**
         * 搜索框的文本变化实时监听
         */
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            // 输入文本后调用该方法
            @Override
            public void afterTextChanged(Editable s) {
                // 注：若搜索框为空,则模糊搜索空字符 = 显示所有的搜索历史
                String tempName = et_search.getText().toString();

            }
        });


        /**
         * 点击返回按键后的事件
         */
        searchBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注：由于返回需求会根据自身情况不同而不同，所以具体逻辑由开发者自己实现，此处仅留出接口
                if (!(mBackCallBack == null)) {
                    mBackCallBack.BackAciton();
                }

                //根据输入的内容模糊查询商品，并跳转到另一个界面，这个根据需求实现
                Toast.makeText(context, "返回到上一页", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void verifySearchStr() {
        String searchResult = "";
        if (!(mCallBack == null)) {
            if (TextUtils.isEmpty(et_search.getText())) {
                searchResult = et_search.getHint().toString();
            } else {
                searchResult = et_search.getText().toString();
            }
            mCallBack.SearchAciton(searchResult);
            et_search.setText("");
        }
        Toast.makeText(context, "搜索商品：" + searchResult, Toast.LENGTH_SHORT).show();
    }


    private void initView() {

        // 1. 绑定R.layout.search_layout作为搜索框的xml文件
        LayoutInflater.from(context).inflate(R.layout.search_view_layout, this);

        // 2. 绑定搜索框EditText
        et_search = findViewById(R.id.et_search);
        et_search.setTextSize(textSizeSearch);
        et_search.setTextColor(textColorSearch);
        et_search.setHint(textHintSearch);
//        et_search.setText(textHintSearch);

        // 3. 搜索框背景颜色
        search_block = findViewById(R.id.search_block);
        LayoutParams params = (LayoutParams) search_block.getLayoutParams();
        params.height = searchBlockHeight;
        search_block.setBackgroundColor(searchBlockColor);
        search_block.setLayoutParams(params);

        // 6. 返回按键
        searchBack = findViewById(R.id.search_back);
        searchBack.setImageResource(mLeftPic);
        mIvRightPic = findViewById(R.id.iv_right_pic);
        mIvRightPic.setImageResource(mRightPic);

    }


    /**
     * 点击键盘中搜索键后的操作，用于接口回调
     */
    public void setOnClickSearch(ICallBack mCallBack) {
        this.mCallBack = mCallBack;

    }

    /**
     * 点击返回后的操作，用于接口回调
     */
    public void setOnClickBack(BackCallBack backCallBack) {
        this.mBackCallBack = backCallBack;
    }

    public void setOnEditCallBack(EditCallBack editCallBack) {
        this.mEditCallBack = editCallBack;
    }

    public void setOnSearchRightIconCallBack(SearchRightIconCallBack onSearchRightIconCallBack) {
        if (onSearchRightIconCallBack != null) {
            this.mSearchRightIconCallBack = onSearchRightIconCallBack;
        }
    }


}
