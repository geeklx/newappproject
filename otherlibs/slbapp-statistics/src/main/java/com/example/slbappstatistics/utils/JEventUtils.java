package com.example.slbappstatistics.utils;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import cn.jiguang.analytics.android.api.BrowseEvent;
import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.Currency;
import cn.jiguang.analytics.android.api.Event;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.PurchaseEvent;
import cn.jiguang.analytics.android.api.RegisterEvent;

/**
 * JAnalytics 自定义事件相关使用示例
 */
public class JEventUtils {
    private static final String TAG = "JEventUtils";
    private static int eventIdIndex = 1;

    /**
     * 登录事件
     *
     * @param context
     */
    public static void onLoginEvent(Context context) {
        LoginEvent loginEvent = new LoginEvent("testLoginMethod", true);
        //添加自己的Extra 信息
        //方法1：增加一个hashMap<String,String>
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put("key_login_event_extra1", "value_extra1");
        loginEvent.addExtMap(extraMap);

        Map<String, String> extraMap2 = new HashMap<String, String>();
        extraMap2.put("key_login_event_extra2", "value_extra2");
        loginEvent.addExtMap(extraMap2);

        //方法2：增加单个值
        loginEvent.addKeyValue("key_login_event_extra3", "value_extra3")
                .addKeyValue("key_login_event_extra4", "value_extra4");
        JAnalyticsInterface.onEvent(context, loginEvent);
    }

