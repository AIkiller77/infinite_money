package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.a.a.z;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class j extends n {
    public static final n b = new j();

    private j() {
    }

    public int a() {
        return 2;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + d(hVar);
    }

    public void a(a aVar, h hVar) {
        a(aVar, a(hVar, hVar.i().b(0).e()), (short) ((z) hVar).d());
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
        if (!(hVar instanceof z) || i.a() != 1 || !d(i.b(0).e())) {
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
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, d(i.b(0).e()));
        return bitSet;
    }
}
