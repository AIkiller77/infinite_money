package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.c.v;

public class h implements Comparable<h> {
    private final v a;
    private final v b;

    private static int a(v vVar, v vVar2) {
        if (vVar == vVar2) {
            return 0;
        }
        if (vVar == null) {
            return -1;
        }
        if (vVar2 == null) {
            return 1;
        }
        return vVar.compareTo(vVar2);
    }

    /* renamed from: a */
    public int compareTo(h hVar) {
        int a2 = a(this.a, hVar.a);
        return a2 != 0 ? a2 : a(this.b, hVar.b);
    }

    public v a() {
        return this.a;
    }

    public v b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        return (obj instanceof h) && compareTo((h) obj) == 0;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.a == null ? 0 : this.a.hashCode()) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        if (this.a != null && this.b == null) {
            return this.a.f();
        }
        if (this.a == null && this.b == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.a == null ? "" : this.a.f());
        sb.append("|");
        sb.append(this.b == null ? "" : this.b.f());
        return sb.toString();
    }
}
