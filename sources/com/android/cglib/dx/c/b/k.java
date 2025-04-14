package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.e;

public final class k extends f {
    public k(p pVar, s sVar, m mVar, m mVar2) {
        this(pVar, sVar, mVar, n.a(mVar2));
    }

    public k(p pVar, s sVar, m mVar, n nVar) {
        super(pVar, sVar, mVar, nVar);
        switch (pVar.b()) {
            case 5:
            case 6:
                throw new IllegalArgumentException("bogus branchingness");
            default:
                if (mVar != null && pVar.b() != 1) {
                    throw new IllegalArgumentException("can't mix branchingness with result");
                }
                return;
        }
    }

    public void a(f.b bVar) {
        bVar.a(this);
    }

    public e i() {
        return b.a;
    }
}
