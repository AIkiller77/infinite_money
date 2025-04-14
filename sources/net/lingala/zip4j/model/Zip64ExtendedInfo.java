package net.lingala.zip4j.model;

public class Zip64ExtendedInfo {
    private long compressedSize = -1;
    private int diskNumberStart = -1;
    private int header;
    private long offsetLocalHeader = -1;
    private int size;
    private long unCompressedSize = -1;

    public Zip64ExtendedInfo() {
    }

    public int getHeader() {
        return this.header;
    }

    public void setHeader(int i) {
        int i2 = i;
        this.header = i2;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        int i2 = i;
        this.size = i2;
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(long j) {
        long j2 = j;
        this.compressedSize = j2;
    }

    public long getUnCompressedSize() {
        return this.unCompressedSize;
    }

    public void setUnCompressedSize(long j) {
        long j2 = j;
        this.unCompressedSize = j2;
    }

    public long getOffsetLocalHeader() {
        return this.offsetLocalHeader;
    }

    public void setOffsetLocalHeader(long j) {
        long j2 = j;
        this.offsetLocalHeader = j2;
    }

    public int getDiskNumberStart() {
        return this.diskNumberStart;
    }

    public void setDiskNumberStart(int i) {
        int i2 = i;
        this.diskNumberStart = i2;
    }
}
