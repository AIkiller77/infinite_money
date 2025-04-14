package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.c;
import com.android.cglib.dx.c.c.p;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.c.y;
import com.android.cglib.dx.d.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public final class f extends ag {
    private final w a;
    private final ArrayList<n> b;
    private final HashMap<n, a> c;
    private final ArrayList<n> d;
    private final ArrayList<p> e;
    private final ArrayList<p> f;
    private c g;
    private byte[] h;

    public f(w wVar) {
        super(1, -1);
        if (wVar == null) {
            throw new NullPointerException("thisClass == null");
        }
        this.a = wVar;
        this.b = new ArrayList<>(20);
        this.c = new HashMap<>(40);
        this.d = new ArrayList<>(20);
        this.e = new ArrayList<>(20);
        this.f = new ArrayList<>(20);
        this.g = null;
    }

    private static void a(l lVar, com.android.cglib.dx.d.a aVar, String str, int i) {
        if (aVar.a()) {
            aVar.a(String.format("  %-21s %08x", new Object[]{str + "_size:", Integer.valueOf(i)}));
        }
        aVar.e(i);
    }

    private static void a(l lVar, com.android.cglib.dx.d.a aVar, String str, ArrayList<? extends o> arrayList) {
        int size = arrayList.size();
        if (size != 0) {
            if (aVar.a()) {
                aVar.a(0, "  " + str + ":");
            }
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i = ((o) arrayList.get(i2)).a(lVar, aVar, i, i2);
            }
        }
    }

    private void b(l lVar, com.android.cglib.dx.d.a aVar) {
        boolean a2 = aVar.a();
        if (a2) {
            aVar.a(0, g() + " class data for " + this.a.a_());
        }
        a(lVar, aVar, "static_fields", this.b.size());
        a(lVar, aVar, "instance_fields", this.d.size());
        a(lVar, aVar, "direct_methods", this.e.size());
        a(lVar, aVar, "virtual_methods", this.f.size());
        a(lVar, aVar, "static_fields", (ArrayList<? extends o>) this.b);
        a(lVar, aVar, "instance_fields", (ArrayList<? extends o>) this.d);
        a(lVar, aVar, "direct_methods", (ArrayList<? extends o>) this.e);
        a(lVar, aVar, "virtual_methods", (ArrayList<? extends o>) this.f);
        if (a2) {
            aVar.c();
        }
    }

    private c h() {
        Collections.sort(this.b);
        int size = this.b.size();
        while (size > 0) {
            a aVar = this.c.get(this.b.get(size - 1));
            if (aVar instanceof p) {
                if (((p) aVar).h() != 0) {
                    break;
                }
            } else if (aVar != null) {
                break;
            }
            size--;
        }
        if (size == 0) {
            return null;
        }
        c.a aVar2 = new c.a(size);
        for (int i = 0; i < size; i++) {
            n nVar = this.b.get(i);
            a aVar3 = this.c.get(nVar);
            if (aVar3 == null) {
                aVar3 = y.a(nVar.b().b());
            }
            aVar2.a(i, aVar3);
        }
        aVar2.e();
        return new c(aVar2);
    }

    public y a() {
        return y.TYPE_CLASS_DATA_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        d dVar = new d();
        b(akVar.e(), dVar);
        this.h = dVar.f();
        a(this.h.length);
    }

    public void a(l lVar) {
        if (!this.b.isEmpty()) {
            d();
            Iterator<n> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().a(lVar);
            }
        }
        if (!this.d.isEmpty()) {
            Collections.sort(this.d);
            Iterator<n> it2 = this.d.iterator();
            while (it2.hasNext()) {
                it2.next().a(lVar);
            }
        }
        if (!this.e.isEmpty()) {
            Collections.sort(this.e);
            Iterator<p> it3 = this.e.iterator();
            while (it3.hasNext()) {
                it3.next().a(lVar);
            }
        }
        if (!this.f.isEmpty()) {
            Collections.sort(this.f);
            Iterator<p> it4 = this.f.iterator();
            while (it4.hasNext()) {
                it4.next().a(lVar);
            }
        }
    }

    public void a(n nVar) {
        if (nVar == null) {
            throw new NullPointerException("field == null");
        }
        this.d.add(nVar);
    }

    public void a(n nVar, a aVar) {
        if (nVar == null) {
            throw new NullPointerException("field == null");
        } else if (this.g != null) {
            throw new UnsupportedOperationException("static fields already sorted");
        } else {
            this.b.add(nVar);
            this.c.put(nVar, aVar);
        }
    }

    public void a(p pVar) {
        if (pVar == null) {
            throw new NullPointerException("method == null");
        }
        this.e.add(pVar);
    }

    public void a_(l lVar, com.android.cglib.dx.d.a aVar) {
        if (aVar.a()) {
            b(lVar, aVar);
        } else {
            aVar.a(this.h);
        }
    }

    public String b() {
        return toString();
    }

    public void b(p pVar) {
        if (pVar == null) {
            throw new NullPointerException("method == null");
        }
        this.f.add(pVar);
    }

    public boolean c() {
        return this.b.isEmpty() && this.d.isEmpty() && this.e.isEmpty() && this.f.isEmpty();
    }

    public c d() {
        if (this.g == null && this.b.size() != 0) {
            this.g = h();
        }
        return this.g;
    }
}
