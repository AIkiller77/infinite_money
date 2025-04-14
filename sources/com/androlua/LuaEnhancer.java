package com.androlua;

import com.a.a.a.a.a.a.a;
import com.android.cglib.proxy.Enhancer;
import com.android.cglib.proxy.EnhancerInterface;
import com.android.cglib.proxy.MethodFilter;
import com.android.cglib.proxy.MethodInterceptor;

public class LuaEnhancer {
    private Enhancer a;

    public LuaEnhancer(Class<?> cls) {
        this.a = new Enhancer(LuaApplication.getInstance());
        this.a.setSuperclass(cls);
    }

    public LuaEnhancer(String str) {
        this(Class.forName(str));
    }

    public Class<?> create() {
        try {
            return this.a.create();
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    public Class<?> create(MethodFilter methodFilter) {
        try {
            this.a.setMethodFilter(methodFilter);
            return this.a.create();
        } catch (Exception e) {
            a.a(e);
            return null;
        }
    }

    public void setInterceptor(EnhancerInterface enhancerInterface, MethodInterceptor methodInterceptor) {
        enhancerInterface.setMethodInterceptor_Enhancer(methodInterceptor);
    }
}
