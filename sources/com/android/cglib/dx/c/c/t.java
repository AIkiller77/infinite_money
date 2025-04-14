package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;

public final class t extends a {
    public static final t a = new t(new v("TYPE"), new v("Ljava/lang/Class;"));
    private final v b;
    private final v c;

    public t(v vVar, v vVar2) {
        if (vVar == null) {
            throw new NullPointerException("name == null");
        } else if (vVar2 == null) {
            throw new NullPointerException("descriptor == null");
        } else {
            this.b = vVar;
            this.c = vVar2;
        }
    }

    public String a_() {
        return this.b.a_() + ':' + this.c.a_();
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        t tVar = (t) aVar;
        int a2 = this.b.compareTo(tVar.b);
        return a2 != 0 ? a2 : this.c.compareTo(tVar.c);
    }

    public v b() {
        return this.b;
    }

    public v c() {
        return this.c;
    }

    public c d() {
        return c.a(this.c.g());
    }

    public String e() {
        return "nat";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return this.b.equals(tVar.b) && this.c.equals(tVar.c);
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) ^ this.c.hashCode();
    }

    public String toString() {
        return "nat{" + a_() + '}';
    }
}
