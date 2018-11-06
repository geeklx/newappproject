package com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean;

import com.haier.cellarette.baselibrary.recycleviewalluses.demo3baseadpterhelp.BaseRecActDemo3Bean;
import com.haier.cellarette.baselibrary.recycleviewalluses.demo3baseadpterhelp.BaseRecActDemo3BeanHead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopItemBean implements Serializable {
    private String tab_id;
    private String tab_name;

    public ShopItemBean() {
    }

    public ShopItemBean(String tab_id, String tab_name) {
        this.tab_id = tab_id;
        this.tab_name = tab_name;
    }

    public String getTab_id() {
        return tab_id;
    }

    public void setTab_id(String tab_id) {
        this.tab_id = tab_id;
    }

    public String getTab_name() {
        return tab_name;
    }

    public void setTab_name(String tab_name) {
        this.tab_name = tab_name;
    }


    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "CymChad";
    public static List<BaseRecActDemo3BeanHead> getSampleData1() {
        List<BaseRecActDemo3BeanHead> list = new ArrayList<>();
        list.add(new BaseRecActDemo3BeanHead(true, "Section 1", true));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(true, "Section 2", false));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(true, "Section 3", false));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(true, "Section 4", false));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(true, "Section 5", true));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        return list;
    }

    public static List<BaseRecActDemo3BeanHead> getSampleData2() {
        List<BaseRecActDemo3BeanHead> list = new ArrayList<>();
        list.add(new BaseRecActDemo3BeanHead(true, "Section 1", true));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));
        list.add(new BaseRecActDemo3BeanHead(new BaseRecActDemo3Bean(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD)));

        return list;
    }
}
