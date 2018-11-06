package com.example.slbappindex.fragment.fragment2.utils;

import android.content.Context;

import com.blankj.utilcode.util.FileUtils;
import com.example.slbappindex.fragment.fragment2.comm.Fragment2Factory;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ChildImgBean;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ImgBean;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22ChildVideoBean;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment22VideoBean;
import com.haier.cellarette.baselibrary.common.BaseApp;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.libutils.utilslib.etc.DateUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cc.shinichi.library.bean.ImageInfo;

public class CopyAssetsToLujingUtils {

    private static CopyAssetsToLujingUtils sInstance;
    private Context mContext;

    private CopyAssetsToLujingUtils(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static synchronized CopyAssetsToLujingUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CopyAssetsToLujingUtils(context);
        }
        return sInstance;
    }


    /**
     * ----------------------demo 图片-----------------------
     */

    public List<Fragment21ImgBean> getMultipleItemData(int lenth) {
        List<Fragment21ImgBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Fragment21ChildImgBean status = new Fragment21ChildImgBean();
            status.setUserName(DateUtil.format_ymd(new Date()));
            status.setCreatedAt(DateUtil.format_ymd(new Date()));
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar(ConstantsUtils.file_url_img + "1540437944518.jpeg");
            status.setText("长按图片可删除");

            list.add(new Fragment21ImgBean(Fragment21ImgBean.style2, status));
            list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));
            list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));
            list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));
            list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));
            list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));
            list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));


        }

        return list;
    }

    /**
     * 获取图片路径并显示出来
     *
     * @return
     */
    public List<Fragment21ImgBean> getMultipleItemDataUrlImg() {
        List<Fragment21ImgBean> list = new ArrayList<>();
        List<File> img_list = new ArrayList<>();
        if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_img)) {
            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_img, Fragment2Factory.getInstance(BaseApp.get()).mFilterImg, false).toString());
            img_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_img, Fragment2Factory.getInstance(BaseApp.get()).mFilterImg, false);
            for (int i = 0; i < img_list.size(); i++) {
                File img_file = img_list.get(i);//图片file
                String currentItemOriginPathUrl = img_list.get(i).getAbsolutePath();//图片路径
                String bean1 = img_list.get(i).getName();
//                String bean2 = System.currentTimeMillis() + "";// DateUtil.format_ymd(new Date())
                String bean_time = "";
                if (i % 2 == 0) {
                    bean_time = "1540461022";
                } else {
                    bean_time = "1540374622";
                }

//                String aaa = DateUtil.format_ymd(TimeUtils.string2Date(bean_time));// 2018-10-25
//                String aaa = DateUtil.format(new Date(Long.valueOf(bean_time) * 1000),
//                        DateUtil.FORMATER_YMD);

                Fragment21ChildImgBean status = new Fragment21ChildImgBean();
                status.setUserName(bean1);
                status.setCreatedAt(bean_time);
                status.setRetweet(false);
                status.setUserAvatar(img_file.toString());
                status.setText("这是一款神奇的工具");
//                list.add(new Fragment21ImgBean(Fragment21ImgBean.style2, new Fragment21ChildImgBean(false,"","","",bean2)));
                list.add(new Fragment21ImgBean(Fragment21ImgBean.style3, status));

            }
        }
        return paixu(list);
    }

    public List<Fragment21ImgBean> paixu(List<Fragment21ImgBean> list) {
        Collections.sort(list, new Comparator<Fragment21ImgBean>() {
            @Override
            public int compare(Fragment21ImgBean lhs, Fragment21ImgBean rhs) {
                return Integer.valueOf(lhs.getmBean().getCreatedAt()) > Integer.valueOf(rhs.getmBean().getCreatedAt()) ? -1 : 1;/*返回1升序，返回-1降序*/
            }
        });
        List<Fragment21ImgBean> list2 = new ArrayList<>();
        list2.addAll(list);

        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                Fragment21ImgBean bean1 = new Fragment21ImgBean(Fragment21ImgBean.style2, new Fragment21ChildImgBean(false, "长按图片可删除", "", "", list.get(i).getmBean().getCreatedAt()));
                list2.add(0, bean1);
                index += 1;
            }
            if ((i + 1) < list.size()) {
                if (!list.get(i).getmBean().getCreatedAt().equals(list.get(i + 1).getmBean().getCreatedAt())) {
                    index += 1;
                    Fragment21ImgBean bean2 = new Fragment21ImgBean(Fragment21ImgBean.style2, new Fragment21ChildImgBean(false, "长按图片可删除", "", "", list.get(i + 1).getmBean().getCreatedAt()));
                    list2.add(index, bean2);
                }
            }
            index++;
        }
        return list2;
    }


    public  String[] images_img = {
            "http://img3.16fan.com/live/origin/201805/21/E421b24c08446.jpg",
            "http://img3.16fan.com/live/origin/201805/21/4D7B35fdf082e.jpg",
            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg", //  5760 * 3840
            "http://img3.16fan.com/live/origin/201805/21/2D02ebc5838e6.jpg",
            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818716180ge.jpeg", //  2280 * 22116
            "http://img3.16fan.com/live/origin/201805/21/14C5e483e7583.jpg",
            "http://img3.16fan.com/live/origin/201805/21/A1B17c5f59b78.jpg",
            "http://img3.16fan.com/live/origin/201805/21/94699b2be3cfa.jpg",
            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818716180ge.jpeg", //  2280 * 22116
            "http://img3.16fan.com/live/origin/201805/21/14C5e483e7583.jpg",
            "http://img3.16fan.com/live/origin/201805/21/EB298ce595dd2.jpg",
            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg", //  5760 * 3840
            "http://img3.16fan.com/live/origin/201805/21/264Ba4860d469.jpg",
            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818716180ge.jpeg", //  2280 * 22116
            "http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg" //  5760 * 3840
    };

    public List<ImageInfo> get_img_info_list() {
        ImageInfo imageInfo;
        final List<ImageInfo> imageInfoList = new ArrayList<>();
        for (String image : images_img) {
            imageInfo = new ImageInfo();
            imageInfo.setOriginUrl(image);// 原图
            imageInfo.setThumbnailUrl(
                    image.concat("-1200"));// 缩略图，实际使用中，根据需求传入缩略图路径。如果没有缩略图url，可以将两项设置为一样，并隐藏查看原图按钮即可。
            imageInfoList.add(imageInfo);
            imageInfo = null;
        }
        return imageInfoList;
    }

    /**
     * ----------------------demo 图片-----------------------
     */


    /**
     * ----------------------demo 视频---------------------------
     */

    /**
     * 获取图片路径并显示出来
     *
     * @return
     */
    public  List<Fragment22VideoBean> getMultipleItemDataUrlVideo() {
        List<Fragment22VideoBean> list = new ArrayList<>();
        List<File> img_list = new ArrayList<>();
        if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_mp4)) {
            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp4, Fragment2Factory.getInstance(BaseApp.get()).getInstance(BaseApp.get()).mFilterVideo, false).toString());
            img_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp4, Fragment2Factory.getInstance(BaseApp.get()).mFilterVideo, false);
            for (int i = 0; i < img_list.size(); i++) {
                File img_file = img_list.get(i);//图片file
                String currentItemOriginPathUrl = img_list.get(i).getAbsolutePath();//图片路径
                String bean1 = img_list.get(i).getName();
//                String bean2 = System.currentTimeMillis() + "";// DateUtil.format_ymd(new Date())
                String bean_time = "";
                if (i % 2 == 0) {
                    bean_time = "1540461022";
                } else {
                    bean_time = "1540374622";
                }

//                String aaa = DateUtil.format_ymd(TimeUtils.string2Date(bean_time));// 2018-10-25
//                String aaa = DateUtil.format(new Date(Long.valueOf(bean_time) * 1000),
//                        DateUtil.FORMATER_YMD);

                Fragment22ChildVideoBean status = new Fragment22ChildVideoBean();
                status.setUserName(bean1);
                status.setCreatedAt(bean_time);
                status.setRetweet(true);
                status.setUserAvatar(img_file.toString());
                status.setText("这是一款神奇的工具");
//                list.add(new Fragment21ImgBean(Fragment21ImgBean.style2, new Fragment22ChildVideoBean(false,"","","",bean2)));
                list.add(new Fragment22VideoBean(Fragment22VideoBean.style3, status));

            }
        }
        return paixu_video(list);
    }

    public List<Fragment22VideoBean> paixu_video(List<Fragment22VideoBean> list) {
        Collections.sort(list, new Comparator<Fragment22VideoBean>() {
            @Override
            public int compare(Fragment22VideoBean lhs, Fragment22VideoBean rhs) {
                return Integer.valueOf(lhs.getmBean().getCreatedAt()) > Integer.valueOf(rhs.getmBean().getCreatedAt()) ? -1 : 1;/*返回1升序，返回-1降序*/
            }
        });
        List<Fragment22VideoBean> list2 = new ArrayList<>();
        list2.addAll(list);

        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                Fragment22VideoBean bean1 = new Fragment22VideoBean(Fragment22VideoBean.style2, new Fragment22ChildVideoBean(false, "长按图片可删除", "", "", list.get(i).getmBean().getCreatedAt()));
                list2.add(0, bean1);
                index += 1;
            }
            if ((i + 1) < list.size()) {
                if (!list.get(i).getmBean().getCreatedAt().equals(list.get(i + 1).getmBean().getCreatedAt())) {
                    index += 1;
                    Fragment22VideoBean bean2 = new Fragment22VideoBean(Fragment22VideoBean.style2, new Fragment22ChildVideoBean(false, "长按图片可删除", "", "", list.get(i + 1).getmBean().getCreatedAt()));
                    list2.add(index, bean2);
                }
            }
            index++;
        }
        return list2;
    }


    // 网络视频地址
    public String[] images_video = {
            "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
            "http://p8eixp1u7.bkt.clouddn.com/temp_video.mp4"
    };
    // 本地视频地址
    public  String[] strings = new String[]{"mp4/a.mp4",
            "mp4/b.mp4"};
    public  List<String> urlList = Arrays.asList(strings);

    /**
     * ----------------------demo 视频---------------------------
     */

    /**
     * 复制video到硬盘路径下
     *
     * @param src  assets路径下支持子路径 如：assets下a.MP4 或 assets下 mp4/a.mp4
     * @param name
     * @param flag
     * @return
     */
    public File copyResurces(String src, String name, int flag) {
        File filesDir = null;
        try {
            if (flag == 0) {//copy to sdcard
                filesDir = new File(ConstantsUtils.file_url_mp4 + name);
                File parentDir = filesDir.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
            } else {//copy to data
                filesDir = new File(BaseApp.get().getFilesDir(), name);
            }
            if (!filesDir.exists()) {
                filesDir.createNewFile();
                InputStream open = BaseApp.get().getAssets().open(src);
                FileOutputStream fileOutputStream = new FileOutputStream(filesDir);
                byte[] buffer = new byte[4 * 1024];
                int len = 0;
                while ((len = open.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
                open.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (flag == 0) {
                filesDir = copyResurces(src, name, 1);
            }
        }
        return filesDir;
    }

}
