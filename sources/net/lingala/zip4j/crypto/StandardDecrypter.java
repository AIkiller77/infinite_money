package net.lingala.zip4j.crypto;

import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class StandardDecrypter implements IDecrypter {
    private byte[] crc = new byte[4];
    private FileHeader fileHeader;
    private ZipCryptoEngine zipCryptoEngine;

    public StandardDecrypter(FileHeader fileHeader2, byte[] bArr) throws ZipException {
        ZipCryptoEngine zipCryptoEngine2;
        Throwable th;
        FileHeader fileHeader3 = fileHeader2;
        byte[] bArr2 = bArr;
        if (fileHeader3 == null) {
            Throwable th2 = th;
            new ZipException("one of more of the input parameters were null in StandardDecryptor");
            throw th2;
        }
        this.fileHeader = fileHeader3;
        new ZipCryptoEngine();
        this.zipCryptoEngine = zipCryptoEngine2;
        init(bArr2);
    }

    public int decryptData(byte[] bArr) throws ZipException {
        byte[] bArr2 = bArr;
        return decryptData(bArr2, 0, bArr2.length);
    }

    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        Throwable th;
        Throwable th2;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (i3 < 0 || i4 < 0) {
            Throwable th3 = th;
            new ZipException("one of the input parameters were null in standard decrpyt data");
            throw th3;
        }
        int i5 = i3;
        while (i5 < i3 + i4) {
            try {
                byte decryptByte = ((bArr2[i5] & 255) ^ this.zipCryptoEngine.decryptByte()) & 255;
                this.zipCryptoEngine.updateKeys((byte) decryptByte);
                bArr2[i5] = (byte) decryptByte;
                i5++;
            } catch (Exception e) {
                Exception exc = e;
                Throwable th4 = th2;
                new ZipException((Throwable) exc);
                throw th4;
            }
        }
        return i4;
    }

    public void init(byte[] bArr) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        byte[] bArr2 = bArr;
        byte[] crcBuff = this.fileHeader.getCrcBuff();
        this.crc[3] = (byte) (crcBuff[3] & 255);
        this.crc[2] = (byte) ((crcBuff[3] >> 8) & 255);
        this.crc[1] = (byte) ((crcBuff[3] >> 16) & 255);
        this.crc[0] = (byte) ((crcBuff[3] >> 24) & 255);
        if (this.crc[2] > 0 || this.crc[1] > 0 || this.crc[0] > 0) {
            Throwable th4 = th;
            new IllegalStateException("Invalid CRC in File Header");
            throw th4;
        } else if (this.fileHeader.getPassword() == null || this.fileHeader.getPassword().length <= 0) {
            Throwable th5 = th2;
            new ZipException("Wrong password!", 5);
            throw th5;
        } else {
            this.zipCryptoEngine.initKeys(this.fileHeader.getPassword());
            try {
                byte b = bArr2[0];
                for (int i = 0; i < 12; i++) {
                    this.zipCryptoEngine.updateKeys((byte) (b ^ this.zipCryptoEngine.decryptByte()));
                    if (i + 1 != 12) {
                        b = bArr2[i + 1];
                    }
                }
            } catch (Exception e) {
                Exception exc = e;
                Throwable th6 = th3;
                new ZipException((Throwable) exc);
                throw th6;
            }
        }
    }
}
