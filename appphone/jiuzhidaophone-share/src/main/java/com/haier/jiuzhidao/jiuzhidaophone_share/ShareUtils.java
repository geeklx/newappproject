package com.haier.jiuzhidao.jiuzhidaophone_share;

import android.content.Context;
import android.os.Environment;

import com.haier.cellarette.baselibrary.toasts2.Toasty;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

public class ShareUtils {


    public static void showShare(String name, final Context mc) {

        Platform.ShareParams sp = new Platform.ShareParams();
//
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        sp.setTitle("一键分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        sp.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        sp.setText("一键分享");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        sp.setImagePath(Environment.getExternalStorageDirectory() + "/1/a.jpg");// 确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        sp.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        sp.setComment("我是ShareSdk，我已在手，天下我有");
        // site是分享此内容的网站名称，仅在QQ空间使用
        sp.setSite("网站名称");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        sp.setSiteUrl("http://sharesdk.cn");

        sp.setShareType(Platform.SHARE_WEBPAGE);// 一定要设置分享属性
        Platform platform = ShareSDK.getPlatform(name);
// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        platform.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                Toasty.info(mc, "失败" + arg1 + "--" + arg2.toString()).show();
            }

            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                //分享成功的回调
                Toasty.info(mc, "成功").show();
            }

            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
                Toasty.info(mc, "取消").show();
            }
        });
// 执行图文分享
        platform.share(sp);
    }

}
