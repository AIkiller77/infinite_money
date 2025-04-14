package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.f;
import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;

public final class aa extends n {
    public static final n b = new aa();

    private aa() {
    }

    public int a() {
        return 5;
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
        a(aVar, h(hVar), c, (short) i.b(), (short) i2);
    }

    public String b(h hVar, boolean z) {
        return z ? g(hVar) : "";
    }

    public boolean b(h hVar) {
        if (!a || !(hVar instanceof f)) {
            return false;
        }
        f fVar = (f) hVar;
        com.android.cglib.dx.c.c.a b2 = fVar.b();
        if (!(b2 instanceof s) && !(b2 instanceof w)) {
            return false;
        }
        com.android.cglib.dx.c.b.n i = fVar.i();
        i.a();
        return i.a() == 0 || (c(i) && f(i.b(0).e()) && f(i.b()));
    }
}
