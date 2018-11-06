package com.example.slbappreadbook;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.slbappreadbook.adapter.BasemusicAdapter2;
import com.example.slbappreadbook.callback.PreparedCallBack;
import com.example.slbappreadbook.callback.SeekBarListener;
import com.example.slbappreadbook.domain.BaseBean2;
import com.example.slbappreadbook.domain.HuibenFileCacheBean;
import com.example.slbappreadbook.huancun.HuibenFlieCacheBaseBean2Manager;
import com.example.slbappreadbook.pagertransformer.AccordionTransformer2;
import com.example.slbappreadbook.pagertransformer.ZoomOutTransformer2;
import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.imgothertobitmap.ImgOtherToBitmap;
import com.haier.cellarette.baselibrary.toasts.ToastUtil;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.ArrayList;
import java.util.List;

public class ReadBookActivityBeiben extends BaseActivity {

    private int current = -1;
    private int current_tag = -1;
    private boolean is_first;
    private MusicPlayerService.MyBinder mBinder;
    private boolean flag;
    private List<BaseBean2> items;
    private CoolViewPager vp;
    private FrameLayout fl1;
    private LinearLayout ll2;
    private TextView tv_back;
    private TextView tv_play;
    private SeekBar sb_progressbar;
    private TextView tv_strings;
    private BasemusicAdapter2 adapter;
    private int mVideoWidth;
    private int mVideoHeight;

    public static final int[] ids = new int[]{111, 222, 333, 444, 555};
    public static int[] drawableList1 = new int[]{R.drawable.i1,
            R.drawable.i2,
            R.drawable.i5,
            R.drawable.img3,
            R.drawable.i6
    };
    public static String[] drawableList2 = new String[]{"https://wx4.sinaimg.cn/mw1024/60d2c107ly1fvend50aqaj20bl0fsjvm.jpg",
            "https://wx4.sinaimg.cn/mw1024/60d2c107ly1fvend5iqztj20g90fnjxn.jpg",
            "http://wx1.sinaimg.cn/large/60d2c107ly1ft8k94ri8sj20qo140djg.jpg",
            "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif",
            "http://wx3.sinaimg.cn/large/60d2c107ly1ftudgzpoufg20ba0k0jxc.gif"
    };

    public List<BaseBean2> getListDrawableBean() {
        // 本地bufen
//        List<BaseBean2> items = new ArrayList<>();
//        for (int i = 0; i < drawableList1.length; i++) {
//            BaseBean2 baseBean = new BaseBean2();
//            baseBean.setId(ids[i]);
//            Bitmap bitmap1 = HuibenImgOtherToBitmap.drawableToBitmap(drawableList1[i]);
//            Bitmap bitmap2 = HuibenImgOtherToBitmap.downloadUrlToBitmap(drawableList2[i]);
//            baseBean.setDrawable(drawableList1[i]);
//            baseBean.setUrl(drawableList2[i]);
//            baseBean.setBitmap(bitmap2);
//            items.add(baseBean);
//        }
        // 网络bufen
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<BaseBean2> items = new ArrayList<>();
                    for (int i = 0; i < drawableList1.length; i++) {
                        BaseBean2 baseBean = new BaseBean2();
                        baseBean.setId(ids[i]);
                        Bitmap bitmap1 = ImgOtherToBitmap.drawableToBitmap(drawableList1[i]);
                        Bitmap bitmap2 = ImgOtherToBitmap.downloadUrlToBitmap(drawableList2[i]);
                        baseBean.setDrawable(drawableList1[i]);
                        baseBean.setUrl(drawableList2[i]);
                        baseBean.setBitmap(bitmap2);
                        items.add(baseBean);
                    }
                    Message msg = Message.obtain();
                    msg.obj = items;
                    img_handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return items;
    }


