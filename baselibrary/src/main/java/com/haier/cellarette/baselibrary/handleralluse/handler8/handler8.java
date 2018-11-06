package com.haier.cellarette.baselibrary.handleralluse.handler8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

import java.util.Arrays;
import java.util.List;

public class handler8 extends AppCompatActivity implements View.OnClickListener, Handler.Callback {

    private ImageView mIvDisplay;
    private TextView mTvStatus;
    private Button mBtnDownload;
    private String[] strings = new String[]{"https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg",
            "https://ws1.sinaimg.cn/large/d23c7564ly1fg6qckyqxkj20u00zmaf1.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg"};
    private List<String> urlList = Arrays.asList(strings);
    private int mFinishCount;   //完成的任务个数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler8);
        mIvDisplay = findViewById(R.id.iv_display);
        mTvStatus = findViewById(R.id.tv_status);
        mBtnDownload = findViewById(R.id.btn_download);
        mBtnDownload.setOnClickListener(this);
        DownloadService.setUIHandler(new Handler(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case DownloadService.WHAT_DOWNLOAD_FINISHED:
                    mIvDisplay.setImageBitmap((Bitmap) msg.obj);
                    mBtnDownload.setText("完成 " + (++mFinishCount) + "个任务");
                    break;
                case DownloadService.WHAT_DOWNLOAD_STARTED:
                    mTvStatus.setText(mTvStatus.getText() + (String) msg.obj);
                    break;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_download) {
            Intent intent = new Intent(this, DownloadService.class);
            for (String url : urlList) {
                intent.putExtra(DownloadService.DOWNLOAD_URL, url);
                startService(intent);
            }
            mBtnDownload.setEnabled(false);
        } else {
        }
    }
}
