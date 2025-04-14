package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.w;

public abstract class v extends w {
    private final w a;

    public v(w wVar) {
        if (wVar == null) {
            throw new NullPointerException("type == null");
        }
        this.a = wVar;
    }

    public void a(l lVar) {
        lVar.j().a(this.a);
    }

    public final w e() {
        return this.a;
    }
}
