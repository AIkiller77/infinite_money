package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.d.a;

public final class w extends n {
    public static final n b = new w();

    private w() {
    }

    public int a() {
        return 3;
    }

    public String a(h hVar) {
        return b(hVar.i()) + ", " + f(hVar);
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        int c = ((f) hVar).c();
        int i2 = 0;
        if (i.a() != 0) {
            i2 = i.b(0).e();
        }
        a(aVar, a(hVar, i.b()), (short) c, (short) i2);
    }

    public String b(h hVar, boolean z) {
        return z ? g(hVar) : "";
    }

    public boolean b(h hVar) {
        if (!(hVar instanceof f)) {
            return false;
        }
        f fVar = (f) hVar;
        int c = fVar.c();
        com.android.cglib.dx.c.c.a b2 = fVar.b();
        if (!f(c)) {
            return false;
        }
        if (!(b2 instanceof s) && !(b2 instanceof com.android.cglib.dx.c.c.w)) {
            return false;
        }
        com.android.cglib.dx.c.b.n i = fVar.i();
        i.a();
        return i.a() == 0 || (c(i) && f(i.b(0).e()) && d(i.b()));
    }
}
