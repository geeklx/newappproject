package com.haier.biz.pollorder.bean;

import java.io.Serializable;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/8.
 * Description:
 */
public class PollOrderBean implements Serializable {


    /**
     * code : 200
     * datas : {"has_login":false,"web_url":"http://localhost/wap/tmpl/member/marki_list.html?key=123","has_neworder":false}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable{
        /**
         * has_login : false
         * web_url : http://localhost/wap/tmpl/member/marki_list.html?key=123
         * has_neworder : false
         */

        private boolean has_login;
        private String web_url;
        private boolean has_neworder;

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
    }
}
