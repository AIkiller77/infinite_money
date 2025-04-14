package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.d;
import com.android.cglib.dx.d.r;
import java.util.HashMap;

public final class m implements d, r, Comparable<m> {
    private static final HashMap<Object, m> a = new HashMap<>(1000);
    private static final a b = new a();
    private final int c;
    private final d d;
    private final h e;

    private static class a {
        /* access modifiers changed from: private */
        public int a;
        /* access modifiers changed from: private */
        public d b;
        /* access modifiers changed from: private */
        public h c;

        private a() {
        }

        public m a() {
            return new m(this.a, this.b, this.c);
        }

        public void a(int i, d dVar, h hVar) {
            this.a = i;
            this.b = dVar;
            this.c = hVar;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof m)) {
                return false;
            }
            return ((m) obj).d(this.a, this.b, this.c);
        }

        public int hashCode() {
            return m.e(this.a, this.b, this.c);
        }
    }

    private m(int i, d dVar, h hVar) {
        if (i < 0) {
            throw new IllegalArgumentException("reg < 0");
        } else if (dVar == null) {
            throw new NullPointerException("type == null");
        } else {
            this.c = i;
            this.d = dVar;
            this.e = hVar;
        }
    }

    public static m a(int i, d dVar) {
        return c(i, dVar, (h) null);
    }

    public static m a(int i, d dVar, h hVar) {
        return c(i, dVar, hVar);
    }

    public static String a(int i) {
        return "v" + i;
    }

    private String a(boolean z) {
        String a_;
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append(k());
        stringBuffer.append(":");
        if (this.e != null) {
            stringBuffer.append(this.e.toString());
        }
        c b2 = this.d.b();
        stringBuffer.append(b2);
        if (b2 != this.d) {
            stringBuffer.append("=");
            if (z && (this.d instanceof v)) {
                a_ = ((v) this.d).f();
            } else if (!z || !(this.d instanceof com.android.cglib.dx.c.c.a)) {
                stringBuffer.append(this.d);
            } else {
                a_ = this.d.a_();
            }
            stringBuffer.append(a_);
        }
        return stringBuffer.toString();
    }

    private static m c(int i, d dVar, h hVar) {
        synchronized (a) {
            b.a(i, dVar, hVar);
            m mVar = a.get(b);
            if (mVar != null) {
                return mVar;
            }
            m a2 = b.a();
            a.put(a2, a2);
            return a2;
        }
    }

    /* access modifiers changed from: private */
    public boolean d(int i, d dVar, h hVar) {
        if (this.c != i || !this.d.equals(dVar)) {
            return false;
        }
        if (this.e != hVar) {
            return this.e != null && this.e.equals(hVar);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static int e(int i, d dVar, h hVar) {
        return ((((hVar != null ? hVar.hashCode() : 0) * 31) + dVar.hashCode()) * 31) + i;
    }

    public m a(d dVar) {
        return a(this.c, dVar, this.e);
    }

    public boolean a(m mVar) {
        return b(mVar) && this.c == mVar.c;
    }

    public String a_() {
        return a(true);
    }

    public m b(int i) {
        return this.c == i ? this : a(i, this.d, this.e);
    }

    public c b() {
        return this.d.b();
    }

    public boolean b(m mVar) {
        if (mVar != null && this.d.b().equals(mVar.d.b())) {
            return this.e == mVar.e || (this.e != null && this.e.equals(mVar.e));
        }
        return false;
    }

    public final int c() {
        return this.d.c();
    }

    /* renamed from: c */
    public int compareTo(m mVar) {
        if (this.c < mVar.c) {
            return -1;
        }
        if (this.c > mVar.c) {
            return 1;
        }
        int a2 = this.d.b().compareTo(mVar.d.b());
        if (a2 != 0) {
            return a2;
        }
        if (this.e == null) {
            return mVar.e == null ? 0 : -1;
        }
        if (mVar.e == null) {
            return 1;
        }
        return this.e.compareTo(mVar.e);
    }

    public m c(int i) {
        return i == 0 ? this : b(this.c + i);
    }

    public final int d() {
        return this.d.d();
    }

    public int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        int i;
        d dVar;
        h hVar;
        if (obj instanceof m) {
            m mVar = (m) obj;
            i = mVar.c;
            dVar = mVar.d;
            hVar = mVar.e;
        } else if (!(obj instanceof a)) {
            return false;
        } else {
            a aVar = (a) obj;
            i = aVar.a;
            dVar = aVar.b;
            hVar = aVar.c;
        }
        return d(i, dVar, hVar);
    }

    public d f() {
        return this.d;
    }

    public h g() {
        return this.e;
    }

    public int h() {
        return this.c + i();
    }

    public int hashCode() {
        return e(this.c, this.d, this.e);
    }

    public int i() {
        return this.d.b().g();
    }

    public boolean j() {
        return this.d.b().h();
    }

    public String k() {
        return a(this.c);
    }

    public String toString() {
        return a(false);
    }
}
