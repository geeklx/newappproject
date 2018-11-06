package com.haier.cellarette.jiuzhidao_demo.glinbufen.glinmvp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ParamsUtils {

    /**
     * 根据一个java bean 生成参数
     *
     * @param bean
     * @return
     */
    public static String just(Object bean) {
        if (bean == null) {
            return justJSON(null);
        }
        return justJSON((JSONObject) JSON.toJSON(bean));
    }

    /**
     * 根据一个组合好的data 生成参数
     *
     * @param data
     * @return
     */
    public static String justJSON(JSONObject data) {
        if (data == null) {
            data = new JSONObject();
        }
        JSONObject params = new JSONObject();
        params.put("verify_info", verifyInfo(data));
        params.put("data", data);
        return params.toJSONString();
    }

    public static JSONObject verifyInfo(JSONObject data) {
//        JSONObject info = new JSONObject();
//        info.put("app", app());
//        info.put("device", device());
        return (JSONObject) JSON.toJSON(null);//DataProvider.getVerifyInfo(data)
    }
}
