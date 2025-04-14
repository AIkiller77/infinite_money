package net.lingala.zip4j.core;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.DigitalSignature;
import net.lingala.zip4j.model.EndCentralDirRecord;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndCentralDirLocator;
import net.lingala.zip4j.model.Zip64EndCentralDirRecord;
import net.lingala.zip4j.model.Zip64ExtendedInfo;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class HeaderReader {
    private RandomAccessFile zip4jRaf = null;
    private ZipModel zipModel;

    public HeaderReader(RandomAccessFile randomAccessFile) {
        this.zip4jRaf = randomAccessFile;
    }

    public ZipModel readAllHeaders() throws ZipException {
        return readAllHeaders((String) null);
    }

    public ZipModel readAllHeaders(String str) throws ZipException {
        ZipModel zipModel2;
        new ZipModel();
        this.zipModel = zipModel2;
        this.zipModel.setFileNameCharset(str);
        this.zipModel.setEndCentralDirRecord(readEndOfCentralDirectoryRecord());
        this.zipModel.setZip64EndCentralDirLocator(readZip64EndCentralDirLocator());
        if (this.zipModel.isZip64Format()) {
            this.zipModel.setZip64EndCentralDirRecord(readZip64EndCentralDirRec());
            if (this.zipModel.getZip64EndCentralDirRecord() == null || this.zipModel.getZip64EndCentralDirRecord().getNoOfThisDisk() <= 0) {
                this.zipModel.setSplitArchive(false);
            } else {
                this.zipModel.setSplitArchive(true);
            }
        }
        this.zipModel.setCentralDirectory(readCentralDirectory());
        return this.zipModel;
    }

    private EndCentralDirRecord readEndOfCentralDirectoryRecord() throws ZipException {
        Throwable th;
        EndCentralDirRecord endCentralDirRecord;
        String str;
        Throwable th2;
        Throwable th3;
        if (this.zip4jRaf == null) {
            Throwable th4 = th3;
            new ZipException("random access file was null", 3);
            throw th4;
        }
        try {
            byte[] bArr = new byte[4];
            long length = this.zip4jRaf.length() - 22;
            new EndCentralDirRecord();
            EndCentralDirRecord endCentralDirRecord2 = endCentralDirRecord;
            int i = 0;
            do {
                long j = length;
                length = j - 1;
                this.zip4jRaf.seek(j);
                i++;
                if (((long) Raw.readLeInt(this.zip4jRaf, bArr)) == InternalZipConstants.ENDSIG || i > 3000) {
                }
                long j2 = length;
                length = j2 - 1;
                this.zip4jRaf.seek(j2);
                i++;
                break;
            } while (i > 3000);
            if (((long) Raw.readIntLittleEndian(bArr, 0)) != InternalZipConstants.ENDSIG) {
                Throwable th5 = th2;
                new ZipException("zip headers not found. probably not a zip file");
                throw th5;
            }
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[2];
            endCentralDirRecord2.setSignature(InternalZipConstants.ENDSIG);
            byte[] readIntoBuff = readIntoBuff(this.zip4jRaf, bArr3);
            endCentralDirRecord2.setNoOfThisDisk(Raw.readShortLittleEndian(bArr3, 0));
            byte[] readIntoBuff2 = readIntoBuff(this.zip4jRaf, bArr3);
            endCentralDirRecord2.setNoOfThisDiskStartOfCentralDir(Raw.readShortLittleEndian(bArr3, 0));
            byte[] readIntoBuff3 = readIntoBuff(this.zip4jRaf, bArr3);
            endCentralDirRecord2.setTotNoOfEntriesInCentralDirOnThisDisk(Raw.readShortLittleEndian(bArr3, 0));
            byte[] readIntoBuff4 = readIntoBuff(this.zip4jRaf, bArr3);
            endCentralDirRecord2.setTotNoOfEntriesInCentralDir(Raw.readShortLittleEndian(bArr3, 0));
            byte[] readIntoBuff5 = readIntoBuff(this.zip4jRaf, bArr2);
            endCentralDirRecord2.setSizeOfCentralDir(Raw.readIntLittleEndian(bArr2, 0));
            byte[] readIntoBuff6 = readIntoBuff(this.zip4jRaf, bArr2);
            endCentralDirRecord2.setOffsetOfStartOfCentralDir(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr2), 0));
            byte[] readIntoBuff7 = readIntoBuff(this.zip4jRaf, bArr3);
            int readShortLittleEndian = Raw.readShortLittleEndian(bArr3, 0);
            endCentralDirRecord2.setCommentLength(readShortLittleEndian);
            if (readShortLittleEndian > 0) {
                byte[] bArr4 = new byte[readShortLittleEndian];
                byte[] readIntoBuff8 = readIntoBuff(this.zip4jRaf, bArr4);
                new String(bArr4);
                endCentralDirRecord2.setComment(str);
                endCentralDirRecord2.setCommentBytes(bArr4);
            } else {
                endCentralDirRecord2.setComment((String) null);
            }
            if (endCentralDirRecord2.getNoOfThisDisk() > 0) {
                this.zipModel.setSplitArchive(true);
            } else {
                this.zipModel.setSplitArchive(false);
            }
            return endCentralDirRecord2;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th6 = th;
            new ZipException("Probably not a zip file or a corrupted zip file", iOException, 4);
            throw th6;
        }
    }

    private CentralDirectory readCentralDirectory() throws ZipException {
        Throwable th;
        CentralDirectory centralDirectory;
        ArrayList arrayList;
        DigitalSignature digitalSignature;
        String str;
        FileHeader fileHeader;
        Throwable th2;
        StringBuilder sb;
        String str2;
        String str3;
        String decodeFileName;
        Throwable th3;
        StringBuilder sb2;
        StringBuilder sb3;
        String str4;
        Throwable th4;
        Throwable th5;
        if (this.zip4jRaf == null) {
            Throwable th6 = th5;
            new ZipException("random access file was null", 3);
            throw th6;
        }
        if (this.zipModel.getEndCentralDirRecord() == null) {
            Throwable th7 = th4;
            new ZipException("EndCentralRecord was null, maybe a corrupt zip file");
            throw th7;
        }
        try {
            new CentralDirectory();
            CentralDirectory centralDirectory2 = centralDirectory;
            new ArrayList();
            ArrayList arrayList2 = arrayList;
            EndCentralDirRecord endCentralDirRecord = this.zipModel.getEndCentralDirRecord();
            long offsetOfStartOfCentralDir = endCentralDirRecord.getOffsetOfStartOfCentralDir();
            int totNoOfEntriesInCentralDir = endCentralDirRecord.getTotNoOfEntriesInCentralDir();
            if (this.zipModel.isZip64Format()) {
                offsetOfStartOfCentralDir = this.zipModel.getZip64EndCentralDirRecord().getOffsetStartCenDirWRTStartDiskNo();
                totNoOfEntriesInCentralDir = (int) this.zipModel.getZip64EndCentralDirRecord().getTotNoOfEntriesInCentralDir();
            }
            this.zip4jRaf.seek(offsetOfStartOfCentralDir);
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[2];
            byte[] bArr3 = new byte[8];
            for (int i = 0; i < totNoOfEntriesInCentralDir; i++) {
                new FileHeader();
                FileHeader fileHeader2 = fileHeader;
                byte[] readIntoBuff = readIntoBuff(this.zip4jRaf, bArr);
                int readIntLittleEndian = Raw.readIntLittleEndian(bArr, 0);
                if (((long) readIntLittleEndian) != InternalZipConstants.CENSIG) {
                    Throwable th8 = th2;
                    new StringBuilder();
                    new ZipException(sb.append("Expected central directory entry not found (#").append(i + 1).append(")").toString());
                    throw th8;
                }
                fileHeader2.setSignature(readIntLittleEndian);
                byte[] readIntoBuff2 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setVersionMadeBy(Raw.readShortLittleEndian(bArr2, 0));
                byte[] readIntoBuff3 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setVersionNeededToExtract(Raw.readShortLittleEndian(bArr2, 0));
                byte[] readIntoBuff4 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setFileNameUTF8Encoded((Raw.readShortLittleEndian(bArr2, 0) & 2048) != 0);
                byte b = bArr2[0];
                if ((b & 1) != 0) {
                    fileHeader2.setEncrypted(true);
                }
                fileHeader2.setGeneralPurposeFlag((byte[]) bArr2.clone());
                fileHeader2.setDataDescriptorExists((b >> 3) == 1);
                byte[] readIntoBuff5 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setCompressionMethod(Raw.readShortLittleEndian(bArr2, 0));
                byte[] readIntoBuff6 = readIntoBuff(this.zip4jRaf, bArr);
                fileHeader2.setLastModFileTime(Raw.readIntLittleEndian(bArr, 0));
                byte[] readIntoBuff7 = readIntoBuff(this.zip4jRaf, bArr);
                fileHeader2.setCrc32((long) Raw.readIntLittleEndian(bArr, 0));
                fileHeader2.setCrcBuff((byte[]) bArr.clone());
                byte[] readIntoBuff8 = readIntoBuff(this.zip4jRaf, bArr);
                fileHeader2.setCompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr), 0));
                byte[] readIntoBuff9 = readIntoBuff(this.zip4jRaf, bArr);
                fileHeader2.setUncompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr), 0));
                byte[] readIntoBuff10 = readIntoBuff(this.zip4jRaf, bArr2);
                int readShortLittleEndian = Raw.readShortLittleEndian(bArr2, 0);
                fileHeader2.setFileNameLength(readShortLittleEndian);
                byte[] readIntoBuff11 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setExtraFieldLength(Raw.readShortLittleEndian(bArr2, 0));
                byte[] readIntoBuff12 = readIntoBuff(this.zip4jRaf, bArr2);
                int readShortLittleEndian2 = Raw.readShortLittleEndian(bArr2, 0);
                new String(bArr2);
                fileHeader2.setFileComment(str2);
                byte[] readIntoBuff13 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setDiskNumberStart(Raw.readShortLittleEndian(bArr2, 0));
                byte[] readIntoBuff14 = readIntoBuff(this.zip4jRaf, bArr2);
                fileHeader2.setInternalFileAttr((byte[]) bArr2.clone());
                byte[] readIntoBuff15 = readIntoBuff(this.zip4jRaf, bArr);
                fileHeader2.setExternalFileAttr((byte[]) bArr.clone());
                byte[] readIntoBuff16 = readIntoBuff(this.zip4jRaf, bArr);
                fileHeader2.setOffsetLocalHeader(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr), 0) & InternalZipConstants.ZIP_64_LIMIT);
                if (readShortLittleEndian > 0) {
                    byte[] bArr4 = new byte[readShortLittleEndian];
                    byte[] readIntoBuff17 = readIntoBuff(this.zip4jRaf, bArr4);
                    if (Zip4jUtil.isStringNotNullAndNotEmpty(this.zipModel.getFileNameCharset())) {
                        new String(bArr4, this.zipModel.getFileNameCharset());
                        decodeFileName = str4;
                    } else {
                        decodeFileName = Zip4jUtil.decodeFileName(bArr4, fileHeader2.isFileNameUTF8Encoded());
                    }
                    if (decodeFileName == null) {
                        Throwable th9 = th3;
                        new ZipException("fileName is null when reading central directory");
                        throw th9;
                    }
                    new StringBuilder();
                    if (decodeFileName.indexOf(sb2.append(":").append(System.getProperty("file.separator")).toString()) >= 0) {
                        new StringBuilder();
                        decodeFileName = decodeFileName.substring(decodeFileName.indexOf(sb3.append(":").append(System.getProperty("file.separator")).toString()) + 2);
                    }
                    fileHeader2.setFileName(decodeFileName);
                    fileHeader2.setDirectory(decodeFileName.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) || decodeFileName.endsWith("\\"));
                } else {
                    fileHeader2.setFileName((String) null);
                }
                readAndSaveExtraDataRecord(fileHeader2);
                readAndSaveZip64ExtendedInfo(fileHeader2);
                readAndSaveAESExtraDataRecord(fileHeader2);
                if (readShortLittleEndian2 > 0) {
                    byte[] bArr5 = new byte[readShortLittleEndian2];
                    byte[] readIntoBuff18 = readIntoBuff(this.zip4jRaf, bArr5);
                    new String(bArr5);
                    fileHeader2.setFileComment(str3);
                }
                boolean add = arrayList2.add(fileHeader2);
            }
            centralDirectory2.setFileHeaders(arrayList2);
            new DigitalSignature();
            DigitalSignature digitalSignature2 = digitalSignature;
            byte[] readIntoBuff19 = readIntoBuff(this.zip4jRaf, bArr);
            int readIntLittleEndian2 = Raw.readIntLittleEndian(bArr, 0);
            if (((long) readIntLittleEndian2) != InternalZipConstants.DIGSIG) {
                return centralDirectory2;
            }
            digitalSignature2.setHeaderSignature(readIntLittleEndian2);
            byte[] readIntoBuff20 = readIntoBuff(this.zip4jRaf, bArr2);
            int readShortLittleEndian3 = Raw.readShortLittleEndian(bArr2, 0);
            digitalSignature2.setSizeOfData(readShortLittleEndian3);
            if (readShortLittleEndian3 > 0) {
                byte[] bArr6 = new byte[readShortLittleEndian3];
                byte[] readIntoBuff21 = readIntoBuff(this.zip4jRaf, bArr6);
                new String(bArr6);
                digitalSignature2.setSignatureData(str);
            }
            return centralDirectory2;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th10 = th;
            new ZipException((Throwable) iOException);
            throw th10;
        }
    }

    private void readAndSaveExtraDataRecord(FileHeader fileHeader) throws ZipException {
        Throwable th;
        Throwable th2;
        FileHeader fileHeader2 = fileHeader;
        if (this.zip4jRaf == null) {
            Throwable th3 = th2;
            new ZipException("invalid file handler when trying to read extra data record");
            throw th3;
        } else if (fileHeader2 == null) {
            Throwable th4 = th;
            new ZipException("file header is null");
            throw th4;
        } else {
            int extraFieldLength = fileHeader2.getExtraFieldLength();
            if (extraFieldLength > 0) {
                fileHeader2.setExtraDataRecords(readExtraDataRecords(extraFieldLength));
            }
        }
    }

    private void readAndSaveExtraDataRecord(LocalFileHeader localFileHeader) throws ZipException {
        Throwable th;
        Throwable th2;
        LocalFileHeader localFileHeader2 = localFileHeader;
        if (this.zip4jRaf == null) {
            Throwable th3 = th2;
            new ZipException("invalid file handler when trying to read extra data record");
            throw th3;
        } else if (localFileHeader2 == null) {
            Throwable th4 = th;
            new ZipException("file header is null");
            throw th4;
        } else {
            int extraFieldLength = localFileHeader2.getExtraFieldLength();
            if (extraFieldLength > 0) {
                localFileHeader2.setExtraDataRecords(readExtraDataRecords(extraFieldLength));
            }
        }
    }

    private ArrayList readExtraDataRecords(int i) throws ZipException {
        Throwable th;
        ArrayList arrayList;
        ExtraDataRecord extraDataRecord;
        int i2 = i;
        if (i2 <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i2];
            int read = this.zip4jRaf.read(bArr);
            int i3 = 0;
            new ArrayList();
            ArrayList arrayList2 = arrayList;
            while (i3 < i2) {
                new ExtraDataRecord();
                ExtraDataRecord extraDataRecord2 = extraDataRecord;
                extraDataRecord2.setHeader((long) Raw.readShortLittleEndian(bArr, i3));
                int i4 = i3 + 2;
                short readShortLittleEndian = Raw.readShortLittleEndian(bArr, i4);
                if (2 + readShortLittleEndian > i2) {
                    readShortLittleEndian = Raw.readShortBigEndian(bArr, i4);
                    if (2 + readShortLittleEndian > i2) {
                        break;
                    }
                }
                extraDataRecord2.setSizeOfData(readShortLittleEndian);
                int i5 = i4 + 2;
                if (readShortLittleEndian > 0) {
                    byte[] bArr2 = new byte[readShortLittleEndian];
                    System.arraycopy(bArr, i5, bArr2, 0, readShortLittleEndian);
                    extraDataRecord2.setData(bArr2);
                }
                i3 = i5 + readShortLittleEndian;
                boolean add = arrayList2.add(extraDataRecord2);
            }
            if (arrayList2.size() > 0) {
                return arrayList2;
            }
            return null;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new ZipException((Throwable) iOException);
            throw th2;
        }
    }

    private Zip64EndCentralDirLocator readZip64EndCentralDirLocator() throws ZipException {
        Throwable th;
        Zip64EndCentralDirLocator zip64EndCentralDirLocator;
        Throwable th2;
        if (this.zip4jRaf == null) {
            Throwable th3 = th2;
            new ZipException("invalid file handler when trying to read Zip64EndCentralDirLocator");
            throw th3;
        }
        try {
            new Zip64EndCentralDirLocator();
            Zip64EndCentralDirLocator zip64EndCentralDirLocator2 = zip64EndCentralDirLocator;
            setFilePointerToReadZip64EndCentralDirLoc();
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[8];
            byte[] readIntoBuff = readIntoBuff(this.zip4jRaf, bArr);
            int readIntLittleEndian = Raw.readIntLittleEndian(bArr, 0);
            if (((long) readIntLittleEndian) == InternalZipConstants.ZIP64ENDCENDIRLOC) {
                this.zipModel.setZip64Format(true);
                zip64EndCentralDirLocator2.setSignature((long) readIntLittleEndian);
                byte[] readIntoBuff2 = readIntoBuff(this.zip4jRaf, bArr);
                zip64EndCentralDirLocator2.setNoOfDiskStartOfZip64EndOfCentralDirRec(Raw.readIntLittleEndian(bArr, 0));
                byte[] readIntoBuff3 = readIntoBuff(this.zip4jRaf, bArr2);
                zip64EndCentralDirLocator2.setOffsetZip64EndOfCentralDirRec(Raw.readLongLittleEndian(bArr2, 0));
                byte[] readIntoBuff4 = readIntoBuff(this.zip4jRaf, bArr);
                zip64EndCentralDirLocator2.setTotNumberOfDiscs(Raw.readIntLittleEndian(bArr, 0));
                return zip64EndCentralDirLocator2;
            }
            this.zipModel.setZip64Format(false);
            return null;
        } catch (Exception e) {
            Exception exc = e;
            Throwable th4 = th;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private Zip64EndCentralDirRecord readZip64EndCentralDirRec() throws ZipException {
        Throwable th;
        Zip64EndCentralDirRecord zip64EndCentralDirRecord;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        if (this.zipModel.getZip64EndCentralDirLocator() == null) {
            Throwable th5 = th4;
            new ZipException("invalid zip64 end of central directory locator");
            throw th5;
        }
        long offsetZip64EndOfCentralDirRec = this.zipModel.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec();
        if (offsetZip64EndOfCentralDirRec < 0) {
            Throwable th6 = th3;
            new ZipException("invalid offset for start of end of central directory record");
            throw th6;
        }
        try {
            this.zip4jRaf.seek(offsetZip64EndOfCentralDirRec);
            new Zip64EndCentralDirRecord();
            Zip64EndCentralDirRecord zip64EndCentralDirRecord2 = zip64EndCentralDirRecord;
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            byte[] readIntoBuff = readIntoBuff(this.zip4jRaf, bArr2);
            int readIntLittleEndian = Raw.readIntLittleEndian(bArr2, 0);
            if (((long) readIntLittleEndian) != InternalZipConstants.ZIP64ENDCENDIRREC) {
                Throwable th7 = th2;
                new ZipException("invalid signature for zip64 end of central directory record");
                throw th7;
            }
            zip64EndCentralDirRecord2.setSignature((long) readIntLittleEndian);
            byte[] readIntoBuff2 = readIntoBuff(this.zip4jRaf, bArr3);
            zip64EndCentralDirRecord2.setSizeOfZip64EndCentralDirRec(Raw.readLongLittleEndian(bArr3, 0));
            byte[] readIntoBuff3 = readIntoBuff(this.zip4jRaf, bArr);
            zip64EndCentralDirRecord2.setVersionMadeBy(Raw.readShortLittleEndian(bArr, 0));
            byte[] readIntoBuff4 = readIntoBuff(this.zip4jRaf, bArr);
            zip64EndCentralDirRecord2.setVersionNeededToExtract(Raw.readShortLittleEndian(bArr, 0));
            byte[] readIntoBuff5 = readIntoBuff(this.zip4jRaf, bArr2);
            zip64EndCentralDirRecord2.setNoOfThisDisk(Raw.readIntLittleEndian(bArr2, 0));
            byte[] readIntoBuff6 = readIntoBuff(this.zip4jRaf, bArr2);
            zip64EndCentralDirRecord2.setNoOfThisDiskStartOfCentralDir(Raw.readIntLittleEndian(bArr2, 0));
            byte[] readIntoBuff7 = readIntoBuff(this.zip4jRaf, bArr3);
            zip64EndCentralDirRecord2.setTotNoOfEntriesInCentralDirOnThisDisk(Raw.readLongLittleEndian(bArr3, 0));
            byte[] readIntoBuff8 = readIntoBuff(this.zip4jRaf, bArr3);
            zip64EndCentralDirRecord2.setTotNoOfEntriesInCentralDir(Raw.readLongLittleEndian(bArr3, 0));
            byte[] readIntoBuff9 = readIntoBuff(this.zip4jRaf, bArr3);
            zip64EndCentralDirRecord2.setSizeOfCentralDir(Raw.readLongLittleEndian(bArr3, 0));
            byte[] readIntoBuff10 = readIntoBuff(this.zip4jRaf, bArr3);
            zip64EndCentralDirRecord2.setOffsetStartCenDirWRTStartDiskNo(Raw.readLongLittleEndian(bArr3, 0));
            long sizeOfZip64EndCentralDirRec = zip64EndCentralDirRecord2.getSizeOfZip64EndCentralDirRec() - 44;
            if (sizeOfZip64EndCentralDirRec > 0) {
                byte[] bArr4 = new byte[((int) sizeOfZip64EndCentralDirRec)];
                byte[] readIntoBuff11 = readIntoBuff(this.zip4jRaf, bArr4);
                zip64EndCentralDirRecord2.setExtensibleDataSector(bArr4);
            }
            return zip64EndCentralDirRecord2;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th8 = th;
            new ZipException((Throwable) iOException);
            throw th8;
        }
    }

    private void readAndSaveZip64ExtendedInfo(FileHeader fileHeader) throws ZipException {
        Zip64ExtendedInfo readZip64ExtendedInfo;
        Throwable th;
        FileHeader fileHeader2 = fileHeader;
        if (fileHeader2 == null) {
            Throwable th2 = th;
            new ZipException("file header is null in reading Zip64 Extended Info");
            throw th2;
        } else if (fileHeader2.getExtraDataRecords() != null && fileHeader2.getExtraDataRecords().size() > 0 && (readZip64ExtendedInfo = readZip64ExtendedInfo(fileHeader2.getExtraDataRecords(), fileHeader2.getUncompressedSize(), fileHeader2.getCompressedSize(), fileHeader2.getOffsetLocalHeader(), fileHeader2.getDiskNumberStart())) != null) {
            fileHeader2.setZip64ExtendedInfo(readZip64ExtendedInfo);
            if (readZip64ExtendedInfo.getUnCompressedSize() != -1) {
                fileHeader2.setUncompressedSize(readZip64ExtendedInfo.getUnCompressedSize());
            }
            if (readZip64ExtendedInfo.getCompressedSize() != -1) {
                fileHeader2.setCompressedSize(readZip64ExtendedInfo.getCompressedSize());
            }
            if (readZip64ExtendedInfo.getOffsetLocalHeader() != -1) {
                fileHeader2.setOffsetLocalHeader(readZip64ExtendedInfo.getOffsetLocalHeader());
            }
            if (readZip64ExtendedInfo.getDiskNumberStart() != -1) {
                fileHeader2.setDiskNumberStart(readZip64ExtendedInfo.getDiskNumberStart());
            }
        }
    }

    private void readAndSaveZip64ExtendedInfo(LocalFileHeader localFileHeader) throws ZipException {
        Zip64ExtendedInfo readZip64ExtendedInfo;
        Throwable th;
        LocalFileHeader localFileHeader2 = localFileHeader;
        if (localFileHeader2 == null) {
            Throwable th2 = th;
            new ZipException("file header is null in reading Zip64 Extended Info");
            throw th2;
        } else if (localFileHeader2.getExtraDataRecords() != null && localFileHeader2.getExtraDataRecords().size() > 0 && (readZip64ExtendedInfo = readZip64ExtendedInfo(localFileHeader2.getExtraDataRecords(), localFileHeader2.getUncompressedSize(), localFileHeader2.getCompressedSize(), -1, -1)) != null) {
            localFileHeader2.setZip64ExtendedInfo(readZip64ExtendedInfo);
            if (readZip64ExtendedInfo.getUnCompressedSize() != -1) {
                localFileHeader2.setUncompressedSize(readZip64ExtendedInfo.getUnCompressedSize());
            }
            if (readZip64ExtendedInfo.getCompressedSize() != -1) {
                localFileHeader2.setCompressedSize(readZip64ExtendedInfo.getCompressedSize());
            }
        }
    }

    private Zip64ExtendedInfo readZip64ExtendedInfo(ArrayList arrayList, long j, long j2, long j3, int i) throws ZipException {
        Zip64ExtendedInfo zip64ExtendedInfo;
        ArrayList arrayList2 = arrayList;
        long j4 = j;
        long j5 = j2;
        long j6 = j3;
        int i2 = i;
        int i3 = 0;
        while (true) {
            if (i3 >= arrayList2.size()) {
                break;
            }
            ExtraDataRecord extraDataRecord = (ExtraDataRecord) arrayList2.get(i3);
            if (extraDataRecord != null && extraDataRecord.getHeader() == 1) {
                new Zip64ExtendedInfo();
                Zip64ExtendedInfo zip64ExtendedInfo2 = zip64ExtendedInfo;
                byte[] data = extraDataRecord.getData();
                if (extraDataRecord.getSizeOfData() > 0) {
                    byte[] bArr = new byte[8];
                    byte[] bArr2 = new byte[4];
                    int i4 = 0;
                    boolean z = false;
                    if ((j4 & 65535) == 65535 && 0 < extraDataRecord.getSizeOfData()) {
                        System.arraycopy(data, 0, bArr, 0, 8);
                        zip64ExtendedInfo2.setUnCompressedSize(Raw.readLongLittleEndian(bArr, 0));
                        i4 = 0 + 8;
                        z = true;
                    }
                    if ((j5 & 65535) == 65535 && i4 < extraDataRecord.getSizeOfData()) {
                        System.arraycopy(data, i4, bArr, 0, 8);
                        zip64ExtendedInfo2.setCompressedSize(Raw.readLongLittleEndian(bArr, 0));
                        i4 += 8;
                        z = true;
                    }
                    if ((j6 & 65535) == 65535 && i4 < extraDataRecord.getSizeOfData()) {
                        System.arraycopy(data, i4, bArr, 0, 8);
                        zip64ExtendedInfo2.setOffsetLocalHeader(Raw.readLongLittleEndian(bArr, 0));
                        i4 += 8;
                        z = true;
                    }
                    if ((i2 & 65535) == 65535 && i4 < extraDataRecord.getSizeOfData()) {
                        System.arraycopy(data, i4, bArr2, 0, 4);
                        zip64ExtendedInfo2.setDiskNumberStart(Raw.readIntLittleEndian(bArr2, 0));
                        int i5 = i4 + 8;
                        z = true;
                    }
                    if (z) {
                        return zip64ExtendedInfo2;
                    }
                }
            } else {
                i3++;
            }
        }
        return null;
    }

    private void setFilePointerToReadZip64EndCentralDirLoc() throws ZipException {
        Throwable th;
        try {
            byte[] bArr = new byte[4];
            long length = this.zip4jRaf.length() - 22;
            do {
                long j = length;
                length = j - 1;
                this.zip4jRaf.seek(j);
            } while (((long) Raw.readLeInt(this.zip4jRaf, bArr)) != InternalZipConstants.ENDSIG);
            this.zip4jRaf.seek(((((this.zip4jRaf.getFilePointer() - 4) - 4) - 8) - 4) - 4);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new ZipException((Throwable) iOException);
            throw th2;
        }
    }

    public LocalFileHeader readLocalFileHeader(FileHeader fileHeader) throws ZipException {
        Throwable th;
        Throwable th2;
        LocalFileHeader localFileHeader;
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th3;
        Throwable th4;
        StringBuilder sb3;
        Throwable th5;
        FileHeader fileHeader2 = fileHeader;
        if (fileHeader2 != null) {
            if (this.zip4jRaf != null) {
                long offsetLocalHeader = fileHeader2.getOffsetLocalHeader();
                if (fileHeader2.getZip64ExtendedInfo() != null && fileHeader2.getZip64ExtendedInfo().getOffsetLocalHeader() > 0) {
                    offsetLocalHeader = fileHeader2.getOffsetLocalHeader();
                }
                if (offsetLocalHeader < 0) {
                    Throwable th6 = th5;
                    new ZipException("invalid local header offset");
                    throw th6;
                }
                try {
                    this.zip4jRaf.seek(offsetLocalHeader);
                    new LocalFileHeader();
                    LocalFileHeader localFileHeader2 = localFileHeader;
                    byte[] bArr = new byte[2];
                    byte[] bArr2 = new byte[4];
                    byte[] bArr3 = new byte[8];
                    byte[] readIntoBuff = readIntoBuff(this.zip4jRaf, bArr2);
                    int readIntLittleEndian = Raw.readIntLittleEndian(bArr2, 0);
                    if (((long) readIntLittleEndian) != InternalZipConstants.LOCSIG) {
                        Throwable th7 = th4;
                        new StringBuilder();
                        new ZipException(sb3.append("invalid local header signature for file: ").append(fileHeader2.getFileName()).toString());
                        throw th7;
                    }
                    localFileHeader2.setSignature(readIntLittleEndian);
                    byte[] readIntoBuff2 = readIntoBuff(this.zip4jRaf, bArr);
                    localFileHeader2.setVersionNeededToExtract(Raw.readShortLittleEndian(bArr, 0));
                    int i = 0 + 4 + 2;
                    byte[] readIntoBuff3 = readIntoBuff(this.zip4jRaf, bArr);
                    localFileHeader2.setFileNameUTF8Encoded((Raw.readShortLittleEndian(bArr, 0) & 2048) != 0);
                    byte b = bArr[0];
                    if ((b & 1) != 0) {
                        localFileHeader2.setEncrypted(true);
                    }
                    localFileHeader2.setGeneralPurposeFlag(bArr);
                    int i2 = i + 2;
                    String binaryString = Integer.toBinaryString(b);
                    if (binaryString.length() >= 4) {
                        localFileHeader2.setDataDescriptorExists(binaryString.charAt(3) == '1');
                    }
                    byte[] readIntoBuff4 = readIntoBuff(this.zip4jRaf, bArr);
                    localFileHeader2.setCompressionMethod(Raw.readShortLittleEndian(bArr, 0));
                    byte[] readIntoBuff5 = readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader2.setLastModFileTime(Raw.readIntLittleEndian(bArr2, 0));
                    byte[] readIntoBuff6 = readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader2.setCrc32((long) Raw.readIntLittleEndian(bArr2, 0));
                    localFileHeader2.setCrcBuff((byte[]) bArr2.clone());
                    byte[] readIntoBuff7 = readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader2.setCompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr2), 0));
                    byte[] readIntoBuff8 = readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader2.setUncompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr2), 0));
                    byte[] readIntoBuff9 = readIntoBuff(this.zip4jRaf, bArr);
                    int readShortLittleEndian = Raw.readShortLittleEndian(bArr, 0);
                    localFileHeader2.setFileNameLength(readShortLittleEndian);
                    byte[] readIntoBuff10 = readIntoBuff(this.zip4jRaf, bArr);
                    int readShortLittleEndian2 = Raw.readShortLittleEndian(bArr, 0);
                    localFileHeader2.setExtraFieldLength(readShortLittleEndian2);
                    int i3 = i2 + 2 + 4 + 4 + 4 + 4 + 2 + 2;
                    if (readShortLittleEndian > 0) {
                        byte[] bArr4 = new byte[readShortLittleEndian];
                        byte[] readIntoBuff11 = readIntoBuff(this.zip4jRaf, bArr4);
                        String decodeFileName = Zip4jUtil.decodeFileName(bArr4, localFileHeader2.isFileNameUTF8Encoded());
                        if (decodeFileName == null) {
                            Throwable th8 = th3;
                            new ZipException("file name is null, cannot assign file name to local file header");
                            throw th8;
                        }
                        new StringBuilder();
                        if (decodeFileName.indexOf(sb.append(":").append(System.getProperty("file.separator")).toString()) >= 0) {
                            new StringBuilder();
                            decodeFileName = decodeFileName.substring(decodeFileName.indexOf(sb2.append(":").append(System.getProperty("file.separator")).toString()) + 2);
                        }
                        localFileHeader2.setFileName(decodeFileName);
                        i3 += readShortLittleEndian;
                    } else {
                        localFileHeader2.setFileName((String) null);
                    }
                    readAndSaveExtraDataRecord(localFileHeader2);
                    localFileHeader2.setOffsetStartOfData(offsetLocalHeader + ((long) (i3 + readShortLittleEndian2)));
                    localFileHeader2.setPassword(fileHeader2.getPassword());
                    readAndSaveZip64ExtendedInfo(localFileHeader2);
                    readAndSaveAESExtraDataRecord(localFileHeader2);
                    if (localFileHeader2.isEncrypted() && localFileHeader2.getEncryptionMethod() != 99) {
                        if ((b & 64) == 64) {
                            localFileHeader2.setEncryptionMethod(1);
                        } else {
                            localFileHeader2.setEncryptionMethod(0);
                        }
                    }
                    if (localFileHeader2.getCrc32() <= 0) {
                        localFileHeader2.setCrc32(fileHeader2.getCrc32());
                        localFileHeader2.setCrcBuff(fileHeader2.getCrcBuff());
                    }
                    if (localFileHeader2.getCompressedSize() <= 0) {
                        localFileHeader2.setCompressedSize(fileHeader2.getCompressedSize());
                    }
                    if (localFileHeader2.getUncompressedSize() <= 0) {
                        localFileHeader2.setUncompressedSize(fileHeader2.getUncompressedSize());
                    }
                    return localFileHeader2;
                } catch (IOException e) {
                    IOException iOException = e;
                    Throwable th9 = th2;
                    new ZipException((Throwable) iOException);
                    throw th9;
                }
            }
        }
        Throwable th10 = th;
        new ZipException("invalid read parameters for local header");
        throw th10;
    }

    private void readAndSaveAESExtraDataRecord(FileHeader fileHeader) throws ZipException {
        AESExtraDataRecord readAESExtraDataRecord;
        Throwable th;
        FileHeader fileHeader2 = fileHeader;
        if (fileHeader2 == null) {
            Throwable th2 = th;
            new ZipException("file header is null in reading Zip64 Extended Info");
            throw th2;
        } else if (fileHeader2.getExtraDataRecords() != null && fileHeader2.getExtraDataRecords().size() > 0 && (readAESExtraDataRecord = readAESExtraDataRecord(fileHeader2.getExtraDataRecords())) != null) {
            fileHeader2.setAesExtraDataRecord(readAESExtraDataRecord);
            fileHeader2.setEncryptionMethod(99);
        }
    }

    private void readAndSaveAESExtraDataRecord(LocalFileHeader localFileHeader) throws ZipException {
        AESExtraDataRecord readAESExtraDataRecord;
        Throwable th;
        LocalFileHeader localFileHeader2 = localFileHeader;
        if (localFileHeader2 == null) {
            Throwable th2 = th;
            new ZipException("file header is null in reading Zip64 Extended Info");
            throw th2;
        } else if (localFileHeader2.getExtraDataRecords() != null && localFileHeader2.getExtraDataRecords().size() > 0 && (readAESExtraDataRecord = readAESExtraDataRecord(localFileHeader2.getExtraDataRecords())) != null) {
            localFileHeader2.setAesExtraDataRecord(readAESExtraDataRecord);
            localFileHeader2.setEncryptionMethod(99);
        }
    }

    private AESExtraDataRecord readAESExtraDataRecord(ArrayList arrayList) throws ZipException {
        AESExtraDataRecord aESExtraDataRecord;
        String str;
        Throwable th;
        ArrayList arrayList2 = arrayList;
        if (arrayList2 == null) {
            return null;
        }
        int i = 0;
        while (i < arrayList2.size()) {
            ExtraDataRecord extraDataRecord = (ExtraDataRecord) arrayList2.get(i);
            if (extraDataRecord == null || extraDataRecord.getHeader() != 39169) {
                i++;
            } else if (extraDataRecord.getData() == null) {
                Throwable th2 = th;
                new ZipException("corrput AES extra data records");
                throw th2;
            } else {
                new AESExtraDataRecord();
                AESExtraDataRecord aESExtraDataRecord2 = aESExtraDataRecord;
                aESExtraDataRecord2.setSignature(39169);
                aESExtraDataRecord2.setDataSize(extraDataRecord.getSizeOfData());
                byte[] data = extraDataRecord.getData();
                aESExtraDataRecord2.setVersionNumber(Raw.readShortLittleEndian(data, 0));
                byte[] bArr = new byte[2];
                System.arraycopy(data, 2, bArr, 0, 2);
                new String(bArr);
                aESExtraDataRecord2.setVendorID(str);
                aESExtraDataRecord2.setAesStrength(data[4] & 255);
                aESExtraDataRecord2.setCompressionMethod(Raw.readShortLittleEndian(data, 5));
                return aESExtraDataRecord2;
            }
        }
        return null;
    }

    private byte[] readIntoBuff(RandomAccessFile randomAccessFile, byte[] bArr) throws ZipException {
        Throwable th;
        Throwable th2;
        byte[] bArr2 = bArr;
        try {
            if (randomAccessFile.read(bArr2, 0, bArr2.length) != -1) {
                return bArr2;
            }
            Throwable th3 = th2;
            new ZipException("unexpected end of file when reading short buff");
            throw th3;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th4 = th;
            new ZipException("IOException when reading short buff", (Throwable) iOException);
            throw th4;
        }
    }

    private byte[] getLongByteFromIntByte(byte[] bArr) throws ZipException {
        Throwable th;
        Throwable th2;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            Throwable th3 = th2;
            new ZipException("input parameter is null, cannot expand to 8 bytes");
            throw th3;
        } else if (bArr2.length != 4) {
            Throwable th4 = th;
            new ZipException("invalid byte length, cannot expand to 8 bytes");
            throw th4;
        } else {
            byte[] bArr3 = new byte[8];
            bArr3[0] = bArr2[0];
            byte[] bArr4 = bArr3;
            bArr4[1] = bArr2[1];
            byte[] bArr5 = bArr4;
            bArr5[2] = bArr2[2];
            byte[] bArr6 = bArr5;
            bArr6[3] = bArr2[3];
            byte[] bArr7 = bArr6;
            bArr7[4] = 0;
            byte[] bArr8 = bArr7;
            bArr8[5] = 0;
            byte[] bArr9 = bArr8;
            bArr9[6] = 0;
            byte[] bArr10 = bArr9;
            bArr10[7] = 0;
            return bArr10;
        }
    }
}
