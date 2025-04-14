package net.lingala.zip4j.model;

public class DataDescriptor {
    private int compressedSize;
    private String crc32;
    private int uncompressedSize;

    public DataDescriptor() {
    }

    public String getCrc32() {
        return this.crc32;
    }

    public void setCrc32(String str) {
        String str2 = str;
        this.crc32 = str2;
    }

    public int getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(int i) {
        int i2 = i;
        this.compressedSize = i2;
    }

    public int getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void setUncompressedSize(int i) {
        int i2 = i;
        this.uncompressedSize = i2;
    }
}
