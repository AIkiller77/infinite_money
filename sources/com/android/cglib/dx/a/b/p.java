package com.android.cglib.dx.a.b;

import com.android.cglib.dx.a.a.g;
import com.android.cglib.dx.c.c.d;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.n;

public final class p extends o implements Comparable<p> {
    private final s a;
    private final i b;

    public p(s sVar, int i, g gVar, e eVar) {
        super(i);
        if (sVar == null) {
            throw new NullPointerException("method == null");
        }
        this.a = sVar;
        if (gVar == null) {
            this.b = null;
        } else {
            this.b = new i(sVar, gVar, (i & 8) != 0, eVar);
        }
    }

    public int a(l lVar, a aVar, int i, int i2) {
        int b2 = lVar.m().b(this.a);
        int i3 = b2 - i;
        int c = c();
        int b3 = ag.b(this.b);
        if ((b3 != 0) != ((c & 1280) == 0)) {
            throw new UnsupportedOperationException("code vs. access_flags mismatch");
        }
        if (aVar.a()) {
            aVar.a(0, String.format("  [%x] %s", new Object[]{Integer.valueOf(i2), this.a.a_()}));
            int a2 = n.a(i3);
            aVar.a(a2, "    method_idx:   " + i.a(b2));
            int a3 = n.a(c);
            aVar.a(a3, "    access_flags: " + com.android.cglib.dx.c.b.a.c(c));
            int a4 = n.a(b3);
            aVar.a(a4, "    code_off:     " + i.a(b3));
        }
        aVar.e(i3);
        aVar.e(c);
        aVar.e(b3);
        return b2;
    }

    /* renamed from: a */
    public int compareTo(p pVar) {
        return this.a.compareTo(pVar.a);
    }

    public void a(l lVar) {
        ae m = lVar.m();
        af d = lVar.d();
        m.a((d) this.a);
        if (this.b != null) {
            d.a((ag) this.b);
        }
    }

    public final String a_() {
        return this.a.a_();
    }

    public boolean equals(Object obj) {
        return (obj instanceof p) && compareTo((p) obj) == 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(getClass().getName());
        stringBuffer.append('{');
        stringBuffer.append(i.c(c()));
        stringBuffer.append(' ');
        stringBuffer.append(this.a);
        if (this.b != null) {
            stringBuffer.append(' ');
            stringBuffer.append(this.b);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
