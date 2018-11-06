package com.haier.jiuzhidao.jiuzhidaophone_address;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.AllocationAdapter;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.jiuzhidao.jiuzhidaophone_address.bean.AddressBean;
import com.haier.jiuzhidao.jiuzhidaophone_address.bean.AddressBean2;
import com.haier.jiuzhidao.jiuzhidaophone_address.daoutils.AddressDao;
import com.haier.jiuzhidao.jiuzhidaophone_address.utils.AlertUtils;

public class CreateAddressActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private AddressBean2 addressBean2 = new AddressBean2();
    private AddressBean addressBean = new AddressBean();
    private EditText etPhone;
    private Button mSave;
    private EditText etPerson;
    private EditText etDoorNum;
    private EditText etAddress;
    private String mAddStr;
    private String mDoorNumStr;
    private String mPersonStr;
    private String mPhoneStr;
    private int addressModel;
    private ImageView ivDelete;
    private RadioGroup sexTag;
    private RadioGroup locationTag;
    private TextView tvTitle;
    private ImageView ivBack;
    private TextView tvCreate;
    private TextView tvMail;
    private RadioButton childAt;
    private LinearLayout llLocation;
    private ImageView ivArrow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_createaddress;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        find();
        setJump();
    }

    private void setJump() {
        addressModel = getIntent().getIntExtra("addressModel", 0);
        if (addressModel == 2) {
            tvTitle.setText(getResources().getString(R.string.change));
            ivDelete.setVisibility(View.VISIBLE);
            addressBean2 = (AddressBean2) getIntent().getSerializableExtra("addressBean");
            initData();
        } else {
            tvTitle.setText(getResources().getString(R.string.create));
            ivDelete.setVisibility(View.GONE);
        }
    }

    private void initData() {
        etPhone.setText(addressBean2.getNum());
        etPerson.setText(addressBean2.getName());
        etDoorNum.setText(addressBean2.getDoornum());
        etAddress.setText(addressBean2.getAddress());
        addressBean.setId(addressBean2.getId());

        if (addressBean2.getSex() == 0) {
            childAt = (RadioButton) sexTag.getChildAt(1);
        } else {
            childAt = (RadioButton) sexTag.getChildAt(0);
        }
        childAt.setChecked(true);

        String flag = addressBean2.getFlag();
        for (int x = 0; x < locationTag.getChildCount(); x++) {
            RadioButton rb = (RadioButton) locationTag.getChildAt(x);
            if (rb.getText().toString().equals(flag)) {
                rb.setChecked(true);
            }
        }
    }

    private void find() {
        ivBack = findViewById(R.id.iv_back);
        llLocation = findViewById(R.id.ll_create_location);
        tvTitle = findViewById(R.id.tv_title);
        tvCreate = findViewById(R.id.tv_create_add);
        tvMail = findViewById(R.id.tv_create_mail);
        mSave = findViewById(R.id.save);
        etPhone = findViewById(R.id.et_phone);
        etPerson = findViewById(R.id.et_person);
        etDoorNum = findViewById(R.id.et_door_num);
        etAddress = findViewById(R.id.et_address);
        ivDelete = findViewById(R.id.iv_delete);
        ivArrow = findViewById(R.id.iv_arrow);


        ivArrow.setOnClickListener(this);
        mSave.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvCreate.setOnClickListener(this);
        tvMail.setOnClickListener(this);
        ivDelete.setOnClickListener(this);

        locationTag = findViewById(R.id.rg_location_tag);
        sexTag = findViewById(R.id.rg_sex_tag);
        llLocation.setOnClickListener(this);
        locationTag.setOnCheckedChangeListener(this);
        sexTag.setOnCheckedChangeListener(this);
    }

    public void btn_contacts() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        startActivityForResult(intent, 1);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.tv_create_mail) {
            btn_contacts();
        } else if (i == R.id.save) {
            if (Verification()) {
                addressBean.setNum(mPhoneStr);
                addressBean.setAddress(mAddStr);
                addressBean.setName(mPersonStr);
                addressBean.setDoornum(mDoorNumStr);
                if (addressModel == 1) {
                    AddressDao.saveData(this, addressBean);
                } else {
                    AddressDao.changeData(this, addressBean);
                }

                finish();
            }
        } else if (i == R.id.iv_delete) {
            AddressDao.deleteByKeyData(this, addressBean2.getId());
            AlertUtils.showAlert("确定删除改收货地址?", this);
        } else if (i == R.id.ll_create_location||i==R.id.iv_arrow) {
//            startActivityForResult(new Intent(this, LoacationActivity.class), 12306);
        }
    }

    @Override
    protected void onActResult(int requestCode, int resultCode, Intent data) {
        super.onActResult(requestCode, resultCode, data);
        if (requestCode == 12306) {
            if (null != data && !data.getStringExtra("address").equals("")) {
                etAddress.setText(data.getStringExtra("address"));
                etDoorNum.setText(data.getStringExtra("doorNum"));
//                latitude = data.getDoubleExtra("lat", 0.0);
//                longitude = data.getDoubleExtra("lng", 0.0);
            }
        } else {
            if (data != null) {
                //  String num = data.getStringExtra("num");
                Uri uri = data.getData();
                String num = null;
                // 创建内容解析者
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(uri,
                        null, null, null, null);
                while (cursor.moveToNext()) {
                    num = cursor.getString(cursor.getColumnIndex("data1"));
                }
                String str1 = num.replaceAll(" ", "");
                String str2 = str1.replaceAll("-", "");
                etPhone.setText(str2);
                cursor.close();
            }
        }
    }

    public boolean Verification() {
        mAddStr = etAddress.getEditableText().toString().trim();
        mDoorNumStr = etDoorNum.getEditableText().toString().trim();
        mPersonStr = etPerson.getEditableText().toString().trim();
        mPhoneStr = etPhone.getEditableText().toString().trim();
        if (TextUtils.isEmpty(mPersonStr)) {
            Toasty.info(this, "联系人不能为空。").show();
            return false;
        } else if (TextUtils.isEmpty(mPhoneStr)) {
            Toasty.info(this, "电话号码不能为空。").show();
            return false;
        } else if (TextUtils.isEmpty(mAddStr)) {
            Toasty.info(this, "地址不能为空。").show();
            return false;
        } else if (TextUtils.isEmpty(mDoorNumStr)) {
            Toasty.info(this, "门牌号不能为空。").show();
            return false;
        }

        return true;
    }

    int tag = 0;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int checkedRadioButtonId = group.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedRadioButtonId);
        String text = radioButton.getText().toString();
        if (text.equals("先生")) {
            tag = 1;
            addressBean.setSex(tag);
        } else if (text.equals("女士")) {
            tag = 0;
            addressBean.setSex(tag);
        } else {
            addressBean.setFlag(text);
        }
    }
}
