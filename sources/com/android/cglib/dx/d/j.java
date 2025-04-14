package com.android.cglib.dx.d;

import java.io.FilterWriter;
import java.io.Writer;

public final class j extends FilterWriter {
    private final String a;
    private final int b;
    private final int c;
    private int d;
    private boolean e;
    private int f;

    public j(Writer writer, int i) {
        this(writer, i, "");
    }

    public j(Writer writer, int i, String str) {
        super(writer);
        if (writer == null) {
            throw new NullPointerException("out == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("width < 0");
        } else if (str == null) {
            throw new NullPointerException("prefix == null");
        } else {
            this.b = i != 0 ? i : Integer.MAX_VALUE;
            this.c = i >> 1;
            this.a = str.length() == 0 ? null : str;
            a();
        }
    }

    private void a() {
        this.d = 0;
        this.e = this.c != 0;
        this.f = 0;
    }

    public void write(int i) {
        synchronized (this.lock) {
            if (this.e) {
                if (i == 32) {
                    this.f++;
                    if (this.f >= this.c) {
                        this.f = this.c;
                    }
                }
                this.e = false;
            }
            if (this.d == this.b && i != 10) {
                this.out.write(10);
                this.d = 0;
            }
            if (this.d == 0) {
                if (this.a != null) {
                    this.out.write(this.a);
                }
                if (!this.e) {
                    for (int i2 = 0; i2 < this.f; i2++) {
                        this.out.write(32);
                    }
                    this.d = this.f;
                }
            }
            this.out.write(i);
            if (i == 10) {
                a();
            } else {
                this.d++;
            }
        }
    }

    public void write(String str, int i, int i2) {
        synchronized (this.lock) {
            while (i2 > 0) {
                try {
                    write(str.charAt(i));
                    i++;
                    i2--;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void write(char[] cArr, int i, int i2) {
        synchronized (this.lock) {
            while (i2 > 0) {
                try {
                    write(cArr[i]);
                    i++;
                    i2--;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
