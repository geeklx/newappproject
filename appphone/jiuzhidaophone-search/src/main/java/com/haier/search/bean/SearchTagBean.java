package com.haier.search.bean;

/**
 * Created by JefferyLeng on 2018/6/12.
 */
public class SearchTagBean {
    private String tagId;
    private String tagName;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public SearchTagBean(String tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public SearchTagBean(String tagName) {
        this.tagName = tagName;
    }

    public SearchTagBean() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (this.getClass() == obj.getClass()) {
                SearchTagBean searchTagBean = (SearchTagBean) obj;
                return this.getTagName().equals(searchTagBean.getTagName());
            }
            return false;
        }
    }
}
