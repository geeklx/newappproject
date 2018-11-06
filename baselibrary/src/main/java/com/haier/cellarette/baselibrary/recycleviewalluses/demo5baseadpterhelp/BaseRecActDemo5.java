package com.haier.cellarette.baselibrary.recycleviewalluses.demo5baseadpterhelp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo5 extends AppCompatActivity {

    private static final String TAG = "BaseRecActDemo5";
    private RecyclerView mRecyclerView;
    private BaseRecActDemo5Adapter mAdapter;
    private List<BaseRecActDemo5Bean> mList;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private OnItemSwipeListener onItemSwipeListener;
    private OnItemDragListener listener;

    public static List<BaseRecActDemo5Bean> getSampleData(int lenth) {
        List<BaseRecActDemo5Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo5Bean status = new BaseRecActDemo5Bean();
            status.setUserName("Geek" + i);
            status.setText("http://blog.51cto.com/liangxiao");
            status.setImg(R.drawable.img01);
            list.add(status);
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo5);
        findview();
        onclick();
        donetwork();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo5Bean bean = (BaseRecActDemo5Bean) adapter.getData().get(position);
                int i = view.getId();
                if (i == R.id.tv) {
                    Toasty.normal(BaseRecActDemo5.this, bean.getUserName()).show();
                } else if (i == R.id.email) {
                    Toasty.normal(BaseRecActDemo5.this, bean.getText()).show();
                } else {
                }
            }
        });
    }

    private void donetwork() {
        mList = new ArrayList<>();
        mList = getSampleData(20);
        mAdapter = new BaseRecActDemo5Adapter(R.layout.activity_recycleviewallsuses_demo5_item, mList);

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.END);

        // 开启滑动删除
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);
        // 开启拖拽
        mAdapter.enableDragItem(mItemTouchHelper, R.id.iv_head, true);
        mAdapter.setOnItemDragListener(listener);
//        mRecyclerView.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void onclick() {
        listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag start");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                holder.setTextColor(R.id.tv, ContextCompat.getColor(BaseRecActDemo5.this, R.color.red));
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag end");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                holder.setTextColor(R.id.tv, ContextCompat.getColor(BaseRecActDemo5.this, R.color.black));
            }
        };
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(40);
        paint.setColor(ContextCompat.getColor(BaseRecActDemo5.this,R.color.white));
        onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "view swiped start: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View reset: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View Swiped: " + pos);

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(BaseRecActDemo5.this, R.color.orange_red_press));
                canvas.drawText("滑动删除", 100, 165, paint);
            }
        };
    }

    private void findview() {
        mRecyclerView = findViewById(R.id.rvlist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
