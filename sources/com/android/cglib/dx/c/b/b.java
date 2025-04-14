package com.android.cglib.dx.c.b;

import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.k;
import com.android.cglib.dx.d.l;

public final class b implements l {
    private final int a;
    private final g b;
    private final k c;
    private final int d;

    public b(int i, g gVar, k kVar, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("label < 0");
        }
        try {
            gVar.i();
            int a2 = gVar.a();
            if (a2 == 0) {
                throw new IllegalArgumentException("insns.size() == 0");
            }
            for (int i3 = a2 - 2; i3 >= 0; i3--) {
                if (gVar.a(i3).d().b() != 1) {
                    throw new IllegalArgumentException("insns[" + i3 + "] is a branch or can throw");
                }
            }
            if (gVar.a(a2 - 1).d().b() == 1) {
                throw new IllegalArgumentException("insns does not end with a branch or throwing instruction");
            }
            try {
                kVar.i();
                if (i2 < -1) {
                    throw new IllegalArgumentException("primarySuccessor < -1");
                } else if (i2 < 0 || kVar.f(i2)) {
                    this.a = i;
                    this.b = gVar;
                    this.c = kVar;
                    this.d = i2;
                } else {
                    throw new IllegalArgumentException("primarySuccessor " + i2 + " not in successors " + kVar);
                }
            } catch (NullPointerException unused) {
                throw new NullPointerException("successors == null");
            }
        } catch (NullPointerException unused2) {
            throw new NullPointerException("insns == null");
        }
    }

    public int a() {
        return this.a;
    }

    public g b() {
        return this.b;
    }

    public k c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        if (this.c.a() != 2) {
            throw new UnsupportedOperationException("block doesn't have exactly two successors");
        }
        int a2 = this.c.a(0);
        return a2 == this.d ? this.c.a(1) : a2;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public f f() {
        return this.b.b();
    }

    public boolean g() {
        return this.b.b().h();
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return '{' + i.c(this.a) + '}';
    }
}
