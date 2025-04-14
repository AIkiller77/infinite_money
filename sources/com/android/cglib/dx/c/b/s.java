package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.i;

public final class s {
    public static final s a = new s((v) null, -1, -1);
    private final v b;
    private final int c;
    private final int d;

    public s(v vVar, int i, int i2) {
        if (i < -1) {
            throw new IllegalArgumentException("address < -1");
        } else if (i2 < -1) {
            throw new IllegalArgumentException("line < -1");
        } else {
            this.b = vVar;
            this.c = i;
            this.d = i2;
        }
    }

    public int a() {
        return this.d;
    }

    public boolean a(s sVar) {
        return this.d == sVar.d;
    }

    public boolean b(s sVar) {
        if (this.d != sVar.d) {
            return false;
        }
        if (this.b != sVar.b) {
            return this.b != null && this.b.equals(sVar.b);
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        s sVar = (s) obj;
        return this.c == sVar.c && b(sVar);
    }

    public int hashCode() {
        return this.b.hashCode() + this.c + this.d;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(50);
        if (this.b != null) {
            stringBuffer.append(this.b.a_());
            stringBuffer.append(":");
        }
        if (this.d >= 0) {
            stringBuffer.append(this.d);
        }
        stringBuffer.append('@');
        stringBuffer.append(this.c < 0 ? "????" : i.c(this.c));
        return stringBuffer.toString();
    }
}
