package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.z;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class n extends com.android.cglib.dx.a.a.n {
    public static final com.android.cglib.dx.a.a.n b = new n();

    private n() {
    }

    public int a() {
        return 2;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + i.b(1).k() + ", " + d(hVar);
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, a(hVar, b(i.b(0).e(), i.b(1).e())), (short) ((z) hVar).d());
    }

    public boolean a(z zVar) {
        int d = zVar.d();
        return d != 0 && e(d);
    }

    public String b(h hVar, boolean z) {
        return e(hVar);
    }

    public boolean b(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        if (!(hVar instanceof z) || i.a() != 2 || !b(i.b(0).e()) || !b(i.b(1).e())) {
            return false;
        }
        z zVar = (z) hVar;
        if (zVar.o()) {
            return a(zVar);
        }
        return true;
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, b(i.b(0).e()));
        bitSet.set(1, b(i.b(1).e()));
        return bitSet;
    }
}
