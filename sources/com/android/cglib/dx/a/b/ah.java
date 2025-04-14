package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.d;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.r;

public final class ah implements r, Comparable<ah> {
    private final s a;
    private final at<c> b;

    /* renamed from: a */
    public int compareTo(ah ahVar) {
        return this.a.compareTo(ahVar.a);
    }

    public void a(l lVar) {
        ae m = lVar.m();
        af d = lVar.d();
        m.a((d) this.a);
        d.a((ag) this.b);
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.a.a_());
        sb.append(": ");
        boolean z = true;
        for (c next : this.b.c()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(next.b());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ah)) {
            return false;
        }
        return this.a.equals(((ah) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
