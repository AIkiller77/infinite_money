package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public final class af extends ak {
    private static final Comparator<ag> a = new Comparator<ag>() {
        /* renamed from: a */
        public int compare(ag agVar, ag agVar2) {
            return agVar.a().compareTo(agVar2.a());
        }
    };
    private final ArrayList<ag> b = new ArrayList<>(100);
    private final HashMap<ag, ag> c = new HashMap<>(100);
    private final a d;
    private int e;

    enum a {
        NONE,
        TYPE,
        INSTANCE
    }

    public af(String str, l lVar, int i, a aVar) {
        super(str, lVar, i);
        this.d = aVar;
        this.e = -1;
    }

    public int a(x xVar) {
        return ((ag) xVar).e();
    }

    public Collection<? extends x> a() {
        return this.b;
    }

    public void a(ag agVar) {
        j();
        try {
            if (agVar.f() > f()) {
                throw new IllegalArgumentException("incompatible item alignment");
            }
            this.b.add(agVar);
        } catch (NullPointerException unused) {
            throw new NullPointerException("item == null");
        }
    }

    public void a(com.android.cglib.dx.d.a aVar, y yVar, String str) {
        i();
        TreeMap treeMap = new TreeMap();
        Iterator<ag> it = this.b.iterator();
        while (it.hasNext()) {
            ag next = it.next();
            if (next.a() == yVar) {
                treeMap.put(next.b(), next);
            }
        }
        if (treeMap.size() != 0) {
            aVar.a(0, str);
            for (Map.Entry entry : treeMap.entrySet()) {
                aVar.a(0, ((ag) entry.getValue()).g() + ' ' + ((String) entry.getKey()) + 10);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a_(com.android.cglib.dx.d.a aVar) {
        boolean a2 = aVar.a();
        l e2 = e();
        Iterator<ag> it = this.b.iterator();
        int i = 0;
        boolean z = true;
        while (it.hasNext()) {
            ag next = it.next();
            if (a2) {
                if (z) {
                    z = false;
                } else {
                    aVar.a(0, "\n");
                }
            }
            int f = next.f() - 1;
            int i2 = (f ^ -1) & (i + f);
            if (i != i2) {
                aVar.g(i2 - i);
                i = i2;
            }
            next.a(e2, aVar);
            i += next.b_();
        }
        if (i != this.e) {
            throw new RuntimeException("output size mismatch");
        }
    }

    public <T extends ag> T b(T t) {
        j();
        T t2 = (ag) this.c.get(t);
        if (t2 != null) {
            return t2;
        }
        a((ag) t);
        this.c.put(t, t);
        return t;
    }

    /* access modifiers changed from: protected */
    public void c() {
        l e2 = e();
        int i = 0;
        while (true) {
            int size = this.b.size();
            if (i < size) {
                while (i < size) {
                    this.b.get(i).a(e2);
                    i++;
                }
            } else {
                return;
            }
        }
    }

    public int c_() {
        i();
        return this.e;
    }

    public void d() {
        i();
        switch (this.d) {
            case INSTANCE:
                Collections.sort(this.b);
                break;
            case TYPE:
                Collections.sort(this.b, a);
                break;
        }
        int size = this.b.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            ag agVar = this.b.get(i);
            try {
                int b2 = agVar.b(this, i2);
                if (b2 < i2) {
                    throw new RuntimeException("bogus place() result for " + agVar);
                }
                i2 = agVar.b_() + b2;
                i++;
            } catch (RuntimeException e2) {
                throw g.a(e2, "...while placing " + agVar);
            }
        }
        this.e = i2;
    }
}
