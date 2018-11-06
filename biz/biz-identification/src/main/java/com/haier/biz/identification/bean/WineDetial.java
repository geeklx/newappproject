package com.haier.biz.identification.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/14.
 * Description:
 */
public class WineDetial implements Serializable {

    /**
     * command : 10000
     * status : 0
     * description : 搜索成功
     * search_id : ru5csb87-26827-71267700-1474951715
     * image_url : http://staticfs1.9kacha.com/image/5a72fe48aac971b6e22b3b2130f1e1e6/c43175527e6
     * wine_list : [{"wine_id":"56d517e5c","sign":"e1bb0bf78d3224051271f229e42ab480A07CBBE99B4B05E305FEE7F655948D64","wyear":"NV","name_ch":"爱柏朗德巴吉酒庄长笛里斯园白葡萄酒","name_en":"Albert Bichot Domaine Long-Depaquit Les Lys","pic_url":"http://staticfs2.9kacha.com/image/e0f385cf6979fe80972dda811bb4ec12/thumb","year":"NV","country":"法国","region":"勃艮第丘（勃艮第）","taste_temp":"7-10℃"},{"wine_id":"384468682","sign":"63984d006a544ca6074da4bbe378b6cf92B030178034A9B2622D19366CEAA7B4","wyear":"NV","name_ch":"爱柏酒庄科通特级园红葡萄酒","name_en":"Albert Bichot Corton Grand Cru","pic_url":"http://staticfs2.9kacha.com/image/1030cb986ba712509e262846ec35e061/thumb","year":"NV","country":"法国","region":"勃艮第丘（勃艮第）","taste_temp":"14-18℃"},{"wine_id":"56921a55a","sign":"d9074494d80cedce1ce83a2e5829b8a76A12CC2EFF604538F387747C888B2E27","wyear":"2013","name_ch":"爱柏酒庄绍黑红葡萄酒","name_en":"Albert Bichot Chorey-Les-Beaune","pic_url":"http://staticfs2.9kacha.com/image/2dfa6da6d8243ce68fe2369400892d45/thumb","year":"2013","country":"法国","region":"勃艮第丘（勃艮第）","taste_temp":"14-18℃"},{"wine_id":"57c9522fr","sign":"96e4cf73892368a76c54008dbb898f94BB715F3625EE64D4A2322B694A64CAE0","wyear":"NV","name_ch":"朗德巴吉酒庄望民一级园红葡萄酒","name_en":"Domaine Long-Depaquit Montmains","pic_url":"http://staticfs1.9kacha.com/image/cfdc995b8faca567d2ee81a24fc130f5/thumb","year":"NV","country":"法国","region":"勃艮第丘（勃艮第）","taste_temp":"14-18℃"},{"wine_id":"228091918","sign":"3158d1ead08f0aa128c888128c7cedd94CBA77B8756868E7CBC6DB0C5327D212","wyear":"NV","name_ch":"爱柏酒庄悠盖特红葡萄酒","name_en":"Albert Bichot Les Dames Huguettes","pic_url":"http://staticfs2.9kacha.com/image/7cb526a06ba4166ed2188ed62ae0f63f/thumb","year":"NV","country":"法国","region":"勃艮第丘（勃艮第）","taste_temp":"14-18℃"}]
     */

    private String command;//命令号
    private String status;//返回登录状态(0表示成功)
    private String description;//返回后的描述信息
    private String search_id;
    private String image_url;
    /**
     * wine_id : 56d517e5c
     * sign : e1bb0bf78d3224051271f229e42ab480A07CBBE99B4B05E305FEE7F655948D64
     * wyear : NV
     * name_ch : 爱柏朗德巴吉酒庄长笛里斯园白葡萄酒
     * name_en : Albert Bichot Domaine Long-Depaquit Les Lys
     * pic_url : http://staticfs2.9kacha.com/image/e0f385cf6979fe80972dda811bb4ec12/thumb
     * year : NV
     * country : 法国
     * region : 勃艮第丘（勃艮第）
     * taste_temp : 7-10℃
     */

    private List<WineListBean> wine_list;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSearch_id() {
        return search_id;
    }

    public void setSearch_id(String search_id) {
        this.search_id = search_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<WineListBean> getWine_list() {
        return wine_list;
    }

    public void setWine_list(List<WineListBean> wine_list) {
        this.wine_list = wine_list;
    }

    public static class WineListBean implements Serializable {
        private String wine_id;//酒窖商品ID
        private String sign;//商品对应随机码
        private String wyear;
        private String name_ch;//商品的中文名称
        private String name_en;//商品的英文名称
        private String pic_url;//商品的图片
        private String year;
        private String country;//国家
        private String region;//产区
        private String taste_temp;//最佳试饮温度

        public String getWine_id() {
            return wine_id;
        }

        public void setWine_id(String wine_id) {
            this.wine_id = wine_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getWyear() {
            return wyear;
        }

        public void setWyear(String wyear) {
            this.wyear = wyear;
        }

        public String getName_ch() {
            return name_ch;
        }

        public void setName_ch(String name_ch) {
            this.name_ch = name_ch;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getTaste_temp() {
            return taste_temp;
        }

        public void setTaste_temp(String taste_temp) {
            this.taste_temp = taste_temp;
        }
    }

    @Override
    public String toString() {
        return "WineDetial{" +
                "command='" + command + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", search_id='" + search_id + '\'' +
                ", image_url='" + image_url + '\'' +
                ", wine_list=" + wine_list +
                '}';
    }
}
