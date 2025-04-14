package net.lingala.zip4j.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.EndCentralDirRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.ArchiveMaintainer;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class ZipEngine {
    private ZipModel zipModel;

    public ZipEngine(ZipModel zipModel2) throws ZipException {
        Throwable th;
        ZipModel zipModel3 = zipModel2;
        if (zipModel3 == null) {
            Throwable th2 = th;
            new ZipException("zip model is null in ZipEngine constructor");
            throw th2;
        }
        this.zipModel = zipModel3;
    }

    public void addFiles(ArrayList arrayList, ZipParameters zipParameters, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Throwable th;
        Thread thread;
        Throwable th2;
        ArrayList arrayList2 = arrayList;
        ZipParameters zipParameters2 = zipParameters;
        ProgressMonitor progressMonitor2 = progressMonitor;
        boolean z2 = z;
        if (arrayList2 == null || zipParameters2 == null) {
            Throwable th3 = th;
            new ZipException("one of the input parameters is null when adding files");
            throw th3;
        } else if (arrayList2.size() <= 0) {
            Throwable th4 = th2;
            new ZipException("no files to add");
            throw th4;
        } else {
            progressMonitor2.setCurrentOperation(0);
            progressMonitor2.setState(1);
            progressMonitor2.setResult(1);
            if (z2) {
                progressMonitor2.setTotalWork(calculateTotalWork(arrayList2, zipParameters2));
                progressMonitor2.setFileName(((File) arrayList2.get(0)).getAbsolutePath());
                final ArrayList arrayList3 = arrayList2;
                final ZipParameters zipParameters3 = zipParameters2;
                final ProgressMonitor progressMonitor3 = progressMonitor2;
                new Thread(this, InternalZipConstants.THREAD_NAME) {
                    final /* synthetic */ ZipEngine this$0;

                    {
                        this.this$0 = r9;
                    }

                    public void run() {
                        try {
                            this.this$0.initAddFiles(arrayList3, zipParameters3, progressMonitor3);
                        } catch (ZipException e) {
                            ZipException zipException = e;
                        }
                    }
                };
                thread.start();
                return;
            }
            initAddFiles(arrayList2, zipParameters2, progressMonitor2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v120, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initAddFiles(java.util.ArrayList r24, net.lingala.zip4j.model.ZipParameters r25, net.lingala.zip4j.progress.ProgressMonitor r26) throws net.lingala.zip4j.exception.ZipException {
        /*
            r23 = this;
            r2 = r23
            r3 = r24
            r4 = r25
            r5 = r26
            r17 = r3
            if (r17 == 0) goto L_0x0010
            r17 = r4
            if (r17 != 0) goto L_0x001e
        L_0x0010:
            net.lingala.zip4j.exception.ZipException r17 = new net.lingala.zip4j.exception.ZipException
            r22 = r17
            r17 = r22
            r18 = r22
            java.lang.String r19 = "one of the input parameters is null when adding files"
            r18.<init>((java.lang.String) r19)
            throw r17
        L_0x001e:
            r17 = r3
            int r17 = r17.size()
            if (r17 > 0) goto L_0x0034
            net.lingala.zip4j.exception.ZipException r17 = new net.lingala.zip4j.exception.ZipException
            r22 = r17
            r17 = r22
            r18 = r22
            java.lang.String r19 = "no files to add"
            r18.<init>((java.lang.String) r19)
            throw r17
        L_0x0034:
            r17 = r2
            r0 = r17
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel
            r17 = r0
            net.lingala.zip4j.model.EndCentralDirRecord r17 = r17.getEndCentralDirRecord()
            if (r17 != 0) goto L_0x0053
            r17 = r2
            r0 = r17
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel
            r17 = r0
            r18 = r2
            net.lingala.zip4j.model.EndCentralDirRecord r18 = r18.createEndOfCentralDirectoryRecord()
            r17.setEndCentralDirRecord(r18)
        L_0x0053:
            r17 = 0
            r6 = r17
            r17 = 0
            r7 = r17
            r17 = r2
            r18 = r4
            r17.checkParameters(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r2
            r18 = r3
            r19 = r4
            r20 = r5
            r17.removeFilesIfExists(r18, r19, r20)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r2
            r0 = r17
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r0
            java.lang.String r17 = r17.getZipFile()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            boolean r17 = net.lingala.zip4j.util.Zip4jUtil.checkFileExists((java.lang.String) r17)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r8 = r17
            net.lingala.zip4j.io.SplitOutputStream r17 = new net.lingala.zip4j.io.SplitOutputStream     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r22 = r17
            r17 = r22
            r18 = r22
            java.io.File r19 = new java.io.File     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r22 = r19
            r19 = r22
            r20 = r22
            r21 = r2
            r0 = r21
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r21 = r0
            java.lang.String r21 = r21.getZipFile()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r20.<init>(r21)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r20 = r2
            r0 = r20
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r20 = r0
            long r20 = r20.getSplitLength()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r18.<init>((java.io.File) r19, (long) r20)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r9 = r17
            net.lingala.zip4j.io.ZipOutputStream r17 = new net.lingala.zip4j.io.ZipOutputStream     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r9
            r20 = r2
            r0 = r20
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r20 = r0
            r18.<init>(r19, r20)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r6 = r17
            r17 = r8
            if (r17 == 0) goto L_0x0120
            r17 = r2
            r0 = r17
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r0
            net.lingala.zip4j.model.EndCentralDirRecord r17 = r17.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 != 0) goto L_0x010b
            net.lingala.zip4j.exception.ZipException r17 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r22 = r17
            r17 = r22
            r18 = r22
            java.lang.String r19 = "invalid end of central directory record"
            r18.<init>((java.lang.String) r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            throw r17     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
        L_0x00e6:
            r17 = move-exception
            r8 = r17
            r17 = r5
            r18 = r8
            r17.endProgressMonitorError(r18)     // Catch:{ all -> 0x00f3 }
            r17 = r8
            throw r17     // Catch:{ all -> 0x00f3 }
        L_0x00f3:
            r17 = move-exception
            r15 = r17
            r17 = r7
            if (r17 == 0) goto L_0x00ff
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x0318 }
        L_0x00ff:
            r17 = r6
            if (r17 == 0) goto L_0x0108
            r17 = r6
            r17.close()     // Catch:{ IOException -> 0x031d }
        L_0x0108:
            r17 = r15
            throw r17
        L_0x010b:
            r17 = r9
            r18 = r2
            r0 = r18
            net.lingala.zip4j.model.ZipModel r0 = r0.zipModel     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r18 = r0
            net.lingala.zip4j.model.EndCentralDirRecord r18 = r18.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            long r18 = r18.getOffsetOfStartOfCentralDir()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17.seek(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
        L_0x0120:
            r17 = 4096(0x1000, float:5.74E-42)
            r0 = r17
            byte[] r0 = new byte[r0]     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r0
            r10 = r17
            r17 = -1
            r11 = r17
            r17 = 0
            r12 = r17
        L_0x0132:
            r17 = r12
            r18 = r3
            int r18 = r18.size()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x02f2
            r17 = r5
            boolean r17 = r17.isCancelAllTasks()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 == 0) goto L_0x0171
            r17 = r5
            r18 = 3
            r17.setResult(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            r18 = 0
            r17.setState(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r7
            if (r17 == 0) goto L_0x015f
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x0169 }
        L_0x015f:
            r17 = r6
            if (r17 == 0) goto L_0x0168
            r17 = r6
            r17.close()     // Catch:{ IOException -> 0x016d }
        L_0x0168:
            return
        L_0x0169:
            r17 = move-exception
            r13 = r17
            goto L_0x015f
        L_0x016d:
            r17 = move-exception
            r13 = r17
            goto L_0x0168
        L_0x0171:
            r17 = r4
            java.lang.Object r17 = r17.clone()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            net.lingala.zip4j.model.ZipParameters r17 = (net.lingala.zip4j.model.ZipParameters) r17     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r13 = r17
            r17 = r5
            r18 = r3
            r19 = r12
            java.lang.Object r18 = r18.get(r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r18 = (java.io.File) r18     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.lang.String r18 = r18.getAbsolutePath()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17.setFileName(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r3
            r18 = r12
            java.lang.Object r17 = r17.get(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r17 = (java.io.File) r17     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            boolean r17 = r17.isDirectory()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 != 0) goto L_0x0227
            r17 = r13
            boolean r17 = r17.isEncryptFiles()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 == 0) goto L_0x020c
            r17 = r13
            int r17 = r17.getEncryptionMethod()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 != 0) goto L_0x020c
            r17 = r5
            r18 = 3
            r17.setCurrentOperation(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r13
            r18 = r3
            r19 = r12
            java.lang.Object r18 = r18.get(r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r18 = (java.io.File) r18     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.lang.String r18 = r18.getAbsolutePath()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r19 = r5
            long r18 = net.lingala.zip4j.util.CRCUtil.computeFileCRC(r18, r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r0 = r18
            int r0 = (int) r0     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r18 = r0
            r17.setSourceFileCRC(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            r18 = 0
            r17.setCurrentOperation(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            boolean r17 = r17.isCancelAllTasks()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 == 0) goto L_0x020c
            r17 = r5
            r18 = 3
            r17.setResult(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            r18 = 0
            r17.setState(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r7
            if (r17 == 0) goto L_0x01f9
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x0204 }
        L_0x01f9:
            r17 = r6
            if (r17 == 0) goto L_0x0202
            r17 = r6
            r17.close()     // Catch:{ IOException -> 0x0208 }
        L_0x0202:
            goto L_0x0168
        L_0x0204:
            r17 = move-exception
            r14 = r17
            goto L_0x01f9
        L_0x0208:
            r17 = move-exception
            r14 = r17
            goto L_0x0202
        L_0x020c:
            r17 = r3
            r18 = r12
            java.lang.Object r17 = r17.get(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r17 = (java.io.File) r17     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            long r17 = net.lingala.zip4j.util.Zip4jUtil.getFileLengh((java.io.File) r17)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r19 = 0
            int r17 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r17 != 0) goto L_0x0227
            r17 = r13
            r18 = 0
            r17.setCompressionMethod(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
        L_0x0227:
            r17 = r6
            r18 = r3
            r19 = r12
            java.lang.Object r18 = r18.get(r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r18 = (java.io.File) r18     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r19 = r13
            r17.putNextEntry(r18, r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r3
            r18 = r12
            java.lang.Object r17 = r17.get(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r17 = (java.io.File) r17     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            boolean r17 = r17.isDirectory()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 == 0) goto L_0x0251
            r17 = r6
            r17.closeEntry()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
        L_0x024d:
            int r12 = r12 + 1
            goto L_0x0132
        L_0x0251:
            java.io.FileInputStream r17 = new java.io.FileInputStream     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r3
            r20 = r12
            java.lang.Object r19 = r19.get(r20)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            java.io.File r19 = (java.io.File) r19     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r18.<init>(r19)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r7 = r17
        L_0x0268:
            r17 = r7
            r18 = r10
            int r17 = r17.read(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r22 = r17
            r17 = r22
            r18 = r22
            r11 = r18
            r18 = -1
            r0 = r17
            r1 = r18
            if (r0 == r1) goto L_0x02ca
            r17 = r5
            boolean r17 = r17.isCancelAllTasks()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            if (r17 == 0) goto L_0x02b2
            r17 = r5
            r18 = 3
            r17.setResult(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            r18 = 0
            r17.setState(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r7
            if (r17 == 0) goto L_0x029f
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x02aa }
        L_0x029f:
            r17 = r6
            if (r17 == 0) goto L_0x02a8
            r17 = r6
            r17.close()     // Catch:{ IOException -> 0x02ae }
        L_0x02a8:
            goto L_0x0168
        L_0x02aa:
            r17 = move-exception
            r14 = r17
            goto L_0x029f
        L_0x02ae:
            r17 = move-exception
            r14 = r17
            goto L_0x02a8
        L_0x02b2:
            r17 = r6
            r18 = r10
            r19 = 0
            r20 = r11
            r17.write(r18, r19, r20)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            r18 = r11
            r0 = r18
            long r0 = (long) r0     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r18 = r0
            r17.updateWorkCompleted(r18)     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            goto L_0x0268
        L_0x02ca:
            r17 = r6
            r17.closeEntry()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r7
            if (r17 == 0) goto L_0x024d
            r17 = r7
            r17.close()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            goto L_0x024d
        L_0x02da:
            r17 = move-exception
            r8 = r17
            r17 = r5
            r18 = r8
            r17.endProgressMonitorError(r18)     // Catch:{ all -> 0x00f3 }
            net.lingala.zip4j.exception.ZipException r17 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x00f3 }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r8
            r18.<init>((java.lang.Throwable) r19)     // Catch:{ all -> 0x00f3 }
            throw r17     // Catch:{ all -> 0x00f3 }
        L_0x02f2:
            r17 = r6
            r17.finish()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r5
            r17.endProgressMonitorSuccess()     // Catch:{ ZipException -> 0x00e6, Exception -> 0x02da }
            r17 = r7
            if (r17 == 0) goto L_0x0305
            r17 = r7
            r17.close()     // Catch:{ IOException -> 0x0310 }
        L_0x0305:
            r17 = r6
            if (r17 == 0) goto L_0x030e
            r17 = r6
            r17.close()     // Catch:{ IOException -> 0x0314 }
        L_0x030e:
            goto L_0x0168
        L_0x0310:
            r17 = move-exception
            r8 = r17
            goto L_0x0305
        L_0x0314:
            r17 = move-exception
            r8 = r17
            goto L_0x030e
        L_0x0318:
            r17 = move-exception
            r16 = r17
            goto L_0x00ff
        L_0x031d:
            r17 = move-exception
            r16 = r17
            goto L_0x0108
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.zip.ZipEngine.initAddFiles(java.util.ArrayList, net.lingala.zip4j.model.ZipParameters, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v47, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addStreamToZip(java.io.InputStream r18, net.lingala.zip4j.model.ZipParameters r19) throws net.lingala.zip4j.exception.ZipException {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r3 = r19
            r11 = r2
            if (r11 == 0) goto L_0x000c
            r11 = r3
            if (r11 != 0) goto L_0x001a
        L_0x000c:
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "one of the input parameters is null, cannot add stream to zip"
            r12.<init>((java.lang.String) r13)
            throw r11
        L_0x001a:
            r11 = 0
            r4 = r11
            r11 = r1
            r12 = r3
            r11.checkParameters(r12)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r11 = r1
            net.lingala.zip4j.model.ZipModel r11 = r11.zipModel     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            java.lang.String r11 = r11.getZipFile()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            boolean r11 = net.lingala.zip4j.util.Zip4jUtil.checkFileExists((java.lang.String) r11)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r5 = r11
            net.lingala.zip4j.io.SplitOutputStream r11 = new net.lingala.zip4j.io.SplitOutputStream     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r16 = r11
            r11 = r16
            r12 = r16
            java.io.File r13 = new java.io.File     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r16 = r13
            r13 = r16
            r14 = r16
            r15 = r1
            net.lingala.zip4j.model.ZipModel r15 = r15.zipModel     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            java.lang.String r15 = r15.getZipFile()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r14.<init>(r15)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r14 = r1
            net.lingala.zip4j.model.ZipModel r14 = r14.zipModel     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            long r14 = r14.getSplitLength()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r12.<init>((java.io.File) r13, (long) r14)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r6 = r11
            net.lingala.zip4j.io.ZipOutputStream r11 = new net.lingala.zip4j.io.ZipOutputStream     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r6
            r14 = r1
            net.lingala.zip4j.model.ZipModel r14 = r14.zipModel     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r12.<init>(r13, r14)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r4 = r11
            r11 = r5
            if (r11 == 0) goto L_0x009a
            r11 = r1
            net.lingala.zip4j.model.ZipModel r11 = r11.zipModel     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            net.lingala.zip4j.model.EndCentralDirRecord r11 = r11.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            if (r11 != 0) goto L_0x008b
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "invalid end of central directory record"
            r12.<init>((java.lang.String) r13)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            throw r11     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
        L_0x007c:
            r11 = move-exception
            r5 = r11
            r11 = r5
            throw r11     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r11 = move-exception
            r9 = r11
            r11 = r4
            if (r11 == 0) goto L_0x0089
            r11 = r4
            r11.close()     // Catch:{ IOException -> 0x00fb }
        L_0x0089:
            r11 = r9
            throw r11
        L_0x008b:
            r11 = r6
            r12 = r1
            net.lingala.zip4j.model.ZipModel r12 = r12.zipModel     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            net.lingala.zip4j.model.EndCentralDirRecord r12 = r12.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            long r12 = r12.getOffsetOfStartOfCentralDir()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r11.seek(r12)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
        L_0x009a:
            r11 = 4096(0x1000, float:5.74E-42)
            byte[] r11 = new byte[r11]     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r7 = r11
            r11 = -1
            r8 = r11
            r11 = r4
            r12 = 0
            r13 = r3
            r11.putNextEntry(r12, r13)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r11 = r3
            java.lang.String r11 = r11.getFileNameInZip()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            java.lang.String r12 = "/"
            boolean r11 = r11.endsWith(r12)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            if (r11 != 0) goto L_0x00d9
            r11 = r3
            java.lang.String r11 = r11.getFileNameInZip()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            java.lang.String r12 = "\\"
            boolean r11 = r11.endsWith(r12)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            if (r11 != 0) goto L_0x00d9
        L_0x00c1:
            r11 = r2
            r12 = r7
            int r11 = r11.read(r12)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r16 = r11
            r11 = r16
            r12 = r16
            r8 = r12
            r12 = -1
            if (r11 == r12) goto L_0x00d9
            r11 = r4
            r12 = r7
            r13 = 0
            r14 = r8
            r11.write(r12, r13, r14)     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            goto L_0x00c1
        L_0x00d9:
            r11 = r4
            r11.closeEntry()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r11 = r4
            r11.finish()     // Catch:{ ZipException -> 0x007c, Exception -> 0x00ec }
            r11 = r4
            if (r11 == 0) goto L_0x00e8
            r11 = r4
            r11.close()     // Catch:{ IOException -> 0x00e9 }
        L_0x00e8:
            return
        L_0x00e9:
            r11 = move-exception
            r5 = r11
            goto L_0x00e8
        L_0x00ec:
            r11 = move-exception
            r5 = r11
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0080 }
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r5
            r12.<init>((java.lang.Throwable) r13)     // Catch:{ all -> 0x0080 }
            throw r11     // Catch:{ all -> 0x0080 }
        L_0x00fb:
            r11 = move-exception
            r10 = r11
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.zip.ZipEngine.addStreamToZip(java.io.InputStream, net.lingala.zip4j.model.ZipParameters):void");
    }

    public void addFolderToZip(File file, ZipParameters zipParameters, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        Throwable th;
        String absolutePath;
        ArrayList arrayList;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        Throwable th4;
        File file2 = file;
        ZipParameters zipParameters2 = zipParameters;
        ProgressMonitor progressMonitor2 = progressMonitor;
        boolean z2 = z;
        if (file2 == null || zipParameters2 == null) {
            Throwable th5 = th;
            new ZipException("one of the input parameters is null, cannot add folder to zip");
            throw th5;
        } else if (!Zip4jUtil.checkFileExists(file2.getAbsolutePath())) {
            Throwable th6 = th4;
            new ZipException("input folder does not exist");
            throw th6;
        } else if (!file2.isDirectory()) {
            Throwable th7 = th3;
            new ZipException("input file is not a folder, user addFileToZip method to add files");
            throw th7;
        } else if (!Zip4jUtil.checkFileReadAccess(file2.getAbsolutePath())) {
            Throwable th8 = th2;
            new StringBuilder();
            new ZipException(sb.append("cannot read folder: ").append(file2.getAbsolutePath()).toString());
            throw th8;
        } else {
            if (!zipParameters2.isIncludeRootFolder()) {
                absolutePath = file2.getAbsolutePath();
            } else if (file2.getAbsolutePath() != null) {
                absolutePath = file2.getAbsoluteFile().getParentFile() != null ? file2.getAbsoluteFile().getParentFile().getAbsolutePath() : "";
            } else {
                absolutePath = file2.getParentFile() != null ? file2.getParentFile().getAbsolutePath() : "";
            }
            zipParameters2.setDefaultFolderPath(absolutePath);
            ArrayList filesInDirectoryRec = Zip4jUtil.getFilesInDirectoryRec(file2, zipParameters2.isReadHiddenFiles());
            if (zipParameters2.isIncludeRootFolder()) {
                if (filesInDirectoryRec == null) {
                    new ArrayList();
                    filesInDirectoryRec = arrayList;
                }
                boolean add = filesInDirectoryRec.add(file2);
            }
            addFiles(filesInDirectoryRec, zipParameters2, progressMonitor2, z2);
        }
    }

    private void checkParameters(ZipParameters zipParameters) throws ZipException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        ZipParameters zipParameters2 = zipParameters;
        if (zipParameters2 == null) {
            Throwable th6 = th5;
            new ZipException("cannot validate zip parameters");
            throw th6;
        } else if (zipParameters2.getCompressionMethod() != 0 && zipParameters2.getCompressionMethod() != 8) {
            Throwable th7 = th4;
            new ZipException("unsupported compression type");
            throw th7;
        } else if (zipParameters2.getCompressionMethod() == 8 && zipParameters2.getCompressionLevel() < 0 && zipParameters2.getCompressionLevel() > 9) {
            Throwable th8 = th3;
            new ZipException("invalid compression level. compression level dor deflate should be in the range of 0-9");
            throw th8;
        } else if (!zipParameters2.isEncryptFiles()) {
            zipParameters2.setAesKeyStrength(-1);
            zipParameters2.setEncryptionMethod(-1);
        } else if (zipParameters2.getEncryptionMethod() != 0 && zipParameters2.getEncryptionMethod() != 99) {
            Throwable th9 = th2;
            new ZipException("unsupported encryption method");
            throw th9;
        } else if (zipParameters2.getPassword() == null || zipParameters2.getPassword().length <= 0) {
            Throwable th10 = th;
            new ZipException("input password is empty or null");
            throw th10;
        }
    }

    private void removeFilesIfExists(ArrayList arrayList, ZipParameters zipParameters, ProgressMonitor progressMonitor) throws ZipException {
        Throwable th;
        ArchiveMaintainer archiveMaintainer;
        Throwable th2;
        Throwable th3;
        ArrayList arrayList2 = arrayList;
        ZipParameters zipParameters2 = zipParameters;
        ProgressMonitor progressMonitor2 = progressMonitor;
        if (this.zipModel != null) {
            if (this.zipModel.getCentralDirectory() != null) {
                if (this.zipModel.getCentralDirectory().getFileHeaders() != null) {
                    if (this.zipModel.getCentralDirectory().getFileHeaders().size() > 0) {
                        RandomAccessFile randomAccessFile = null;
                        int i = 0;
                        while (true) {
                            try {
                                if (i < arrayList2.size()) {
                                    FileHeader fileHeader = Zip4jUtil.getFileHeader(this.zipModel, Zip4jUtil.getRelativeFileName(((File) arrayList2.get(i)).getAbsolutePath(), zipParameters2.getRootFolderInZip(), zipParameters2.getDefaultFolderPath()));
                                    if (fileHeader != null) {
                                        if (randomAccessFile != null) {
                                            randomAccessFile.close();
                                            randomAccessFile = null;
                                        }
                                        new ArchiveMaintainer();
                                        progressMonitor2.setCurrentOperation(2);
                                        HashMap initRemoveZipFile = archiveMaintainer.initRemoveZipFile(this.zipModel, fileHeader, progressMonitor2);
                                        if (progressMonitor2.isCancelAllTasks()) {
                                            progressMonitor2.setResult(3);
                                            progressMonitor2.setState(0);
                                            if (randomAccessFile != null) {
                                                try {
                                                    randomAccessFile.close();
                                                    return;
                                                } catch (IOException e) {
                                                    IOException iOException = e;
                                                    return;
                                                }
                                            } else {
                                                return;
                                            }
                                        } else {
                                            progressMonitor2.setCurrentOperation(0);
                                            if (randomAccessFile == null) {
                                                randomAccessFile = prepareFileOutputStream();
                                                if (!(initRemoveZipFile == null || initRemoveZipFile.get(InternalZipConstants.OFFSET_CENTRAL_DIR) == null)) {
                                                    long parseLong = Long.parseLong((String) initRemoveZipFile.get(InternalZipConstants.OFFSET_CENTRAL_DIR));
                                                    if (parseLong >= 0) {
                                                        randomAccessFile.seek(parseLong);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i++;
                                } else if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                        return;
                                    } catch (IOException e2) {
                                        IOException iOException2 = e2;
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } catch (NumberFormatException e3) {
                                NumberFormatException numberFormatException = e3;
                                Throwable th4 = th3;
                                new ZipException("NumberFormatException while parsing offset central directory. Cannot update already existing file header");
                                throw th4;
                            } catch (Exception e4) {
                                Exception exc = e4;
                                Throwable th5 = th2;
                                new ZipException("Error while parsing offset central directory. Cannot update already existing file header");
                                throw th5;
                            } catch (IOException e5) {
                                IOException iOException3 = e5;
                                try {
                                    Throwable th6 = th;
                                    new ZipException((Throwable) iOException3);
                                    throw th6;
                                } catch (Throwable th7) {
                                    Throwable th8 = th7;
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (IOException e6) {
                                            IOException iOException4 = e6;
                                        }
                                    }
                                    throw th8;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private RandomAccessFile prepareFileOutputStream() throws ZipException {
        Throwable th;
        File file;
        RandomAccessFile randomAccessFile;
        Throwable th2;
        String zipFile = this.zipModel.getZipFile();
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(zipFile)) {
            Throwable th3 = th2;
            new ZipException("invalid output path");
            throw th3;
        }
        try {
            new File(zipFile);
            File file2 = file;
            if (!file2.getParentFile().exists()) {
                boolean mkdirs = file2.getParentFile().mkdirs();
            }
            RandomAccessFile randomAccessFile2 = randomAccessFile;
            new RandomAccessFile(file2, InternalZipConstants.WRITE_MODE);
            return randomAccessFile2;
        } catch (FileNotFoundException e) {
            FileNotFoundException fileNotFoundException = e;
            Throwable th4 = th;
            new ZipException((Throwable) fileNotFoundException);
            throw th4;
        }
    }

    private EndCentralDirRecord createEndOfCentralDirectoryRecord() {
        EndCentralDirRecord endCentralDirRecord;
        new EndCentralDirRecord();
        EndCentralDirRecord endCentralDirRecord2 = endCentralDirRecord;
        endCentralDirRecord2.setSignature(InternalZipConstants.ENDSIG);
        endCentralDirRecord2.setNoOfThisDisk(0);
        endCentralDirRecord2.setTotNoOfEntriesInCentralDir(0);
        endCentralDirRecord2.setTotNoOfEntriesInCentralDirOnThisDisk(0);
        endCentralDirRecord2.setOffsetOfStartOfCentralDir(0);
        return endCentralDirRecord2;
    }

    private long calculateTotalWork(ArrayList arrayList, ZipParameters zipParameters) throws ZipException {
        FileHeader fileHeader;
        File file;
        Throwable th;
        ArrayList arrayList2 = arrayList;
        ZipParameters zipParameters2 = zipParameters;
        if (arrayList2 == null) {
            Throwable th2 = th;
            new ZipException("file list is null, cannot calculate total work");
            throw th2;
        }
        long j = 0;
        for (int i = 0; i < arrayList2.size(); i++) {
            if ((arrayList2.get(i) instanceof File) && ((File) arrayList2.get(i)).exists()) {
                if (!zipParameters2.isEncryptFiles() || zipParameters2.getEncryptionMethod() != 0) {
                    j += Zip4jUtil.getFileLengh((File) arrayList2.get(i));
                } else {
                    j += Zip4jUtil.getFileLengh((File) arrayList2.get(i)) * 2;
                }
                if (!(this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null || this.zipModel.getCentralDirectory().getFileHeaders().size() <= 0 || (fileHeader = Zip4jUtil.getFileHeader(this.zipModel, Zip4jUtil.getRelativeFileName(((File) arrayList2.get(i)).getAbsolutePath(), zipParameters2.getRootFolderInZip(), zipParameters2.getDefaultFolderPath()))) == null)) {
                    new File(this.zipModel.getZipFile());
                    j += Zip4jUtil.getFileLengh(file) - fileHeader.getCompressedSize();
                }
            }
        }
        return j;
    }
}
