package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

/* compiled from: ProGuard */
public class b {
    public static b a;
    static final /* synthetic */ boolean d = (!b.class.desiredAssertionStatus());
    private static int e = 0;
    public HashMap<String, a> b;
    public final String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /* compiled from: ProGuard */
    public static class a {
        public IUiListener a;
        public a b;
        public String c;

        public a() {
        }
    }

    public b() {
        HashMap<String, a> hashMap;
        new HashMap<>();
        this.b = hashMap;
    }

    public static b a() {
        b bVar;
        if (null == a) {
            new b();
            a = bVar;
        }
        return a;
    }

    public static int b() {
        int i = e + 1;
        int i2 = i;
        e = i;
        return i2;
    }

    public String a(a aVar) {
        StringBuilder sb;
        StringBuilder sb2;
        a aVar2 = aVar;
        int b2 = b();
        try {
            HashMap<String, a> hashMap = this.b;
            new StringBuilder();
            a put = hashMap.put(sb2.append("").append(b2).toString(), aVar2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        new StringBuilder();
        return sb.append("").append(b2).toString();
    }

    public String c() {
        StringBuffer stringBuffer;
        int ceil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = charArray.length;
        new StringBuffer();
        StringBuffer stringBuffer2 = stringBuffer;
        for (int i = 0; i < ceil; i++) {
            StringBuffer append = stringBuffer2.append(charArray[(int) (Math.random() * ((double) length))]);
        }
        return stringBuffer2.toString();
    }
}
