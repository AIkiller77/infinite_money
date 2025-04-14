package net.lingala.zip4j.io;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.Inflater;
import net.lingala.zip4j.unzip.UnzipEngine;

public class InflaterInputStream extends PartInputStream {
    private byte[] buff;
    private long bytesWritten;
    private Inflater inflater;
    private byte[] oneByteBuff = new byte[1];
    private long uncompressedSize;
    private UnzipEngine unzipEngine;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InflaterInputStream(java.io.RandomAccessFile r17, long r18, long r20, net.lingala.zip4j.unzip.UnzipEngine r22) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r4 = r20
            r6 = r22
            r7 = r0
            r8 = r1
            r9 = r2
            r11 = r4
            r13 = r6
            r7.<init>(r8, r9, r11, r13)
            r7 = r0
            r8 = 1
            byte[] r8 = new byte[r8]
            r7.oneByteBuff = r8
            r7 = r0
            java.util.zip.Inflater r8 = new java.util.zip.Inflater
            r14 = r8
            r8 = r14
            r9 = r14
            r10 = 1
            r9.<init>(r10)
            r7.inflater = r8
            r7 = r0
            r8 = 4096(0x1000, float:5.74E-42)
            byte[] r8 = new byte[r8]
            r7.buff = r8
            r7 = r0
            r8 = r6
            r7.unzipEngine = r8
            r7 = r0
            r8 = 0
            r7.bytesWritten = r8
            r7 = r0
            r8 = r6
            net.lingala.zip4j.model.FileHeader r8 = r8.getFileHeader()
            long r8 = r8.getUncompressedSize()
            r7.uncompressedSize = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.io.InflaterInputStream.<init>(java.io.RandomAccessFile, long, long, net.lingala.zip4j.unzip.UnzipEngine):void");
    }

    public int read() throws IOException {
        return read(this.oneByteBuff, 0, 1) == -1 ? -1 : this.oneByteBuff[0] & 255;
    }

    public int read(byte[] bArr) throws IOException {
        Throwable th;
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            return read(bArr2, 0, bArr2.length);
        }
        Throwable th2 = th;
        new NullPointerException("input buffer is null");
        throw th2;
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 151 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r14, int r15, int r16) throws java.io.IOException {
        /*
            r13 = this;
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r7 = r2
            if (r7 != 0) goto L_0x0013
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r12 = r7
            r7 = r12
            r8 = r12
            java.lang.String r9 = "input buffer is null"
            r8.<init>(r9)
            throw r7
        L_0x0013:
            r7 = r3
            if (r7 < 0) goto L_0x0020
            r7 = r4
            if (r7 < 0) goto L_0x0020
            r7 = r4
            r8 = r2
            int r8 = r8.length
            r9 = r3
            int r8 = r8 - r9
            if (r7 <= r8) goto L_0x0029
        L_0x0020:
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            throw r7
        L_0x0029:
            r7 = r4
            if (r7 != 0) goto L_0x002f
            r7 = 0
            r1 = r7
        L_0x002e:
            return r1
        L_0x002f:
            r7 = r1
            long r7 = r7.bytesWritten     // Catch:{ DataFormatException -> 0x0084 }
            r9 = r1
            long r9 = r9.uncompressedSize     // Catch:{ DataFormatException -> 0x0084 }
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 < 0) goto L_0x004d
            r7 = r1
            r7.finishInflating()     // Catch:{ DataFormatException -> 0x0084 }
            r7 = -1
            r1 = r7
            goto L_0x002e
        L_0x0040:
            r7 = r1
            java.util.zip.Inflater r7 = r7.inflater     // Catch:{ DataFormatException -> 0x0084 }
            boolean r7 = r7.needsInput()     // Catch:{ DataFormatException -> 0x0084 }
            if (r7 == 0) goto L_0x004d
            r7 = r1
            r7.fill()     // Catch:{ DataFormatException -> 0x0084 }
        L_0x004d:
            r7 = r1
            java.util.zip.Inflater r7 = r7.inflater     // Catch:{ DataFormatException -> 0x0084 }
            r8 = r2
            r9 = r3
            r10 = r4
            int r7 = r7.inflate(r8, r9, r10)     // Catch:{ DataFormatException -> 0x0084 }
            r12 = r7
            r7 = r12
            r8 = r12
            r5 = r8
            if (r7 != 0) goto L_0x0076
            r7 = r1
            java.util.zip.Inflater r7 = r7.inflater     // Catch:{ DataFormatException -> 0x0084 }
            boolean r7 = r7.finished()     // Catch:{ DataFormatException -> 0x0084 }
            if (r7 != 0) goto L_0x006f
            r7 = r1
            java.util.zip.Inflater r7 = r7.inflater     // Catch:{ DataFormatException -> 0x0084 }
            boolean r7 = r7.needsDictionary()     // Catch:{ DataFormatException -> 0x0084 }
            if (r7 == 0) goto L_0x0040
        L_0x006f:
            r7 = r1
            r7.finishInflating()     // Catch:{ DataFormatException -> 0x0084 }
            r7 = -1
            r1 = r7
            goto L_0x002e
        L_0x0076:
            r7 = r1
            r12 = r7
            r7 = r12
            r8 = r12
            long r8 = r8.bytesWritten     // Catch:{ DataFormatException -> 0x0084 }
            r10 = r5
            long r10 = (long) r10     // Catch:{ DataFormatException -> 0x0084 }
            long r8 = r8 + r10
            r7.bytesWritten = r8     // Catch:{ DataFormatException -> 0x0084 }
            r7 = r5
            r1 = r7
            goto L_0x002e
        L_0x0084:
            r7 = move-exception
            r5 = r7
            java.lang.String r7 = "Invalid ZLIB data format"
            r6 = r7
            r7 = r5
            java.lang.String r7 = r7.getMessage()
            if (r7 == 0) goto L_0x0096
            r7 = r5
            java.lang.String r7 = r7.getMessage()
            r6 = r7
        L_0x0096:
            r7 = r1
            net.lingala.zip4j.unzip.UnzipEngine r7 = r7.unzipEngine
            if (r7 == 0) goto L_0x00cd
            r7 = r1
            net.lingala.zip4j.unzip.UnzipEngine r7 = r7.unzipEngine
            net.lingala.zip4j.model.LocalFileHeader r7 = r7.getLocalFileHeader()
            boolean r7 = r7.isEncrypted()
            if (r7 == 0) goto L_0x00cd
            r7 = r1
            net.lingala.zip4j.unzip.UnzipEngine r7 = r7.unzipEngine
            net.lingala.zip4j.model.LocalFileHeader r7 = r7.getLocalFileHeader()
            int r7 = r7.getEncryptionMethod()
            if (r7 != 0) goto L_0x00cd
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            r8 = r6
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = " - Wrong Password?"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6 = r7
        L_0x00cd:
            java.io.IOException r7 = new java.io.IOException
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r6
            r8.<init>(r9)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.io.InflaterInputStream.read(byte[], int, int):int");
    }

    private void finishInflating() throws IOException {
        do {
        } while (super.read(new byte[1024], 0, 1024) != -1);
        checkAndReadAESMacBytes();
    }

    private void fill() throws IOException {
        Throwable th;
        int read = super.read(this.buff, 0, this.buff.length);
        if (read == -1) {
            Throwable th2 = th;
            new EOFException("Unexpected end of ZLIB input stream");
            throw th2;
        }
        this.inflater.setInput(this.buff, 0, read);
    }

    public long skip(long j) throws IOException {
        Throwable th;
        long j2 = j;
        if (j2 < 0) {
            Throwable th2 = th;
            new IllegalArgumentException("negative skip length");
            throw th2;
        }
        int min = (int) Math.min(j2, 2147483647L);
        int i = 0;
        byte[] bArr = new byte[512];
        while (i < min) {
            int i2 = min - i;
            if (i2 > bArr.length) {
                i2 = bArr.length;
            }
            int read = read(bArr, 0, i2);
            if (read == -1) {
                break;
            }
            i += read;
        }
        return (long) i;
    }

    public void seek(long j) throws IOException {
        super.seek(j);
    }

    public int available() {
        return this.inflater.finished() ? 0 : 1;
    }

    public void close() throws IOException {
        this.inflater.end();
        super.close();
    }

    public UnzipEngine getUnzipEngine() {
        return super.getUnzipEngine();
    }
}
