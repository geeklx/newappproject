package com.github.commonlibs.libupdateapputils.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.commonlibs.libupdateapputils.R;
import com.github.commonlibs.libupdateapputils.feature.Callback;


/**
 * Created by Teprinciple on 2016/10/13.
 */
public class ConfirmDialogNew extends Dialog {

    Callback callback;
    private TextView content;
    private TextView title;
    private TextView sureBtn;
    private TextView cancleBtn;

    public ConfirmDialogNew(Context context, Callback callback) {
        super(context, R.style.CustomDialog);
        this.callback = callback;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm_new, null);
        sureBtn = mView.findViewById(R.id.dialog_confirm_sure);
        cancleBtn = mView.findViewById(R.id.dialog_confirm_cancle);
        content = mView.findViewById(R.id.dialog_confirm_content);
        title = mView.findViewById(R.id.dialog_confirm_title);


        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(1);
                ConfirmDialogNew.this.cancel();
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callback(0);
                ConfirmDialogNew.this.cancel();
            }
        });
        super.setContentView(mView);
    }


    public ConfirmDialogNew setContent(String s){
        content.setText(s);
        return this;
    }

    public ConfirmDialogNew setTitle(String s){
        title.setText(s);
        return this;
    }


}
