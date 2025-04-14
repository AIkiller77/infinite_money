package com.android.cglib.dx.c.a;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.v;

public final class d implements Comparable<d> {
    private final v a;
    private final a b;

    /* renamed from: a */
    public int compareTo(d dVar) {
        int a2 = this.a.compareTo(dVar.a);
        return a2 != 0 ? a2 : this.b.compareTo(dVar.b);
    }

    public v a() {
        return this.a;
    }

    public a b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.a.equals(dVar.a) && this.b.equals(dVar.b);
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return this.a.a_() + ":" + this.b;
    }
}
