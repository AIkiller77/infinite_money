package com.android.cglib.dx.d;

public final class b {
    public static int a(int i, int i2) {
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i & (((1 << i2) - 1) ^ -1));
        if (numberOfTrailingZeros == 32) {
            return -1;
        }
        return numberOfTrailingZeros;
    }

    public static boolean a(int[] iArr, int i) {
        return (iArr[i >> 5] & (1 << (i & 31))) != 0;
    }

    public static int[] a(int i) {
        return new int[((i + 31) >> 5)];
    }

    public static void b(int[] iArr, int i) {
        int i2 = i >> 5;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public static void c(int[] iArr, int i) {
        int i2 = i >> 5;
        iArr[i2] = ((1 << (i & 31)) ^ -1) & iArr[i2];
    }

    public static int d(int[] iArr, int i) {
        int a;
        int length = iArr.length;
        int i2 = i & 31;
        for (int i3 = i >> 5; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i4 != 0 && (a = a(i4, i2)) >= 0) {
                return (i3 << 5) + a;
            }
            i2 = 0;
        }
        return -1;
    }
}
