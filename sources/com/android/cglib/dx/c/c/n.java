package com.android.cglib.dx.c.c;

public abstract class n extends p {
    private final int a;

    n(int i) {
        this.a = i;
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        int i = ((n) aVar).a;
        if (this.a < i) {
            return -1;
        }
        return this.a > i ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass() && this.a == ((n) obj).a;
    }

    public final boolean f() {
        return true;
    }

    public final int g() {
        return this.a;
    }

    public final long h() {
        return (long) this.a;
    }

    public final int hashCode() {
        return this.a;
    }
}
