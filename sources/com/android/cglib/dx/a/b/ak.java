package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.a;
import java.util.Collection;

public abstract class ak {
    private final String a;
    private final l b;
    private final int c;
    private int d;
    private boolean e;

    public ak(String str, l lVar, int i) {
        if (lVar == null) {
            throw new NullPointerException("file == null");
        }
        a(i);
        this.a = str;
        this.b = lVar;
        this.c = i;
        this.d = -1;
        this.e = false;
    }

    public static void a(int i) {
        if (i <= 0 || (i & (i - 1)) != 0) {
            throw new IllegalArgumentException("invalid alignment");
        }
    }

    public abstract int a(x xVar);

    public abstract Collection<? extends x> a();

    /* access modifiers changed from: protected */
    public abstract void a_(a aVar);

    public final int b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("fileOffset < 0");
        } else if (this.d >= 0) {
            throw new RuntimeException("fileOffset already set");
        } else {
            int i2 = this.c - 1;
            int i3 = (i + i2) & (i2 ^ -1);
            this.d = i3;
            return i3;
        }
    }

    public final int c(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("relative < 0");
        } else if (this.d >= 0) {
            return this.d + i;
        } else {
            throw new RuntimeException("fileOffset not yet set");
        }
    }

    /* access modifiers changed from: protected */
    public abstract void c();

    public final void c(a aVar) {
        String str;
        i();
        d(aVar);
        int g = aVar.g();
        if (this.d < 0) {
            this.d = g;
        } else if (this.d != g) {
            throw new RuntimeException("alignment mismatch: for " + this + ", at " + g + ", but expected " + this.d);
        }
        if (aVar.a()) {
            if (this.a != null) {
                str = "\n" + this.a + ":";
            } else if (g != 0) {
                str = "\n";
            }
            aVar.a(0, str);
        }
        a_(aVar);
    }

    public abstract int c_();

    /* access modifiers changed from: protected */
    public final void d(a aVar) {
        aVar.h(this.c);
    }

    public final l e() {
        return this.b;
    }

    public final int f() {
        return this.c;
    }

    public final int g() {
        if (this.d >= 0) {
            return this.d;
        }
        throw new RuntimeException("fileOffset not set");
    }

    public final void h() {
        j();
        c();
        this.e = true;
    }

    /* access modifiers changed from: protected */
    public final void i() {
        if (!this.e) {
            throw new RuntimeException("not prepared");
        }
    }

    /* access modifiers changed from: protected */
    public final void j() {
        if (this.e) {
            throw new RuntimeException("already prepared");
        }
    }
}
