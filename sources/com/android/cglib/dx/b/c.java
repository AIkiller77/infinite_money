package com.android.cglib.dx.b;

public final class c {
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0009, code lost:
        r1 = r4 & 255;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(int r4) {
        /*
            r0 = 0
            r1 = -1
            if (r4 >= r1) goto L_0x0005
            return r0
        L_0x0005:
            r2 = 1
            if (r4 != r1) goto L_0x0009
            return r2
        L_0x0009:
            r1 = r4 & 255(0xff, float:3.57E-43)
            if (r1 == 0) goto L_0x001a
            r3 = 255(0xff, float:3.57E-43)
            if (r1 != r3) goto L_0x0012
            return r2
        L_0x0012:
            r1 = 65280(0xff00, float:9.1477E-41)
            r4 = r4 & r1
            if (r4 != 0) goto L_0x0019
            r0 = 1
        L_0x0019:
            return r0
        L_0x001a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.cglib.dx.b.c.a(int):boolean");
    }

    public static boolean b(int i) {
        return i >= 255;
    }
}
