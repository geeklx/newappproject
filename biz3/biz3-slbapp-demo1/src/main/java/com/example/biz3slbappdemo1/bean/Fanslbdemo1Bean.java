package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class Fanslbdemo1Bean implements Serializable {
    private int id;
    private String jsonrpc;
    private String method;
    private String result;

    public Fanslbdemo1Bean() {
    }

    public Fanslbdemo1Bean(int id, String jsonrpc, String method, String result) {
        this.id = id;
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
