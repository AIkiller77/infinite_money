package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class an extends w implements Comparable {
    private final v a;
    private am b;

    public an(v vVar) {
        if (vVar == null) {
            throw new NullPointerException("value == null");
        }
        this.a = vVar;
        this.b = null;
    }

    public y a() {
        return y.TYPE_STRING_ID_ITEM;
    }

    public void a(l lVar) {
        if (this.b == null) {
            af c = lVar.c();
            this.b = new am(this.a);
            c.a((ag) this.b);
        }
    }

    public void a(l lVar, a aVar) {
        int e = this.b.e();
        if (aVar.a()) {
            aVar.a(0, h() + ' ' + this.a.a(100));
            StringBuilder sb = new StringBuilder();
            sb.append("  string_data_off: ");
            sb.append(i.a(e));
            aVar.a(4, sb.toString());
        }
        aVar.d(e);
    }

    public int b_() {
        return 4;
    }

    public v c() {
        return this.a;
    }

    public int compareTo(Object obj) {
        return this.a.compareTo(((an) obj).a);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof an)) {
            return false;
        }
        return this.a.equals(((an) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
