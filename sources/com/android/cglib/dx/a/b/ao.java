package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.i;
import java.util.Collection;
import java.util.TreeMap;

public final class ao extends as {
    private final TreeMap<v, an> a = new TreeMap<>();

    public ao(l lVar) {
        super("string_ids", lVar, 4);
    }

    public an a(an anVar) {
        if (anVar == null) {
            throw new NullPointerException("string == null");
        }
        j();
        v c = anVar.c();
        an anVar2 = this.a.get(c);
        if (anVar2 != null) {
            return anVar2;
        }
        this.a.put(c, anVar);
        return anVar;
    }

    public an a(v vVar) {
        return a(new an(vVar));
    }

    public w a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
        i();
        w wVar = this.a.get((v) aVar);
        if (wVar != null) {
            return wVar;
        }
        throw new IllegalArgumentException("not found");
    }

    public Collection<? extends x> a() {
        return this.a.values();
    }

    public int b(v vVar) {
        if (vVar == null) {
            throw new NullPointerException("string == null");
        }
        i();
        an anVar = this.a.get(vVar);
        if (anVar != null) {
            return anVar.g();
        }
        throw new IllegalArgumentException("not found");
    }

    /* access modifiers changed from: protected */
    public void b() {
        int i = 0;
        for (an a2 : this.a.values()) {
            a2.a(i);
            i++;
        }
    }

    public void b(com.android.cglib.dx.d.a aVar) {
        i();
        int size = this.a.size();
        int g = size == 0 ? 0 : g();
        if (aVar.a()) {
            aVar.a(4, "string_ids_size: " + i.a(size));
            aVar.a(4, "string_ids_off:  " + i.a(g));
        }
        aVar.d(size);
        aVar.d(g);
    }
}
