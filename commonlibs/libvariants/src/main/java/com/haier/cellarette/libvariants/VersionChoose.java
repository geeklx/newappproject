package com.haier.cellarette.libvariants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 弃用 因为aar嵌套只支持远程库
 */
public class VersionChoose {
    public static final int CCC = 0;
    public static final int YYY = 1;
    public static final int OOO = 2;

    @IntDef({CCC,YYY,OOO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VersionStatus{}

    public static String getVersionNameString(@VersionStatus int type) {
        if (type == CCC) {
            return "ceshi";
        } else if (type == YYY) {
            return "yushengchan";
        } else if (type == OOO) {
            return "xianshang";
        } else {
            return "ceshi";
        }
    }

}
