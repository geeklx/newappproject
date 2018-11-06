package com.haier.cellarette.jiuzhidao_demo.glinbufen.bean.glinnet;

import java.io.Serializable;
import java.util.List;

public class Demo1Model implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Demo1Bean> userShopCardList;

    public Demo1Model() {
    }

    public Demo1Model(List<Demo1Bean> userShopCardList) {
        this.userShopCardList = userShopCardList;
    }

    public List<Demo1Bean> getUserShopCardList() {
        return userShopCardList;
    }

    public void setUserShopCardList(List<Demo1Bean> userShopCardList) {
        this.userShopCardList = userShopCardList;
    }
}
