package com.tencent.open.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* compiled from: ProGuard */
public class a extends i implements Handler.Callback {
    private b a;
    private FileWriter b;
    private File c;
    private char[] d;
    private volatile g e;
    private volatile g f;
    private volatile g g;
    private volatile g h;
    private volatile boolean i;
    private HandlerThread j;
    private Handler k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(int i2, boolean z, h hVar, b bVar) {
        super(i2, z, hVar);
        g gVar;
        g gVar2;
        HandlerThread handlerThread;
        Handler handler;
        b bVar2 = bVar;
        this.i = false;
        a(bVar2);
        new g();
        this.e = gVar;
        new g();
        this.f = gVar2;
        this.g = this.e;
        this.h = this.f;
        this.d = new char[bVar2.d()];
        Writer g2 = g();
        new HandlerThread(bVar2.c(), bVar2.f());
        this.j = handlerThread;
        if (this.j != null) {
            this.j.start();
        }
        if (this.j.isAlive() && this.j.getLooper() != null) {
            new Handler(this.j.getLooper(), this);
            this.k = handler;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public a(b bVar) {
        this(c.b, true, h.a, bVar);
    }

    private void f() {
        if (Thread.currentThread() == this.j && !this.i) {
            this.i = true;
            i();
            try {
                this.h.a(g(), this.d);
                this.h.b();
            } catch (IOException e2) {
                IOException iOException = e2;
                this.h.b();
            } catch (Throwable th) {
                Throwable th2 = th;
                this.h.b();
                throw th2;
            }
            this.i = false;
        }
    }

    private Writer g() {
        FileWriter fileWriter;
        File a2 = c().a();
        if ((a2 != null && !a2.equals(this.c)) || (this.b == null && a2 != null)) {
            this.c = a2;
            h();
            try {
                new FileWriter(this.c, true);
                this.b = fileWriter;
            } catch (IOException e2) {
                IOException iOException = e2;
                return null;
            }
        }
        return this.b;
    }

    private void h() {
        try {
            if (this.b != null) {
                this.b.flush();
                this.b.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: finally extract failed */
    private void i() {
        synchronized (this) {
            try {
                if (this.g == this.e) {
                    this.g = this.f;
                    this.h = this.e;
                } else {
                    this.g = this.e;
                    this.h = this.f;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    public void a() {
        if (this.k.hasMessages(1024)) {
            this.k.removeMessages(1024);
        }
        boolean sendEmptyMessage = this.k.sendEmptyMessage(1024);
    }

    /* access modifiers changed from: protected */
    public void a(int i2, Thread thread, long j2, String str, String str2, Throwable th) {
        a(e().a(i2, thread, j2, str, str2, th));
    }

    public void a(b bVar) {
        b bVar2 = bVar;
        this.a = bVar2;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        int a2 = this.g.a(str);
        if (this.g.a() >= c().d()) {
            a();
        }
    }

    public void b() {
        h();
        boolean quit = this.j.quit();
    }

    public b c() {
        return this.a;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1024:
                f();
                break;
        }
        return true;
    }
}
