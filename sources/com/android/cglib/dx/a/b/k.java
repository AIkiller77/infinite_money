package com.android.cglib.dx.a.b;

import com.android.cglib.dx.a.a.g;
import com.android.cglib.dx.a.a.i;
import com.android.cglib.dx.a.a.p;
import com.android.cglib.dx.a.a.u;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.d.a;
import java.io.PrintWriter;

public class k extends ag {
    private final g a;
    private byte[] b;
    private final boolean c;
    private final s d;

    public k(g gVar, boolean z, s sVar) {
        super(1, -1);
        if (gVar == null) {
            throw new NullPointerException("code == null");
        }
        this.a = gVar;
        this.c = z;
        this.d = sVar;
    }

    private byte[] a(l lVar, String str, PrintWriter printWriter, a aVar, boolean z) {
        return b(lVar, str, printWriter, aVar, z);
    }

    private byte[] b(l lVar, String str, PrintWriter printWriter, a aVar, boolean z) {
        u h = this.a.h();
        p i = this.a.i();
        i f = this.a.f();
        j jVar = new j(h, i, lVar, f.b(), f.c(), this.c, this.d);
        return (printWriter == null && aVar == null) ? jVar.a() : jVar.a(str, printWriter, aVar, z);
    }

    public y a() {
        return y.TYPE_DEBUG_INFO_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        try {
            this.b = a(akVar.e(), (String) null, (PrintWriter) null, (a) null, false);
            a(this.b.length);
        } catch (RuntimeException e) {
            throw com.android.cglib.dx.d.g.a(e, "...while placing debug info for " + this.d.a_());
        }
    }

    public void a(l lVar) {
    }

    public void a(l lVar, a aVar, String str) {
        a(lVar, str, (PrintWriter) null, aVar, false);
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        if (aVar.a()) {
            aVar.a(g() + " debug info");
            a(lVar, (String) null, (PrintWriter) null, aVar, true);
        }
        aVar.a(this.b);
    }

    public String b() {
        throw new RuntimeException("unsupported");
    }
}
