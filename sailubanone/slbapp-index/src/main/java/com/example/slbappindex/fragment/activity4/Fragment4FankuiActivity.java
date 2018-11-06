package com.example.slbappindex.fragment.activity4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.slbappindex.R;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.qcode.ExpandViewRect;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

public class Fragment4FankuiActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvRight;
    private TextView tvCenter;
    private EditText edt1;
    private EditText edt2;
    private Button btnSure;
    private TextView tv_ed_content;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment4_fankui;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText("反馈");
        ExpandViewRect.expandViewTouchDelegate(tvRight, 20, 20, 20, 20);
        edt1.clearFocus();
        edt2.clearFocus();
        edt1.addTextChangedListener(mTextWatcher);

    }

    private int MAX_TEXT = 100;
    private TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            tv_ed_content.setText("还能输入" + (MAX_TEXT - s.length()) + "字符");
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = edt1.getSelectionStart();
            editEnd = edt1.getSelectionEnd();
            if (temp.length() > MAX_TEXT) {
                Toasty.normal(Fragment4FankuiActivity.this, "限制输入" + MAX_TEXT + "字符!").show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                edt1.setText(s);
                edt1.setSelection(tempSelection);
            }
        }
    };

    private void onclick() {
        tvRight.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        //用于嵌套Scrollview滚动冲突问题bufen
//        edt1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    scl1.requestDisallowInterceptTouchEvent(false);
//                } else {
//                    scl1.requestDisallowInterceptTouchEvent(true);
//                }
//                return false;
//            }
//        });
    }

    private void findview() {
        tvRight = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        tv_ed_content = findViewById(R.id.tv_ed_content);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnSure = findViewById(R.id.btn_sure);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_right) {
            onBackPressed();
        } else if (i == R.id.btn_sure) {
            //发送bufen
            if (TextUtils.isEmpty(edt1.getText().toString().trim())) {
                Toasty.normal(Fragment4FankuiActivity.this, "填写内容不能为空").show();
                return;
            }
            if (TextUtils.isEmpty(edt2.getText().toString().trim())) {
                Toasty.normal(Fragment4FankuiActivity.this, "联系方式不能为空").show();
                return;
            }
            Toasty.normal(Fragment4FankuiActivity.this, "发送成功，请等待回复").show();
        } else {
        }
    }
}
