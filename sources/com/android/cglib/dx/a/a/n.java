package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.c.m;
import com.android.cglib.dx.c.c.o;
import com.android.cglib.dx.c.c.p;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.util.BitSet;

public abstract class n {
    public static boolean a = true;

    protected static String a(com.android.cglib.dx.c.b.n nVar) {
        int a2 = nVar.a();
        StringBuffer stringBuffer = new StringBuffer((a2 * 5) + 2);
        stringBuffer.append('{');
        for (int i = 0; i < a2; i++) {
            if (i != 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(nVar.b(i).k());
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    protected static String a(p pVar) {
        String a_;
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append('#');
        if (pVar instanceof m) {
            a_ = "null";
        } else {
            stringBuffer.append(pVar.e());
            stringBuffer.append(' ');
            a_ = pVar.a_();
        }
        stringBuffer.append(a_);
        return stringBuffer.toString();
    }

    protected static String a(p pVar, int i) {
        String str;
        StringBuffer stringBuffer = new StringBuffer(20);
        stringBuffer.append("#");
        long h = pVar instanceof o ? ((o) pVar).h() : (long) pVar.g();
        if (i == 4) {
            str = i.f((int) h);
        } else if (i == 8) {
            str = i.e((int) h);
        } else if (i == 16) {
            str = i.c((int) h);
        } else if (i == 32) {
            str = i.a((int) h);
        } else if (i != 64) {
            throw new RuntimeException("shouldn't happen");
        } else {
            str = i.a(h);
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    protected static short a(int i, int i2) {
        if ((i & 255) != i) {
            throw new IllegalArgumentException("low out of range 0..255");
        } else if ((i2 & 255) == i2) {
            return (short) (i | (i2 << 8));
        } else {
            throw new IllegalArgumentException("high out of range 0..255");
        }
    }

    protected static short a(int i, int i2, int i3, int i4) {
        if ((i & 15) != i) {
            throw new IllegalArgumentException("n0 out of range 0..15");
        } else if ((i2 & 15) != i2) {
            throw new IllegalArgumentException("n1 out of range 0..15");
        } else if ((i3 & 15) != i3) {
            throw new IllegalArgumentException("n2 out of range 0..15");
        } else if ((i4 & 15) == i4) {
            return (short) (i | (i2 << 4) | (i3 << 8) | (i4 << 12));
        } else {
            throw new IllegalArgumentException("n3 out of range 0..15");
        }
    }

    protected static short a(h hVar, int i) {
        if ((i & 255) != i) {
            throw new IllegalArgumentException("arg out of range 0..255");
        }
        int a2 = hVar.g().a();
        if ((a2 & 255) == a2) {
            return (short) (a2 | (i << 8));
        }
        throw new IllegalArgumentException("opcode out of range 0..255");
    }

    protected static void a(a aVar, short s) {
        aVar.c(s);
    }

    protected static void a(a aVar, short s, int i) {
        a(aVar, s, (short) i, (short) (i >> 16));
    }

    protected static void a(a aVar, short s, int i, short s2) {
        a(aVar, s, (short) i, (short) (i >> 16), s2);
    }

    protected static void a(a aVar, short s, int i, short s2, short s3) {
        a(aVar, s, (short) i, (short) (i >> 16), s2, s3);
    }

    protected static void a(a aVar, short s, long j) {
        a(aVar, s, (short) ((int) j), (short) ((int) (j >> 16)), (short) ((int) (j >> 32)), (short) ((int) (j >> 48)));
    }

    protected static void a(a aVar, short s, short s2) {
        aVar.c(s);
        aVar.c(s2);
    }

    protected static void a(a aVar, short s, short s2, short s3) {
        aVar.c(s);
        aVar.c(s2);
        aVar.c(s3);
    }

    protected static void a(a aVar, short s, short s2, short s3, short s4) {
        aVar.c(s);
        aVar.c(s2);
        aVar.c(s3);
        aVar.c(s4);
    }

    protected static void a(a aVar, short s, short s2, short s3, short s4, short s5) {
        aVar.c(s);
        aVar.c(s2);
        aVar.c(s3);
        aVar.c(s4);
        aVar.c(s5);
    }

    protected static boolean a(int i) {
        return i >= -8 && i <= 7;
    }

    protected static int b(int i, int i2) {
        if ((i & 15) != i) {
            throw new IllegalArgumentException("low out of range 0..15");
        } else if ((i2 & 15) == i2) {
            return i | (i2 << 4);
        } else {
            throw new IllegalArgumentException("high out of range 0..15");
        }
    }

    protected static String b(com.android.cglib.dx.c.b.n nVar) {
        String str;
        int a2 = nVar.a();
        StringBuilder sb = new StringBuilder(30);
        sb.append("{");
        switch (a2) {
            case 0:
                sb.append("}");
                return sb.toString();
            case 1:
                str = nVar.b(0).k();
                break;
            default:
                com.android.cglib.dx.c.b.m b = nVar.b(a2 - 1);
                if (b.i() == 2) {
                    b = b.c(1);
                }
                sb.append(nVar.b(0).k());
                sb.append("..");
                str = b.k();
                break;
        }
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    protected static boolean b(int i) {
        return i == (i & 15);
    }

    protected static boolean c(int i) {
        return ((byte) i) == i;
    }

    protected static boolean c(com.android.cglib.dx.c.b.n nVar) {
        int a2 = nVar.a();
        if (a2 < 2) {
            return true;
        }
        int e = nVar.b(0).e();
        for (int i = 0; i < a2; i++) {
            com.android.cglib.dx.c.b.m b = nVar.b(i);
            if (b.e() != e) {
                return false;
            }
            e += b.i();
        }
        return true;
    }

    protected static String d(h hVar) {
        int c = ((z) hVar).c();
        return c == ((char) c) ? i.c(c) : i.a(c);
    }

    protected static boolean d(int i) {
        return i == (i & 255);
    }

    protected static String e(h hVar) {
        int d = ((z) hVar).d();
        return d == ((short) d) ? i.h(d) : i.g(d);
    }

    protected static boolean e(int i) {
        return ((short) i) == i;
    }

    protected static String f(h hVar) {
        com.android.cglib.dx.c.c.a b = ((f) hVar).b();
        return b instanceof v ? ((v) b).f() : b.a_();
    }

    protected static boolean f(int i) {
        return i == (65535 & i);
    }

    protected static String g(h hVar) {
        f fVar = (f) hVar;
        if (!fVar.d()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(20);
        int c = fVar.c();
        sb.append(fVar.b().e());
        sb.append('@');
        sb.append(c < 65536 ? i.c(c) : i.a(c));
        return sb.toString();
    }

    protected static short h(h hVar) {
        int a2 = hVar.g().a();
        if (a2 >= 255 && a2 <= 65535) {
            return (short) a2;
        }
        throw new IllegalArgumentException("extended opcode out of range 255..65535");
    }

    public abstract int a();

    public abstract String a(h hVar);

    public final String a(h hVar, boolean z) {
        String e = hVar.g().e();
        String a2 = a(hVar);
        String b = b(hVar, z);
        StringBuilder sb = new StringBuilder(100);
        sb.append(e);
        if (a2.length() != 0) {
            sb.append(' ');
            sb.append(a2);
        }
        if (b.length() != 0) {
            sb.append(" // ");
            sb.append(b);
        }
        return sb.toString();
    }

    public abstract void a(a aVar, h hVar);

    public boolean a(z zVar) {
        return false;
    }

    public abstract String b(h hVar, boolean z);

    public abstract boolean b(h hVar);

    public BitSet c(h hVar) {
        return new BitSet();
    }
}
