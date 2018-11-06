package com.haier.cellarette.libglide37.glidenetpicpressnormal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * 动态设置 点击事件 selector 的工具类  可以从本地添加  也可以从网络添加
 * SelectorUtil.addSeletorFromNet(SecondIndexFragmentAdapter.class,data.getDrawbleDown(),data.getDrawbleUp(),imageView,context);
 * Created by suwenlai on 16-12-26.
 */

public class SelectorUtil {

    private static Context mContext;
    private static Drawable drawable;

    /**
     * 从 drawable 获取图片 id 给 Imageview 添加 selector
     *
     * @param context  调用方法的 Activity
     * @param idNormal 默认图片的 id
     * @param idPress  点击图片的 id
     * @param iv       点击的 view
     */
    public static void addSelectorFromDrawable(Context context, int idNormal, int idPress, ImageView iv) {

        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = context.getResources().getDrawable(idNormal);
        Drawable press = context.getResources().getDrawable(idPress);
        drawable.addState(new int[]{android.R.attr.state_pressed}, press);
        drawable.addState(new int[]{-android.R.attr.state_pressed}, normal);
        iv.setBackground(drawable);
    }

    /**
     * 从 drawable 获取图片 id 给 Button 添加 selector
     *
     * @param context  调用方法的 Activity
     * @param idNormal 默认图片的 id
     * @param idPress  点击图片的 id
     * @param button   点击的 view
     */

    public static void addSelectorFromDrawable(Context context, int idNormal, int idPress, Button button) {

        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = context.getResources().getDrawable(idNormal);
        Drawable press = context.getResources().getDrawable(idPress);
        drawable.addState(new int[]{android.R.attr.state_pressed}, press);
        drawable.addState(new int[]{-android.R.attr.state_pressed}, normal);
        button.setBackground(drawable);
    }

    /**
     * 从网络获取图片 给 ImageView 设置 selector
     *
     * @param clazz     调用方法的类
     * @param normalUrl 获取默认图片的链接
     * @param pressUrl  获取点击图片的链接
     * @param img       点击的 view
     * @param context
     */
    public static void addSeletorFromNet(final Class clazz, final String normalUrl, final String pressUrl, final ImageView img, final int width, final int height, Context context) {
        mContext = context;
        new AsyncTask<Void, Void, Drawable>() {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected Drawable doInBackground(Void... params) {

                StateListDrawable drawable = new StateListDrawable();
                Drawable normal = loadImageFromNet(clazz, normalUrl, img,width, height);
                Drawable press = loadImageFromNet(clazz, pressUrl, img, width, height);
                drawable.addState(new int[]{android.R.attr.state_pressed}, press);
                drawable.addState(new int[]{-android.R.attr.state_pressed}, normal);
                return drawable;
            }

            @Override
            protected void onPostExecute(Drawable drawable1) {
                img.setBackground(drawable1);
            }
        }.execute();

    }

    /**
     * 从网络获取图片
     *
     * @param clazz  调用方法的类
     * @param netUrl 获取图片的链接
     * @param img
     * @return 返回一个 drawable 类型的图片
     */
    private static Drawable loadImageFromNet(Class clazz, String netUrl, ImageView img, int width, int height) {

        try {
//            ViewGroup.LayoutParams para = img.getLayoutParams();
//            para.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            para.height = ViewGroup.LayoutParams.MATCH_PARENT;
            Bitmap myBitmap = Glide.with(mContext)
                    .load(netUrl)
                    .asBitmap()
                    .centerCrop()
                    .into(width, height)
                    .get();

            drawable = new BitmapDrawable(mContext.getResources(), myBitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return drawable;
    }

    /**
     * 从网络获取图片
     * @param clazz 调用方法的类
     * @param netUrl 获取图片的链接
     * @return  返回一个 drawable 类型的图片
     */
    public static Drawable loadImageFromNetBendi(final Class clazz, final String netUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Drawable drawable =null;
                try {
                    drawable = Drawable.createFromStream(new URL(netUrl).openStream(), "netUrl.jpg");
                    String aa = "";
                } catch (IOException e) {
                    Log.e(clazz.getName(),e.getMessage());
                }

            }
        }).start();

        return drawable;
    }


}