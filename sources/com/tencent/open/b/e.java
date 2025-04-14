package com.tencent.open.b;

import com.tencent.open.utils.f;

/* compiled from: ProGuard */
public class e {
    public static int a() {
        int a = f.a(com.tencent.open.utils.e.a(), (String) null).a("Common_HttpRetryCount");
        if (a == 0) {
            a = 2;
        }
        return a;
    }

    public static int a(String str) {
        String str2 = str;
        if (com.tencent.open.utils.e.a() == null) {
            return 100;
        }
        int a = f.a(com.tencent.open.utils.e.a(), str2).a("Common_BusinessReportFrequency");
        if (a == 0) {
            a = 100;
        }
        return a;
    }
}
