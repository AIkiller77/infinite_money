package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.c;
import com.android.cglib.dx.d.d;

public final class m extends ag {
    private final c a;
    private byte[] b;

    public m(c cVar) {
        super(1, -1);
        if (cVar == null) {
            throw new NullPointerException("array == null");
        }
        this.a = cVar;
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public int a(ag agVar) {
        return this.a.compareTo(((m) agVar).a);
    }

    public y a() {
        return y.TYPE_ENCODED_ARRAY_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        d dVar = new d();
        new au(akVar.e(), dVar).a(this.a, false);
        this.b = dVar.f();
        a(this.b.length);
    }

    public void a(l lVar) {
        au.a(lVar, (a) this.a);
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, com.android.cglib.dx.d.a aVar) {
        if (aVar.a()) {
            aVar.a(0, g() + " encoded array");
            new au(lVar, aVar).a(this.a, true);
            return;
        }
        aVar.a(this.b);
    }

    public String b() {
        return this.a.a_();
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
