package com.android.cglib.dx.a.a;

import com.android.cglib.dx.a.a.g;
import com.android.cglib.dx.a.b;
import com.android.cglib.dx.c.b.h;
import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.o;
import com.android.cglib.dx.c.c.a;
import com.android.cglib.dx.c.c.r;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.f;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

public final class t {
    private final b a;
    private final int b;
    private ArrayList<h> c;
    private boolean d = false;
    private boolean e = false;
    private int f = -1;

    public t(b bVar, int i, int i2) {
        this.a = bVar;
        this.b = i2;
        this.c = new ArrayList<>(i);
    }

    private j a(h hVar, j jVar) {
        while (jVar != null && !jVar.c().b(hVar)) {
            jVar = k.a(jVar, this.a);
        }
        return jVar;
    }

    private static void a(f fVar, g.a aVar) {
        int a2;
        a b2 = fVar.b();
        int a3 = aVar.a(b2);
        if (a3 >= 0) {
            fVar.a(a3);
        }
        if ((b2 instanceof r) && (a2 = aVar.a(((r) b2).g())) >= 0) {
            fVar.b(a2);
        }
    }

    private static void a(HashSet<a> hashSet, h hVar) {
        if (hVar instanceof f) {
            hashSet.add(((f) hVar).b());
        } else if (hVar instanceof q) {
            o b2 = ((q) hVar).b();
            int b3 = b2.b();
            for (int i = 0; i < b3; i++) {
                a(hashSet, b2.a(i));
            }
        } else if (hVar instanceof r) {
            a(hashSet, ((r) hVar).b());
        }
    }

    private static void a(HashSet<a> hashSet, m mVar) {
        if (mVar != null) {
            h g = mVar.g();
            v a2 = g.a();
            v b2 = g.b();
            c b3 = mVar.b();
            if (b3 != c.j) {
                hashSet.add(w.a(b3));
            }
            if (a2 != null) {
                hashSet.add(a2);
            }
            if (b2 != null) {
                hashSet.add(b2);
            }
        }
    }

    private void a(j[] jVarArr) {
        int i = this.f < 0 ? 0 : this.f;
        while (true) {
            int b2 = b(jVarArr);
            if (i >= b2) {
                this.f = i;
                return;
            }
            int i2 = b2 - i;
            int size = this.c.size();
            for (int i3 = 0; i3 < size; i3++) {
                h hVar = this.c.get(i3);
                if (!(hVar instanceof e)) {
                    this.c.set(i3, hVar.d(i2));
                }
            }
            i = b2;
        }
    }

    private static boolean a(m mVar) {
        return (mVar == null || mVar.g().a() == null) ? false : true;
    }

