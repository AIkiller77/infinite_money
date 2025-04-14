package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.d.e;

public final class t extends d {
    private final e a;

    public t(p pVar, s sVar, n nVar, e eVar, a aVar) {
        super(pVar, sVar, (m) null, nVar, aVar);
        if (pVar.b() != 6) {
            throw new IllegalArgumentException("bogus branchingness");
        } else if (eVar == null) {
            throw new NullPointerException("catches == null");
        } else {
            this.a = eVar;
        }
    }

    public void a(f.b bVar) {
        bVar.a(this);
    }

    public String b() {
        a c = c();
        String a_ = c.a_();
        if (c instanceof v) {
            a_ = ((v) c).f();
        }
        return a_ + " " + u.a(this.a);
    }

    public e i() {
        return this.a;
    }
}
