package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class k extends n {
    public static final k a = a(Float.floatToIntBits(0.0f));
    public static final k b = a(Float.floatToIntBits(1.0f));
    public static final k c = a(Float.floatToIntBits(2.0f));

    private k(int i) {
        super(i);
    }

    public static k a(int i) {
        return new k(i);
    }

    public String a_() {
        return Float.toString(Float.intBitsToFloat(g()));
    }

    public c b() {
        return c.e;
    }

    public String e() {
        return "float";
    }

    public String toString() {
        int g = g();
        return "float{0x" + i.a(g) + " / " + Float.intBitsToFloat(g) + '}';
    }
}
