package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.c;
import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.b.i;
import com.android.cglib.dx.c.b.j;
import com.android.cglib.dx.c.b.k;
import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.p;
import com.android.cglib.dx.c.b.q;
import com.android.cglib.dx.c.b.s;
import com.android.cglib.dx.c.b.t;
import com.android.cglib.dx.c.b.u;
import com.android.cglib.dx.c.c.l;

public final class w {
    private final com.android.cglib.dx.a.b a;
    /* access modifiers changed from: private */
    public final q b;
    private final int c;
    private final i d;
    /* access modifiers changed from: private */
    public final a e;
    private final s f;
    private final b g;
    /* access modifiers changed from: private */
    public final int h;
    private int[] i = null;
    /* access modifiers changed from: private */
    public final int j;
    /* access modifiers changed from: private */
    public boolean k;

    private class a extends b {
        private i c;

        public a(s sVar, i iVar) {
            super(sVar);
            this.c = iVar;
        }

        public void a(f fVar) {
            m a2 = this.c.a(fVar);
            if (a2 != null) {
                a((h) new r(fVar.e(), a2));
            }
        }

        public void a(j jVar) {
            super.a(jVar);
            a((f) jVar);
        }

        public void a(k kVar) {
            super.a(kVar);
            a((f) kVar);
        }

        public void a(t tVar) {
            super.a(tVar);
            a((f) tVar);
        }

        public void a(u uVar) {
            super.a(uVar);
            a((f) uVar);
        }
    }

    private class b implements f.b {
        private final s a;
        private com.android.cglib.dx.c.b.b c;
        private e d;

        public b(s sVar) {
            this.a = sVar;
        }

        private m a() {
            int d2 = this.c.d();
            if (d2 < 0) {
                return null;
            }
            f a2 = w.this.b.a().b(d2).b().a(0);
            if (a2.d().a() != 56) {
                return null;
            }
            return a2.f();
        }

        /* access modifiers changed from: protected */
        public void a(h hVar) {
            this.a.a(hVar);
        }

        public void a(com.android.cglib.dx.c.b.b bVar, e eVar) {
            this.c = bVar;
            this.d = eVar;
        }

        public void a(j jVar) {
            h hVar;
            s e = jVar.e();
            j a2 = v.a(jVar);
            p d2 = jVar.d();
            int a3 = d2.a();
            if (d2.b() != 1) {
                throw new RuntimeException("shouldn't happen");
            }
            if (a3 != 3) {
                hVar = new f(a2, e, w.b((f) jVar), jVar.c());
            } else if (!w.this.k) {
                m f = jVar.f();
                hVar = new x(a2, e, n.a(f, m.a((w.this.h - w.this.j) + ((l) jVar.c()).e_(), f.b())));
            } else {
                return;
            }
            a(hVar);
        }

        public void a(k kVar) {
            h hVar;
            p d2 = kVar.d();
            if (d2.a() != 54 && d2.a() != 56) {
                s e = kVar.e();
                j a2 = v.a(kVar);
                int b2 = d2.b();
                if (b2 != 6) {
                    switch (b2) {
                        case 1:
                        case 2:
                            break;
                        case 3:
                            return;
                        case 4:
                            hVar = new z(a2, e, w.b((f) kVar), w.this.e.a(this.c.c().a(1)));
                            break;
                        default:
                            throw new RuntimeException("shouldn't happen");
                    }
                }
                hVar = new x(a2, e, w.b((f) kVar));
                a(hVar);
            }
        }

        public void a(t tVar) {
            s e = tVar.e();
            j a2 = v.a(tVar);
            p d2 = tVar.d();
            com.android.cglib.dx.c.c.a c2 = tVar.c();
            if (d2.b() != 6) {
                throw new RuntimeException("shouldn't happen");
            }
            a((h) this.d);
            if (d2.c()) {
                a((h) new f(a2, e, tVar.g(), c2));
                return;
            }
            m a3 = a();
            n a4 = w.b(tVar, a3);
            boolean z = true;
            boolean z2 = a2.d() || d2.a() == 43;
            if (a3 == null) {
                z = false;
            }
            if (z2 != z) {
                throw new RuntimeException("Insn with result/move-result-pseudo mismatch " + tVar);
            }
            a((d2.a() != 41 || a2.a() == 35) ? new f(a2, e, a4, c2) : new x(a2, e, a4));
        }

        public void a(u uVar) {
            s e = uVar.e();
            j a2 = v.a(uVar);
            if (uVar.d().b() != 6) {
                throw new RuntimeException("shouldn't happen");
            }
            m a3 = a();
            if (a2.d() != (a3 != null)) {
                throw new RuntimeException("Insn with result/move-result-pseudo mismatch" + uVar);
            }
            a((h) this.d);
            a((h) new x(a2, e, w.b(uVar, a3)));
        }
    }

