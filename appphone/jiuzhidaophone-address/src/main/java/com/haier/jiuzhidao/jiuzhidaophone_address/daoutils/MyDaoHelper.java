package com.haier.jiuzhidao.jiuzhidaophone_address.daoutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.haier.jiuzhidao.jiuzhidaophone_address.dao.AddressBeanDao;
import com.haier.jiuzhidao.jiuzhidaophone_address.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class MyDaoHelper extends DaoMaster.OpenHelper {
    public MyDaoHelper(Context context) {
        super(context, DbManager.DB_NAME);
    }

    public MyDaoHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DbManager.DB_NAME, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.i("version", oldVersion + "---先前和更新之后的版本---" + newVersion);
        if (oldVersion < newVersion) {
            Log.i("version", oldVersion + "---先前和更新之后的版本---" + newVersion);
            //操作数据库的更新 有几个表升级都可以传入到下面
            MigrationHelper.getInstance().migrate(db,AddressBeanDao.class);
            //更改过的实体类(新增的不用加)   更新UserDao文件 可以添加多个  XXDao.class 文件
//             MigrationHelper.getInstance().migrate(db, UserDao.class,XXDao.class);
        }
    }


}
