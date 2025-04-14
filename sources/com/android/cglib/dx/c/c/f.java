package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class f extends n {
    public static final f a = a((byte) 0);

    private f(byte b) {
        super(b);
    }

    public static f a(byte b) {
        return new f(b);
    }

    public String a_() {
        return Integer.toString(g());
    }

    public c b() {
        return c.b;
    }

    public String e() {
        return "byte";
    }

    public String toString() {
        int g = g();
        return "byte{0x" + i.e(g) + " / " + g + '}';
    }
}
