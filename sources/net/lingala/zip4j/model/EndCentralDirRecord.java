package net.lingala.zip4j.model;

public class EndCentralDirRecord {
    private String comment;
    private byte[] commentBytes;
    private int commentLength;
    private int noOfThisDisk;
    private int noOfThisDiskStartOfCentralDir;
    private long offsetOfStartOfCentralDir;
    private long signature;
    private int sizeOfCentralDir;
    private int totNoOfEntriesInCentralDir;
    private int totNoOfEntriesInCentralDirOnThisDisk;

    public EndCentralDirRecord() {
    }

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        long j2 = j;
        this.signature = j2;
    }

    public int getNoOfThisDisk() {
        return this.noOfThisDisk;
    }

    public void setNoOfThisDisk(int i) {
        int i2 = i;
        this.noOfThisDisk = i2;
    }

    public int getNoOfThisDiskStartOfCentralDir() {
        return this.noOfThisDiskStartOfCentralDir;
    }

    public void setNoOfThisDiskStartOfCentralDir(int i) {
        int i2 = i;
        this.noOfThisDiskStartOfCentralDir = i2;
    }

    public int getTotNoOfEntriesInCentralDirOnThisDisk() {
        return this.totNoOfEntriesInCentralDirOnThisDisk;
    }

    public void setTotNoOfEntriesInCentralDirOnThisDisk(int i) {
        int i2 = i;
        this.totNoOfEntriesInCentralDirOnThisDisk = i2;
    }

    public int getTotNoOfEntriesInCentralDir() {
        return this.totNoOfEntriesInCentralDir;
    }

    public void setTotNoOfEntriesInCentralDir(int i) {
        int i2 = i;
        this.totNoOfEntriesInCentralDir = i2;
    }

    public int getSizeOfCentralDir() {
        return this.sizeOfCentralDir;
    }

    public void setSizeOfCentralDir(int i) {
        int i2 = i;
        this.sizeOfCentralDir = i2;
    }

    public long getOffsetOfStartOfCentralDir() {
        return this.offsetOfStartOfCentralDir;
    }

    public void setOffsetOfStartOfCentralDir(long j) {
        long j2 = j;
        this.offsetOfStartOfCentralDir = j2;
    }

    public int getCommentLength() {
        return this.commentLength;
    }

    public void setCommentLength(int i) {
        int i2 = i;
        this.commentLength = i2;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        String str2 = str;
        this.comment = str2;
    }

    public byte[] getCommentBytes() {
        return this.commentBytes;
    }

    public void setCommentBytes(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.commentBytes = bArr2;
    }
}
