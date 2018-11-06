package com.example.shining.libglin.glin.call;


import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Params;
import com.example.shining.libglin.glin.client.IClient;

public class DelCall<T> extends Call<T> {

    public DelCall(IClient client, String url,
                   Params params, Object tag,
                   boolean cache) {
        super(client, url, params, tag, cache);
    }

    @Override
    public void exec(Callback<T> callback) {
        mClient.delete(mUrl, mHeaders, mTag, shouldCache, callback);
    }
}
