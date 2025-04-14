package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: ProGuard */
public final class a {
    private static final l a;
    /* access modifiers changed from: private */
    public static final m b;

    /* renamed from: com.tencent.open.utils.a$a  reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private static class C0011a {
        Properties a;
        byte[] b;

        private C0011a() {
            Properties properties;
            new Properties();
            this.a = properties;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0011a(AnonymousClass1 r4) {
            this();
            AnonymousClass1 r1 = r4;
        }

        /* access modifiers changed from: package-private */
        public void a(byte[] bArr) throws IOException {
            Object obj;
            m mVar;
            InputStream inputStream;
            Throwable th;
            StringBuilder sb;
            byte[] bArr2 = bArr;
            if (null != bArr2) {
                ByteBuffer wrap = ByteBuffer.wrap(bArr2);
                int length = a.b.a().length;
                byte[] bArr3 = new byte[length];
                ByteBuffer byteBuffer = wrap.get(bArr3);
                new m(bArr3);
                if (!a.b.equals(obj)) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new ProtocolException(sb.append("unknow protocl [").append(Arrays.toString(bArr2)).append("]").toString());
                    throw th2;
                } else if (bArr2.length - length > 2) {
                    byte[] bArr4 = new byte[2];
                    ByteBuffer byteBuffer2 = wrap.get(bArr4);
                    new m(bArr4);
                    int b2 = mVar.b();
                    if ((bArr2.length - length) - 2 >= b2) {
                        byte[] bArr5 = new byte[b2];
                        ByteBuffer byteBuffer3 = wrap.get(bArr5);
                        new ByteArrayInputStream(bArr5);
                        this.a.load(inputStream);
                        int length2 = ((bArr2.length - length) - b2) - 2;
                        if (length2 > 0) {
                            this.b = new byte[length2];
                            ByteBuffer byteBuffer4 = wrap.get(this.b);
                        }
                    }
                }
            }
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("ApkExternalInfo [p=").append(this.a).append(", otherData=").append(Arrays.toString(this.b)).append("]").toString();
        }
    }

    static {
        l lVar;
        m mVar;
        new l(InternalZipConstants.ENDSIG);
        a = lVar;
        new m(38651);
        b = mVar;
    }

    public static String a(File file) throws IOException {
        return a(file, "channelNo");
    }

    /* JADX INFO: finally extract failed */
    public static String a(File file, String str) throws IOException {
        RandomAccessFile randomAccessFile;
        C0011a aVar;
        String str2 = str;
        RandomAccessFile randomAccessFile2 = null;
        try {
            new RandomAccessFile(file, InternalZipConstants.READ_MODE);
            randomAccessFile2 = randomAccessFile;
            byte[] a2 = a(randomAccessFile2);
            if (null == a2) {
                if (null != randomAccessFile2) {
                    randomAccessFile2.close();
                }
                return null;
            }
            new C0011a((AnonymousClass1) null);
            C0011a aVar2 = aVar;
            aVar2.a(a2);
            String property = aVar2.a.getProperty(str2);
            if (null != randomAccessFile2) {
                randomAccessFile2.close();
            }
            return property;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (null != randomAccessFile2) {
                randomAccessFile2.close();
            }
            throw th2;
        }
    }

    /* JADX WARNING: type inference failed for: r8v7, types: [int] */
    /* JADX WARNING: type inference failed for: r8v30, types: [int] */
    /* JADX WARNING: type inference failed for: r8v32, types: [int] */
    /* JADX WARNING: type inference failed for: r8v35, types: [int] */
    /* JADX WARNING: type inference failed for: r8v38, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(java.io.RandomAccessFile r15) throws java.io.IOException {
        /*
            r0 = r15
            r8 = r0
            long r8 = r8.length()
            r10 = 22
            long r8 = r8 - r10
            r1 = r8
            r8 = r0
            r9 = r1
            r8.seek(r9)
            com.tencent.open.utils.l r8 = a
            byte[] r8 = r8.a()
            r3 = r8
            r8 = r0
            int r8 = r8.read()
            r4 = r8
            r8 = 0
            r5 = r8
        L_0x001e:
            r8 = r4
            r9 = -1
            if (r8 == r9) goto L_0x0052
            r8 = r4
            r9 = r3
            r10 = 0
            byte r9 = r9[r10]
            if (r8 != r9) goto L_0x0060
            r8 = r0
            int r8 = r8.read()
            r4 = r8
            r8 = r4
            r9 = r3
            r10 = 1
            byte r9 = r9[r10]
            if (r8 != r9) goto L_0x0060
            r8 = r0
            int r8 = r8.read()
            r4 = r8
            r8 = r4
            r9 = r3
            r10 = 2
            byte r9 = r9[r10]
            if (r8 != r9) goto L_0x0060
            r8 = r0
            int r8 = r8.read()
            r4 = r8
            r8 = r4
            r9 = r3
            r10 = 3
            byte r9 = r9[r10]
            if (r8 != r9) goto L_0x0060
            r8 = 1
            r5 = r8
        L_0x0052:
            r8 = r5
            if (r8 != 0) goto L_0x0073
            java.util.zip.ZipException r8 = new java.util.zip.ZipException
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "archive is not a ZIP archive"
            r9.<init>(r10)
            throw r8
        L_0x0060:
            r8 = r0
            r9 = r1
            r11 = 1
            long r9 = r9 - r11
            r13 = r9
            r9 = r13
            r11 = r13
            r1 = r11
            r8.seek(r9)
            r8 = r0
            int r8 = r8.read()
            r4 = r8
            goto L_0x001e
        L_0x0073:
            r8 = r0
            r9 = r1
            r11 = 16
            long r9 = r9 + r11
            r11 = 4
            long r9 = r9 + r11
            r8.seek(r9)
            r8 = 2
            byte[] r8 = new byte[r8]
            r6 = r8
            r8 = r0
            r9 = r6
            r8.readFully(r9)
            com.tencent.open.utils.m r8 = new com.tencent.open.utils.m
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r6
            r9.<init>((byte[]) r10)
            int r8 = r8.b()
            r7 = r8
            r8 = r7
            if (r8 != 0) goto L_0x009b
            r8 = 0
            r0 = r8
        L_0x009a:
            return r0
        L_0x009b:
            r8 = r7
            byte[] r8 = new byte[r8]
            r6 = r8
            r8 = r0
            r9 = r6
            int r8 = r8.read(r9)
            r8 = r6
            r0 = r8
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.a.a(java.io.RandomAccessFile):byte[]");
    }
}
