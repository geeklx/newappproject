package com.haier.cellarette.baselibrary.expandableview.extextview.bean;


import java.io.Serializable;

public class TextViewGroupBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;

    public TextViewGroupBean() {
    }

    public TextViewGroupBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}