    private w(q qVar, int i2, i iVar, int i3, com.android.cglib.dx.a.b bVar) {
        this.a = bVar;
        this.b = qVar;
        this.c = i2;
        this.d = iVar;
        this.e = new a(qVar);
        this.j = i3;
        this.k = a(qVar, i3);
        c a2 = qVar.a();
        int a3 = a2.a();
        int i4 = a3 * 3;
        int c2 = a2.c() + i4;
        c2 = iVar != null ? c2 + a3 + iVar.a() : c2;
        this.h = a2.b() + (this.k ? 0 : this.j);
        this.f = new s(bVar, c2, i4, this.h);
        this.g = iVar != null ? new a(this.f, iVar) : new b(this.f);
    }

    private g a() {
        c();
        b();
        return new g(this.c, this.f.a(), new y(this.b, this.i, this.e));
    }

    public static g a(q qVar, int i2, i iVar, int i3, com.android.cglib.dx.a.b bVar) {
        return new w(qVar, i2, iVar, i3, bVar).a();
    }

    private void a(com.android.cglib.dx.c.b.b bVar, int i2) {
        e a2 = this.e.a(bVar);
        this.f.a(a2);
        if (this.d != null) {
            this.f.a(new q(a2.h(), this.d.a(bVar)));
        }
        this.g.a(bVar, this.e.b(bVar));
        bVar.b().a((f.b) this.g);
        this.f.a(this.e.c(bVar));
        int d2 = bVar.d();
        f f2 = bVar.f();
        if (d2 >= 0 && d2 != i2) {
            if (f2.d().b() == 4 && bVar.e() == i2) {
                this.f.a(1, this.e.a(d2));
                return;
            }
            this.f.a(new z(k.P, f2.e(), n.a, this.e.a(d2)));
        }
    }

    private static boolean a(q qVar, final int i2) {
        final boolean[] zArr = {true};
        final int b2 = qVar.a().b();
        qVar.a().a((f.b) new f.a() {
            public void a(j jVar) {
                if (jVar.d().a() == 3) {
                    zArr[0] = zArr[0] && (b2 - i2) + ((l) jVar.c()).e_() == jVar.f().e();
                }
            }
        });
        return zArr[0];
    }

    /* access modifiers changed from: private */
    public static n b(f fVar) {
        return b(fVar, fVar.f());
    }

    /* access modifiers changed from: private */
    public static n b(f fVar, m mVar) {
        n g2 = fVar.g();
        if (fVar.d().d() && g2.a() == 2 && mVar.e() == g2.b(1).e()) {
            g2 = n.a(g2.b(1), g2.b(0));
        }
        return mVar == null ? g2 : g2.b(mVar);
    }

    private void b() {
        c a2 = this.b.a();
        int[] iArr = this.i;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            a(a2.b(iArr[i2]), i3 == iArr.length ? -1 : iArr[i3]);
            i2 = i3;
        }
    }

    private void c() {
        int i2;
        c a2 = this.b.a();
        int a3 = a2.a();
        int d2 = a2.d();
        int[] a4 = com.android.cglib.dx.d.b.a(d2);
        int[] a5 = com.android.cglib.dx.d.b.a(d2);
        for (int i3 = 0; i3 < a3; i3++) {
            com.android.cglib.dx.d.b.b(a4, a2.a(i3).a());
        }
        int[] iArr = new int[a3];
        int b2 = this.b.b();
        int i4 = 0;
        while (b2 != -1) {
            while (true) {
                com.android.cglib.dx.d.k a6 = this.b.a(i2);
                int a7 = a6.a();
                int i5 = 0;
                while (i5 < a7) {
                    int a8 = a6.a(i5);
                    if (com.android.cglib.dx.d.b.a(a5, a8)) {
                        break;
                    } else if (com.android.cglib.dx.d.b.a(a4, a8) && a2.b(a8).d() == i2) {
                        com.android.cglib.dx.d.b.b(a5, a8);
                        b2 = a8;
                    } else {
                        i5++;
                    }
                }
                break;
            }
            while (i2 != -1) {
                com.android.cglib.dx.d.b.c(a4, i2);
                com.android.cglib.dx.d.b.c(a5, i2);
                iArr[i4] = i2;
                i4++;
                com.android.cglib.dx.c.b.b b3 = a2.b(i2);
                com.android.cglib.dx.c.b.b a9 = a2.a(b3);
                if (a9 == null) {
                    break;
                }
                int a10 = a9.a();
                int d3 = b3.d();
                if (com.android.cglib.dx.d.b.a(a4, a10)) {
                    i2 = a10;
                } else if (d3 == a10 || d3 < 0 || !com.android.cglib.dx.d.b.a(a4, d3)) {
                    com.android.cglib.dx.d.k c2 = b3.c();
                    int a11 = c2.a();
                    int i6 = 0;
                    while (true) {
                        if (i6 >= a11) {
                            i2 = -1;
                            break;
                        }
                        int a12 = c2.a(i6);
                        if (com.android.cglib.dx.d.b.a(a4, a12)) {
                            i2 = a12;
                            break;
                        }
                        i6++;
                    }
                } else {
                    i2 = d3;
                }
            }
            b2 = com.android.cglib.dx.d.b.d(a4, 0);
        }
        if (i4 != a3) {
            throw new RuntimeException("shouldn't happen");
        }
        this.i = iArr;
    }
}
