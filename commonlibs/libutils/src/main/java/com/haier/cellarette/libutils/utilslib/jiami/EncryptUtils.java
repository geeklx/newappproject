package com.haier.cellarette.libutils.utilslib.jiami;

import android.provider.SyncStateContract;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private static final String KEY = "123456";

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     *                    //     * @param KEY  密钥
     * @return
     * @throws Exception
     */
    public static String hmacSHA1Encrypt(String encryptText) throws Exception {
        byte[] data = KEY.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        String ret = Base64.encodeToString(mac.doFinal(text), Base64.NO_WRAP);
        return ret;
    }
    public static byte[] hmacSHA1Encrypt2(String encryptText) throws Exception {
        byte[] data = KEY.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        return mac.doFinal(text);
    }
    public static byte[] getHmacSHA1(String src)
            throws NoSuchAlgorithmException, UnsupportedEncodingException,
            InvalidKeyException {
        byte[] data = KEY.getBytes(ENCODING);
        Mac mac = Mac.getInstance("HmacSHA1");
//        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        SecretKeySpec secret = new SecretKeySpec(data, mac.getAlgorithm());
        mac.init(secret);
        System.out.println("ppppp:"+mac.doFinal(src.getBytes()).length);
        return mac.doFinal(src.getBytes(ENCODING));
    }
}
