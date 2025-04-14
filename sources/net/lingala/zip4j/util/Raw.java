package net.lingala.zip4j.util;

import java.io.DataInput;
import java.io.IOException;
import net.lingala.zip4j.exception.ZipException;

public class Raw {
    public Raw() {
    }

    public static long readLongLittleEndian(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return ((((((((((((((0 | ((long) (bArr2[i2 + 7] & 255))) << 8) | ((long) (bArr2[i2 + 6] & 255))) << 8) | ((long) (bArr2[i2 + 5] & 255))) << 8) | ((long) (bArr2[i2 + 4] & 255))) << 8) | ((long) (bArr2[i2 + 3] & 255))) << 8) | ((long) (bArr2[i2 + 2] & 255))) << 8) | ((long) (bArr2[i2 + 1] & 255))) << 8) | ((long) (bArr2[i2] & 255));
    }

    public static int readLeInt(DataInput dataInput, byte[] bArr) throws ZipException {
        Throwable th;
        byte[] bArr2 = bArr;
        try {
            dataInput.readFully(bArr2, 0, 4);
            return (bArr2[0] & 255) | ((bArr2[1] & 255) << 8) | (((bArr2[2] & 255) | ((bArr2[3] & 255) << 8)) << 16);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new ZipException((Throwable) iOException);
            throw th2;
        }
    }

    public static int readShortLittleEndian(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return (bArr2[i2] & 255) | ((bArr2[i2 + 1] & 255) << 8);
    }

    /* JADX WARNING: type inference failed for: r3v6, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final short readShortBigEndian(byte[] r7, int r8) {
        /*
            r0 = r7
            r1 = r8
            r3 = 0
            r2 = r3
            r3 = r2
            r4 = r0
            r5 = r1
            byte r4 = r4[r5]
            r5 = 255(0xff, float:3.57E-43)
            r4 = r4 & 255(0xff, float:3.57E-43)
            r3 = r3 | r4
            short r3 = (short) r3
            r2 = r3
            r3 = r2
            r4 = 8
            int r3 = r3 << 8
            short r3 = (short) r3
            r2 = r3
            r3 = r2
            r4 = r0
            r5 = r1
            r6 = 1
            int r5 = r5 + 1
            byte r4 = r4[r5]
            r5 = 255(0xff, float:3.57E-43)
            r4 = r4 & 255(0xff, float:3.57E-43)
            r3 = r3 | r4
            short r3 = (short) r3
            r2 = r3
            r3 = r2
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.Raw.readShortBigEndian(byte[], int):short");
    }

    public static int readIntLittleEndian(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        return (bArr2[i2] & 255) | ((bArr2[i2 + 1] & 255) << 8) | (((bArr2[i2 + 2] & 255) | ((bArr2[i2 + 3] & 255) << 8)) << 16);
    }

    public static byte[] toByteArray(int i, int i2) {
        int i3 = i2;
        byte[] bArr = new byte[i3];
        byte[] byteArray = toByteArray(i);
        int i4 = 0;
        while (i4 < byteArray.length && i4 < i3) {
            bArr[i4] = byteArray[i4];
            i4++;
        }
        return bArr;
    }

    public static byte[] toByteArray(int i) {
        int i2 = i;
        return new byte[]{(byte) i2, (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24)};
    }

    public static final void writeShortLittleEndian(byte[] bArr, int i, short s) {
        byte[] bArr2 = bArr;
        int i2 = i;
        short s2 = s;
        bArr2[i2 + 1] = (byte) (s2 >>> 8);
        bArr2[i2] = (byte) (s2 & 255);
    }

    public static final void writeIntLittleEndian(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        bArr2[i3 + 3] = (byte) (i4 >>> 24);
        bArr2[i3 + 2] = (byte) (i4 >>> 16);
        bArr2[i3 + 1] = (byte) (i4 >>> 8);
        bArr2[i3] = (byte) (i4 & 255);
    }

    public static void writeLongLittleEndian(byte[] bArr, int i, long j) {
        byte[] bArr2 = bArr;
        int i2 = i;
        long j2 = j;
        bArr2[i2 + 7] = (byte) ((int) (j2 >>> 56));
        bArr2[i2 + 6] = (byte) ((int) (j2 >>> 48));
        bArr2[i2 + 5] = (byte) ((int) (j2 >>> 40));
        bArr2[i2 + 4] = (byte) ((int) (j2 >>> 32));
        bArr2[i2 + 3] = (byte) ((int) (j2 >>> 24));
        bArr2[i2 + 2] = (byte) ((int) (j2 >>> 16));
        bArr2[i2 + 1] = (byte) ((int) (j2 >>> 8));
        bArr2[i2] = (byte) ((int) (j2 & 255));
    }

    public static byte bitArrayToByte(int[] iArr) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int[] iArr2 = iArr;
        if (iArr2 == null) {
            Throwable th4 = th3;
            new ZipException("bit array is null, cannot calculate byte from bits");
            throw th4;
        } else if (iArr2.length != 8) {
            Throwable th5 = th2;
            new ZipException("invalid bit array length, cannot calculate byte");
            throw th5;
        } else if (!checkBits(iArr2)) {
            Throwable th6 = th;
            new ZipException("invalid bits provided, bits contain other values than 0 or 1");
            throw th6;
        } else {
            int i = 0;
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                i = (int) (((double) i) + (Math.pow(2.0d, (double) i2) * ((double) iArr2[i2])));
            }
            return (byte) i;
        }
    }

    private static boolean checkBits(int[] iArr) {
        int[] iArr2 = iArr;
        for (int i = 0; i < iArr2.length; i++) {
            if (iArr2[i] != 0 && iArr2[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static void prepareBuffAESIVBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        bArr2[0] = (byte) i3;
        bArr2[1] = (byte) (i3 >> 8);
        bArr2[2] = (byte) (i3 >> 16);
        bArr2[3] = (byte) (i3 >> 24);
        bArr2[4] = 0;
        bArr2[5] = 0;
        bArr2[6] = 0;
        bArr2[7] = 0;
        bArr2[8] = 0;
        bArr2[9] = 0;
        bArr2[10] = 0;
        bArr2[11] = 0;
        bArr2[12] = 0;
        bArr2[13] = 0;
        bArr2[14] = 0;
        bArr2[15] = 0;
    }

    public static byte[] convertCharArrayToByteArray(char[] cArr) {
        Throwable th;
        char[] cArr2 = cArr;
        if (cArr2 == null) {
            Throwable th2 = th;
            new NullPointerException();
            throw th2;
        }
        byte[] bArr = new byte[cArr2.length];
        for (int i = 0; i < cArr2.length; i++) {
            bArr[i] = (byte) cArr2[i];
        }
        return bArr;
    }
}
