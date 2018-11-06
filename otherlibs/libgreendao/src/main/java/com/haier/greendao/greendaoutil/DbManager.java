package com.haier.greendao.greendaoutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.haier.greendao.autoputgreendao.DaoMaster;
import com.haier.greendao.autoputgreendao.DaoSession;


public class DbManager {
    public static String DB_NAME = "greendaogeek.db";
    private static DaoMaster.DevOpenHelper mDevOpenHelper = null;
    private static DaoSession mDaoSession;
    private static DaoMaster mDaoMaster;
    private static DbManager mDbManager;

    public DbManager(Context context) {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);
    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }

        return mDaoSession;
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    GreenDaoHelper myDaoHelper = new GreenDaoHelper(context);
                    mDaoMaster = new DaoMaster(myDaoHelper.getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }


    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }


    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }

        return mDevOpenHelper.getWritableDatabase();
    }

    public static DbManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }
}
