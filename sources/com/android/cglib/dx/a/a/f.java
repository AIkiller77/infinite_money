package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.s;
import com.android.cglib.dx.c.c.a;

public final class f extends l {
    private final a a;
    private int b;
    private int c;

    public f(j jVar, s sVar, n nVar, a aVar) {
        super(jVar, sVar, nVar);
        if (aVar == null) {
            throw new NullPointerException("constant == null");
        }
        this.a = aVar;
        this.b = -1;
        this.c = -1;
    }

    public h a(j jVar) {
        f fVar = new f(jVar, h(), i(), this.a);
        if (this.b >= 0) {
            fVar.a(this.b);
        }
        if (this.c >= 0) {
            fVar.b(this.c);
        }
        return fVar;
    }

    public h a(n nVar) {
        f fVar = new f(g(), h(), nVar, this.a);
        if (this.b >= 0) {
            fVar.a(this.b);
        }
        if (this.c >= 0) {
            fVar.b(this.c);
        }
        return fVar;
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.a.a_();
    }

    public void a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        } else if (this.b >= 0) {
            throw new RuntimeException("index already set");
        } else {
            this.b = i;
        }
    }

    public a b() {
        return this.a;
    }

    public void b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        } else if (this.c >= 0) {
            throw new RuntimeException("class index already set");
        } else {
            this.c = i;
        }
    }

    public int c() {
        if (this.b >= 0) {
            return this.b;
        }
        throw new RuntimeException("index not yet set for " + this.a);
    }

    public boolean d() {
        return this.b >= 0;
    }
}
