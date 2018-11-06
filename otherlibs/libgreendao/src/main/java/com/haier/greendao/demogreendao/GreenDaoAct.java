package com.haier.greendao.demogreendao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haier.greendao.R;
import com.haier.greendao.autoputgreendao.SecondBeanDao;
import com.haier.greendao.demogreendao.bean.SecondBean;
import com.haier.greendao.greendaoutil.DbManager;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoAct extends AppCompatActivity implements View.OnClickListener {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private TextView tv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        tv1 = findViewById(R.id.tv1);

    }

    private void queryData() {
        List<SecondBean> users = SecondBeanSqlite.queryAll(this);
        String aaaaa = "";
        for (int i = 0; i < users.size(); i++) {
            String a = users.get(i).getId() + "Id " + users.get(i).getSex() + " " + users.get(i).getAddress() + " " + users.get(i).getDoornum()
                    + " " + users.get(i).getFlag() + " " + users.get(i).getName() + " " + users.get(i).getNum() + "\n\n";
            aaaaa += a;
        }
        tv1.setText(aaaaa);
    }

    @Override
    public void onClick(View v) {
        int a = v.getId();
        if (a == R.id.btn0) {
            //重置
            SecondBeanSqlite.deleteAllData(this);
            List<SecondBean> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                SecondBean bean = new SecondBean(null, "geek" + i, i, "666-" + i, "北京1-" + i, "北京2-" + i, "北京3-" + i);
                list.add(bean);
//                SecondBeanSqlite.saveData(GreenDaoAct.this, bean);
//                SecondBeanSqlite.insertData(GreenDaoAct.this, bean);
            }
            SecondBeanSqlite.saveData(GreenDaoAct.this, list);

            queryData();
        } else if (a == R.id.btn1) {
            //搜索
            queryData();
        } else if (a == R.id.btn2) {
            //添加
            int i = 11;
            SecondBean bean = new SecondBean(null, "geek" + i, i, "666-" + i, "郑州1-" + i, "郑州2-" + i, "郑州3-" + i);
            SecondBeanSqlite.saveData(GreenDaoAct.this, bean);
            queryData();
        } else if (a == R.id.btn3) {
            //更新
            int i = 11;
            SecondBean user = DbManager.getDaoSession(GreenDaoAct.this).getSecondBeanDao().queryBuilder().
                    where(SecondBeanDao.Properties.Name.eq("geek" + i)).build().unique();
            if (user == null) {
                return;
            }
            user.setName("geek" + i);
            user.setSex(i);
            user.setNum("666-" + i);
            user.setAddress("上海1-" + i);
            user.setFlag("上海2-" + i);
            user.setDoornum("上海3-" + i);
//            SecondBeanSqlite.updateData(GreenDaoAct.this, user);//
            SecondBeanSqlite.changeData(GreenDaoAct.this, user);//
            queryData();
        } else if (a == R.id.btn4) {
            //删除
            int i = 11;
            SecondBean user = DbManager.getDaoSession(GreenDaoAct.this).getSecondBeanDao().queryBuilder().
                    where(SecondBeanDao.Properties.Name.eq("geek" + i)).build().unique();
            SecondBeanSqlite.deleteData(GreenDaoAct.this, user);
//            SecondBeanSqlite.deleteByKeyData(GreenDaoAct.this, user.getId());
            queryData();
        } else if (a == R.id.btn5) {
            //all删除
            SecondBeanSqlite.deleteAllData(this);
            queryData();
        } else {

        }
    }

}
