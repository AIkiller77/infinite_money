package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class q extends o {
    public static final q a = a(0);
    public static final q b = a(1);

    private q(long j) {
        super(j);
    }

    public static q a(long j) {
        return new q(j);
    }

    public String a_() {
        return Long.toString(h());
    }

    public c b() {
        return c.g;
    }

    public String e() {
        return "long";
    }

    public String toString() {
        long h = h();
        return "long{0x" + i.a(h) + " / " + h + '}';
    }
}
