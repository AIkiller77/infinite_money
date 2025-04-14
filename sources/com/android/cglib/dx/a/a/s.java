package com.android.cglib.dx.a.a;

import com.android.cglib.dx.a.b;
import java.util.ArrayList;

public final class s {
    private final t a;
    private ArrayList<h> b;

    public s(b bVar, int i, int i2, int i3) {
        this.a = new t(bVar, i, i3);
        this.b = new ArrayList<>(i2);
    }

    private void b() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.a.a(this.b.get(i));
        }
        this.b = null;
    }

    public t a() {
        if (this.b == null) {
            throw new UnsupportedOperationException("already processed");
        }
        b();
        return this.a;
    }

    public void a(int i, e eVar) {
        this.a.a(i, eVar);
    }

    public void a(h hVar) {
        this.a.a(hVar);
    }
}
