package com.haier.cellarette.baselibrary.zuni;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.changelanguage.LocalManageUtil;


public class DemoZuniScrollViewActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorll_view);
    }


    public void img(View view) {
        Toast.makeText(this, "赵丽颖", Toast.LENGTH_SHORT).show();
    }
}
