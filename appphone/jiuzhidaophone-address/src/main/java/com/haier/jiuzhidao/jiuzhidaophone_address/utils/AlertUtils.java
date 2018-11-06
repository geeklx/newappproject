package com.haier.jiuzhidao.jiuzhidaophone_address.utils;

import android.content.Context;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.widget.AlertView;

public class AlertUtils {
    public static void showAlert(String str, final Context mContext) {
        final AlertView dialog = new AlertView(mContext, "温馨提示", str,
                "取消", "确定");
        dialog.show();
        dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                    @Override
                                    public void doLeft() {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void doRight() {
                                        dialog.dismiss();
                                        if(mContext instanceof BaseActivity){
                                            ((BaseActivity) mContext).finish();
                                        }
                                    }
                                }
        );
    }
}
