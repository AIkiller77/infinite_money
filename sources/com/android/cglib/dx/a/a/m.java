package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.s;
import com.android.cglib.dx.d.a;

public final class m extends aa {
    private x[] a;

    public m(s sVar, n nVar) {
        super(sVar, nVar);
        if (nVar.a() == 0) {
            throw new IllegalArgumentException("registers.size() == 0");
        }
        this.a = null;
    }

    private static x a(com.android.cglib.dx.c.b.m mVar, int i) {
        return h.a(s.a, com.android.cglib.dx.c.b.m.a(i, mVar.b()), mVar);
    }

    private void b() {
        if (this.a == null) {
            n i = i();
            int a2 = i.a();
            this.a = new x[a2];
            int i2 = 0;
            for (int i3 = 0; i3 < a2; i3++) {
                com.android.cglib.dx.c.b.m b = i.b(i3);
                this.a[i3] = a(b, i2);
                i2 += b.i();
            }
        }
    }

    public h a(n nVar) {
        return new m(h(), nVar);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String a(boolean z) {
        n i = i();
        int a2 = i.a();
        StringBuffer stringBuffer = new StringBuffer(100);
        int i2 = 0;
        for (int i3 = 0; i3 < a2; i3++) {
            com.android.cglib.dx.c.b.m b = i.b(i3);
            x a3 = a(b, i2);
            if (i3 != 0) {
                stringBuffer.append(10);
            }
            stringBuffer.append(a3.a(z));
            i2 += b.i();
        }
        return stringBuffer.toString();
    }

    public void a(a aVar) {
        b();
        for (x a2 : this.a) {
            a2.a(aVar);
        }
    }

    public int n() {
        b();
        int i = 0;
        for (x n : this.a) {
            i += n.n();
        }
        return i;
    }
}
