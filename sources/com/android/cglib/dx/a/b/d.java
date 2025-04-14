package com.android.cglib.dx.a.b;

import com.android.cglib.dx.d.a;
import com.android.cglib.dx.d.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public final class d extends ag {
    private b a = null;
    private ArrayList<q> b = null;
    private ArrayList<ac> c = null;
    private ArrayList<ah> d = null;

    public d() {
        super(4, -1);
    }

    private static int a(ArrayList<?> arrayList) {
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int a(ag agVar) {
        if (d()) {
            return this.a.compareTo(((d) agVar).a);
        }
        throw new UnsupportedOperationException("uninternable instance");
    }

    public y a() {
        return y.TYPE_ANNOTATIONS_DIRECTORY_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        a(((a((ArrayList<?>) this.b) + a((ArrayList<?>) this.c) + a((ArrayList<?>) this.d)) * 8) + 16);
    }

    public void a(l lVar) {
        af d2 = lVar.d();
        if (this.a != null) {
            this.a = (b) d2.b(this.a);
        }
        if (this.b != null) {
            Iterator<q> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().a(lVar);
            }
        }
        if (this.c != null) {
            Iterator<ac> it2 = this.c.iterator();
            while (it2.hasNext()) {
                it2.next().a(lVar);
            }
        }
        if (this.d != null) {
            Iterator<ah> it3 = this.d.iterator();
            while (it3.hasNext()) {
                it3.next().a(lVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, a aVar) {
        boolean a2 = aVar.a();
        int b2 = ag.b(this.a);
        int a3 = a((ArrayList<?>) this.b);
        int a4 = a((ArrayList<?>) this.c);
        int a5 = a((ArrayList<?>) this.d);
        if (a2) {
            aVar.a(0, g() + " annotations directory");
            aVar.a(4, "  class_annotations_off: " + i.a(b2));
            aVar.a(4, "  fields_size:           " + i.a(a3));
            aVar.a(4, "  methods_size:          " + i.a(a4));
            aVar.a(4, "  parameters_size:       " + i.a(a5));
        }
        aVar.d(b2);
        aVar.d(a3);
        aVar.d(a4);
        aVar.d(a5);
        if (a3 != 0) {
            Collections.sort(this.b);
            if (a2) {
                aVar.a(0, "  fields:");
            }
            Iterator<q> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().a(lVar, aVar);
            }
        }
        if (a4 != 0) {
            Collections.sort(this.c);
            if (a2) {
                aVar.a(0, "  methods:");
            }
            Iterator<ac> it2 = this.c.iterator();
            while (it2.hasNext()) {
                it2.next().a(lVar, aVar);
            }
        }
        if (a5 != 0) {
            Collections.sort(this.d);
            if (a2) {
                aVar.a(0, "  parameters:");
            }
            Iterator<ah> it3 = this.d.iterator();
            while (it3.hasNext()) {
                it3.next().a(lVar, aVar);
            }
        }
    }

    public String b() {
        throw new RuntimeException("unsupported");
    }

    public boolean c() {
        return this.a == null && this.b == null && this.c == null && this.d == null;
    }

    public boolean d() {
        return this.a != null && this.b == null && this.c == null && this.d == null;
    }

    public int hashCode() {
        if (this.a == null) {
            return 0;
        }
        return this.a.hashCode();
    }
}
