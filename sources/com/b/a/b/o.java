package com.b.a.b;

import java.util.List;
import java.util.Vector;

public class o implements CharSequence {
    protected char[] a = new char[51];
    protected int b;
    protected int c;
    protected int d;
    protected List<m> e;
    private int f;
    private p g;
    private r h;

    public o() {
        this.a[50] = 65535;
        this.f = 1;
        this.b = 0;
        this.c = 50;
        this.d = 1;
        this.g = new p();
        this.h = new r(this);
    }

    private int a(int i, int i2, int i3) {
        int p = p(i3);
        q.a(o(i3), "findCharOffsetBackward: Invalid startingOffset given");
        while (i2 < i && p < this.a.length) {
            if (this.a[p] == 10) {
                i2++;
            }
            p++;
            if (p == this.b) {
                p = this.c;
            }
        }
        if (i2 != i) {
            return -1;
        }
        return q(p);
    }

    private void a(int i, int i2) {
        m mVar = this.e.get(b(i).a());
        mVar.a(mVar.a() + i2);
    }

    private int b(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        q.a(o(i3), "findCharOffsetBackward: Invalid startOffset given");
        int p = p(i3);
        while (i2 > i - 1 && p >= 0) {
            if (p == this.c) {
                p = this.b;
            }
            p--;
            if (this.a[p] == 10) {
                i2--;
            }
        }
        if (p >= 0) {
            return q(p) + 1;
        }
        q.a(false, "findCharOffsetBackward: Invalid cache entry or line arguments");
        return -1;
    }

