package net.lingala.zip4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.crypto.AESDecrypter;
import net.lingala.zip4j.crypto.IDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.unzip.UnzipEngine;

public class PartInputStream extends BaseInputStream {
    private byte[] aesBlockByte = new byte[16];
    private int aesBytesReturned = 0;
    private long bytesRead;
    private int count = -1;
    private IDecrypter decrypter;
    private boolean isAESEncryptedFile = false;
    private long length;
    private byte[] oneByteBuff = new byte[1];
    private RandomAccessFile raf;
    private UnzipEngine unzipEngine;

    public PartInputStream(RandomAccessFile randomAccessFile, long j, long j2, UnzipEngine unzipEngine2) {
        long j3 = j;
        UnzipEngine unzipEngine3 = unzipEngine2;
        this.raf = randomAccessFile;
        this.unzipEngine = unzipEngine3;
        this.decrypter = unzipEngine3.getDecrypter();
        this.bytesRead = 0;
        this.length = j2;
        this.isAESEncryptedFile = unzipEngine3.getFileHeader().isEncrypted() && unzipEngine3.getFileHeader().getEncryptionMethod() == 99;
    }

    public int available() {
        long j = this.length - this.bytesRead;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    public int read() throws IOException {
        if (this.bytesRead >= this.length) {
            return -1;
        }
        if (this.isAESEncryptedFile) {
            if (this.aesBytesReturned == 0 || this.aesBytesReturned == 16) {
                if (read(this.aesBlockByte) == -1) {
                    return -1;
                }
                this.aesBytesReturned = 0;
            }
            byte[] bArr = this.aesBlockByte;
            int i = this.aesBytesReturned;
            int i2 = i + 1;
            this.aesBytesReturned = i2;
            return bArr[i] & 255;
        }
        return read(this.oneByteBuff, 0, 1) == -1 ? -1 : this.oneByteBuff[0] & 255;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        return read(bArr2, 0, bArr2.length);
    }

    /* JADX INFO: finally extract failed */
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Throwable th;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (((long) i4) > this.length - this.bytesRead) {
            i4 = (int) (this.length - this.bytesRead);
            if (i4 == 0) {
                checkAndReadAESMacBytes();
                return -1;
            }
        }
        if ((this.unzipEngine.getDecrypter() instanceof AESDecrypter) && this.bytesRead + ((long) i4) < this.length && i4 % 16 != 0) {
            i4 -= i4 % 16;
        }
        RandomAccessFile randomAccessFile = this.raf;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        synchronized (randomAccessFile) {
            try {
                this.count = this.raf.read(bArr2, i3, i4);
                if (this.count < i4 && this.unzipEngine.getZipModel().isSplitArchive()) {
                    this.raf.close();
                    this.raf = this.unzipEngine.startNextSplitFile();
                    if (this.count < 0) {
                        this.count = 0;
                    }
                    int read = this.raf.read(bArr2, this.count, i4 - this.count);
                    if (read > 0) {
                        this.count += read;
                    }
                }
                if (this.count > 0) {
                    if (this.decrypter != null) {
                        try {
                            int decryptData = this.decrypter.decryptData(bArr2, i3, this.count);
                        } catch (ZipException e) {
                            ZipException zipException = e;
                            Throwable th2 = th;
                            new IOException(zipException.getMessage());
                            throw th2;
                        }
                    }
                    this.bytesRead += (long) this.count;
                }
                if (this.bytesRead >= this.length) {
                    checkAndReadAESMacBytes();
                }
                return this.count;
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    RandomAccessFile randomAccessFile3 = randomAccessFile2;
                    throw th4;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkAndReadAESMacBytes() throws IOException {
        Throwable th;
        if (this.isAESEncryptedFile && this.decrypter != null && (this.decrypter instanceof AESDecrypter) && ((AESDecrypter) this.decrypter).getStoredMac() == null) {
            byte[] bArr = new byte[10];
            int read = this.raf.read(bArr);
            if (read != 10) {
                if (this.unzipEngine.getZipModel().isSplitArchive()) {
                    this.raf.close();
                    this.raf = this.unzipEngine.startNextSplitFile();
                    int read2 = read + this.raf.read(bArr, read, 10 - read);
                } else {
                    Throwable th2 = th;
                    new IOException("Error occured while reading stored AES authentication bytes");
                    throw th2;
                }
            }
            ((AESDecrypter) this.unzipEngine.getDecrypter()).setStoredMac(bArr);
        }
    }

    public long skip(long j) throws IOException {
        Throwable th;
        long j2 = j;
        if (j2 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        if (j2 > this.length - this.bytesRead) {
            j2 = this.length - this.bytesRead;
        }
        this.bytesRead += j2;
        return j2;
    }

    public void close() throws IOException {
        this.raf.close();
    }

    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    public UnzipEngine getUnzipEngine() {
        return this.unzipEngine;
    }
}
