package com.android.cglib.dx.c.c;

public abstract class r extends x {
    private final w a;
    private final t b;

    r(w wVar, t tVar) {
        if (wVar == null) {
            throw new NullPointerException("definingClass == null");
        } else if (tVar == null) {
            throw new NullPointerException("nat == null");
        } else {
            this.a = wVar;
            this.b = tVar;
        }
    }

    public final String a_() {
        return this.a.a_() + '.' + this.b.a_();
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        r rVar = (r) aVar;
        int a2 = this.a.compareTo(rVar.a);
        return a2 != 0 ? a2 : this.b.b().compareTo(rVar.b.b());
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        return this.a.equals(rVar.a) && this.b.equals(rVar.b);
    }

    public final w g() {
        return this.a;
    }

    public final t h() {
        return this.b;
    }

    public final int hashCode() {
        return (this.a.hashCode() * 31) ^ this.b.hashCode();
    }

    public final String toString() {
        return e() + '{' + a_() + '}';
    }
}
