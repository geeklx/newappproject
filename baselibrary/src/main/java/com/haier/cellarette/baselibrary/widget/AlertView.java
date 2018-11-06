package com.haier.cellarette.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

/**
 * 仿IOS的弹出框
 */
public class AlertView extends Dialog {

    private Context context;
    private String title;
    private String message;
    private String buttonLeftText;
    private String buttonRightText;
    private ClickListenerInterface clickListenerInterface;

    public AlertView(Context context, String title, String message,
                       String buttonLeftText, String buttonRightText) {
        super(context, R.style.AlertViewStyle);

        this.context = context;
        this.title = title;
        this.message = message;
        this.buttonLeftText = buttonLeftText;
        this.buttonRightText = buttonRightText;
    }

    public AlertView(Context context, String title, String message, String buttonRightText) {
        super(context, R.style.AlertViewStyle);

        this.context = context;
        this.title = title;
        this.message = message;
        this.buttonRightText = buttonRightText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        inite();
    }

    public void inite() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.alert_view, null);
        setContentView(view);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        TextView tvLeft = view.findViewById(R.id.tvBtnLeft);
        View viewV = view.findViewById(R.id.view_v);
        TextView tvRight = view.findViewById(R.id.tvBtnRight);
        TextView tvTitle = view.findViewById(R.id.tvTitle);

        if ("".equals(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        tvMessage.setText(message);
        if (null==buttonLeftText||"".equals(buttonLeftText)){
            tvLeft.setVisibility(View.GONE);
            viewV.setVisibility(View.GONE);
        }else {
            tvLeft.setText(buttonLeftText);
        }
        tvRight.setText(buttonRightText);

        tvLeft.setOnClickListener(new clickListener());
        tvRight.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();

        lp.width = (int) (d.widthPixels * 0.8);
        dialogWindow.setAttributes(lp);
    }


    public interface ClickListenerInterface {
        void doLeft();

        void doRight();
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.tvBtnLeft) {
                clickListenerInterface.doLeft();
            } else if (id == R.id.tvBtnRight) {
                clickListenerInterface.doRight();
            }
        }
    }
}
