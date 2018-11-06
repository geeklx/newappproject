package com.example.shining.libglin.glin.factory;


import com.example.shining.libglin.glin.annotation.DEL;
import com.example.shining.libglin.glin.annotation.GET;
import com.example.shining.libglin.glin.annotation.JSON;
import com.example.shining.libglin.glin.annotation.POST;
import com.example.shining.libglin.glin.annotation.PUT;
import com.example.shining.libglin.glin.call.Call;
import com.example.shining.libglin.glin.call.DelCall;
import com.example.shining.libglin.glin.call.GetCall;
import com.example.shining.libglin.glin.call.JsonCall;
import com.example.shining.libglin.glin.call.PostCall;
import com.example.shining.libglin.glin.call.PutCall;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public class CallFactory {

    private HashMap<Class<? extends Annotation>, Class<? extends Call>> mMapping = new HashMap<>();

    public CallFactory() {
        autoRegist();
    }

    private void autoRegist() {
        register(JSON.class, JsonCall.class);
        register(GET.class, GetCall.class);
        register(POST.class, PostCall.class);
        register(PUT.class, PutCall.class);
        register(DEL.class, DelCall.class);
    }

    public void register(Class<? extends Annotation> key, Class<? extends Call> value) {
        mMapping.put(key, value);
    }

    public Class<? extends Call> get(Class<? extends Annotation> key) {
        return mMapping.get(key);
    }

    public HashMap<Class<? extends Annotation>, Class<? extends Call>> get() {
        return mMapping;
    }
}
