package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class ap extends v {
    public ap(w wVar) {
        super(wVar);
    }

    public y a() {
        return y.TYPE_TYPE_ID_ITEM;
    }

    public void a(l lVar) {
        lVar.g().a(e().g());
    }

    public void a(l lVar, a aVar) {
        v g = e().g();
        int b = lVar.g().b(g);
        if (aVar.a()) {
            aVar.a(0, h() + ' ' + g.a_());
            StringBuilder sb = new StringBuilder();
            sb.append("  descriptor_idx: ");
            sb.append(i.a(b));
            aVar.a(4, sb.toString());
        }
        aVar.d(b);
    }

    public int b_() {
        return 4;
    }
}
