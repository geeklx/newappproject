/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.shining.libglin.net.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shining.libglin.glin.RawResult;
import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.glin.parser.Parser;


/**
 * <p class="note">解析json对象到List</p>
 */
public class ListParser extends Parser {

    public ListParser(String key) {
        super(key);
    }

    @Override
    public <T> Result<T> parse(Class<T> klass, RawResult netResult) {
        Result<T> result = new Result<>();
        try {
            JSONObject baseObject = JSON.parseObject(netResult.getResponse());
            result = JsonParser.parseArray(baseObject, klass, mKey);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("数据获取失败");
        }

        return result;
    }
}