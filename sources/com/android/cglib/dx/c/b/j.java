package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.e;

public final class j extends d {
    public j(p pVar, s sVar, m mVar, n nVar, a aVar) {
        super(pVar, sVar, mVar, nVar, aVar);
        if (pVar.b() != 1) {
            throw new IllegalArgumentException("bogus branchingness");
        }
    }

    public void a(f.b bVar) {
        bVar.a(this);
    }

    public e i() {
        return b.a;
    }
}
