package net.lingala.zip4j.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;

public class DeflaterOutputStream extends CipherOutputStream {
    private byte[] buff = new byte[4096];
    protected Deflater deflater;
    private boolean firstBytesRead = false;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeflaterOutputStream(OutputStream outputStream, ZipModel zipModel) {
        super(outputStream, zipModel);
        Deflater deflater2;
        new Deflater();
        this.deflater = deflater2;
    }

    public void putNextEntry(File file, ZipParameters zipParameters) throws ZipException {
        Throwable th;
        ZipParameters zipParameters2 = zipParameters;
        super.putNextEntry(file, zipParameters2);
        if (zipParameters2.getCompressionMethod() == 8) {
            this.deflater.reset();
            if ((zipParameters2.getCompressionLevel() < 0 || zipParameters2.getCompressionLevel() > 9) && zipParameters2.getCompressionLevel() != -1) {
                Throwable th2 = th;
                new ZipException("invalid compression level for deflater. compression level should be in the range of 0-9");
                throw th2;
            }
            this.deflater.setLevel(zipParameters2.getCompressionLevel());
        }
    }

    public void write(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        write(bArr2, 0, bArr2.length);
    }

    private void deflate() throws IOException {
        int deflate = this.deflater.deflate(this.buff, 0, this.buff.length);
        if (deflate > 0) {
            if (this.deflater.finished()) {
                if (deflate != 4) {
                    if (deflate < 4) {
                        decrementCompressedFileSize(4 - deflate);
                        return;
                    }
                    deflate -= 4;
                } else {
                    return;
                }
            }
            if (!this.firstBytesRead) {
                super.write(this.buff, 2, deflate - 2);
                this.firstBytesRead = true;
                return;
            }
            super.write(this.buff, 0, deflate);
        }
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this.zipParameters.getCompressionMethod() != 8) {
            super.write(bArr2, i3, i4);
            return;
        }
        this.deflater.setInput(bArr2, i3, i4);
        while (!this.deflater.needsInput()) {
            deflate();
        }
    }

    public void closeEntry() throws IOException, ZipException {
        if (this.zipParameters.getCompressionMethod() == 8) {
            if (!this.deflater.finished()) {
                this.deflater.finish();
                while (!this.deflater.finished()) {
                    deflate();
                }
            }
            this.firstBytesRead = false;
        }
        super.closeEntry();
    }

    public void finish() throws IOException, ZipException {
        super.finish();
    }
}
