package com.haier.search.contract;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.haier.cellarette.libmvp.mvp.Presenter;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libutils.utilslib.data.SpUtils;
import com.haier.search.bean.SearchTagBean;
import com.haier.search.utils.DataQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by JefferyLeng on 2018/6/12.
 */
public class ShopSearchPresenter extends Presenter<ShopSearchContract.ShopSearchView> {

    private final String TAG = this.getClass().getSimpleName();
    private DataQueue<SearchTagBean> mSearchTagBeanDataQueue = new DataQueue<>();
    private ArrayList<SearchTagBean> mSearchTagBeans;
    private final String SP_SHOP_SEARCH_HISTORY = "sp_shop_search_history";
    private Context mContext;

    public List<SearchTagBean> getHotWords() {
        return fillData();
    }

    /**
     * 初始化
     * 1，view的热词搜索
     * 2，view的本地搜索历史
     */
    public void start(Context context) {
        mContext = context.getApplicationContext();
        getView().updateHotWordsView(getHotWords());
        getView().updateLocalHistoryWordsView(initHistoryWords());
    }

    /**
     * 处理本次查询的数据
     * @param currentSearchContent
     */
    public void handleLocalSearchHistory(String currentSearchContent) {
        mSearchTagBeanDataQueue.queue(new SearchTagBean(currentSearchContent));
        LinkedList<SearchTagBean> dataQueue = mSearchTagBeanDataQueue.getDataQueue();
        getView().updateLocalHistoryWordsView(dataQueue);
    }


    /**
     * 模拟接口请求数据
     * @return
     */
    public List<SearchTagBean> fillData() {
        // TODO: 2018/6/15  do network to get hotwords from server
        if (mSearchTagBeans == null) {
            mSearchTagBeans = new ArrayList<>();
        }
//        for (int i = 0; i < 9; i++) {
//            mSearchTagBeans.add(new SearchTagBean("tag" + i,"tagName" +i));
//        }
        mSearchTagBeans.add(new SearchTagBean("拉睦"));
        mSearchTagBeans.add(new SearchTagBean("梦坡"));
        mSearchTagBeans.add(new SearchTagBean("菲斯特"));
        mSearchTagBeans.add(new SearchTagBean("须尽欢"));
        mSearchTagBeans.add(new SearchTagBean("冰白"));
        mSearchTagBeans.add(new SearchTagBean("120"));
        mSearchTagBeans.add(new SearchTagBean("奔富"));
        mSearchTagBeans.add(new SearchTagBean("拉菲"));
        return mSearchTagBeans;
    }

    public LinkedList<SearchTagBean> initHistoryWords() {
        /**
         * 1，页面初始化 读取SP对象 反序列化为List对象，更新界面ui
         * 2，将反序列化完成的list对象存储到DataQueue中，做内存cache
         * 3，返回界面时，将内存缓存序列号到SP中
         *
         */
        String queueStr = (String) SpUtils.getInstance(mContext).get(SP_SHOP_SEARCH_HISTORY, "");
        if (TextUtils.isEmpty(queueStr)) {
            return null;
        }
        MyLogUtil.d(TAG,"initHistoryWords ---> sp str is : " + queueStr);
        List<SearchTagBean> searchTagBeanList = JSON.parseArray(queueStr,SearchTagBean.class);
        LinkedList<SearchTagBean> linkedList = new LinkedList<>(searchTagBeanList);
        mSearchTagBeanDataQueue.recoverData(linkedList);
        return linkedList;

    }

    /**
     * 将内存的搜索历史数据集合持久化到文件中
     */
    public void toSyncHistorySearchWords() {
        if (mSearchTagBeanDataQueue.getQueueSize() == 0) {
            MyLogUtil.d(TAG,"没有查询数据，不需要持久化SP");
            return;
        }
        LinkedList<SearchTagBean> dataQueue = mSearchTagBeanDataQueue.getDataQueue();
        String queueStr = JSON.toJSONString(dataQueue);
        MyLogUtil.d(TAG,"持久化sp的数据str ： " + queueStr);
        SpUtils.getInstance(mContext).put(SP_SHOP_SEARCH_HISTORY,queueStr);
    }

    /**
     * 清除历史调用
     * @return 该次调用的处理结果，UI直接显示
     */
    public String clearHistoryData() {
        if (mSearchTagBeanDataQueue.getQueueSize() == 0) {
            return "没有搜索历史哦";
        }
        mSearchTagBeanDataQueue.clearData();
        SpUtils.getInstance(mContext).put(SP_SHOP_SEARCH_HISTORY,"");
        getView().updateLocalHistoryWordsView(null);
        return "清除成功";
    }
}
