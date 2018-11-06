package com.haier.cellarette.baselibrary.recycleviewalluses.demo1baseadpterhelp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo1 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRecActDemo1Adapter mAdapter;
    private List<BaseRecActDemo1Bean> mList;

    public static List<BaseRecActDemo1Bean> getSampleData(int lenth) {
        List<BaseRecActDemo1Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo1Bean status = new BaseRecActDemo1Bean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo1);
        findview();
        onclicklistener();
        donetwork();
        initMenu();
    }

    private void donetwork() {
        mList = new ArrayList<>();
        mList = getSampleData(100);
        mAdapter.setNewData(mList);
        mAdapter.notifyDataSetChanged();

    }

    private void onclicklistener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                Toasty.normal(BaseRecActDemo1.this, position + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo1Bean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    Toasty.normal(BaseRecActDemo1.this, addressBean.getUserAvatar() + "    " + position).show();
                } else if (i == R.id.brademo1_tweetName) {
                    Toasty.normal(BaseRecActDemo1.this, addressBean.getUserName() + position).show();
                }else if (i == R.id.brademo1_tweetText) {
                    Toasty.normal(BaseRecActDemo1.this, addressBean.getText() + position).show();
                } else {
                }
            }
        });
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo1Bean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    Toasty.normal(BaseRecActDemo1.this, addressBean.getUserAvatar() + "长按    " + position).show();
                } else if (i == R.id.brademo1_tweetName) {
                    Toasty.normal(BaseRecActDemo1.this, addressBean.getUserName() + position).show();
                }else if (i == R.id.brademo1_tweetText) {
                    Toasty.normal(BaseRecActDemo1.this, addressBean.getText() + position).show();
                } else {
                }
                return true;
            }
        });
    }

    private void findview() {
        mRecyclerView = findViewById(R.id.rvlist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseRecActDemo1Adapter(R.layout.activity_recycleviewalluses_demo1_item);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initMenu() {
        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "Custom");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                        break;
                    case 1:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                        break;
                    case 2:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        break;
                    case 3:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        break;
                    case 4:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        break;
                    case 5:
                        mAdapter.openLoadAnimation(new CustomAnimation());
                        break;
                    default:
                        break;
                }
                mRecyclerView.setAdapter(mAdapter);
            }
        });
        mAdapter.isFirstOnly(false);//init firstOnly state
        SwitchButton switchButton = findViewById(R.id.switch_button);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    mAdapter.isFirstOnly(true);
                } else {
                    mAdapter.isFirstOnly(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
