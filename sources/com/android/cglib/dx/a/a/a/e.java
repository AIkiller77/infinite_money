package com.android.cglib.dx.a.a.a;

import com.android.cglib.dx.a.a.h;
import com.android.cglib.dx.a.a.n;
import com.android.cglib.dx.a.a.x;
import com.android.cglib.dx.c.b.m;
import com.android.cglib.dx.d.a;
import java.util.BitSet;

public final class e extends n {
    public static final n b = new e();

    private e() {
    }

    public int a() {
        return 1;
    }

    public String a(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        int a = i.a();
        return i.b(a - 2).k() + ", " + i.b(a - 1).k();
    }

    public void a(a aVar, h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        int a = i.a();
        a(aVar, a(hVar, b(i.b(a - 2).e(), i.b(a - 1).e())));
    }

    public String b(h hVar, boolean z) {
        return "";
    }

    public boolean b(h hVar) {
        m mVar;
        m mVar2;
        if (!(hVar instanceof x)) {
            return false;
        }
        com.android.cglib.dx.c.b.n i = hVar.i();
        switch (i.a()) {
            case 2:
                mVar2 = i.b(0);
                mVar = i.b(1);
                break;
            case 3:
                mVar2 = i.b(1);
                mVar = i.b(2);
                if (mVar2.e() != i.b(0).e()) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return b(mVar2.e()) && b(mVar.e());
    }

    public BitSet c(h hVar) {
        com.android.cglib.dx.c.b.n i = hVar.i();
        BitSet bitSet = new BitSet(2);
        bitSet.set(0, b(i.b(0).e()));
        bitSet.set(1, b(i.b(1).e()));
        return bitSet;
    }
}
