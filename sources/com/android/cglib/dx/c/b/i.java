package com.android.cglib.dx.c.b;

import com.android.cglib.dx.d.o;
import java.util.HashMap;

public final class i extends o {
    private final o a;
    private final o[] b;
    private final HashMap<f, m> c;

    private o b(int i) {
        try {
            return this.b[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("bogus label");
        }
    }

    public int a() {
        return this.c.size();
    }

    public m a(f fVar) {
        return this.c.get(fVar);
    }

    public o a(int i) {
        o b2 = b(i);
        return b2 != null ? b2 : this.a;
    }

    public o a(b bVar) {
        return a(bVar.a());
    }
}
