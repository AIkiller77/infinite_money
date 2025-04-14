package com.android.cglib.dx.d;

import android.support.v4.media.TransportMediator;

public final class n {
    public static int a(int i) {
        int i2 = i >> 7;
        int i3 = 0;
        while (i2 != 0) {
            i2 >>= 7;
            i3++;
        }
        return i3 + 1;
    }

    public static void a(e eVar, int i) {
        while (true) {
            int i2 = i;
            i >>>= 7;
            if (i != 0) {
                eVar.b((byte) ((i2 & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128));
            } else {
                eVar.b((byte) (i2 & TransportMediator.KEYCODE_MEDIA_PAUSE));
                return;
            }
        }
    }

    public static void b(e eVar, int i) {
        int i2 = i >> 7;
        int i3 = (Integer.MIN_VALUE & i) == 0 ? 0 : -1;
        int i4 = i;
        boolean z = true;
        while (z) {
            z = (i2 == i3 && (i2 & 1) == ((i4 >> 6) & 1)) ? false : true;
            eVar.b((byte) ((i4 & TransportMediator.KEYCODE_MEDIA_PAUSE) | (z ? 128 : 0)));
            i4 = i2;
            i2 >>= 7;
        }
    }
}
