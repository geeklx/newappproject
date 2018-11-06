package com.example.shining.libglin.glin.call;


import com.example.shining.libglin.glin.Callback;
import com.example.shining.libglin.glin.Params;
import com.example.shining.libglin.glin.client.IClient;

public class GetCall<T> extends Call<T> {

    public GetCall(IClient client, String url,
                   Params params, Object tag,
                   boolean cache) {
        super(client, url, params, tag, cache);
    }

    @Override
    public void exec(final Callback<T> callback) {
        String query = mParams.encode();

        String url = mUrl;
        if (query != null) {
            if (url.contains("?")) { url = url + "&" + query;}
            else { url = url + "?" + query;}
        }

        mClient.get(url, mHeaders, mTag, shouldCache, callback);
    }
}
