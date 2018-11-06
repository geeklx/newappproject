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
 * <p class="note">解析json对象到java bean</p>
 */
public class CommParser extends Parser {

    public CommParser(String key) {
        super(key);
    }

    @Override
    public <T> Result<T> parse(Class<T> klass, RawResult netResult) {
        Result<T> result = new Result<>();
        try {
            JSONObject baseObject = JSON.parseObject(netResult.getResponse());
            result = JsonParser.parseObject(baseObject, klass, mKey);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("数据获取失败");
        }
        return result;
    }
}
