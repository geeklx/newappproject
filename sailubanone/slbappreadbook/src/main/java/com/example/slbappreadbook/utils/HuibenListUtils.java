package com.example.slbappreadbook.utils;

import android.content.Context;

import com.example.slbappreadbook.domain.BaseBean2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuibenListUtils {

    private static volatile HuibenListUtils instance;
    private static Context mContext;

    private HuibenListUtils(Context context) {
        mContext = context;
    }

    public static HuibenListUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (HuibenListUtils.class) {
                instance = new HuibenListUtils(context);
            }
        }
        return instance;
    }


    public boolean IsEqual(List<BaseBean2> defaultList1, List<BaseBean2> bannerBeanList1) {
        if (defaultList1.size() != bannerBeanList1.size()) {
            return false;
        }
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        for (int i = 0; i <= defaultList1.size() - 1; i++) {
            a.add(defaultList1.get(i).getId() + "");
        }
        for (int j = 0; j <= bannerBeanList1.size() - 1; j++) {
            b.add(bannerBeanList1.get(j).getId() + "");
        }
        return compare(a, b);
    }

    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if (a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }


}
