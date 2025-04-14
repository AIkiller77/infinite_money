package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class v extends n {
    public static final n b = new v();

    private v() {
    }

    private static int d(com.android.cglib.dx.c.b.n nVar) {
        int a = nVar.a();
        if (a > 5) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < a; i2++) {
            m b2 = nVar.b(i2);
            i += b2.i();
            if (!b((b2.e() + b2.i()) - 1)) {
                return -1;
            }
        }
        if (i <= 5) {
            return i;
        }
        return -1;
    }

    private static com.android.cglib.dx.c.b.n e(com.android.cglib.dx.c.b.n nVar) {
        int d = d(nVar);
        int a = nVar.a();
        if (d == a) {
            return nVar;
        }
        com.android.cglib.dx.c.b.n nVar2 = new com.android.cglib.dx.c.b.n(d);
        int i = 0;
        for (int i2 = 0; i2 < a; i2++) {
            m b2 = nVar.b(i2);
            nVar2.a(i, b2);
            if (b2.i() == 2) {
                nVar2.a(i + 1, m.a(b2.e() + 1, c.i));
                i += 2;
            } else {
                i++;
            }
        }
        nVar2.e();
        return nVar2;
    }

    public int a() {
        return 3;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n e = e(hVar.i());
        return a(e) + ", " + f(hVar);
    }

    public void a(a aVar, h hVar) {
        int c = ((f) hVar).c();
        com.android.cglib.dx.c.b.n e = e(hVar.i());
        int a = e.a();
        int i = 0;
        int e2 = a > 0 ? e.b(0).e() : 0;
        int e3 = a > 1 ? e.b(1).e() : 0;
        int e4 = a > 2 ? e.b(2).e() : 0;
        int e5 = a > 3 ? e.b(3).e() : 0;
        if (a > 4) {
            i = e.b(4).e();
        }
        a(aVar, a(hVar, b(i, a)), (short) c, a(e2, e3, e4, e5));
    }

    public String b(h hVar, boolean z) {
        return z ? g(hVar) : "";
    }

    public boolean b(h hVar) {
        if (!(hVar instanceof f)) {
            return false;
        }
        f fVar = (f) hVar;
        if (!f(fVar.c())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = fVar.b();
        return ((b2 instanceof s) || (b2 instanceof w)) && d(fVar.i()) >= 0;
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        int a = i.a();
        BitSet bitSet = new BitSet(a);
        for (int i2 = 0; i2 < a; i2++) {
            m b2 = i.b(i2);
            bitSet.set(i2, b((b2.e() + b2.i()) - 1));
        }
        return bitSet;
    }
}
