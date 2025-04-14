package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.c.a;

public abstract class d extends f {
    private final a a;

    public d(p pVar, s sVar, m mVar, n nVar, a aVar) {
        super(pVar, sVar, mVar, nVar);
        if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
        this.a = aVar;
    }

    public String b() {
        return this.a.a_();
    }

    public a c() {
        return this.a;
    }
}
