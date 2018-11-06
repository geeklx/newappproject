package com.haier.uplus.control.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.uplus.control.R;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_back;
    private TextView tv_title;
    private Button btn_next;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initUI();
    }

    public void initUI(){
        iv_back = f(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = f(R.id.tv_title);
        tv_title.setText("设备配置向导");
        btn_next = f(R.id.btn_next);

        iv_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.iv_back){
            onBackPressed();
        }else if (id==R.id.btn_next){
            startActivity(new Intent("hs.act.phone.LinkWifiActivity"));
            this.finish();
        }
    }
}
