package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.d.i;
import java.util.Collection;
import java.util.TreeMap;

public final class s extends ab {
    private final TreeMap<j, r> a = new TreeMap<>();

    public s(l lVar) {
        super("field_ids", lVar);
    }

    public r a(j jVar) {
        if (jVar == null) {
            throw new NullPointerException("field == null");
        }
        j();
        r rVar = this.a.get(jVar);
        if (rVar != null) {
            return rVar;
        }
        r rVar2 = new r(jVar);
        this.a.put(jVar, rVar2);
        return rVar2;
    }

    public w a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
        i();
        w wVar = this.a.get((j) aVar);
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
            aVar.a(4, "field_ids_size:  " + i.a(size));
            aVar.a(4, "field_ids_off:   " + i.a(g));
        }
        aVar.d(size);
        aVar.d(g);
    }

    public int b(j jVar) {
        if (jVar == null) {
            throw new NullPointerException("ref == null");
        }
        i();
        r rVar = this.a.get(jVar);
        if (rVar != null) {
            return rVar.g();
        }
        throw new IllegalArgumentException("not found");
    }
}
