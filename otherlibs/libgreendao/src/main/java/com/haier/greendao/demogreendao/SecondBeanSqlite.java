package com.haier.greendao.demogreendao;

import android.content.Context;
import android.widget.Toast;

import com.haier.greendao.autoputgreendao.SecondBeanDao;
import com.haier.greendao.demogreendao.bean.SecondBean;
import com.haier.greendao.greendaoutil.DbManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class SecondBeanSqlite {

    /**
     * 判断是否存在这条记录
     *
     * @param context
     * @param address
     * @return
     */
    public static boolean is_cunzai(Context context, SecondBean address) {
        if (address == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
            return false;
        }
        SecondBean user = DbManager.getDaoSession(context).getSecondBeanDao().queryBuilder().where(SecondBeanDao.Properties.Id.eq(address.getId() + "")).build().unique();
        if (user == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    /**
     * 判断是否存在这条记录
     *
     * @param context
     * @param id
     * @return
     */
    public static boolean is_cunzai2(Context context, long id) {
        SecondBean user = DbManager.getDaoSession(context).getSecondBeanDao().queryBuilder().where(SecondBeanDao.Properties.Id.eq(id + "")).build().unique();
        if (user == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<SecondBean> queryAll(Context context) {
        QueryBuilder<SecondBean> builder = DbManager.getDaoSession(context).getSecondBeanDao().queryBuilder();
        return builder.list();
    }

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param stu
     */
    public static void insertData(Context context, SecondBean stu) {
        DbManager.getDaoSession(context).getSecondBeanDao().insert(stu);
        DbManager.getDaoSession(context).clear();
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<SecondBean> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getSecondBeanDao().insertInTx(list);
    }


    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param address
     */
    public static void saveData(Context context, SecondBean address) {
        DbManager.getDaoSession(context).getSecondBeanDao().save(address);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     */
    public static void saveData(Context context, List<SecondBean> list) {
        DbManager.getDaoSession(context).getSecondBeanDao().saveInTx(list);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param address 删除具体内容
     */
    public static void deleteData(Context context, SecondBean address) {
        if (!is_cunzai(context, address)) {
            return;
        }
        DbManager.getDaoSession(context).getSecondBeanDao().delete(address);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        if (!is_cunzai2(context, id)) {
            return;
        }
        DbManager.getDaoSession(context).getSecondBeanDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getSecondBeanDao().deleteAll();
    }

    /**
     * 修改后增加单个数据
     *
     * @param context
     */
    public static void changeData(Context context, SecondBean address) {
        if (!is_cunzai(context, address)) {
            return;
        }
        DbManager.getDaoSession(context).getSecondBeanDao().insertOrReplace(address);
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param address
     */
    public static void updateData(Context context, SecondBean address) {
        if (!is_cunzai(context, address)) {
            return;
        }
        DbManager.getDaoSession(context).getSecondBeanDao().update(address);
    }

    /**
     * 根据id，其他的字段类似 查询
     *
     * @param context
     * @param id
     * @return
     */
    public static List<SecondBean> queryListForId(Context context, long id) {
        QueryBuilder<SecondBean> builder = DbManager.getDaoSession(context).getSecondBeanDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(addressDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         *
         */
        // Query<address> build = builder.where(addressDao.Properties.Id.eq(id)).build();
        // List<address> list = build.list();
        return builder.where(SecondBeanDao.Properties.Id.eq(id)).list();
    }

    /**
     * 判断是否存在这条记录
     *
     * @param context
     * @param id
     * @return
     */
    public static SecondBean queryBeanForId(Context context, long id) {
        SecondBean user = DbManager.getDaoSession(context).getSecondBeanDao().queryBuilder().
                where(SecondBeanDao.Properties.Id.eq(id + "")).build().unique();
        if (user == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
            return null;
        }
        return user;
    }

}
