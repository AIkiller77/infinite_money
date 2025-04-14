package com.android.cglib.dx.c.b;

import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.k;

public final class q {
    private final c a;
    private final int b;
    private k[] c;
    private k d;

    public q(c cVar, int i) {
        if (cVar == null) {
            throw new NullPointerException("blocks == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("firstLabel < 0");
        } else {
            this.a = cVar;
            this.b = i;
            this.c = null;
            this.d = null;
        }
    }

    private void c() {
        int d2 = this.a.d();
        k[] kVarArr = new k[d2];
        k kVar = new k(10);
        int a2 = this.a.a();
        for (int i = 0; i < a2; i++) {
            b a3 = this.a.a(i);
            int a4 = a3.a();
            k c2 = a3.c();
            int a5 = c2.a();
            if (a5 == 0) {
                kVar.b(a4);
            } else {
                for (int i2 = 0; i2 < a5; i2++) {
                    int a6 = c2.a(i2);
                    k kVar2 = kVarArr[a6];
                    if (kVar2 == null) {
                        kVar2 = new k(10);
                        kVarArr[a6] = kVar2;
                    }
                    kVar2.b(a4);
                }
            }
        }
        for (int i3 = 0; i3 < d2; i3++) {
            k kVar3 = kVarArr[i3];
            if (kVar3 != null) {
                kVar3.b();
                kVar3.e();
            }
        }
        kVar.b();
        kVar.e();
        if (kVarArr[this.b] == null) {
            kVarArr[this.b] = k.a;
        }
        this.c = kVarArr;
        this.d = kVar;
    }

    public c a() {
        return this.a;
    }

    public k a(int i) {
        if (this.d == null) {
            c();
        }
        k kVar = this.c[i];
        if (kVar != null) {
            return kVar;
        }
        throw new RuntimeException("no such block: " + i.c(i));
    }

    public int b() {
        return this.b;
    }
}
