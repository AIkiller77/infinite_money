package net.lingala.zip4j.io;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class SplitOutputStream extends OutputStream {
    private long bytesWrittenForThisPart;
    private int currSplitFileCounter;
    private File outFile;
    private RandomAccessFile raf;
    private long splitLength;
    private File zipFile;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SplitOutputStream(java.lang.String r8) throws java.io.FileNotFoundException, net.lingala.zip4j.exception.ZipException {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            boolean r3 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r3)
            if (r3 == 0) goto L_0x0017
            java.io.File r3 = new java.io.File
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
        L_0x0013:
            r2.<init>((java.io.File) r3)
            return
        L_0x0017:
            r3 = 0
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.io.SplitOutputStream.<init>(java.lang.String):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SplitOutputStream(File file) throws FileNotFoundException, ZipException {
        this(file, -1);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SplitOutputStream(java.lang.String r11, long r12) throws java.io.FileNotFoundException, net.lingala.zip4j.exception.ZipException {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r4 = r0
            r5 = r1
            boolean r5 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r5)
            if (r5 != 0) goto L_0x0019
            java.io.File r5 = new java.io.File
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r1
            r6.<init>(r7)
        L_0x0014:
            r6 = r2
            r4.<init>((java.io.File) r5, (long) r6)
            return
        L_0x0019:
            r5 = 0
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.io.SplitOutputStream.<init>(java.lang.String, long):void");
    }

    public SplitOutputStream(File file, long j) throws FileNotFoundException, ZipException {
        RandomAccessFile randomAccessFile;
        Throwable th;
        File file2 = file;
        long j2 = j;
        if (j2 < 0 || j2 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            new RandomAccessFile(file2, InternalZipConstants.WRITE_MODE);
            this.raf = randomAccessFile;
            this.splitLength = j2;
            this.outFile = file2;
            this.zipFile = file2;
            this.currSplitFileCounter = 0;
            this.bytesWrittenForThisPart = 0;
            return;
        }
        Throwable th2 = th;
        new ZipException("split length less than minimum allowed split length of 65536 Bytes");
        throw th2;
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    public void write(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        write(bArr2, 0, bArr2.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        Throwable th;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (i4 > 0) {
            if (this.splitLength == -1) {
                this.raf.write(bArr2, i3, i4);
                this.bytesWrittenForThisPart += (long) i4;
            } else if (this.splitLength < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                Throwable th2 = th;
                new IOException("split length less than minimum allowed split length of 65536 Bytes");
                throw th2;
            } else if (this.bytesWrittenForThisPart >= this.splitLength) {
                startNextSplitFile();
                this.raf.write(bArr2, i3, i4);
                this.bytesWrittenForThisPart = (long) i4;
            } else if (this.bytesWrittenForThisPart + ((long) i4) <= this.splitLength) {
                this.raf.write(bArr2, i3, i4);
                this.bytesWrittenForThisPart += (long) i4;
            } else if (isHeaderData(bArr2)) {
                startNextSplitFile();
                this.raf.write(bArr2, i3, i4);
                this.bytesWrittenForThisPart = (long) i4;
            } else {
                this.raf.write(bArr2, i3, (int) (this.splitLength - this.bytesWrittenForThisPart));
                startNextSplitFile();
                this.raf.write(bArr2, i3 + ((int) (this.splitLength - this.bytesWrittenForThisPart)), (int) (((long) i4) - (this.splitLength - this.bytesWrittenForThisPart)));
                this.bytesWrittenForThisPart = ((long) i4) - (this.splitLength - this.bytesWrittenForThisPart);
            }
        }
    }

    private void startNextSplitFile() throws IOException {
        Throwable th;
        StringBuilder sb;
        String sb2;
        File file;
        StringBuilder sb3;
        File file2;
        File file3;
        RandomAccessFile randomAccessFile;
        Throwable th2;
        Throwable th3;
        StringBuilder sb4;
        File file4;
        StringBuilder sb5;
        try {
            String zipFileNameWithoutExt = Zip4jUtil.getZipFileNameWithoutExt(this.outFile.getName());
            String absolutePath = this.zipFile.getAbsolutePath();
            if (this.outFile.getParent() == null) {
                sb2 = "";
            } else {
                new StringBuilder();
                sb2 = sb.append(this.outFile.getParent()).append(System.getProperty("file.separator")).toString();
            }
            String str = sb2;
            if (this.currSplitFileCounter < 9) {
                new StringBuilder();
                new File(sb5.append(str).append(zipFileNameWithoutExt).append(".z0").append(this.currSplitFileCounter + 1).toString());
                file2 = file4;
            } else {
                new StringBuilder();
                new File(sb3.append(str).append(zipFileNameWithoutExt).append(".z").append(this.currSplitFileCounter + 1).toString());
                file2 = file;
            }
            this.raf.close();
            if (file2.exists()) {
                Throwable th4 = th3;
                new StringBuilder();
                new IOException(sb4.append("split file: ").append(file2.getName()).append(" already exists in the current directory, cannot rename this file").toString());
                throw th4;
            } else if (!this.zipFile.renameTo(file2)) {
                Throwable th5 = th2;
                new IOException("cannot rename newly created split file");
                throw th5;
            } else {
                new File(absolutePath);
                this.zipFile = file3;
                new RandomAccessFile(this.zipFile, InternalZipConstants.WRITE_MODE);
                this.raf = randomAccessFile;
                this.currSplitFileCounter++;
            }
        } catch (ZipException e) {
            ZipException zipException = e;
            Throwable th6 = th;
            new IOException(zipException.getMessage());
            throw th6;
        }
    }

    private boolean isHeaderData(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 == null || bArr2.length < 4) {
            return false;
        }
        int readIntLittleEndian = Raw.readIntLittleEndian(bArr2, 0);
        long[] allHeaderSignatures = Zip4jUtil.getAllHeaderSignatures();
        if (allHeaderSignatures != null && allHeaderSignatures.length > 0) {
            for (int i = 0; i < allHeaderSignatures.length; i++) {
                if (allHeaderSignatures[i] != 134695760 && allHeaderSignatures[i] == ((long) readIntLittleEndian)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkBuffSizeAndStartNextSplitFile(int i) throws ZipException {
        Throwable th;
        Throwable th2;
        int i2 = i;
        if (i2 < 0) {
            Throwable th3 = th2;
            new ZipException("negative buffersize for checkBuffSizeAndStartNextSplitFile");
            throw th3;
        } else if (isBuffSizeFitForCurrSplitFile(i2)) {
            return false;
        } else {
            try {
                startNextSplitFile();
                this.bytesWrittenForThisPart = 0;
                return true;
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th4 = th;
                new ZipException((Throwable) iOException);
                throw th4;
            }
        }
    }

    public boolean isBuffSizeFitForCurrSplitFile(int i) throws ZipException {
        Throwable th;
        int i2 = i;
        if (i2 < 0) {
            Throwable th2 = th;
            new ZipException("negative buffersize for isBuffSizeFitForCurrSplitFile");
            throw th2;
        } else if (this.splitLength < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            return true;
        } else {
            return this.bytesWrittenForThisPart + ((long) i2) <= this.splitLength;
        }
    }

    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    public void close() throws IOException {
        if (this.raf != null) {
            this.raf.close();
        }
    }

    public void flush() throws IOException {
    }

    public long getFilePointer() throws IOException {
        return this.raf.getFilePointer();
    }

    public boolean isSplitZipFile() {
        return this.splitLength != -1;
    }

    public long getSplitLength() {
        return this.splitLength;
    }

    public int getCurrSplitFileCounter() {
        return this.currSplitFileCounter;
    }
}