    private int b(j[] jVarArr) {
        int size = this.c.size();
        int i = this.f;
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = this.c.get(i2);
            j jVar = jVarArr[i2];
            j a2 = a(hVar, jVar);
            if (a2 == null) {
                int a3 = hVar.a(d(hVar).c().c(hVar));
                if (a3 > i) {
                    i = a3;
                }
            } else if (jVar == a2) {
            }
            jVarArr[i2] = a2;
        }
        return i;
    }

    private static boolean b(h hVar) {
        if (!(hVar instanceof q)) {
            return (hVar instanceof r) && a(((r) hVar).b());
        }
        o b2 = ((q) hVar).b();
        int b3 = b2.b();
        for (int i = 0; i < b3; i++) {
            if (a(b2.a(i))) {
                return true;
            }
        }
    }

    private void c(h hVar) {
        if (!this.d && hVar.h().a() >= 0) {
            this.d = true;
        }
        if (!this.e && b(hVar)) {
            this.e = true;
        }
    }

    private void c(j[] jVarArr) {
        if (this.f == 0) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                h hVar = this.c.get(i);
                j g = hVar.g();
                j jVar = jVarArr[i];
                if (g != jVar) {
                    this.c.set(i, hVar.a(jVar));
                }
            }
            return;
        }
        this.c = d(jVarArr);
    }

    private j d(h hVar) {
        j a2 = a(hVar.k(), hVar.g());
        if (a2 != null) {
            return a2;
        }
        throw new f("No expanded opcode for " + hVar);
    }

    private ArrayList<h> d(j[] jVarArr) {
        h hVar;
        int size = this.c.size();
        ArrayList<h> arrayList = new ArrayList<>(size * 2);
        for (int i = 0; i < size; i++) {
            h hVar2 = this.c.get(i);
            j g = hVar2.g();
            j jVar = jVarArr[i];
            h hVar3 = null;
            if (jVar != null) {
                hVar = null;
            } else {
                jVar = d(hVar2);
                BitSet c2 = jVar.c().c(hVar2);
                h b2 = hVar2.b(c2);
                hVar = hVar2.c(c2);
                hVar2 = hVar2.d(c2);
                hVar3 = b2;
            }
            if (hVar3 != null) {
                arrayList.add(hVar3);
            }
            if (jVar != g) {
                hVar2 = hVar2.a(jVar);
            }
            arrayList.add(hVar2);
            if (hVar != null) {
                arrayList.add(hVar);
            }
        }
        return arrayList;
    }

    private j[] e() {
        int size = this.c.size();
        j[] jVarArr = new j[size];
        for (int i = 0; i < size; i++) {
            jVarArr[i] = this.c.get(i).g();
        }
        return jVarArr;
    }

    private void f() {
        do {
            g();
        } while (h());
    }

    private void g() {
        int size = this.c.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = this.c.get(i2);
            hVar.c(i);
            i += hVar.n();
        }
    }

    private boolean h() {
        int size = this.c.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            h hVar = this.c.get(i);
            if (hVar instanceof z) {
                j g = hVar.g();
                z zVar = (z) hVar;
                if (!g.c().a(zVar)) {
                    if (g.b() == 40) {
                        j a2 = a(hVar, g);
                        if (a2 == null) {
                            throw new UnsupportedOperationException("method too long");
                        }
                        this.c.set(i, hVar.a(a2));
                    } else {
                        try {
                            int i2 = i + 1;
                            e eVar = (e) this.c.get(i2);
                            this.c.set(i, new z(k.P, zVar.h(), n.a, zVar.b()));
                            this.c.add(i, zVar.a(eVar));
                            size++;
                            i = i2;
                        } catch (IndexOutOfBoundsException unused) {
                            throw new IllegalStateException("unpaired TargetInsn (dangling)");
                        } catch (ClassCastException unused2) {
                            throw new IllegalStateException("unpaired TargetInsn");
                        }
                    }
                    z = true;
                } else {
                    continue;
                }
            }
            i++;
        }
        return z;
    }

    public void a(int i, e eVar) {
        int size = (this.c.size() - i) - 1;
        try {
            this.c.set(size, ((z) this.c.get(size)).a(eVar));
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("too few instructions");
        } catch (ClassCastException unused2) {
            throw new IllegalArgumentException("non-reversible instruction");
        }
    }

    public void a(g.a aVar) {
        Iterator<h> it = this.c.iterator();
        while (it.hasNext()) {
            h next = it.next();
            if (next instanceof f) {
                a((f) next, aVar);
            }
        }
    }

    public void a(h hVar) {
        this.c.add(hVar);
        c(hVar);
    }

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.e;
    }

    public HashSet<a> c() {
        HashSet<a> hashSet = new HashSet<>(20);
        Iterator<h> it = this.c.iterator();
        while (it.hasNext()) {
            a(hashSet, it.next());
        }
        return hashSet;
    }

    public i d() {
        if (this.f >= 0) {
            throw new UnsupportedOperationException("already processed");
        }
        j[] e2 = e();
        a(e2);
        c(e2);
        f();
        return i.a(this.c, this.f + this.b);
    }
}
