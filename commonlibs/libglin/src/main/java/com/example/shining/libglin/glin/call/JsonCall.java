package com.example.shining.libglin.glin.call;


import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Params;
import com.example.shining.libglin.glin.client.IClient;

public class JsonCall<T> extends Call<T> {

    public JsonCall(IClient client, String url,
                    Params params, Object tag,
                    boolean cache) {
        super(client, url, params, tag, cache);
    }

    @Override
    public void exec(final Callback<T> callback) {
        String json = mParams.getParams(Params.DEFAULT_JSON_KEY);
        if (json == null) {
            throw new UnsupportedOperationException("cannot find json");
        }
        mClient.post(mUrl, mHeaders, mParams.getParams(Params.DEFAULT_JSON_KEY), mTag, shouldCache, callback);
    }
}
