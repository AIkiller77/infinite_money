package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.p;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class k extends n {
    public static final n b = new k();

    private k() {
    }

    public int a() {
        return 2;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + i.b(1).k() + ", " + a((p) ((f) hVar).b());
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, a(hVar, i.b(0).e()), a(i.b(1).e(), ((p) ((f) hVar).b()).g() & 255));
    }

    public String b(h hVar, boolean z) {
        return a((p) ((f) hVar).b(), 8);
    }

    public boolean b(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        if (!(hVar instanceof f) || i.a() != 2 || !d(i.b(0).e()) || !d(i.b(1).e())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = ((f) hVar).b();
        if (!(b2 instanceof p)) {
            return false;
        }
        p pVar = (p) b2;
        return pVar.f() && c(pVar.g());
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, d(i.b(0).e()));
        bitSet.set(1, d(i.b(1).e()));
        return bitSet;
    }
}
