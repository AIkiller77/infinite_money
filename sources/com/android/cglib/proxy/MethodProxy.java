package com.android.cglib.proxy;

import java.lang.reflect.Method;

public class MethodProxy {
    private Class a;
    private String b;
    private Class[] c;

    public MethodProxy(Class cls, String str, Class[] clsArr) {
        this.a = cls;
        this.b = str;
        this.c = clsArr;
    }

    public String getMethodName() {
        return this.b;
    }

    public Method getOriginalMethod() {
        try {
            return this.a.getMethod(this.b, this.c);
        } catch (NoSuchMethodException e) {
            throw new ProxyException(e.getMessage());
        }
    }

    public Method getProxyMethod() {
        try {
            Class cls = this.a;
            return cls.getMethod(this.b + Const.SUBCLASS_INVOKE_SUPER_SUFFIX, this.c);
        } catch (NoSuchMethodException e) {
            throw new ProxyException(e.getMessage());
        }
    }

    public Object invokeSuper(Object obj, Object[] objArr) {
        return ((EnhancerInterface) obj).executeSuperMethod_Enhancer(this.b, this.c, objArr);
    }
}
