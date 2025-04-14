package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.a.a;

public final class b extends a {
    private final a a;

    public String a_() {
        return this.a.toString();
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        return this.a.compareTo(((b) aVar).a);
    }

    public a b() {
        return this.a;
    }

    public String e() {
        return "annotation";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        return this.a.equals(((b) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }
}
