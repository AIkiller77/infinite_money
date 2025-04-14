package net.lingala.zip4j.io;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.model.ZipModel;

public class ZipOutputStream extends DeflaterOutputStream {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ZipOutputStream(OutputStream outputStream) {
        this(outputStream, (ZipModel) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZipOutputStream(OutputStream outputStream, ZipModel zipModel) {
        super(outputStream, zipModel);
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void write(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        write(bArr2, 0, bArr2.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        this.crc.update(bArr2, i3, i4);
        updateTotalBytesRead(i4);
        super.write(bArr2, i3, i4);
    }
}
