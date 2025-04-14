package net.lingala.zip4j.model;

public class Zip64EndCentralDirLocator {
    private int noOfDiskStartOfZip64EndOfCentralDirRec;
    private long offsetZip64EndOfCentralDirRec;
    private long signature;
    private int totNumberOfDiscs;

    public Zip64EndCentralDirLocator() {
    }

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        long j2 = j;
        this.signature = j2;
    }

    public int getNoOfDiskStartOfZip64EndOfCentralDirRec() {
        return this.noOfDiskStartOfZip64EndOfCentralDirRec;
    }

    public void setNoOfDiskStartOfZip64EndOfCentralDirRec(int i) {
        int i2 = i;
        this.noOfDiskStartOfZip64EndOfCentralDirRec = i2;
    }

    public long getOffsetZip64EndOfCentralDirRec() {
        return this.offsetZip64EndOfCentralDirRec;
    }

    public void setOffsetZip64EndOfCentralDirRec(long j) {
        long j2 = j;
        this.offsetZip64EndOfCentralDirRec = j2;
    }

    public int getTotNumberOfDiscs() {
        return this.totNumberOfDiscs;
    }

    public void setTotNumberOfDiscs(int i) {
        int i2 = i;
        this.totNumberOfDiscs = i2;
    }
}
