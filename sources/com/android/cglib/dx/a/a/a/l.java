package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class l extends n {
    public static final n b = new l();

    private l() {
    }

    public int a() {
        return 2;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + i.b(1).k() + ", " + f(hVar);
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, a(hVar, b(i.b(0).e(), i.b(1).e())), (short) ((f) hVar).c());
    }

    public String b(h hVar, boolean z) {
        return z ? g(hVar) : "";
    }

    public boolean b(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        if (!(hVar instanceof f) || i.a() != 2 || !b(i.b(0).e()) || !b(i.b(1).e())) {
            return false;
        }
        f fVar = (f) hVar;
        if (!f(fVar.c())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = fVar.b();
        return (b2 instanceof w) || (b2 instanceof j);
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, b(i.b(0).e()));
        bitSet.set(1, b(i.b(1).e()));
        return bitSet;
    }
}
