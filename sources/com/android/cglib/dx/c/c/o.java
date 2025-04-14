package com.android.cglib.dx.c.c;

public abstract class o extends p {
    private final long a;

    o(long j) {
        this.a = j;
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        long j = ((o) aVar).a;
        if (this.a < j) {
            return -1;
        }
        return this.a > j ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass() && this.a == ((o) obj).a;
    }

    public final boolean f() {
        return ((long) ((int) this.a)) == this.a;
    }

    public final int g() {
        return (int) this.a;
    }

    public final long h() {
        return this.a;
    }

    public final int hashCode() {
        return ((int) this.a) ^ ((int) (this.a >> 32));
    }
}
