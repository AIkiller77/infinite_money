package com.tencent.open.a;

import com.tencent.open.a.d;

/* compiled from: ProGuard */
public abstract class i {
    private volatile int a;
    private volatile boolean b;
    private h c;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public i() {
        this(c.a, true, h.a);
    }

    public i(int i, boolean z, h hVar) {
        this.a = c.a;
        this.b = true;
        this.c = h.a;
        a(i);
        a(z);
        a(hVar);
    }

    public void a(int i) {
        int i2 = i;
        this.a = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void a(int i, Thread thread, long j, String str, String str2, Throwable th);

    public void a(h hVar) {
        h hVar2 = hVar;
        this.c = hVar2;
    }

    public void a(boolean z) {
        boolean z2 = z;
        this.b = z2;
    }

    public void b(int i, Thread thread, long j, String str, String str2, Throwable th) {
        int i2 = i;
        Thread thread2 = thread;
        long j2 = j;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (d() && d.a.a(this.a, i2)) {
            a(i2, thread2, j2, str3, str4, th2);
        }
    }

    public boolean d() {
        return this.b;
    }

    public h e() {
        return this.c;
    }
}
