package com.haier.cellarette.libwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haier.cellarette.libwebview.hioscommon.HiosRegister;
import com.haier.cellarette.libwebview.hois2.HiosHelper;


/**
 * Java数据类型	query_string标识符
 * boolean	           {b}
 * byte	               {y}
 * short	           {o}
 * int	               {i}
 * long	               {l}
 * float	           {f}
 * double	           {d}
 * String	           {s}
 */
public class DemoWebviewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewmain);
//        configHios();
        String change1 = "change1";
        init();
    }

    private void configHios() {
        HiosRegister.load();
        HiosHelper.config("ad.web.page", "web.page");

        // 接收部分
        // private int mAction; // default 0
        // private String mSkuId; // maybe null
        // private String mCategoryId;
        // mAction = getIntent().getIntExtra("act", 0);
        // mSkuId = getIntent().getStringExtra("sku_id");
        // mCategoryId = getIntent().getStringExtra("category_id");
    }

    private void init() {
        findViewById(R.id.tv01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 普通的Activity包名HiosRegister跳转
                // action跳转
                // hios://jump.HiosMainActivity
                // action跳转带参数
                // hios://jump.HiosMainActivity?skuId={s}value&category_id={s}category_id_value
                // action跳转带参数并且弹出窗
                // hios://jump.HiosMainActivity?act={i}1&sku_id={s}value&category_id={s}category_id_value&playing={b}false
                // value为商品的Activity包名sku_id    category_id_value为分类Activity包名category_id
                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "hios://jump.HiosMainActivity?act={i}1&sku_id={s}2&category_id={s}3");
            }
        });
        findViewById(R.id.tv11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 普通的Activity包名未HiosRegister跳转
                // action跳转
                // hios://com.haier.cellarette.libwebview.activity.NoHiosMainActivity
                // action跳转带参数
                // hios://com.haier.cellarette.libwebview.activity.NoHiosMainActivity?skuId={s}value&category_id={s}category_id_value
                // action跳转带参数并且弹出窗
                // hios://com.haier.cellarette.libwebview.activity.NoHiosMainActivity?act={i}1&sku_id={s}value&category_id={s}category_id_value&playing={b}false
                // value为商品的Activity包名sku_id    category_id_value为分类Activity包名category_id
                // hios://com.haier.cellarette.libwebview.activity.NoHiosMainActivity?act={i}1&sku_id={s}341703311759500256
//                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "hios://.activity.NoHiosMainActivity?act={i}1&sku_id={s}2a&category_id={s}3a");
                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "hios://com.haier.cellarette.libwebview.activity.NoHiosMainActivity?act={i}1&sku_id={s}2a&category_id={s}3a");
            }
        });
        findViewById(R.id.tv21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 普通的ActivityAction跳转
                // hios://hs.ac.NoHiosMainActivity{act}                                                                 action跳转
                // hios://hs.ac.NoHiosMainActivity{act}skuId={s}value&category_id={s}category_id_value                  action跳转带参数
                // hios://hs.ac.NoHiosMainActivity{act}?act={i}1&sku_id={s}value&category_id={s}category_id_value       action跳转带参数并且弹出窗
                // value为商品的id category_id_value为分类id
                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "hios://hs.ac.NoHiosMainActivity{act}?act={i}1&sku_id={s}2a&category_id={s}3a");
            }
        });

        findViewById(R.id.tv31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 继承webviewactivity请求接口
                // Activity act = (Activity) container.getContext();
                // HiosHelper.resolveAd(act, mReceiver, "");
                HiosHelper.click(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "1", "");
            }
        });

        findViewById(R.id.tv41).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 继承webviewactivity不请求接口
                // Activity act = (Activity) container.getContext();
                // HiosHelper.resolveAd(act, mReceiver, "");
                // 如果是activity中的fragment 那么 HiosHelper.resolveAd(activity, fragment, "");
                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "http://liangxiao.blog.51cto.com/");
            }
        });

        findViewById(R.id.tv51).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 继承webviewactivity调用JS按钮
                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "file:///android_asset/demo/web.html");
            }
        });

        findViewById(R.id.tv61).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 嵌套webview布局
                HiosHelper.resolveAd(DemoWebviewMainActivity.this, DemoWebviewMainActivity.this, "hios://ad.web.page.part{act}");
            }
        });

        //测试
//                double aa = 100.0000;
//                final BigDecimal bg = new BigDecimal(aa).setScale(2,
//                        RoundingMode.HALF_UP);
//                if (bg.doubleValue() <= 0.0) {
//                    tv3.setText("0.00");
//                } else {
//                    tv3.setText(bg + "");
//                }
    }
}
