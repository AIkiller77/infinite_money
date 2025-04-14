package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.p;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class s extends n {
    public static final n b = new s();

    private s() {
    }

    public int a() {
        return 3;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + a((p) ((f) hVar).b());
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, a(hVar, i.b(0).e()), ((p) ((f) hVar).b()).g());
    }

    public String b(h hVar, boolean z) {
        return a((p) ((f) hVar).b(), 32);
    }

    public boolean b(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        if (!(hVar instanceof f) || i.a() != 1 || !d(i.b(0).e())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = ((f) hVar).b();
        if (!(b2 instanceof p)) {
            return false;
        }
        return ((p) b2).f();
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, d(i.b(0).e()));
        return bitSet;
    }
}
