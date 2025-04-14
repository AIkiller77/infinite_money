package com.android.cglib.dx.a.b;

import com.android.cglib.dx.a.a.c;
import com.android.cglib.dx.a.a.d;
import com.android.cglib.dx.a.a.g;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public final class e {
    private final g a;
    private d b = null;
    private byte[] c = null;
    private int d = 0;
    private TreeMap<c, Integer> e = null;

    public e(g gVar) {
        this.a = gVar;
    }

    private static void a(c cVar, int i, int i2, String str, PrintWriter printWriter, a aVar) {
        String a2 = cVar.a(str, i.c(i) + ": ");
        if (printWriter != null) {
            printWriter.println(a2);
        }
        aVar.a(i2, a2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: com.android.cglib.dx.a.a.c} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r16, java.io.PrintWriter r17, com.android.cglib.dx.d.a r18) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r7 = r17
            r8 = r18
            r0.c()
            r2 = 0
            if (r8 == 0) goto L_0x000f
            r3 = 1
            goto L_0x0010
        L_0x000f:
            r3 = 0
        L_0x0010:
            if (r3 == 0) goto L_0x0014
            r4 = 6
            goto L_0x0015
        L_0x0014:
            r4 = 0
        L_0x0015:
            if (r3 == 0) goto L_0x0019
            r5 = 2
            goto L_0x001a
        L_0x0019:
            r5 = 0
        L_0x001a:
            com.android.cglib.dx.a.a.d r6 = r0.b
            int r6 = r6.a()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r1)
            java.lang.String r10 = "  "
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            if (r3 == 0) goto L_0x0048
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String r11 = "tries:"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r8.a(r2, r10)
            goto L_0x005c
        L_0x0048:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String r11 = "tries:"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r7.println(r10)
        L_0x005c:
            r10 = 0
        L_0x005d:
            if (r10 >= r6) goto L_0x00ad
            com.android.cglib.dx.a.a.d r11 = r0.b
            com.android.cglib.dx.a.a.d$a r11 = r11.a((int) r10)
            com.android.cglib.dx.a.a.c r12 = r11.c()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r9)
            java.lang.String r14 = "try "
            r13.append(r14)
            int r14 = r11.a()
            java.lang.String r14 = com.android.cglib.dx.d.i.d(r14)
            r13.append(r14)
            java.lang.String r14 = ".."
            r13.append(r14)
            int r11 = r11.b()
            java.lang.String r11 = com.android.cglib.dx.d.i.d(r11)
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            java.lang.String r13 = ""
            java.lang.String r12 = r12.a(r9, r13)
            if (r3 == 0) goto L_0x00a4
            r8.a(r4, r11)
            r8.a(r5, r12)
            goto L_0x00aa
        L_0x00a4:
            r7.println(r11)
            r7.println(r12)
        L_0x00aa:
            int r10 = r10 + 1
            goto L_0x005d
        L_0x00ad:
            if (r3 != 0) goto L_0x00b0
            return
        L_0x00b0:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = "handlers:"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r8.a(r2, r1)
            int r1 = r0.d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r9)
            java.lang.String r4 = "size: "
            r3.append(r4)
            java.util.TreeMap<com.android.cglib.dx.a.a.c, java.lang.Integer> r4 = r0.e
            int r4 = r4.size()
            java.lang.String r4 = com.android.cglib.dx.d.i.c(r4)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r8.a(r1, r3)
            r1 = 0
            java.util.TreeMap<com.android.cglib.dx.a.a.c, java.lang.Integer> r3 = r0.e
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r10 = r3.iterator()
        L_0x00f2:
            boolean r3 = r10.hasNext()
            if (r3 == 0) goto L_0x011c
            java.lang.Object r3 = r10.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            r11 = r4
            com.android.cglib.dx.a.a.c r11 = (com.android.cglib.dx.a.a.c) r11
            java.lang.Object r3 = r3.getValue()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r12 = r3.intValue()
            if (r1 == 0) goto L_0x0119
            int r3 = r12 - r2
            r4 = r9
            r5 = r7
            r6 = r8
            a(r1, r2, r3, r4, r5, r6)
        L_0x0119:
            r1 = r11
            r2 = r12
            goto L_0x00f2
        L_0x011c:
            byte[] r3 = r0.c
            int r3 = r3.length
            int r3 = r3 - r2
            r4 = r9
            r5 = r7
            r6 = r8
            a(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.a.b.e.a(java.lang.String, java.io.PrintWriter, com.android.cglib.dx.d.a):void");
    }

    private void c() {
        if (this.b == null) {
            this.b = this.a.g();
        }
    }

    public int a() {
        c();
        return this.b.a();
    }

    public void a(l lVar) {
        c();
        aq j = lVar.j();
        int a2 = this.b.a();
        this.e = new TreeMap<>();
        for (int i = 0; i < a2; i++) {
            this.e.put(this.b.a(i).c(), (Object) null);
        }
        if (this.e.size() > 65535) {
            throw new UnsupportedOperationException("too many catch handlers");
        }
        com.android.cglib.dx.d.d dVar = new com.android.cglib.dx.d.d();
        this.d = dVar.e(this.e.size());
        for (Map.Entry next : this.e.entrySet()) {
            c cVar = (c) next.getKey();
            int a3 = cVar.a();
            boolean b2 = cVar.b();
            next.setValue(Integer.valueOf(dVar.g()));
            if (b2) {
                dVar.f(-(a3 - 1));
                a3--;
            } else {
                dVar.f(a3);
            }
            for (int i2 = 0; i2 < a3; i2++) {
                c.a a4 = cVar.a(i2);
                dVar.e(j.b(a4.a()));
                dVar.e(a4.b());
            }
            if (b2) {
                dVar.e(cVar.a(a3).b());
            }
        }
        this.c = dVar.f();
    }

    public void a(l lVar, a aVar) {
        c();
        if (aVar.a()) {
            a("  ", (PrintWriter) null, aVar);
        }
        int a2 = this.b.a();
        for (int i = 0; i < a2; i++) {
            d.a a3 = this.b.a(i);
            int a4 = a3.a();
            int b2 = a3.b();
            int i2 = b2 - a4;
            if (i2 >= 65536) {
                throw new UnsupportedOperationException("bogus exception range: " + i.a(a4) + ".." + i.a(b2));
            }
            aVar.d(a4);
            aVar.c(i2);
            aVar.c(this.e.get(a3.c()).intValue());
        }
        aVar.a(this.c);
    }

    public int b() {
        return (a() * 8) + this.c.length;
    }
}
