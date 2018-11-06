package com.haier.jiuzhidao.biz_mz.bean;

import java.io.Serializable;

public class MyResult implements Serializable {
    private String msg;
    private String error;
    private String token;
    private boolean has_login;
    private String web_url;

    public boolean isHas_login() {
        return has_login;
    }

    public void setHas_login(boolean has_login) {
        this.has_login = has_login;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public boolean isHas_neworder() {
        return has_neworder;
    }

    public void setHas_neworder(boolean has_neworder) {
        this.has_neworder = has_neworder;
    }

    private boolean has_neworder;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
