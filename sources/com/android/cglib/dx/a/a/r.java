package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.s;

public final class r extends ab {
    private final m a;

    public r(s sVar, m mVar) {
        super(sVar);
        if (mVar == null) {
            throw new NullPointerException("local == null");
        }
        this.a = mVar;
    }

    public static String a(m mVar) {
        return mVar.k() + ' ' + mVar.g().toString() + ": " + mVar.f().a_();
    }

    public h a(n nVar) {
        return new r(h(), this.a);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.a.toString();
    }

    /* access modifiers changed from: protected */
    public String a(boolean z) {
        return "local-start " + a(this.a);
    }

    public m b() {
        return this.a;
    }

    public h d(int i) {
        return new r(h(), this.a.c(i));
    }
}
