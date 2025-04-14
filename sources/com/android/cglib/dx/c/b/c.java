package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.d.i;
import com.android.cglib.dx.d.k;
import com.android.cglib.dx.d.l;
import com.android.cglib.dx.d.m;

public final class c extends m {
    private int a = -1;

    private static class a implements f.b {
        private int a = 0;

        private void a(f fVar) {
            m f = fVar.f();
            if (f != null) {
                a(f);
            }
            n g = fVar.g();
            int a2 = g.a();
            for (int i = 0; i < a2; i++) {
                a(g.b(i));
            }
        }

        private void a(m mVar) {
            int h = mVar.h();
            if (h > this.a) {
                this.a = h;
            }
        }

        public int a() {
            return this.a;
        }

        public void a(j jVar) {
            a((f) jVar);
        }

        public void a(k kVar) {
            a((f) kVar);
        }

        public void a(t tVar) {
            a((f) tVar);
        }

        public void a(u uVar) {
            a((f) uVar);
        }
    }

    public c(int i) {
        super(i);
    }

    public b a(int i) {
        return (b) d(i);
    }

    public b a(b bVar) {
        int d = bVar.d();
        k c = bVar.c();
        switch (c.a()) {
            case 0:
                return null;
            case 1:
                break;
            default:
                if (d != -1) {
                    return b(d);
                }
                break;
        }
        return b(c.a(0));
    }

    public void a(int i, b bVar) {
        super.a(i, (l) bVar);
        this.a = -1;
    }

    public void a(f.b bVar) {
        int a2 = a();
        for (int i = 0; i < a2; i++) {
            a(i).b().a(bVar);
        }
    }

    public int b() {
        if (this.a == -1) {
            a aVar = new a();
            a((f.b) aVar);
            this.a = aVar.a();
        }
        return this.a;
    }

    public b b(int i) {
        int c = c(i);
        if (c >= 0) {
            return a(c);
        }
        throw new IllegalArgumentException("no such label: " + i.c(i));
    }

    public int c() {
        int a2 = a();
        int i = 0;
        for (int i2 = 0; i2 < a2; i2++) {
            b bVar = (b) e(i2);
            if (bVar != null) {
                i += bVar.b().a();
            }
        }
        return i;
    }
}
