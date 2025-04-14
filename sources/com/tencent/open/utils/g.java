package com.tencent.open.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.a.f;
import java.lang.ref.WeakReference;
import java.net.URL;

/* compiled from: ProGuard */
public class g {
    private static g a = null;
    private volatile WeakReference<SharedPreferences> b = null;

    public g() {
    }

    public static synchronized g a() {
        g gVar;
        g gVar2;
        synchronized (g.class) {
            if (a == null) {
                new g();
                a = gVar2;
            }
            gVar = a;
        }
        return gVar;
    }

    public String a(Context context, String str) {
        StringBuilder sb;
        URL url;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        WeakReference<SharedPreferences> weakReference;
        Context context2 = context;
        String str2 = str;
        if (this.b == null || this.b.get() == null) {
            new WeakReference<>(context2.getSharedPreferences("ServerPrefs", 0));
            this.b = weakReference;
        }
        try {
            new URL(str2);
            String host = url.getHost();
            if (host == null) {
                new StringBuilder();
                f.e("openSDK_LOG.ServerSetting", sb4.append("Get host error. url=").append(str2).toString());
                return str2;
            }
            String string = ((SharedPreferences) this.b.get()).getString(host, (String) null);
            if (string == null || host.equals(string)) {
                new StringBuilder();
                f.a("openSDK_LOG.ServerSetting", sb2.append("host=").append(host).append(", envHost=").append(string).toString());
                return str2;
            }
            String replace = str2.replace(host, string);
            new StringBuilder();
            f.a("openSDK_LOG.ServerSetting", sb3.append("return environment url : ").append(replace).toString());
            return replace;
        } catch (Exception e) {
            new StringBuilder();
            f.e("openSDK_LOG.ServerSetting", sb.append("getEnvUrl url=").append(str2).append("error.: ").append(e.getMessage()).toString());
            return str2;
        }
    }
}
