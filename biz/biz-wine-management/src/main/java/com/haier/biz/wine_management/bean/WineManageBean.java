package com.haier.biz.wine_management.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:
 */
public class WineManageBean implements Serializable{


    /**
     * code : 00000
     * data : [{"joinDate":"2018-05-05","goodsId":100618,"rfid":"00271f000900010000000087","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544901422417210_360.jpg","producingArea":"智利","goodsName":"智利安第诺干红"},{"joinDate":"2018-05-05","goodsId":100619,"rfid":"0027200009000100000000b9","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544901087359377_360.jpg","producingArea":"法国","goodsName":"法国梨涡干红"},{"joinDate":"2018-05-05","goodsId":100620,"rfid":"002721000900010000000083","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544897405115200_360.jpg","producingArea":"智利","goodsName":"智利Lady Penguin干红"},{"joinDate":"2018-05-05","goodsId":100622,"rfid":"00272300090001000000003d","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544900433211162_360.jpg","producingArea":"澳大利亚","goodsName":"纷赋卡本妮梅洛红"},{"joinDate":"2018-05-05","goodsId":100624,"rfid":"002725000900010000000057","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544899985747069_360.jpg","producingArea":"奥地利","goodsName":"奥地利威雷德干白"},{"joinDate":"2018-05-05","goodsId":100626,"rfid":"0027270009000100000000a3","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544898910632980_360.jpg","producingArea":"法国","goodsName":"法国乐桦黑皮诺干红"},{"joinDate":"2018-05-05","goodsId":100627,"rfid":"002728000900010000000059","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544898541613289_360.jpg","producingArea":"南非","goodsName":"苍鹭之巢窖藏精选干红"},{"joinDate":"2018-05-05","goodsId":100629,"rfid":"00272a00090001000000004a","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/12/9_05531900978740151_360.jpg","producingArea":"澳大利亚","goodsName":"泉灯霞多丽白葡萄酒"},{"joinDate":"2018-05-05","goodsId":100630,"rfid":"00272b000900010000000068","goodsImage":"http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544899602150387_360.jpg","producingArea":"新西兰","goodsName":"新西兰灰石干红"}]
     * msg : 操作成功
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * joinDate : 2018-05-05
         * goodsId : 100618
         * rfid : 00271f000900010000000087
         * goodsImage : http://jiuzhidao.com:8888/data/upload/shop/store/goods/9/2017/07/27/9_05544901422417210_360.jpg
         * producingArea : 智利
         * goodsName : 智利安第诺干红
         */

        private String joinDate;
        private int goodsId;
        private String rfid;
        private String goodsImage;
        private String producingArea;
        private String goodsName;

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getRfid() {
            return rfid;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getProducingArea() {
            return producingArea;
        }

        public void setProducingArea(String producingArea) {
            this.producingArea = producingArea;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }
}
