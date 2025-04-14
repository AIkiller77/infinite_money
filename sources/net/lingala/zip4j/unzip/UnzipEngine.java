package net.lingala.zip4j.unzip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.CRC32;
import net.lingala.zip4j.core.HeaderReader;
import net.lingala.zip4j.crypto.AESDecrypter;
import net.lingala.zip4j.crypto.IDecrypter;
import net.lingala.zip4j.crypto.StandardDecrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.BaseInputStream;
import net.lingala.zip4j.io.InflaterInputStream;
import net.lingala.zip4j.io.PartInputStream;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class UnzipEngine {
    private CRC32 crc;
    private int currSplitFileCounter = 0;
    private IDecrypter decrypter;
    private FileHeader fileHeader;
    private LocalFileHeader localFileHeader;
    private ZipModel zipModel;

    public UnzipEngine(ZipModel zipModel2, FileHeader fileHeader2) throws ZipException {
        Throwable th;
        CRC32 crc32;
        ZipModel zipModel3 = zipModel2;
        FileHeader fileHeader3 = fileHeader2;
        if (zipModel3 == null || fileHeader3 == null) {
            Throwable th2 = th;
            new ZipException("Invalid parameters passed to StoreUnzip. One or more of the parameters were null");
            throw th2;
        }
        this.zipModel = zipModel3;
        this.fileHeader = fileHeader3;
        new CRC32();
        this.crc = crc32;
    }

    public void unzipFile(ProgressMonitor progressMonitor, String str, String str2, UnzipParameters unzipParameters) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        File file;
        ProgressMonitor progressMonitor2 = progressMonitor;
        String str3 = str;
        String str4 = str2;
        UnzipParameters unzipParameters2 = unzipParameters;
        if (this.zipModel == null || this.fileHeader == null || !Zip4jUtil.isStringNotNullAndNotEmpty(str3)) {
            Throwable th4 = th;
            new ZipException("Invalid parameters passed during unzipping file. One or more of the parameters were null");
            throw th4;
        }
        ZipInputStream zipInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bArr = new byte[4096];
            zipInputStream = getInputStream();
            fileOutputStream = getOutputStream(str3, str4);
            do {
                int read = zipInputStream.read(bArr);
                int i = read;
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, i);
                    progressMonitor2.updateWorkCompleted((long) i);
                } else {
                    closeStreams(zipInputStream, fileOutputStream);
                    new File(getOutputFileNameWithPath(str3, str4));
                    UnzipUtil.applyFileAttributes(this.fileHeader, file, unzipParameters2);
                    closeStreams(zipInputStream, fileOutputStream);
                    return;
                }
            } while (!progressMonitor2.isCancelAllTasks());
            progressMonitor2.setResult(3);
            progressMonitor2.setState(0);
            closeStreams(zipInputStream, fileOutputStream);
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th5 = th3;
            new ZipException((Throwable) iOException);
            throw th5;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th6 = th2;
            new ZipException((Throwable) exc);
            throw th6;
        } catch (Throwable th7) {
            Throwable th8 = th7;
            closeStreams(zipInputStream, fileOutputStream);
            throw th8;
        }
    }

    public ZipInputStream getInputStream() throws ZipException {
        Throwable th;
        ZipInputStream zipInputStream;
        BaseInputStream baseInputStream;
        ZipInputStream zipInputStream2;
        BaseInputStream baseInputStream2;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        Throwable th4;
        StringBuilder sb2;
        Throwable th5;
        Throwable th6;
        if (this.fileHeader == null) {
            Throwable th7 = th6;
            new ZipException("file header is null, cannot get inputstream");
            throw th7;
        }
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = createFileHandler(InternalZipConstants.READ_MODE);
            String str = "local header and file header do not match";
            if (!checkLocalHeader()) {
                Throwable th8 = th5;
                new ZipException(str);
                throw th8;
            }
            init(randomAccessFile);
            long compressedSize = this.localFileHeader.getCompressedSize();
            long offsetStartOfData = this.localFileHeader.getOffsetStartOfData();
            if (this.localFileHeader.isEncrypted()) {
                if (this.localFileHeader.getEncryptionMethod() == 99) {
                    if (this.decrypter instanceof AESDecrypter) {
                        compressedSize -= (long) ((((AESDecrypter) this.decrypter).getSaltLength() + ((AESDecrypter) this.decrypter).getPasswordVerifierLength()) + 10);
                        offsetStartOfData += (long) (((AESDecrypter) this.decrypter).getSaltLength() + ((AESDecrypter) this.decrypter).getPasswordVerifierLength());
                    } else {
                        Throwable th9 = th4;
                        new StringBuilder();
                        new ZipException(sb2.append("invalid decryptor when trying to calculate compressed size for AES encrypted file: ").append(this.fileHeader.getFileName()).toString());
                        throw th9;
                    }
                } else if (this.localFileHeader.getEncryptionMethod() == 0) {
                    compressedSize -= 12;
                    offsetStartOfData += 12;
                }
            }
            int compressionMethod = this.fileHeader.getCompressionMethod();
            if (this.fileHeader.getEncryptionMethod() == 99) {
                if (this.fileHeader.getAesExtraDataRecord() != null) {
                    compressionMethod = this.fileHeader.getAesExtraDataRecord().getCompressionMethod();
                } else {
                    Throwable th10 = th3;
                    new StringBuilder();
                    new ZipException(sb.append("AESExtraDataRecord does not exist for AES encrypted file: ").append(this.fileHeader.getFileName()).toString());
                    throw th10;
                }
            }
            randomAccessFile.seek(offsetStartOfData);
            switch (compressionMethod) {
                case 0:
                    new PartInputStream(randomAccessFile, offsetStartOfData, compressedSize, this);
                    new ZipInputStream(baseInputStream2);
                    return zipInputStream2;
                case 8:
                    ZipInputStream zipInputStream3 = zipInputStream;
                    new InflaterInputStream(randomAccessFile, offsetStartOfData, compressedSize, this);
                    new ZipInputStream(baseInputStream);
                    return zipInputStream3;
                default:
                    Throwable th11 = th2;
                    new ZipException("compression type not supported");
                    throw th11;
            }
        } catch (ZipException e) {
            ZipException zipException = e;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e2) {
                    IOException iOException = e2;
                }
            }
            throw zipException;
        } catch (Exception e3) {
            Exception exc = e3;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e4) {
                    IOException iOException2 = e4;
                }
            }
            Throwable th12 = th;
            new ZipException((Throwable) exc);
            throw th12;
        }
    }

    private void init(RandomAccessFile randomAccessFile) throws ZipException {
        Throwable th;
        Throwable th2;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        if (this.localFileHeader == null) {
            Throwable th3 = th2;
            new ZipException("local file header is null, cannot initialize input stream");
            throw th3;
        }
        try {
            initDecrypter(randomAccessFile2);
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private void initDecrypter(RandomAccessFile randomAccessFile) throws ZipException {
        Throwable th;
        IDecrypter iDecrypter;
        IDecrypter iDecrypter2;
        Throwable th2;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        if (this.localFileHeader == null) {
            Throwable th3 = th2;
            new ZipException("local file header is null, cannot init decrypter");
            throw th3;
        } else if (!this.localFileHeader.isEncrypted()) {
        } else {
            if (this.localFileHeader.getEncryptionMethod() == 0) {
                new StandardDecrypter(this.fileHeader, getStandardDecrypterHeaderBytes(randomAccessFile2));
                this.decrypter = iDecrypter2;
            } else if (this.localFileHeader.getEncryptionMethod() == 99) {
                new AESDecrypter(this.localFileHeader, getAESSalt(randomAccessFile2), getAESPasswordVerifier(randomAccessFile2));
                this.decrypter = iDecrypter;
            } else {
                Throwable th4 = th;
                new ZipException("unsupported encryption method");
                throw th4;
            }
        }
    }

    private byte[] getStandardDecrypterHeaderBytes(RandomAccessFile randomAccessFile) throws ZipException {
        Throwable th;
        Throwable th2;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        try {
            byte[] bArr = new byte[12];
            randomAccessFile2.seek(this.localFileHeader.getOffsetStartOfData());
            int read = randomAccessFile2.read(bArr, 0, 12);
            return bArr;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th3 = th2;
            new ZipException((Throwable) iOException);
            throw th3;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private byte[] getAESSalt(RandomAccessFile randomAccessFile) throws ZipException {
        Throwable th;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        if (this.localFileHeader.getAesExtraDataRecord() == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[calculateAESSaltLength(this.localFileHeader.getAesExtraDataRecord())];
            randomAccessFile2.seek(this.localFileHeader.getOffsetStartOfData());
            int read = randomAccessFile2.read(bArr);
            return bArr;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new ZipException((Throwable) iOException);
            throw th2;
        }
    }

    private byte[] getAESPasswordVerifier(RandomAccessFile randomAccessFile) throws ZipException {
        Throwable th;
        try {
            byte[] bArr = new byte[2];
            int read = randomAccessFile.read(bArr);
            return bArr;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new ZipException((Throwable) iOException);
            throw th2;
        }
    }

    private int calculateAESSaltLength(AESExtraDataRecord aESExtraDataRecord) throws ZipException {
        Throwable th;
        Throwable th2;
        AESExtraDataRecord aESExtraDataRecord2 = aESExtraDataRecord;
        if (aESExtraDataRecord2 == null) {
            Throwable th3 = th2;
            new ZipException("unable to determine salt length: AESExtraDataRecord is null");
            throw th3;
        }
        switch (aESExtraDataRecord2.getAesStrength()) {
            case 1:
                return 8;
            case 2:
                return 12;
            case 3:
                return 16;
            default:
                Throwable th4 = th;
                new ZipException("unable to determine salt length: invalid aes key strength");
                throw th4;
        }
    }

    public void checkCRC() throws ZipException {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        StringBuilder sb3;
        Throwable th3;
        StringBuilder sb4;
        if (this.fileHeader == null) {
            return;
        }
        if (this.fileHeader.getEncryptionMethod() == 99) {
            if (this.decrypter != null && (this.decrypter instanceof AESDecrypter)) {
                byte[] calculatedAuthenticationBytes = ((AESDecrypter) this.decrypter).getCalculatedAuthenticationBytes();
                byte[] storedMac = ((AESDecrypter) this.decrypter).getStoredMac();
                byte[] bArr = new byte[10];
                if (bArr == null || storedMac == null) {
                    Throwable th4 = th2;
                    new StringBuilder();
                    new ZipException(sb3.append("CRC (MAC) check failed for ").append(this.fileHeader.getFileName()).toString());
                    throw th4;
                }
                System.arraycopy(calculatedAuthenticationBytes, 0, bArr, 0, 10);
                if (!Arrays.equals(bArr, storedMac)) {
                    Throwable th5 = th3;
                    new StringBuilder();
                    new ZipException(sb4.append("invalid CRC (MAC) for file: ").append(this.fileHeader.getFileName()).toString());
                    throw th5;
                }
            }
        } else if ((this.crc.getValue() & InternalZipConstants.ZIP_64_LIMIT) != this.fileHeader.getCrc32()) {
            new StringBuilder();
            String sb5 = sb.append("invalid CRC for file: ").append(this.fileHeader.getFileName()).toString();
            if (this.localFileHeader.isEncrypted() && this.localFileHeader.getEncryptionMethod() == 0) {
                new StringBuilder();
                sb5 = sb2.append(sb5).append(" - Wrong Password?").toString();
            }
            Throwable th6 = th;
            new ZipException(sb5);
            throw th6;
        }
    }

    private boolean checkLocalHeader() throws ZipException {
        Throwable th;
        HeaderReader headerReader;
        Throwable th2;
        RandomAccessFile randomAccessFile;
        File file;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile2 = checkSplitFile();
            if (randomAccessFile2 == null) {
                new File(this.zipModel.getZipFile());
                new RandomAccessFile(file, InternalZipConstants.READ_MODE);
                randomAccessFile2 = randomAccessFile;
            }
            new HeaderReader(randomAccessFile2);
            this.localFileHeader = headerReader.readLocalFileHeader(this.fileHeader);
            if (this.localFileHeader == null) {
                Throwable th3 = th2;
                new ZipException("error reading local file header. Is this a valid zip file?");
                throw th3;
            } else if (this.localFileHeader.getCompressionMethod() != this.fileHeader.getCompressionMethod()) {
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    } catch (Exception e2) {
                        Exception exc = e2;
                    }
                }
                return false;
            } else {
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e3) {
                        IOException iOException2 = e3;
                    } catch (Exception e4) {
                        Exception exc2 = e4;
                    }
                }
                return true;
            }
        } catch (FileNotFoundException e5) {
            FileNotFoundException fileNotFoundException = e5;
            Throwable th4 = th;
            new ZipException((Throwable) fileNotFoundException);
            throw th4;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e6) {
                    IOException iOException3 = e6;
                } catch (Exception e7) {
                    Exception exc3 = e7;
                }
            }
            throw th6;
        }
    }

    private RandomAccessFile checkSplitFile() throws ZipException {
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        Throwable th;
        Throwable th2;
        RandomAccessFile randomAccessFile;
        Throwable th3;
        if (!this.zipModel.isSplitArchive()) {
            return null;
        }
        int diskNumberStart = this.fileHeader.getDiskNumberStart();
        this.currSplitFileCounter = diskNumberStart + 1;
        String zipFile = this.zipModel.getZipFile();
        if (diskNumberStart == this.zipModel.getEndCentralDirRecord().getNoOfThisDisk()) {
            sb2 = this.zipModel.getZipFile();
        } else if (diskNumberStart >= 9) {
            new StringBuilder();
            sb2 = sb3.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z").append(diskNumberStart + 1).toString();
        } else {
            new StringBuilder();
            sb2 = sb.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z0").append(diskNumberStart + 1).toString();
        }
        try {
            new RandomAccessFile(sb2, InternalZipConstants.READ_MODE);
            RandomAccessFile randomAccessFile2 = randomAccessFile;
            if (this.currSplitFileCounter == 1) {
                byte[] bArr = new byte[4];
                int read = randomAccessFile2.read(bArr);
                if (((long) Raw.readIntLittleEndian(bArr, 0)) != 134695760) {
                    Throwable th4 = th3;
                    new ZipException("invalid first part split file signature");
                    throw th4;
                }
            }
            return randomAccessFile2;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            Throwable th5 = th2;
            new ZipException((Throwable) fileNotFoundException);
            throw th5;
        } catch (IOException e2) {
            IOException iOException = e2;
            Throwable th6 = th;
            new ZipException((Throwable) iOException);
            throw th6;
        }
    }

    private RandomAccessFile createFileHandler(String str) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        RandomAccessFile randomAccessFile;
        File file;
        RandomAccessFile randomAccessFile2;
        String str2 = str;
        if (this.zipModel == null || !Zip4jUtil.isStringNotNullAndNotEmpty(this.zipModel.getZipFile())) {
            Throwable th4 = th;
            new ZipException("input parameter is null in getFilePointer");
            throw th4;
        }
        try {
            if (this.zipModel.isSplitArchive()) {
                randomAccessFile2 = checkSplitFile();
            } else {
                RandomAccessFile randomAccessFile3 = randomAccessFile;
                new File(this.zipModel.getZipFile());
                new RandomAccessFile(file, str2);
                randomAccessFile2 = randomAccessFile3;
            }
            return randomAccessFile2;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            Throwable th5 = th3;
            new ZipException((Throwable) fileNotFoundException);
            throw th5;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th6 = th2;
            new ZipException((Throwable) exc);
            throw th6;
        }
    }

    private FileOutputStream getOutputStream(String str, String str2) throws ZipException {
        Throwable th;
        File file;
        FileOutputStream fileOutputStream;
        Throwable th2;
        String str3 = str;
        String str4 = str2;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str3)) {
            Throwable th3 = th2;
            new ZipException("invalid output path");
            throw th3;
        }
        try {
            new File(getOutputFileNameWithPath(str3, str4));
            File file2 = file;
            if (!file2.getParentFile().exists()) {
                boolean mkdirs = file2.getParentFile().mkdirs();
            }
            if (file2.exists()) {
                boolean delete = file2.delete();
            }
            FileOutputStream fileOutputStream2 = fileOutputStream;
            new FileOutputStream(file2);
            return fileOutputStream2;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            Throwable th4 = th;
            new ZipException((Throwable) fileNotFoundException);
            throw th4;
        }
    }

    private String getOutputFileNameWithPath(String str, String str2) throws ZipException {
        String fileName;
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str4)) {
            fileName = str4;
        } else {
            fileName = this.fileHeader.getFileName();
        }
        new StringBuilder();
        return sb.append(str3).append(System.getProperty("file.separator")).append(fileName).toString();
    }

    public RandomAccessFile startNextSplitFile() throws IOException, FileNotFoundException {
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        Throwable th;
        RandomAccessFile randomAccessFile;
        Throwable th2;
        StringBuilder sb4;
        String zipFile = this.zipModel.getZipFile();
        if (this.currSplitFileCounter == this.zipModel.getEndCentralDirRecord().getNoOfThisDisk()) {
            sb2 = this.zipModel.getZipFile();
        } else if (this.currSplitFileCounter >= 9) {
            new StringBuilder();
            sb2 = sb3.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z").append(this.currSplitFileCounter + 1).toString();
        } else {
            new StringBuilder();
            sb2 = sb.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z0").append(this.currSplitFileCounter + 1).toString();
        }
        this.currSplitFileCounter++;
        try {
            if (!Zip4jUtil.checkFileExists(sb2)) {
                Throwable th3 = th2;
                new StringBuilder();
                new IOException(sb4.append("zip split file does not exist: ").append(sb2).toString());
                throw th3;
            }
            new RandomAccessFile(sb2, InternalZipConstants.READ_MODE);
            return randomAccessFile;
        } catch (ZipException e) {
            ZipException zipException = e;
            Throwable th4 = th;
            new IOException(zipException.getMessage());
            throw th4;
        }
    }

    private void closeStreams(InputStream inputStream, OutputStream outputStream) throws ZipException {
        Throwable th;
        InputStream inputStream2 = inputStream;
        OutputStream outputStream2 = outputStream;
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e) {
                IOException iOException = e;
                if (iOException != null) {
                    if (Zip4jUtil.isStringNotNullAndNotEmpty(iOException.getMessage()) && iOException.getMessage().indexOf(" - Wrong Password?") >= 0) {
                        Throwable th2 = th;
                        new ZipException(iOException.getMessage());
                        throw th2;
                    }
                }
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                        return;
                    } catch (IOException e2) {
                        IOException iOException2 = e2;
                        return;
                    }
                } else {
                    return;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                    } catch (IOException e3) {
                        IOException iOException3 = e3;
                    }
                }
                throw th4;
            }
        }
        if (outputStream2 != null) {
            try {
                outputStream2.close();
            } catch (IOException e4) {
                IOException iOException4 = e4;
            }
        }
    }

    public void updateCRC(int i) {
        this.crc.update(i);
    }

    public void updateCRC(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (bArr2 != null) {
            this.crc.update(bArr2, i3, i4);
        }
    }

    public FileHeader getFileHeader() {
        return this.fileHeader;
    }

    public IDecrypter getDecrypter() {
        return this.decrypter;
    }

    public ZipModel getZipModel() {
        return this.zipModel;
    }

    public LocalFileHeader getLocalFileHeader() {
        return this.localFileHeader;
    }
}
