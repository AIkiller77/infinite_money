package com.tencent.open.a;

import android.util.Log;

/* compiled from: ProGuard */
public final class e extends i {
    public static final e a;

    static {
        e eVar;
        new e();
        a = eVar;
    }

    public e() {
    }

    /* access modifiers changed from: protected */
    public void a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        Thread thread2 = thread;
        long j2 = j;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        switch (i) {
            case 1:
                int v = Log.v(str3, str4, th2);
                return;
            case 2:
                int d = Log.d(str3, str4, th2);
                return;
            case 4:
                int i2 = Log.i(str3, str4, th2);
                return;
            case 8:
                int w = Log.w(str3, str4, th2);
                return;
            case 16:
                int e = Log.e(str3, str4, th2);
                return;
            case 32:
                int e2 = Log.e(str3, str4, th2);
                return;
            default:
                return;
        }
    }
}
