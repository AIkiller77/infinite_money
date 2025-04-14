package com.android.cglib.dx.d;

public class m extends h {
    private final k a;

    public m(int i) {
        super(i);
        this.a = new k(i);
    }

    private void a(int i) {
        this.a.a(i, -1);
    }

    private void a(int i, int i2) {
        int a2 = this.a.a();
        for (int i3 = 0; i3 <= i - a2; i3++) {
            this.a.b(-1);
        }
        this.a.a(i, i2);
    }

    /* access modifiers changed from: protected */
    public void a(int i, l lVar) {
        l lVar2 = (l) e(i);
        a(i, lVar);
        if (lVar2 != null) {
            a(lVar2.a());
        }
        if (lVar != null) {
            a(lVar.a(), i);
        }
    }

    public final int c(int i) {
        if (i >= this.a.a()) {
            return -1;
        }
        return this.a.a(i);
    }

    public final int d() {
        int a2 = this.a.a() - 1;
        while (a2 >= 0 && this.a.a(a2) < 0) {
            a2--;
        }
        int i = a2 + 1;
        this.a.c(i);
        return i;
    }
}
