package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.d;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.r;

public final class ac implements r, Comparable<ac> {
    private final s a;
    private b b;

    /* renamed from: a */
    public int compareTo(ac acVar) {
        return this.a.compareTo(acVar.a);
    }

    public void a(l lVar) {
        ae m = lVar.m();
        af d = lVar.d();
        m.a((d) this.a);
        this.b = (b) d.b(this.b);
    }

    public void a(l lVar, a aVar) {
        int b2 = lVar.m().b(this.a);
        int e = this.b.e();
        if (aVar.a()) {
            aVar.a(0, "    " + this.a.a_());
            aVar.a(4, "      method_idx:      " + i.a(b2));
            aVar.a(4, "      annotations_off: " + i.a(e));
        }
        aVar.d(b2);
        aVar.d(e);
    }

    public String a_() {
        return this.a.a_() + ": " + this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ac)) {
            return false;
        }
        return this.a.equals(((ac) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
