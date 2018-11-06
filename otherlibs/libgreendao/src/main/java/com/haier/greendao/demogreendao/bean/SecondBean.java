package com.haier.greendao.demogreendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

@Entity
public class SecondBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String name;
    private int sex;
    private String num;
    private String address;
    private String flag;
    private String doornum;
    @Keep
    public SecondBean(Long id, String name, int sex, String num, String address,
            String flag, String doornum) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.num = num;
        this.address = address;
        this.flag = flag;
        this.doornum = doornum;
    }
    @Keep
    public SecondBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFlag() {
        return this.flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getDoornum() {
        return this.doornum;
    }
    public void setDoornum(String doornum) {
        this.doornum = doornum;
    }


}