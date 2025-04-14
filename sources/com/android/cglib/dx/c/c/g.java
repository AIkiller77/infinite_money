package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class g extends n {
    public static final g a = a(0);

    private g(char c) {
        super(c);
    }

    public static g a(char c) {
        return new g(c);
    }

    public String a_() {
        return Integer.toString(g());
    }

    public c b() {
        return c.c;
    }

    public String e() {
        return "char";
    }

    public String toString() {
        int g = g();
        return "char{0x" + i.c(g) + " / " + g + '}';
    }
}
