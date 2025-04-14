package com.android.cglib.dx.d;

public final class i {
    public static String a(int i) {
        char[] cArr = new char[8];
        for (int i2 = 0; i2 < 8; i2++) {
            cArr[7 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String a(long j) {
        char[] cArr = new char[16];
        for (int i = 0; i < 16; i++) {
            cArr[15 - i] = Character.forDigit(((int) j) & 15, 16);
            j >>= 4;
        }
        return new String(cArr);
    }

    public static String a(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = i + i2;
        if ((i | i2 | i6) < 0 || i6 > bArr.length) {
            throw new IndexOutOfBoundsException("arr.length " + bArr.length + "; " + i + "..!" + i6);
        } else if (i3 < 0) {
            throw new IllegalArgumentException("outOffset < 0");
        } else if (i2 == 0) {
            return "";
        } else {
            StringBuffer stringBuffer = new StringBuffer((i2 * 4) + 6);
            int i7 = i;
            int i8 = 0;
            while (i2 > 0) {
                if (i8 == 0) {
                    stringBuffer.append(i5 != 2 ? i5 != 4 ? i5 != 6 ? a(i3) : b(i3) : c(i3) : e(i3));
                    stringBuffer.append(": ");
                } else if ((i8 & 1) == 0) {
                    stringBuffer.append(' ');
                }
                stringBuffer.append(e(bArr[i7]));
                i3++;
                i7++;
                i8++;
                if (i8 == i4) {
                    stringBuffer.append(10);
                    i8 = 0;
                }
                i2--;
            }
            if (i8 != 0) {
                stringBuffer.append(10);
            }
            return stringBuffer.toString();
        }
    }

    public static String b(int i) {
        char[] cArr = new char[6];
        for (int i2 = 0; i2 < 6; i2++) {
            cArr[5 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String c(int i) {
        char[] cArr = new char[4];
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[3 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String d(int i) {
        return i == ((char) i) ? c(i) : a(i);
    }

    public static String e(int i) {
        char[] cArr = new char[2];
        for (int i2 = 0; i2 < 2; i2++) {
            cArr[1 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String f(int i) {
        return new String(new char[]{Character.forDigit(i & 15, 16)});
    }

    public static String g(int i) {
        char[] cArr = new char[9];
        if (i < 0) {
            cArr[0] = '-';
            i = -i;
        } else {
            cArr[0] = '+';
        }
        for (int i2 = 0; i2 < 8; i2++) {
            cArr[8 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String h(int i) {
        char[] cArr = new char[5];
        if (i < 0) {
            cArr[0] = '-';
            i = -i;
        } else {
            cArr[0] = '+';
        }
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[4 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }
}
