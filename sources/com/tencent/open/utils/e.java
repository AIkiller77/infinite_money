package com.tencent.open.utils;

import android.content.Context;
import java.io.File;

/* compiled from: ProGuard */
public final class e {
    private static Context a;

    public static final Context a() {
        if (a == null) {
            return null;
        }
        return a;
    }

    public static final void a(Context context) {
        a = context;
    }

    public static final String b() {
        return null == a() ? "" : a().getPackageName();
    }

    public static final File c() {
        if (null == a()) {
            return null;
        }
        return a().getFilesDir();
    }
}
