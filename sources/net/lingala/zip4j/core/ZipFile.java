package net.lingala.zip4j.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.unzip.Unzip;
import net.lingala.zip4j.util.ArchiveMaintainer;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;
import net.lingala.zip4j.zip.ZipEngine;

public class ZipFile {
    private String file;
    private String fileNameCharset;
    private boolean isEncrypted;
    private int mode;
    private ProgressMonitor progressMonitor;
    private boolean runInThread;
    private ZipModel zipModel;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ZipFile(java.lang.String r8) throws net.lingala.zip4j.exception.ZipException {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            java.io.File r3 = new java.io.File
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            r2.<init>((java.io.File) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.core.ZipFile.<init>(java.lang.String):void");
    }

    public ZipFile(File file2) throws ZipException {
        ProgressMonitor progressMonitor2;
        Throwable th;
        File file3 = file2;
        if (file3 == null) {
            Throwable th2 = th;
            new ZipException("Input zip file parameter is not null", 1);
            throw th2;
        }
        this.file = file3.getPath();
        this.mode = 2;
        new ProgressMonitor();
        this.progressMonitor = progressMonitor2;
        this.runInThread = false;
    }

    public void createZipFile(File file2, ZipParameters zipParameters) throws ZipException {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        boolean add = arrayList2.add(file2);
        createZipFile(arrayList2, zipParameters, false, -1);
    }

    public void createZipFile(File file2, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        boolean add = arrayList2.add(file2);
        createZipFile(arrayList2, zipParameters, z, j);
    }

    public void createZipFile(ArrayList arrayList, ZipParameters zipParameters) throws ZipException {
        createZipFile(arrayList, zipParameters, false, -1);
    }

    public void createZipFile(ArrayList arrayList, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        Throwable th4;
        ArrayList arrayList2 = arrayList;
        ZipParameters zipParameters2 = zipParameters;
        boolean z2 = z;
        long j2 = j;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(this.file)) {
            Throwable th5 = th4;
            new ZipException("zip file path is empty");
            throw th5;
        } else if (Zip4jUtil.checkFileExists(this.file)) {
            Throwable th6 = th3;
            new StringBuilder();
            new ZipException(sb.append("zip file: ").append(this.file).append(" already exists. To add files to existing zip file use addFile method").toString());
            throw th6;
        } else if (arrayList2 == null) {
            Throwable th7 = th2;
            new ZipException("input file ArrayList is null, cannot create zip file");
            throw th7;
        } else if (!Zip4jUtil.checkArrayListTypes(arrayList2, 1)) {
            Throwable th8 = th;
            new ZipException("One or more elements in the input ArrayList is not of type File");
            throw th8;
        } else {
            createNewZipModel();
            this.zipModel.setSplitArchive(z2);
            this.zipModel.setSplitLength(j2);
            addFiles(arrayList2, zipParameters2);
        }
    }

