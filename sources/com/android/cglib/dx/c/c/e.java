package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;

public final class e extends n {
    public static final e a = new e(false);
    public static final e b = new e(true);

    private e(boolean z) {
        super(z ? 1 : 0);
    }

    public static e a(boolean z) {
        return z ? b : a;
    }

    public String a_() {
        return d_() ? "true" : "false";
    }

    public c b() {
        return c.a;
    }

    public boolean d_() {
        return g() != 0;
    }

    public String e() {
        return "boolean";
    }

    public String toString() {
        return d_() ? "boolean{true}" : "boolean{false}";
    }
}
