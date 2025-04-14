package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.a.a.x;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class o extends n {
    public static final n b = new o();

    private o() {
    }

    public int a() {
        return 2;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + i.b(1).k();
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, a(hVar, i.b(0).e()), (short) i.b(1).e());
    }

    public String b(h hVar, boolean z) {
        return "";
    }

    public boolean b(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return (hVar instanceof x) && i.a() == 2 && d(i.b(0).e()) && f(i.b(1).e());
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, d(i.b(0).e()));
        bitSet.set(1, f(i.b(1).e()));
        return bitSet;
    }
}
