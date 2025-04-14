package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class z extends n {
    public static final n b = new z();

    private z() {
    }

    public int a() {
        return 5;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + i.b(1).k() + ", " + f(hVar);
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, h(hVar), ((f) hVar).c(), (short) i.b(0).e(), (short) i.b(1).e());
    }

    public String b(h hVar, boolean z) {
        return z ? g(hVar) : "";
    }

    public boolean b(h hVar) {
        if (!a) {
            return false;
        }
        com.android.cglib.dx.c.b.n i = hVar.i();
        if (!(hVar instanceof f) || i.a() != 2 || !f(i.b(0).e()) || !f(i.b(1).e())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = ((f) hVar).b();
        return (b2 instanceof w) || (b2 instanceof j);
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, f(i.b(0).e()));
        bitSet.set(1, f(i.b(1).e()));
        return bitSet;
    }
}
