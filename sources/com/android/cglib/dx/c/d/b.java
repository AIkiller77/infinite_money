package com.android.cglib.dx.c.d;

import com.android.cglib.dx.d.h;

public final class b extends h implements e {
    public static final b A = a(c.J, c.f);
    public static final b B = a(c.f, c.G, c.f);
    public static final b C = a(c.g, c.H, c.f);
    public static final b D = a(c.e, c.F, c.f);
    public static final b E = a(c.d, c.E, c.f);
    public static final b F = a(c.o, c.I, c.f);
    public static final b G = a(c.f, c.B, c.f);
    public static final b H = a(c.f, c.C, c.f);
    public static final b I = a(c.f, c.D, c.f);
    public static final b J = a(c.f, c.J, c.f);
    public static final b a = new b(0);
    public static final b b = a(c.f);
    public static final b c = a(c.g);
    public static final b d = a(c.e);
    public static final b e = a(c.d);
    public static final b f = a(c.o);
    public static final b g = a(c.k);
    public static final b h = a(c.r);
    public static final b i = a(c.f, c.f);
    public static final b j = a(c.g, c.g);
    public static final b k = a(c.e, c.e);
    public static final b l = a(c.d, c.d);
    public static final b m = a(c.o, c.o);
    public static final b n = a(c.f, c.o);
    public static final b o = a(c.g, c.o);
    public static final b p = a(c.e, c.o);

    /* renamed from: q  reason: collision with root package name */
    public static final b f18q = a(c.d, c.o);
    public static final b r = a(c.g, c.f);
    public static final b s = a(c.G, c.f);
    public static final b t = a(c.H, c.f);
    public static final b u = a(c.F, c.f);
    public static final b v = a(c.E, c.f);
    public static final b w = a(c.I, c.f);
    public static final b x = a(c.B, c.f);
    public static final b y = a(c.C, c.f);
    public static final b z = a(c.D, c.f);

    public b(int i2) {
        super(i2);
    }

    public static int a(e eVar, e eVar2) {
        int a2 = eVar.a();
        int a3 = eVar2.a();
        int min = Math.min(a2, a3);
        for (int i2 = 0; i2 < min; i2++) {
            int a4 = eVar.a(i2).compareTo(eVar2.a(i2));
            if (a4 != 0) {
                return a4;
            }
        }
        if (a2 == a3) {
            return 0;
        }
        return a2 < a3 ? -1 : 1;
    }

    public static b a(c cVar) {
        b bVar = new b(1);
        bVar.a(0, cVar);
        return bVar;
    }

    public static b a(c cVar, c cVar2) {
        b bVar = new b(2);
        bVar.a(0, cVar);
        bVar.a(1, cVar2);
        return bVar;
    }

    public static b a(c cVar, c cVar2, c cVar3) {
        b bVar = new b(3);
        bVar.a(0, cVar);
        bVar.a(1, cVar2);
        bVar.a(2, cVar3);
        return bVar;
    }

    public static b a(c cVar, c cVar2, c cVar3, c cVar4) {
        b bVar = new b(4);
        bVar.a(0, cVar);
        bVar.a(1, cVar2);
        bVar.a(2, cVar3);
        bVar.a(3, cVar4);
        return bVar;
    }

    public static String a(e eVar) {
        int a2 = eVar.a();
        if (a2 == 0) {
            return "<empty>";
        }
        StringBuffer stringBuffer = new StringBuffer(100);
        for (int i2 = 0; i2 < a2; i2++) {
            if (i2 != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(eVar.a(i2).a_());
        }
        return stringBuffer.toString();
    }

    public static int b(e eVar) {
        int a2 = eVar.a();
        int i2 = 0;
        for (int i3 = 0; i3 < a2; i3++) {
            i2 = (i2 * 31) + eVar.a(i3).hashCode();
        }
        return i2;
    }

    public c a(int i2) {
        return b(i2);
    }

    public void a(int i2, c cVar) {
        a(i2, cVar);
    }

    public int b() {
        int a2 = a();
        int i2 = 0;
        for (int i3 = 0; i3 < a2; i3++) {
            i2 += b(i3).g();
        }
        return i2;
    }

    public b b(c cVar) {
        int a2 = a();
        b bVar = new b(a2 + 1);
        int i2 = 0;
        bVar.a(0, cVar);
        while (i2 < a2) {
            int i3 = i2 + 1;
            bVar.a(i3, e(i2));
            i2 = i3;
        }
        return bVar;
    }

    public c b(int i2) {
        return (c) d(i2);
    }
}
