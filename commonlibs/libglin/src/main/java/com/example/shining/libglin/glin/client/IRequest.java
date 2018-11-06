package com.example.shining.libglin.glin.client;


import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Params;

import java.util.LinkedHashMap;

public interface IRequest {
    <T> void get(final String url, final LinkedHashMap<String, String> header, final Object tag,
                 final boolean shouldCache, final Callback<T> callback);

    <T> void post(final String url, final LinkedHashMap<String, String> header, final Params params,
                  final Object tag, final boolean shouldCache, final Callback<T> callback);

    <T> void post(final String url, final LinkedHashMap<String, String> header, final String json,
                  final Object tag, final boolean shouldCache, final Callback<T> callback);

    <T> void put(final String url, final LinkedHashMap<String, String> header, final Params params,
                 final Object tag, final boolean shouldCache, final Callback<T> callback);

    <T> void put(final String url, final LinkedHashMap<String, String> header, final String json,
                 final Object tag, final boolean shouldCache, final Callback<T> callback);

    <T> void delete(final String url, final LinkedHashMap<String, String> header,
                    final Object tag, final boolean shouldCache, final Callback<T> callback);
}
