package com.example.slbappreadbook.domain;

import java.io.Serializable;


public class HuibenFileCacheBean implements Serializable {

    private String id;
    private String url;

    public HuibenFileCacheBean() {
    }

    public HuibenFileCacheBean(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
