package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.a.d;
import com.android.cglib.dx.c.c.b;
import com.android.cglib.dx.c.c.c;
import com.android.cglib.dx.c.c.e;
import com.android.cglib.dx.c.c.f;
import com.android.cglib.dx.c.c.g;
import com.android.cglib.dx.c.c.h;
import com.android.cglib.dx.c.c.i;
import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.c.c.k;
import com.android.cglib.dx.c.c.l;
import com.android.cglib.dx.c.c.m;
import com.android.cglib.dx.c.c.q;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.c.u;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;
import java.util.Collection;

public final class au {
    private final l a;
    private final a b;

    public au(l lVar, a aVar) {
        if (lVar == null) {
            throw new NullPointerException("file == null");
        } else if (aVar == null) {
            throw new NullPointerException("out == null");
        } else {
            this.a = lVar;
            this.b = aVar;
        }
    }

    private void a(int i, long j) {
        int numberOfLeadingZeros = ((65 - Long.numberOfLeadingZeros(j ^ (j >> 63))) + 7) >> 3;
        this.b.b(i | ((numberOfLeadingZeros - 1) << 5));
        while (numberOfLeadingZeros > 0) {
            this.b.b((byte) ((int) j));
            j >>= 8;
            numberOfLeadingZeros--;
        }
    }

    public static void a(l lVar, com.android.cglib.dx.c.a.a aVar) {
        aq j = lVar.j();
        ao g = lVar.g();
        j.a(aVar.b());
        for (d next : aVar.d()) {
            g.a(next.a());
            a(lVar, next.b());
        }
    }

    public static void a(l lVar, com.android.cglib.dx.c.c.a aVar) {
        if (aVar instanceof b) {
            a(lVar, ((b) aVar).b());
        } else if (aVar instanceof c) {
            c.a b2 = ((c) aVar).b();
            int a2 = b2.a();
            for (int i = 0; i < a2; i++) {
                a(lVar, b2.a(i));
            }
        } else {
            lVar.a(aVar);
        }
    }

    public static String b(com.android.cglib.dx.c.c.a aVar) {
        if (c(aVar) == 30) {
            return "null";
        }
        return aVar.e() + ' ' + aVar.a_();
    }

    private void b(int i, long j) {
        int numberOfLeadingZeros = 64 - Long.numberOfLeadingZeros(j);
        if (numberOfLeadingZeros == 0) {
            numberOfLeadingZeros = 1;
        }
        int i2 = (numberOfLeadingZeros + 7) >> 3;
        this.b.b(i | ((i2 - 1) << 5));
        while (i2 > 0) {
            this.b.b((byte) ((int) j));
            j >>= 8;
            i2--;
        }
    }

    private static int c(com.android.cglib.dx.c.c.a aVar) {
        if (aVar instanceof f) {
            return 0;
        }
        if (aVar instanceof u) {
            return 2;
        }
        if (aVar instanceof g) {
            return 3;
        }
        if (aVar instanceof l) {
            return 4;
        }
        if (aVar instanceof q) {
            return 6;
        }
        if (aVar instanceof k) {
            return 16;
        }
        if (aVar instanceof h) {
            return 17;
        }
        if (aVar instanceof v) {
            return 23;
        }
        if (aVar instanceof w) {
            return 24;
        }
        if (aVar instanceof j) {
            return 25;
        }
        if (aVar instanceof s) {
            return 26;
        }
        if (aVar instanceof i) {
            return 27;
        }
        if (aVar instanceof c) {
            return 28;
        }
        if (aVar instanceof b) {
            return 29;
        }
        if (aVar instanceof m) {
            return 30;
        }
        if (aVar instanceof e) {
            return 31;
        }
        throw new RuntimeException("Shouldn't happen");
    }

    private void c(int i, long j) {
        int numberOfTrailingZeros = 64 - Long.numberOfTrailingZeros(j);
        if (numberOfTrailingZeros == 0) {
            numberOfTrailingZeros = 1;
        }
        int i2 = (numberOfTrailingZeros + 7) >> 3;
        long j2 = j >> (64 - (i2 * 8));
        this.b.b(i | ((i2 - 1) << 5));
        while (i2 > 0) {
            this.b.b((byte) ((int) j2));
            j2 >>= 8;
            i2--;
        }
    }

