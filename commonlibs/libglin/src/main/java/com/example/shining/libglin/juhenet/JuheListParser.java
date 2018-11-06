package com.example.shining.libglin.juhenet;

import com.alibaba.fastjson.JSON;
import com.example.shining.libglin.glin.RawResult;
import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.glin.parser.Parser;


public class JuheListParser extends Parser {

    @Override
    public <T> Result<T> parse(Class<T> klass, RawResult netResult) {
        Result<T> result = new Result<T>();
        result.setCode(netResult.getStatusCode());
        result.setObj(result.getCode());
        result.setMessage(netResult.getMessage());
        try {
            T res = (T) JSON.parseArray(netResult.getResponse(), klass);
            result.setResult(res);
            result.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.ok(false);
        }

        return result;
    }
}
