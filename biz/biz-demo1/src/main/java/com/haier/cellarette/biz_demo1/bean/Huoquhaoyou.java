package com.haier.cellarette.biz_demo1.bean;

import java.io.Serializable;

/**
 * Created by pc on 2017/11/21.
 */

public class Huoquhaoyou implements Serializable {
    private String user_id;
    private String honor;
    private String color_gradients;
    private String avatar;
    private String user_nickname;
    private String honor_pic;

    private boolean choosed;

    public Huoquhaoyou() {
    }

    public Huoquhaoyou(String user_id, String honor, String color_gradients, String avatar, String user_nickname, String honor_pic, boolean choosed) {
        this.user_id = user_id;
        this.honor = honor;
        this.color_gradients = color_gradients;
        this.avatar = avatar;
        this.user_nickname = user_nickname;
        this.honor_pic = honor_pic;
        this.choosed = choosed;
    }

    public Huoquhaoyou(String user_id, String honor, String color_gradients, String avatar, String user_nickname, String honor_pic) {
        this.user_id = user_id;
        this.honor = honor;
        this.color_gradients = color_gradients;
        this.avatar = avatar;
        this.user_nickname = user_nickname;
        this.honor_pic = honor_pic;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getColor_gradients() {
        return color_gradients;
    }

    public void setColor_gradients(String color_gradients) {
        this.color_gradients = color_gradients;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getHonor_pic() {
        return honor_pic;
    }

    public void setHonor_pic(String honor_pic) {
        this.honor_pic = honor_pic;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }
}
