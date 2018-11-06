package com.haier.cellarette.baselibrary.download;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

import java.util.Timer;
import java.util.TimerTask;

public class DownLoadDialog extends Dialog {
    public DownLoadDialog(Context context) {
        super(context,R.style.style_loading_dialog);
    }

    private TextView tv_loading;
    private ProgressBar progressBar;

    private Timer timer;
    private int count = 1;

    public void closeDialog() {
        if (this != null && this.isShowing()) {
            this.dismiss();
        }
    }

    public void openDialog(final int newProgress) {
        if (this != null && this.isShowing()) {
            this.setProgress(newProgress);
//            progressBar.setProgress(newProgress);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_progress);
        tv_loading = findViewById(R.id.tv_loading);
        progressBar = findViewById(R.id.pb);

        // 设置Dialog显示的宽度，
        Display d = getWindow().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        //这里设置为屏幕宽度的百分之八十
        lp.width = (int) (d.getWidth() * 0.8);
        getWindow().setAttributes(lp);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 300, 300);
        setCanceledOnTouchOutside(false);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (timer != null) {
                    timer.cancel();
                }
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                    handler = null;
                }
            }
        });
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            count++;
            if (count > 3) {
                count = 1;
            }
            switch (count) {
                case 1:
                    tv_loading.setText("加载中.");
                    break;
                case 2:
                    tv_loading.setText("加载中..");
                    break;
                case 3:
                    tv_loading.setText("加载中...");
                    break;
            }
            return true;
        }
    });

    public void setProgress(int progress) {
        int color = Integer.parseInt("f25252", 16);//tag.getCoupon_color().substring(1)  FF5001  ratings.getTag_color().substring(1)
        color = 0xFF000000 + color;
//            viewHolder.pb.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        LayerDrawable drawable = (LayerDrawable) progressBar.getProgressDrawable();
        drawable.getDrawable(1).setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        progressBar.setProgress(progress);
        if (progress == 100) {
            this.dismiss();
        }
    }

}
