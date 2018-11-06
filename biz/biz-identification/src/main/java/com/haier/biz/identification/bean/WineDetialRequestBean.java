package com.haier.biz.identification.bean;

import java.io.Serializable;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/4/17.
 * Description:
 */

public class WineDetialRequestBean implements Serializable {

    private String img_binary;

    private RequestInfoBean jparams;

    public String getImg_binary() {
        return img_binary;
    }

    public void setImg_binary(String img_binary) {
        this.img_binary = img_binary;
    }

    public RequestInfoBean getJparams() {
        return jparams;
    }

    public void setJparams(RequestInfoBean jparams) {
        this.jparams = jparams;
    }

    public static class RequestInfoBean implements Serializable {
        private String command;
        private String uid;
        private String openid;
        private String rtoken;
        private String time;
        private String stype;
        private String img_url;
        private String response_type;
//        private String client;
//        private String mobile_type;

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = "10000";
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getRtoken() {
            return rtoken;
        }

        public void setRtoken(String rtoken) {
            this.rtoken = rtoken;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getResponse_type() {
            return response_type;
        }

        public void setResponse_type(String response_type) {
            this.response_type = response_type;
        }
    }
}
