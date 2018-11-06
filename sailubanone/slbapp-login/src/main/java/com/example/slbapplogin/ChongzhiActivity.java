package com.example.slbapplogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slbapplogin.utils.YanzhengUtil;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;

public class ChongzhiActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tv0;
    private TextView tv1;
    private EditText edt1;
    private ImageView ivEyes1;
    private Button btnSure;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chongzhi;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText("重置新密码");
        tvLeft.setVisibility(View.VISIBLE);
        tv0.setText("");
        tv1.setText("您的用户名为:"+"15137615080");
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            onBackPressed();
        } else if (i == R.id.iv_eyes1) {
            YanzhengUtil.set_mima_vis(edt1, ivEyes1);
        } else if (i == R.id.btn_sure) {
            czcg();
        }
    }


    private void onclick() {
        tvLeft.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        ivEyes1.setOnClickListener(this);
    }

    private void findview() {
        tvLeft = findViewById(R.id.tv_left);
        tvCenter = findViewById(R.id.tv_center);
        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        edt1 = findViewById(R.id.edt1);
        ivEyes1 = findViewById(R.id.iv_eyes1);
        btnSure = findViewById(R.id.btn_sure);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */

    /**
     * 确定重置
     */
    private void czcg() {
        if (TextUtils.isEmpty(edt1.getText().toString().trim())) {
            return;
        }
        tv0.setText("重置成功");

    }
}

