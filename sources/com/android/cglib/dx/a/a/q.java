package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.o;
import com.android.cglib.dx.c.b.s;

public final class q extends ab {
    private final o a;

    public q(s sVar, o oVar) {
        super(sVar);
        if (oVar == null) {
            throw new NullPointerException("locals == null");
        }
        this.a = oVar;
    }

    public h a(n nVar) {
        return new q(h(), this.a);
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.a.toString();
    }

    /* access modifiers changed from: protected */
    public String a(boolean z) {
        int b = this.a.b();
        int a2 = this.a.a();
        StringBuffer stringBuffer = new StringBuffer((b * 40) + 100);
        stringBuffer.append("local-snapshot");
        for (int i = 0; i < a2; i++) {
            m a3 = this.a.a(i);
            if (a3 != null) {
                stringBuffer.append("\n  ");
                stringBuffer.append(r.a(a3));
            }
        }
        return stringBuffer.toString();
    }

    public o b() {
        return this.a;
    }

    public h d(int i) {
        return new q(h(), this.a.b(i));
    }
}
