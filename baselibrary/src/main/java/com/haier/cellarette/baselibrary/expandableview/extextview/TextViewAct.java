package com.haier.cellarette.baselibrary.expandableview.extextview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.expandableview.SimplePaddingDecoration;
import com.haier.cellarette.baselibrary.expandableview.extextview.adapter.TextViewAdapter;
import com.haier.cellarette.baselibrary.expandableview.extextview.bean.TextViewChildBean;
import com.haier.cellarette.baselibrary.expandableview.extextview.bean.TextViewGroupBean;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;
import java.util.List;

import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;

public class TextViewAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<RecyclerViewData> mDatas;
    private TextViewAdapter mAdapter;

    private void initDatas() {
        List<TextViewChildBean> bean1 = new ArrayList<>();
        List<TextViewChildBean> bean2 = new ArrayList<>();
        List<TextViewChildBean> bean3 = new ArrayList<>();
        List<TextViewChildBean> bean4 = new ArrayList<>();
        // id , pid , label , 其他属性
        bean1.add(new TextViewChildBean("文件管理系统"));
        bean1.add(new TextViewChildBean("游戏"));
        bean1.add(new TextViewChildBean("文档"));
        bean1.add(new TextViewChildBean("程序"));
        bean2.add(new TextViewChildBean("war3"));
        bean2.add(new TextViewChildBean("刀塔传奇"));

        bean1.add(new TextViewChildBean("面向对象"));
        bean2.add(new TextViewChildBean("非面向对象"));

        bean2.add(new TextViewChildBean("C++"));
        bean2.add(new TextViewChildBean("JAVA"));
        bean2.add(new TextViewChildBean("Javascript"));
        bean2.add(new TextViewChildBean("C"));

        bean3.add(new TextViewChildBean("文件管理系统"));
        bean3.add(new TextViewChildBean("游戏"));
        bean4.add(new TextViewChildBean("文档"));
        bean4.add(new TextViewChildBean("程序"));
        bean4.add(new TextViewChildBean("war3"));
        bean3.add(new TextViewChildBean("刀塔传奇"));

        bean3.add(new TextViewChildBean("面向对象"));
        bean4.add(new TextViewChildBean("非面向对象"));

        bean3.add(new TextViewChildBean("文件管理系统"));
        bean3.add(new TextViewChildBean("游戏"));
        bean4.add(new TextViewChildBean("文档"));
        bean4.add(new TextViewChildBean("程序"));
        bean4.add(new TextViewChildBean("war3"));
        bean4.add(new TextViewChildBean("刀塔传奇"));

        TextViewGroupBean bean11 = new TextViewGroupBean("分组0");
        TextViewGroupBean bean22 = new TextViewGroupBean("分组1");
        TextViewGroupBean bean33 = new TextViewGroupBean("分组2");
        TextViewGroupBean bean44 = new TextViewGroupBean("分组3");

        mDatas = new ArrayList<>();
        mDatas.add(new RecyclerViewData(bean11, bean1));
        mDatas.add(new RecyclerViewData(bean22, bean2));
        mDatas.add(new RecyclerViewData(bean33, bean3));
        mDatas.add(new RecyclerViewData(bean44, bean4));

//        mDatas.add(new RecyclerViewData(bean11, bean1, true));
//        mDatas.add(new RecyclerViewData(bean22, bean2, true));
//        mDatas.add(new RecyclerViewData(bean33, bean3, true));
//        mDatas.add(new RecyclerViewData(bean44, bean4, true));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandableact_textview);
        findview();
        donetwork();
        onclicklistener();
    }

    private void donetwork() {
        initDatas();
        mAdapter = new TextViewAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void onclicklistener() {
        mAdapter.setOnItemClickListener(new OnRecyclerViewListener.OnItemClickListener() {
            @Override
            public void onGroupItemClick(int position, int groupPosition, View view) {
                //点击group bufen
                TextViewGroupBean group = (TextViewGroupBean) mDatas.get(groupPosition).getGroupData();
                Toasty.normal(TextViewAct.this, "groupPos:" + groupPosition + " group:" + group.getName()).show();
            }

            @Override
            public void onChildItemClick(int position, int groupPosition, int childPosition, View view) {
                //点击child bufen
                TextViewChildBean bean = (TextViewChildBean) mDatas.get(groupPosition).getChild(childPosition);
                Toasty.normal(TextViewAct.this, "groupPos:" + groupPosition + " childPos:" + childPosition + " child:" + bean.getName()).show();
            }
        });
        mAdapter.setOnItemLongClickListener(new OnRecyclerViewListener.OnItemLongClickListener() {
            @Override
            public void onGroupItemLongClick(int position, int groupPosition, View view) {
                TextViewGroupBean group = (TextViewGroupBean) mDatas.get(groupPosition).getGroupData();
                Toasty.normal(TextViewAct.this, "groupPos:" + groupPosition + " group:" + group.getName()).show();
                showDeleteDialog(position, groupPosition, 0, true);
            }

            @Override
            public void onChildItemLongClick(int position, int groupPosition, int childPosition, View view) {
                TextViewChildBean bean = (TextViewChildBean) mDatas.get(groupPosition).getChild(childPosition);
                Toasty.normal(TextViewAct.this, "groupPos:" + groupPosition + " childPos:" + childPosition + " child:" + bean.getName()).show();
                showDeleteDialog(position, groupPosition, childPosition, false);
            }
        });
    }

    private void findview() {
        mRecyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SimplePaddingDecoration(this));

    }

    /**
     * 删除数据
     *
     * @param position
     * @param isGroup
     */
    private void showDeleteDialog(final int position, final int groupPosition, final int childPosition, final boolean isGroup) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TextViewAct.this)
                .setTitle("提示!")
                .setMessage(isGroup ? "您确定要删除此组数据" : "您确定要删除此条数据")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //然后根据回调里的groupPosition和childPosition来更新你的数据源
                        if (isGroup) {
                            //删除group 行
                            mDatas.remove(groupPosition);
                        } else {
                            //删除child 行
                            mDatas.get(groupPosition).removeChild(childPosition);
                        }
                        mAdapter.notifyRecyclerViewData();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
