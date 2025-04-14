package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.a.c;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class b extends ag {
    private final c a;
    private final a[] b;

    /* access modifiers changed from: protected */
    public int a(ag agVar) {
        return this.a.compareTo(((b) agVar).a);
    }

    public y a() {
        return y.TYPE_ANNOTATION_SET_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        a.a(this.b);
    }

    public void a(l lVar) {
        af n = lVar.n();
        int length = this.b.length;
        for (int i = 0; i < length; i++) {
            this.b[i] = (a) n.b(this.b[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        boolean a2 = aVar.a();
        int length = this.b.length;
        if (a2) {
            aVar.a(0, g() + " annotation set");
            aVar.a(4, "  size: " + i.a(length));
        }
        aVar.d(length);
        for (int i = 0; i < length; i++) {
            int e = this.b[i].e();
            if (a2) {
                aVar.a(4, "  entries[" + Integer.toHexString(i) + "]: " + i.a(e));
                this.b[i].a(aVar, "    ");
            }
            aVar.d(e);
        }
    }

    public String b() {
        return this.a.toString();
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
