package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class Slbdemo1Bean implements Serializable {

    private String sid;
    private String username;
    private String password;
    private String method;
    private String action;
    private String cmd;
    private String mail;
    private String tel;
    private String weixin;
    private String role;

    public Slbdemo1Bean() {
    }

    public Slbdemo1Bean(String sid, String username, String password, String method, String action, String cmd, String mail, String tel, String weixin, String role) {
        this.sid = sid;
        this.username = username;
        this.password = password;
        this.method = method;
        this.action = action;
        this.cmd = cmd;
        this.mail = mail;
        this.tel = tel;
        this.weixin = weixin;
        this.role = role;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
