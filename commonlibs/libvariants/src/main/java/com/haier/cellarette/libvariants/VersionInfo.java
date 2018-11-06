package com.haier.cellarette.libvariants;

/**
 * <p>function: 版本管理</p>
 * <p>function: 弃用 因为aar嵌套只支持远程库</p>
 * <p>description:  </p>
 * <p>history:  1. 2016/11/22</p>
 * <p>Author: geek</p>
 * <p>modification:</p>
 */
public enum VersionInfo {

    /** 测试*/
    CCC(0),

    /** 预生产*/
    YYY(1),

    /** 线上*/
    OOO(2);

    private int version;

    VersionInfo(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
