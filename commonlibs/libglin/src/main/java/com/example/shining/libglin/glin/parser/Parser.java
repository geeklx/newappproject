package com.example.shining.libglin.glin.parser;


import com.example.shining.libglin.glin.RawResult;
import com.example.shining.libglin.glin.Result;

public abstract class Parser {
    public String mKey;

    public Parser() {

    }

    public Parser(String key) {
        mKey = key;
    }

    /**
     *
     * @param klass the class of data struct
     * @param netResult
     * @param <T>
     * @return
     */
    public abstract <T> Result<T> parse(Class<T> klass, RawResult netResult);
}
