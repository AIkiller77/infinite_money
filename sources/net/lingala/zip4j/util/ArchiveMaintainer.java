package net.lingala.zip4j.util;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import net.lingala.zip4j.core.HeaderReader;
import net.lingala.zip4j.core.HeaderWriter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.SplitOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;

public class ArchiveMaintainer {
    public ArchiveMaintainer() {
    }

    public HashMap removeZipFile(ZipModel zipModel, FileHeader fileHeader, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Thread thread;
        ZipModel zipModel2 = zipModel;
        FileHeader fileHeader2 = fileHeader;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (z) {
            final ZipModel zipModel3 = zipModel2;
            final FileHeader fileHeader3 = fileHeader2;
            final ProgressMonitor progressMonitor3 = progressMonitor2;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ ArchiveMaintainer this$0;

                {
                    this.this$0 = r9;
                }

                public void run() {
                    try {
                        HashMap initRemoveZipFile = this.this$0.initRemoveZipFile(zipModel3, fileHeader3, progressMonitor3);
                        progressMonitor3.endProgressMonitorSuccess();
                    } catch (ZipException e) {
                        ZipException zipException = e;
                    }
                }
            };
            thread.start();
            return null;
        }
        HashMap initRemoveZipFile = initRemoveZipFile(zipModel2, fileHeader2, progressMonitor2);
        progressMonitor2.endProgressMonitorSuccess();
        return initRemoveZipFile;
    }

    public HashMap initRemoveZipFile(ZipModel zipModel, FileHeader fileHeader, ProgressMonitor progressMonitor) throws ZipException {
        Throwable th;
        HashMap hashMap;
        Throwable th2;
        File file;
        Throwable th3;
        StringBuilder sb;
        File file2;
        Throwable th4;
        OutputStream outputStream;
        File file3;
        File file4;
        HeaderReader headerReader;
        Throwable th5;
        HeaderWriter headerWriter;
        File file5;
        Throwable th6;
        File file6;
        Throwable th7;
        Throwable th8;
        StringBuilder sb2;
        File file7;
        Throwable th9;
        Throwable th10;
        ZipModel zipModel2 = zipModel;
        FileHeader fileHeader2 = fileHeader;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (fileHeader2 == null || zipModel2 == null) {
            Throwable th11 = th;
            new ZipException("input parameters is null in maintain zip file, cannot remove file from archive");
            throw th11;
        }
        OutputStream outputStream2 = null;
        File file8 = null;
        RandomAccessFile randomAccessFile = null;
        String str = null;
        new HashMap();
        HashMap hashMap2 = hashMap;
        try {
            int indexOfFileHeader = Zip4jUtil.getIndexOfFileHeader(zipModel2, fileHeader2);
            if (indexOfFileHeader < 0) {
                Throwable th12 = th10;
                new ZipException("file header not found in zip model, cannot remove file");
                throw th12;
            } else if (zipModel2.isSplitArchive()) {
                Throwable th13 = th9;
                new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
                throw th13;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                new StringBuilder();
                str = sb.append(zipModel2.getZipFile()).append(currentTimeMillis % 1000).toString();
                File file9 = file2;
                new File(str);
                while (file9.exists()) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    new StringBuilder();
                    str = sb2.append(zipModel2.getZipFile()).append(currentTimeMillis2 % 1000).toString();
                    file9 = file7;
                    new File(str);
                }
                OutputStream outputStream3 = outputStream;
                new File(str);
                new SplitOutputStream(file3);
                outputStream2 = outputStream3;
                new File(zipModel2.getZipFile());
                file8 = file4;
                randomAccessFile = createFileHandler(zipModel2, InternalZipConstants.READ_MODE);
                new HeaderReader(randomAccessFile);
                if (headerReader.readLocalFileHeader(fileHeader2) == null) {
                    Throwable th14 = th8;
                    new ZipException("invalid local file header, cannot remove file from archive");
                    throw th14;
                }
                long offsetLocalHeader = fileHeader2.getOffsetLocalHeader();
                if (!(fileHeader2.getZip64ExtendedInfo() == null || fileHeader2.getZip64ExtendedInfo().getOffsetLocalHeader() == -1)) {
                    offsetLocalHeader = fileHeader2.getZip64ExtendedInfo().getOffsetLocalHeader();
                }
                long j = -1;
                long offsetOfStartOfCentralDir = zipModel2.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
                if (zipModel2.isZip64Format() && zipModel2.getZip64EndCentralDirRecord() != null) {
                    offsetOfStartOfCentralDir = zipModel2.getZip64EndCentralDirRecord().getOffsetStartCenDirWRTStartDiskNo();
                }
                ArrayList fileHeaders = zipModel2.getCentralDirectory().getFileHeaders();
                if (indexOfFileHeader == fileHeaders.size() - 1) {
                    j = offsetOfStartOfCentralDir - 1;
                } else {
                    FileHeader fileHeader3 = (FileHeader) fileHeaders.get(indexOfFileHeader + 1);
                    if (fileHeader3 != null) {
                        j = fileHeader3.getOffsetLocalHeader() - 1;
                        if (!(fileHeader3.getZip64ExtendedInfo() == null || fileHeader3.getZip64ExtendedInfo().getOffsetLocalHeader() == -1)) {
                            j = fileHeader3.getZip64ExtendedInfo().getOffsetLocalHeader() - 1;
                        }
                    }
                }
                if (offsetLocalHeader < 0 || j < 0) {
                    Throwable th15 = th5;
                    new ZipException("invalid offset for start and end of local file, cannot remove file");
                    throw th15;
                }
                if (indexOfFileHeader != 0) {
                    if (indexOfFileHeader == fileHeaders.size() - 1) {
                        copyFile(randomAccessFile, outputStream2, 0, offsetLocalHeader, progressMonitor2);
                    } else {
                        copyFile(randomAccessFile, outputStream2, 0, offsetLocalHeader, progressMonitor2);
                        copyFile(randomAccessFile, outputStream2, j + 1, offsetOfStartOfCentralDir, progressMonitor2);
                    }
                } else if (zipModel2.getCentralDirectory().getFileHeaders().size() > 1) {
                    copyFile(randomAccessFile, outputStream2, j + 1, offsetOfStartOfCentralDir, progressMonitor2);
                }
                if (progressMonitor2.isCancelAllTasks()) {
                    progressMonitor2.setResult(3);
                    progressMonitor2.setState(0);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e) {
                            IOException iOException = e;
                            Throwable th16 = th7;
                            new ZipException("cannot close input stream or output stream when trying to delete a file from zip file");
                            throw th16;
                        }
                    }
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (0 != 0) {
                        restoreFileName(file8, str);
                    } else {
                        new File(str);
                        boolean delete = file6.delete();
                    }
                    return null;
                }
                zipModel2.getEndCentralDirRecord().setOffsetOfStartOfCentralDir(((SplitOutputStream) outputStream2).getFilePointer());
                zipModel2.getEndCentralDirRecord().setTotNoOfEntriesInCentralDir(zipModel2.getEndCentralDirRecord().getTotNoOfEntriesInCentralDir() - 1);
                zipModel2.getEndCentralDirRecord().setTotNoOfEntriesInCentralDirOnThisDisk(zipModel2.getEndCentralDirRecord().getTotNoOfEntriesInCentralDirOnThisDisk() - 1);
                Object remove = zipModel2.getCentralDirectory().getFileHeaders().remove(indexOfFileHeader);
                for (int i = indexOfFileHeader; i < zipModel2.getCentralDirectory().getFileHeaders().size(); i++) {
                    long offsetLocalHeader2 = ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i)).getOffsetLocalHeader();
                    if (!(((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i)).getZip64ExtendedInfo() == null || ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i)).getZip64ExtendedInfo().getOffsetLocalHeader() == -1)) {
                        offsetLocalHeader2 = ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i)).getZip64ExtendedInfo().getOffsetLocalHeader();
                    }
                    ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i)).setOffsetLocalHeader((offsetLocalHeader2 - (j - offsetLocalHeader)) - 1);
                }
                new HeaderWriter();
                headerWriter.finalizeZipFile(zipModel2, outputStream2);
                Object put = hashMap2.put(InternalZipConstants.OFFSET_CENTRAL_DIR, Long.toString(zipModel2.getEndCentralDirRecord().getOffsetOfStartOfCentralDir()));
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                        IOException iOException2 = e2;
                        Throwable th17 = th6;
                        new ZipException("cannot close input stream or output stream when trying to delete a file from zip file");
                        throw th17;
                    }
                }
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (1 != 0) {
                    restoreFileName(file8, str);
                } else {
                    new File(str);
                    boolean delete2 = file5.delete();
                }
                return hashMap2;
            }
        } catch (FileNotFoundException e3) {
            FileNotFoundException fileNotFoundException = e3;
            Throwable th18 = th4;
            new ZipException((Throwable) fileNotFoundException);
            throw th18;
        } catch (ZipException e4) {
            ZipException zipException = e4;
            progressMonitor2.endProgressMonitorError(zipException);
            throw zipException;
        } catch (Exception e5) {
            Exception exc = e5;
            progressMonitor2.endProgressMonitorError(exc);
            Throwable th19 = th2;
            new ZipException((Throwable) exc);
            throw th19;
        } catch (Throwable th20) {
            Throwable th21 = th20;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e6) {
                    IOException iOException3 = e6;
                    Throwable th22 = th3;
                    new ZipException("cannot close input stream or output stream when trying to delete a file from zip file");
                    throw th22;
                }
            }
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (0 != 0) {
                restoreFileName(file8, str);
            } else {
                new File(str);
                boolean delete3 = file.delete();
            }
            throw th21;
        }
    }

    private void restoreFileName(File file, String str) throws ZipException {
        Throwable th;
        File file2;
        Throwable th2;
        File file3 = file;
        String str2 = str;
        if (file3.delete()) {
            new File(str2);
            if (!file2.renameTo(file3)) {
                Throwable th3 = th2;
                new ZipException("cannot rename modified zip file");
                throw th3;
            }
            return;
        }
        Throwable th4 = th;
        new ZipException("cannot delete old zip file");
        throw th4;
    }

    private void copyFile(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        byte[] bArr;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        RandomAccessFile randomAccessFile2 = randomAccessFile;
        OutputStream outputStream2 = outputStream;
        long j3 = j;
        long j4 = j2;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (randomAccessFile2 == null || outputStream2 == null) {
            Throwable th7 = th;
            new ZipException("input or output stream is null, cannot copy file");
            throw th7;
        } else if (j3 < 0) {
            Throwable th8 = th6;
            new ZipException("starting offset is negative, cannot copy file");
            throw th8;
        } else if (j4 < 0) {
            Throwable th9 = th5;
            new ZipException("end offset is negative, cannot copy file");
            throw th9;
        } else if (j3 > j4) {
            Throwable th10 = th4;
            new ZipException("start offset is greater than end offset, cannot copy file");
            throw th10;
        } else if (j3 != j4) {
            if (progressMonitor2.isCancelAllTasks()) {
                progressMonitor2.setResult(3);
                progressMonitor2.setState(0);
                return;
            }
            try {
                randomAccessFile2.seek(j3);
                long j5 = 0;
                long j6 = j4 - j3;
                if (j4 - j3 < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
                    bArr = new byte[((int) (j4 - j3))];
                } else {
                    bArr = new byte[4096];
                }
                while (true) {
                    int read = randomAccessFile2.read(bArr);
                    int i = read;
                    if (read == -1) {
                        break;
                    }
                    outputStream2.write(bArr, 0, i);
                    progressMonitor2.updateWorkCompleted((long) i);
                    if (progressMonitor2.isCancelAllTasks()) {
                        progressMonitor2.setResult(3);
                        return;
                    }
                    j5 += (long) i;
                    if (j5 == j6) {
                        break;
                    } else if (j5 + ((long) bArr.length) > j6) {
                        bArr = new byte[((int) (j6 - j5))];
                    }
                }
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th11 = th3;
                new ZipException((Throwable) iOException);
                throw th11;
            } catch (Exception e2) {
                Exception exc = e2;
                Throwable th12 = th2;
                new ZipException((Throwable) exc);
                throw th12;
            }
        }
    }

    private RandomAccessFile createFileHandler(ZipModel zipModel, String str) throws ZipException {
        Throwable th;
        Throwable th2;
        RandomAccessFile randomAccessFile;
        File file;
        ZipModel zipModel2 = zipModel;
        String str2 = str;
        if (zipModel2 == null || !Zip4jUtil.isStringNotNullAndNotEmpty(zipModel2.getZipFile())) {
            Throwable th3 = th;
            new ZipException("input parameter is null in getFilePointer, cannot create file handler to remove file");
            throw th3;
        }
        try {
            RandomAccessFile randomAccessFile2 = randomAccessFile;
            new File(zipModel2.getZipFile());
            new RandomAccessFile(file, str2);
            return randomAccessFile2;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            Throwable th4 = th2;
            new ZipException((Throwable) fileNotFoundException);
            throw th4;
        }
    }

    public void mergeSplitZipFiles(ZipModel zipModel, File file, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Thread thread;
        ZipModel zipModel2 = zipModel;
        File file2 = file;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (z) {
            final ZipModel zipModel3 = zipModel2;
            final File file3 = file2;
            final ProgressMonitor progressMonitor3 = progressMonitor2;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ ArchiveMaintainer this$0;

                {
                    this.this$0 = r9;
                }

                public void run() {
                    try {
                        this.this$0.initMergeSplitZipFile(zipModel3, file3, progressMonitor3);
                    } catch (ZipException e) {
                        ZipException zipException = e;
                    }
                }
            };
            thread.start();
            return;
        }
        initMergeSplitZipFile(zipModel2, file2, progressMonitor2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0233, code lost:
        r20 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0234, code lost:
        r13 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r6.endProgressMonitorError(r13);
        r20 = r28;
        new net.lingala.zip4j.exception.ZipException((java.lang.Throwable) r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x024a, code lost:
        throw r20;
     */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0233 A[ExcHandler: Exception (r20v10 'e' java.lang.Exception A[CUSTOM_DECLARE]), PHI: r7 r8 
      PHI: (r7v1 java.io.OutputStream) = (r7v0 java.io.OutputStream), (r7v0 java.io.OutputStream), (r7v4 java.io.OutputStream), (r7v4 java.io.OutputStream), (r7v4 java.io.OutputStream), (r7v4 java.io.OutputStream), (r7v4 java.io.OutputStream), (r7v4 java.io.OutputStream) binds: [B:9:0x0065, B:33:0x00b5, B:78:0x01df, B:79:?, B:69:0x01cc, B:70:?, B:72:0x01d2, B:73:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r8v1 java.io.RandomAccessFile) = (r8v0 java.io.RandomAccessFile), (r8v0 java.io.RandomAccessFile), (r8v4 java.io.RandomAccessFile), (r8v4 java.io.RandomAccessFile), (r8v5 java.io.RandomAccessFile), (r8v5 java.io.RandomAccessFile), (r8v5 java.io.RandomAccessFile), (r8v5 java.io.RandomAccessFile) binds: [B:9:0x0065, B:33:0x00b5, B:78:0x01df, B:79:?, B:69:0x01cc, B:70:?, B:72:0x01d2, B:73:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0065] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initMergeSplitZipFile(net.lingala.zip4j.model.ZipModel r30, java.io.File r31, net.lingala.zip4j.progress.ProgressMonitor r32) throws net.lingala.zip4j.exception.ZipException {
        /*
            r29 = this;
            r3 = r29
            r4 = r30
            r5 = r31
            r6 = r32
            r20 = r4
            if (r20 != 0) goto L_0x0025
            net.lingala.zip4j.exception.ZipException r20 = new net.lingala.zip4j.exception.ZipException
            r28 = r20
            r20 = r28
            r21 = r28
            java.lang.String r22 = "one of the input parameters is null, cannot merge split zip file"
            r21.<init>((java.lang.String) r22)
            r7 = r20
            r20 = r6
            r21 = r7
            r20.endProgressMonitorError(r21)
            r20 = r7
            throw r20
        L_0x0025:
            r20 = r4
            boolean r20 = r20.isSplitArchive()
            if (r20 != 0) goto L_0x0046
            net.lingala.zip4j.exception.ZipException r20 = new net.lingala.zip4j.exception.ZipException
            r28 = r20
            r20 = r28
            r21 = r28
            java.lang.String r22 = "archive not a split zip file"
            r21.<init>((java.lang.String) r22)
            r7 = r20
            r20 = r6
            r21 = r7
            r20.endProgressMonitorError(r21)
            r20 = r7
            throw r20
        L_0x0046:
            r20 = 0
            r7 = r20
            r20 = 0
            r8 = r20
            java.util.ArrayList r20 = new java.util.ArrayList
            r28 = r20
            r20 = r28
            r21 = r28
            r21.<init>()
            r9 = r20
            r20 = 0
            r10 = r20
            r20 = 0
            r12 = r20
            r20 = r4
            net.lingala.zip4j.model.EndCentralDirRecord r20 = r20.getEndCentralDirRecord()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            int r20 = r20.getNoOfThisDisk()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r13 = r20
            r20 = r13
            if (r20 > 0) goto L_0x00b1
            net.lingala.zip4j.exception.ZipException r20 = new net.lingala.zip4j.exception.ZipException     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r28 = r20
            r20 = r28
            r21 = r28
            java.lang.String r22 = "corrupt zip model, archive not a split zip file"
            r21.<init>((java.lang.String) r22)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            throw r20     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
        L_0x0081:
            r20 = move-exception
            r13 = r20
            r20 = r6
            r21 = r13
            r20.endProgressMonitorError(r21)     // Catch:{ all -> 0x0099 }
            net.lingala.zip4j.exception.ZipException r20 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0099 }
            r28 = r20
            r20 = r28
            r21 = r28
            r22 = r13
            r21.<init>((java.lang.Throwable) r22)     // Catch:{ all -> 0x0099 }
            throw r20     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r20 = move-exception
            r18 = r20
            r20 = r7
            if (r20 == 0) goto L_0x00a5
            r20 = r7
            r20.close()     // Catch:{ IOException -> 0x024b }
        L_0x00a5:
            r20 = r8
            if (r20 == 0) goto L_0x00ae
            r20 = r8
            r20.close()     // Catch:{ IOException -> 0x0250 }
        L_0x00ae:
            r20 = r18
            throw r20
        L_0x00b1:
            r20 = r3
            r21 = r5
            java.io.OutputStream r20 = r20.prepareOutputStreamForMerge(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r7 = r20
            r20 = 0
            r14 = r20
        L_0x00bf:
            r20 = r14
            r21 = r13
            r0 = r20
            r1 = r21
            if (r0 > r1) goto L_0x01dd
            r20 = r3
            r21 = r4
            r22 = r14
            java.io.RandomAccessFile r20 = r20.createSplitZipFileHandler(r21, r22)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r8 = r20
            r20 = 0
            r15 = r20
            java.lang.Long r20 = new java.lang.Long     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r28 = r20
            r20 = r28
            r21 = r28
            r22 = r8
            long r22 = r22.length()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r21.<init>(r22)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r16 = r20
            r20 = r14
            if (r20 != 0) goto L_0x0149
            r20 = r4
            net.lingala.zip4j.model.CentralDirectory r20 = r20.getCentralDirectory()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            if (r20 == 0) goto L_0x0149
            r20 = r4
            net.lingala.zip4j.model.CentralDirectory r20 = r20.getCentralDirectory()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            java.util.ArrayList r20 = r20.getFileHeaders()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            if (r20 == 0) goto L_0x0149
            r20 = r4
            net.lingala.zip4j.model.CentralDirectory r20 = r20.getCentralDirectory()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            java.util.ArrayList r20 = r20.getFileHeaders()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            int r20 = r20.size()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            if (r20 <= 0) goto L_0x0149
            r20 = 4
            r0 = r20
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r0
            r17 = r20
            r20 = r8
            r21 = 0
            r20.seek(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r8
            r21 = r17
            int r20 = r20.read(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r17
            r21 = 0
            int r20 = net.lingala.zip4j.util.Raw.readIntLittleEndian(r20, r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r0 = r20
            long r0 = (long) r0     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r0
            r22 = 134695760(0x8074b50, double:6.65485477E-316)
            int r20 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r20 != 0) goto L_0x0149
            r20 = 4
            r15 = r20
            r20 = 1
            r12 = r20
        L_0x0149:
            r20 = r14
            r21 = r13
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x016a
            java.lang.Long r20 = new java.lang.Long     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r28 = r20
            r20 = r28
            r21 = r28
            r22 = r4
            net.lingala.zip4j.model.EndCentralDirRecord r22 = r22.getEndCentralDirRecord()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            long r22 = r22.getOffsetOfStartOfCentralDir()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r21.<init>(r22)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r16 = r20
        L_0x016a:
            r20 = r3
            r21 = r8
            r22 = r7
            r23 = r15
            r0 = r23
            long r0 = (long) r0     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r23 = r0
            r25 = r16
            long r25 = r25.longValue()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r27 = r6
            r20.copyFile(r21, r22, r23, r25, r27)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r10
            r22 = r16
            long r22 = r22.longValue()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r24 = r15
            r0 = r24
            long r0 = (long) r0     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r24 = r0
            long r22 = r22 - r24
            long r20 = r20 + r22
            r10 = r20
            r20 = r6
            boolean r20 = r20.isCancelAllTasks()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            if (r20 == 0) goto L_0x01c8
            r20 = r6
            r21 = 3
            r20.setResult(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r6
            r21 = 0
            r20.setState(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r7
            if (r20 == 0) goto L_0x01b6
            r20 = r7
            r20.close()     // Catch:{ IOException -> 0x01c0 }
        L_0x01b6:
            r20 = r8
            if (r20 == 0) goto L_0x01bf
            r20 = r8
            r20.close()     // Catch:{ IOException -> 0x01c4 }
        L_0x01bf:
            return
        L_0x01c0:
            r20 = move-exception
            r17 = r20
            goto L_0x01b6
        L_0x01c4:
            r20 = move-exception
            r17 = r20
            goto L_0x01bf
        L_0x01c8:
            r20 = r9
            r21 = r16
            boolean r20 = r20.add(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r8
            r20.close()     // Catch:{ IOException -> 0x01d9, Exception -> 0x0233 }
        L_0x01d5:
            int r14 = r14 + 1
            goto L_0x00bf
        L_0x01d9:
            r20 = move-exception
            r17 = r20
            goto L_0x01d5
        L_0x01dd:
            r20 = r4
            java.lang.Object r20 = r20.clone()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            net.lingala.zip4j.model.ZipModel r20 = (net.lingala.zip4j.model.ZipModel) r20     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r14 = r20
            r20 = r14
            net.lingala.zip4j.model.EndCentralDirRecord r20 = r20.getEndCentralDirRecord()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r21 = r10
            r20.setOffsetOfStartOfCentralDir(r21)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r3
            r21 = r14
            r22 = r9
            r23 = r12
            r20.updateSplitZipModel(r21, r22, r23)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            net.lingala.zip4j.core.HeaderWriter r20 = new net.lingala.zip4j.core.HeaderWriter     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r28 = r20
            r20 = r28
            r21 = r28
            r21.<init>()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r15 = r20
            r20 = r15
            r21 = r14
            r22 = r7
            r20.finalizeZipFileWithoutValidations(r21, r22)     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r6
            r20.endProgressMonitorSuccess()     // Catch:{ IOException -> 0x0081, Exception -> 0x0233 }
            r20 = r7
            if (r20 == 0) goto L_0x0221
            r20 = r7
            r20.close()     // Catch:{ IOException -> 0x022b }
        L_0x0221:
            r20 = r8
            if (r20 == 0) goto L_0x022a
            r20 = r8
            r20.close()     // Catch:{ IOException -> 0x022f }
        L_0x022a:
            goto L_0x01bf
        L_0x022b:
            r20 = move-exception
            r13 = r20
            goto L_0x0221
        L_0x022f:
            r20 = move-exception
            r13 = r20
            goto L_0x022a
        L_0x0233:
            r20 = move-exception
            r13 = r20
            r20 = r6
            r21 = r13
            r20.endProgressMonitorError(r21)     // Catch:{ all -> 0x0099 }
            net.lingala.zip4j.exception.ZipException r20 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0099 }
            r28 = r20
            r20 = r28
            r21 = r28
            r22 = r13
            r21.<init>((java.lang.Throwable) r22)     // Catch:{ all -> 0x0099 }
            throw r20     // Catch:{ all -> 0x0099 }
        L_0x024b:
            r20 = move-exception
            r19 = r20
            goto L_0x00a5
        L_0x0250:
            r20 = move-exception
            r19 = r20
            goto L_0x00ae
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.ArchiveMaintainer.initMergeSplitZipFile(net.lingala.zip4j.model.ZipModel, java.io.File, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    private RandomAccessFile createSplitZipFileHandler(ZipModel zipModel, int i) throws ZipException {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        File file;
        RandomAccessFile randomAccessFile;
        Throwable th3;
        StringBuilder sb4;
        Throwable th4;
        Throwable th5;
        ZipModel zipModel2 = zipModel;
        int i2 = i;
        if (zipModel2 == null) {
            Throwable th6 = th5;
            new ZipException("zip model is null, cannot create split file handler");
            throw th6;
        } else if (i2 < 0) {
            Throwable th7 = th4;
            new ZipException("invlaid part number, cannot create split file handler");
            throw th7;
        } else {
            try {
                String zipFile = zipModel2.getZipFile();
                if (i2 == zipModel2.getEndCentralDirRecord().getNoOfThisDisk()) {
                    sb2 = zipModel2.getZipFile();
                } else if (i2 >= 9) {
                    new StringBuilder();
                    sb2 = sb3.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z").append(i2 + 1).toString();
                } else {
                    new StringBuilder();
                    sb2 = sb.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z0").append(i2 + 1).toString();
                }
                new File(sb2);
                File file2 = file;
                if (!Zip4jUtil.checkFileExists(file2)) {
                    Throwable th8 = th3;
                    new StringBuilder();
                    new ZipException(sb4.append("split file does not exist: ").append(sb2).toString());
                    throw th8;
                }
                RandomAccessFile randomAccessFile2 = randomAccessFile;
                new RandomAccessFile(file2, InternalZipConstants.READ_MODE);
                return randomAccessFile2;
            } catch (FileNotFoundException e) {
                FileNotFoundException fileNotFoundException = e;
                Throwable th9 = th2;
                new ZipException((Throwable) fileNotFoundException);
                throw th9;
            } catch (Exception e2) {
                Exception exc = e2;
                Throwable th10 = th;
                new ZipException((Throwable) exc);
                throw th10;
            }
        }
    }

    private OutputStream prepareOutputStreamForMerge(File file) throws ZipException {
        Throwable th;
        Throwable th2;
        OutputStream outputStream;
        Throwable th3;
        File file2 = file;
        if (file2 == null) {
            Throwable th4 = th3;
            new ZipException("outFile is null, cannot create outputstream");
            throw th4;
        }
        try {
            OutputStream outputStream2 = outputStream;
            new FileOutputStream(file2);
            return outputStream2;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            Throwable th5 = th2;
            new ZipException((Throwable) fileNotFoundException);
            throw th5;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th6 = th;
            new ZipException((Throwable) exc);
            throw th6;
        }
    }

    private void updateSplitZipModel(ZipModel zipModel, ArrayList arrayList, boolean z) throws ZipException {
        Throwable th;
        ZipModel zipModel2 = zipModel;
        ArrayList arrayList2 = arrayList;
        boolean z2 = z;
        if (zipModel2 == null) {
            Throwable th2 = th;
            new ZipException("zip model is null, cannot update split zip model");
            throw th2;
        }
        zipModel2.setSplitArchive(false);
        updateSplitFileHeader(zipModel2, arrayList2, z2);
        updateSplitEndCentralDirectory(zipModel2);
        if (zipModel2.isZip64Format()) {
            updateSplitZip64EndCentralDirLocator(zipModel2, arrayList2);
            updateSplitZip64EndCentralDirRec(zipModel2, arrayList2);
        }
    }

    private void updateSplitFileHeader(ZipModel zipModel, ArrayList arrayList, boolean z) throws ZipException {
        Throwable th;
        Throwable th2;
        ZipModel zipModel2 = zipModel;
        ArrayList arrayList2 = arrayList;
        boolean z2 = z;
        try {
            if (zipModel2.getCentralDirectory() == null) {
                Throwable th3 = th2;
                new ZipException("corrupt zip model - getCentralDirectory, cannot update split zip model");
                throw th3;
            }
            int size = zipModel2.getCentralDirectory().getFileHeaders().size();
            int i = 0;
            if (z2) {
                i = 4;
            }
            for (int i2 = 0; i2 < size; i2++) {
                long j = 0;
                for (int i3 = 0; i3 < ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i2)).getDiskNumberStart(); i3++) {
                    j += ((Long) arrayList2.get(i3)).longValue();
                }
                ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i2)).setOffsetLocalHeader((((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i2)).getOffsetLocalHeader() + j) - ((long) i));
                ((FileHeader) zipModel2.getCentralDirectory().getFileHeaders().get(i2)).setDiskNumberStart(0);
            }
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th;
            new ZipException((Throwable) exc);
            throw th4;
        }
    }

    private void updateSplitEndCentralDirectory(ZipModel zipModel) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ZipModel zipModel2 = zipModel;
        if (zipModel2 == null) {
            try {
                Throwable th4 = th3;
                new ZipException("zip model is null - cannot update end of central directory for split zip model");
                throw th4;
            } catch (ZipException e) {
                throw e;
            } catch (Exception e2) {
                Exception exc = e2;
                Throwable th5 = th;
                new ZipException((Throwable) exc);
                throw th5;
            }
        } else if (zipModel2.getCentralDirectory() == null) {
            Throwable th6 = th2;
            new ZipException("corrupt zip model - getCentralDirectory, cannot update split zip model");
            throw th6;
        } else {
            zipModel2.getEndCentralDirRecord().setNoOfThisDisk(0);
            zipModel2.getEndCentralDirRecord().setNoOfThisDiskStartOfCentralDir(0);
            zipModel2.getEndCentralDirRecord().setTotNoOfEntriesInCentralDir(zipModel2.getCentralDirectory().getFileHeaders().size());
            zipModel2.getEndCentralDirRecord().setTotNoOfEntriesInCentralDirOnThisDisk(zipModel2.getCentralDirectory().getFileHeaders().size());
        }
    }

    private void updateSplitZip64EndCentralDirLocator(ZipModel zipModel, ArrayList arrayList) throws ZipException {
        Throwable th;
        ZipModel zipModel2 = zipModel;
        ArrayList arrayList2 = arrayList;
        if (zipModel2 == null) {
            Throwable th2 = th;
            new ZipException("zip model is null, cannot update split Zip64 end of central directory locator");
            throw th2;
        } else if (zipModel2.getZip64EndCentralDirLocator() != null) {
            zipModel2.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(0);
            long j = 0;
            for (int i = 0; i < arrayList2.size(); i++) {
                j += ((Long) arrayList2.get(i)).longValue();
            }
            zipModel2.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(zipModel2.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec() + j);
            zipModel2.getZip64EndCentralDirLocator().setTotNumberOfDiscs(1);
        }
    }

    private void updateSplitZip64EndCentralDirRec(ZipModel zipModel, ArrayList arrayList) throws ZipException {
        Throwable th;
        ZipModel zipModel2 = zipModel;
        ArrayList arrayList2 = arrayList;
        if (zipModel2 == null) {
            Throwable th2 = th;
            new ZipException("zip model is null, cannot update split Zip64 end of central directory record");
            throw th2;
        } else if (zipModel2.getZip64EndCentralDirRecord() != null) {
            zipModel2.getZip64EndCentralDirRecord().setNoOfThisDisk(0);
            zipModel2.getZip64EndCentralDirRecord().setNoOfThisDiskStartOfCentralDir(0);
            zipModel2.getZip64EndCentralDirRecord().setTotNoOfEntriesInCentralDirOnThisDisk((long) zipModel2.getEndCentralDirRecord().getTotNoOfEntriesInCentralDir());
            long j = 0;
            for (int i = 0; i < arrayList2.size(); i++) {
                j += ((Long) arrayList2.get(i)).longValue();
            }
            zipModel2.getZip64EndCentralDirRecord().setOffsetStartCenDirWRTStartDiskNo(zipModel2.getZip64EndCentralDirRecord().getOffsetStartCenDirWRTStartDiskNo() + j);
        }
    }

    public void setComment(ZipModel zipModel, String str) throws ZipException {
        Throwable th;
        Throwable th2;
        HeaderWriter headerWriter;
        SplitOutputStream splitOutputStream;
        Throwable th3;
        String str2;
        Throwable th4;
        Throwable th5;
        ZipModel zipModel2 = zipModel;
        String str3 = str;
        if (str3 == null) {
            Throwable th6 = th5;
            new ZipException("comment is null, cannot update Zip file with comment");
            throw th6;
        } else if (zipModel2 == null) {
            Throwable th7 = th4;
            new ZipException("zipModel is null, cannot update Zip file with comment");
            throw th7;
        } else {
            String str4 = str3;
            byte[] bytes = str3.getBytes();
            int length = str3.length();
            if (Zip4jUtil.isSupportedCharset(InternalZipConstants.CHARSET_COMMENTS_DEFAULT)) {
                try {
                    new String(str3.getBytes(InternalZipConstants.CHARSET_COMMENTS_DEFAULT), InternalZipConstants.CHARSET_COMMENTS_DEFAULT);
                    str4 = str2;
                    bytes = str4.getBytes(InternalZipConstants.CHARSET_COMMENTS_DEFAULT);
                    length = str4.length();
                } catch (UnsupportedEncodingException e) {
                    UnsupportedEncodingException unsupportedEncodingException = e;
                    str4 = str3;
                    bytes = str3.getBytes();
                    length = str3.length();
                }
            }
            if (length > 65535) {
                Throwable th8 = th3;
                new ZipException("comment length exceeds maximum length");
                throw th8;
            }
            zipModel2.getEndCentralDirRecord().setComment(str4);
            zipModel2.getEndCentralDirRecord().setCommentBytes(bytes);
            zipModel2.getEndCentralDirRecord().setCommentLength(length);
            SplitOutputStream splitOutputStream2 = null;
            try {
                new HeaderWriter();
                HeaderWriter headerWriter2 = headerWriter;
                new SplitOutputStream(zipModel2.getZipFile());
                splitOutputStream2 = splitOutputStream;
                if (zipModel2.isZip64Format()) {
                    splitOutputStream2.seek(zipModel2.getZip64EndCentralDirRecord().getOffsetStartCenDirWRTStartDiskNo());
                } else {
                    splitOutputStream2.seek(zipModel2.getEndCentralDirRecord().getOffsetOfStartOfCentralDir());
                }
                headerWriter2.finalizeZipFileWithoutValidations(zipModel2, splitOutputStream2);
                if (splitOutputStream2 != null) {
                    try {
                        splitOutputStream2.close();
                    } catch (IOException e2) {
                        IOException iOException = e2;
                    }
                }
            } catch (FileNotFoundException e3) {
                FileNotFoundException fileNotFoundException = e3;
                Throwable th9 = th2;
                new ZipException((Throwable) fileNotFoundException);
                throw th9;
            } catch (IOException e4) {
                IOException iOException2 = e4;
                Throwable th10 = th;
                new ZipException((Throwable) iOException2);
                throw th10;
            } catch (Throwable th11) {
                Throwable th12 = th11;
                if (splitOutputStream2 != null) {
                    try {
                        splitOutputStream2.close();
                    } catch (IOException e5) {
                        IOException iOException3 = e5;
                    }
                }
                throw th12;
            }
        }
    }

    public void initProgressMonitorForRemoveOp(ZipModel zipModel, FileHeader fileHeader, ProgressMonitor progressMonitor) throws ZipException {
        Throwable th;
        ZipModel zipModel2 = zipModel;
        FileHeader fileHeader2 = fileHeader;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (zipModel2 == null || fileHeader2 == null || progressMonitor2 == null) {
            Throwable th2 = th;
            new ZipException("one of the input parameters is null, cannot calculate total work");
            throw th2;
        }
        progressMonitor2.setCurrentOperation(2);
        progressMonitor2.setFileName(fileHeader2.getFileName());
        progressMonitor2.setTotalWork(calculateTotalWorkForRemoveOp(zipModel2, fileHeader2));
        progressMonitor2.setState(1);
    }

    private long calculateTotalWorkForRemoveOp(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        File file;
        new File(zipModel.getZipFile());
        return Zip4jUtil.getFileLengh(file) - fileHeader.getCompressedSize();
    }

    public void initProgressMonitorForMergeOp(ZipModel zipModel, ProgressMonitor progressMonitor) throws ZipException {
        Throwable th;
        ZipModel zipModel2 = zipModel;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (zipModel2 == null) {
            Throwable th2 = th;
            new ZipException("zip model is null, cannot calculate total work for merge op");
            throw th2;
        }
        progressMonitor2.setCurrentOperation(4);
        progressMonitor2.setFileName(zipModel2.getZipFile());
        progressMonitor2.setTotalWork(calculateTotalWorkForMergeOp(zipModel2));
        progressMonitor2.setState(1);
    }

    private long calculateTotalWorkForMergeOp(ZipModel zipModel) throws ZipException {
        StringBuilder sb;
        String sb2;
        StringBuilder sb3;
        File file;
        ZipModel zipModel2 = zipModel;
        long j = 0;
        if (zipModel2.isSplitArchive()) {
            int noOfThisDisk = zipModel2.getEndCentralDirRecord().getNoOfThisDisk();
            String zipFile = zipModel2.getZipFile();
            for (int i = 0; i <= noOfThisDisk; i++) {
                if (0 == zipModel2.getEndCentralDirRecord().getNoOfThisDisk()) {
                    sb2 = zipModel2.getZipFile();
                } else if (0 >= 9) {
                    new StringBuilder();
                    sb2 = sb3.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z").append(0 + 1).toString();
                } else {
                    new StringBuilder();
                    sb2 = sb.append(zipFile.substring(0, zipFile.lastIndexOf("."))).append(".z0").append(0 + 1).toString();
                }
                new File(sb2);
                j += Zip4jUtil.getFileLengh(file);
            }
        }
        return j;
    }
}
