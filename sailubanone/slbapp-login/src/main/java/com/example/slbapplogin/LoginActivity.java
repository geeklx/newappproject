package com.example.slbapplogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slbappshare.denglu.JPushDengluUtils;
import com.example.slbappshare.denglu.OnResultInfoLitener;
import com.example.slbappshare.fenxiang.ShareUtils;
import com.example.slbappshare.fenxiang.beans.WeixinBeanParam;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.CustomImageView;
import com.haier.cellarette.baselibrary.widget.SmoothCheckBox;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;

public class LoginActivity extends BaseActivity implements View.OnClickListener, SmoothCheckBox.OnCheckedChangeListener, OnResultInfoLitener {

    private TextView tv_left;
    private TextView tv_center;
    private CustomImageView iv_center;//头像
    private EditText edt1;//账号
    private EditText edt2;//密码
    private LinearLayout ll_left;//记住密码
    private LinearLayout ll_right;//自动登录
    private SmoothCheckBox scb_left;//
    private SmoothCheckBox scb_right;//
    private Button btn_sure;//登录
    private Button btn_cancel;//临时访问
    private TextView tv_zc;//注册
    private TextView tv_wjmm;//忘记密码
    private ImageView iv_wx;//微信登录
    private ImageView iv_wx2;//微信登录


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {

    }

    private void onclick() {
        ll_left.setOnClickListener(this);
        ll_right.setOnClickListener(this);
        scb_left.setOnCheckedChangeListener(this);
        scb_right.setOnCheckedChangeListener(this);
        scb_right.setEnabled(true);
        btn_sure.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        tv_zc.setOnClickListener(this);
        tv_wjmm.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
        iv_wx2.setOnClickListener(this);

    }

