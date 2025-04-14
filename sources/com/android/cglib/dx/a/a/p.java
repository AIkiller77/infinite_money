package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.o;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.c.d.d;
import com.android.cglib.dx.d.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public final class p extends h {
    public static final p a = new p(0);

    public enum a {
        START,
        END_SIMPLY,
        END_REPLACED,
        END_MOVED,
        END_CLOBBERED_BY_PREV,
        END_CLOBBERED_BY_NEXT
    }

    public static class b implements Comparable<b> {
        private final int a;
        private final a b;
        private final m c;
        private final w d;

        public b(int i, a aVar, m mVar) {
            if (i < 0) {
                throw new IllegalArgumentException("address < 0");
            } else if (aVar == null) {
                throw new NullPointerException("disposition == null");
            } else {
                try {
                    if (mVar.g() == null) {
                        throw new NullPointerException("spec.getLocalItem() == null");
                    }
                    this.a = i;
                    this.b = aVar;
                    this.c = mVar;
                    this.d = w.a(mVar.b());
                } catch (NullPointerException unused) {
                    throw new NullPointerException("spec == null");
                }
            }
        }

        public int a() {
            return this.a;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            if (this.a < bVar.a) {
                return -1;
            }
            if (this.a > bVar.a) {
                return 1;
            }
            boolean c2 = c();
            return c2 != bVar.c() ? c2 ? 1 : -1 : this.c.compareTo(bVar.c);
        }

        public b a(a aVar) {
            return aVar == this.b ? this : new b(this.a, aVar, this.c);
        }

        public boolean a(m mVar) {
            return this.c.a(mVar);
        }

        public a b() {
            return this.b;
        }

        public boolean b(b bVar) {
            return a(bVar.c);
        }

        public boolean c() {
            return this.b == a.START;
        }

        public v d() {
            return this.c.g().a();
        }

        public v e() {
            return this.c.g().b();
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && compareTo((b) obj) == 0;
        }

        public w f() {
            return this.d;
        }

        public int g() {
            return this.c.e();
        }

        public m h() {
            return this.c;
        }

        public String toString() {
            return Integer.toHexString(this.a) + " " + this.b + " " + this.c;
        }
    }

    public static class c {
        private final ArrayList<b> a;
        private int b = 0;
        private o c = null;
        private int[] d = null;
        private int e = 0;

        public c(int i) {
            this.a = new ArrayList<>(i);
        }

        private static m a(m mVar) {
            return (mVar == null || mVar.b() != com.android.cglib.dx.c.d.c.j) ? mVar : mVar.a((d) com.android.cglib.dx.c.d.c.o);
        }

        private void a(int i, int i2) {
            boolean z = this.d == null;
            if (i == this.e && !z) {
                return;
            }
            if (i < this.e) {
                throw new RuntimeException("shouldn't happen");
            } else if (z || i2 >= this.d.length) {
                int i3 = i2 + 1;
                o oVar = new o(i3);
                int[] iArr = new int[i3];
                Arrays.fill(iArr, -1);
                if (!z) {
                    oVar.a(this.c);
                    System.arraycopy(this.d, 0, iArr, 0, this.d.length);
                }
                this.c = oVar;
                this.d = iArr;
            }
        }

        private void a(int i, a aVar, m mVar) {
            int e2 = mVar.e();
            this.a.add(new b(i, aVar, mVar));
            if (aVar == a.START) {
                this.c.c(mVar);
                this.d[e2] = -1;
                return;
            }
            this.c.b(mVar);
            this.d[e2] = this.a.size() - 1;
        }

        private void b(int i, a aVar, m mVar) {
            if (aVar == a.START) {
                throw new RuntimeException("shouldn't happen");
            }
            int i2 = this.d[mVar.e()];
            if (i2 >= 0) {
                b bVar = this.a.get(i2);
                if (bVar.a() == i && bVar.h().equals(mVar)) {
                    this.a.set(i2, bVar.a(aVar));
                    this.c.b(mVar);
                    return;
                }
            }
            a(i, mVar, aVar);
        }

        private boolean c(int i, m mVar) {
            boolean z;
            int size = this.a.size() - 1;
            while (true) {
                z = false;
                if (size < 0) {
                    break;
                }
                b bVar = this.a.get(size);
                if (bVar != null) {
                    if (bVar.a() != i) {
                        return false;
                    }
                    if (bVar.a(mVar)) {
                        break;
                    }
                }
                size--;
            }
            this.c.b(mVar);
            b bVar2 = null;
            this.a.set(size, (Object) null);
            this.b++;
            int e2 = mVar.e();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                bVar2 = this.a.get(size);
                if (bVar2 != null && bVar2.h().e() == e2) {
                    z = true;
                    break;
                }
            }
            if (z) {
                this.d[e2] = size;
                if (bVar2.a() == i) {
                    this.a.set(size, bVar2.a(a.END_SIMPLY));
                }
            }
            return true;
        }

        public p a() {
            a(Integer.MAX_VALUE, 0);
            int size = this.a.size();
            int i = size - this.b;
            if (i == 0) {
                return p.a;
            }
            b[] bVarArr = new b[i];
            if (size == i) {
                this.a.toArray(bVarArr);
            } else {
                Iterator<b> it = this.a.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    b next = it.next();
                    if (next != null) {
                        bVarArr[i2] = next;
                        i2++;
                    }
                }
            }
            Arrays.sort(bVarArr);
            p pVar = new p(i);
            for (int i3 = 0; i3 < i; i3++) {
                pVar.a(i3, bVarArr[i3]);
            }
            pVar.e();
            return pVar;
        }

        public void a(int i, m mVar) {
            m a2;
            m a3;
            int e2 = mVar.e();
            m a4 = a(mVar);
            a(i, e2);
            m a5 = this.c.a(e2);
            if (!a4.a(a5)) {
                m a6 = this.c.a(a4);
                if (a6 != null) {
                    b(i, a.END_MOVED, a6);
                }
                int i2 = this.d[e2];
                if (a5 != null) {
                    a(i, a.END_REPLACED, a5);
                } else if (i2 >= 0) {
                    b bVar = this.a.get(i2);
                    if (bVar.a() == i) {
                        if (bVar.a(a4)) {
                            this.a.set(i2, (Object) null);
                            this.b++;
                            this.c.c(a4);
                            this.d[e2] = -1;
                            return;
                        }
                        this.a.set(i2, bVar.a(a.END_REPLACED));
                    }
                }
                if (e2 > 0 && (a3 = this.c.a(e2 - 1)) != null && a3.j()) {
                    b(i, a.END_CLOBBERED_BY_NEXT, a3);
                }
                if (a4.j() && (a2 = this.c.a(e2 + 1)) != null) {
                    b(i, a.END_CLOBBERED_BY_PREV, a2);
                }
                a(i, a.START, a4);
            }
        }

        public void a(int i, m mVar, a aVar) {
            int e2 = mVar.e();
            m a2 = a(mVar);
            a(i, e2);
            if (this.d[e2] < 0 && !c(i, a2)) {
                a(i, aVar, a2);
            }
        }

        public void a(int i, o oVar) {
            int a2 = oVar.a();
            a(i, a2 - 1);
            for (int i2 = 0; i2 < a2; i2++) {
                m a3 = this.c.a(i2);
                m a4 = a(oVar.a(i2));
                if (a3 != null) {
                    if (a4 == null) {
                        b(i, a3);
                    } else if (!a4.a(a3)) {
                        b(i, a3);
                    }
                } else if (a4 == null) {
                }
                a(i, a4);
            }
        }

        public void b(int i, m mVar) {
            a(i, mVar, a.END_SIMPLY);
        }
    }

    public p(int i) {
        super(i);
    }

    public static p a(i iVar) {
        int a2 = iVar.a();
        c cVar = new c(a2);
        for (int i = 0; i < a2; i++) {
            h a3 = iVar.a(i);
            if (a3 instanceof q) {
                cVar.a(a3.f(), ((q) a3).b());
            } else if (a3 instanceof r) {
                cVar.a(a3.f(), ((r) a3).b());
            } else if (a3 instanceof o) {
                cVar.b(a3.f(), ((o) a3).b());
            }
        }
        return cVar.a();
    }

    public b a(int i) {
        return (b) d(i);
    }

    public void a(int i, b bVar) {
        a(i, bVar);
    }
}
