package com.android.cglib.dx.c.c;

import com.android.cglib.dx.d.h;

public final class c extends a {
    private final a a;

    public static final class a extends h implements Comparable<a> {
        public a(int i) {
            super(i);
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            int a = a();
            int a2 = aVar.a();
            int i = a < a2 ? a : a2;
            for (int i2 = 0; i2 < i; i2++) {
                int a3 = ((a) d(i2)).compareTo((a) aVar.d(i2));
                if (a3 != 0) {
                    return a3;
                }
            }
            if (a < a2) {
                return -1;
            }
            return a > a2 ? 1 : 0;
        }

        public a a(int i) {
            return (a) d(i);
        }

        public void a(int i, a aVar) {
            a(i, aVar);
        }
    }

    public c(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("list == null");
        }
        aVar.i();
        this.a = aVar;
    }

    public String a_() {
        return this.a.b("{", ", ", "}");
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        return this.a.compareTo(((c) aVar).a);
    }

    public a b() {
        return this.a;
    }

    public String e() {
        return "array";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        return this.a.equals(((c) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.a("array{", ", ", "}");
    }
}
