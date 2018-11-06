package com.haier.index.winedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.biz.identification.bean.WineDetial;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.index.R;

import java.util.List;


public class WineListActivity extends BaseActivity implements View.OnClickListener {

    private TextView title;
    private ImageView back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_winedetail;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        FindView();
        init();
        List<WineDetial.WineListBean> list = (List<WineDetial.WineListBean>) getIntent().getSerializableExtra("list");
    }

    private void FindView() {
        title = findViewById(R.id.tv_title);
        back = findViewById(R.id.iv_back);
    }

    private void init() {
        title.setText("酒品信息");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        }
    }
}