    /**
     * 登录事件
     *
     * @param context
     * @param loginMethod 登录方式
     * @param isSuccess   是否成功
     * @param extra       附加信息
     */
    public static void onLoginEvent(Context context,
                                    String loginMethod,
                                    boolean isSuccess,
                                    Map<String, String> extra) {

        Event loginEvent = new LoginEvent()
                .setLoginMethod(loginMethod) //login方式
                .setLoginSuccess(isSuccess) //是否成功
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, loginEvent);
    }

    /**
     * 注册事件
     *
     * @param context
     */
    public static void onRegisterEvent(Context context) {
        RegisterEvent registerEvent = new RegisterEvent("testRegisterMethod", true);
        //添加自己的Extra 信息
        //方法1：增加一个hashMap<String,String>
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put("key_register_event_extra1", "value_extra1");
        registerEvent.addExtMap(extraMap);

        Map<String, String> extraMap2 = new HashMap<String, String>();
        extraMap2.put("key_register_event_extra2", "value_extra2");
        registerEvent.addExtMap(extraMap2);

        //方法2：增加单个值
        registerEvent.addKeyValue("key_register_event_extra3", "value_extra3")
                .addKeyValue("key_register_event_extra4", "value_extra4");
        JAnalyticsInterface.onEvent(context, registerEvent);
    }

    /**
     * 注册事件
     *
     * @param context
     * @param registerMethod 注册方式
     * @param isSuccess      是否成功
     * @param extra          附加信息
     */
    public static void onRegisterEvent(Context context,
                                       String registerMethod,
                                       boolean isSuccess,
                                       Map<String, String> extra) {

        Event registerEvent = new RegisterEvent()
                .setRegisterMethod(registerMethod) //注册方式
                .setRegisterSuccess(isSuccess) //是否成功
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, registerEvent);
    }

    /**
     * 购买事件
     *
     * @param context
     */
    public static void onPurchaseEvent(Context context) {
        PurchaseEvent purchaseEvent = new PurchaseEvent("test_purchaseGoodsID", //商品ID
                "篮球", //商品名称
                300, //商品价格
                true, //商品购买是否成功
                Currency.CNY, //货币类型
                "sport",//商品类型
                1); //商品购买数量
        //添加自己的Extra 信息
        //方法1：增加一个hashMap<String,String>
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put("key_purchase_event_extra1", "value_extra1");
        purchaseEvent.addExtMap(extraMap);

        Map<String, String> extraMap2 = new HashMap<String, String>();
        extraMap2.put("key_purchase_event_extra2", "value_extra2");
        purchaseEvent.addExtMap(extraMap2);

        //方法2：增加单个值
        purchaseEvent.addKeyValue("key_purchase_event_extra3", "value_extra3")
                .addKeyValue("key_purchase_event_extra4", "value_extra4");

        JAnalyticsInterface.onEvent(context, purchaseEvent);
    }

    /**
     * 购买事件
     *
     * @param context
     * @param purchaseGoodsid    物品id，开发者自己定义
     * @param purchaseGoodsname  购买物品名称(如lv ，香奈儿，劳力士表，安踏运动鞋 etc）
     * @param purchasePrice      购买价格
     * @param purchaseSuccess    购买是否成功
     * @param purchaseCurrency   购买货币类型(人民币(CNY)，美元(USD) ,具体参考Currency)
     * @param purchaseGoodsCount 购买数量
     * @param purchaseGoodstype  购买物品类型(如衣服，手表，鞋子，家用电器etc)
     */
    public static void onPurchaseEvent(Context context,
                                       String purchaseGoodsid,
                                       String purchaseGoodsname,
                                       double purchasePrice,
                                       boolean purchaseSuccess,
                                       Currency purchaseCurrency,
                                       String purchaseGoodstype,
                                       int purchaseGoodsCount,
                                       Map<String, String> extra) {

        Event purchaseEvent = new PurchaseEvent()
                .setPurchaseGoodsid(purchaseGoodsid) //商品ID
                .setPurchaseGoodsname(purchaseGoodsname) //商品名称
                .setPurchasePrice(purchasePrice) //商品价格
                .setPurchaseSuccess(purchaseSuccess) //购买是否成功
                .setPurchaseCurrency(purchaseCurrency) //货币类型
                .setPurchaseGoodstype(purchaseGoodstype) //商品类型
                .setPurchaseGoodsCount(purchaseGoodsCount)
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, purchaseEvent);
    }

    /**
     * 浏览事件
     *
     * @param context
     */
    public static void onBrowseEvent(Context context) {
        BrowseEvent browseEvent = new BrowseEvent("test_browseID",//设置浏览内容id
                "深圳热点新闻",//设置浏览的内容的名称
                "news", //设置浏览的内容类型
                30);//设置浏览的内容时长,单位秒

        //添加自己的Extra 信息
        //方法1：增加一个hashMap<String,String>
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put("key_browse_event_extra1", "value_extra1");
        browseEvent.addExtMap(extraMap);

        Map<String, String> extraMap2 = new HashMap<String, String>();
        extraMap2.put("key_browse_event_extra2", "value_extra2");
        browseEvent.addExtMap(extraMap2);

        //方法2：增加单个值
        browseEvent.addKeyValue("key_browse_event_extra3", "value_extra3")
                .addKeyValue("key_browse_event_extra4", "value_extra4");
        JAnalyticsInterface.onEvent(context, browseEvent);
    }

    /**
     * 浏览事件
     *
     * @param context
     * @param browseId       浏览内容id，开发者自己定义
     * @param browseName     浏览的内容的名称(如内容标题等)
     * @param browseType     浏览的内容类型(如是热点，还是nba，还是cba，还是汽车，财经 etc)
     * @param browseDuration 浏览的内容时长，单位秒
     * @param extra          附加信息
     */
    public static void onBrowseEvent(Context context,
                                     String browseId,
                                     String browseName,
                                     String browseType,
                                     float browseDuration,
                                     Map<String, String> extra) {

        Event browseEvent = new BrowseEvent()
                .setBrowseId(browseId) //设置浏览内容id
                .setBrowseName(browseName) //设置浏览的内容的名称
                .setBrowseType(browseType) //设置浏览的内容类型
                .setBrowseDuration(browseDuration) //设置浏览的内容时长
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, browseEvent);
    }

    /**
     * 计算事件
     *
     * @param context
     */
    public static void onCalculateEvent(Context context) {
        CalculateEvent calculateEvent = new CalculateEvent("test_eventID"+eventIdIndex, 1);
        //添加自己的Extra 信息
        //方法1：增加一个hashMap<String,String>
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put("key_calculate_event_extra1", "value_extra1");
        calculateEvent.addExtMap(extraMap);

        Map<String, String> extraMap2 = new HashMap<String, String>();
        extraMap2.put("key_calculate_event_extra2", "value_extra2");
        calculateEvent.addExtMap(extraMap2);

        //方法2：增加单个值
        calculateEvent.addKeyValue("key_calculate_event_extra3", "value_extra3")
                .addKeyValue("key_calculate_event_extra4", "value_extra4");
        JAnalyticsInterface.onEvent(context, calculateEvent);
        eventIdIndex++;
    }

    /**
     * 计算事件
     *
     * @param context
     * @param eventId    事件ID
     * @param eventValue 事件对应的值
     * @param extra      附加信息
     */
    public static void onCalculateEvent(Context context,
                                        String eventId,
                                        double eventValue,
                                        Map<String, String> extra) {


        Event calculateEvent = new CalculateEvent()
                .setEventId(eventId)
                .setEventValue(eventValue)
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, calculateEvent);
    }

    /**
     * 计数事件
     *
     * @param context
     */
    public static void onCountEvent(Context context) {
        CountEvent countEvent = new CountEvent("test_countEventID"+eventIdIndex);
        //添加自己的Extra 信息
        //方法1：增加一个hashMap<String,String>
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put("key_count_event_extra1", "value_extra1");
        countEvent.addExtMap(extraMap);

        Map<String, String> extraMap2 = new HashMap<String, String>();
        extraMap2.put("key_count_event_extra2", "value_extra2");
        countEvent.addExtMap(extraMap2);

        //方法2：增加单个值
        countEvent.addKeyValue("key_count_event_extra3", "value_extra3")
                .addKeyValue("key_count_event_extra4", "value_extra4");

        JAnalyticsInterface.onEvent(context, countEvent);
        eventIdIndex++;
    }

    /**
     * 计数事件
     *
     * @param context
     * @param eventId   事件ID
     * @param extra     附加信息
     */
    public static void onCountEvent(Context context, String eventId, Map<String, String> extra) {
        Event countEvent = new CountEvent()
                .setEventId(eventId)
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, countEvent);
    }
}
