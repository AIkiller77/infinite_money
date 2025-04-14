package com.android.cglib.dx;

import com.android.cglib.dx.c.b.b;
import com.android.cglib.dx.c.b.f;
import com.android.cglib.dx.c.b.g;
import com.android.cglib.dx.d.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Label {
    final List<f> a = new ArrayList();
    Code b;
    boolean c = false;
    List<Label> d = Collections.emptyList();
    Label e;
    Label f;
    int g = -1;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.a.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        for (int i = 0; i < this.d.size(); i++) {
            while (this.d.get(i).a()) {
                this.d.set(i, this.d.get(i).e);
            }
        }
        while (this.e != null && this.e.a()) {
            this.e = this.e.e;
        }
        while (this.f != null && this.f.a()) {
            this.f = this.f.e;
        }
    }

    /* access modifiers changed from: package-private */
    public b c() {
        g gVar = new g(this.a.size());
        for (int i = 0; i < this.a.size(); i++) {
            gVar.a(i, this.a.get(i));
        }
        gVar.e();
        int i2 = -1;
        k kVar = new k();
        for (Label label : this.d) {
            kVar.b(label.g);
        }
        if (this.e != null) {
            i2 = this.e.g;
            kVar.b(i2);
        }
        if (this.f != null) {
            kVar.b(this.f.g);
        }
        kVar.e();
        return new b(this.g, gVar, kVar, i2);
    }
}
