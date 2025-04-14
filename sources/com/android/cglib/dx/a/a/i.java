package com.android.cglib.dx.a.a;

import com.android.cglib.dx.c.c.d;
import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.g;
import com.android.cglib.dx.d.h;
import java.util.ArrayList;

public final class i extends h {
    private final int a;

    public i(int i, int i2) {
        super(i);
        this.a = i2;
    }

    public static i a(ArrayList<h> arrayList, int i) {
        int size = arrayList.size();
        i iVar = new i(size, i);
        for (int i2 = 0; i2 < size; i2++) {
            iVar.a(i2, arrayList.get(i2));
        }
        iVar.e();
        return iVar;
    }

    public h a(int i) {
        return (h) d(i);
    }

    public void a(int i, h hVar) {
        a(i, hVar);
    }

    public void a(a aVar) {
        int g = aVar.g();
        int a2 = a();
        int i = 0;
        if (aVar.a()) {
            boolean b = aVar.b();
            for (int i2 = 0; i2 < a2; i2++) {
                h hVar = (h) d(i2);
                int n = hVar.n() * 2;
                String a3 = (n != 0 || b) ? hVar.a("  ", aVar.d(), true) : null;
                if (a3 == null) {
                    if (n != 0) {
                        a3 = "";
                    }
                }
                aVar.a(n, a3);
            }
        }
        while (i < a2) {
            h hVar2 = (h) d(i);
            try {
                hVar2.a(aVar);
                i++;
            } catch (RuntimeException e) {
                throw g.a(e, "...while writing " + hVar2);
            }
        }
        int g2 = (aVar.g() - g) / 2;
        if (g2 != b()) {
            throw new RuntimeException("write length mismatch; expected " + b() + " but actually wrote " + g2);
        }
    }

    public int b() {
        int a2 = a();
        if (a2 == 0) {
            return 0;
        }
        return a(a2 - 1).m();
    }

    public int c() {
        return this.a;
    }

    public int d() {
        int a2 = a();
        int i = 0;
        for (int i2 = 0; i2 < a2; i2++) {
            h hVar = (h) d(i2);
            if (hVar instanceof f) {
                com.android.cglib.dx.c.c.a b = ((f) hVar).b();
                if (b instanceof d) {
                    int b2 = ((d) b).b(hVar.g().b() == 113);
                    if (b2 > i) {
                        i = b2;
                    }
                }
            }
        }
        return i;
    }
}
