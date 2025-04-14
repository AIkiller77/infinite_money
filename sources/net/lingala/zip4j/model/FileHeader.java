package net.lingala.zip4j.model;

import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.unzip.Unzip;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class FileHeader {
    private AESExtraDataRecord aesExtraDataRecord;
    private long compressedSize;
    private int compressionMethod;
    private long crc32 = 0;
    private byte[] crcBuff;
    private boolean dataDescriptorExists;
    private int diskNumberStart;
    private int encryptionMethod = -1;
    private byte[] externalFileAttr;
    private ArrayList extraDataRecords;
    private int extraFieldLength;
    private String fileComment;
    private int fileCommentLength;
    private String fileName;
    private int fileNameLength;
    private boolean fileNameUTF8Encoded;
    private byte[] generalPurposeFlag;
    private byte[] internalFileAttr;
    private boolean isDirectory;
    private boolean isEncrypted;
    private int lastModFileTime;
    private long offsetLocalHeader;
    private char[] password;
    private int signature;
    private long uncompressedSize = 0;
    private int versionMadeBy;
    private int versionNeededToExtract;
    private Zip64ExtendedInfo zip64ExtendedInfo;

    public FileHeader() {
    }

    public int getSignature() {
        return this.signature;
    }

    public void setSignature(int i) {
        int i2 = i;
        this.signature = i2;
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
        return this.crc32 & InternalZipConstants.ZIP_64_LIMIT;
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

    public int getFileCommentLength() {
        return this.fileCommentLength;
    }

    public void setFileCommentLength(int i) {
        int i2 = i;
        this.fileCommentLength = i2;
    }

    public int getDiskNumberStart() {
        return this.diskNumberStart;
    }

    public void setDiskNumberStart(int i) {
        int i2 = i;
        this.diskNumberStart = i2;
    }

    public byte[] getInternalFileAttr() {
        return this.internalFileAttr;
    }

    public void setInternalFileAttr(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.internalFileAttr = bArr2;
    }

    public byte[] getExternalFileAttr() {
        return this.externalFileAttr;
    }

    public void setExternalFileAttr(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.externalFileAttr = bArr2;
    }

    public long getOffsetLocalHeader() {
        return this.offsetLocalHeader;
    }

    public void setOffsetLocalHeader(long j) {
        long j2 = j;
        this.offsetLocalHeader = j2;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        String str2 = str;
        this.fileName = str2;
    }

    public String getFileComment() {
        return this.fileComment;
    }

    public void setFileComment(String str) {
        String str2 = str;
        this.fileComment = str2;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public void setDirectory(boolean z) {
        boolean z2 = z;
        this.isDirectory = z2;
    }

    public void extractFile(ZipModel zipModel, String str, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        extractFile(zipModel, str, (UnzipParameters) null, progressMonitor, z);
    }

    public void extractFile(ZipModel zipModel, String str, UnzipParameters unzipParameters, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        extractFile(zipModel, str, unzipParameters, (String) null, progressMonitor, z);
    }

    public void extractFile(ZipModel zipModel, String str, UnzipParameters unzipParameters, String str2, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Unzip unzip;
        Throwable th;
        Throwable th2;
        Throwable th3;
        ZipModel zipModel2 = zipModel;
        String str3 = str;
        UnzipParameters unzipParameters2 = unzipParameters;
        String str4 = str2;
        ProgressMonitor progressMonitor2 = progressMonitor;
        boolean z2 = z;
        if (zipModel2 == null) {
            Throwable th4 = th3;
            new ZipException("input zipModel is null");
            throw th4;
        } else if (!Zip4jUtil.checkOutputFolder(str3)) {
            Throwable th5 = th2;
            new ZipException("Invalid output path");
            throw th5;
        } else if (this == null) {
            Throwable th6 = th;
            new ZipException("invalid file header");
            throw th6;
        } else {
            new Unzip(zipModel2);
            unzip.extractFile(this, str3, unzipParameters2, str4, progressMonitor2, z2);
        }
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

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] cArr) {
        char[] cArr2 = cArr;
        this.password = cArr2;
    }

    public byte[] getCrcBuff() {
        return this.crcBuff;
    }

    public void setCrcBuff(byte[] bArr) {
        byte[] bArr2 = bArr;
        this.crcBuff = bArr2;
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

    public boolean isFileNameUTF8Encoded() {
        return this.fileNameUTF8Encoded;
    }

    public void setFileNameUTF8Encoded(boolean z) {
        boolean z2 = z;
        this.fileNameUTF8Encoded = z2;
    }
}
