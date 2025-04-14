package com.b.a.b;

public class p {
    private m[] a = new m[4];

    public p() {
        this.a[0] = new m(0, 0);
        for (int i = 1; i < 4; i++) {
            this.a[i] = new m(-1, -1);
        }
    }

    private boolean b(int i, int i2) {
        for (int i3 = 1; i3 < 4; i3++) {
            if (this.a[i3].a() == i) {
                this.a[i3].b(i2);
                return true;
            }
        }
        return false;
    }

    private void c(int i, int i2) {
        d(3);
        this.a[1] = new m(i, i2);
    }

    private void d(int i) {
        if (i != 0) {
            m mVar = this.a[i];
            while (i > 1) {
                this.a[i] = this.a[i - 1];
                i--;
            }
            this.a[1] = mVar;
        }
    }

    public m a(int i) {
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            int abs = Math.abs(i - this.a[i4].a());
            if (abs < i2) {
                i3 = i4;
                i2 = abs;
            }
        }
        m mVar = this.a[i3];
        d(i3);
        return mVar;
    }

    public void a(int i, int i2) {
        if (i > 0 && !b(i, i2)) {
            c(i, i2);
        }
    }

    public m b(int i) {
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            int abs = Math.abs(i - this.a[i4].b());
            if (abs < i2) {
                i3 = i4;
                i2 = abs;
            }
        }
        m mVar = this.a[i3];
        d(i3);
        return mVar;
    }

    /* access modifiers changed from: protected */
    public final void c(int i) {
        for (int i2 = 1; i2 < 4; i2++) {
            if (this.a[i2].b() >= i) {
                this.a[i2] = new m(-1, -1);
            }
        }
    }
}
