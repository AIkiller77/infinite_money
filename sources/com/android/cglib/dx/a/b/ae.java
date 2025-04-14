package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.d;
import com.android.cglib.dx.d.i;
import java.util.Collection;
import java.util.TreeMap;

public final class ae extends ab {
    private final TreeMap<d, ad> a = new TreeMap<>();

    public ae(l lVar) {
        super("method_ids", lVar);
    }

    public ad a(d dVar) {
        if (dVar == null) {
            throw new NullPointerException("method == null");
        }
        j();
        ad adVar = this.a.get(dVar);
        if (adVar != null) {
            return adVar;
        }
        ad adVar2 = new ad(dVar);
        this.a.put(dVar, adVar2);
        return adVar2;
    }

    public w a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
        i();
        w wVar = this.a.get((d) aVar);
        if (wVar != null) {
            return wVar;
        }
        throw new IllegalArgumentException("not found");
    }

    public Collection<? extends x> a() {
        return this.a.values();
    }

    public void a(com.android.cglib.dx.d.a aVar) {
        i();
        int size = this.a.size();
        int g = size == 0 ? 0 : g();
        if (aVar.a()) {
            aVar.a(4, "method_ids_size: " + i.a(size));
            aVar.a(4, "method_ids_off:  " + i.a(g));
        }
        aVar.d(size);
        aVar.d(g);
    }

    public int b(d dVar) {
        if (dVar == null) {
            throw new NullPointerException("ref == null");
        }
        i();
        ad adVar = this.a.get(dVar);
        if (adVar != null) {
            return adVar.g();
        }
        throw new IllegalArgumentException("not found");
    }
}
