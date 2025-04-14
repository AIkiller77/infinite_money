package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.s;
import com.android.cglib.dx.d.h;

public final class u extends h {
    public static final u a = new u(0);

    public static class a {
        private final int a;
        private final s b;

        public a(int i, s sVar) {
            if (i < 0) {
                throw new IllegalArgumentException("address < 0");
            } else if (sVar == null) {
                throw new NullPointerException("position == null");
            } else {
                this.a = i;
                this.b = sVar;
            }
        }

        public int a() {
            return this.a;
        }

        public s b() {
            return this.b;
        }
    }

    public u(int i) {
        super(i);
    }

    public static u a(i iVar, int i) {
        switch (i) {
            case 1:
                return a;
            case 2:
            case 3:
                s sVar = s.a;
                int a2 = iVar.a();
                a[] aVarArr = new a[a2];
                s sVar2 = sVar;
                int i2 = 0;
                boolean z = false;
                for (int i3 = 0; i3 < a2; i3++) {
                    h a3 = iVar.a(i3);
                    if (a3 instanceof e) {
                        z = true;
                    } else {
                        s h = a3.h();
                        if (!h.equals(sVar) && !h.a(sVar2) && (i != 3 || z)) {
                            aVarArr[i2] = new a(a3.f(), h);
                            i2++;
                            sVar2 = h;
                            z = false;
                        }
                    }
                }
                u uVar = new u(i2);
                for (int i4 = 0; i4 < i2; i4++) {
                    uVar.a(i4, aVarArr[i4]);
                }
                uVar.e();
                return uVar;
            default:
                throw new IllegalArgumentException("bogus howMuch");
        }
    }

    public a a(int i) {
        return (a) d(i);
    }

    public void a(int i, a aVar) {
        a(i, aVar);
    }
}
