package net.lingala.zip4j.crypto.engine;

public class ZipCryptoEngine {
    private static final int[] CRC_TABLE = new int[256];
    private final int[] keys = new int[3];

    static {
        int i;
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                if ((i3 & 1) == 1) {
                    i = (i3 >>> 1) ^ -306674912;
                } else {
                    i = i3 >>> 1;
                }
                i3 = i;
            }
            CRC_TABLE[i2] = i3;
        }
    }

    public ZipCryptoEngine() {
    }

    public void initKeys(char[] cArr) {
        char[] cArr2 = cArr;
        this.keys[0] = 305419896;
        this.keys[1] = 591751049;
        this.keys[2] = 878082192;
        for (int i = 0; i < cArr2.length; i++) {
            updateKeys((byte) (cArr2[i] & 255));
        }
    }

    public void updateKeys(byte b) {
        this.keys[0] = crc32(this.keys[0], b);
        int[] iArr = this.keys;
        iArr[1] = iArr[1] + (this.keys[0] & 255);
        this.keys[1] = (this.keys[1] * 134775813) + 1;
        this.keys[2] = crc32(this.keys[2], (byte) (this.keys[1] >> 24));
    }

    private int crc32(int i, byte b) {
        int i2 = i;
        return (i2 >>> 8) ^ CRC_TABLE[(i2 ^ b) & 255];
    }

    public byte decryptByte() {
        int i = this.keys[2] | 2;
        return (byte) ((i * (i ^ 1)) >>> 8);
    }
}
