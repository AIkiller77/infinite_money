package com.androlua;

import dalvik.system.DexClassLoader;
import java.util.HashMap;

public class LuaDexClassLoader extends DexClassLoader {
    private HashMap<String, Class<?>> a = new HashMap<>();
    private String b;

    public LuaDexClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
        super(str, str2, str3, classLoader);
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str) {
        Class<?> cls = this.a.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> findClass = super.findClass(str);
        this.a.put(str, findClass);
        return findClass;
    }

    public String getDexPath() {
        return this.b;
    }
}
