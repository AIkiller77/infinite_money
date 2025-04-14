package com.android.cglib.dx.d;

public class o {
    private boolean a;

    public o() {
        this.a = true;
    }

    public o(boolean z) {
        this.a = z;
    }

    public void e() {
        this.a = false;
    }

    public final boolean f() {
        return !this.a;
    }

    public final boolean g() {
        return this.a;
    }

    public final void h() {
        if (!this.a) {
            throw new p("immutable instance");
        }
    }

    public final void i() {
        if (this.a) {
            throw new p("mutable instance");
        }
    }
}
