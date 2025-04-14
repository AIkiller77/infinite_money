package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class n extends o implements Comparable<n> {
    private final j a;

    public n(j jVar, int i) {
        super(i);
        if (jVar == null) {
            throw new NullPointerException("field == null");
        }
        this.a = jVar;
    }

    public int a(l lVar, a aVar, int i, int i2) {
        int b = lVar.l().b(this.a);
        int i3 = b - i;
        int c = c();
        if (aVar.a()) {
            aVar.a(0, String.format("  [%x] %s", new Object[]{Integer.valueOf(i2), this.a.a_()}));
            int a2 = com.android.cglib.dx.d.n.a(i3);
            aVar.a(a2, "    field_idx:    " + i.a(b));
            int a3 = com.android.cglib.dx.d.n.a(c);
            aVar.a(a3, "    access_flags: " + com.android.cglib.dx.c.b.a.b(c));
        }
        aVar.e(i3);
        aVar.e(c);
        return b;
    }

    /* renamed from: a */
    public int compareTo(n nVar) {
        return this.a.compareTo(nVar.a);
    }

    public void a(l lVar) {
        lVar.l().a(this.a);
    }

    public String a_() {
        return this.a.a_();
    }

    public j b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof n) && compareTo((n) obj) == 0;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(getClass().getName());
        stringBuffer.append('{');
        stringBuffer.append(i.c(c()));
        stringBuffer.append(' ');
        stringBuffer.append(this.a);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
