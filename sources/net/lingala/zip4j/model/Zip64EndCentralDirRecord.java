package net.lingala.zip4j.model;

public class Zip64EndCentralDirRecord {
    private byte[] extensibleDataSector;
    private int noOfThisDisk;
    private int noOfThisDiskStartOfCentralDir;
    private long offsetStartCenDirWRTStartDiskNo;
    private long signature;
    private long sizeOfCentralDir;
    private long sizeOfZip64EndCentralDirRec;
    private long totNoOfEntriesInCentralDir;
    private long totNoOfEntriesInCentralDirOnThisDisk;
    private int versionMadeBy;
    private int versionNeededToExtract;

    public Zip64EndCentralDirRecord() {
    }

    public long getSignature() {
        return this.signature;
    }

    public void setSignature(long j) {
        long j2 = j;
        this.signature = j2;
    }

    public long getSizeOfZip64EndCentralDirRec() {
        return this.sizeOfZip64EndCentralDirRec;
    }

    public void setSizeOfZip64EndCentralDirRec(long j) {
        long j2 = j;
        this.sizeOfZip64EndCentralDirRec = j2;
    }

    public int getVersionMadeBy() {
        return this.versionMadeBy;
    }

    public void setVersionMadeBy(int i) {
        int i2 = i;
        this.versionMadeBy = i2;
    }

    public int getVersionNeededToExtract() {
        return this.versionNeededToExtract;
    }

    public void setVersionNeededToExtract(int i) {
        int i2 = i;
        this.versionNeededToExtract = i2;
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

    public long getTotNoOfEntriesInCentralDirOnThisDisk() {
        return this.totNoOfEntriesInCentralDirOnThisDisk;
    }

    public void setTotNoOfEntriesInCentralDirOnThisDisk(long j) {
        long j2 = j;
        this.totNoOfEntriesInCentralDirOnThisDisk = j2;
    }

    public long getTotNoOfEntriesInCentralDir() {
        return this.totNoOfEntriesInCentralDir;
    }

    public void setTotNoOfEntriesInCentralDir(long j) {
        long j2 = j;
        this.totNoOfEntriesInCentralDir = j2;
    }

    public long getSizeOfCentralDir() {
        return this.sizeOfCentralDir;
    }

    public void setSizeOfCentralDir(long j) {
        long j2 = j;
        this.sizeOfCentralDir = j2;
    }

    public long getOffsetStartCenDirWRTStartDiskNo() {
        return this.offsetStartCenDirWRTStartDiskNo;
    }

    public void setOffsetStartCenDirWRTStartDiskNo(long j) {
        long j2 = j;
        this.offsetStartCenDirWRTStartDiskNo = j2;
    }

    public byte[] getExtensibleDataSector() {
        return this.extensibleDataSector;
    }

    public void setExtensibleDataSector(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.extensibleDataSector = bArr2;
    }
}
