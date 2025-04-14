package net.lingala.zip4j.util;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

public class CRCUtil {
    private static final int BUF_SIZE = 16384;

    public CRCUtil() {
    }

    public static long computeFileCRC(String str) throws ZipException {
        return computeFileCRC(str, (ProgressMonitor) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009d, code lost:
        r6 = r5.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a0, code lost:
        if (r2 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a9, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        r8 = r11;
        r11 = r16;
        new net.lingala.zip4j.exception.ZipException("error while closing the file after calculating crc");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b8, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long computeFileCRC(java.lang.String r17, net.lingala.zip4j.progress.ProgressMonitor r18) throws net.lingala.zip4j.exception.ZipException {
        /*
            r0 = r17
            r1 = r18
            r11 = r0
            boolean r11 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r11)
            if (r11 != 0) goto L_0x0019
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "input file is null or empty, cannot calculate CRC for the file"
            r12.<init>((java.lang.String) r13)
            throw r11
        L_0x0019:
            r11 = 0
            r2 = r11
            r11 = r0
            boolean r11 = net.lingala.zip4j.util.Zip4jUtil.checkFileReadAccess(r11)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r16 = r11
            r11 = r16
            r12 = r16
            java.io.File r13 = new java.io.File     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r16 = r13
            r13 = r16
            r14 = r16
            r15 = r0
            r14.<init>(r15)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r12.<init>(r13)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r2 = r11
            r11 = 16384(0x4000, float:2.2959E-41)
            byte[] r11 = new byte[r11]     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r3 = r11
            r11 = -2
            r4 = r11
            java.util.zip.CRC32 r11 = new java.util.zip.CRC32     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r5 = r11
        L_0x004b:
            r11 = r2
            r12 = r3
            int r11 = r11.read(r12)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r16 = r11
            r11 = r16
            r12 = r16
            r4 = r12
            r12 = -1
            if (r11 == r12) goto L_0x0099
            r11 = r5
            r12 = r3
            r13 = 0
            r14 = r4
            r11.update(r12, r13, r14)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r11 = r1
            if (r11 == 0) goto L_0x004b
            r11 = r1
            r12 = r4
            long r12 = (long) r12     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r11.updateWorkCompleted(r12)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r11 = r1
            boolean r11 = r11.isCancelAllTasks()     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            if (r11 == 0) goto L_0x004b
            r11 = r1
            r12 = 3
            r11.setResult(r12)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r11 = r1
            r12 = 0
            r11.setState(r12)     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r11 = 0
            r6 = r11
            r11 = r2
            if (r11 == 0) goto L_0x0086
            r11 = r2
            r11.close()     // Catch:{ IOException -> 0x0089 }
        L_0x0086:
            r11 = r6
            r0 = r11
        L_0x0088:
            return r0
        L_0x0089:
            r11 = move-exception
            r8 = r11
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "error while closing the file after calculating crc"
            r12.<init>((java.lang.String) r13)
            throw r11
        L_0x0099:
            r11 = r5
            long r11 = r11.getValue()     // Catch:{ IOException -> 0x00b9, Exception -> 0x00d3 }
            r6 = r11
            r11 = r2
            if (r11 == 0) goto L_0x00a6
            r11 = r2
            r11.close()     // Catch:{ IOException -> 0x00a9 }
        L_0x00a6:
            r11 = r6
            r0 = r11
            goto L_0x0088
        L_0x00a9:
            r11 = move-exception
            r8 = r11
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "error while closing the file after calculating crc"
            r12.<init>((java.lang.String) r13)
            throw r11
        L_0x00b9:
            r11 = move-exception
            r3 = r11
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x00c8 }
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r3
            r12.<init>((java.lang.Throwable) r13)     // Catch:{ all -> 0x00c8 }
            throw r11     // Catch:{ all -> 0x00c8 }
        L_0x00c8:
            r11 = move-exception
            r9 = r11
            r11 = r2
            if (r11 == 0) goto L_0x00d1
            r11 = r2
            r11.close()     // Catch:{ IOException -> 0x00e2 }
        L_0x00d1:
            r11 = r9
            throw r11
        L_0x00d3:
            r11 = move-exception
            r3 = r11
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x00c8 }
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r3
            r12.<init>((java.lang.Throwable) r13)     // Catch:{ all -> 0x00c8 }
            throw r11     // Catch:{ all -> 0x00c8 }
        L_0x00e2:
            r11 = move-exception
            r10 = r11
            net.lingala.zip4j.exception.ZipException r11 = new net.lingala.zip4j.exception.ZipException
            r16 = r11
            r11 = r16
            r12 = r16
            java.lang.String r13 = "error while closing the file after calculating crc"
            r12.<init>((java.lang.String) r13)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.CRCUtil.computeFileCRC(java.lang.String, net.lingala.zip4j.progress.ProgressMonitor):long");
    }
}
