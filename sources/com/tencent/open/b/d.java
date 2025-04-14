package com.tencent.open.b;

import android.os.SystemClock;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.k;

/* compiled from: ProGuard */
public class d {
    protected static d a;

    protected d() {
    }

    public static synchronized d a() {
        d dVar;
        d dVar2;
        synchronized (d.class) {
            if (a == null) {
                new d();
                a = dVar2;
            }
            dVar = a;
        }
        return dVar;
    }

    public void a(int i, String str, String str2, String str3, String str4, Long l, int i2, int i3, String str5) {
        StringBuffer stringBuffer;
        int i4 = i;
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        String str9 = str4;
        Long l2 = l;
        int i5 = i2;
        int i6 = i3;
        String str10 = str5;
        long elapsedRealtime = SystemClock.elapsedRealtime() - l2.longValue();
        if (l2.longValue() == 0 || elapsedRealtime < 0) {
            elapsedRealtime = 0;
        }
        new StringBuffer("https://huatuocode.huatuo.qq.com");
        StringBuffer stringBuffer2 = stringBuffer;
        StringBuffer append = stringBuffer2.append("?domain=mobile.opensdk.com&cgi=opensdk&type=").append(i4).append("&code=").append(i5).append("&time=").append(elapsedRealtime).append("&rate=").append(i6).append("&uin=").append(str7).append("&data=");
        g.a().a(stringBuffer2.toString(), Constants.HTTP_GET, k.a(String.valueOf(i4), String.valueOf(i5), String.valueOf(elapsedRealtime), String.valueOf(i6), str6, str7, str8, str9, str10), true);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = str2;
        g.a().a(k.a(str, str3, str4, str5, str7, str6), str7, true);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        String str9 = str2;
        g.a().a(k.a(str, str4, str5, str3, str9, str6, "", str7, str8, "", "", ""), str9, false);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        String str11 = str2;
        g.a().a(k.a(str, str4, str5, str3, str11, str6, str7, "", "", str8, str9, str10), str11, false);
    }
}
