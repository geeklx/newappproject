/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.shining.libglin.net;

import android.util.Log;

import com.example.shining.libglin.glin.Glin;
import com.example.shining.libglin.glin.chan.LogChanNode;
import com.example.shining.libglin.glin.helper.LogHelper;
import com.example.shining.libglin.glin.interceptor.IResultInterceptor;
import com.example.shining.libglin.net.cache.JsonCacheProvider;
import com.example.shining.libglin.net.parser.FastJsonParserFactory;


/**
 * <p class="note">Net Library</p>
 */

public class Net {

    private static Net sInstance;
    private Glin mGlin;

    private static final LogHelper.LogPrinter printer = new LogHelper.LogPrinter() {
        @Override
        public void print(String tag, String content) {
            Log.d(tag, content);
        }
    };

    private Net(Builder builder) {
        mGlin = new Glin.Builder()
                .client(new OkClient())
                .baseUrl(builder.baseUrl)
                .timeout(builder.timeoutMs)
                .logChanNode(new LogChanNode(builder.debug, printer))
                .parserFactory(new FastJsonParserFactory())
                .resultInterceptor(builder.resultInterceptor)
                .cacheProvider(new JsonCacheProvider(builder.cacheDir, builder.cacheSize))
                .build();
    }

    public static Net get() {
        return sInstance;
    }

    /**
     * 获取网络框架
     *
     * @return
     */
    public Glin glin() {
        return mGlin;
    }

    /**
     * 创建一个业务请求
     *
     * @param convertClass 业务请求接口的class
     * @param tag          本次网络请求的tag
     * @return
     */
    public <T> T create(Class<T> convertClass, Object tag) {
        return glin().create(convertClass, tag);
    }

    /**
     * 创建一个业务请求 <br />
     * {@link Net#create}方法的静态封装
     *
     * @param convertClass 业务请求接口的class
     * @param tag          本次网络请求的tag
     * @return
     */
    public static <T> T build(Class<T> convertClass, Object tag) {
        return get().create(convertClass, tag);
    }

    public static void cancel(String tag) {
        get().glin().cancel(tag);
    }

    public static class Builder {

        private String baseUrl;
        private long timeoutMs;
        private boolean debug;
        private String cacheDir;
        private long cacheSize;
        private IResultInterceptor resultInterceptor;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder timeout(long timeoutMs) {
            this.timeoutMs = timeoutMs;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder cacheDir(String cacheDir) {
            this.cacheDir = cacheDir;
            return this;
        }

        public Builder cacheSize(long cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Builder resultInterceptor(IResultInterceptor resultInterceptor) {
            this.resultInterceptor = resultInterceptor;
            return this;
        }

        public void build() {
            sInstance = new Net(this);
        }
    }
}