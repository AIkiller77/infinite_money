package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.c;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class g extends w {
    private final w a;
    private final int b;
    private final w c;
    private ar d;
    private final v e;
    private final f f;
    private m g;
    private d h;

    public g(w wVar, int i, w wVar2, e eVar, v vVar) {
        if (wVar == null) {
            throw new NullPointerException("thisClass == null");
        } else if (eVar == null) {
            throw new NullPointerException("interfaces == null");
        } else {
            this.a = wVar;
            this.b = i;
            this.c = wVar2;
            this.d = eVar.a() == 0 ? null : new ar(eVar);
            this.e = vVar;
            this.f = new f(wVar);
            this.g = null;
            this.h = new d();
        }
    }

    public y a() {
        return y.TYPE_CLASS_DEF_ITEM;
    }

    public void a(l lVar) {
        aq j = lVar.j();
        af n = lVar.n();
        af d2 = lVar.d();
        af e2 = lVar.e();
        ao g2 = lVar.g();
        j.a(this.a);
        if (!this.f.c()) {
            lVar.i().a((ag) this.f);
            c d3 = this.f.d();
            if (d3 != null) {
                this.g = (m) n.b(new m(d3));
            }
        }
        if (this.c != null) {
            j.a(this.c);
        }
        if (this.d != null) {
            this.d = (ar) e2.b(this.d);
        }
        if (this.e != null) {
            g2.a(this.e);
        }
        if (this.h.c()) {
            return;
        }
        if (this.h.d()) {
            this.h = (d) d2.b(this.h);
        } else {
            d2.a((ag) this.h);
        }
    }

    public void a(l lVar, a aVar) {
        boolean a2 = aVar.a();
        aq j = lVar.j();
        int b2 = j.b(this.a);
        int i = -1;
        int b3 = this.c == null ? -1 : j.b(this.c);
        int b4 = ag.b(this.d);
        int e2 = this.h.c() ? 0 : this.h.e();
        if (this.e != null) {
            i = lVar.g().b(this.e);
        }
        int e3 = this.f.c() ? 0 : this.f.e();
        int b5 = ag.b(this.g);
        if (a2) {
            aVar.a(0, h() + ' ' + this.a.a_());
            StringBuilder sb = new StringBuilder();
            sb.append("  class_idx:           ");
            sb.append(i.a(b2));
            aVar.a(4, sb.toString());
            aVar.a(4, "  access_flags:        " + com.android.cglib.dx.c.b.a.a(this.b));
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  superclass_idx:      ");
            sb2.append(i.a(b3));
            sb2.append(" // ");
            sb2.append(this.c == null ? "<none>" : this.c.a_());
            aVar.a(4, sb2.toString());
            aVar.a(4, "  interfaces_off:      " + i.a(b4));
            if (b4 != 0) {
                e c2 = this.d.c();
                int a3 = c2.a();
                for (int i2 = 0; i2 < a3; i2++) {
                    aVar.a(0, "    " + c2.a(i2).a_());
                }
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("  source_file_idx:     ");
            sb3.append(i.a(i));
            sb3.append(" // ");
            sb3.append(this.e == null ? "<none>" : this.e.a_());
            aVar.a(4, sb3.toString());
            aVar.a(4, "  annotations_off:     " + i.a(e2));
            aVar.a(4, "  class_data_off:      " + i.a(e3));
            aVar.a(4, "  static_values_off:   " + i.a(b5));
        }
        aVar.d(b2);
        aVar.d(this.b);
        aVar.d(b3);
        aVar.d(b4);
        aVar.d(i);
        aVar.d(e2);
        aVar.d(e3);
        aVar.d(b5);
    }

    public void a(n nVar) {
        this.f.a(nVar);
    }

    public void a(n nVar, com.android.cglib.dx.c.c.a aVar) {
        this.f.a(nVar, aVar);
    }

    public void a(p pVar) {
        this.f.a(pVar);
    }

    public void b(p pVar) {
        this.f.b(pVar);
    }

    public int b_() {
        return 32;
    }

    public w c() {
        return this.a;
    }

    public w d() {
        return this.c;
    }

    public e e() {
        return this.d == null ? b.a : this.d.c();
    }
}
