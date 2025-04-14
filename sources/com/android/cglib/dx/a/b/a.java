package com.android.cglib.dx.a.b;

import com.android.cglib.dx.c.a.b;
import com.android.cglib.dx.c.c.v;
import com.android.cglib.dx.d.d;
import java.util.Arrays;
import java.util.Comparator;

public final class a extends ag {
    private static final C0003a a = new C0003a();
    private final com.android.cglib.dx.c.a.a b;
    /* access modifiers changed from: private */
    public ap c;
    private byte[] d;

    /* renamed from: com.android.cglib.dx.a.b.a$a  reason: collision with other inner class name */
    private static class C0003a implements Comparator<a> {
        private C0003a() {
        }

        /* renamed from: a */
        public int compare(a aVar, a aVar2) {
            int g = aVar.c.g();
            int g2 = aVar2.c.g();
            if (g < g2) {
                return -1;
            }
            return g > g2 ? 1 : 0;
        }
    }

    public static void a(a[] aVarArr) {
        Arrays.sort(aVarArr, a);
    }

    /* access modifiers changed from: protected */
    public int a(ag agVar) {
        return this.b.compareTo(((a) agVar).b);
    }

    public y a() {
        return y.TYPE_ANNOTATION_ITEM;
    }

    /* access modifiers changed from: protected */
    public void a(ak akVar, int i) {
        d dVar = new d();
        new au(akVar.e(), dVar).a(this.b, false);
        this.d = dVar.f();
        a(this.d.length + 1);
    }

    public void a(l lVar) {
        this.c = lVar.j().a(this.b.b());
        au.a(lVar, this.b);
    }

    public void a(com.android.cglib.dx.d.a aVar, String str) {
        aVar.a(0, str + "visibility: " + this.b.c().a_());
        aVar.a(0, str + "type: " + this.b.b().a_());
        for (com.android.cglib.dx.c.a.d next : this.b.d()) {
            v a2 = next.a();
            com.android.cglib.dx.c.c.a b2 = next.b();
            aVar.a(0, str + a2.a_() + ": " + au.b(b2));
        }
    }

    /* access modifiers changed from: protected */
    public void a_(l lVar, com.android.cglib.dx.d.a aVar) {
        boolean a2 = aVar.a();
        b c2 = this.b.c();
        if (a2) {
            aVar.a(0, g() + " annotation");
            aVar.a(1, "  visibility: VISBILITY_" + c2);
        }
        switch (c2) {
            case BUILD:
                aVar.b(0);
                break;
            case RUNTIME:
                aVar.b(1);
                break;
            case SYSTEM:
                aVar.b(2);
                break;
            default:
                throw new RuntimeException("shouldn't happen");
        }
        if (a2) {
            new au(lVar, aVar).a(this.b, true);
        } else {
            aVar.a(this.d);
        }
    }

    public String b() {
        return this.b.a_();
    }

    public int hashCode() {
        return this.b.hashCode();
    }
}
