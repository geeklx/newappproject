package com.haier.cellarette.baselibrary.handleralluse;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.zothers.DaojishiUtil;

import java.lang.ref.WeakReference;

public class handler4 extends AppCompatActivity {

    private TextView tv1;

    private H mHandler2 = new H(this);
    private static final int MSG_RUN = 189;
    private static final int DELAY_MILLIS = 1000;
    private long mCurrentTime;

    private static class H extends Handler {
        private WeakReference<handler4> mActivity;
        private long mNextTime;

        public H(handler4 activity) {
            mActivity = new WeakReference<handler4>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler4 activity = mActivity.get();
            if (activity == null || msg.what != MSG_RUN) {
                return;
            }
            if (activity.decrTime(SystemClock.uptimeMillis() - mNextTime)) {
                mNextTime = SystemClock.uptimeMillis();
                sendEmptyMessageDelayed(MSG_RUN, DELAY_MILLIS);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler4);
        tv1 = findViewById(R.id.tv1);
        startTimer2(6000000);
    }

    private void startTimer2(long current) {
        //设置时分秒bufen
        mCurrentTime = current;
        startTime();
    }

    private void startTime() {
        handler_chushihua();
        //启动handlerbufen
//        mHandler2.sendEmptyMessageDelayed(MSG_RUN, 0);
        mHandler2.sendEmptyMessage(MSG_RUN);
    }

    private void handler_chushihua() {
        mHandler2.removeMessages(MSG_RUN);
        mHandler2.mNextTime = SystemClock.uptimeMillis();
        decrTime(0);
    }

    /**
     * 倒计时
     *
     * @param ms
     * @return true 继续倒计时， false停止倒计时
     */
    private boolean decrTime(long ms) {
        mCurrentTime -= ms;
        if (mCurrentTime <= 0) {
//        if (mCurrentTime - 1000 <= 1000) {
            // 已结束
            doCancel();
            return false;
        }

        long[] times = DaojishiUtil.compute(mCurrentTime);
        tv1.setText(DaojishiUtil.time_change_one(times[1]) + " : " + DaojishiUtil.time_change_one(times[2]) + " : " + DaojishiUtil.time_change_one(times[3]));
        return true;
    }

    private void doCancel() {
        mHandler2.removeMessages(MSG_RUN);
        setTime(0, 0, 0);
//        tp_hour.setText("00");
//        tp_min.setText("00");
//        tp_sec.setText("00");
        tv1.setText("00 : 00 : 00");
    }

    /**
     * @param
     * @return void
     * @throws Exception
     * @throws
     * @Description: 设置倒计时的时长
     */
    public void setTime(int hour, int min, int sec) {
        mCurrentTime = (hour * 60 * 60 + min * 60 + sec) * 1000;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doCancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler2.removeMessages(MSG_RUN);
    }

}
