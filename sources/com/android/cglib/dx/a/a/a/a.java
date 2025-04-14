package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.a.a.z;

public final class a extends n {
    public static final n b = new a();

    private a() {
    }

    public int a() {
        return 1;
    }

    public String a(h hVar) {
        return d(hVar);
    }

    public void a(com.android.cglib.dx.d.a aVar, h hVar) {
        a(aVar, a(hVar, ((z) hVar).d() & 255));
    }

    public boolean a(z zVar) {
        int d = zVar.d();
        return d != 0 && c(d);
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
