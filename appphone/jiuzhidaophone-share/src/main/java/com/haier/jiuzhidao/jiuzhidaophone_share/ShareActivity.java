package com.haier.jiuzhidao.jiuzhidaophone_share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;

import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_mail;
    private LinearLayout ll_qq;
    private LinearLayout ll_qq_space;
    private LinearLayout ll_tengxun;
    private LinearLayout ll_xinlang;
    private LinearLayout ll_wx_circle;
    private LinearLayout ll_wx_collection;
    private LinearLayout ll_wx_friend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        FindViewId();
    }

    private void FindViewId() {
        ll_mail = findViewById(R.id.ll_mail);
        ll_qq = findViewById(R.id.ll_qq);
        ll_qq_space = findViewById(R.id.ll_qq_space);
        ll_tengxun = findViewById(R.id.ll_tengxun);
        ll_xinlang = findViewById(R.id.ll_xinlang);
        ll_wx_circle = findViewById(R.id.ll_wx_circle);
        ll_wx_collection = findViewById(R.id.ll_wx_collection);
        ll_wx_friend = findViewById(R.id.ll_wx_friend);

        ll_mail.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_qq_space.setOnClickListener(this);
        ll_tengxun.setOnClickListener(this);
        ll_xinlang.setOnClickListener(this);
        ll_wx_circle.setOnClickListener(this);
        ll_wx_collection.setOnClickListener(this);
        ll_wx_friend.setOnClickListener(this);
    }


    String name = "";

    @Override
    public void onClick(View v) {
//
        int i = v.getId();
        if (i == R.id.ll_mail) {
            name = Email.NAME;
        } else if (i == R.id.ll_qq) {
            name = QQ.NAME;
        } else if (i == R.id.ll_qq_space) {
            name = QZone.NAME;
        } else if (i == R.id.ll_tengxun) {
            name = TencentWeibo.NAME;
        } else if (i == R.id.ll_wx_circle) {
            name = WechatMoments.NAME;
        } else if (i == R.id.ll_wx_collection) {
            name = WechatFavorite.NAME;
        } else if (i == R.id.ll_wx_friend) {
            name = Wechat.NAME;
        } else if (i == R.id.ll_xinlang) {
            name = SinaWeibo.NAME;
        }
        ShareUtils.showShare(name, this);
        finish();
    }

}