    public void a(com.android.cglib.dx.c.a.a aVar, boolean z) {
        boolean z2 = z && this.b.a();
        ao g = this.a.g();
        aq j = this.a.j();
        w b2 = aVar.b();
        int b3 = j.b(b2);
        if (z2) {
            a aVar2 = this.b;
            aVar2.a("  type_idx: " + com.android.cglib.dx.d.i.a(b3) + " // " + b2.a_());
        }
        this.b.e(j.b(aVar.b()));
        Collection<d> d = aVar.d();
        int size = d.size();
        if (z2) {
            a aVar3 = this.b;
            aVar3.a("  size: " + com.android.cglib.dx.d.i.a(size));
        }
        this.b.e(size);
        int i = 0;
        for (d next : d) {
            v a2 = next.a();
            int b4 = g.b(a2);
            com.android.cglib.dx.c.c.a b5 = next.b();
            if (z2) {
                a aVar4 = this.b;
                aVar4.a(0, "  elements[" + i + "]:");
                i++;
                a aVar5 = this.b;
                aVar5.a("    name_idx: " + com.android.cglib.dx.d.i.a(b4) + " // " + a2.a_());
            }
            this.b.e(b4);
            if (z2) {
                a aVar6 = this.b;
                aVar6.a("    value: " + b(b5));
            }
            a(b5);
        }
        if (z2) {
            this.b.c();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
        r4 = r1.b(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008a, code lost:
        r1 = (long) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009c, code lost:
        c(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a6, code lost:
        b(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a9, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.android.cglib.dx.c.c.a r4) {
        /*
            r3 = this;
            int r0 = c(r4)
            if (r0 == 0) goto L_0x00aa
            r1 = 6
            if (r0 == r1) goto L_0x00aa
            switch(r0) {
                case 2: goto L_0x00aa;
                case 3: goto L_0x00a0;
                case 4: goto L_0x00aa;
                default: goto L_0x000c;
            }
        L_0x000c:
            switch(r0) {
                case 16: goto L_0x0093;
                case 17: goto L_0x008c;
                default: goto L_0x000f;
            }
        L_0x000f:
            r1 = 0
            switch(r0) {
                case 23: goto L_0x007e;
                case 24: goto L_0x0071;
                case 25: goto L_0x0064;
                case 26: goto L_0x0057;
                case 27: goto L_0x004a;
                case 28: goto L_0x003f;
                case 29: goto L_0x0030;
                case 30: goto L_0x002a;
                case 31: goto L_0x001b;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r0 = "Shouldn't happen"
            r4.<init>(r0)
            throw r4
        L_0x001b:
            com.android.cglib.dx.c.c.e r4 = (com.android.cglib.dx.c.c.e) r4
            int r4 = r4.g()
            com.android.cglib.dx.d.a r1 = r3.b
            int r4 = r4 << 5
            r4 = r4 | r0
            r1.b(r4)
            return
        L_0x002a:
            com.android.cglib.dx.d.a r4 = r3.b
            r4.b(r0)
            return
        L_0x0030:
            com.android.cglib.dx.d.a r2 = r3.b
            r2.b(r0)
            com.android.cglib.dx.c.c.b r4 = (com.android.cglib.dx.c.c.b) r4
            com.android.cglib.dx.c.a.a r4 = r4.b()
            r3.a((com.android.cglib.dx.c.a.a) r4, (boolean) r1)
            return
        L_0x003f:
            com.android.cglib.dx.d.a r2 = r3.b
            r2.b(r0)
            com.android.cglib.dx.c.c.c r4 = (com.android.cglib.dx.c.c.c) r4
            r3.a((com.android.cglib.dx.c.c.c) r4, (boolean) r1)
            return
        L_0x004a:
            com.android.cglib.dx.c.c.i r4 = (com.android.cglib.dx.c.c.i) r4
            com.android.cglib.dx.c.c.j r4 = r4.f()
            com.android.cglib.dx.a.b.l r1 = r3.a
            com.android.cglib.dx.a.b.s r1 = r1.l()
            goto L_0x006c
        L_0x0057:
            com.android.cglib.dx.a.b.l r1 = r3.a
            com.android.cglib.dx.a.b.ae r1 = r1.m()
            com.android.cglib.dx.c.c.s r4 = (com.android.cglib.dx.c.c.s) r4
            int r4 = r1.b(r4)
            goto L_0x008a
        L_0x0064:
            com.android.cglib.dx.a.b.l r1 = r3.a
            com.android.cglib.dx.a.b.s r1 = r1.l()
            com.android.cglib.dx.c.c.j r4 = (com.android.cglib.dx.c.c.j) r4
        L_0x006c:
            int r4 = r1.b(r4)
            goto L_0x008a
        L_0x0071:
            com.android.cglib.dx.a.b.l r1 = r3.a
            com.android.cglib.dx.a.b.aq r1 = r1.j()
            com.android.cglib.dx.c.c.w r4 = (com.android.cglib.dx.c.c.w) r4
            int r4 = r1.b((com.android.cglib.dx.c.c.w) r4)
            goto L_0x008a
        L_0x007e:
            com.android.cglib.dx.a.b.l r1 = r3.a
            com.android.cglib.dx.a.b.ao r1 = r1.g()
            com.android.cglib.dx.c.c.v r4 = (com.android.cglib.dx.c.c.v) r4
            int r4 = r1.b((com.android.cglib.dx.c.c.v) r4)
        L_0x008a:
            long r1 = (long) r4
            goto L_0x00a6
        L_0x008c:
            com.android.cglib.dx.c.c.h r4 = (com.android.cglib.dx.c.c.h) r4
            long r1 = r4.h()
            goto L_0x009c
        L_0x0093:
            com.android.cglib.dx.c.c.k r4 = (com.android.cglib.dx.c.c.k) r4
            long r1 = r4.h()
            r4 = 32
            long r1 = r1 << r4
        L_0x009c:
            r3.c(r0, r1)
            return
        L_0x00a0:
            com.android.cglib.dx.c.c.p r4 = (com.android.cglib.dx.c.c.p) r4
            long r1 = r4.h()
        L_0x00a6:
            r3.b(r0, r1)
            return
        L_0x00aa:
            com.android.cglib.dx.c.c.p r4 = (com.android.cglib.dx.c.c.p) r4
            long r1 = r4.h()
            r3.a((int) r0, (long) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.a.b.au.a(com.android.cglib.dx.c.c.a):void");
    }

    public void a(c cVar, boolean z) {
        boolean z2 = z && this.b.a();
        c.a b2 = cVar.b();
        int a2 = b2.a();
        if (z2) {
            a aVar = this.b;
            aVar.a("  size: " + com.android.cglib.dx.d.i.a(a2));
        }
        this.b.e(a2);
        for (int i = 0; i < a2; i++) {
            com.android.cglib.dx.c.c.a a3 = b2.a(i);
            if (z2) {
                a aVar2 = this.b;
                aVar2.a("  [" + Integer.toHexString(i) + "] " + b(a3));
            }
            a(a3);
        }
        if (z2) {
            this.b.c();
        }
    }
}
