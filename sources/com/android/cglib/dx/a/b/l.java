package com.android.cglib.dx.a.b;

import com.android.cglib.dx.a.b;
import com.android.cglib.dx.a.b.af;
import com.android.cglib.dx.c.c.i;
import com.android.cglib.dx.c.c.j;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.c.c.w;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.d;
import com.android.cglib.dx.d.g;
import java.io.Writer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public final class l {
    private b a;
    private final af b = new af("word_data", this, 4, af.a.TYPE);
    private final af c = new af((String) null, this, 4, af.a.NONE);
    private final af d = new af("map", this, 4, af.a.NONE);
    private final af e = new af("string_data", this, 1, af.a.INSTANCE);
    private final ao f = new ao(this);
    private final aq g = new aq(this);
    private final aj h = new aj(this);
    private final s i = new s(this);
    private final ae j = new ae(this);
    private final h k = new h(this);
    private final af l = new af((String) null, this, 1, af.a.NONE);
    private final af m = new af("byte_data", this, 1, af.a.TYPE);
    private final u n = new u(this);
    private final ak[] o = {this.n, this.f, this.g, this.h, this.i, this.j, this.k, this.b, this.c, this.e, this.m, this.l, this.d};
    private int p = -1;

    /* renamed from: q  reason: collision with root package name */
    private int f12q = 79;

    public l(b bVar) {
        this.a = bVar;
    }

    private d a(boolean z, boolean z2) {
        this.k.h();
        this.l.h();
        this.b.h();
        this.m.h();
        this.j.h();
        this.i.h();
        this.h.h();
        this.c.h();
        this.g.h();
        this.f.h();
        this.e.h();
        this.n.h();
        int length = this.o.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            ak akVar = this.o[i3];
            int b2 = akVar.b(i4);
            if (b2 < i4) {
                throw new RuntimeException("bogus placement for section " + i3);
            }
            try {
                if (akVar == this.d) {
                    z.a(this.o, this.d);
                    this.d.h();
                }
                if (akVar instanceof af) {
                    ((af) akVar).d();
                }
                i4 = akVar.c_() + b2;
                i3++;
            } catch (RuntimeException e2) {
                throw g.a(e2, "...while writing section " + i3);
            }
        }
        this.p = i4;
        byte[] bArr = new byte[this.p];
        d dVar = new d(bArr);
        if (z) {
            dVar.a(this.f12q, z2);
        }
        while (i2 < length) {
            try {
                ak akVar2 = this.o[i2];
                int g2 = akVar2.g() - dVar.g();
                if (g2 < 0) {
                    throw new g("excess write of " + (-g2));
                }
                dVar.g(akVar2.g() - dVar.g());
                akVar2.c((a) dVar);
                i2++;
            } catch (RuntimeException e3) {
                g gVar = e3 instanceof g ? (g) e3 : new g((Throwable) e3);
                gVar.a("...while writing section " + i2);
                throw gVar;
            }
        }
        if (dVar.g() != this.p) {
            throw new RuntimeException("foreshortened write");
        }
        a(bArr);
        b(bArr);
        if (z) {
            this.b.a(dVar, y.TYPE_CODE_ITEM, "\nmethod code index:\n\n");
            q().a((a) dVar);
            dVar.h();
        }
        return dVar;
    }

    private static void a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr, 32, bArr.length - 32);
            try {
                int digest = instance.digest(bArr, 12, 20);
                if (digest != 20) {
                    throw new RuntimeException("unexpected digest write: " + digest + " bytes");
                }
            } catch (DigestException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static void b(byte[] bArr) {
        Adler32 adler32 = new Adler32();
        adler32.update(bArr, 12, bArr.length - 12);
        int value = (int) adler32.getValue();
        bArr[8] = (byte) value;
        bArr[9] = (byte) (value >> 8);
        bArr[10] = (byte) (value >> 16);
        bArr[11] = (byte) (value >> 24);
    }

    public b a() {
        return this.a;
    }

    public void a(g gVar) {
        this.k.a(gVar);
    }

    /* access modifiers changed from: package-private */
    public void a(com.android.cglib.dx.c.c.a aVar) {
        s sVar;
        j f2;
        if (aVar instanceof v) {
            this.f.a((v) aVar);
        } else if (aVar instanceof w) {
            this.g.a((w) aVar);
        } else if (aVar instanceof com.android.cglib.dx.c.c.d) {
            this.j.a((com.android.cglib.dx.c.c.d) aVar);
        } else {
            if (aVar instanceof j) {
                sVar = this.i;
                f2 = (j) aVar;
            } else if (aVar instanceof i) {
                sVar = this.i;
                f2 = ((i) aVar).f();
            } else if (aVar == null) {
                throw new NullPointerException("cst == null");
            } else {
                return;
            }
            sVar.a(f2);
        }
    }

    public byte[] a(Writer writer, boolean z) {
        boolean z2 = writer != null;
        d a2 = a(z2, z);
        if (z2) {
            a2.a(writer);
        }
        return a2.e();
    }

    /* access modifiers changed from: package-private */
    public int b() {
        if (this.p >= 0) {
            return this.p;
        }
        throw new RuntimeException("file size not yet known");
    }

    /* access modifiers changed from: package-private */
    public w b(com.android.cglib.dx.c.c.a aVar) {
        if (aVar instanceof v) {
            return this.f.a(aVar);
        }
        if (aVar instanceof w) {
            return this.g.a(aVar);
        }
        if (aVar instanceof com.android.cglib.dx.c.c.d) {
            return this.j.a(aVar);
        }
        if (aVar instanceof j) {
            return this.i.a(aVar);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public af c() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public af d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public af e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public af f() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public ao g() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public h h() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public af i() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public aq j() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public aj k() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public s l() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public ae m() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public af n() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public ak o() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public ak p() {
        return this.d;
    }

    public al q() {
        al alVar = new al();
        for (ak a2 : this.o) {
            alVar.a(a2);
        }
        return alVar;
    }
}
