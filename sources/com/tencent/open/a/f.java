package com.tencent.open.a;

import android.os.Environment;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.d;
import com.tencent.open.utils.e;
import java.io.File;

/* compiled from: ProGuard */
public class f {
    public static f a = null;
    protected static final b c;
    private static boolean d = false;
    protected a b;

    static {
        b bVar;
        int i = c.m;
        long j = c.n;
        new b(c(), i, c.g, c.h, c.c, (long) c.i, 10, c.e, j);
        c = bVar;
    }

    private f() {
        a aVar;
        new a(c);
        this.b = aVar;
    }

    public static f a() {
        f fVar;
        if (a == null) {
            Class<f> cls = f.class;
            Class<f> cls2 = cls;
            synchronized (cls) {
                try {
                    if (a == null) {
                        new f();
                        a = fVar;
                        d = true;
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Class<f> cls3 = cls2;
                        throw th2;
                    }
                }
            }
        }
        return a;
    }

    public static final void a(String str, String str2) {
        a().a(1, str, str2, (Throwable) null);
    }

    public static final void a(String str, String str2, Throwable th) {
        a().a(2, str, str2, th);
    }

    public static void b() {
        Class<f> cls = f.class;
        Class<f> cls2 = cls;
        synchronized (cls) {
            try {
                a().d();
                if (null != a) {
                    a = null;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Class<f> cls3 = cls2;
                throw th2;
            }
        }
    }

    public static final void b(String str, String str2) {
        a().a(2, str, str2, (Throwable) null);
    }

    public static final void b(String str, String str2, Throwable th) {
        a().a(16, str, str2, th);
    }

    protected static File c() {
        File file;
        File file2;
        boolean z = false;
        String str = c.d;
        try {
            d.c b2 = d.b.b();
            if (b2 != null && b2.c() > c.f) {
                z = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (z) {
            new File(Environment.getExternalStorageDirectory(), str);
            return file2;
        }
        new File(e.c(), str);
        return file;
    }

    public static final void c(String str, String str2) {
        a().a(4, str, str2, (Throwable) null);
    }

    public static final void d(String str, String str2) {
        a().a(8, str, str2, (Throwable) null);
    }

    public static final void e(String str, String str2) {
        a().a(16, str, str2, (Throwable) null);
    }

    /* access modifiers changed from: protected */
    public void a(int i, String str, String str2, Throwable th) {
        StringBuilder sb;
        int i2 = i;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        if (d) {
            String b2 = e.b();
            if (TextUtils.isEmpty(b2)) {
                Object obj = "default";
            } else {
                new StringBuilder();
                String sb2 = sb.append(b2).append(" SDK_VERSION:").append(Constants.SDK_VERSION).toString();
                if (this.b != null) {
                    e.a.b(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", sb2, (Throwable) null);
                    this.b.b(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", sb2, (Throwable) null);
                    d = false;
                } else {
                    return;
                }
            }
        }
        e.a.b(i2, Thread.currentThread(), System.currentTimeMillis(), str3, str4, th2);
        if (d.a.a(c.b, i2) && this.b != null) {
            this.b.b(i2, Thread.currentThread(), System.currentTimeMillis(), str3, str4, th2);
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        if (this.b != null) {
            this.b.a();
            this.b.b();
            this.b = null;
        }
    }
}
