package com.example.slbappindex.fragment.fragment2.comm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.FragmentContent21Img;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.FragmentContent22Video;
import com.example.slbappindex.fragment.fragment2.utils.CopyAssetsToLujingUtils;
import com.example.slbappindex.fragment.fragment2.utils.DownloadServiceMp4;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;

import java.io.File;
import java.io.FileFilter;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.glide.ImageLoader;
import cc.shinichi.sherlockutillibrary.utility.ui.ToastUtil;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


/**
 * Created by shining on 2017/2/27 0027.
 */

public class Fragment2Factory {

    private static Fragment2Factory sInstance;
    private Context mContext;

    private Fragment2Factory(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static synchronized Fragment2Factory getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Fragment2Factory(context);
        }
        return sInstance;
    }


    public static final String BC_fragment21 = "com.bc.fragment21Img";
    public static final String BC_fragment22 = "com.bc.fragment22Video";

    public FileFilter mFilterImg = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".png") || pathname.getName().endsWith(".jpeg") || pathname.getName().endsWith(".jpg");
        }
    };

    public FileFilter mFilterMusic = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".mp3");
        }
    };

    public FileFilter mFilterVideo = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".mp4");
        }
    };

    /**
     * 发广播通知fragment21和fragment22的列表变化了
     *
     * @param context
     */
    public void set_brc_fragment21and22_change(Context context) {
        // 切换后不下拉刷新但是执行传递信息操作bufen
        Intent msgIntent21 = new Intent();
        Intent msgIntent22 = new Intent();
        msgIntent21.setAction(Fragment2Factory.BC_fragment21);
        msgIntent22.setAction(Fragment2Factory.BC_fragment22);
        LocalBroadcastManagers.getInstance(context).sendBroadcast(msgIntent21);
        LocalBroadcastManagers.getInstance(context).sendBroadcast(msgIntent22);
    }

    /**
     * viewpager页大小
     */
    public static int PAGE_COUNT = 2;
    /**
     * viewpager每页的itemview id
     */
    public static String PAGE_LAYOUT_ID = "activity_fragment2_layout_pager_item_";
    /**
     * 默认显示第几页
     */
    public static int DEFAULT_PAGE_INDEX = 0;

    private static SparseArrayCompat<Class<? extends Fragment>> sIndexFragments = new SparseArrayCompat<>();

    static {

        sIndexFragments.put(R.id.fragment2_pager_index_0_0, FragmentContent21Img.class);//模块1
//        sIndexFragments.put(R.id.fragment2_pager_index_1_0, FragmentContent22Demo.class);//模块2
        sIndexFragments.put(R.id.fragment2_pager_index_1_0, FragmentContent22Video.class);//模块2

    }

    public static Class<? extends Fragment> get(int id) {
        if (sIndexFragments.indexOfKey(id) < 0) {
            throw new UnsupportedOperationException("cannot find fragment by " + id);
        }
        return sIndexFragments.get(id);
    }

    public static SparseArrayCompat<Class<? extends Fragment>> get() {
        return sIndexFragments;
    }


    /**
     * -------------------demobufen-----------------------
     */
    /**
     * 网络大图预览bufen
     *
     * @param context
     * @param flag
     */
    public void jump_img_net_yulan(final Context context, final int flag) {
        String title = "";
        String message = "";
        String btn_left = "";
        String btn_right = "";
        if (flag == 1) {
            title = "温馨提示";
            message = "网络请求图片demo";
            btn_left = "删除图片缓存";
            btn_right = "获取图片";
        }
        if (flag == 2) {
            title = "温馨提示";
            message = "本地视频复制demo";
            btn_left = "播放网络视频";
            btn_right = "添加本地视频到sd";
        }
        final AlertView dialog = new AlertView(context, title, message,
                btn_left, btn_right);
        dialog.show();
        dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                    @Override
                                    public void doLeft() {
                                        if (flag == 1) {
                                            ImageLoader.cleanDiskCache(context);
                                            ToastUtil.getInstance()._short(context, "磁盘缓存已成功清除");
                                        }
                                        if (flag == 2) {
                                            Jzvd.startFullscreen(context, JzvdStd.class, CopyAssetsToLujingUtils.getInstance(context).images_video[1], "网络视频播放");
                                        }
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void doRight() {
                                        dialog.dismiss();
                                        if (flag == 1) {
                                            // 打开网络图片并下载到指定路径
                                            ImagePreview
                                                    .getInstance()
                                                    .setContext(context)
                                                    .setIndex(0)
                                                    .setImageInfoList(CopyAssetsToLujingUtils.getInstance(context).get_img_info_list())
                                                    .setShowDownButton(true)
                                                    .setLoadStrategy(ImagePreview.LoadStrategy.Default)
                                                    .setFolderName(ConstantsUtils.gen_img)
                                                    .setScaleLevel(1, 3, 8)
                                                    .setZoomTransitionDuration(500)
                                                    .setShowCloseButton(true)
                                                    .setEnableDragClose(true)// 是否启用上拉/下拉关闭，默认不启用
                                                    .setEnableClickClose(true)// 是否启用点击图片关闭，默认启用
                                                    .start();
                                        } else if (flag == 2) {
                                            // 复制本地视频到指定路径
                                            Intent intent = new Intent("hs.act.slbapp.DownloadServiceMp4");
                                            intent.setPackage(BaseApp.get().getPackageName());
                                            for (String url : CopyAssetsToLujingUtils.getInstance(context).urlList) {
                                                intent.putExtra(DownloadServiceMp4.DOWNLOAD_URL, url);
                                                context.startService(intent);
                                            }
                                        }
                                    }
                                }
        );
    }


}
