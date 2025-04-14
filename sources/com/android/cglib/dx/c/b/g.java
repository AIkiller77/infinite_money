package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.d.h;

public final class g extends h {
    public g(int i) {
        super(i);
    }

    public f a(int i) {
        return (f) d(i);
    }

    public void a(int i, f fVar) {
        a(i, fVar);
    }

    public void a(f.b bVar) {
        int a = a();
        for (int i = 0; i < a; i++) {
            a(i).a(bVar);
        }
    }

    public f b() {
        return a(a() - 1);
    }
}
