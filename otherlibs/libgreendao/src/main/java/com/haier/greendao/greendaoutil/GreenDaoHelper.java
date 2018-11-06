package com.haier.greendao.greendaoutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.haier.greendao.autoputgreendao.DaoMaster;
import com.haier.greendao.autoputgreendao.SecondBeanDao;

import org.greenrobot.greendao.database.Database;

public class GreenDaoHelper extends DaoMaster.OpenHelper {
    public GreenDaoHelper(Context context) {
        super(context, DbManager.DB_NAME);
    }

    public GreenDaoHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DbManager.DB_NAME, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.i("version", oldVersion + "---先前和更新之后的版本---" + newVersion);
        if (oldVersion < newVersion) {
            Log.i("version", oldVersion + "---先前和更新之后的版本---" + newVersion);
            //操作数据库的更新 有几个表升级都可以传入到下面
            MigrationHelper.getInstance().migrate(db, SecondBeanDao.class);
            //更改过的实体类(新增的不用加)   更新UserDao文件 可以添加多个  XXDao.class 文件
//             MigrationHelper.getInstance().migrate(db, UserDao.class,XXDao.class);
        }
    }


}
