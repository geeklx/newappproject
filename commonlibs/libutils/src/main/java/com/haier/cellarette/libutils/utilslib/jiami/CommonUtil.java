package com.haier.cellarette.libutils.utilslib.jiami;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;


public class CommonUtil {
    public static void copy(Context context, String text) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    public static String getEncryptToken(int id, String method, String date, String params) {
        try {
            String base64Md5Params = Base64.encodeToString(Md5Utils.getMd5Bytes(params), Base64.NO_WRAP);
            String str = id + "\n" + method + "\n" + date +"\n"+ base64Md5Params + "\n";
            byte[] bytes = EncryptUtils.hmacSHA1Encrypt2(str);
            String ret = Base64.encodeToString(bytes, Base64.NO_WRAP);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