    private m b(int i) {
        int size = this.e.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int a2 = this.e.get(i3).a();
            i2 += a2;
            if (i2 >= i) {
                return new m(i3, i2 - a2);
            }
        }
        return new m(0, 0);
    }

    private void b(int i, int i2) {
        if (length() == 0) {
            f();
            return;
        }
        m c2 = c(i);
        if (i2 == 1) {
            m mVar = this.e.get(c2.a());
            if (mVar.a() > 1) {
                mVar.a(mVar.a() - 1);
            } else {
                this.e.remove(c2.a());
            }
        } else {
            int b2 = i - c2.b();
            m mVar2 = this.e.get(c2.a());
            if (mVar2.a() > b2) {
                mVar2.a(mVar2.a() - b2);
            } else {
                this.e.remove(c2.a());
            }
            int i3 = i2 - b2;
            if (i3 > 0) {
                int a2 = c2.a();
                while (a2 >= 0) {
                    m mVar3 = this.e.get(a2);
                    int a3 = mVar3.a();
                    if (i3 > a3) {
                        i3 -= a3;
                        this.e.remove(a2);
                        a2--;
                    } else {
                        mVar3.a(mVar3.a() - i3);
                        return;
                    }
                }
            }
        }
    }

    private int c(int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            if (this.a[i4] == 10) {
                i3++;
            }
        }
        return i3;
    }

    private m c(int i) {
        int size = this.e.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int a2 = this.e.get(i3).a();
            i2 += a2;
            if (i2 > i) {
                return new m(i3, i2 - a2);
            }
        }
        return new m(0, 0);
    }

    public static int g(int i) {
        long j = (long) (i + 50 + 1);
        if (j < 2147483647L) {
            return (int) j;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(int i) {
        if (i >= 0) {
            try {
                a(this.b, i);
                this.d += c(this.b, i);
            } catch (Throwable th) {
                throw th;
            }
        } else {
            b(this.b, 0 - i);
            this.d -= c(this.b + i, -i);
        }
        this.b += i;
        this.g.c(q(this.b - 1) + 1);
    }

    public synchronized void a(int i, int i2, long j, boolean z) {
        if (z) {
            try {
                this.h.b(i, i2, j);
            } catch (Throwable th) {
                throw th;
            }
        }
        int i3 = i + i2;
        if (i3 != this.b) {
            if (r(i3)) {
                k(i3);
            } else {
                l(i3 + e());
            }
        }
        for (int i4 = 0; i4 < i2; i4++) {
            this.b--;
            if (this.a[this.b] == 10) {
                this.d--;
            }
        }
        this.g.c(i);
        b(i, i2);
    }

    public void a(List<m> list) {
        this.e = list;
    }

    public synchronized void a(char[] cArr, int i, int i2) {
        this.a = cArr;
        m(i);
        this.d = i2;
        this.f = 1;
    }

    public synchronized void a(char[] cArr, int i, long j, boolean z) {
        if (z) {
            try {
                this.h.a(i, cArr.length, j);
            } catch (Throwable th) {
                throw th;
            }
        }
        int p = p(i);
        if (p != this.c) {
            if (r(p)) {
                k(p);
            } else {
                l(p);
            }
        }
        if (cArr.length >= e()) {
            n(cArr.length - e());
        }
        for (int i2 = 0; i2 < cArr.length; i2++) {
            if (cArr[i2] == 10) {
                this.d++;
            }
            this.a[this.b] = cArr[i2];
            this.b++;
        }
        this.g.c(i);
        a(i, cArr.length);
    }

    public synchronized char charAt(int i) {
        return this.a[p(i)];
    }

    public final synchronized int d() {
        return this.a.length - e();
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return this.c - this.b;
    }

    public void f() {
        this.e = new Vector();
        this.e.add(new m(length(), 0));
    }

    public List<m> g() {
        return this.e;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int h(int r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 >= 0) goto L_0x0006
            r3 = -1
            monitor-exit(r2)
            return r3
        L_0x0006:
            com.b.a.b.p r0 = r2.g     // Catch:{ all -> 0x002a }
            com.b.a.b.m r0 = r0.a(r3)     // Catch:{ all -> 0x002a }
            int r1 = r0.a()     // Catch:{ all -> 0x002a }
            int r0 = r0.b()     // Catch:{ all -> 0x002a }
            if (r3 <= r1) goto L_0x001b
            int r0 = r2.a((int) r3, (int) r1, (int) r0)     // Catch:{ all -> 0x002a }
            goto L_0x0021
        L_0x001b:
            if (r3 >= r1) goto L_0x0021
            int r0 = r2.b(r3, r1, r0)     // Catch:{ all -> 0x002a }
        L_0x0021:
            if (r0 < 0) goto L_0x0028
            com.b.a.b.p r1 = r2.g     // Catch:{ all -> 0x002a }
            r1.a(r3, r0)     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r2)
            return r0
        L_0x002a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.o.h(int):int");
    }

    public boolean h() {
        return this.h.e();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0075, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int i(int r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.o(r9)     // Catch:{ all -> 0x0078 }
            r1 = -1
            if (r0 != 0) goto L_0x000a
            monitor-exit(r8)
            return r1
        L_0x000a:
            com.b.a.b.p r0 = r8.g     // Catch:{ all -> 0x0078 }
            com.b.a.b.m r0 = r0.b(r9)     // Catch:{ all -> 0x0078 }
            int r2 = r0.a()     // Catch:{ all -> 0x0078 }
            int r0 = r0.b()     // Catch:{ all -> 0x0078 }
            int r0 = r8.p(r0)     // Catch:{ all -> 0x0078 }
            int r9 = r8.p(r9)     // Catch:{ all -> 0x0078 }
            r3 = 10
            if (r9 <= r0) goto L_0x0046
            r5 = r2
            r2 = -1
            r4 = -1
        L_0x0027:
            if (r0 >= r9) goto L_0x006b
            char[] r6 = r8.a     // Catch:{ all -> 0x0078 }
            int r6 = r6.length     // Catch:{ all -> 0x0078 }
            if (r0 >= r6) goto L_0x006b
            char[] r6 = r8.a     // Catch:{ all -> 0x0078 }
            char r6 = r6[r0]     // Catch:{ all -> 0x0078 }
            if (r6 != r3) goto L_0x003d
            int r2 = r5 + 1
            int r4 = r8.q(r0)     // Catch:{ all -> 0x0078 }
            int r4 = r4 + 1
            r5 = r2
        L_0x003d:
            int r0 = r0 + 1
            int r6 = r8.b     // Catch:{ all -> 0x0078 }
            if (r0 != r6) goto L_0x0027
            int r0 = r8.c     // Catch:{ all -> 0x0078 }
            goto L_0x0027
        L_0x0046:
            if (r9 >= r0) goto L_0x0068
            r5 = r2
            r2 = -1
            r4 = -1
        L_0x004b:
            if (r0 <= r9) goto L_0x006b
            if (r0 <= 0) goto L_0x006b
            int r6 = r8.c     // Catch:{ all -> 0x0078 }
            if (r0 != r6) goto L_0x0055
            int r0 = r8.b     // Catch:{ all -> 0x0078 }
        L_0x0055:
            int r0 = r0 + r1
            char[] r6 = r8.a     // Catch:{ all -> 0x0078 }
            char r6 = r6[r0]     // Catch:{ all -> 0x0078 }
            if (r6 != r3) goto L_0x004b
            int r2 = r8.q(r0)     // Catch:{ all -> 0x0078 }
            int r4 = r2 + 1
            int r2 = r5 + -1
            r7 = r5
            r5 = r2
            r2 = r7
            goto L_0x004b
        L_0x0068:
            r5 = r2
            r2 = -1
            r4 = -1
        L_0x006b:
            if (r0 != r9) goto L_0x0076
            if (r2 == r1) goto L_0x0074
            com.b.a.b.p r9 = r8.g     // Catch:{ all -> 0x0078 }
            r9.a(r2, r4)     // Catch:{ all -> 0x0078 }
        L_0x0074:
            monitor-exit(r8)
            return r5
        L_0x0076:
            monitor-exit(r8)
            return r1
        L_0x0078:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.b.o.i(int):int");
    }

    public void i() {
        this.h.f();
    }

    public void j() {
        this.h.g();
    }

    /* access modifiers changed from: package-private */
    public char[] j(int i) {
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = this.a[this.b + i2];
        }
        return cArr;
    }

    public int k() {
        return this.h.a();
    }

    /* access modifiers changed from: protected */
    public final void k(int i) {
        while (this.b > i) {
            this.c--;
            this.b--;
            this.a[this.c] = this.a[this.b];
        }
    }

    public int l() {
        return this.h.b();
    }

    /* access modifiers changed from: protected */
    public final void l(int i) {
        while (this.c < i) {
            this.a[this.b] = this.a[this.c];
            this.b++;
            this.c++;
        }
    }

    public int length() {
        return d() - 1;
    }

    /* access modifiers changed from: protected */
    public void m(int i) {
        int length = this.a.length - 1;
        int i2 = length - 1;
        this.a[length] = 65535;
        for (int i3 = i - 1; i3 >= 0; i3--) {
            this.a[i2] = this.a[i3];
            i2--;
        }
        this.b = 0;
        this.c = i2 + 1;
    }

    /* access modifiers changed from: protected */
    public void n(int i) {
        int i2 = i + (this.f * 50);
        char[] cArr = new char[(this.a.length + i2)];
        for (int i3 = 0; i3 < this.b; i3++) {
            cArr[i3] = this.a[i3];
        }
        for (int i4 = this.c; i4 < this.a.length; i4++) {
            cArr[i4 + i2] = this.a[i4];
        }
        this.c += i2;
        this.a = cArr;
        this.f <<= 1;
    }

    public final synchronized boolean o(int i) {
        boolean z;
        if (i >= 0) {
            if (i < d()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* access modifiers changed from: protected */
    public final int p(int i) {
        return r(i) ? i : i + e();
    }

    /* access modifiers changed from: protected */
    public final int q(int i) {
        return r(i) ? i : i - e();
    }

    /* access modifiers changed from: protected */
    public final boolean r(int i) {
        return i < this.b;
    }

    public synchronized CharSequence subSequence(int i, int i2) {
        if (o(i)) {
            if (i2 > 0) {
                if (i + i2 > d()) {
                    i2 = d() - i;
                }
                int p = p(i);
                char[] cArr = new char[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    cArr[i3] = this.a[p];
                    p++;
                    if (p == this.b) {
                        p = this.c;
                    }
                }
                return new String(cArr);
            }
        }
        return new String();
    }

    public String toString() {
        int d2 = d();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < d2; i++) {
            char charAt = charAt(i);
            if (charAt == 65535) {
                break;
            }
            stringBuffer.append(charAt);
        }
        return new String(stringBuffer);
    }
}
