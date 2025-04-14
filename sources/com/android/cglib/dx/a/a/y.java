package com.android.cglib.dx.a.a;

import com.android.cglib.dx.a.a.d;
import com.android.cglib.dx.c.b.b;
import com.android.cglib.dx.c.b.q;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.k;
import java.util.ArrayList;
import java.util.HashSet;

public final class y implements b {
    private final q a;
    private final int[] b;
    private final a c;

    public y(q qVar, int[] iArr, a aVar) {
        if (qVar == null) {
            throw new NullPointerException("method == null");
        } else if (iArr == null) {
            throw new NullPointerException("order == null");
        } else if (aVar == null) {
            throw new NullPointerException("addresses == null");
        } else {
            this.a = qVar;
            this.b = iArr;
            this.c = aVar;
        }
    }

    private static c a(b bVar, a aVar) {
        k c2 = bVar.c();
        int a2 = c2.a();
        int d = bVar.d();
        e i = bVar.f().i();
        int a3 = i.a();
        if (a3 == 0) {
            return c.a;
        }
        if ((d != -1 || a2 == a3) && (d == -1 || (a2 == a3 + 1 && d == c2.a(a3)))) {
            int i2 = 0;
            while (true) {
                if (i2 >= a3) {
                    break;
                } else if (i.a(i2).equals(c.o)) {
                    a3 = i2 + 1;
                    break;
                } else {
                    i2++;
                }
            }
            c cVar = new c(a3);
            for (int i3 = 0; i3 < a3; i3++) {
                cVar.a(i3, new w(i.a(i3)), aVar.a(c2.a(i3)).f());
            }
            cVar.e();
            return cVar;
        }
        throw new RuntimeException("shouldn't happen: weird successors list");
    }

    private static d.a a(b bVar, b bVar2, c cVar, a aVar) {
        return new d.a(aVar.b(bVar).f(), aVar.c(bVar2).f(), cVar);
    }

    public static d a(q qVar, int[] iArr, a aVar) {
        com.android.cglib.dx.c.b.c a2 = qVar.a();
        ArrayList arrayList = new ArrayList(r0);
        b bVar = null;
        b bVar2 = null;
        c cVar = c.a;
        for (int b2 : iArr) {
            b b3 = a2.b(b2);
            if (b3.g()) {
                c a3 = a(b3, aVar);
                if (cVar.a() != 0) {
                    if (cVar.equals(a3) && a(bVar, b3, aVar)) {
                        bVar2 = b3;
                    } else if (cVar.a() != 0) {
                        arrayList.add(a(bVar, bVar2, cVar, aVar));
                    }
                }
                bVar = b3;
                bVar2 = bVar;
                cVar = a3;
            }
        }
        if (cVar.a() != 0) {
            arrayList.add(a(bVar, bVar2, cVar, aVar));
        }
        int size = arrayList.size();
        if (size == 0) {
            return d.a;
        }
        d dVar = new d(size);
        for (int i = 0; i < size; i++) {
            dVar.a(i, (d.a) arrayList.get(i));
        }
        dVar.e();
        return dVar;
    }

    private static boolean a(b bVar, b bVar2, a aVar) {
        if (bVar == null) {
            throw new NullPointerException("start == null");
        } else if (bVar2 == null) {
            throw new NullPointerException("end == null");
        } else {
            return aVar.c(bVar2).f() - aVar.b(bVar).f() <= 65535;
        }
    }

    public d a() {
        return a(this.a, this.b, this.c);
    }

    public boolean b() {
        com.android.cglib.dx.c.b.c a2 = this.a.a();
        int a3 = a2.a();
        for (int i = 0; i < a3; i++) {
            if (a2.a(i).f().i().a() != 0) {
                return true;
            }
        }
        return false;
    }

    public HashSet<c> c() {
        HashSet<c> hashSet = new HashSet<>(20);
        com.android.cglib.dx.c.b.c a2 = this.a.a();
        int a3 = a2.a();
        for (int i = 0; i < a3; i++) {
            e i2 = a2.a(i).f().i();
            int a4 = i2.a();
            for (int i3 = 0; i3 < a4; i3++) {
                hashSet.add(i2.a(i3));
            }
        }
        return hashSet;
    }
}
