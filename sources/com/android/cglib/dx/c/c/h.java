package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class h extends o {
    public static final h a = new h(Double.doubleToLongBits(0.0d));
    public static final h b = new h(Double.doubleToLongBits(1.0d));

    private h(long j) {
        super(j);
    }

    public static h a(long j) {
        return new h(j);
    }

    public String a_() {
        return Double.toString(Double.longBitsToDouble(h()));
    }

    public c b() {
        return c.d;
    }

    public String e() {
        return "double";
    }

    public String toString() {
        long h = h();
        return "double{0x" + i.a(h) + " / " + Double.longBitsToDouble(h) + '}';
    }
}
