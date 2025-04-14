package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public final class h extends as {
    private final TreeMap<c, g> a = new TreeMap<>();
    private ArrayList<g> b = null;

    public h(l lVar) {
        super("class_defs", lVar, 4);
    }

    private int a(c cVar, int i, int i2) {
        g gVar = this.a.get(cVar);
        if (gVar == null || gVar.f()) {
            return i;
        }
        if (i2 < 0) {
            throw new RuntimeException("class circularity with " + cVar);
        }
        int i3 = i2 - 1;
        w d = gVar.d();
        if (d != null) {
            i = a(d.f(), i, i3);
        }
        e e = gVar.e();
        int a2 = e.a();
        for (int i4 = 0; i4 < a2; i4++) {
            i = a(e.a(i4), i, i3);
        }
        gVar.a(i);
        this.b.add(gVar);
        return i + 1;
    }

    public Collection<? extends x> a() {
        return this.b != null ? this.b : this.a.values();
    }

    public void a(g gVar) {
        try {
            c f = gVar.c().f();
            j();
            if (this.a.get(f) != null) {
                throw new IllegalArgumentException("already added: " + f);
            }
            this.a.put(f, gVar);
        } catch (NullPointerException unused) {
            throw new NullPointerException("clazz == null");
        }
    }

    public void a(a aVar) {
        i();
        int size = this.a.size();
        int g = size == 0 ? 0 : g();
        if (aVar.a()) {
            aVar.a(4, "class_defs_size: " + i.a(size));
            aVar.a(4, "class_defs_off:  " + i.a(g));
        }
        aVar.d(size);
        aVar.d(g);
    }

    /* access modifiers changed from: protected */
    public void b() {
        int size = this.a.size();
        this.b = new ArrayList<>(size);
        int i = 0;
        for (c a2 : this.a.keySet()) {
            i = a(a2, i, size - i);
        }
    }
}
