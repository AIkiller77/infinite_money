package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.h;
import java.util.BitSet;

public final class n extends h implements e {
    public static final n a = new n(0);

    public n(int i) {
        super(i);
    }

    public static n a(m mVar) {
        n nVar = new n(1);
        nVar.a(0, mVar);
        return nVar;
    }

    public static n a(m mVar, m mVar2) {
        n nVar = new n(2);
        nVar.a(0, mVar);
        nVar.a(1, mVar2);
        return nVar;
    }

    public static n a(m mVar, m mVar2, m mVar3) {
        n nVar = new n(3);
        nVar.a(0, mVar);
        nVar.a(1, mVar2);
        nVar.a(2, mVar3);
        return nVar;
    }

    public n a(int i, boolean z, BitSet bitSet) {
        int a2 = a();
        if (a2 == 0) {
            return this;
        }
        n nVar = new n(a2);
        boolean z2 = z;
        int i2 = i;
        for (int i3 = 0; i3 < a2; i3++) {
            m mVar = (m) d(i3);
            boolean z3 = true;
            if (bitSet != null && bitSet.get(i3)) {
                z3 = false;
            }
            if (z3) {
                nVar.a(i3, mVar.b(i2));
                if (!z2) {
                    i2 += mVar.i();
                }
            } else {
                nVar.a(i3, mVar);
            }
            if (z2) {
                z2 = false;
            }
        }
        if (f()) {
            nVar.e();
        }
        return nVar;
    }

    public n a(BitSet bitSet) {
        int a2 = a() - bitSet.cardinality();
        if (a2 == 0) {
            return a;
        }
        n nVar = new n(a2);
        int i = 0;
        for (int i2 = 0; i2 < a(); i2++) {
            if (!bitSet.get(i2)) {
                nVar.a(i, d(i2));
                i++;
            }
        }
        if (f()) {
            nVar.e();
        }
        return nVar;
    }

    public c a(int i) {
        return b(i).b().b();
    }

    public void a(int i, m mVar) {
        a(i, mVar);
    }

    public int b() {
        int a2 = a();
        int i = 0;
        for (int i2 = 0; i2 < a2; i2++) {
            i += a(i2).g();
        }
        return i;
    }

    public m b(int i) {
        return (m) d(i);
    }

    public n b(m mVar) {
        int a2 = a();
        n nVar = new n(a2 + 1);
        int i = 0;
        while (i < a2) {
            int i2 = i + 1;
            nVar.a(i2, d(i));
            i = i2;
        }
        nVar.a(0, mVar);
        if (f()) {
            nVar.e();
        }
        return nVar;
    }

    public n c(int i) {
        int a2 = a();
        if (a2 == 0) {
            return this;
        }
        n nVar = new n(a2);
        for (int i2 = 0; i2 < a2; i2++) {
            m mVar = (m) d(i2);
            if (mVar != null) {
                nVar.a(i2, mVar.c(i));
            }
        }
        if (f()) {
            nVar.e();
        }
        return nVar;
    }
}
