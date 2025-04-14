package com.android.cglib.dx.a.b;

public abstract class w extends x {
    private int a = -1;

    public final void a(int i) {
        if (this.a != -1) {
            throw new RuntimeException("index already set");
        }
        this.a = i;
    }

    public final boolean f() {
        return this.a >= 0;
    }

    public final int g() {
        if (this.a >= 0) {
            return this.a;
        }
        throw new RuntimeException("index not yet set");
    }

    public final String h() {
        return '[' + Integer.toHexString(this.a) + ']';
    }
}
