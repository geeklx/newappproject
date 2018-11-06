package com.example.slbappshare.fenxiang.beans;

import android.graphics.Bitmap;

import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;

public class WeixinBeanParam {

    /**
     * 分享文本
     */
    public static ShareParams share_wenben() {
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_TEXT);
        shareParams.setText("Text");//必须
        return shareParams;
    }

    /**
     * 分享图片1 路径
     */
    public static ShareParams share_img1(String img_url) {
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setImagePath(img_url);
        return shareParams;
    }

    /**
     * 分享图片2 bitmap
     */
    public static ShareParams share_img2(Bitmap bitmap) {
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setImageData(bitmap);
        return shareParams;
    }

    /**
     * 分享音乐
     */
    public static ShareParams share_music(String share_title, String share_text, String url, String music_url, String fileurl) {
        ShareParams shareParams = new ShareParams();
        shareParams.setTitle(share_title);
        shareParams.setText(share_text);
        shareParams.setShareType(Platform.SHARE_MUSIC);
        shareParams.setUrl(url);
        shareParams.setMusicUrl(music_url);
        shareParams.setImagePath(fileurl);
        return shareParams;
    }

    /**
     * 分享视频
     */
    public static ShareParams share_video(String share_title, String share_text, String url, String share_videourl, String fileurl) {
        ShareParams shareParams = new ShareParams();
        shareParams.setTitle(share_title);
        shareParams.setText(share_text);
        shareParams.setShareType(Platform.SHARE_VIDEO);
        shareParams.setUrl(share_videourl);
        shareParams.setImagePath(fileurl);
        return shareParams;
    }

    /**
     * 分享网页
     */
    public static ShareParams share_web(String share_title, String share_text, String url,  String fileurl) {
        ShareParams shareParams = new ShareParams();
        shareParams.setTitle(share_title);
        shareParams.setText(share_text);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(url);//必须
        shareParams.setImagePath(fileurl);
        return shareParams;
    }

    /**
     * 分享网页
     */
    public static ShareParams share_web2(String share_title, String share_text, String url,  Bitmap fileurl) {
        ShareParams shareParams = new ShareParams();
        shareParams.setTitle(share_title);
        shareParams.setText(share_text);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setUrl(url);//必须
        shareParams.setImageData(fileurl);
        return shareParams;
    }


}
