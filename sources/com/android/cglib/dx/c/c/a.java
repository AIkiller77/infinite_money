package com.android.cglib.dx.c.c;

import com.android.cglib.dx.d.r;

public abstract class a implements r, Comparable<a> {
    /* renamed from: a */
    public final int compareTo(a aVar) {
        Class<?> cls = getClass();
        Class<?> cls2 = aVar.getClass();
        return cls != cls2 ? cls.getName().compareTo(cls2.getName()) : b(aVar);
    }

    /* access modifiers changed from: protected */
    public abstract int b(a aVar);

    public abstract String e();
}
