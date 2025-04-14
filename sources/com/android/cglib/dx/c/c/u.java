package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class u extends n {
    public static final u a = a(0);

    private u(short s) {
        super(s);
    }

    public static u a(short s) {
        return new u(s);
    }

    public String a_() {
        return Integer.toString(g());
    }

    public c b() {
        return c.h;
    }

    public String e() {
        return "short";
    }

    public String toString() {
        int g = g();
        return "short{0x" + i.c(g) + " / " + g + '}';
    }
}
