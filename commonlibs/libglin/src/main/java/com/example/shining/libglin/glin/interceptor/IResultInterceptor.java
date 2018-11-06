package com.example.shining.libglin.glin.interceptor;


import com.example.shining.libglin.glin.Result;

public interface IResultInterceptor {
    /**
     * 是否拦截结果
     * @param result
     * @return true callback不会执行
     */
    boolean intercept(Result<?> result);
}
