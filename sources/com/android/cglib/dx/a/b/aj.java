package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.d.a;
import com.android.cglib.dx.d.i;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

public final class aj extends as {
    private final TreeMap<a, ai> a = new TreeMap<>();

    public aj(l lVar) {
        super("proto_ids", lVar, 4);
    }

    public ai a(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("prototype == null");
        }
        j();
        ai aiVar = this.a.get(aVar);
        if (aiVar != null) {
            return aiVar;
        }
        ai aiVar2 = new ai(aVar);
        this.a.put(aVar, aiVar2);
        return aiVar2;
    }

    public Collection<? extends x> a() {
        return this.a.values();
    }

    public int b(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("prototype == null");
        }
        i();
        ai aiVar = this.a.get(aVar);
        if (aiVar != null) {
            return aiVar.g();
        }
        throw new IllegalArgumentException("not found");
    }

    /* access modifiers changed from: protected */
    public void b() {
        Iterator<? extends x> it = a().iterator();
        int i = 0;
        while (it.hasNext()) {
            ((ai) it.next()).a(i);
            i++;
        }
    }

    public void b(com.android.cglib.dx.d.a aVar) {
        i();
        int size = this.a.size();
        int g = size == 0 ? 0 : g();
        if (size > 65536) {
            throw new UnsupportedOperationException("too many proto ids");
        }
        if (aVar.a()) {
            aVar.a(4, "proto_ids_size:  " + i.a(size));
            aVar.a(4, "proto_ids_off:   " + i.a(g));
        }
        aVar.d(size);
        aVar.d(g);
    }
}
