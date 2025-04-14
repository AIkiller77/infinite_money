package net.lingala.zip4j.model;

public class ExtraDataRecord {
    private byte[] data;
    private long header;
    private int sizeOfData;

    public ExtraDataRecord() {
    }

    public long getHeader() {
        return this.header;
    }

    public void setHeader(long j) {
        long j2 = j;
        this.header = j2;
    }

    public int getSizeOfData() {
        return this.sizeOfData;
    }

    public void setSizeOfData(int i) {
        int i2 = i;
        this.sizeOfData = i2;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.data = bArr2;
    }
}
