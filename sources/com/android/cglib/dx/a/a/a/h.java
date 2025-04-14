package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.p;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class h extends n {
    public static final n b = new h();

    private h() {
    }

    public int a() {
        return 2;
    }

    public String a(com.android.cglib.dx.a.a.h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + a((p) ((f) hVar).b());
    }

    public void a(a aVar, com.android.cglib.dx.a.a.h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        p pVar = (p) ((f) hVar).b();
        a(aVar, a(hVar, i.b(0).e()), (short) (i.b(0).i() == 1 ? pVar.g() >>> 16 : (int) (pVar.h() >>> 48)));
    }

    public String b(com.android.cglib.dx.a.a.h hVar, boolean z) {
        return a((p) ((f) hVar).b(), hVar.i().b(0).i() == 1 ? 32 : 64);
    }

    public boolean b(com.android.cglib.dx.a.a.h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        if (!(hVar instanceof f) || i.a() != 1 || !d(i.b(0).e())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = ((f) hVar).b();
        if (!(b2 instanceof p)) {
            return false;
        }
        p pVar = (p) b2;
        return i.b(0).i() == 1 ? (pVar.g() & 65535) == 0 : (pVar.h() & 281474976710655L) == 0;
    }

    public BitSet c(com.android.cglib.dx.a.a.h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, d(i.b(0).e()));
        return bitSet;
    }
}
