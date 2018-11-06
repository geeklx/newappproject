package com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinnet;

import java.io.Serializable;

public class Demo1Bean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String card_id;
    private String card_name;
    private String card_desc;
    private String card_balance;

    public Demo1Bean() {
    }

    public Demo1Bean(String card_id, String card_name, String card_desc, String card_balance) {
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_desc = card_desc;
        this.card_balance = card_balance;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_desc() {
        return card_desc;
    }

    public void setCard_desc(String card_desc) {
        this.card_desc = card_desc;
    }

    public String getCard_balance() {
        return card_balance;
    }

    public void setCard_balance(String card_balance) {
        this.card_balance = card_balance;
    }
}