    private void findview() {
        tv_left = findViewById(R.id.tv_left);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_center = findViewById(R.id.tv_center);
        tv_center.setText("账号登录");
        iv_center = findViewById(R.id.iv_center);
        iv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("hs.act.slbapp.ShareIndexActivity"));
            }
        });
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        ll_left = findViewById(R.id.ll_left);
        ll_right = findViewById(R.id.ll_right);
        scb_left = findViewById(R.id.scb_left);
        scb_right = findViewById(R.id.scb_right);
        btn_sure = findViewById(R.id.btn_sure);
        btn_cancel = findViewById(R.id.btn_cancel);
        tv_zc = findViewById(R.id.tv_zc);
        tv_wjmm = findViewById(R.id.tv_wjmm);
        iv_wx = findViewById(R.id.iv_wx);
        iv_wx2 = findViewById(R.id.iv_wx2);

    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
        int id = checkBox.getId();
        if (id == R.id.scb_left) {
            if (isChecked) {
                jzmm_xuanzhong();
            } else {
                jzmm_quxiao();
            }
        } else if (id == R.id.scb_right) {
            if (isChecked) {
                zddl_xuanzhong();
            } else {
                zddl_quxiao();
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_left) {
            if (scb_left.isChecked()) {
                //设置取消
                if (scb_right.isChecked()) {
                    Toasty.normal(this, "请先取消自动登录!").show();
                    return;
                }
                scb_left.setChecked(false, true);
            } else {
                //设置选中
                scb_left.setChecked(true, true);
            }
        } else if (i == R.id.ll_right) {
            if (scb_right.isChecked()) {
                //设置取消的时候把记住密码 设置选中
                scb_right.setChecked(false, true);
                if (!scb_left.isChecked()) {
                    scb_left.setChecked(true, true);
                }
            } else {
                //设置选中
                scb_right.setChecked(true, true);
            }
        } else if (i == R.id.btn_sure) {
//            if (!is_login()) {
//                return;
//            }
            denglu();
        } else if (i == R.id.btn_cancel) {
            if (!is_login()) {
                return;
            }
            linshifangwen();
        } else if (i == R.id.tv_zc) {
            zhuce();
        } else if (i == R.id.tv_wjmm) {
            wangjimima();
        } else if (i == R.id.iv_wx) {
            qita_wx_login();
        } else if (i == R.id.iv_wx2) {
            qita_wx_share();
        }
    }

    private boolean is_login() {
        String a = edt1.getText().toString().trim();
        String b = edt2.getText().toString().trim();
        if (TextUtils.isEmpty(a)) {
            Toasty.normal(this, "请输入您的账号").show();
            return false;
        }
        if (TextUtils.isEmpty(b)) {
            Toasty.normal(this, "请输入您的密码").show();
            return false;
        }
        return true;
    }


    /**
     *
     *
     * ----------------------------------下面为业务逻辑--分割线------------------------------
     *
     */

    /**
     * 记住密码 选中业务
     */
    private void jzmm_xuanzhong() {
        Toasty.normal(this, "scb_cen_degree: checked").show();

    }

    /**
     * 记住密码 取消业务
     */
    private void jzmm_quxiao() {
        Toasty.normal(this, "scb_cen_degree: unchecked").show();

    }

    /**
     * 自动登录 选中业务
     */
    private void zddl_xuanzhong() {
        Toasty.normal(this, "scb_fah_degree: checked").show();

    }

    /**
     * 自动登录 取消业务
     */
    private void zddl_quxiao() {
        Toasty.normal(this, "scb_fah_degree: unchecked").show();

    }

    /**
     * 登录
     */
    private void denglu() {
        Toasty.normal(this, "登录").show();
        startActivity(new Intent("hs.act.slbapp.index"));

    }

    /**
     * 临时访问
     */
    private void linshifangwen() {
        Toasty.normal(this, "临时访问").show();
    }

    /**
     * 注册
     */
    private void zhuce() {
        startActivity(new Intent("hs.act.slbapp.ZhuceActivity"));
    }

    /**
     * 忘记密码
     */
    private void wangjimima() {
        startActivity(new Intent("hs.act.slbapp.WangjiActivity"));

    }

    /**
     * 其他方式登录 - 微信
     */
    private void qita_wx_login() {
        Toasty.normal(this, "微信").show();
        JPushDengluUtils JPushDengluUtils = new JPushDengluUtils(this);
        JPushDengluUtils.shezhi_shouquan_getinfo("Wechat");
    }


    @Override
    public void onResults(String platform, String toastMsg, String data) {
        Toasty.normal(BaseApp.get(), platform + "---" + toastMsg + "---" + data).show();
    }

    /**
     * 其他方式分享 - 微信
     */
    private void qita_wx_share() {
        //网络图片转bitmap
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = downloadUrlToBitmap(ShareUtils.share_imageurl);
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    img_handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private Handler img_handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Bitmap bitmap = (Bitmap) message.obj;
            JShareInterface.share("Wechat",
                    WeixinBeanParam.share_web2(
                            ShareUtils.share_title,
                            ShareUtils.share_text,
                            ShareUtils.share_url,
                            bitmap),
                    mShareListener1);
            return true;
        }
    });

    /**
     * 下载图片
     *
     * @param url
     * @return
     * @throws Exception
     */
    private Bitmap downloadUrlToBitmap(String url) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        urlConnection.disconnect();
        in.close();
        return bitmap;
    }

    private Handler mHandler = new Handler();
    private PlatActionListener mShareListener1 = new PlatActionListener() {
        @Override
        public void onComplete(Platform platform, int action, HashMap<String, Object> data) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "分享成功", Toast.LENGTH_LONG).show();

                }
            });

        }

        @Override
        public void onError(Platform platform, int action, final int errorCode, final Throwable error) {
            Log.e("LoginActivity", "error:" + errorCode + ",msg:" + error);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "分享失败:" + error.getMessage() + "---" + errorCode, Toast.LENGTH_LONG).show();//41020
                }
            });
        }

        @Override
        public void onCancel(Platform platform, int action) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "分享取消", Toast.LENGTH_LONG).show();
                }
            });

        }
    };

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (img_handler != null) {
            img_handler.removeCallbacksAndMessages(null);
            img_handler = null;
        }
        super.onDestroy();

    }

}
