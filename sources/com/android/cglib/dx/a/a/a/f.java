package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.a.a.z;
import com.android.cglib.dx.d.a;

public final class f extends n {
    public static final n b = new f();

    private f() {
    }

    public int a() {
        return 2;
    }

    public String a(h hVar) {
        return d(hVar);
    }

    public void a(a aVar, h hVar) {
        a(aVar, a(hVar, 0), (short) ((z) hVar).d());
    }

    public boolean a(z zVar) {
        int d = zVar.d();
        return d != 0 && e(d);
    }

    public String b(h hVar, boolean z) {
        return e(hVar);
    }

    public boolean b(h hVar) {
        if (!(hVar instanceof z) || hVar.i().a() != 0) {
            return false;
        }
        z zVar = (z) hVar;
        if (zVar.o()) {
            return a(zVar);
        }
        return true;
    }
}
