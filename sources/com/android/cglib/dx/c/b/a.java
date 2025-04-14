package com.android.cglib.dx.c.b;

import com.android.cglib.dx.d.i;

public final class a {
    public static String a(int i) {
        return a(i, 30257, 1);
    }

    private static String a(int i, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(80);
        int i4 = (i2 ^ -1) & i;
        int i5 = i & i2;
        if ((i5 & 1) != 0) {
            stringBuffer.append("|public");
        }
        if ((i5 & 2) != 0) {
            stringBuffer.append("|private");
        }
        if ((i5 & 4) != 0) {
            stringBuffer.append("|protected");
        }
        if ((i5 & 8) != 0) {
            stringBuffer.append("|static");
        }
        if ((i5 & 16) != 0) {
            stringBuffer.append("|final");
        }
        if ((i5 & 32) != 0) {
            stringBuffer.append(i3 == 1 ? "|super" : "|synchronized");
        }
        if ((i5 & 64) != 0) {
            stringBuffer.append(i3 == 3 ? "|bridge" : "|volatile");
        }
        if ((i5 & 128) != 0) {
            stringBuffer.append(i3 == 3 ? "|varargs" : "|transient");
        }
        if ((i5 & 256) != 0) {
            stringBuffer.append("|native");
        }
        if ((i5 & 512) != 0) {
            stringBuffer.append("|interface");
        }
        if ((i5 & 1024) != 0) {
            stringBuffer.append("|abstract");
        }
        if ((i5 & 2048) != 0) {
            stringBuffer.append("|strictfp");
        }
        if ((i5 & 4096) != 0) {
            stringBuffer.append("|synthetic");
        }
        if ((i5 & 8192) != 0) {
            stringBuffer.append("|annotation");
        }
        if ((i5 & 16384) != 0) {
            stringBuffer.append("|enum");
        }
        if ((65536 & i5) != 0) {
            stringBuffer.append("|constructor");
        }
        if ((i5 & 131072) != 0) {
            stringBuffer.append("|declared_synchronized");
        }
        if (i4 != 0 || stringBuffer.length() == 0) {
            stringBuffer.append('|');
            stringBuffer.append(i.c(i4));
        }
        return stringBuffer.substring(1);
    }

    public static String b(int i) {
        return a(i, 20703, 2);
    }

    public static String c(int i) {
        return a(i, 204287, 3);
    }
}
