package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class c extends ag {
    private b a;

    public y a() {
        return y.TYPE_ANNOTATION_SET_REF_ITEM;
    }

    public void a(l lVar) {
        this.a = (b) lVar.d().b(this.a);
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        int e = this.a.e();
        if (aVar.a()) {
            aVar.a(4, "  annotations_off: " + i.a(e));
        }
        aVar.d(e);
    }

    public String b() {
        return this.a.b();
    }
}
