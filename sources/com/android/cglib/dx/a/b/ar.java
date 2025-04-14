package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;

public final class ar extends ag {
    private final e a;

    public ar(e eVar) {
        super(4, (eVar.a() * 2) + 4);
        this.a = eVar;
    }

    /* access modifiers changed from: protected */
    public int a(ag agVar) {
        return b.a(this.a, ((ar) agVar).a);
    }

    public y a() {
        return y.TYPE_TYPE_LIST;
    }

    public void a(l lVar) {
        aq j = lVar.j();
        int a2 = this.a.a();
        for (int i = 0; i < a2; i++) {
            j.a(this.a.a(i));
        }
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        aq j = lVar.j();
        int a2 = this.a.a();
        if (aVar.a()) {
            aVar.a(0, g() + " type_list");
            aVar.a(4, "  size: " + i.a(a2));
            for (int i = 0; i < a2; i++) {
                c a3 = this.a.a(i);
                int b = j.b(a3);
                aVar.a(2, "  " + i.c(b) + " // " + a3.a_());
            }
        }
        aVar.d(a2);
        for (int i2 = 0; i2 < a2; i2++) {
            aVar.c(j.b(this.a.a(i2)));
        }
    }

    public String b() {
        throw new RuntimeException("unsupported");
    }

    public e c() {
        return this.a;
    }

    public int hashCode() {
        return b.b(this.a);
    }
}
