package com.haier.pay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.haier.biz2phonedemo1.ConstantNetConfig;

public class demoact extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //版本判断

        if (TextUtils.equals(ConstantNetConfig.VERSION_NAME, "测试")) {

        } else if (TextUtils.equals(ConstantNetConfig.VERSION_NAME, "预生产")) {

        } else if (TextUtils.equals(ConstantNetConfig.VERSION_NAME, "线上")) {

        }


    }


}
