//package com.example.slbappindex.fragment.fragment2.comm.demo;
//
//import com.blankj.utilcode.util.FileUtils;
//import com.haier.cellarette.baselibrary.common.ConstantsUtils;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileAllUrlUtils {
//
//
////    public static FileFilter mFilterImg = new FileFilter() {
////        @Override
////        public boolean accept(File pathname) {
////            return pathname.getName().endsWith(".png") || pathname.getName().endsWith(".jpeg") || pathname.getName().endsWith(".jpg");
////        }
////    };
////
////    public static FileFilter mFilterMusic = new FileFilter() {
////        @Override
////        public boolean accept(File pathname) {
////            return pathname.getName().endsWith(".mp3");
////        }
////    };
////
////    public static FileFilter mFilterVideo = new FileFilter() {
////        @Override
////        public boolean accept(File pathname) {
////            return pathname.getName().endsWith(".mp4");
////        }
////    };
//
//    public static void GetlistFilesInDirWithFilter(String PATH_FILE) {
//        System.out.println(FileUtils.listFilesInDirWithFilter(PATH_FILE, mFilterImg, false).toString());
//        System.out.println(FileUtils.listFilesInDirWithFilter(PATH_FILE, mFilterMusic, true).toString());
//        System.out.println(FileUtils.listFilesInDirWithFilter(PATH_FILE, mFilterVideo, true).toString());
//    }
//
//    public static void get_phone_img() {
//        // 获取缓存路径下的所有图片文件列表bufen
//        List<File> img_list = new ArrayList<>();
//        List<File> mp3_list = new ArrayList<>();
//        List<File> mp4_list = new ArrayList<>();
//        List<String> img_list_string = new ArrayList<>();
//        List<String> mp3_list_string = new ArrayList<>();
//        List<String> mp4_list_string = new ArrayList<>();
//        if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_img) &&
//                FileUtils.createOrExistsDir(ConstantsUtils.file_url_mp3) &&
//                FileUtils.createOrExistsDir(ConstantsUtils.file_url_mp4)) {
//            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_img, FileAllUrlUtils.mFilterImg, false).toString());
//            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp3, FileAllUrlUtils.mFilterMusic, true).toString());
//            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp4, FileAllUrlUtils.mFilterVideo, true).toString());
//            img_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_img, FileAllUrlUtils.mFilterImg, false);
//            mp3_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp3, FileAllUrlUtils.mFilterMusic, true);
//            mp4_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp4, FileAllUrlUtils.mFilterVideo, true);
//            for (int i = 0; i < img_list.size(); i++) {
//                String currentItemOriginPathUrl = img_list.get(i).getAbsolutePath();
//                String name = currentItemOriginPathUrl.substring(currentItemOriginPathUrl.lastIndexOf("/") + 1, currentItemOriginPathUrl.length());
//            }
//        }
//    }
//
//    /**
//     * 获取缓存路径下的所有图片文件列表bufen
//     */
//    public static void get_phone_fileimg_all() {
//        List<File> img_list = new ArrayList<>();
//        List<String> img_list_string = new ArrayList<>();
//        if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_img)) {
//            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_img, FileAllUrlUtils.mFilterImg, false).toString());
//            img_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_img, FileAllUrlUtils.mFilterImg, false);
//            for (int i = 0; i < img_list.size(); i++) {
//                File file_url = img_list.get(i);//图片的file
//                String string_url = img_list.get(i).getAbsolutePath();//图片的url
////                String name = currentItemOriginPathUrl.substring(currentItemOriginPathUrl.lastIndexOf("/") + 1, currentItemOriginPathUrl.length());
//                String name = img_list.get(i).getName();//图片的name
//
//
//            }
//        }
//    }
//
//    /**
//     * 获取缓存路径下的所有视频文件列表bufen
//     */
//    public static void get_phone_filevideo_all() {
//        List<File> img_list = new ArrayList<>();
//        List<String> img_list_string = new ArrayList<>();
//        if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_mp4)) {
//            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp4, FileAllUrlUtils.mFilterVideo, false).toString());
//            img_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp4, FileAllUrlUtils.mFilterVideo, false);
//            for (int i = 0; i < img_list.size(); i++) {
//                File file_url = img_list.get(i);//视频的file
//                String string_url = img_list.get(i).getAbsolutePath();//视频的url
////                String name = currentItemOriginPathUrl.substring(currentItemOriginPathUrl.lastIndexOf("/") + 1, currentItemOriginPathUrl.length());
//                String name = img_list.get(i).getName();//视频的name
//
//
//            }
//        }
//    }
//
//    /**
//     * 获取缓存路径下的所有音频文件列表bufen
//     */
//    public static void get_phone_filemusic_all() {
//        List<File> img_list = new ArrayList<>();
//        List<String> img_list_string = new ArrayList<>();
//        if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_mp3)) {
//            System.out.println(FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp3, FileAllUrlUtils.mFilterMusic, true).toString());
//            img_list = FileUtils.listFilesInDirWithFilter(ConstantsUtils.file_url_mp3, FileAllUrlUtils.mFilterMusic, true);
//            for (int i = 0; i < img_list.size(); i++) {
//                File file_url = img_list.get(i);//视频的file
//                String string_url = img_list.get(i).getAbsolutePath();//视频的url
////                String name = currentItemOriginPathUrl.substring(currentItemOriginPathUrl.lastIndexOf("/") + 1, currentItemOriginPathUrl.length());
//                String name = img_list.get(i).getName();//视频的name
//
//
//            }
//        }
//    }
//
//}