    private Handler img_handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            List<BaseBean2> items = (List<BaseBean2>) message.obj;
            mGoodsList = new ArrayList<>();
            if (items.size() == 0) {
                // 未联网
                adapter.setData(chushihua_hashmap());// 拿出缓存bufen
            } else {
                // 联网
                huiben_txt(items);//写入缓存bufen
                adapter.setData(items);// 网络
            }
//            adapter.setData(chushihua_hashmap());// 拿出缓存bufen
//            adapter.notifyDataSetChanged();
            vp.setAdapter(adapter);
            sb_progressbar.setMax(adapter.getCount() - 1);
            return true;
        }
    });

    private String TXT_ID = "10086";// 以商品的id为txt的名字
    private ArrayList<HuibenFileCacheBean> mGoodsList;
    private int id = 100;// 0x100

    private List<BaseBean2> chushihua_hashmap() {
        List<BaseBean2> mList = new ArrayList<>();
        mGoodsList = HuibenFlieCacheBaseBean2Manager.getInstance().getListBean(TXT_ID);
        String aaa = "";
        for (int i = 0; i < mGoodsList.size(); i++) {
//            Bitmap bitmap = HuibenImgOtherToBitmap.downloadUrlToBitmap();
            BaseBean2 baseBean2 = new BaseBean2(Integer.valueOf(mGoodsList.get(i).getId()),
                    R.drawable.i1, mGoodsList.get(i).getUrl());
            mList.add(baseBean2);
        }
        return mList;
    }


    /**
     * 写入缓存中的TXT 文件bufen
     */
    public void huiben_txt(List<BaseBean2> items) {
        for (int i = 0; i < items.size(); i++) {
            mGoodsList.add(new HuibenFileCacheBean(items.get(i).getId() + "", items.get(i).getUrl()));
        }
        HuibenFlieCacheBaseBean2Manager.getInstance().AddHashMap(TXT_ID, mGoodsList);
//        chushihua_hashmap();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null && mBinder.isService()) {
            unbindService(conn);
            stopService(new Intent(this, MusicPlayerService.class));
//            this.finish();
        } else {
//            this.finish();
        }
        // 写入缓存bufen
        HuibenFlieCacheBaseBean2Manager.getInstance().AddHashMap(TXT_ID, mGoodsList);
    }

    @Override
    public void onBackPressed() {
        if (is_dianji) {
            //缩小动画
            is_dianji = false;
            dianji_onplay();
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main41;
    }

    private void initViews() {
        vp = findViewById(R.id.vp);
        tv_strings = findViewById(R.id.tv_strings);
        sb_progressbar = findViewById(R.id.sb_progressbar);
        fl1 = findViewById(R.id.fl1);
        ll2 = findViewById(R.id.ll2);
        tv_back = findViewById(R.id.tv_back);
        tv_back.setVisibility(View.GONE);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_play = findViewById(R.id.tv_play);
        FrameLayout.LayoutParams ll_param = (FrameLayout.LayoutParams) fl1.getLayoutParams();
        ll_param.width = 800;
        ll_param.height = 1200;
        mVideoWidth = ll_param.width;
        mVideoHeight = ll_param.height;
        fl1.setLayoutParams(ll_param);
        ll2.setVisibility(View.GONE);
        tv_strings.setText("");

        initData();
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MusicPlayerService.MyBinder) service;
            flag = true;
            mBinder.getService().setOnPrepared(pcb);
            // 数据显示bufen
            tv_strings.setText(getString(R.string.image_counts, current + 1, adapter.getCount()));
            sb_progressbar.setProgress(current);
            // 音乐bufen
            if (flag) {
                mBinder.musicStart(ReadBookActivityBeiben.this, "mp3/" + adapter.getContacts(current).getId() + ".mp3");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private PreparedCallBack pcb = new PreparedCallBack() {
        @Override
        public void isPrepared(boolean prepared) {
            if (!prepared) {
                //next
                if (current + 1 > adapter.getCount() - 1) {
                    ToastUtil.showToastLong("最后一页");
                    return;
                }
                BaseBean2 model = adapter.getNext(current);
                vp.setCurrentItem(current + 1);

            }
        }
    };


    private void initData() {
        items = new ArrayList<>();
        items = getListDrawableBean();// 网络
        adapter = new BasemusicAdapter2(this, items);
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        vp.setAutoScroll(false);
        vp.setAutoScrollDirection(CoolViewPager.AutoScrollDirection.BACKWARD);
        vp.setInfiniteLoop(false);
        vp.setScrollDuration(true, 600);
        vp.setDrawEdgeEffect(true);
        vp.setEdgeEffectColor(getResources().getColor(R.color.colorPrimary));
        vp.toggleAutoScrollDirection();
        vp.setPageTransformer(false, new ZoomOutTransformer2());

        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new CoolViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                MyLogUtil.e("----第几个pos--onPageScrolled--", i + "");
                current = i;
                BaseBean2 model = adapter.getContacts(i);
                //播放音乐bufen
                if (!is_first) {
                    is_first = true;
                    sb_progressbar.setMax(adapter.getCount() - 1);
                    return;
                }
                if (i != current_tag) { // 1
                    current_tag = i;// 0
                    // 数据显示bufen
                    tv_strings.setText(getString(R.string.image_counts, current + 1, adapter.getCount()));
                    sb_progressbar.setProgress(current);
                    // 音乐bufen
                    if (mBinder != null) {
                        mBinder.musicNext(ReadBookActivityBeiben.this, "mp3/" + model.getId() + ".mp3");
                    }
                }
            }

            @Override
            public void onPageSelected(int i) {
                MyLogUtil.e("----第几个pos--onPageSelected--", i + "");

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                MyLogUtil.e("----第几个pos--onPageScrollStateChanged--", i + "");
            }
        });
        sb_progressbar.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                int type = (int) seekBar.getTag();
                if (fromUser) {
                    mBinder.musicStop();
                    vp.setCurrentItem(progress);
                }

            }
        });
    }

    private boolean is_dianji;

    /**
     * 视图缩小放大操作bufen
     *
     * @param view
     */
    public void FL1(View view) {
        if (mVideoWidth < 1080 && mVideoHeight < 1920) {
            is_dianji = true;
            vp.setPageTransformer(false, new AccordionTransformer2());
            dianji_play();
        } else {
            is_dianji = false;
            dianji_onplay();
        }
    }

    private void dianji_play() {
        //放大
        fl1.animate().scaleX(1.6f).scaleY(1.6f).start();

        ll2.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_play.setVisibility(View.GONE);

        Intent intent = new Intent(this, MusicPlayerService.class);
        intent.putExtra("flag", flag);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private void dianji_onplay() {
        //缩小
        fl1.animate().scaleX(1.0f).scaleY(1.0f).start();
        ll2.setVisibility(View.GONE);
        tv_back.setVisibility(View.GONE);
        tv_play.setVisibility(View.VISIBLE);
        if (mBinder.isService()) {
            unbindService(conn);
            stopService(new Intent(this, MusicPlayerService.class));
            mBinder = null;
        }
    }


}
