package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.d.c;
import java.util.HashSet;

public final class g {
    private final int a;
    private t b;
    private b c;
    private d d;
    private u e;
    private p f;
    private i g;

    public interface a {
        int a(com.android.cglib.dx.c.c.a aVar);
    }

    public g(int i, t tVar, b bVar) {
        if (tVar == null) {
            throw new NullPointerException("unprocessedInsns == null");
        } else if (bVar == null) {
            throw new NullPointerException("unprocessedCatches == null");
        } else {
            this.a = i;
            this.b = tVar;
            this.c = bVar;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
        }
    }

    private void j() {
        if (this.g == null) {
            this.g = this.b.d();
            this.e = u.a(this.g, this.a);
            this.f = p.a(this.g);
            this.d = this.c.a();
            this.b = null;
            this.c = null;
        }
    }

    public void a(a aVar) {
        this.b.a(aVar);
    }

    public boolean a() {
        return this.a != 1 && this.b.a();
    }

    public boolean b() {
        return this.b.b();
    }

    public boolean c() {
        return this.c.b();
    }

    public HashSet<c> d() {
        return this.c.c();
    }

    public HashSet<com.android.cglib.dx.c.c.a> e() {
        return this.b.c();
    }

    public i f() {
        j();
        return this.g;
    }

    public d g() {
        j();
        return this.d;
    }

    public u h() {
        j();
        return this.e;
    }

    public p i() {
        j();
        return this.f;
    }
}
