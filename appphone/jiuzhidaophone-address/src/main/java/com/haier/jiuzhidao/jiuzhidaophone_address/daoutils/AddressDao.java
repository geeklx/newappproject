package com.haier.jiuzhidao.jiuzhidaophone_address.daoutils;

import android.content.Context;

import com.haier.jiuzhidao.jiuzhidaophone_address.bean.AddressBean;
import com.haier.jiuzhidao.jiuzhidaophone_address.dao.AddressBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class AddressDao {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param stu
     */
    public static void insertData(Context context, AddressBean stu) {
        DbManager.getDaoSession(context).getAddressBeanDao().insert(stu);
        DbManager.getDaoSession(context).clear();
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<AddressBean> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getAddressBeanDao().insertInTx(list);
    }


    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param address
     */
    public static void saveData(Context context, AddressBean address) {
        DbManager.getDaoSession(context).getAddressBeanDao().save(address);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     */
    public static void saveData(Context context, List<AddressBean> list) {
        DbManager.getDaoSession(context).getAddressBeanDao().saveInTx(list);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param address 删除具体内容
     */
    public static void deleteData(Context context, AddressBean address) {
        DbManager.getDaoSession(context).getAddressBeanDao().delete(address);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getAddressBeanDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getAddressBeanDao().deleteAll();
    }


    /**
     * 修改数据
     *
     * @param context
     */
    public static void changeData(Context context,AddressBean addressBean) {
        DbManager.getDaoSession(context).getAddressBeanDao().insertOrReplace(addressBean);
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param address
     */
    public static void updateData(Context context, AddressBean address) {
        DbManager.getDaoSession(context).getAddressBeanDao().update(address);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<AddressBean> queryAll(Context context) {
        QueryBuilder<AddressBean> builder = DbManager.getDaoSession(context).getAddressBeanDao().queryBuilder();
        return builder.list();
    }

    /**
     * 根据id，其他的字段类似
     *
     * @param context
     * @param id
     * @return
     */
    public static List<AddressBean> queryForId(Context context, long id) {
        QueryBuilder<AddressBean> builder = DbManager.getDaoSession(context).getAddressBeanDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(addressDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         *
         */
        // Query<address> build = builder.where(addressDao.Properties.Id.eq(id)).build();
        // List<address> list = build.list();
        return builder.where(AddressBeanDao.Properties.Id.eq(id)).list();
    }


}
