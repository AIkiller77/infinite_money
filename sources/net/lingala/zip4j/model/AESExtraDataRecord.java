package net.lingala.zip4j.model;

public class AESExtraDataRecord {
    private int aesStrength = -1;
    private int compressionMethod = -1;
    private int dataSize = -1;
    private long signature = -1;
    private String vendorID = null;
    private int versionNumber = -1;

    public AESExtraDataRecord() {
    }

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        long j2 = j;
        this.signature = j2;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(int i) {
        int i2 = i;
        this.dataSize = i2;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(int i) {
        int i2 = i;
        this.versionNumber = i2;
    }

    public String getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(String str) {
        String str2 = str;
        this.vendorID = str2;
    }

    public int getAesStrength() {
        return this.aesStrength;
    }

    public void setAesStrength(int i) {
        int i2 = i;
        this.aesStrength = i2;
    }

    public int getCompressionMethod() {
        return this.compressionMethod;
    }

    public void setCompressionMethod(int i) {
        int i2 = i;
        this.compressionMethod = i2;
    }
}
