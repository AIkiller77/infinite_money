package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.s;

public final class o extends ab {
    private final m a;

    public o(s sVar, m mVar) {
        super(sVar);
        if (mVar == null) {
            throw new NullPointerException("local == null");
        }
        this.a = mVar;
    }

    public h a(n nVar) {
        return new o(h(), this.a);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.a.toString();
    }

    /* access modifiers changed from: protected */
    public String a(boolean z) {
        return "local-end " + r.a(this.a);
    }

    public m b() {
        return this.a;
    }

    public h d(int i) {
        return new o(h(), this.a.c(i));
    }
}
