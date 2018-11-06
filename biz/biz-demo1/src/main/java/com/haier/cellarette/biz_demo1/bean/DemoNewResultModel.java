package com.haier.cellarette.biz_demo1.bean;

import java.io.Serializable;
import java.util.List;

public class DemoNewResultModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stat;
    private List<DemoNewDataModel> data;

    public DemoNewResultModel() {
    }

    public DemoNewResultModel(String stat, List<DemoNewDataModel> data) {
        this.stat = stat;
        this.data = data;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DemoNewDataModel> getData() {
        return data;
    }

    public void setData(List<DemoNewDataModel> data) {
        this.data = data;
    }
}
