package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.a.a.x;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class d extends n {
    public static final n b = new d();

    private d() {
    }

    public int a() {
        return 1;
    }

    public String a(h hVar) {
        return hVar.i().b(0).k();
    }

    public void a(a aVar, h hVar) {
        a(aVar, a(hVar, hVar.i().b(0).e()));
    }

    public String b(h hVar, boolean z) {
        return "";
    }

    public boolean b(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return (hVar instanceof x) && i.a() == 1 && d(i.b(0).e());
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, d(i.b(0).e()));
        return bitSet;
    }
}
