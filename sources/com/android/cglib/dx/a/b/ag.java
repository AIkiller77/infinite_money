package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.g;

public abstract class ag extends x implements Comparable<ag> {
    private final int a;
    private int b;
    private ak c;
    private int d;

    public ag(int i, int i2) {
        ak.a(i);
        if (i2 < -1) {
            throw new IllegalArgumentException("writeSize < -1");
        }
        this.a = i;
        this.b = i2;
        this.c = null;
        this.d = -1;
    }

    public static int b(ag agVar) {
        if (agVar == null) {
            return 0;
        }
        return agVar.e();
    }

    /* access modifiers changed from: protected */
    public int a(ag agVar) {
        throw new UnsupportedOperationException("unsupported");
    }

    public final void a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("writeSize < 0");
        } else if (this.b >= 0) {
            throw new UnsupportedOperationException("writeSize already set");
        } else {
            this.b = i;
        }
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
    }

    public final void a(l lVar, a aVar) {
        aVar.h(this.a);
        try {
            if (this.b < 0) {
                throw new UnsupportedOperationException("writeSize is unknown");
            }
            aVar.a(e());
            a_(lVar, aVar);
        } catch (RuntimeException e) {
            throw g.a(e, "...while writing " + this);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a_(l lVar, a aVar);

    public final int b(ak akVar, int i) {
        if (akVar == null) {
            throw new NullPointerException("addedTo == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("offset < 0");
        } else if (this.c != null) {
            throw new RuntimeException("already written");
        } else {
            int i2 = this.a - 1;
            int i3 = (i + i2) & (i2 ^ -1);
            this.c = akVar;
            this.d = i3;
            a(akVar, i3);
            return i3;
        }
    }

    public abstract String b();

    public final int b_() {
        if (this.b >= 0) {
            return this.b;
        }
        throw new UnsupportedOperationException("writeSize is unknown");
    }

    /* renamed from: c */
    public final int compareTo(ag agVar) {
        if (this == agVar) {
            return 0;
        }
        y a2 = a();
        y a3 = agVar.a();
        return a2 != a3 ? a2.compareTo(a3) : a(agVar);
    }

    public final int e() {
        if (this.d >= 0) {
            return this.c.c(this.d);
        }
        throw new RuntimeException("offset not yet known");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        ag agVar = (ag) obj;
        return a() == agVar.a() && a(agVar) == 0;
    }

    public final int f() {
        return this.a;
    }

    public final String g() {
        return '[' + Integer.toHexString(e()) + ']';
    }
}
