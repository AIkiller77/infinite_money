package com.tencent.open.a;

import com.tencent.open.a.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* compiled from: ProGuard */
public class b {
    private static SimpleDateFormat a = d.C0008d.a("yy.MM.dd.HH");
    private String b = "Tracer.File";
    private int c = Integer.MAX_VALUE;
    private int d = Integer.MAX_VALUE;
    private int e = 4096;
    private long f = 10000;
    private File g;
    private int h = 10;
    private String i = ".log";
    private long j = Long.MAX_VALUE;

    public b(File file, int i2, int i3, int i4, String str, long j2, int i5, String str2, long j3) {
        a(file);
        b(i2);
        a(i3);
        c(i4);
        a(str);
        a(j2);
        d(i5);
        b(str2);
        b(j3);
    }

    private File c(long j2) {
        File file;
        File b2 = b();
        Object obj = "";
        try {
            File file2 = file;
            new File(b2, c(d(j2)));
            b2 = file2;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return b2;
    }

    private String c(String str) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("com.tencent.mobileqq_connectSdk.").append(str).append(".log").toString();
    }

    private String d(long j2) {
        SimpleDateFormat simpleDateFormat;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j2);
        new SimpleDateFormat("yy.MM.dd.HH");
        return simpleDateFormat.format(instance.getTime());
    }

    public File a() {
        return c(System.currentTimeMillis());
    }

    public void a(int i2) {
        int i3 = i2;
        this.c = i3;
    }

    public void a(long j2) {
        long j3 = j2;
        this.f = j3;
    }

    public void a(File file) {
        File file2 = file;
        this.g = file2;
    }

    public void a(String str) {
        String str2 = str;
        this.b = str2;
    }

    public File b() {
        File e2 = e();
        boolean mkdirs = e2.mkdirs();
        return e2;
    }

    public void b(int i2) {
        int i3 = i2;
        this.d = i3;
    }

    public void b(long j2) {
        long j3 = j2;
        this.j = j3;
    }

    public void b(String str) {
        String str2 = str;
        this.i = str2;
    }

    public String c() {
        return this.b;
    }

    public void c(int i2) {
        int i3 = i2;
        this.e = i3;
    }

    public int d() {
        return this.e;
    }

    public void d(int i2) {
        int i3 = i2;
        this.h = i3;
    }

    public File e() {
        return this.g;
    }

    public int f() {
        return this.h;
    }
}
