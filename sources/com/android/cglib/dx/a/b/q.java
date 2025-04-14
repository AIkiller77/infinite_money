package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.r;

public final class q implements r, Comparable<q> {
    private final j a;
    private b b;

    /* renamed from: a */
    public int compareTo(q qVar) {
        return this.a.compareTo(qVar.a);
    }

    public void a(l lVar) {
        s l = lVar.l();
        af d = lVar.d();
        l.a(this.a);
        this.b = (b) d.b(this.b);
    }

    public void a(l lVar, a aVar) {
        int b2 = lVar.l().b(this.a);
        int e = this.b.e();
        if (aVar.a()) {
            aVar.a(0, "    " + this.a.a_());
            aVar.a(4, "      field_idx:       " + i.a(b2));
            aVar.a(4, "      annotations_off: " + i.a(e));
        }
        aVar.d(b2);
        aVar.d(e);
    }

    public String a_() {
        return this.a.a_() + ": " + this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        return this.a.equals(((q) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
