package com.haier.cellarette.baselibrary.handleralluse.handler7;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;

public class handler7 extends AppCompatActivity implements View.OnClickListener, Handler.Callback {

    private TextView mTvStartMsg;
    private TextView mTvFinishMsg;
    private Button mBtnStartDownload;

    private Handler mUIHandler;
    private DownloadThread mDownloadThread;
    private String[] strings = new String[]{"http://pan.baidu.com/s/1qYc3EDQ", "http://bbs.005.tv/thread-589833-1-1.html", "http://list.youku.com/show/id_zc51e1d547a5b11e2a19e.html?"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler7);
        mTvStartMsg = findViewById(R.id.tv_start_msg);
        mTvFinishMsg = findViewById(R.id.tv_finish_msg);
        mBtnStartDownload = findViewById(R.id.btn_start_download);
        mBtnStartDownload.setOnClickListener(this);
        init();
    }

    private void init() {
        mUIHandler = new Handler(this);
        mDownloadThread = new DownloadThread("下载线程");
        mDownloadThread.setUIHandler(mUIHandler);
        mDownloadThread.setDownloadUrls(strings[0], strings[1], strings[2]);

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case DownloadThread.TYPE_FINISHED:
                mTvFinishMsg.setText(mTvFinishMsg.getText().toString() + "\n " + msg.obj);
                break;
            case DownloadThread.TYPE_START:
                mTvStartMsg.setText(mTvStartMsg.getText().toString() + "\n " + msg.obj);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadThread.quitSafely();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_start_download) {
            //开始下载
            mDownloadThread.start();
            mBtnStartDownload.setText("正在下载");
            mBtnStartDownload.setEnabled(false);
        } else {
        }
    }
}
