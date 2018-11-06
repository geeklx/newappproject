/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.shining.libglin.net.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.shining.libglin.glin.Result;


/**
 * <p class="note">JsonParser</p>
 */
public class JsonParser {

    public static <T> Result<T> parseArray(JSONObject baseObject, Class<T> klass, String key) {
        Result<T> result = new Result<>();
        if (baseObject.containsKey("message")) {
            result.setMessage(baseObject.getString("message"));// message always get
        }

        result.setCode(baseObject.getIntValue("code"));
        result.setObj(result.getCode());
        result.ok(baseObject.getBooleanValue("ok"));
        if (result.isOK()) { // ok true
            if (baseObject.containsKey("data")) {
                JSONArray arr = baseObject.getJSONArray("data");
                T t = (T) JSON.parseArray(arr.toString(), klass);
                result.setResult(t);
            }
        }
        return result;
    }

    public static <T> Result<T> parseObject(JSONObject baseObject, Class<T> klass, String key) {
        Result<T> result = new Result<>();
        if (baseObject.containsKey("message")) {
            result.setMessage(baseObject.getString("message"));// message always get
        }

        result.setCode(baseObject.getIntValue("code"));
        result.setObj(result.getCode());
        result.ok(baseObject.getBooleanValue("ok"));
        if (result.isOK()) { // ok true
            if (baseObject.containsKey("data")) {
                T t = baseObject.getObject("data", klass);
                result.setResult(t);
            }
        }
        return result;
    }
}
