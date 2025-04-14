package net.lingala.zip4j.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import net.lingala.zip4j.core.HeaderWriter;
import net.lingala.zip4j.crypto.AESEncrpyter;
import net.lingala.zip4j.crypto.IEncrypter;
import net.lingala.zip4j.crypto.StandardEncrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.EndCentralDirRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class CipherOutputStream extends BaseOutputStream {
    private long bytesWrittenForThisFile = 0;
    protected CRC32 crc;
    private IEncrypter encrypter;
    protected FileHeader fileHeader;
    protected LocalFileHeader localFileHeader;
    protected OutputStream outputStream;
    private byte[] pendingBuffer = new byte[16];
    private int pendingBufferLength = 0;
    private File sourceFile;
    private long totalBytesRead = 0;
    private long totalBytesWritten = 0;
    protected ZipModel zipModel;
    protected ZipParameters zipParameters;

    public CipherOutputStream(OutputStream outputStream2, ZipModel zipModel2) {
        CRC32 crc32;
        this.outputStream = outputStream2;
        initZipModel(zipModel2);
        new CRC32();
        this.crc = crc32;
    }

    public void putNextEntry(File file, ZipParameters zipParameters2) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HeaderWriter headerWriter;
        Throwable th4;
        Throwable th5;
        File file2 = file;
        ZipParameters zipParameters3 = zipParameters2;
        if (!zipParameters3.isSourceExternalStream() && file2 == null) {
            Throwable th6 = th5;
            new ZipException("input file is null");
            throw th6;
        } else if (zipParameters3.isSourceExternalStream() || Zip4jUtil.checkFileExists(file2)) {
            try {
                this.sourceFile = file2;
                this.zipParameters = (ZipParameters) zipParameters3.clone();
                if (!zipParameters3.isSourceExternalStream()) {
                    if (this.sourceFile.isDirectory()) {
                        this.zipParameters.setEncryptFiles(false);
                        this.zipParameters.setEncryptionMethod(-1);
                        this.zipParameters.setCompressionMethod(0);
                    }
                } else if (!Zip4jUtil.isStringNotNullAndNotEmpty(this.zipParameters.getFileNameInZip())) {
                    Throwable th7 = th3;
                    new ZipException("file name is empty for external stream");
                    throw th7;
                } else if (this.zipParameters.getFileNameInZip().endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) || this.zipParameters.getFileNameInZip().endsWith("\\")) {
                    this.zipParameters.setEncryptFiles(false);
                    this.zipParameters.setEncryptionMethod(-1);
                    this.zipParameters.setCompressionMethod(0);
                }
                createFileHeader();
                createLocalFileHeader();
                if (this.zipModel.isSplitArchive() && (this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null || this.zipModel.getCentralDirectory().getFileHeaders().size() == 0)) {
                    byte[] bArr = new byte[4];
                    Raw.writeIntLittleEndian(bArr, 0, 134695760);
                    this.outputStream.write(bArr);
                    this.totalBytesWritten += 4;
                }
                if (this.outputStream instanceof SplitOutputStream) {
                    if (this.totalBytesWritten == 4) {
                        this.fileHeader.setOffsetLocalHeader(4);
                    } else {
                        this.fileHeader.setOffsetLocalHeader(((SplitOutputStream) this.outputStream).getFilePointer());
                    }
                } else if (this.totalBytesWritten == 4) {
                    this.fileHeader.setOffsetLocalHeader(4);
                } else {
                    this.fileHeader.setOffsetLocalHeader(this.totalBytesWritten);
                }
                new HeaderWriter();
                HeaderWriter headerWriter2 = headerWriter;
                this.totalBytesWritten += (long) headerWriter2.writeLocalFileHeader(this.zipModel, this.localFileHeader, this.outputStream);
                if (this.zipParameters.isEncryptFiles()) {
                    initEncrypter();
                    if (this.encrypter != null) {
                        if (zipParameters3.getEncryptionMethod() == 0) {
                            byte[] headerBytes = ((StandardEncrypter) this.encrypter).getHeaderBytes();
                            this.outputStream.write(headerBytes);
                            this.totalBytesWritten += (long) headerBytes.length;
                            this.bytesWrittenForThisFile += (long) headerBytes.length;
                        } else if (zipParameters3.getEncryptionMethod() == 99) {
                            byte[] saltBytes = ((AESEncrpyter) this.encrypter).getSaltBytes();
                            byte[] derivedPasswordVerifier = ((AESEncrpyter) this.encrypter).getDerivedPasswordVerifier();
                            this.outputStream.write(saltBytes);
                            this.outputStream.write(derivedPasswordVerifier);
                            this.totalBytesWritten += (long) (saltBytes.length + derivedPasswordVerifier.length);
                            this.bytesWrittenForThisFile += (long) (saltBytes.length + derivedPasswordVerifier.length);
                        }
                    }
                }
                this.crc.reset();
            } catch (CloneNotSupportedException e) {
                CloneNotSupportedException cloneNotSupportedException = e;
                Throwable th8 = th2;
                new ZipException((Throwable) cloneNotSupportedException);
                throw th8;
            } catch (ZipException e2) {
                throw e2;
            } catch (Exception e3) {
                Exception exc = e3;
                Throwable th9 = th;
                new ZipException((Throwable) exc);
                throw th9;
            }
        } else {
            Throwable th10 = th4;
            new ZipException("input file does not exist");
            throw th10;
        }
    }

    private void initEncrypter() throws ZipException {
        IEncrypter iEncrypter;
        IEncrypter iEncrypter2;
        Throwable th;
        if (!this.zipParameters.isEncryptFiles()) {
            this.encrypter = null;
            return;
        }
        switch (this.zipParameters.getEncryptionMethod()) {
            case 0:
                new StandardEncrypter(this.zipParameters.getPassword(), (this.localFileHeader.getLastModFileTime() & 65535) << 16);
                this.encrypter = iEncrypter2;
                return;
            case 99:
                new AESEncrpyter(this.zipParameters.getPassword(), this.zipParameters.getAesKeyStrength());
                this.encrypter = iEncrypter;
                return;
            default:
                Throwable th2 = th;
                new ZipException("invalid encprytion method");
                throw th2;
        }
    }

    private void initZipModel(ZipModel zipModel2) {
        List list;
        ArrayList arrayList;
        CentralDirectory centralDirectory;
        EndCentralDirRecord endCentralDirRecord;
        ZipModel zipModel3;
        ZipModel zipModel4 = zipModel2;
        if (zipModel4 == null) {
            new ZipModel();
            this.zipModel = zipModel3;
        } else {
            this.zipModel = zipModel4;
        }
        if (this.zipModel.getEndCentralDirRecord() == null) {
            new EndCentralDirRecord();
            this.zipModel.setEndCentralDirRecord(endCentralDirRecord);
        }
        if (this.zipModel.getCentralDirectory() == null) {
            new CentralDirectory();
            this.zipModel.setCentralDirectory(centralDirectory);
        }
        if (this.zipModel.getCentralDirectory().getFileHeaders() == null) {
            new ArrayList();
            this.zipModel.getCentralDirectory().setFileHeaders(arrayList);
        }
        if (this.zipModel.getLocalFileHeaderList() == null) {
            new ArrayList();
            this.zipModel.setLocalFileHeaderList(list);
        }
        if ((this.outputStream instanceof SplitOutputStream) && ((SplitOutputStream) this.outputStream).isSplitZipFile()) {
            this.zipModel.setSplitArchive(true);
            this.zipModel.setSplitLength(((SplitOutputStream) this.outputStream).getSplitLength());
        }
        this.zipModel.getEndCentralDirRecord().setSignature(InternalZipConstants.ENDSIG);
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void write(byte[] bArr) throws IOException {
        Throwable th;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            Throwable th2 = th;
            new NullPointerException();
            throw th2;
        } else if (bArr2.length != 0) {
            write(bArr2, 0, bArr2.length);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (i4 != 0) {
            if (this.zipParameters.isEncryptFiles() && this.zipParameters.getEncryptionMethod() == 99) {
                if (this.pendingBufferLength != 0) {
                    if (i4 >= 16 - this.pendingBufferLength) {
                        System.arraycopy(bArr2, i3, this.pendingBuffer, this.pendingBufferLength, 16 - this.pendingBufferLength);
                        encryptAndWrite(this.pendingBuffer, 0, this.pendingBuffer.length);
                        i3 = 16 - this.pendingBufferLength;
                        i4 -= i3;
                        this.pendingBufferLength = 0;
                    } else {
                        System.arraycopy(bArr2, i3, this.pendingBuffer, this.pendingBufferLength, i4);
                        this.pendingBufferLength += i4;
                        return;
                    }
                }
                if (!(i4 == 0 || i4 % 16 == 0)) {
                    System.arraycopy(bArr2, (i4 + i3) - (i4 % 16), this.pendingBuffer, 0, i4 % 16);
                    this.pendingBufferLength = i4 % 16;
                    i4 -= this.pendingBufferLength;
                }
            }
            if (i4 != 0) {
                encryptAndWrite(bArr2, i3, i4);
            }
        }
    }

    private void encryptAndWrite(byte[] bArr, int i, int i2) throws IOException {
        Throwable th;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this.encrypter != null) {
            try {
                int encryptData = this.encrypter.encryptData(bArr2, i3, i4);
            } catch (ZipException e) {
                ZipException zipException = e;
                Throwable th2 = th;
                new IOException(zipException.getMessage());
                throw th2;
            }
        }
        this.outputStream.write(bArr2, i3, i4);
        this.totalBytesWritten += (long) i4;
        this.bytesWrittenForThisFile += (long) i4;
    }

    public void closeEntry() throws IOException, ZipException {
        HeaderWriter headerWriter;
        Throwable th;
        if (this.pendingBufferLength != 0) {
            encryptAndWrite(this.pendingBuffer, 0, this.pendingBufferLength);
            this.pendingBufferLength = 0;
        }
        if (this.zipParameters.isEncryptFiles() && this.zipParameters.getEncryptionMethod() == 99) {
            if (this.encrypter instanceof AESEncrpyter) {
                this.outputStream.write(((AESEncrpyter) this.encrypter).getFinalMac());
                this.bytesWrittenForThisFile += 10;
                this.totalBytesWritten += 10;
            } else {
                Throwable th2 = th;
                new ZipException("invalid encrypter for AES encrypted file");
                throw th2;
            }
        }
        this.fileHeader.setCompressedSize(this.bytesWrittenForThisFile);
        this.localFileHeader.setCompressedSize(this.bytesWrittenForThisFile);
        if (this.zipParameters.isSourceExternalStream()) {
            this.fileHeader.setUncompressedSize(this.totalBytesRead);
            if (this.localFileHeader.getUncompressedSize() != this.totalBytesRead) {
                this.localFileHeader.setUncompressedSize(this.totalBytesRead);
            }
        }
        long value = this.crc.getValue();
        if (this.fileHeader.isEncrypted() && this.fileHeader.getEncryptionMethod() == 99) {
            value = 0;
        }
        if (!this.zipParameters.isEncryptFiles() || this.zipParameters.getEncryptionMethod() != 99) {
            this.fileHeader.setCrc32(value);
            this.localFileHeader.setCrc32(value);
        } else {
            this.fileHeader.setCrc32(0);
            this.localFileHeader.setCrc32(0);
        }
        boolean add = this.zipModel.getLocalFileHeaderList().add(this.localFileHeader);
        boolean add2 = this.zipModel.getCentralDirectory().getFileHeaders().add(this.fileHeader);
        new HeaderWriter();
        HeaderWriter headerWriter2 = headerWriter;
        this.totalBytesWritten += (long) headerWriter2.writeExtendedLocalHeader(this.localFileHeader, this.outputStream);
        this.crc.reset();
        this.bytesWrittenForThisFile = 0;
        this.encrypter = null;
        this.totalBytesRead = 0;
    }

    public void finish() throws IOException, ZipException {
        HeaderWriter headerWriter;
        this.zipModel.getEndCentralDirRecord().setOffsetOfStartOfCentralDir(this.totalBytesWritten);
        new HeaderWriter();
        headerWriter.finalizeZipFile(this.zipModel, this.outputStream);
    }

    public void close() throws IOException {
        if (this.outputStream != null) {
            this.outputStream.close();
        }
    }

    private void createFileHeader() throws ZipException {
        FileHeader fileHeader2;
        String relativeFileName;
        int i;
        Throwable th;
        Throwable th2;
        Throwable th3;
        new FileHeader();
        this.fileHeader = fileHeader2;
        this.fileHeader.setSignature(33639248);
        this.fileHeader.setVersionMadeBy(20);
        this.fileHeader.setVersionNeededToExtract(20);
        if (!this.zipParameters.isEncryptFiles() || this.zipParameters.getEncryptionMethod() != 99) {
            this.fileHeader.setCompressionMethod(this.zipParameters.getCompressionMethod());
        } else {
            this.fileHeader.setCompressionMethod(99);
            this.fileHeader.setAesExtraDataRecord(generateAESExtraDataRecord(this.zipParameters));
        }
        if (this.zipParameters.isEncryptFiles()) {
            this.fileHeader.setEncrypted(true);
            this.fileHeader.setEncryptionMethod(this.zipParameters.getEncryptionMethod());
        }
        if (this.zipParameters.isSourceExternalStream()) {
            this.fileHeader.setLastModFileTime((int) Zip4jUtil.javaToDosTime(System.currentTimeMillis()));
            if (!Zip4jUtil.isStringNotNullAndNotEmpty(this.zipParameters.getFileNameInZip())) {
                Throwable th4 = th3;
                new ZipException("fileNameInZip is null or empty");
                throw th4;
            }
            relativeFileName = this.zipParameters.getFileNameInZip();
        } else {
            this.fileHeader.setLastModFileTime((int) Zip4jUtil.javaToDosTime(Zip4jUtil.getLastModifiedFileTime(this.sourceFile, this.zipParameters.getTimeZone())));
            this.fileHeader.setUncompressedSize(this.sourceFile.length());
            relativeFileName = Zip4jUtil.getRelativeFileName(this.sourceFile.getAbsolutePath(), this.zipParameters.getRootFolderInZip(), this.zipParameters.getDefaultFolderPath());
        }
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(relativeFileName)) {
            Throwable th5 = th2;
            new ZipException("fileName is null or empty. unable to create file header");
            throw th5;
        }
        this.fileHeader.setFileName(relativeFileName);
        if (Zip4jUtil.isStringNotNullAndNotEmpty(this.zipModel.getFileNameCharset())) {
            this.fileHeader.setFileNameLength(Zip4jUtil.getEncodedStringLength(relativeFileName, this.zipModel.getFileNameCharset()));
        } else {
            this.fileHeader.setFileNameLength(Zip4jUtil.getEncodedStringLength(relativeFileName));
        }
        if (this.outputStream instanceof SplitOutputStream) {
            this.fileHeader.setDiskNumberStart(((SplitOutputStream) this.outputStream).getCurrSplitFileCounter());
        } else {
            this.fileHeader.setDiskNumberStart(0);
        }
        int i2 = 0;
        if (!this.zipParameters.isSourceExternalStream()) {
            i2 = getFileAttributes(this.sourceFile);
        }
        byte[] bArr = new byte[4];
        bArr[0] = (byte) i2;
        byte[] bArr2 = bArr;
        bArr2[1] = 0;
        byte[] bArr3 = bArr2;
        bArr3[2] = 0;
        byte[] bArr4 = bArr3;
        bArr4[3] = 0;
        this.fileHeader.setExternalFileAttr(bArr4);
        if (this.zipParameters.isSourceExternalStream()) {
            this.fileHeader.setDirectory(relativeFileName.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) || relativeFileName.endsWith("\\"));
        } else {
            this.fileHeader.setDirectory(this.sourceFile.isDirectory());
        }
        if (this.fileHeader.isDirectory()) {
            this.fileHeader.setCompressedSize(0);
            this.fileHeader.setUncompressedSize(0);
        } else if (!this.zipParameters.isSourceExternalStream()) {
            long fileLengh = Zip4jUtil.getFileLengh(this.sourceFile);
            if (this.zipParameters.getCompressionMethod() != 0) {
                this.fileHeader.setCompressedSize(0);
            } else if (this.zipParameters.getEncryptionMethod() == 0) {
                this.fileHeader.setCompressedSize(fileLengh + 12);
            } else if (this.zipParameters.getEncryptionMethod() == 99) {
                switch (this.zipParameters.getAesKeyStrength()) {
                    case 1:
                        i = 8;
                        break;
                    case 3:
                        i = 16;
                        break;
                    default:
                        Throwable th6 = th;
                        new ZipException("invalid aes key strength, cannot determine key sizes");
                        throw th6;
                }
                this.fileHeader.setCompressedSize(fileLengh + ((long) i) + 10 + 2);
            } else {
                this.fileHeader.setCompressedSize(0);
            }
            this.fileHeader.setUncompressedSize(fileLengh);
        }
        if (this.zipParameters.isEncryptFiles() && this.zipParameters.getEncryptionMethod() == 0) {
            this.fileHeader.setCrc32((long) this.zipParameters.getSourceFileCRC());
        }
        byte[] bArr5 = new byte[2];
        bArr5[0] = Raw.bitArrayToByte(generateGeneralPurposeBitArray(this.fileHeader.isEncrypted(), this.zipParameters.getCompressionMethod()));
        boolean isStringNotNullAndNotEmpty = Zip4jUtil.isStringNotNullAndNotEmpty(this.zipModel.getFileNameCharset());
        if ((!isStringNotNullAndNotEmpty || !this.zipModel.getFileNameCharset().equalsIgnoreCase(InternalZipConstants.CHARSET_UTF8)) && (isStringNotNullAndNotEmpty || !Zip4jUtil.detectCharSet(this.fileHeader.getFileName()).equals(InternalZipConstants.CHARSET_UTF8))) {
            bArr5[1] = 0;
        } else {
            bArr5[1] = 8;
        }
        this.fileHeader.setGeneralPurposeFlag(bArr5);
    }

    private void createLocalFileHeader() throws ZipException {
        LocalFileHeader localFileHeader2;
        Throwable th;
        if (this.fileHeader == null) {
            Throwable th2 = th;
            new ZipException("file header is null, cannot create local file header");
            throw th2;
        }
        new LocalFileHeader();
        this.localFileHeader = localFileHeader2;
        this.localFileHeader.setSignature(67324752);
        this.localFileHeader.setVersionNeededToExtract(this.fileHeader.getVersionNeededToExtract());
        this.localFileHeader.setCompressionMethod(this.fileHeader.getCompressionMethod());
        this.localFileHeader.setLastModFileTime(this.fileHeader.getLastModFileTime());
        this.localFileHeader.setUncompressedSize(this.fileHeader.getUncompressedSize());
        this.localFileHeader.setFileNameLength(this.fileHeader.getFileNameLength());
        this.localFileHeader.setFileName(this.fileHeader.getFileName());
        this.localFileHeader.setEncrypted(this.fileHeader.isEncrypted());
        this.localFileHeader.setEncryptionMethod(this.fileHeader.getEncryptionMethod());
        this.localFileHeader.setAesExtraDataRecord(this.fileHeader.getAesExtraDataRecord());
        this.localFileHeader.setCrc32(this.fileHeader.getCrc32());
        this.localFileHeader.setCompressedSize(this.fileHeader.getCompressedSize());
        this.localFileHeader.setGeneralPurposeFlag((byte[]) this.fileHeader.getGeneralPurposeFlag().clone());
    }

    private int getFileAttributes(File file) throws ZipException {
        Throwable th;
        File file2 = file;
        if (file2 == null) {
            Throwable th2 = th;
            new ZipException("input file is null, cannot get file attributes");
            throw th2;
        } else if (!file2.exists()) {
            return 0;
        } else {
            if (file2.isDirectory()) {
                if (file2.isHidden()) {
                    return 18;
                }
                return 16;
            } else if (!file2.canWrite() && file2.isHidden()) {
                return 3;
            } else {
                if (!file2.canWrite()) {
                    return 1;
                }
                if (file2.isHidden()) {
                    return 2;
                }
                return 0;
            }
        }
    }

    private int[] generateGeneralPurposeBitArray(boolean z, int i) {
        int i2 = i;
        int[] iArr = new int[8];
        if (z) {
            iArr[0] = 1;
        } else {
            iArr[0] = 0;
        }
        if (i2 != 8) {
            iArr[1] = 0;
            iArr[2] = 0;
        }
        iArr[3] = 1;
        return iArr;
    }

    private AESExtraDataRecord generateAESExtraDataRecord(ZipParameters zipParameters2) throws ZipException {
        AESExtraDataRecord aESExtraDataRecord;
        Throwable th;
        Throwable th2;
        ZipParameters zipParameters3 = zipParameters2;
        if (zipParameters3 == null) {
            Throwable th3 = th2;
            new ZipException("zip parameters are null, cannot generate AES Extra Data record");
            throw th3;
        }
        new AESExtraDataRecord();
        AESExtraDataRecord aESExtraDataRecord2 = aESExtraDataRecord;
        aESExtraDataRecord2.setSignature(39169);
        aESExtraDataRecord2.setDataSize(7);
        aESExtraDataRecord2.setVendorID("AE");
        aESExtraDataRecord2.setVersionNumber(2);
        if (zipParameters3.getAesKeyStrength() == 1) {
            aESExtraDataRecord2.setAesStrength(1);
        } else if (zipParameters3.getAesKeyStrength() == 3) {
            aESExtraDataRecord2.setAesStrength(3);
        } else {
            Throwable th4 = th;
            new ZipException("invalid AES key strength, cannot generate AES Extra data record");
            throw th4;
        }
        aESExtraDataRecord2.setCompressionMethod(zipParameters3.getCompressionMethod());
        return aESExtraDataRecord2;
    }

    public void decrementCompressedFileSize(int i) {
        int i2 = i;
        if (i2 > 0 && ((long) i2) <= this.bytesWrittenForThisFile) {
            this.bytesWrittenForThisFile -= (long) i2;
        }
    }

    /* access modifiers changed from: protected */
    public void updateTotalBytesRead(int i) {
        int i2 = i;
        if (i2 > 0) {
            this.totalBytesRead += (long) i2;
        }
    }

    public void setSourceFile(File file) {
        File file2 = file;
        this.sourceFile = file2;
    }

    public File getSourceFile() {
        return this.sourceFile;
    }
}
