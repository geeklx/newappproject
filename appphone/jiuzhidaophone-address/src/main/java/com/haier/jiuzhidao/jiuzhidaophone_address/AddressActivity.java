package com.haier.jiuzhidao.jiuzhidaophone_address;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.baseactivitys.CheckPermissionsActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.jiuzhidao.jiuzhidaophone_address.adapter.MyAdapter;
import com.haier.jiuzhidao.jiuzhidaophone_address.bean.AddressBean;
import com.haier.jiuzhidao.jiuzhidaophone_address.bean.AddressBean2;
import com.haier.jiuzhidao.jiuzhidaophone_address.daoutils.AddressDao;

import java.util.List;

/**
 * DbManager.getInstance(this);
 * //增
 * AddressDao.insertData(this,student);
 * //删
 * //        AddressDao.deleteData();
 * //改
 * //          AddressDao.updateData();
 * //查
 * List<AddressBean> address = AddressDao.queryAll(this);
 */
public class AddressActivity extends CheckPermissionsActivity implements View.OnClickListener {

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
    };
    private RecyclerView mRecycleView;
    private List<AddressBean> addressBeans;
    private MyAdapter myAdapter;
    private ImageView ivBack;
    private TextView tvCreate;
    private TextView tvTitle;
    private AddressBean2 addressBean2 = new AddressBean2();

    @Override
    protected String[] YouNeedPermissions() {
        return needPermissions;
    }


    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        find();
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        addressBeans = AddressDao.queryAll(this);
        if (addressBeans != null ) {
            myAdapter.setNewData(addressBeans);
            myAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(R.layout.address_item);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AddressBean addressBean = addressBeans.get(position);
                Intent intent = new Intent(AddressActivity.this, CreateAddressActivity.class);
                intent.putExtra("addressModel", 2);
                addressBean2.setAddress(addressBean.getAddress());
                addressBean2.setDoornum(addressBean.getDoornum());
                addressBean2.setFlag(addressBean.getFlag());
                addressBean2.setId(addressBean.getId());
                addressBean2.setName(addressBean.getName());
                addressBean2.setNum(addressBean.getNum());
                addressBean2.setSex(addressBean.getSex());

                intent.putExtra("addressBean", addressBean2);
                startActivity(intent);
            }
        });
        myAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AddressBean addressBean = addressBeans.get(position);
                int i = view.getId();
                if (i == R.id.tv_address2) {
                    Toasty.normal(AddressActivity.this, addressBean.getAddress()).show();
                } else if (i == R.id.tv_person) {
                    Toasty.normal(AddressActivity.this, addressBean.getName()).show();
                } else {
                }
            }
        });
        mRecycleView.setAdapter(myAdapter);
    }

    private void find() {
        mRecycleView = findViewById(R.id.rl_address);
        ivBack = findViewById(R.id.iv_back);
        tvCreate = findViewById(R.id.tv_create_add);
        tvCreate.setVisibility(View.VISIBLE);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(getResources().getString(R.string.title));
        ivBack.setOnClickListener(this);
        tvCreate.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.tv_create_add) {
            Intent intent = new Intent(this, CreateAddressActivity.class);
            intent.putExtra("addressModel", 1);
            startActivity(intent);
        }
    }
}
