package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.a;
import java.util.Collection;

public abstract class as extends ak {
    public as(String str, l lVar, int i) {
        super(str, lVar, i);
    }

    public final int a(x xVar) {
        w wVar = (w) xVar;
        return c(wVar.g() * wVar.b_());
    }

    /* access modifiers changed from: protected */
    public final void a_(a aVar) {
        l e = e();
        int f = f();
        for (x a : a()) {
            a.a(e, aVar);
            aVar.h(f);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public final void c() {
        l e = e();
        b();
        for (x a : a()) {
            a.a(e);
        }
    }

    public final int c_() {
        Collection<? extends x> a = a();
        int size = a.size();
        if (size == 0) {
            return 0;
        }
        return size * ((x) a.iterator().next()).b_();
    }
}
