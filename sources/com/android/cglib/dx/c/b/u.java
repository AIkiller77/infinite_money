package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.d.e;

public final class u extends f {
    private final e a;

    public u(p pVar, s sVar, n nVar, e eVar) {
        super(pVar, sVar, (m) null, nVar);
        if (pVar.b() != 6) {
            throw new IllegalArgumentException("bogus branchingness");
        } else if (eVar == null) {
            throw new NullPointerException("catches == null");
        } else {
            this.a = eVar;
        }
    }

    public static String a(e eVar) {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("catch");
        int a2 = eVar.a();
        for (int i = 0; i < a2; i++) {
            stringBuffer.append(" ");
            stringBuffer.append(eVar.a(i).a_());
        }
        return stringBuffer.toString();
    }

    public void a(f.b bVar) {
        bVar.a(this);
    }

    public String b() {
        return a(this.a);
    }

    public e i() {
        return this.a;
    }
}
