package com.android.cglib.dx.d;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public final class s {
    private final Writer a;
    private final int b;
    private final StringBuffer c;
    private final StringBuffer d;
    private final j e;
    private final j f;

    public s(Writer writer, int i, int i2, String str) {
        if (writer == null) {
            throw new NullPointerException("out == null");
        } else if (i < 1) {
            throw new IllegalArgumentException("leftWidth < 1");
        } else if (i2 < 1) {
            throw new IllegalArgumentException("rightWidth < 1");
        } else if (str == null) {
            throw new NullPointerException("spacer == null");
        } else {
            StringWriter stringWriter = new StringWriter(1000);
            StringWriter stringWriter2 = new StringWriter(1000);
            this.a = writer;
            this.b = i;
            this.c = stringWriter.getBuffer();
            this.d = stringWriter2.getBuffer();
            this.e = new j(stringWriter, i);
            this.f = new j(stringWriter2, i2, str);
        }
    }

    public static String a(String str, int i, String str2, String str3, int i2) {
        StringWriter stringWriter = new StringWriter((str.length() + str3.length()) * 3);
        s sVar = new s(stringWriter, i, i2, str2);
        try {
            sVar.a().write(str);
            sVar.b().write(str3);
            sVar.c();
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new RuntimeException("shouldn't happen", e2);
        }
    }

    private static void a(Writer writer, int i) {
        while (i > 0) {
            writer.write(32);
            i--;
        }
    }

    private static void a(StringBuffer stringBuffer, Writer writer) {
        int length = stringBuffer.length();
        if (length != 0 && stringBuffer.charAt(length - 1) != 10) {
            writer.write(10);
        }
    }

    private void d() {
        int indexOf;
        while (true) {
            int indexOf2 = this.c.indexOf("\n");
            if (indexOf2 >= 0 && (indexOf = this.d.indexOf("\n")) >= 0) {
                if (indexOf2 != 0) {
                    this.a.write(this.c.substring(0, indexOf2));
                }
                if (indexOf != 0) {
                    a(this.a, this.b - indexOf2);
                    this.a.write(this.d.substring(0, indexOf));
                }
                this.a.write(10);
                this.c.delete(0, indexOf2 + 1);
                this.d.delete(0, indexOf + 1);
            } else {
                return;
            }
        }
    }

    private void e() {
        a(this.c, (Writer) this.e);
        while (this.c.length() != 0) {
            this.f.write(10);
            d();
        }
    }

    private void f() {
        a(this.d, (Writer) this.f);
        while (this.d.length() != 0) {
            this.e.write(10);
            d();
        }
    }

    public Writer a() {
        return this.e;
    }

    public Writer b() {
        return this.f;
    }

    public void c() {
        try {
            a(this.c, (Writer) this.e);
            a(this.d, (Writer) this.f);
            d();
            e();
            f();
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }
}
