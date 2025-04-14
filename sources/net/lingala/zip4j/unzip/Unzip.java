package net.lingala.zip4j.unzip;

import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class Unzip {
    private ZipModel zipModel;

    public Unzip(ZipModel zipModel2) throws ZipException {
        Throwable th;
        ZipModel zipModel3 = zipModel2;
        if (zipModel3 == null) {
            Throwable th2 = th;
            new ZipException("ZipModel is null");
            throw th2;
        }
        this.zipModel = zipModel3;
    }

    public void extractAll(UnzipParameters unzipParameters, String str, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Throwable th;
        Thread thread;
        UnzipParameters unzipParameters2 = unzipParameters;
        String str2 = str;
        ProgressMonitor progressMonitor2 = progressMonitor;
        boolean z2 = z;
        CentralDirectory centralDirectory = this.zipModel.getCentralDirectory();
        if (centralDirectory == null || centralDirectory.getFileHeaders() == null) {
            Throwable th2 = th;
            new ZipException("invalid central directory in zipModel");
            throw th2;
        }
        ArrayList fileHeaders = centralDirectory.getFileHeaders();
        progressMonitor2.setCurrentOperation(1);
        progressMonitor2.setTotalWork(calculateTotalWork(fileHeaders));
        progressMonitor2.setState(1);
        if (z2) {
            final ArrayList arrayList = fileHeaders;
            final UnzipParameters unzipParameters3 = unzipParameters2;
            final ProgressMonitor progressMonitor3 = progressMonitor2;
            final String str3 = str2;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ Unzip this$0;

                {
                    this.this$0 = r10;
                }

                public void run() {
                    try {
                        this.this$0.initExtractAll(arrayList, unzipParameters3, progressMonitor3, str3);
                        progressMonitor3.endProgressMonitorSuccess();
                    } catch (ZipException e) {
                        ZipException zipException = e;
                    }
                }
            };
            thread.start();
            return;
        }
        initExtractAll(fileHeaders, unzipParameters2, progressMonitor2, str2);
    }

    /* access modifiers changed from: private */
    public void initExtractAll(ArrayList arrayList, UnzipParameters unzipParameters, ProgressMonitor progressMonitor, String str) throws ZipException {
        ArrayList arrayList2 = arrayList;
        UnzipParameters unzipParameters2 = unzipParameters;
        ProgressMonitor progressMonitor2 = progressMonitor;
        String str2 = str;
        for (int i = 0; i < arrayList2.size(); i++) {
            initExtractFile((FileHeader) arrayList2.get(i), str2, unzipParameters2, (String) null, progressMonitor2);
            if (progressMonitor2.isCancelAllTasks()) {
                progressMonitor2.setResult(3);
                progressMonitor2.setState(0);
                return;
            }
        }
    }

    public void extractFile(FileHeader fileHeader, String str, UnzipParameters unzipParameters, String str2, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Thread thread;
        Throwable th;
        FileHeader fileHeader2 = fileHeader;
        String str3 = str;
        UnzipParameters unzipParameters2 = unzipParameters;
        String str4 = str2;
        ProgressMonitor progressMonitor2 = progressMonitor;
        boolean z2 = z;
        if (fileHeader2 == null) {
            Throwable th2 = th;
            new ZipException("fileHeader is null");
            throw th2;
        }
        progressMonitor2.setCurrentOperation(1);
        progressMonitor2.setTotalWork(fileHeader2.getCompressedSize());
        progressMonitor2.setState(1);
        progressMonitor2.setPercentDone(0);
        progressMonitor2.setFileName(fileHeader2.getFileName());
        if (z2) {
            final FileHeader fileHeader3 = fileHeader2;
            final String str5 = str3;
            final UnzipParameters unzipParameters3 = unzipParameters2;
            final String str6 = str4;
            final ProgressMonitor progressMonitor3 = progressMonitor2;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ Unzip this$0;

                {
                    this.this$0 = r11;
                }

                public void run() {
                    try {
                        this.this$0.initExtractFile(fileHeader3, str5, unzipParameters3, str6, progressMonitor3);
                        progressMonitor3.endProgressMonitorSuccess();
                    } catch (ZipException e) {
                        ZipException zipException = e;
                    }
                }
            };
            thread.start();
            return;
        }
        initExtractFile(fileHeader2, str3, unzipParameters2, str4, progressMonitor2);
        progressMonitor2.endProgressMonitorSuccess();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0097, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0098, code lost:
        r6 = r9;
        r5.endProgressMonitorError(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009f, code lost:
        throw r6;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0097 A[ExcHandler: ZipException (r9v6 'e' net.lingala.zip4j.exception.ZipException A[CUSTOM_DECLARE]), Splitter:B:5:0x001b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initExtractFile(net.lingala.zip4j.model.FileHeader r16, java.lang.String r17, net.lingala.zip4j.model.UnzipParameters r18, java.lang.String r19, net.lingala.zip4j.progress.ProgressMonitor r20) throws net.lingala.zip4j.exception.ZipException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r9 = r1
            if (r9 != 0) goto L_0x0019
            net.lingala.zip4j.exception.ZipException r9 = new net.lingala.zip4j.exception.ZipException
            r14 = r9
            r9 = r14
            r10 = r14
            java.lang.String r11 = "fileHeader is null"
            r10.<init>((java.lang.String) r11)
            throw r9
        L_0x0019:
            r9 = r5
            r10 = r1
            java.lang.String r10 = r10.getFileName()     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r9.setFileName(r10)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r9 = r2
            java.lang.String r10 = net.lingala.zip4j.util.InternalZipConstants.FILE_SEPARATOR     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            boolean r9 = r9.endsWith(r10)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            if (r9 != 0) goto L_0x0043
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r14 = r9
            r9 = r14
            r10 = r14
            r10.<init>()     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r10 = r2
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            java.lang.String r10 = net.lingala.zip4j.util.InternalZipConstants.FILE_SEPARATOR     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            java.lang.String r9 = r9.toString()     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r2 = r9
        L_0x0043:
            r9 = r1
            boolean r9 = r9.isDirectory()     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            if (r9 == 0) goto L_0x00a0
            r9 = r1
            java.lang.String r9 = r9.getFileName()     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r6 = r9
            r9 = r6
            boolean r9 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r9)     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            if (r9 != 0) goto L_0x0058
        L_0x0057:
            return
        L_0x0058:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r14 = r9
            r9 = r14
            r10 = r14
            r10.<init>()     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r10 = r2
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r10 = r6
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r7 = r9
            java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r14 = r9
            r9 = r14
            r10 = r14
            r11 = r7
            r10.<init>(r11)     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            r8 = r9
            r9 = r8
            boolean r9 = r9.exists()     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
            if (r9 != 0) goto L_0x0085
            r9 = r8
            boolean r9 = r9.mkdirs()     // Catch:{ Exception -> 0x0086, ZipException -> 0x0097 }
        L_0x0085:
            goto L_0x0057
        L_0x0086:
            r9 = move-exception
            r6 = r9
            r9 = r5
            r10 = r6
            r9.endProgressMonitorError(r10)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            net.lingala.zip4j.exception.ZipException r9 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r14 = r9
            r9 = r14
            r10 = r14
            r11 = r6
            r10.<init>((java.lang.Throwable) r11)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            throw r9     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
        L_0x0097:
            r9 = move-exception
            r6 = r9
            r9 = r5
            r10 = r6
            r9.endProgressMonitorError(r10)
            r9 = r6
            throw r9
        L_0x00a0:
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r4
            r9.checkOutputDirectoryStructure(r10, r11, r12)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            net.lingala.zip4j.unzip.UnzipEngine r9 = new net.lingala.zip4j.unzip.UnzipEngine     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r14 = r9
            r9 = r14
            r10 = r14
            r11 = r0
            net.lingala.zip4j.model.ZipModel r11 = r11.zipModel     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r12 = r1
            r10.<init>(r11, r12)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r6 = r9
            r9 = r6
            r10 = r5
            r11 = r2
            r12 = r4
            r13 = r3
            r9.unzipFile(r10, r11, r12, r13)     // Catch:{ Exception -> 0x00bd, ZipException -> 0x0097 }
            goto L_0x0085
        L_0x00bd:
            r9 = move-exception
            r7 = r9
            r9 = r5
            r10 = r7
            r9.endProgressMonitorError(r10)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            net.lingala.zip4j.exception.ZipException r9 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            r14 = r9
            r9 = r14
            r10 = r14
            r11 = r7
            r10.<init>((java.lang.Throwable) r11)     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
            throw r9     // Catch:{ ZipException -> 0x0097, Exception -> 0x00ce }
        L_0x00ce:
            r9 = move-exception
            r6 = r9
            r9 = r5
            r10 = r6
            r9.endProgressMonitorError(r10)
            net.lingala.zip4j.exception.ZipException r9 = new net.lingala.zip4j.exception.ZipException
            r14 = r9
            r9 = r14
            r10 = r14
            r11 = r6
            r10.<init>((java.lang.Throwable) r11)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.unzip.Unzip.initExtractFile(net.lingala.zip4j.model.FileHeader, java.lang.String, net.lingala.zip4j.model.UnzipParameters, java.lang.String, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    public ZipInputStream getInputStream(FileHeader fileHeader) throws ZipException {
        UnzipEngine unzipEngine;
        new UnzipEngine(this.zipModel, fileHeader);
        return unzipEngine.getInputStream();
    }

    private void checkOutputDirectoryStructure(FileHeader fileHeader, String str, String str2) throws ZipException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        File file;
        File file2;
        FileHeader fileHeader2 = fileHeader;
        String str3 = str;
        String str4 = str2;
        if (fileHeader2 == null || !Zip4jUtil.isStringNotNullAndNotEmpty(str3)) {
            Throwable th3 = th;
            new ZipException("Cannot check output directory structure...one of the parameters was null");
            throw th3;
        }
        String fileName = fileHeader2.getFileName();
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str4)) {
            fileName = str4;
        }
        if (Zip4jUtil.isStringNotNullAndNotEmpty(fileName)) {
            new StringBuilder();
            try {
                new File(sb.append(str3).append(fileName).toString());
                new File(file.getParent());
                File file3 = file2;
                if (!file3.exists()) {
                    boolean mkdirs = file3.mkdirs();
                }
            } catch (Exception e) {
                Exception exc = e;
                Throwable th4 = th2;
                new ZipException((Throwable) exc);
                throw th4;
            }
        }
    }

    private long calculateTotalWork(ArrayList arrayList) throws ZipException {
        long j;
        long compressedSize;
        Throwable th;
        ArrayList arrayList2 = arrayList;
        if (arrayList2 == null) {
            Throwable th2 = th;
            new ZipException("fileHeaders is null, cannot calculate total work");
            throw th2;
        }
        long j2 = 0;
        for (int i = 0; i < arrayList2.size(); i++) {
            FileHeader fileHeader = (FileHeader) arrayList2.get(i);
            if (fileHeader.getZip64ExtendedInfo() == null || fileHeader.getZip64ExtendedInfo().getUnCompressedSize() <= 0) {
                j = j2;
                compressedSize = fileHeader.getCompressedSize();
            } else {
                j = j2;
                compressedSize = fileHeader.getZip64ExtendedInfo().getCompressedSize();
            }
            j2 = j + compressedSize;
        }
        return j2;
    }
}
