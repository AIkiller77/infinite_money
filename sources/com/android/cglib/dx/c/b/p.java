package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.d.b;
import com.android.cglib.dx.c.d.c;
import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.i;

public final class p {
    private final int a;
    private final c b;
    private final e c;
    private final e d;
    private final int e;
    private final boolean f;
    private final String g;

    public p(int i, c cVar, e eVar, int i2, String str) {
        this(i, cVar, eVar, b.a, i2, false, str);
    }

    public p(int i, c cVar, e eVar, e eVar2, int i2, boolean z, String str) {
        if (cVar == null) {
            throw new NullPointerException("result == null");
        } else if (eVar == null) {
            throw new NullPointerException("sources == null");
        } else if (eVar2 == null) {
            throw new NullPointerException("exceptions == null");
        } else if (i2 < 1 || i2 > 6) {
            throw new IllegalArgumentException("bogus branchingness");
        } else if (eVar2.a() == 0 || i2 == 6) {
            this.a = i;
            this.b = cVar;
            this.c = eVar;
            this.d = eVar2;
            this.e = i2;
            this.f = z;
            this.g = str;
        } else {
            throw new IllegalArgumentException("exceptions / branchingness mismatch");
        }
    }

    public p(int i, c cVar, e eVar, e eVar2, String str) {
        this(i, cVar, eVar, eVar2, 6, false, str);
    }

    public p(int i, c cVar, e eVar, String str) {
        this(i, cVar, eVar, b.a, 1, false, str);
    }

    public p(int i, e eVar, e eVar2) {
        this(i, c.i, eVar, eVar2, 6, true, (String) null);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.e;
    }

    public boolean c() {
        return this.f;
    }

    public boolean d() {
        int i = this.a;
        if (i == 14 || i == 16) {
            return true;
        }
        switch (i) {
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return false;
        }
    }

    public String e() {
        return this.g != null ? this.g : toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.a == pVar.a && this.e == pVar.e && this.b == pVar.b && this.c.equals(pVar.c) && this.d.equals(pVar.d);
    }

    public final boolean f() {
        return this.d.a() != 0;
    }

    public int hashCode() {
        return (((((((this.a * 31) + this.e) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(40);
        stringBuffer.append("Rop{");
        stringBuffer.append(l.a(this.a));
        if (this.b != c.i) {
            stringBuffer.append(" ");
            stringBuffer.append(this.b);
        } else {
            stringBuffer.append(" .");
        }
        stringBuffer.append(" <-");
        int a2 = this.c.a();
        if (a2 == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i = 0; i < a2; i++) {
                stringBuffer.append(' ');
                stringBuffer.append(this.c.a(i));
            }
        }
        if (this.f) {
            stringBuffer.append(" call");
        }
        int a3 = this.d.a();
        if (a3 != 0) {
            stringBuffer.append(" throws");
            for (int i2 = 0; i2 < a3; i2++) {
                stringBuffer.append(' ');
                if (this.d.a(i2) == c.r) {
                    stringBuffer.append("<any>");
                } else {
                    stringBuffer.append(this.d.a(i2));
                }
            }
        } else {
            switch (this.e) {
                case 1:
                    str = " flows";
                    break;
                case 2:
                    str = " returns";
                    break;
                case 3:
                    str = " gotos";
                    break;
                case 4:
                    str = " ifs";
                    break;
                case 5:
                    str = " switches";
                    break;
                default:
                    str = " " + i.e(this.e);
                    break;
            }
            stringBuffer.append(str);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
