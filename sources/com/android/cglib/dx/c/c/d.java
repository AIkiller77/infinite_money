package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.a;
import com.android.cglib.dx.c.d.c;

public abstract class d extends r {
    private final a a = a.a(h().c().g());
    private a b = null;

    d(w wVar, t tVar) {
        super(wVar, tVar);
    }

    public final a a(boolean z) {
        if (z) {
            return this.a;
        }
        if (this.b == null) {
            this.b = this.a.a(g().f());
        }
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final int b(a aVar) {
        int b2 = super.b(aVar);
        return b2 != 0 ? b2 : this.a.compareTo(((d) aVar).a);
    }

    public final int b(boolean z) {
        return a(z).c().b();
    }

    public final c b() {
        return this.a.b();
    }

    public final a f() {
        return this.a;
    }
}
