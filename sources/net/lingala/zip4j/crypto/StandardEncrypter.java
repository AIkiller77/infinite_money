package net.lingala.zip4j.crypto;

import java.util.Random;
import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;

public class StandardEncrypter implements IEncrypter {
    private byte[] headerBytes;
    private ZipCryptoEngine zipCryptoEngine;

    public StandardEncrypter(char[] cArr, int i) throws ZipException {
        Throwable th;
        ZipCryptoEngine zipCryptoEngine2;
        char[] cArr2 = cArr;
        int i2 = i;
        if (cArr2 == null || cArr2.length <= 0) {
            Throwable th2 = th;
            new ZipException("input password is null or empty in standard encrpyter constructor");
            throw th2;
        }
        new ZipCryptoEngine();
        this.zipCryptoEngine = zipCryptoEngine2;
        this.headerBytes = new byte[12];
        init(cArr2, i2);
    }

    private void init(char[] cArr, int i) throws ZipException {
        Throwable th;
        Throwable th2;
        char[] cArr2 = cArr;
        int i2 = i;
        if (cArr2 == null || cArr2.length <= 0) {
            Throwable th3 = th;
            new ZipException("input password is null or empty, cannot initialize standard encrypter");
            throw th3;
        }
        this.zipCryptoEngine.initKeys(cArr2);
        this.headerBytes = generateRandomBytes(12);
        this.zipCryptoEngine.initKeys(cArr2);
        this.headerBytes[11] = (byte) (i2 >>> 24);
        this.headerBytes[10] = (byte) (i2 >>> 16);
        if (this.headerBytes.length < 12) {
            Throwable th4 = th2;
            new ZipException("invalid header bytes generated, cannot perform standard encryption");
            throw th4;
        }
        int encryptData = encryptData(this.headerBytes);
    }

    public int encryptData(byte[] bArr) throws ZipException {
        Throwable th;
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            return encryptData(bArr2, 0, bArr2.length);
        }
        Throwable th2 = th;
        new NullPointerException();
        throw th2;
    }

    public int encryptData(byte[] bArr, int i, int i2) throws ZipException {
        Throwable th;
        Throwable th2;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (i4 < 0) {
            Throwable th3 = th2;
            new ZipException("invalid length specified to decrpyt data");
            throw th3;
        }
        int i5 = i3;
        while (i5 < i3 + i4) {
            try {
                bArr2[i5] = encryptByte(bArr2[i5]);
                i5++;
            } catch (Exception e) {
                Exception exc = e;
                Throwable th4 = th;
                new ZipException((Throwable) exc);
                throw th4;
            }
        }
        return i4;
    }

    /* access modifiers changed from: protected */
    public byte encryptByte(byte b) {
        byte b2 = b;
        byte decryptByte = (byte) (b2 ^ (this.zipCryptoEngine.decryptByte() & 255));
        this.zipCryptoEngine.updateKeys(b2);
        return decryptByte;
    }

    /* access modifiers changed from: protected */
    public byte[] generateRandomBytes(int i) throws ZipException {
        Random random;
        Throwable th;
        int i2 = i;
        if (i2 <= 0) {
            Throwable th2 = th;
            new ZipException("size is either 0 or less than 0, cannot generate header for standard encryptor");
            throw th2;
        }
        byte[] bArr = new byte[i2];
        new Random();
        Random random2 = random;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            bArr[i3] = encryptByte((byte) random2.nextInt(256));
        }
        return bArr;
    }

    public byte[] getHeaderBytes() {
        return this.headerBytes;
    }
}
