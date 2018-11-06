package com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class Fragment21ChildImgBean {
    private boolean isRetweet;
    private String text;
    private String userName;
    private String userAvatar;// img_url
    private String createdAt;// 2018-10-25

    public Fragment21ChildImgBean() {
    }

    public Fragment21ChildImgBean(boolean isRetweet, String text, String userName, String userAvatar, String createdAt) {
        this.isRetweet = isRetweet;
        this.text = text;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.createdAt = createdAt;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Status{" +
                "isRetweet=" + isRetweet +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
