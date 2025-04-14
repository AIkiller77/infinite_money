package com.android.cglib.dx.a.b;

import com.android.cglib.dx.a.a.g;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.a;
import java.util.Iterator;

public final class i extends ag {
    private final s a;
    private final g b;
    private e c;
    private final boolean d;
    private final e e;
    private k f;

    public i(s sVar, g gVar, boolean z, e eVar) {
        super(4, -1);
        if (sVar == null) {
            throw new NullPointerException("ref == null");
        } else if (gVar == null) {
            throw new NullPointerException("code == null");
        } else if (eVar == null) {
            throw new NullPointerException("throwsList == null");
        } else {
            this.a = sVar;
            this.b = gVar;
            this.d = z;
            this.e = eVar;
            this.c = null;
            this.f = null;
        }
    }

    private void b(l lVar, a aVar) {
        try {
            this.b.f().a(aVar);
        } catch (RuntimeException e2) {
            throw com.android.cglib.dx.d.g.a(e2, "...while writing instructions for " + this.a.a_());
        }
    }

    private int c() {
        return this.a.b(this.d);
    }

    private int d() {
        return this.b.f().d();
    }

    private int h() {
        return this.b.f().c();
    }

    public y a() {
        return y.TYPE_CODE_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        int i2;
        final l e2 = akVar.e();
        this.b.a(new g.a() {
            public int a(com.android.cglib.dx.c.c.a aVar) {
                w b2 = e2.b(aVar);
                if (b2 == null) {
                    return -1;
                }
                return b2.g();
            }
        });
        if (this.c != null) {
            this.c.a(e2);
            i2 = this.c.b();
        } else {
            i2 = 0;
        }
        int b2 = this.b.f().b();
        if ((b2 & 1) != 0) {
            b2++;
        }
        a((b2 * 2) + 16 + i2);
    }

    public void a(l lVar) {
        af n = lVar.n();
        aq j = lVar.j();
        if (this.b.a() || this.b.b()) {
            this.f = new k(this.b, this.d, this.a);
            n.a((ag) this.f);
        }
        if (this.b.c()) {
            Iterator<c> it = this.b.d().iterator();
            while (it.hasNext()) {
                j.a(it.next());
            }
            this.c = new e(this.b);
        }
        Iterator<com.android.cglib.dx.c.c.a> it2 = this.b.e().iterator();
        while (it2.hasNext()) {
            lVar.a(it2.next());
        }
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        boolean a2 = aVar.a();
        int h = h();
        int d2 = d();
        int c2 = c();
        int b2 = this.b.f().b();
        boolean z = (b2 & 1) != 0;
        int a3 = this.c == null ? 0 : this.c.a();
        int e2 = this.f == null ? 0 : this.f.e();
        if (a2) {
            aVar.a(0, g() + ' ' + this.a.a_());
            StringBuilder sb = new StringBuilder();
            sb.append("  registers_size: ");
            sb.append(com.android.cglib.dx.d.i.c(h));
            aVar.a(2, sb.toString());
            aVar.a(2, "  ins_size:       " + com.android.cglib.dx.d.i.c(c2));
            aVar.a(2, "  outs_size:      " + com.android.cglib.dx.d.i.c(d2));
            aVar.a(2, "  tries_size:     " + com.android.cglib.dx.d.i.c(a3));
            aVar.a(4, "  debug_off:      " + com.android.cglib.dx.d.i.a(e2));
            aVar.a(4, "  insns_size:     " + com.android.cglib.dx.d.i.a(b2));
            if (this.e.a() != 0) {
                aVar.a(0, "  throws " + b.a(this.e));
            }
        }
        aVar.c(h);
        aVar.c(c2);
        aVar.c(d2);
        aVar.c(a3);
        aVar.d(e2);
        aVar.d(b2);
        b(lVar, aVar);
        if (this.c != null) {
            if (z) {
                if (a2) {
                    aVar.a(2, "  padding: 0");
                }
                aVar.c(0);
            }
            this.c.a(lVar, aVar);
        }
        if (a2 && this.f != null) {
            aVar.a(0, "  debug info");
            this.f.a(lVar, aVar, "    ");
        }
    }

    public String b() {
        return this.a.a_();
    }

    public String toString() {
        return "CodeItem{" + b() + "}";
    }
}
