package com.haier.cellarette.baselibrary.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.haier.cellarette.baselibrary.R;

public class SimpleLoadingDialog {
    private Context mContext;
    private Dialog mDialog;
    private View mDialogContentView;
    private ImageView iv;
    private AnimationDrawable ad;
    private OnkeyBackDestoryLoadingListener onKeyBack;


    public SimpleLoadingDialog(Context context) {
        this.mContext = context;
        init();
    }

    public SimpleLoadingDialog(Context context, OnkeyBackDestoryLoadingListener onKeyBack) {
        this.mContext = context;
        this.onKeyBack = onKeyBack;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, R.style.custom_dialog);
        mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.activity_loading, null);
        iv = mDialogContentView.findViewById(R.id.loadView_img);
        ad = (AnimationDrawable) iv.getBackground();

        mDialog.setContentView(mDialogContentView);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (null != onKeyBack) {
                    onKeyBack.onKeyBack();
                }
                return false;
            }
        });
    }

    public void setBackground(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) mDialogContentView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence) {
//        mLoadingView.setLoadingText(charSequence);
    }

    public void show() {
        mDialog.show();
        ad.start();
    }

    public void dismiss() {
        mDialog.dismiss();
        ad.stop();

    }
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                ad.stop();
//            }
//        }, 5 * 1000);
    public void onDestroy() {
        mDialog.dismiss();
        ad.stop();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

}