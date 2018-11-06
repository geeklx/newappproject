package com.haier.index.zxing;

/**
 *
 * Created by Administrator on 2016/9/26 0026.
 */
public class IdentificationConfig {

    public static final String STORAGEPATH_ROOT = "/haier";
    public static final String STORAGEPATH_MENU_P = "/picture";
    public static final String STORAGEPATH_MENU_U = "/update";
    public static final String STORAGEPATH_PICTURE = STORAGEPATH_ROOT + STORAGEPATH_MENU_P + "/";
    public static final String STORAGEPATH_UPDATE = STORAGEPATH_ROOT + STORAGEPATH_MENU_U + "/";

    public static final String FILE_NAME_JPG = ".jpg";
    public static final String FILE_NAME_APP = ".apk";
    public static final String FILE_NAME_ROM = ".zip";
    public static final String FILE_NAME_SYS = "/sys/bus/platform/drivers/mtk-kpd/motion_sensor_enable";
}
