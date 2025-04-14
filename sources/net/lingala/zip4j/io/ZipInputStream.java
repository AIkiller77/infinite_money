package net.lingala.zip4j.io;

import java.io.IOException;
import java.io.InputStream;
import net.lingala.zip4j.exception.ZipException;

public class ZipInputStream extends InputStream {
    private BaseInputStream is;

    public ZipInputStream(BaseInputStream baseInputStream) {
        this.is = baseInputStream;
    }

    public int read() throws IOException {
        int read = this.is.read();
        if (read != -1) {
            this.is.getUnzipEngine().updateCRC(read);
        }
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        return read(bArr2, 0, bArr2.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int read = this.is.read(bArr2, i3, i2);
        if (read > 0 && this.is.getUnzipEngine() != null) {
            this.is.getUnzipEngine().updateCRC(bArr2, i3, read);
        }
        return read;
    }

    public void close() throws IOException {
        close(false);
    }

    public void close(boolean z) throws IOException {
        Throwable th;
        boolean z2 = z;
        try {
            this.is.close();
            if (!z2 && this.is.getUnzipEngine() != null) {
                this.is.getUnzipEngine().checkCRC();
            }
        } catch (ZipException e) {
            ZipException zipException = e;
            Throwable th2 = th;
            new IOException(zipException.getMessage());
            throw th2;
        }
    }

    public int available() throws IOException {
        return this.is.available();
    }

    public long skip(long j) throws IOException {
        return this.is.skip(j);
    }
}
