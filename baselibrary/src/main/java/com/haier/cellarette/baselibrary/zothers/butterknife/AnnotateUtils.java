package com.haier.cellarette.baselibrary.zothers.butterknife;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class AnnotateUtils {

    public static void injectViews(Activity activity) {
        Class clz = activity.getClass();
        Field[] fls = clz.getFields();
        if (fls != null && fls.length > 0) {
            for (Field fl : fls) {
                ViewInjects in = fl.getAnnotation(ViewInjects.class);
                if (in != null) {
                    View view = activity.findViewById(in.value());
                    if (view != null) {
                        try {
                            fl.setAccessible(true);
                            fl.set(activity, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

//        // 获取activity的Class
//        Class<? extends Activity> object = activity.getClass();
//        // 通过Class获取activity的所有属性
//        Field[] fields = object.getDeclaredFields();
//        for (Field field : fields) {
//            // 获取字段的注解，如果没有ViewInject注解，则返回null
//            ViewInjects viewInject = field.getAnnotation(ViewInjects.class);
//            if (viewInject != null) {
//                // 获取属性的注解的参数，这就是控件的id
//                int viewId = viewInject.value();
//                try {
//                    // 获取类中的findViewById方法，参数为int
//                    Method method = object.getMethod("findViewById", int.class);
//                    // 执行该方法，返回一个Object类型的View实例
//                    Object resView = method.invoke(activity, viewId);
//                    field.setAccessible(true);
//                    // 把属性的值设置为该View的实例
//                    field.set(activity, resView);
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}