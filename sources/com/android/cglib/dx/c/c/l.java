package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;

public final class l extends n {
    public static final l a = a(-1);
    public static final l b = a(0);
    public static final l c = a(1);
    public static final l d = a(2);
    public static final l e = a(3);
    public static final l f = a(4);
    public static final l g = a(5);
    private static final l[] h = new l[511];

    private l(int i) {
        super(i);
    }

    public static l a(int i) {
        int length = (Integer.MAX_VALUE & i) % h.length;
        l lVar = h[length];
        if (lVar != null && lVar.e_() == i) {
            return lVar;
        }
        l lVar2 = new l(i);
        h[length] = lVar2;
        return lVar2;
    }

    public String a_() {
        return Integer.toString(g());
    }

    public c b() {
        return c.f;
    }

    public String e() {
        return "int";
    }

    public int e_() {
        return g();
    }

    public String toString() {
        int g2 = g();
        return "int{0x" + i.a(g2) + " / " + g2 + '}';
    }
}
