package com.tencent.open.a;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;

/* compiled from: ProGuard */
public class d {

    /* compiled from: ProGuard */
    public static final class a {
        public static final boolean a(int i, int i2) {
            int i3 = i2;
            return i3 == (i & i3);
        }
    }

    /* compiled from: ProGuard */
    public static final class b {
        public static boolean a() {
            String externalStorageState = Environment.getExternalStorageState();
            return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
        }

        public static c b() {
            if (!a()) {
                return null;
            }
            return c.b(Environment.getExternalStorageDirectory());
        }
    }

    /* compiled from: ProGuard */
    public static class c {
        private File a;
        private long b;
        private long c;

        public c() {
        }

        public static c b(File file) {
            c cVar;
            StatFs statFs;
            File file2 = file;
            new c();
            c cVar2 = cVar;
            cVar2.a(file2);
            new StatFs(file2.getAbsolutePath());
            StatFs statFs2 = statFs;
            long blockSize = (long) statFs2.getBlockSize();
            long blockCount = (long) statFs2.getBlockCount();
            long availableBlocks = (long) statFs2.getAvailableBlocks();
            cVar2.a(blockCount * blockSize);
            cVar2.b(availableBlocks * blockSize);
            return cVar2;
        }

        public File a() {
            return this.a;
        }

        public void a(long j) {
            long j2 = j;
            this.b = j2;
        }

        public void a(File file) {
            File file2 = file;
            this.a = file2;
        }

        public long b() {
            return this.b;
        }

        public void b(long j) {
            long j2 = j;
            this.c = j2;
        }

        public long c() {
            return this.c;
        }

        public String toString() {
            Object[] objArr = new Object[3];
            objArr[0] = a().getAbsolutePath();
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(c());
            Object[] objArr3 = objArr2;
            objArr3[2] = Long.valueOf(b());
            return String.format("[%s : %d / %d]", objArr3);
        }
    }

    /* renamed from: com.tencent.open.a.d$d  reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public static final class C0008d {
        @SuppressLint({"SimpleDateFormat"})
        public static SimpleDateFormat a(String str) {
            SimpleDateFormat simpleDateFormat;
            new SimpleDateFormat(str);
            return simpleDateFormat;
        }
    }
}
