package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.r;
import com.android.cglib.dx.c.c.t;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public abstract class aa extends v {
    private final r a;

    public aa(r rVar) {
        super(rVar.g());
        this.a = rVar;
    }

    public void a(l lVar) {
        super.a(lVar);
        lVar.g().a(j().h().b());
    }

    public final void a(l lVar, a aVar) {
        aq j = lVar.j();
        ao g = lVar.g();
        t h = this.a.h();
        int b = j.b(e());
        int b2 = g.b(h.b());
        int b3 = b(lVar);
        if (aVar.a()) {
            aVar.a(0, h() + ' ' + this.a.a_());
            StringBuilder sb = new StringBuilder();
            sb.append("  class_idx: ");
            sb.append(i.c(b));
            aVar.a(2, sb.toString());
            aVar.a(2, String.format("  %-10s %s", new Object[]{d() + ':', i.c(b3)}));
            aVar.a(4, "  name_idx:  " + i.a(b2));
        }
        aVar.c(b);
        aVar.c(b3);
        aVar.d(b2);
    }

    /* access modifiers changed from: protected */
    public abstract int b(l lVar);

    public int b_() {
        return 8;
    }

    /* access modifiers changed from: protected */
    public abstract String d();

    public final r j() {
        return this.a;
    }
}
