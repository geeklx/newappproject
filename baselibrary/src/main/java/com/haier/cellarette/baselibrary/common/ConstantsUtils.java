package com.haier.cellarette.baselibrary.common;

import android.os.Environment;

public class ConstantsUtils {
    public static final String gen = "slbappgen";
    public static final String file_url = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String gen_img = gen + FILE_SEP + "img";
    public static final String gen_mp3 = gen + FILE_SEP + "music";
    public static final String gen_mp4 = gen + FILE_SEP + "video";
    public static final String gen_apk = gen + FILE_SEP + "apk";
    public static final String gen_wenjian = gen + FILE_SEP + "wenjian";

    public static final String file_url_img = file_url + FILE_SEP + gen_img + FILE_SEP;//图片全路径
    public static final String file_url_mp3 = file_url + FILE_SEP + gen_mp3 + FILE_SEP;//音乐全路径
    public static final String file_url_mp4 = file_url + FILE_SEP + gen_mp4 + FILE_SEP;//视频全路径
    public static final String file_url_apk = file_url + FILE_SEP + gen_apk + FILE_SEP;//下载apk的全路径
    public static final String file_url_wenjian = file_url + FILE_SEP + gen_wenjian + FILE_SEP;//下载文件的全路径


}
