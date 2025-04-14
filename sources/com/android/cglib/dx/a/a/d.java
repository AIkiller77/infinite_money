package com.android.cglib.dx.a.a;

import com.android.cglib.dx.d.h;

public final class d extends h implements Comparable<d> {
    public static final d a = new d(0);

    public static class a implements Comparable<a> {
        private final int a;
        private final int b;
        private final c c;

        public a(int i, int i2, c cVar) {
            if (i < 0) {
                throw new IllegalArgumentException("start < 0");
            } else if (i2 <= i) {
                throw new IllegalArgumentException("end <= start");
            } else if (cVar.g()) {
                throw new IllegalArgumentException("handlers.isMutable()");
            } else {
                this.a = i;
                this.b = i2;
                this.c = cVar;
            }
        }

        public int a() {
            return this.a;
        }

        /* renamed from: a */
        public int compareTo(a aVar) {
            if (this.a < aVar.a) {
                return -1;
            }
            if (this.a > aVar.a) {
                return 1;
            }
            if (this.b < aVar.b) {
                return -1;
            }
            if (this.b > aVar.b) {
                return 1;
            }
            return this.c.compareTo(aVar.c);
        }

        public int b() {
            return this.b;
        }

        public c c() {
            return this.c;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && compareTo((a) obj) == 0;
        }

        public int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.c.hashCode();
        }
    }

    public d(int i) {
        super(i);
    }

    /* renamed from: a */
    public int compareTo(d dVar) {
        if (this == dVar) {
            return 0;
        }
        int a2 = a();
        int a3 = dVar.a();
        int min = Math.min(a2, a3);
        for (int i = 0; i < min; i++) {
            int a4 = a(i).compareTo(dVar.a(i));
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

    public void a(int i, a aVar) {
        a(i, aVar);
    }
}
