package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.d.a;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class ai extends w {
    private final a a;
    private final v b;
    private ar c;

    public ai(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("prototype == null");
        }
        this.a = aVar;
        this.b = a(aVar);
        b c2 = aVar.c();
        this.c = c2.a() == 0 ? null : new ar(c2);
    }

    private static char a(c cVar) {
        char charAt = cVar.e().charAt(0);
        if (charAt == '[') {
            return 'L';
        }
        return charAt;
    }

    private static v a(a aVar) {
        b c2 = aVar.c();
        int a2 = c2.a();
        StringBuilder sb = new StringBuilder(a2 + 1);
        sb.append(a(aVar.b()));
        for (int i = 0; i < a2; i++) {
            sb.append(a(c2.a(i)));
        }
        return new v(sb.toString());
    }

    public y a() {
        return y.TYPE_PROTO_ID_ITEM;
    }

    public void a(l lVar) {
        ao g = lVar.g();
        aq j = lVar.j();
        af e = lVar.e();
        j.a(this.a.b());
        g.a(this.b);
        if (this.c != null) {
            this.c = (ar) e.b(this.c);
        }
    }

    public void a(l lVar, com.android.cglib.dx.d.a aVar) {
        int b2 = lVar.g().b(this.b);
        int b3 = lVar.j().b(this.a.b());
        int b4 = ag.b(this.c);
        if (aVar.a()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a.b().a_());
            sb.append(" proto(");
            b c2 = this.a.c();
            int a2 = c2.a();
            for (int i = 0; i < a2; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(c2.a(i).a_());
            }
            sb.append(")");
            aVar.a(0, h() + ' ' + sb.toString());
            aVar.a(4, "  shorty_idx:      " + i.a(b2) + " // " + this.b.f());
            aVar.a(4, "  return_type_idx: " + i.a(b3) + " // " + this.a.b().a_());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  parameters_off:  ");
            sb2.append(i.a(b4));
            aVar.a(4, sb2.toString());
        }
        aVar.d(b2);
        aVar.d(b3);
        aVar.d(b4);
    }

    public int b_() {
        return 12;
    }
}
