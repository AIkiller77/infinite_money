package net.lingala.zip4j.model;

import java.util.ArrayList;

public class LocalFileHeader {
    private AESExtraDataRecord aesExtraDataRecord;
    private long compressedSize;
    private int compressionMethod;
    private long crc32 = 0;
    private byte[] crcBuff;
    private boolean dataDescriptorExists;
    private int encryptionMethod = -1;
    private ArrayList extraDataRecords;
    private byte[] extraField;
    private int extraFieldLength;
    private String fileName;
    private int fileNameLength;
    private boolean fileNameUTF8Encoded;
    private byte[] generalPurposeFlag;
    private boolean isEncrypted;
    private int lastModFileTime;
    private long offsetStartOfData;
    private char[] password;
    private int signature;
    private long uncompressedSize = 0;
    private int versionNeededToExtract;
    private boolean writeComprSizeInZip64ExtraRecord = false;
    private Zip64ExtendedInfo zip64ExtendedInfo;

    public LocalFileHeader() {
    }

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int i) {
        int i2 = i;
        this.signature = i2;
    }

    public int getVersionNeededToExtract() {
        return this.versionNeededToExtract;
    }

    public void setVersionNeededToExtract(int i) {
        int i2 = i;
        this.versionNeededToExtract = i2;
    }

    public byte[] getGeneralPurposeFlag() {
        return this.generalPurposeFlag;
    }

    public void setGeneralPurposeFlag(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.generalPurposeFlag = bArr2;
    }

    public int getCompressionMethod() {
        return this.compressionMethod;
    }

    public void setCompressionMethod(int i) {
        int i2 = i;
        this.compressionMethod = i2;
    }

    public int getLastModFileTime() {
        return this.lastModFileTime;
    }

    public void setLastModFileTime(int i) {
        int i2 = i;
        this.lastModFileTime = i2;
    }

    public long getCrc32() {
        return this.crc32;
    }

    public void setCrc32(long j) {
        long j2 = j;
        this.crc32 = j2;
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(long j) {
        long j2 = j;
        this.compressedSize = j2;
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void setUncompressedSize(long j) {
        long j2 = j;
        this.uncompressedSize = j2;
    }

    public int getFileNameLength() {
        return this.fileNameLength;
    }

    public void setFileNameLength(int i) {
        int i2 = i;
        this.fileNameLength = i2;
    }

    public int getExtraFieldLength() {
        return this.extraFieldLength;
    }

    public void setExtraFieldLength(int i) {
        int i2 = i;
        this.extraFieldLength = i2;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        String str2 = str;
        this.fileName = str2;
    }

    public byte[] getExtraField() {
        return this.extraField;
    }

    public void setExtraField(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.extraField = bArr2;
    }

    public long getOffsetStartOfData() {
        return this.offsetStartOfData;
    }

    public void setOffsetStartOfData(long j) {
        long j2 = j;
        this.offsetStartOfData = j2;
    }

    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    public void setEncrypted(boolean z) {
        boolean z2 = z;
        this.isEncrypted = z2;
    }

    public int getEncryptionMethod() {
        return this.encryptionMethod;
    }

    public void setEncryptionMethod(int i) {
        int i2 = i;
        this.encryptionMethod = i2;
    }

    public byte[] getCrcBuff() {
        return this.crcBuff;
    }

    public void setCrcBuff(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.crcBuff = bArr2;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        char[] cArr2 = cArr;
        this.password = cArr2;
    }

    public ArrayList getExtraDataRecords() {
        return this.extraDataRecords;
    }

    public void setExtraDataRecords(ArrayList arrayList) {
        ArrayList arrayList2 = arrayList;
        this.extraDataRecords = arrayList2;
    }

    public boolean isDataDescriptorExists() {
        return this.dataDescriptorExists;
    }

    public void setDataDescriptorExists(boolean z) {
        boolean z2 = z;
        this.dataDescriptorExists = z2;
    }

    public Zip64ExtendedInfo getZip64ExtendedInfo() {
        return this.zip64ExtendedInfo;
    }

    public void setZip64ExtendedInfo(Zip64ExtendedInfo zip64ExtendedInfo2) {
        Zip64ExtendedInfo zip64ExtendedInfo3 = zip64ExtendedInfo2;
        this.zip64ExtendedInfo = zip64ExtendedInfo3;
    }

    public AESExtraDataRecord getAesExtraDataRecord() {
        return this.aesExtraDataRecord;
    }

    public void setAesExtraDataRecord(AESExtraDataRecord aESExtraDataRecord) {
        AESExtraDataRecord aESExtraDataRecord2 = aESExtraDataRecord;
        this.aesExtraDataRecord = aESExtraDataRecord2;
    }

    public boolean isWriteComprSizeInZip64ExtraRecord() {
        return this.writeComprSizeInZip64ExtraRecord;
    }

    public void setWriteComprSizeInZip64ExtraRecord(boolean z) {
        boolean z2 = z;
        this.writeComprSizeInZip64ExtraRecord = z2;
    }

    public boolean isFileNameUTF8Encoded() {
        return this.fileNameUTF8Encoded;
    }

    public void setFileNameUTF8Encoded(boolean z) {
        boolean z2 = z;
        this.fileNameUTF8Encoded = z2;
    }
}
