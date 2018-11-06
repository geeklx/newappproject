package com.example.shining.libglin.glin.client;


import com.example.shining.libglin.glin.cache.ICacheProvider;
import com.example.shining.libglin.glin.factory.ParserFactory;
import com.example.shining.libglin.glin.interceptor.IResultInterceptor;

import java.util.LinkedHashMap;

public interface IClient extends IRequest {
    void cancel(final Object tag);
    void parserFactory(ParserFactory factory);
    LinkedHashMap<String, String> headers();
    void resultInterceptor(IResultInterceptor interceptor);
    void cacheProvider(ICacheProvider provider);
    void timeout(long ms);
}
