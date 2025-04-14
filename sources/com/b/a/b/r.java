package com.b.a.b;

import java.util.LinkedList;

public class r {
    long a = -1;
    /* access modifiers changed from: private */
    public o b;
    private LinkedList<a> c = new LinkedList<>();
    private boolean d = false;
    private int e = 0;
    private int f = 0;

    private abstract class a {
        public int a;
        public int b;
        public String c;
        public int d;

        private a() {
        }

        public abstract void a();

        public abstract boolean a(int i, int i2, long j);

        public abstract void b();

        public abstract void c();

        public abstract int d();

        public abstract int e();
    }

    private class b extends a {
        public b(int i, int i2, int i3) {
            super();
            this.a = i;
            this.b = i2;
            this.d = i3;
        }

        public void a() {
            if (this.c == null) {
                c();
                r.this.b.a(this.b);
                return;
            }
            r.this.b.a(this.c.toCharArray(), this.a, 0, false);
        }

        public boolean a(int i, int i2, long j) {
            if (r.this.a < 0 || j - r.this.a >= 1000000000 || i != ((this.a - this.b) - i2) + 1) {
                return false;
            }
            this.a = i;
            this.b += i2;
            r.this.h();
            return true;
        }

        public void b() {
            r.this.b.a(this.a, this.b, 0, false);
        }

        public void c() {
            this.c = new String(r.this.b.j(this.b));
        }

        public int d() {
            return this.a + this.b;
        }

        public int e() {
            return this.a;
        }
    }

    private class c extends a {
        public c(int i, int i2, int i3) {
            super();
            this.a = i;
            this.b = i2;
            this.d = i3;
        }

        public void a() {
            if (this.c == null) {
                c();
                r.this.b.a(-this.b);
                return;
            }
            r.this.b.a(this.a, this.b, 0, false);
        }

        public boolean a(int i, int i2, long j) {
            if (r.this.a < 0 || j - r.this.a >= 1000000000 || i != this.a + this.b) {
                return false;
            }
            this.b += i2;
            r.this.h();
            return true;
        }

        public void b() {
            r.this.b.a(this.c.toCharArray(), this.a, 0, false);
        }

        public void c() {
            this.c = r.this.b.subSequence(this.a, this.b).toString();
        }

        public int d() {
            return this.a;
        }

        public int e() {
            return this.a + this.b;
        }
    }

    public r(o oVar) {
        this.b = oVar;
    }

    private void a(a aVar) {
        h();
        this.f++;
        this.c.add(aVar);
    }

    /* access modifiers changed from: private */
    public void h() {
        while (this.c.size() > this.f) {
            this.c.removeLast();
        }
    }

    public int a() {
        if (!c()) {
            return -1;
        }
        a aVar = this.c.get(this.f - 1);
        int i = aVar.d;
        while (true) {
            a aVar2 = this.c.get(this.f - 1);
            if (aVar2.d != i) {
                break;
            }
            aVar2.a();
            this.f--;
            if (!c()) {
                aVar = aVar2;
                break;
            }
            aVar = aVar2;
        }
        return aVar.d();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r4, int r5, long r6) {
        /*
            r3 = this;
            boolean r0 = r3.c()
            r1 = 1
            if (r0 == 0) goto L_0x0021
            java.util.LinkedList<com.b.a.b.r$a> r0 = r3.c
            int r2 = r3.f
            int r2 = r2 - r1
            java.lang.Object r0 = r0.get(r2)
            com.b.a.b.r$a r0 = (com.b.a.b.r.a) r0
            boolean r2 = r0 instanceof com.b.a.b.r.c
            if (r2 == 0) goto L_0x001e
            boolean r2 = r0.a(r4, r5, r6)
            if (r2 == 0) goto L_0x001e
            r0 = 1
            goto L_0x0022
        L_0x001e:
            r0.c()
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 != 0) goto L_0x0037
            com.b.a.b.r$c r0 = new com.b.a.b.r$c
            int r2 = r3.e
            r0.<init>(r4, r5, r2)
            r3.a((com.b.a.b.r.a) r0)
            boolean r4 = r3.d
            if (r4 != 0) goto L_0x0037
            int r4 = r3.e
            int r4 = r4 + r1
            r3.e = r4
        L_0x0037:
            r3.a = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.r.a(int, int, long):void");
    }

    public int b() {
        if (!d()) {
            return -1;
        }
        a aVar = this.c.get(this.f);
        int i = aVar.d;
        while (true) {
            a aVar2 = this.c.get(this.f);
            if (aVar2.d != i) {
                break;
            }
            aVar2.b();
            this.f++;
            if (!d()) {
                aVar = aVar2;
                break;
            }
            aVar = aVar2;
        }
        return aVar.e();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(int r4, int r5, long r6) {
        /*
            r3 = this;
            boolean r0 = r3.c()
            r1 = 1
            if (r0 == 0) goto L_0x0021
            java.util.LinkedList<com.b.a.b.r$a> r0 = r3.c
            int r2 = r3.f
            int r2 = r2 - r1
            java.lang.Object r0 = r0.get(r2)
            com.b.a.b.r$a r0 = (com.b.a.b.r.a) r0
            boolean r2 = r0 instanceof com.b.a.b.r.b
            if (r2 == 0) goto L_0x001e
            boolean r2 = r0.a(r4, r5, r6)
            if (r2 == 0) goto L_0x001e
            r0 = 1
            goto L_0x0022
        L_0x001e:
            r0.c()
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 != 0) goto L_0x0037
            com.b.a.b.r$b r0 = new com.b.a.b.r$b
            int r2 = r3.e
            r0.<init>(r4, r5, r2)
            r3.a((com.b.a.b.r.a) r0)
            boolean r4 = r3.d
            if (r4 != 0) goto L_0x0037
            int r4 = r3.e
            int r4 = r4 + r1
            r3.e = r4
        L_0x0037:
            r3.a = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.r.b(int, int, long):void");
    }

    public final boolean c() {
        return this.f > 0;
    }

    public final boolean d() {
        return this.f < this.c.size();
    }

    public boolean e() {
        return this.d;
    }

    public void f() {
        this.d = true;
    }

    public void g() {
        this.d = false;
        this.e++;
    }
}
