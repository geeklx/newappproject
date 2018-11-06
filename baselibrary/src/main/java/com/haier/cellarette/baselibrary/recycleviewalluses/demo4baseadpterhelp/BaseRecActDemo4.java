package com.haier.cellarette.baselibrary.recycleviewalluses.demo4baseadpterhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo1baseadpterhelp.BaseRecActDemo1Adapter;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo1baseadpterhelp.BaseRecActDemo1Bean;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo1baseadpterhelp.CustomAnimation;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo4 extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo4);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.BaseRecActDemo41.act"));
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.BaseRecActDemo42.act"));
            }
        });
    }


}
