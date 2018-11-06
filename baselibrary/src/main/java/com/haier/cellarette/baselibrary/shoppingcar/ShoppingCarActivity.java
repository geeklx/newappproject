package com.haier.cellarette.baselibrary.shoppingcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class ShoppingCarActivity extends AppCompatActivity {

    private RelativeLayout rl_shopping_cart;
    private FrameLayout fl_shopping_cart;
    private ImageView iv_shopping_cart;
    private TextView tv_shopping_cart_count;
    private ImageView iv_from;

    private CarAnimationUtil carAnimationUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcar);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        carAnimationUtil = new CarAnimationUtil();
        carAnimationUtil.isShowCartGoodsCount(tv_shopping_cart_count);
    }

    private void onclick() {
        iv_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carAnimationUtil.addGoodsToCart(rl_shopping_cart,iv_shopping_cart,tv_shopping_cart_count,ShoppingCarActivity.this,iv_from);
            }
        });
    }

    private void findview() {
        rl_shopping_cart = findViewById(R.id.rl_shopping_cart);
        fl_shopping_cart = findViewById(R.id.fl_shopping_cart);
        iv_shopping_cart = findViewById(R.id.iv_shopping_cart);
        tv_shopping_cart_count = findViewById(R.id.tv_shopping_cart_count);
        iv_from = findViewById(R.id.iv_from);
    }


}
