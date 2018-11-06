package com.example.shining.libglin.glin.call;


import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Params;
import com.example.shining.libglin.glin.client.IClient;

public class PutCall<T> extends Call<T> {

    public PutCall(IClient client, String url,
                   Params params, Object tag,
                   boolean cache) {
        super(client, url, params, tag, cache);
    }

    @Override
    public void exec(Callback<T> callback) {
        mClient.put(mUrl, mHeaders, mParams, mTag, shouldCache, callback);
    }
}
