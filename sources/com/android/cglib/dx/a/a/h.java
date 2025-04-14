package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.c.b.n;
import com.android.cglib.dx.c.b.s;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.util.BitSet;

public abstract class h {
    private int a;
    private final j b;
    private final s c;
    private final n d;

    public h(j jVar, s sVar, n nVar) {
        if (jVar == null) {
            throw new NullPointerException("opcode == null");
        } else if (sVar == null) {
            throw new NullPointerException("position == null");
        } else if (nVar == null) {
            throw new NullPointerException("registers == null");
        } else {
            this.a = -1;
            this.b = jVar;
            this.c = sVar;
            this.d = nVar;
        }
    }

    public static x a(s sVar, m mVar, m mVar2) {
        boolean z = true;
        if (mVar.i() != 1) {
            z = false;
        }
        boolean j = mVar.b().j();
        int e = mVar.e();
        return new x((mVar2.e() | e) < 16 ? j ? k.i : z ? k.c : k.f : e < 256 ? j ? k.j : z ? k.d : k.g : j ? k.k : z ? k.e : k.h, sVar, n.a(mVar, mVar2));
    }

    public final int a(BitSet bitSet) {
        int j = j();
        int a2 = this.d.a();
        int i = 0;
        int i2 = (j == 0 || bitSet.get(0)) ? 0 : this.d.b(0).i();
        while (j < a2) {
            if (!bitSet.get(j)) {
                i += this.d.b(j).i();
            }
            j++;
        }
        return Math.max(i, i2);
    }

    public abstract h a(j jVar);

    public abstract h a(n nVar);

    /* access modifiers changed from: protected */
    public abstract String a();

    public final String a(String str, int i, boolean z) {
        String a2 = a(z);
        if (a2 == null) {
            return null;
        }
        String str2 = str + l() + ": ";
        int length = str2.length();
        return com.android.cglib.dx.d.s.a(str2, length, "", a2, i == 0 ? a2.length() : i - length);
    }

    /* access modifiers changed from: protected */
    public abstract String a(boolean z);

    public abstract void a(a aVar);

    public h b(BitSet bitSet) {
        n nVar = this.d;
        boolean z = bitSet.get(0);
        if (j()) {
            bitSet.set(0);
        }
        n a2 = nVar.a(bitSet);
        if (j()) {
            bitSet.set(0, z);
        }
        if (a2.a() == 0) {
            return null;
        }
        return new m(this.c, a2);
    }

    public h c(BitSet bitSet) {
        if (!j() || bitSet.get(0)) {
            return null;
        }
        m b2 = this.d.b(0);
        return a(this.c, b2, b2.b(0));
    }

    public final void c(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("address < 0");
        }
        this.a = i;
    }

    public abstract h d(int i);

    public h d(BitSet bitSet) {
        return a(this.d.a(0, j(), bitSet));
    }

    public final boolean e() {
        return this.a >= 0;
    }

    public final int f() {
        if (this.a >= 0) {
            return this.a;
        }
        throw new RuntimeException("address not yet known");
    }

    public final j g() {
        return this.b;
    }

    public final s h() {
        return this.c;
    }

    public final n i() {
        return this.d;
    }

    public final boolean j() {
        return this.b.d();
    }

    public h k() {
        return a(this.d.a(0, j(), (BitSet) null));
    }

    public final String l() {
        if (this.a == -1) {
            return i.a(System.identityHashCode(this));
        }
        return String.format("%04x", new Object[]{Integer.valueOf(this.a)});
    }

    public final int m() {
        return f() + n();
    }

    public abstract int n();

    public final String toString() {
        boolean z;
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(l());
        stringBuffer.append(' ');
        stringBuffer.append(this.c);
        stringBuffer.append(": ");
        stringBuffer.append(this.b.e());
        if (this.d.a() != 0) {
            stringBuffer.append(this.d.b(" ", ", ", (String) null));
            z = true;
        } else {
            z = false;
        }
        String a2 = a();
        if (a2 != null) {
            if (z) {
                stringBuffer.append(',');
            }
            stringBuffer.append(' ');
            stringBuffer.append(a2);
        }
        return stringBuffer.toString();
    }
}
