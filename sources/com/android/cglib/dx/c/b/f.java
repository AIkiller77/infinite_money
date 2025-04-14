package com.android.cglib.dx.c.b;

import com.android.cglib.dx.c.d.e;
import com.android.cglib.dx.d.r;

public abstract class f implements r {
    private final p a;
    private final s b;
    private final m c;
    private final n d;

    public static class a implements b {
        public void a(j jVar) {
        }

        public void a(k kVar) {
        }

        public void a(t tVar) {
        }

        public void a(u uVar) {
        }
    }

    public interface b {
        void a(j jVar);

        void a(k kVar);

        void a(t tVar);

        void a(u uVar);
    }

    public f(p pVar, s sVar, m mVar, n nVar) {
        if (pVar == null) {
            throw new NullPointerException("opcode == null");
        } else if (sVar == null) {
            throw new NullPointerException("position == null");
        } else if (nVar == null) {
            throw new NullPointerException("sources == null");
        } else {
            this.a = pVar;
            this.b = sVar;
            this.c = mVar;
            this.d = nVar;
        }
    }

    /* access modifiers changed from: protected */
    public final String a(String str) {
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append("Insn{");
        stringBuffer.append(this.b);
        stringBuffer.append(' ');
        stringBuffer.append(this.a);
        if (str != null) {
            stringBuffer.append(' ');
            stringBuffer.append(str);
        }
        stringBuffer.append(" :: ");
        if (this.c != null) {
            stringBuffer.append(this.c);
            stringBuffer.append(" <- ");
        }
        stringBuffer.append(this.d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public abstract void a(b bVar);

    public String a_() {
        return b(b());
    }

    public String b() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final String b(String str) {
        String a_;
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append(this.b);
        stringBuffer.append(": ");
        stringBuffer.append(this.a.e());
        if (str != null) {
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")");
        }
        if (this.c == null) {
            a_ = " .";
        } else {
            stringBuffer.append(" ");
            a_ = this.c.a_();
        }
        stringBuffer.append(a_);
        stringBuffer.append(" <-");
        int a2 = this.d.a();
        if (a2 == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i = 0; i < a2; i++) {
                stringBuffer.append(" ");
                stringBuffer.append(this.d.b(i).a_());
            }
        }
        return stringBuffer.toString();
    }

    public final p d() {
        return this.a;
    }

    public final s e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final m f() {
        return this.c;
    }

    public final n g() {
        return this.d;
    }

    public final boolean h() {
        return this.a.f();
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public abstract e i();

    public String toString() {
        return a(b());
    }
}
