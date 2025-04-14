package com.android.cglib.dx.a.b;

import android.support.v4.view.InputDeviceCompat;
import com.android.cglib.dx.a.a.p;
import com.android.cglib.dx.a.a.u;
import com.android.cglib.dx.c.c.s;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.a;
import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.d.d;
import com.android.cglib.dx.d.g;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public final class j {
    private final u a;
    private final p b;
    private final d c;
    private final l d;
    private final int e;
    private final int f;
    private final a g;
    private final boolean h;
    private int i = 0;
    private int j = 1;
    private com.android.cglib.dx.d.a k;
    private PrintWriter l;
    private String m;
    private boolean n;
    private final p.b[] o;

    public j(u uVar, p pVar, l lVar, int i2, int i3, boolean z, s sVar) {
        this.a = uVar;
        this.b = pVar;
        this.d = lVar;
        this.g = sVar.f();
        this.h = z;
        this.e = i2;
        this.f = i3;
        this.c = new d();
        this.o = new p.b[i3];
    }

    private int a(int i2) {
        int a2 = this.b.a();
        while (i2 < a2 && this.b.a(i2).a() == this.i) {
            int i3 = i2 + 1;
            p.b a3 = this.b.a(i2);
            int g2 = a3.g();
            p.b bVar = this.o[g2];
            if (a3 != bVar) {
                this.o[g2] = a3;
                if (a3.c()) {
                    if (bVar == null || !a3.b(bVar)) {
                        c(a3);
                    } else if (bVar.c()) {
                        throw new RuntimeException("shouldn't happen");
                    } else {
                        b(a3);
                    }
                } else if (a3.b() != p.a.END_REPLACED) {
                    e(a3);
                }
            }
            i2 = i3;
        }
        return i2;
    }

    private static int a(int i2, int i3) {
        if (i2 >= -4 && i2 <= 10) {
            return (i2 - -4) + (i3 * 15) + 10;
        }
        throw new RuntimeException("Parameter out of range");
    }

    private int a(int i2, ArrayList<u.a> arrayList) {
        int size = arrayList.size();
        while (i2 < size && arrayList.get(i2).a() == this.i) {
            a(arrayList.get(i2));
            i2++;
        }
        return i2;
    }

    private String a(p.b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("v");
        sb.append(bVar.g());
        sb.append(' ');
        v d2 = bVar.d();
        sb.append(d2 == null ? "null" : d2.a_());
        sb.append(' ');
        w f2 = bVar.f();
        sb.append(f2 == null ? "null" : f2.a_());
        v e2 = bVar.e();
        if (e2 != null) {
            sb.append(' ');
            sb.append(e2.a_());
        }
        return sb.toString();
    }

    private void a(int i2, String str) {
        if (this.m != null) {
            str = this.m + str;
        }
        if (this.k != null) {
            com.android.cglib.dx.d.a aVar = this.k;
            if (!this.n) {
                i2 = 0;
            }
            aVar.a(i2, str);
        }
        if (this.l != null) {
            this.l.println(str);
        }
    }

    private void a(u.a aVar) {
        int a2 = aVar.b().a();
        int a3 = aVar.a();
        int i2 = a2 - this.j;
        int i3 = a3 - this.i;
        if (i3 < 0) {
            throw new RuntimeException("Position entries must be in ascending address order");
        }
        if (i2 < -4 || i2 > 10) {
            b(i2);
            i2 = 0;
        }
        int a4 = a(i2, i3);
        if ((a4 & InputDeviceCompat.SOURCE_ANY) > 0) {
            c(i3);
            a4 = a(i2, 0);
            if ((a4 & InputDeviceCompat.SOURCE_ANY) > 0) {
                b(i2);
                a4 = a(0, 0);
                i3 = 0;
                i2 = 0;
            } else {
                i3 = 0;
            }
        }
        this.c.b(a4);
        this.j += i2;
        this.i += i3;
        if (this.k != null || this.l != null) {
            a(1, String.format("%04x: line %d", new Object[]{Integer.valueOf(this.i), Integer.valueOf(this.j)}));
        }
    }

    private void a(v vVar) {
        if (vVar == null || this.d == null) {
            this.c.e(0);
        } else {
            this.c.e(this.d.g().b(vVar) + 1);
        }
    }

    private void a(w wVar) {
        if (wVar == null || this.d == null) {
            this.c.e(0);
        } else {
            this.c.e(this.d.j().b(wVar) + 1);
        }
    }

    private void a(ArrayList<u.a> arrayList, ArrayList<p.b> arrayList2) {
        p.b bVar;
        boolean z = (this.k == null && this.l == null) ? false : true;
        int g2 = this.c.g();
        if (arrayList.size() > 0) {
            this.j = arrayList.get(0).b().a();
        }
        this.c.e(this.j);
        if (z) {
            a(this.c.g() - g2, "line_start: " + this.j);
        }
        int d2 = d();
        b c2 = this.g.c();
        int a2 = c2.a();
        if (!this.h) {
            Iterator<p.b> it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                p.b next = it.next();
                if (d2 == next.g()) {
                    this.o[d2] = next;
                    break;
                }
            }
            d2++;
        }
        int g3 = this.c.g();
        this.c.e(a2);
        if (z) {
            a(this.c.g() - g3, String.format("parameters_size: %04x", new Object[]{Integer.valueOf(a2)}));
        }
        int i2 = d2;
        for (int i3 = 0; i3 < a2; i3++) {
            c b2 = c2.b(i3);
            int g4 = this.c.g();
            Iterator<p.b> it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    bVar = null;
                    break;
                }
                bVar = it2.next();
                if (i2 == bVar.g()) {
                    if (bVar.e() != null) {
                        a((v) null);
                    } else {
                        a(bVar.d());
                    }
                    this.o[i2] = bVar;
                }
            }
            if (bVar == null) {
                a((v) null);
            }
            if (z) {
                a(this.c.g() - g4, "parameter " + ((bVar == null || bVar.e() != null) ? "<unnamed>" : bVar.d().a_()) + " " + "v" + i2);
            }
            i2 += b2.g();
        }
        for (p.b bVar2 : this.o) {
            if (!(bVar2 == null || bVar2.e() == null)) {
                d(bVar2);
            }
        }
    }

    private void b(int i2) {
        int g2 = this.c.g();
        this.c.b(2);
        this.c.f(i2);
        this.j += i2;
        if (this.k != null || this.l != null) {
            a(this.c.g() - g2, String.format("line = %d", new Object[]{Integer.valueOf(this.j)}));
        }
    }

    private void b(p.b bVar) {
        int g2 = this.c.g();
        this.c.b(6);
        d(bVar.g());
        if (this.k != null || this.l != null) {
            a(this.c.g() - g2, String.format("%04x: +local restart %s", new Object[]{Integer.valueOf(this.i), a(bVar)}));
        }
    }

    private byte[] b() {
        ArrayList<u.a> c2 = c();
        a(c2, e());
        this.c.b(7);
        int i2 = 0;
        if (!(this.k == null && this.l == null)) {
            a(1, String.format("%04x: prologue end", new Object[]{Integer.valueOf(this.i)}));
        }
        int size = c2.size();
        int a2 = this.b.a();
        int i3 = 0;
        while (true) {
            i2 = a(i2);
            i3 = a(i3, c2);
            int a3 = i2 < a2 ? this.b.a(i2).a() : Integer.MAX_VALUE;
            int a4 = i3 < size ? c2.get(i3).a() : Integer.MAX_VALUE;
            int min = Math.min(a4, a3);
            if (!(min == Integer.MAX_VALUE || (min == this.e && a3 == Integer.MAX_VALUE && a4 == Integer.MAX_VALUE))) {
                if (min == a4) {
                    a(c2.get(i3));
                    i3++;
                } else {
                    c(min - this.i);
                }
            }
        }
        f();
        return this.c.f();
    }

    private ArrayList<u.a> c() {
        int a2 = this.a == null ? 0 : this.a.a();
        ArrayList<u.a> arrayList = new ArrayList<>(a2);
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList.add(this.a.a(i2));
        }
        Collections.sort(arrayList, new Comparator<u.a>() {
            /* renamed from: a */
            public int compare(u.a aVar, u.a aVar2) {
                return aVar.a() - aVar2.a();
            }

            public boolean equals(Object obj) {
                return obj == this;
            }
        });
        return arrayList;
    }

    private void c(int i2) {
        int g2 = this.c.g();
        this.c.b(1);
        this.c.e(i2);
        this.i += i2;
        if (this.k != null || this.l != null) {
            a(this.c.g() - g2, String.format("%04x: advance pc", new Object[]{Integer.valueOf(this.i)}));
        }
    }

    private void c(p.b bVar) {
        if (bVar.e() != null) {
            d(bVar);
            return;
        }
        int g2 = this.c.g();
        this.c.b(3);
        d(bVar.g());
        a(bVar.d());
        a(bVar.f());
        if (this.k != null || this.l != null) {
            a(this.c.g() - g2, String.format("%04x: +local %s", new Object[]{Integer.valueOf(this.i), a(bVar)}));
        }
    }

    private int d() {
        return (this.f - this.g.c().b()) - (this.h ^ true ? 1 : 0);
    }

    private void d(int i2) {
        if (i2 < 0) {
            throw new RuntimeException("Signed value where unsigned required: " + i2);
        }
        this.c.e(i2);
    }

    private void d(p.b bVar) {
        int g2 = this.c.g();
        this.c.b(4);
        d(bVar.g());
        a(bVar.d());
        a(bVar.f());
        a(bVar.e());
        if (this.k != null || this.l != null) {
            a(this.c.g() - g2, String.format("%04x: +localx %s", new Object[]{Integer.valueOf(this.i), a(bVar)}));
        }
    }

    private ArrayList<p.b> e() {
        ArrayList<p.b> arrayList = new ArrayList<>(this.g.c().a());
        int d2 = d();
        BitSet bitSet = new BitSet(this.f - d2);
        int a2 = this.b.a();
        for (int i2 = 0; i2 < a2; i2++) {
            p.b a3 = this.b.a(i2);
            int g2 = a3.g();
            if (g2 >= d2) {
                int i3 = g2 - d2;
                if (!bitSet.get(i3)) {
                    bitSet.set(i3);
                    arrayList.add(a3);
                }
            }
        }
        Collections.sort(arrayList, new Comparator<p.b>() {
            /* renamed from: a */
            public int compare(p.b bVar, p.b bVar2) {
                return bVar.g() - bVar2.g();
            }

            public boolean equals(Object obj) {
                return obj == this;
            }
        });
        return arrayList;
    }

    private void e(p.b bVar) {
        int g2 = this.c.g();
        this.c.b(5);
        this.c.e(bVar.g());
        if (this.k != null || this.l != null) {
            a(this.c.g() - g2, String.format("%04x: -local %s", new Object[]{Integer.valueOf(this.i), a(bVar)}));
        }
    }

    private void f() {
        this.c.b(0);
        if (this.k != null || this.l != null) {
            a(1, "end sequence");
        }
    }

    public byte[] a() {
        try {
            return b();
        } catch (IOException e2) {
            throw g.a(e2, "...while encoding debug info");
        }
    }

    public byte[] a(String str, PrintWriter printWriter, com.android.cglib.dx.d.a aVar, boolean z) {
        this.m = str;
        this.l = printWriter;
        this.k = aVar;
        this.n = z;
        return a();
    }
}
