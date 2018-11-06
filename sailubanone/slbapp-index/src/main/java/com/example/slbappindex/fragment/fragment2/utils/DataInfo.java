package com.example.slbappindex.fragment.fragment2.utils;

import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ChildImgBean;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ImgBean;
import com.haier.cellarette.libutils.utilslib.etc.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataInfo {
    private static Map dataMap;

    public static void setData() {
        dataMap = new HashMap();
        List<Fragment21ImgBean> fragment21ImgBeanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fragment21ChildImgBean status = new Fragment21ChildImgBean();
            status.setUserName(DateUtil.format_ymd(new Date()));
            status.setCreatedAt(DateUtil.format_ymd(new Date()));
            status.setRetweet(false);
            status.setUserAvatar("跳转到视频播放页面的id");
            status.setText("这是一款神奇的工具");
            Fragment21ImgBean fragment21ImgBean = new Fragment21ImgBean(Fragment21ImgBean.style3, status);
            fragment21ImgBeanList.add(fragment21ImgBean);
        }

        List<Fragment21ImgBean> fragment21ImgBeanList1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fragment21ChildImgBean status = new Fragment21ChildImgBean();
            status.setUserName(DateUtil.format_ymd(new Date()));
            status.setCreatedAt(DateUtil.format_ymd(new Date()));
            status.setRetweet(false);
            status.setUserAvatar("跳转到视频播放页面的id");
            status.setText("这是一款神奇的工具");
            Fragment21ImgBean fragment21ImgBean = new Fragment21ImgBean(Fragment21ImgBean.style2, status);
            fragment21ImgBeanList1.add(fragment21ImgBean);
        }
        dataMap.put(Fragment21ImgBean.style3, fragment21ImgBeanList);
        dataMap.put(Fragment21ImgBean.style2, fragment21ImgBeanList1);
    }


    private List<Fragment21ImgBean> getList() {
        return (List<Fragment21ImgBean>) dataMap.values();
    }

    private void deleteData(){

    }
}
