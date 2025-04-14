package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class x extends n {
    public static final n b = new x();

    private x() {
    }

    public int a() {
        return 4;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        return i.b(0).k() + ", " + f(hVar);
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        a(aVar, h(hVar), ((f) hVar).c(), (short) i.b(0).e());
    }

    public String b(h hVar, boolean z) {
        return z ? g(hVar) : "";
    }

    public boolean b(h hVar) {
        m mVar;
        if (!a || !(hVar instanceof f)) {
            return false;
        }
        com.android.cglib.dx.c.b.n i = hVar.i();
        switch (i.a()) {
            case 1:
                mVar = i.b(0);
                break;
            case 2:
                mVar = i.b(0);
                if (mVar.e() != i.b(1).e()) {
                    return false;
                }
                break;
            default:
                return false;
        }
        if (!f(mVar.e())) {
            return false;
        }
        com.android.cglib.dx.c.c.a b2 = ((f) hVar).b();
        return (b2 instanceof w) || (b2 instanceof j);
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        int a = i.a();
        BitSet bitSet = new BitSet(a);
        boolean d = d(i.b(0).e());
        if (a == 1) {
            bitSet.set(0, d);
            return bitSet;
        }
        if (i.b(0).e() == i.b(1).e()) {
            bitSet.set(0, d);
            bitSet.set(1, d);
        }
        return bitSet;
    }
}
