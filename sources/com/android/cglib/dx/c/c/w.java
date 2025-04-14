package com.android.cglib.dx.c.c;

import com.android.cglib.dx.c.d.c;
import com.tencent.open.SocialConstants;
import java.util.HashMap;

public final class w extends x {
    public static final w a = a(c.o);
    public static final w b = a(c.s);
    public static final w c = a(c.t);
    public static final w d = a(c.u);
    public static final w e = a(c.v);
    public static final w f = a(c.w);
    public static final w g = a(c.y);
    public static final w h = a(c.x);
    public static final w i = a(c.z);
    public static final w j = a(c.A);
    public static final w k = a(c.B);
    public static final w l = a(c.C);
    public static final w m = a(c.D);
    public static final w n = a(c.E);
    public static final w o = a(c.F);
    public static final w p = a(c.H);

    /* renamed from: q  reason: collision with root package name */
    public static final w f17q = a(c.G);
    public static final w r = a(c.J);
    private static final HashMap<c, w> s = new HashMap<>(100);
    private final c t;
    private v u;

    public w(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        } else if (cVar == c.j) {
            throw new UnsupportedOperationException("KNOWN_NULL is not representable");
        } else {
            this.t = cVar;
            this.u = null;
        }
    }

    public static w a(c cVar) {
        w wVar;
        synchronized (s) {
            wVar = s.get(cVar);
            if (wVar == null) {
                wVar = new w(cVar);
                s.put(cVar, wVar);
            }
        }
        return wVar;
    }

    public String a_() {
        return this.t.a_();
    }

    /* access modifiers changed from: protected */
    public int b(a aVar) {
        return this.t.e().compareTo(((w) aVar).t.e());
    }

    public c b() {
        return c.m;
    }

    public String e() {
        return SocialConstants.PARAM_TYPE;
    }

    public boolean equals(Object obj) {
        return (obj instanceof w) && this.t == ((w) obj).t;
    }

    public c f() {
        return this.t;
    }

    public v g() {
        if (this.u == null) {
            this.u = new v(this.t.e());
        }
        return this.u;
    }

    public int hashCode() {
        return this.t.hashCode();
    }

    public String toString() {
        return "type{" + a_() + '}';
    }
}
