package com.haier.jiuzhidao.jiuzhidaophone_address.bean;

import java.io.Serializable;

public class AddressBean2 implements Serializable {
    private Long id;
    private String name;
    private int sex;
    private String num;
    private String address;
    private String flag;
    private String doornum;

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