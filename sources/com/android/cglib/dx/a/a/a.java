package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.b;
import com.android.cglib.dx.c.b.c;
import com.android.cglib.dx.c.b.q;
import com.android.cglib.dx.c.b.s;

public final class a {
    private final e[] a;
    private final e[] b;
    private final e[] c;

    public a(q qVar) {
        int d = qVar.a().d();
        this.a = new e[d];
        this.b = new e[d];
        this.c = new e[d];
        a(qVar);
    }

    private void a(q qVar) {
        c a2 = qVar.a();
        int a3 = a2.a();
        for (int i = 0; i < a3; i++) {
            b a4 = a2.a(i);
            int a5 = a4.a();
            this.a[a5] = new e(a4.b().a(0).e());
            s e = a4.f().e();
            this.b[a5] = new e(e);
            this.c[a5] = new e(e);
        }
    }

    public e a(int i) {
        return this.a[i];
    }

    public e a(b bVar) {
        return this.a[bVar.a()];
    }

    public e b(b bVar) {
        return this.b[bVar.a()];
    }

    public e c(b bVar) {
        return this.c[bVar.a()];
    }
}
