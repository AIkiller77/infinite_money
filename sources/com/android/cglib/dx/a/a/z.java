package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.s;

public final class z extends l {
    private e a;

    public z(j jVar, s sVar, n nVar, e eVar) {
        super(jVar, sVar, nVar);
        if (eVar == null) {
            throw new NullPointerException("target == null");
        }
        this.a = eVar;
    }

    public h a(j jVar) {
        return new z(jVar, h(), i(), this.a);
    }

    public h a(n nVar) {
        return new z(g(), h(), nVar, this.a);
    }

    public z a(e eVar) {
        return new z(g().g(), h(), i(), eVar);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.a == null ? "????" : this.a.l();
    }

    public e b() {
        return this.a;
    }

    public int c() {
        return this.a.f();
    }

    public int d() {
        return this.a.f() - f();
    }

    public boolean o() {
        return e() && this.a.e();
    }
}
