package com.haier.uplus.control.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.uplus.control.R;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:WIFI连接成功
 */
public class LinkSuccessActivity extends BaseActivity implements View.OnClickListener{

    private ImageView iv_back;
    private TextView tv_title;
    private Button btn_sucess;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_link_success;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);

        initUI();
    }

    public void initUI(){
        iv_back = f(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = f(R.id.tv_title);
        tv_title.setText("入网配置成功");
        btn_sucess = f(R.id.btn_sucess);

        iv_back.setOnClickListener(this);
        btn_sucess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.iv_back){
            finish();
        }else if (id==R.id.btn_sucess){
//            Toasty.success(this,"查看详情").show();
            this.finish();
        }
    }
}
