package com.tencent.open.a;

import android.text.format.Time;
import android.util.Log;

/* compiled from: ProGuard */
public final class h {
    public static final h a;

    static {
        h hVar;
        new h();
        a = hVar;
    }

    public h() {
    }

    public final String a(int i) {
        switch (i) {
            case 1:
                return "V";
            case 2:
                return "D";
            case 4:
                return "I";
            case 8:
                return "W";
            case 16:
                return "E";
            case 32:
                return "A";
            default:
                return "-";
        }
    }

    public String a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        Time time;
        StringBuilder sb;
        Thread thread2 = thread;
        long j2 = j;
        String str3 = str;
        String str4 = str2;
        Throwable th2 = th;
        long j3 = j2 % 1000;
        new Time();
        Time time2 = time;
        time2.set(j2);
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append(a(i)).append('/').append(time2.format("%Y-%m-%d %H:%M:%S")).append('.');
        if (j3 < 10) {
            StringBuilder append2 = sb2.append("00");
        } else if (j3 < 100) {
            StringBuilder append3 = sb2.append('0');
        }
        StringBuilder append4 = sb2.append(j3).append(' ').append('[');
        if (thread2 == null) {
            StringBuilder append5 = sb2.append("N/A");
        } else {
            StringBuilder append6 = sb2.append(thread2.getName());
        }
        StringBuilder append7 = sb2.append(']').append('[').append(str3).append(']').append(' ').append(str4).append(10);
        if (th2 != null) {
            StringBuilder append8 = sb2.append("* Exception : \n").append(Log.getStackTraceString(th2)).append(10);
        }
        return sb2.toString();
    }
}
