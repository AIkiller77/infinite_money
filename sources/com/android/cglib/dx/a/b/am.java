package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.c;
import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.n;

public final class am extends ag {
    private final v a;

    public am(v vVar) {
        super(1, a(vVar));
        this.a = vVar;
    }

    private static int a(v vVar) {
        return n.a(vVar.j()) + vVar.i() + 1;
    }

    /* access modifiers changed from: protected */
    public int a(ag agVar) {
        return this.a.compareTo(((am) agVar).a);
    }

    public y a() {
        return y.TYPE_STRING_DATA_ITEM;
    }

    public void a(l lVar) {
    }

    public void a_(l lVar, a aVar) {
        c h = this.a.h();
        int j = this.a.j();
        if (aVar.a()) {
            int a2 = n.a(j);
            aVar.a(a2, "utf16_size: " + i.a(j));
            aVar.a(h.a() + 1, this.a.f());
        }
        aVar.e(j);
        aVar.a(h);
        aVar.b(0);
    }

    public String b() {
        return this.a.f();
    }
}
