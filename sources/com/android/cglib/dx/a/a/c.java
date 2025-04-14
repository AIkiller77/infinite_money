package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.h;
import com.android.cglib.dx.d.i;

public final class c extends h implements Comparable<c> {
    public static final c a = new c(0);

    public static class a implements Comparable<a> {
        private final w a;
        private final int b;

        public a(w wVar, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("handler < 0");
            } else if (wVar == null) {
                throw new NullPointerException("exceptionType == null");
            } else {
                this.b = i;
                this.a = wVar;
            }
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            if (this.b < aVar.b) {
                return -1;
            }
            if (this.b > aVar.b) {
                return 1;
            }
            return this.a.compareTo(aVar.a);
        }

        public w a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && compareTo((a) obj) == 0;
        }

        public int hashCode() {
            return (this.b * 31) + this.a.hashCode();
        }
    }

    public c(int i) {
        super(i);
    }

    /* renamed from: a */
    public int compareTo(c cVar) {
        if (this == cVar) {
            return 0;
        }
        int a2 = a();
        int a3 = cVar.a();
        int min = Math.min(a2, a3);
        for (int i = 0; i < min; i++) {
            int a4 = a(i).compareTo(cVar.a(i));
            if (a4 != 0) {
                return a4;
            }
        }
        if (a2 < a3) {
            return -1;
        }
        return a2 > a3 ? 1 : 0;
    }

    public a a(int i) {
        return (a) d(i);
    }

    public String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(100);
        int a2 = a();
        sb.append(str);
        sb.append(str2);
        sb.append("catch ");
        int i = 0;
        while (i < a2) {
            a a3 = a(i);
            if (i != 0) {
                sb.append(",\n");
                sb.append(str);
                sb.append("  ");
            }
            sb.append((i != a2 + -1 || !b()) ? a3.a().a_() : "<any>");
            sb.append(" -> ");
            sb.append(i.d(a3.b()));
            i++;
        }
        return sb.toString();
    }

    public void a(int i, w wVar, int i2) {
        a(i, new a(wVar, i2));
    }

    public String a_() {
        return a("", "");
    }

    public boolean b() {
        int a2 = a();
        if (a2 == 0) {
            return false;
        }
        return a(a2 - 1).a().equals(w.a);
    }
}