    public void createZipFileFromFolder(String str, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        File file2;
        Throwable th;
        String str2 = str;
        ZipParameters zipParameters2 = zipParameters;
        boolean z2 = z;
        long j2 = j;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("folderToAdd is empty or null, cannot create Zip File from folder");
            throw th2;
        }
        new File(str2);
        createZipFileFromFolder(file2, zipParameters2, z2, j2);
    }

    public void createZipFileFromFolder(File file2, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        File file3 = file2;
        ZipParameters zipParameters2 = zipParameters;
        boolean z2 = z;
        long j2 = j;
        if (file3 == null) {
            Throwable th4 = th3;
            new ZipException("folderToAdd is null, cannot create zip file from folder");
            throw th4;
        } else if (zipParameters2 == null) {
            Throwable th5 = th2;
            new ZipException("input parameters are null, cannot create zip file from folder");
            throw th5;
        } else if (Zip4jUtil.checkFileExists(this.file)) {
            Throwable th6 = th;
            new StringBuilder();
            new ZipException(sb.append("zip file: ").append(this.file).append(" already exists. To add files to existing zip file use addFolder method").toString());
            throw th6;
        } else {
            createNewZipModel();
            this.zipModel.setSplitArchive(z2);
            if (z2) {
                this.zipModel.setSplitLength(j2);
            }
            addFolder(file3, zipParameters2, false);
        }
    }

    public void addFile(File file2, ZipParameters zipParameters) throws ZipException {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        boolean add = arrayList2.add(file2);
        addFiles(arrayList2, zipParameters);
    }

    public void addFiles(ArrayList arrayList, ZipParameters zipParameters) throws ZipException {
        ZipEngine zipEngine;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        ArrayList arrayList2 = arrayList;
        ZipParameters zipParameters2 = zipParameters;
        checkZipModel();
        if (this.zipModel == null) {
            Throwable th7 = th6;
            new ZipException("internal error: zip model is null");
            throw th7;
        } else if (arrayList2 == null) {
            Throwable th8 = th5;
            new ZipException("input file ArrayList is null, cannot add files");
            throw th8;
        } else if (!Zip4jUtil.checkArrayListTypes(arrayList2, 1)) {
            Throwable th9 = th4;
            new ZipException("One or more elements in the input ArrayList is not of type File");
            throw th9;
        } else if (zipParameters2 == null) {
            Throwable th10 = th3;
            new ZipException("input parameters are null, cannot add files to zip");
            throw th10;
        } else if (this.progressMonitor.getState() == 1) {
            Throwable th11 = th2;
            new ZipException("invalid operation - Zip4j is in busy state");
            throw th11;
        } else if (!Zip4jUtil.checkFileExists(this.file) || !this.zipModel.isSplitArchive()) {
            new ZipEngine(this.zipModel);
            zipEngine.addFiles(arrayList2, zipParameters2, this.progressMonitor, this.runInThread);
        } else {
            Throwable th12 = th;
            new ZipException("Zip file already exists. Zip file format does not allow updating split/spanned files");
            throw th12;
        }
    }

    public void addFolder(String str, ZipParameters zipParameters) throws ZipException {
        File file2;
        Throwable th;
        String str2 = str;
        ZipParameters zipParameters2 = zipParameters;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("input path is null or empty, cannot add folder to zip file");
            throw th2;
        }
        new File(str2);
        addFolder(file2, zipParameters2);
    }

    public void addFolder(File file2, ZipParameters zipParameters) throws ZipException {
        Throwable th;
        Throwable th2;
        File file3 = file2;
        ZipParameters zipParameters2 = zipParameters;
        if (file3 == null) {
            Throwable th3 = th2;
            new ZipException("input path is null, cannot add folder to zip file");
            throw th3;
        } else if (zipParameters2 == null) {
            Throwable th4 = th;
            new ZipException("input parameters are null, cannot add folder to zip file");
            throw th4;
        } else {
            addFolder(file3, zipParameters2, true);
        }
    }

    private void addFolder(File file2, ZipParameters zipParameters, boolean z) throws ZipException {
        ZipEngine zipEngine;
        Throwable th;
        Throwable th2;
        File file3 = file2;
        ZipParameters zipParameters2 = zipParameters;
        boolean z2 = z;
        checkZipModel();
        if (this.zipModel == null) {
            Throwable th3 = th2;
            new ZipException("internal error: zip model is null");
            throw th3;
        } else if (!z2 || !this.zipModel.isSplitArchive()) {
            new ZipEngine(this.zipModel);
            zipEngine.addFolderToZip(file3, zipParameters2, this.progressMonitor, this.runInThread);
        } else {
            Throwable th4 = th;
            new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
            throw th4;
        }
    }

    public void addStream(InputStream inputStream, ZipParameters zipParameters) throws ZipException {
        ZipEngine zipEngine;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        InputStream inputStream2 = inputStream;
        ZipParameters zipParameters2 = zipParameters;
        if (inputStream2 == null) {
            Throwable th5 = th4;
            new ZipException("inputstream is null, cannot add file to zip");
            throw th5;
        } else if (zipParameters2 == null) {
            Throwable th6 = th3;
            new ZipException("zip parameters are null");
            throw th6;
        } else {
            setRunInThread(false);
            checkZipModel();
            if (this.zipModel == null) {
                Throwable th7 = th2;
                new ZipException("internal error: zip model is null");
                throw th7;
            } else if (!Zip4jUtil.checkFileExists(this.file) || !this.zipModel.isSplitArchive()) {
                new ZipEngine(this.zipModel);
                zipEngine.addStreamToZip(inputStream2, zipParameters2);
            } else {
                Throwable th8 = th;
                new ZipException("Zip file already exists. Zip file format does not allow updating split/spanned files");
                throw th8;
            }
        }
    }

    private void readZipInfo() throws ZipException {
        Throwable th;
        RandomAccessFile randomAccessFile;
        File file2;
        HeaderReader headerReader;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        if (!Zip4jUtil.checkFileExists(this.file)) {
            Throwable th5 = th4;
            new ZipException("zip file does not exist");
            throw th5;
        } else if (!Zip4jUtil.checkFileReadAccess(this.file)) {
            Throwable th6 = th3;
            new ZipException("no read access for the input zip file");
            throw th6;
        } else if (this.mode != 2) {
            Throwable th7 = th2;
            new ZipException("Invalid mode");
            throw th7;
        } else {
            try {
                new File(this.file);
                new RandomAccessFile(file2, InternalZipConstants.READ_MODE);
                RandomAccessFile randomAccessFile2 = randomAccessFile;
                if (this.zipModel == null) {
                    new HeaderReader(randomAccessFile2);
                    this.zipModel = headerReader.readAllHeaders(this.fileNameCharset);
                    if (this.zipModel != null) {
                        this.zipModel.setZipFile(this.file);
                    }
                }
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    }
                }
            } catch (FileNotFoundException e2) {
                FileNotFoundException fileNotFoundException = e2;
                Throwable th8 = th;
                new ZipException((Throwable) fileNotFoundException);
                throw th8;
            } catch (Throwable th9) {
                Throwable th10 = th9;
                if (0 != 0) {
                    RandomAccessFile randomAccessFile3 = null;
                    try {
                        randomAccessFile3.close();
                    } catch (IOException e3) {
                        IOException iOException2 = e3;
                    }
                }
                throw th10;
            }
        }
    }

    public void extractAll(String str) throws ZipException {
        extractAll(str, (UnzipParameters) null);
    }

    public void extractAll(String str, UnzipParameters unzipParameters) throws ZipException {
        Unzip unzip;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        String str2 = str;
        UnzipParameters unzipParameters2 = unzipParameters;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th5 = th4;
            new ZipException("output path is null or invalid");
            throw th5;
        } else if (!Zip4jUtil.checkOutputFolder(str2)) {
            Throwable th6 = th3;
            new ZipException("invalid output path");
            throw th6;
        } else {
            if (this.zipModel == null) {
                readZipInfo();
            }
            if (this.zipModel == null) {
                Throwable th7 = th2;
                new ZipException("Internal error occurred when extracting zip file");
                throw th7;
            } else if (this.progressMonitor.getState() == 1) {
                Throwable th8 = th;
                new ZipException("invalid operation - Zip4j is in busy state");
                throw th8;
            } else {
                new Unzip(this.zipModel);
                unzip.extractAll(unzipParameters2, str2, this.progressMonitor, this.runInThread);
            }
        }
    }

    public void extractFile(FileHeader fileHeader, String str) throws ZipException {
        extractFile(fileHeader, str, (UnzipParameters) null);
    }

    public void extractFile(FileHeader fileHeader, String str, UnzipParameters unzipParameters) throws ZipException {
        extractFile(fileHeader, str, unzipParameters, (String) null);
    }

    public void extractFile(FileHeader fileHeader, String str, UnzipParameters unzipParameters, String str2) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        FileHeader fileHeader2 = fileHeader;
        String str3 = str;
        UnzipParameters unzipParameters2 = unzipParameters;
        String str4 = str2;
        if (fileHeader2 == null) {
            Throwable th4 = th3;
            new ZipException("input file header is null, cannot extract file");
            throw th4;
        } else if (!Zip4jUtil.isStringNotNullAndNotEmpty(str3)) {
            Throwable th5 = th2;
            new ZipException("destination path is empty or null, cannot extract file");
            throw th5;
        } else {
            readZipInfo();
            if (this.progressMonitor.getState() == 1) {
                Throwable th6 = th;
                new ZipException("invalid operation - Zip4j is in busy state");
                throw th6;
            }
            fileHeader2.extractFile(this.zipModel, str3, unzipParameters2, str4, this.progressMonitor, this.runInThread);
        }
    }

    public void extractFile(String str, String str2) throws ZipException {
        extractFile(str, str2, (UnzipParameters) null);
    }

    public void extractFile(String str, String str2, UnzipParameters unzipParameters) throws ZipException {
        extractFile(str, str2, unzipParameters, (String) null);
    }

    public void extractFile(String str, String str2, UnzipParameters unzipParameters, String str3) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        String str4 = str;
        String str5 = str2;
        UnzipParameters unzipParameters2 = unzipParameters;
        String str6 = str3;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str4)) {
            Throwable th5 = th4;
            new ZipException("file to extract is null or empty, cannot extract file");
            throw th5;
        } else if (!Zip4jUtil.isStringNotNullAndNotEmpty(str5)) {
            Throwable th6 = th3;
            new ZipException("destination string path is empty or null, cannot extract file");
            throw th6;
        } else {
            readZipInfo();
            FileHeader fileHeader = Zip4jUtil.getFileHeader(this.zipModel, str4);
            if (fileHeader == null) {
                Throwable th7 = th2;
                new ZipException("file header not found for given file name, cannot extract file");
                throw th7;
            } else if (this.progressMonitor.getState() == 1) {
                Throwable th8 = th;
                new ZipException("invalid operation - Zip4j is in busy state");
                throw th8;
            } else {
                fileHeader.extractFile(this.zipModel, str5, unzipParameters2, str6, this.progressMonitor, this.runInThread);
            }
        }
    }

    public void setPassword(String str) throws ZipException {
        Throwable th;
        String str2 = str;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new NullPointerException();
            throw th2;
        }
        setPassword(str2.toCharArray());
    }

    public void setPassword(char[] cArr) throws ZipException {
        Throwable th;
        Throwable th2;
        char[] cArr2 = cArr;
        if (this.zipModel == null) {
            readZipInfo();
            if (this.zipModel == null) {
                Throwable th3 = th2;
                new ZipException("Zip Model is null");
                throw th3;
            }
        }
        if (this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null) {
            Throwable th4 = th;
            new ZipException("invalid zip file");
            throw th4;
        }
        for (int i = 0; i < this.zipModel.getCentralDirectory().getFileHeaders().size(); i++) {
            if (this.zipModel.getCentralDirectory().getFileHeaders().get(i) != null && ((FileHeader) this.zipModel.getCentralDirectory().getFileHeaders().get(i)).isEncrypted()) {
                ((FileHeader) this.zipModel.getCentralDirectory().getFileHeaders().get(i)).setPassword(cArr2);
            }
        }
    }

    public List getFileHeaders() throws ZipException {
        readZipInfo();
        if (this.zipModel == null || this.zipModel.getCentralDirectory() == null) {
            return null;
        }
        return this.zipModel.getCentralDirectory().getFileHeaders();
    }

    public FileHeader getFileHeader(String str) throws ZipException {
        Throwable th;
        String str2 = str;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th2 = th;
            new ZipException("input file name is emtpy or null, cannot get FileHeader");
            throw th2;
        }
        readZipInfo();
        if (this.zipModel == null || this.zipModel.getCentralDirectory() == null) {
            return null;
        }
        return Zip4jUtil.getFileHeader(this.zipModel, str2);
    }

    public boolean isEncrypted() throws ZipException {
        Throwable th;
        Throwable th2;
        if (this.zipModel == null) {
            readZipInfo();
            if (this.zipModel == null) {
                Throwable th3 = th2;
                new ZipException("Zip Model is null");
                throw th3;
            }
        }
        if (this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null) {
            Throwable th4 = th;
            new ZipException("invalid zip file");
            throw th4;
        }
        ArrayList fileHeaders = this.zipModel.getCentralDirectory().getFileHeaders();
        int i = 0;
        while (true) {
            if (i < fileHeaders.size()) {
                FileHeader fileHeader = (FileHeader) fileHeaders.get(i);
                if (fileHeader != null && fileHeader.isEncrypted()) {
                    this.isEncrypted = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return this.isEncrypted;
    }

    public boolean isSplitArchive() throws ZipException {
        Throwable th;
        if (this.zipModel == null) {
            readZipInfo();
            if (this.zipModel == null) {
                Throwable th2 = th;
                new ZipException("Zip Model is null");
                throw th2;
            }
        }
        return this.zipModel.isSplitArchive();
    }

    public void removeFile(String str) throws ZipException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        String str2 = str;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th4 = th3;
            new ZipException("file name is empty or null, cannot remove file");
            throw th4;
        }
        if (this.zipModel == null && Zip4jUtil.checkFileExists(this.file)) {
            readZipInfo();
        }
        if (this.zipModel.isSplitArchive()) {
            Throwable th5 = th2;
            new ZipException("Zip file format does not allow updating split/spanned files");
            throw th5;
        }
        FileHeader fileHeader = Zip4jUtil.getFileHeader(this.zipModel, str2);
        if (fileHeader == null) {
            Throwable th6 = th;
            new StringBuilder();
            new ZipException(sb.append("could not find file header for file: ").append(str2).toString());
            throw th6;
        }
        removeFile(fileHeader);
    }

    public void removeFile(FileHeader fileHeader) throws ZipException {
        ArchiveMaintainer archiveMaintainer;
        Throwable th;
        Throwable th2;
        FileHeader fileHeader2 = fileHeader;
        if (fileHeader2 == null) {
            Throwable th3 = th2;
            new ZipException("file header is null, cannot remove file");
            throw th3;
        }
        if (this.zipModel == null && Zip4jUtil.checkFileExists(this.file)) {
            readZipInfo();
        }
        if (this.zipModel.isSplitArchive()) {
            Throwable th4 = th;
            new ZipException("Zip file format does not allow updating split/spanned files");
            throw th4;
        }
        new ArchiveMaintainer();
        ArchiveMaintainer archiveMaintainer2 = archiveMaintainer;
        archiveMaintainer2.initProgressMonitorForRemoveOp(this.zipModel, fileHeader2, this.progressMonitor);
        HashMap removeZipFile = archiveMaintainer2.removeZipFile(this.zipModel, fileHeader2, this.progressMonitor, this.runInThread);
    }

    public void mergeSplitFiles(File file2) throws ZipException {
        ArchiveMaintainer archiveMaintainer;
        Throwable th;
        Throwable th2;
        Throwable th3;
        File file3 = file2;
        if (file3 == null) {
            Throwable th4 = th3;
            new ZipException("outputZipFile is null, cannot merge split files");
            throw th4;
        } else if (file3.exists()) {
            Throwable th5 = th2;
            new ZipException("output Zip File already exists");
            throw th5;
        } else {
            checkZipModel();
            if (this.zipModel == null) {
                Throwable th6 = th;
                new ZipException("zip model is null, corrupt zip file?");
                throw th6;
            }
            new ArchiveMaintainer();
            ArchiveMaintainer archiveMaintainer2 = archiveMaintainer;
            archiveMaintainer2.initProgressMonitorForMergeOp(this.zipModel, this.progressMonitor);
            archiveMaintainer2.mergeSplitZipFiles(this.zipModel, file3, this.progressMonitor, this.runInThread);
        }
    }

    public void setComment(String str) throws ZipException {
        ArchiveMaintainer archiveMaintainer;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        String str2 = str;
        if (str2 == null) {
            Throwable th5 = th4;
            new ZipException("input comment is null, cannot update zip file");
            throw th5;
        } else if (!Zip4jUtil.checkFileExists(this.file)) {
            Throwable th6 = th3;
            new ZipException("zip file does not exist, cannot set comment for zip file");
            throw th6;
        } else {
            readZipInfo();
            if (this.zipModel == null) {
                Throwable th7 = th2;
                new ZipException("zipModel is null, cannot update zip file");
                throw th7;
            } else if (this.zipModel.getEndCentralDirRecord() == null) {
                Throwable th8 = th;
                new ZipException("end of central directory is null, cannot set comment");
                throw th8;
            } else {
                new ArchiveMaintainer();
                archiveMaintainer.setComment(this.zipModel, str2);
            }
        }
    }

    public String getComment() throws ZipException {
        return getComment((String) null);
    }

    public String getComment(String str) throws ZipException {
        Throwable th;
        Throwable th2;
        String str2;
        Throwable th3;
        Throwable th4;
        String str3 = str;
        if (str3 == null) {
            str3 = Zip4jUtil.isSupportedCharset(InternalZipConstants.CHARSET_COMMENTS_DEFAULT) ? InternalZipConstants.CHARSET_COMMENTS_DEFAULT : InternalZipConstants.CHARSET_DEFAULT;
        }
        if (Zip4jUtil.checkFileExists(this.file)) {
            checkZipModel();
            if (this.zipModel == null) {
                Throwable th5 = th4;
                new ZipException("zip model is null, cannot read comment");
                throw th5;
            } else if (this.zipModel.getEndCentralDirRecord() == null) {
                Throwable th6 = th3;
                new ZipException("end of central directory record is null, cannot read comment");
                throw th6;
            } else if (this.zipModel.getEndCentralDirRecord().getCommentBytes() == null || this.zipModel.getEndCentralDirRecord().getCommentBytes().length <= 0) {
                return null;
            } else {
                try {
                    String str4 = str2;
                    new String(this.zipModel.getEndCentralDirRecord().getCommentBytes(), str3);
                    return str4;
                } catch (UnsupportedEncodingException e) {
                    UnsupportedEncodingException unsupportedEncodingException = e;
                    Throwable th7 = th2;
                    new ZipException((Throwable) unsupportedEncodingException);
                    throw th7;
                }
            }
        } else {
            Throwable th8 = th;
            new ZipException("zip file does not exist, cannot read comment");
            throw th8;
        }
    }

    private void checkZipModel() throws ZipException {
        if (this.zipModel != null) {
            return;
        }
        if (Zip4jUtil.checkFileExists(this.file)) {
            readZipInfo();
        } else {
            createNewZipModel();
        }
    }

    private void createNewZipModel() {
        ZipModel zipModel2;
        new ZipModel();
        this.zipModel = zipModel2;
        this.zipModel.setZipFile(this.file);
        this.zipModel.setFileNameCharset(this.fileNameCharset);
    }

    public void setFileNameCharset(String str) throws ZipException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        String str2 = str;
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            Throwable th3 = th2;
            new ZipException("null or empty charset name");
            throw th3;
        } else if (!Zip4jUtil.isSupportedCharset(str2)) {
            Throwable th4 = th;
            new StringBuilder();
            new ZipException(sb.append("unsupported charset: ").append(str2).toString());
            throw th4;
        } else {
            this.fileNameCharset = str2;
        }
    }

    public ZipInputStream getInputStream(FileHeader fileHeader) throws ZipException {
        Unzip unzip;
        Throwable th;
        Throwable th2;
        FileHeader fileHeader2 = fileHeader;
        if (fileHeader2 == null) {
            Throwable th3 = th2;
            new ZipException("FileHeader is null, cannot get InputStream");
            throw th3;
        }
        checkZipModel();
        if (this.zipModel == null) {
            Throwable th4 = th;
            new ZipException("zip model is null, cannot get inputstream");
            throw th4;
        }
        new Unzip(this.zipModel);
        return unzip.getInputStream(fileHeader2);
    }

    public boolean isValidZipFile() {
        try {
            readZipInfo();
            return true;
        } catch (Exception e) {
            Exception exc = e;
            return false;
        }
    }

    public ArrayList getSplitZipFiles() throws ZipException {
        checkZipModel();
        return Zip4jUtil.getSplitZipFiles(this.zipModel);
    }

    public ProgressMonitor getProgressMonitor() {
        return this.progressMonitor;
    }

    public boolean isRunInThread() {
        return this.runInThread;
    }

    public void setRunInThread(boolean z) {
        boolean z2 = z;
        this.runInThread = z2;
    }

    public File getFile() {
        File file2;
        new File(this.file);
        return file2;
    }
}
