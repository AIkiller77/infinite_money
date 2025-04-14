package com.b.a.b;

public class g {
    private boolean a = false;

    public final synchronized void a() {
        this.a = true;
    }

    public final synchronized void b() {
        this.a = false;
    }

    public final synchronized boolean c() {
        return this.a;
    }
}
