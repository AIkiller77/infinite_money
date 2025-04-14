package net.lingala.zip4j.core;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.SplitOutputStream;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndCentralDirLocator;
import net.lingala.zip4j.model.Zip64EndCentralDirRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class HeaderWriter {
    private final int ZIP64_EXTRA_BUF = 50;

    public HeaderWriter() {
    }

    public int writeLocalFileHeader(ZipModel zipModel, LocalFileHeader localFileHeader, OutputStream outputStream) throws ZipException {
        Throwable th;
        List list;
        Throwable th2;
        ZipModel zipModel2 = zipModel;
        LocalFileHeader localFileHeader2 = localFileHeader;
        OutputStream outputStream2 = outputStream;
        if (localFileHeader2 == null) {
            Throwable th3 = th2;
            new ZipException("input parameters are null, cannot write local file header");
            throw th3;
        }
        try {
            new ArrayList();
            List list2 = list;
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = {0, 0, 0, 0, 0, 0, 0, 0};
            Raw.writeIntLittleEndian(bArr2, 0, localFileHeader2.getSignature());
            copyByteArrayToArrayList(bArr2, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader2.getVersionNeededToExtract());
            copyByteArrayToArrayList(bArr, list2);
            copyByteArrayToArrayList(localFileHeader2.getGeneralPurposeFlag(), list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader2.getCompressionMethod());
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeIntLittleEndian(bArr2, 0, localFileHeader2.getLastModFileTime());
            copyByteArrayToArrayList(bArr2, list2);
            Raw.writeIntLittleEndian(bArr2, 0, (int) localFileHeader2.getCrc32());
            copyByteArrayToArrayList(bArr2, list2);
            boolean z = false;
            if (localFileHeader2.getUncompressedSize() + 50 >= InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
                copyByteArrayToArrayList(bArr2, list2);
                zipModel2.setZip64Format(true);
                z = true;
                localFileHeader2.setWriteComprSizeInZip64ExtraRecord(true);
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, localFileHeader2.getCompressedSize());
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
                Raw.writeLongLittleEndian(bArr3, 0, localFileHeader2.getUncompressedSize());
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
                localFileHeader2.setWriteComprSizeInZip64ExtraRecord(false);
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader2.getFileNameLength());
            copyByteArrayToArrayList(bArr, list2);
            int i = 0;
            if (z) {
                i = 0 + 20;
            }
            if (localFileHeader2.getAesExtraDataRecord() != null) {
                i += 11;
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) i);
            copyByteArrayToArrayList(bArr, list2);
            if (Zip4jUtil.isStringNotNullAndNotEmpty(zipModel2.getFileNameCharset())) {
                copyByteArrayToArrayList(localFileHeader2.getFileName().getBytes(zipModel2.getFileNameCharset()), list2);
            } else {
                copyByteArrayToArrayList(Zip4jUtil.convertCharset(localFileHeader2.getFileName()), list2);
            }
            if (z) {
                Raw.writeShortLittleEndian(bArr, 0, 1);
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeShortLittleEndian(bArr, 0, 16);
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeLongLittleEndian(bArr3, 0, localFileHeader2.getUncompressedSize());
                copyByteArrayToArrayList(bArr3, list2);
                copyByteArrayToArrayList(bArr4, list2);
            }
            if (localFileHeader2.getAesExtraDataRecord() != null) {
                AESExtraDataRecord aesExtraDataRecord = localFileHeader2.getAesExtraDataRecord();
                Raw.writeShortLittleEndian(bArr, 0, (short) ((int) aesExtraDataRecord.getSignature()));
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getDataSize());
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getVersionNumber());
                copyByteArrayToArrayList(bArr, list2);
                copyByteArrayToArrayList(aesExtraDataRecord.getVendorID().getBytes(), list2);
                copyByteArrayToArrayList(new byte[]{(byte) aesExtraDataRecord.getAesStrength()}, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getCompressionMethod());
                copyByteArrayToArrayList(bArr, list2);
            }
            byte[] byteArrayListToByteArray = byteArrayListToByteArray(list2);
            outputStream2.write(byteArrayListToByteArray);
            return byteArrayListToByteArray.length;
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    public int writeExtendedLocalHeader(LocalFileHeader localFileHeader, OutputStream outputStream) throws ZipException, IOException {
        Throwable th;
        List list;
        LocalFileHeader localFileHeader2 = localFileHeader;
        OutputStream outputStream2 = outputStream;
        if (localFileHeader2 == null || outputStream2 == null) {
            Throwable th2 = th;
            new ZipException("input parameters is null, cannot write extended local header");
            throw th2;
        }
        new ArrayList();
        List list2 = list;
        byte[] bArr = new byte[4];
        Raw.writeIntLittleEndian(bArr, 0, 134695760);
        copyByteArrayToArrayList(bArr, list2);
        Raw.writeIntLittleEndian(bArr, 0, (int) localFileHeader2.getCrc32());
        copyByteArrayToArrayList(bArr, list2);
        long compressedSize = localFileHeader2.getCompressedSize();
        if (compressedSize >= 2147483647L) {
            compressedSize = 2147483647L;
        }
        Raw.writeIntLittleEndian(bArr, 0, (int) compressedSize);
        copyByteArrayToArrayList(bArr, list2);
        long uncompressedSize = localFileHeader2.getUncompressedSize();
        if (uncompressedSize >= 2147483647L) {
            uncompressedSize = 2147483647L;
        }
        Raw.writeIntLittleEndian(bArr, 0, (int) uncompressedSize);
        copyByteArrayToArrayList(bArr, list2);
        byte[] byteArrayListToByteArray = byteArrayListToByteArray(list2);
        outputStream2.write(byteArrayListToByteArray);
        return byteArrayListToByteArray.length;
    }

    public void finalizeZipFile(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        Throwable th;
        Throwable th2;
        List list;
        Zip64EndCentralDirLocator zip64EndCentralDirLocator;
        Zip64EndCentralDirRecord zip64EndCentralDirRecord;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        if (zipModel2 == null || outputStream2 == null) {
            Throwable th3 = th;
            new ZipException("input parameters is null, cannot finalize zip file");
            throw th3;
        }
        try {
            processHeaderData(zipModel2, outputStream2);
            long offsetOfStartOfCentralDir = zipModel2.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
            new ArrayList();
            List list2 = list;
            int writeCentralDirectory = writeCentralDirectory(zipModel2, outputStream2, list2);
            if (zipModel2.isZip64Format()) {
                if (zipModel2.getZip64EndCentralDirRecord() == null) {
                    new Zip64EndCentralDirRecord();
                    zipModel2.setZip64EndCentralDirRecord(zip64EndCentralDirRecord);
                }
                if (zipModel2.getZip64EndCentralDirLocator() == null) {
                    new Zip64EndCentralDirLocator();
                    zipModel2.setZip64EndCentralDirLocator(zip64EndCentralDirLocator);
                }
                zipModel2.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(offsetOfStartOfCentralDir + ((long) writeCentralDirectory));
                if (outputStream2 instanceof SplitOutputStream) {
                    zipModel2.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(((SplitOutputStream) outputStream2).getCurrSplitFileCounter());
                    zipModel2.getZip64EndCentralDirLocator().setTotNumberOfDiscs(((SplitOutputStream) outputStream2).getCurrSplitFileCounter() + 1);
                } else {
                    zipModel2.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(0);
                    zipModel2.getZip64EndCentralDirLocator().setTotNumberOfDiscs(1);
                }
                writeZip64EndOfCentralDirectoryRecord(zipModel2, outputStream2, writeCentralDirectory, offsetOfStartOfCentralDir, list2);
                writeZip64EndOfCentralDirectoryLocator(zipModel2, outputStream2, list2);
            }
            writeEndOfCentralDirectoryRecord(zipModel2, outputStream2, writeCentralDirectory, offsetOfStartOfCentralDir, list2);
            writeZipHeaderBytes(zipModel2, outputStream2, byteArrayListToByteArray(list2));
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th2;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    public void finalizeZipFileWithoutValidations(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        Throwable th;
        Throwable th2;
        List list;
        Zip64EndCentralDirLocator zip64EndCentralDirLocator;
        Zip64EndCentralDirRecord zip64EndCentralDirRecord;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        if (zipModel2 == null || outputStream2 == null) {
            Throwable th3 = th;
            new ZipException("input parameters is null, cannot finalize zip file without validations");
            throw th3;
        }
        try {
            new ArrayList();
            List list2 = list;
            long offsetOfStartOfCentralDir = zipModel2.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
            int writeCentralDirectory = writeCentralDirectory(zipModel2, outputStream2, list2);
            if (zipModel2.isZip64Format()) {
                if (zipModel2.getZip64EndCentralDirRecord() == null) {
                    new Zip64EndCentralDirRecord();
                    zipModel2.setZip64EndCentralDirRecord(zip64EndCentralDirRecord);
                }
                if (zipModel2.getZip64EndCentralDirLocator() == null) {
                    new Zip64EndCentralDirLocator();
                    zipModel2.setZip64EndCentralDirLocator(zip64EndCentralDirLocator);
                }
                zipModel2.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(offsetOfStartOfCentralDir + ((long) writeCentralDirectory));
                writeZip64EndOfCentralDirectoryRecord(zipModel2, outputStream2, writeCentralDirectory, offsetOfStartOfCentralDir, list2);
                writeZip64EndOfCentralDirectoryLocator(zipModel2, outputStream2, list2);
            }
            writeEndOfCentralDirectoryRecord(zipModel2, outputStream2, writeCentralDirectory, offsetOfStartOfCentralDir, list2);
            writeZipHeaderBytes(zipModel2, outputStream2, byteArrayListToByteArray(list2));
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th2;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private void writeZipHeaderBytes(ZipModel zipModel, OutputStream outputStream, byte[] bArr) throws ZipException {
        Throwable th;
        Throwable th2;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            Throwable th3 = th2;
            new ZipException("invalid buff to write as zip headers");
            throw th3;
        }
        try {
            if (!(outputStream2 instanceof SplitOutputStream) || !((SplitOutputStream) outputStream2).checkBuffSizeAndStartNextSplitFile(bArr2.length)) {
                outputStream2.write(bArr2);
            } else {
                finalizeZipFile(zipModel2, outputStream2);
            }
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th4 = th;
            new ZipException((Throwable) iOException);
            throw th4;
        }
    }

    private void processHeaderData(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        Throwable th;
        Zip64EndCentralDirLocator zip64EndCentralDirLocator;
        Zip64EndCentralDirRecord zip64EndCentralDirRecord;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        int i = 0;
        try {
            if (outputStream2 instanceof SplitOutputStream) {
                zipModel2.getEndCentralDirRecord().setOffsetOfStartOfCentralDir(((SplitOutputStream) outputStream2).getFilePointer());
                i = ((SplitOutputStream) outputStream2).getCurrSplitFileCounter();
            }
            if (zipModel2.isZip64Format()) {
                if (zipModel2.getZip64EndCentralDirRecord() == null) {
                    new Zip64EndCentralDirRecord();
                    zipModel2.setZip64EndCentralDirRecord(zip64EndCentralDirRecord);
                }
                if (zipModel2.getZip64EndCentralDirLocator() == null) {
                    new Zip64EndCentralDirLocator();
                    zipModel2.setZip64EndCentralDirLocator(zip64EndCentralDirLocator);
                }
                zipModel2.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(i);
                zipModel2.getZip64EndCentralDirLocator().setTotNumberOfDiscs(i + 1);
            }
            zipModel2.getEndCentralDirRecord().setNoOfThisDisk(i);
            zipModel2.getEndCentralDirRecord().setNoOfThisDiskStartOfCentralDir(i);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new ZipException((Throwable) iOException);
            throw th2;
        }
    }

    private int writeCentralDirectory(ZipModel zipModel, OutputStream outputStream, List list) throws ZipException {
        Throwable th;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        List list2 = list;
        if (zipModel2 == null || outputStream2 == null) {
            Throwable th2 = th;
            new ZipException("input parameters is null, cannot write central directory");
            throw th2;
        } else if (zipModel2.getCentralDirectory() == null || zipModel2.getCentralDirectory().getFileHeaders() == null || zipModel2.getCentralDirectory().getFileHeaders().size() <= 0) {
            return 0;
        } else {
            int i = 0;
            for (int i2 = 0; i2 < zipModel2.getCentralDirectory().getFileHeaders().size(); i2++) {
                i += writeFileHeader(zipModel2, (FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i2), outputStream2, list2);
            }
            return i;
        }
    }

    private int writeFileHeader(ZipModel zipModel, FileHeader fileHeader, OutputStream outputStream, List list) throws ZipException {
        Throwable th;
        Throwable th2;
        int i;
        int encodedStringLength;
        int i2;
        ZipModel zipModel2 = zipModel;
        FileHeader fileHeader2 = fileHeader;
        OutputStream outputStream2 = outputStream;
        List list2 = list;
        if (fileHeader2 == null || outputStream2 == null) {
            Throwable th3 = th;
            new ZipException("input parameters is null, cannot write local file header");
            throw th3;
        }
        try {
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = {0, 0};
            byte[] bArr5 = {0, 0, 0, 0};
            boolean z = false;
            boolean z2 = false;
            Raw.writeIntLittleEndian(bArr2, 0, fileHeader2.getSignature());
            copyByteArrayToArrayList(bArr2, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) fileHeader2.getVersionMadeBy());
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) fileHeader2.getVersionNeededToExtract());
            copyByteArrayToArrayList(bArr, list2);
            copyByteArrayToArrayList(fileHeader2.getGeneralPurposeFlag(), list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) fileHeader2.getCompressionMethod());
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeIntLittleEndian(bArr2, 0, fileHeader2.getLastModFileTime());
            copyByteArrayToArrayList(bArr2, list2);
            Raw.writeIntLittleEndian(bArr2, 0, (int) fileHeader2.getCrc32());
            copyByteArrayToArrayList(bArr2, list2);
            int i3 = 0 + 4 + 2 + 2 + 2 + 2 + 4 + 4;
            if (fileHeader2.getCompressedSize() >= InternalZipConstants.ZIP_64_LIMIT || fileHeader2.getUncompressedSize() + 50 >= InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
                copyByteArrayToArrayList(bArr2, list2);
                i = i3 + 4 + 4;
                z = true;
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, fileHeader2.getCompressedSize());
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
                Raw.writeLongLittleEndian(bArr3, 0, fileHeader2.getUncompressedSize());
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
                i = i3 + 4 + 4;
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) fileHeader2.getFileNameLength());
            copyByteArrayToArrayList(bArr, list2);
            int i4 = i + 2;
            byte[] bArr6 = new byte[4];
            if (fileHeader2.getOffsetLocalHeader() > InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr6, 0, 4);
                z2 = true;
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, fileHeader2.getOffsetLocalHeader());
                System.arraycopy(bArr3, 0, bArr6, 0, 4);
            }
            int i5 = 0;
            if (z || z2) {
                i5 = 0 + 4;
                if (z) {
                    i5 += 16;
                }
                if (z2) {
                    i5 += 8;
                }
            }
            if (fileHeader2.getAesExtraDataRecord() != null) {
                i5 += 11;
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) i5);
            copyByteArrayToArrayList(bArr, list2);
            copyByteArrayToArrayList(bArr4, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) fileHeader2.getDiskNumberStart());
            copyByteArrayToArrayList(bArr, list2);
            copyByteArrayToArrayList(bArr4, list2);
            int i6 = i4 + 2 + 2 + 2 + 2;
            if (fileHeader2.getExternalFileAttr() != null) {
                copyByteArrayToArrayList(fileHeader2.getExternalFileAttr(), list2);
            } else {
                copyByteArrayToArrayList(bArr5, list2);
            }
            copyByteArrayToArrayList(bArr6, list2);
            int i7 = i6 + 4 + 4;
            if (Zip4jUtil.isStringNotNullAndNotEmpty(zipModel2.getFileNameCharset())) {
                byte[] bytes = fileHeader2.getFileName().getBytes(zipModel2.getFileNameCharset());
                copyByteArrayToArrayList(bytes, list2);
                encodedStringLength = i7 + bytes.length;
            } else {
                copyByteArrayToArrayList(Zip4jUtil.convertCharset(fileHeader2.getFileName()), list2);
                encodedStringLength = i7 + Zip4jUtil.getEncodedStringLength(fileHeader2.getFileName());
            }
            if (z || z2) {
                zipModel2.setZip64Format(true);
                Raw.writeShortLittleEndian(bArr, 0, 1);
                copyByteArrayToArrayList(bArr, list2);
                int i8 = i2 + 2;
                int i9 = 0;
                if (z) {
                    i9 = 0 + 16;
                }
                if (z2) {
                    i9 += 8;
                }
                Raw.writeShortLittleEndian(bArr, 0, (short) i9);
                copyByteArrayToArrayList(bArr, list2);
                i2 = i8 + 2;
                if (z) {
                    Raw.writeLongLittleEndian(bArr3, 0, fileHeader2.getUncompressedSize());
                    copyByteArrayToArrayList(bArr3, list2);
                    Raw.writeLongLittleEndian(bArr3, 0, fileHeader2.getCompressedSize());
                    copyByteArrayToArrayList(bArr3, list2);
                    i2 = i2 + 8 + 8;
                }
                if (z2) {
                    Raw.writeLongLittleEndian(bArr3, 0, fileHeader2.getOffsetLocalHeader());
                    copyByteArrayToArrayList(bArr3, list2);
                    i2 += 8;
                }
            }
            if (fileHeader2.getAesExtraDataRecord() != null) {
                AESExtraDataRecord aesExtraDataRecord = fileHeader2.getAesExtraDataRecord();
                Raw.writeShortLittleEndian(bArr, 0, (short) ((int) aesExtraDataRecord.getSignature()));
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getDataSize());
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getVersionNumber());
                copyByteArrayToArrayList(bArr, list2);
                copyByteArrayToArrayList(aesExtraDataRecord.getVendorID().getBytes(), list2);
                copyByteArrayToArrayList(new byte[]{(byte) aesExtraDataRecord.getAesStrength()}, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getCompressionMethod());
                copyByteArrayToArrayList(bArr, list2);
                i2 += 11;
            }
            return i2;
        } catch (Exception e) {
            Exception exc = e;
            Throwable th4 = th2;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private void writeZip64EndOfCentralDirectoryRecord(ZipModel zipModel, OutputStream outputStream, int i, long j, List list) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        int i2 = i;
        long j2 = j;
        List list2 = list;
        if (zipModel2 == null || outputStream2 == null) {
            Throwable th4 = th;
            new ZipException("zip model or output stream is null, cannot write zip64 end of central directory record");
            throw th4;
        }
        try {
            byte[] bArr = new byte[2];
            byte[] bArr2 = {0, 0};
            byte[] bArr3 = new byte[4];
            byte[] bArr4 = new byte[8];
            Raw.writeIntLittleEndian(bArr3, 0, 101075792);
            copyByteArrayToArrayList(bArr3, list2);
            Raw.writeLongLittleEndian(bArr4, 0, 44);
            copyByteArrayToArrayList(bArr4, list2);
            if (zipModel2.getCentralDirectory() == null || zipModel2.getCentralDirectory().getFileHeaders() == null || zipModel2.getCentralDirectory().getFileHeaders().size() <= 0) {
                copyByteArrayToArrayList(bArr2, list2);
                copyByteArrayToArrayList(bArr2, list2);
            } else {
                Raw.writeShortLittleEndian(bArr, 0, (short) ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(0)).getVersionMadeBy());
                copyByteArrayToArrayList(bArr, list2);
                Raw.writeShortLittleEndian(bArr, 0, (short) ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(0)).getVersionNeededToExtract());
                copyByteArrayToArrayList(bArr, list2);
            }
            Raw.writeIntLittleEndian(bArr3, 0, zipModel2.getEndCentralDirRecord().getNoOfThisDisk());
            copyByteArrayToArrayList(bArr3, list2);
            Raw.writeIntLittleEndian(bArr3, 0, zipModel2.getEndCentralDirRecord().getNoOfThisDiskStartOfCentralDir());
            copyByteArrayToArrayList(bArr3, list2);
            int i3 = 0;
            if (zipModel2.getCentralDirectory() == null || zipModel2.getCentralDirectory().getFileHeaders() == null) {
                Throwable th5 = th3;
                new ZipException("invalid central directory/file headers, cannot write end of central directory record");
                throw th5;
            }
            int size = zipModel2.getCentralDirectory().getFileHeaders().size();
            if (zipModel2.isSplitArchive()) {
                int countNumberOfFileHeaderEntriesOnDisk = countNumberOfFileHeaderEntriesOnDisk(zipModel2.getCentralDirectory().getFileHeaders(), zipModel2.getEndCentralDirRecord().getNoOfThisDisk());
            } else {
                i3 = size;
            }
            Raw.writeLongLittleEndian(bArr4, 0, (long) i3);
            copyByteArrayToArrayList(bArr4, list2);
            Raw.writeLongLittleEndian(bArr4, 0, (long) size);
            copyByteArrayToArrayList(bArr4, list2);
            Raw.writeLongLittleEndian(bArr4, 0, (long) i2);
            copyByteArrayToArrayList(bArr4, list2);
            Raw.writeLongLittleEndian(bArr4, 0, j2);
            copyByteArrayToArrayList(bArr4, list2);
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th6 = th2;
            new ZipException((Throwable) exc);
            throw th6;
        }
    }

    private void writeZip64EndOfCentralDirectoryLocator(ZipModel zipModel, OutputStream outputStream, List list) throws ZipException {
        Throwable th;
        Throwable th2;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        List list2 = list;
        if (zipModel2 == null || outputStream2 == null) {
            Throwable th3 = th;
            new ZipException("zip model or output stream is null, cannot write zip64 end of central directory locator");
            throw th3;
        }
        try {
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[8];
            Raw.writeIntLittleEndian(bArr, 0, 117853008);
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeIntLittleEndian(bArr, 0, zipModel2.getZip64EndCentralDirLocator().getNoOfDiskStartOfZip64EndOfCentralDirRec());
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeLongLittleEndian(bArr2, 0, zipModel2.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec());
            copyByteArrayToArrayList(bArr2, list2);
            Raw.writeIntLittleEndian(bArr, 0, zipModel2.getZip64EndCentralDirLocator().getTotNumberOfDiscs());
            copyByteArrayToArrayList(bArr, list2);
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th2;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private void writeEndOfCentralDirectoryRecord(ZipModel zipModel, OutputStream outputStream, int i, long j, List list) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int i2;
        ZipModel zipModel2 = zipModel;
        OutputStream outputStream2 = outputStream;
        int i3 = i;
        long j2 = j;
        List list2 = list;
        if (zipModel2 == null || outputStream2 == null) {
            Throwable th4 = th;
            new ZipException("zip model or output stream is null, cannot write end of central directory record");
            throw th4;
        }
        try {
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            Raw.writeIntLittleEndian(bArr2, 0, (int) zipModel2.getEndCentralDirRecord().getSignature());
            copyByteArrayToArrayList(bArr2, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) zipModel2.getEndCentralDirRecord().getNoOfThisDisk());
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) zipModel2.getEndCentralDirRecord().getNoOfThisDiskStartOfCentralDir());
            copyByteArrayToArrayList(bArr, list2);
            if (zipModel2.getCentralDirectory() == null || zipModel2.getCentralDirectory().getFileHeaders() == null) {
                Throwable th5 = th3;
                new ZipException("invalid central directory/file headers, cannot write end of central directory record");
                throw th5;
            }
            int size = zipModel2.getCentralDirectory().getFileHeaders().size();
            if (zipModel2.isSplitArchive()) {
                i2 = countNumberOfFileHeaderEntriesOnDisk(zipModel2.getCentralDirectory().getFileHeaders(), zipModel2.getEndCentralDirRecord().getNoOfThisDisk());
            } else {
                i2 = size;
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) i2);
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeShortLittleEndian(bArr, 0, (short) size);
            copyByteArrayToArrayList(bArr, list2);
            Raw.writeIntLittleEndian(bArr2, 0, i3);
            copyByteArrayToArrayList(bArr2, list2);
            if (j2 > InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, j2);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list2);
            }
            int i4 = 0;
            if (zipModel2.getEndCentralDirRecord().getComment() != null) {
                i4 = zipModel2.getEndCentralDirRecord().getCommentLength();
            }
            Raw.writeShortLittleEndian(bArr, 0, (short) i4);
            copyByteArrayToArrayList(bArr, list2);
            if (i4 > 0) {
                copyByteArrayToArrayList(zipModel2.getEndCentralDirRecord().getCommentBytes(), list2);
            }
        } catch (Exception e) {
            Exception exc = e;
            Throwable th6 = th2;
            new ZipException((Throwable) exc);
            throw th6;
        }
    }

    public void updateLocalFileHeader(LocalFileHeader localFileHeader, long j, int i, ZipModel zipModel, byte[] bArr, int i2, SplitOutputStream splitOutputStream) throws ZipException {
        Throwable th;
        Throwable th2;
        SplitOutputStream splitOutputStream2;
        File file;
        StringBuilder sb;
        StringBuilder sb2;
        String sb3;
        SplitOutputStream splitOutputStream3;
        File file2;
        StringBuilder sb4;
        LocalFileHeader localFileHeader2 = localFileHeader;
        long j2 = j;
        int i3 = i;
        ZipModel zipModel2 = zipModel;
        byte[] bArr2 = bArr;
        int i4 = i2;
        SplitOutputStream splitOutputStream4 = splitOutputStream;
        if (localFileHeader2 == null || j2 < 0 || zipModel2 == null) {
            Throwable th3 = th;
            new ZipException("invalid input parameters, cannot update local file header");
            throw th3;
        }
        boolean z = false;
        try {
            if (i4 != splitOutputStream4.getCurrSplitFileCounter()) {
                new File(zipModel2.getZipFile());
                File file3 = file;
                String parent = file3.getParent();
                String zipFileNameWithoutExt = Zip4jUtil.getZipFileNameWithoutExt(file3.getName());
                new StringBuilder();
                String sb5 = sb.append(parent).append(System.getProperty("file.separator")).toString();
                if (i4 < 9) {
                    new StringBuilder();
                    sb3 = sb4.append(sb5).append(zipFileNameWithoutExt).append(".z0").append(i4 + 1).toString();
                } else {
                    new StringBuilder();
                    sb3 = sb2.append(sb5).append(zipFileNameWithoutExt).append(".z").append(i4 + 1).toString();
                }
                new File(sb3);
                new SplitOutputStream(file2);
                splitOutputStream2 = splitOutputStream3;
                z = true;
            } else {
                splitOutputStream2 = splitOutputStream4;
            }
            long filePointer = splitOutputStream2.getFilePointer();
            switch (i3) {
                case 14:
                    splitOutputStream2.seek(j2 + ((long) i3));
                    splitOutputStream2.write(bArr2);
                    break;
                case 18:
                case 22:
                    updateCompressedSizeInLocalFileHeader(splitOutputStream2, localFileHeader2, j2, (long) i3, bArr2, zipModel2.isZip64Format());
                    break;
            }
            if (z) {
                splitOutputStream2.close();
            } else {
                splitOutputStream4.seek(filePointer);
            }
        } catch (Exception e) {
            Exception exc = e;
            Throwable th4 = th2;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private void updateCompressedSizeInLocalFileHeader(SplitOutputStream splitOutputStream, LocalFileHeader localFileHeader, long j, long j2, byte[] bArr, boolean z) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        SplitOutputStream splitOutputStream2 = splitOutputStream;
        LocalFileHeader localFileHeader2 = localFileHeader;
        long j3 = j;
        long j4 = j2;
        byte[] bArr2 = bArr;
        boolean z2 = z;
        if (splitOutputStream2 == null) {
            Throwable th4 = th3;
            new ZipException("invalid output stream, cannot update compressed size for local file header");
            throw th4;
        }
        try {
            if (!localFileHeader2.isWriteComprSizeInZip64ExtraRecord()) {
                splitOutputStream2.seek(j3 + j4);
                splitOutputStream2.write(bArr2);
            } else if (bArr2.length != 8) {
                Throwable th5 = th2;
                new ZipException("attempting to write a non 8-byte compressed size block for a zip64 file");
                throw th5;
            } else {
                long fileNameLength = j3 + j4 + 4 + 4 + 2 + 2 + ((long) localFileHeader2.getFileNameLength()) + 2 + 2 + 8;
                if (j4 == 22) {
                    fileNameLength += 8;
                }
                splitOutputStream2.seek(fileNameLength);
                splitOutputStream2.write(bArr2);
            }
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th6 = th;
            new ZipException((Throwable) iOException);
            throw th6;
        }
    }

    private void copyByteArrayToArrayList(byte[] bArr, List list) throws ZipException {
        Throwable th;
        byte[] bArr2 = bArr;
        List list2 = list;
        if (list2 == null || bArr2 == null) {
            Throwable th2 = th;
            new ZipException("one of the input parameters is null, cannot copy byte array to array list");
            throw th2;
        }
        for (int i = 0; i < bArr2.length; i++) {
            boolean add = list2.add(Byte.toString(bArr2[i]));
        }
    }

    private byte[] byteArrayListToByteArray(List list) throws ZipException {
        Throwable th;
        List list2 = list;
        if (list2 == null) {
            Throwable th2 = th;
            new ZipException("input byte array list is null, cannot conver to byte array");
            throw th2;
        } else if (list2.size() <= 0) {
            return null;
        } else {
            byte[] bArr = new byte[list2.size()];
            for (int i = 0; i < list2.size(); i++) {
                bArr[i] = Byte.parseByte((String) list2.get(i));
            }
            return bArr;
        }
    }

    private int countNumberOfFileHeaderEntriesOnDisk(ArrayList arrayList, int i) throws ZipException {
        Throwable th;
        ArrayList arrayList2 = arrayList;
        int i2 = i;
        if (arrayList2 == null) {
            Throwable th2 = th;
            new ZipException("file headers are null, cannot calculate number of entries on this disk");
            throw th2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            if (((FileHeader) arrayList2.get(i4)).getDiskNumberStart() == i2) {
                i3++;
            }
        }
        return i3;
    }
}
