package com.haier.index.zxing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * Created by Administrator on 2016/9/26 0026.
 */
public class StoragePath {
    public static boolean createStoragePath() {
        // Root
        String rootPath = Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_ROOT;
        File root = new File(rootPath);
        if (!root.exists() && !root.mkdir()) {
            return false;
        }
        // Picture
        String pPath = rootPath + IdentificationConfig.STORAGEPATH_MENU_P;
        File p = new File(pPath);
        if (!p.exists() && !p.mkdir()) {
            return false;
        }
        // Update
        String uPath = rootPath + IdentificationConfig.STORAGEPATH_MENU_U;
        File u = new File(uPath);
        return u.exists() || u.mkdir();
    }

    public static File savePicture(Bitmap bitmap) {
        File f = new File(Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_PICTURE
                + System.currentTimeMillis() + IdentificationConfig.FILE_NAME_JPG);
        boolean b = true;
        if (!f.exists()) {
            try {
                b = f.createNewFile();
            } catch (IOException e) {
                b = false;
                e.printStackTrace();
            }
        }
        if (b) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return f;
    }

    /**
     * 按正方形裁切图片
     */
    public static Bitmap imageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = (int) (345.0 * h / 410.0);// 裁切后所取的正方形区域边长
//         int wh = (int) (1.0 * h);// 裁切后所取的正方形区域边长

        // int retX = w > h ? (w - h) / 2 : 0;// 基于原图，取正方形左上角x坐标
        // int retY = w > h ? 0 : (h - w) / 2;

        Matrix m = new Matrix();
        m.setRotate(90);
        // 下面这句是关键
        return Bitmap.createBitmap(bitmap, (w - wh) / 2, 0, wh, h, m, false);
    }


    public static File savePicture(byte[] picture) {
        Bitmap bm = BitmapFactory.decodeByteArray(picture, 0, picture.length);
        Bitmap bm2 = imageCrop(bm);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        try {
            bm.recycle();
            bm2.recycle();
            baos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        File f = new File(Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_PICTURE
                + System.currentTimeMillis() + IdentificationConfig.FILE_NAME_JPG);
        boolean b = true;
        if (!f.exists()) {
            try {
                b = f.createNewFile();
            } catch (IOException e) {
                b = false;
                e.printStackTrace();
            }
        }
        if (b) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(f);
                out.write(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return f;
    }
//    public static File savePicture(byte[] picture) {
//        Bitmap bm = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//        Bitmap bm2 = imageCrop(bm);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] data = baos.toByteArray();
//        try {
//            bm.recycle();
//            bm2.recycle();
//            baos.close();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        File f = new File(Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_PICTURE
//                + System.currentTimeMillis() + IdentificationConfig.FILE_NAME_JPG);
//        boolean b = true;
//        if (!f.exists()) {
//            try {
//                b = f.createNewFile();
//            } catch (IOException e) {
//                b = false;
//                e.printStackTrace();
//            }
//        }
//        if (b) {
//            FileOutputStream out = null;
//            try {
//                out = new FileOutputStream(f);
//                out.write(data);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (out != null) {
//                    try {
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return f;
//    }

    public static File saveApp() {
        File f = new File(Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_UPDATE
                + System.currentTimeMillis() + IdentificationConfig.FILE_NAME_APP);
        boolean b = true;
        if (!f.exists()) {
            try {
                b = f.createNewFile();
            } catch (IOException e) {
                b = false;
                e.printStackTrace();
            }
        }
        if (b) {
            return f;
        }
        return null;
    }

    public static File saveRom() {
        File f = new File(Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_UPDATE
                + System.currentTimeMillis() + IdentificationConfig.FILE_NAME_ROM);
        boolean b = true;
        if (!f.exists()) {
            try {
                b = f.createNewFile();
            } catch (IOException e) {
                b = false;
                e.printStackTrace();
            }
        }
        if (b) {
            return f;
        }
        return null;
    }

    public static void deleteFiles() {
        File f = new File(
                Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_ROOT + IdentificationConfig.STORAGEPATH_MENU_U);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File temp : files) {
                temp.delete();
            }
        }
    }

    /**
     * 删除照片
     */
    public static void deletePictures() {
        File f = new File(
                Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_ROOT + IdentificationConfig.STORAGEPATH_MENU_P);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File temp : files) {
                temp.delete();
            }
        }
    }

    public static void deleteFile(String url) {
        if (url == null || url.length() == 0) {
            return;
        }
        String[] s = url.split("/");
        File f = new File(Environment.getExternalStorageDirectory() + IdentificationConfig.STORAGEPATH_PICTURE + s[s.length - 1]);
        if (f.exists()) {
            f.delete();
        }
    }

    /**
     * toast提示
     * @param context
     * @param tvString
     */
    public static void toastShow(Context context, String tvString) {
        Toast toast = null;
        toast = Toast.makeText(context,tvString,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
