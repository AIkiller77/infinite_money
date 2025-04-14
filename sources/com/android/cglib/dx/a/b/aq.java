package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.i;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public final class aq extends as {
    private final TreeMap<c, ap> a = new TreeMap<>();

    public aq(l lVar) {
        super("type_ids", lVar, 4);
    }

    public ap a(w wVar) {
        if (wVar == null) {
            throw new NullPointerException("type == null");
        }
        j();
        c f = wVar.f();
        ap apVar = this.a.get(f);
        if (apVar != null) {
            return apVar;
        }
        ap apVar2 = new ap(wVar);
        this.a.put(f, apVar2);
        return apVar2;
    }

    public ap a(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        }
        j();
        ap apVar = this.a.get(cVar);
        if (apVar != null) {
            return apVar;
        }
        ap apVar2 = new ap(new w(cVar));
        this.a.put(cVar, apVar2);
        return apVar2;
    }

    public w a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
        i();
        w wVar = this.a.get(((w) aVar).f());
        if (wVar != null) {
            return wVar;
        }
        throw new IllegalArgumentException("not found: " + aVar);
    }

    public Collection<? extends x> a() {
        return this.a.values();
    }

    public int b(w wVar) {
        if (wVar != null) {
            return b(wVar.f());
        }
        throw new NullPointerException("type == null");
    }

    public int b(c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        }
        i();
        ap apVar = this.a.get(cVar);
        if (apVar != null) {
            return apVar.g();
        }
        throw new IllegalArgumentException("not found: " + cVar);
    }

    /* access modifiers changed from: protected */
    public void b() {
        Iterator<? extends x> it = a().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((ap) it.next()).a(i);
            i++;
        }
    }

    public void b(com.android.cglib.dx.d.a aVar) {
        i();
        int size = this.a.size();
        int g = size == 0 ? 0 : g();
        if (size > 65536) {
            throw new UnsupportedOperationException("too many type ids");
        }
        if (aVar.a()) {
            aVar.a(4, "type_ids_size:   " + i.a(size));
            aVar.a(4, "type_ids_off:    " + i.a(g));
        }
        aVar.d(size);
        aVar.d(g);
    }
}
